package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.Base64;

@Stateless
public class YahooRequester {

    private static final String APP_ID = "1oJIiA78";

    private static final String CONSUMER_KEY = "dj0yJmk9cHRaV3k0a0pWME5WJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PTc3";

    private static final String CONSUMER_SECRET = "5fb1cbaea91558463f1710f8e95344b7665c1538";

    private static final String URL = "https://weather-ydn-yql.media.yahoo.com/forecastrss";

    private static final String HMACSHA1 = "HmacSHA1";

    private static final String HMAC_SHA1 = "HMAC-SHA1";

    private static final String FORMAT_JSON = "format=json";

    private static final String OAUTH_CONSUMER_KEY =  "oauth_consumer_key=";

    private static final String OAUTH_NONCE =  "oauth_nonce=";

    private static final String OAUTH_SIGNATURE_METHOD =  "oauth_signature_method=";

    private static final String OAUTH_TIMESTAMP =  "oauth_timestamp=";

    private static final String OAUTH_SIGNATURE =  "oauth_signature=";

    private static final String OAUTH_VERSION =  "oauth_version=";

    private static final String LOCATION =  "location=";

    private static final String AUTHORIZATION_HEADER =  "Authorization";

    private static final String YAHOO_APP_ID_HEADER =  "Yahoo-App-Id";

    private static final String UTF8_ENCODING =  "UTF-8";


    public String makeRequestToYahoo(final String cityName) {

        long timestamp = new Date().getTime() / 1000;
        final String oauthNonce = makeOauthNonce();
        final List<String> parameters = makeParameters(oauthNonce, timestamp, cityName);
        final String signature = makeSignature(parameters);
        final String authorizationLine = buildAuthorizationLine(oauthNonce, timestamp, signature);
        final HttpHeaders headers = makeHeaders(authorizationLine);
        final HttpEntity entity = new HttpEntity(headers);
        final URI uri = URI.create(URL + "?" + LOCATION + cityName + "&" + FORMAT_JSON);
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return result.getBody();
    }

    private String makeOauthNonce() {
        byte[] nonce = new byte[32];
        final Random rand = new Random();
        rand.nextBytes(nonce);
        return new String(nonce).replaceAll("\\W", "");
    }

    private List<String> makeParameters(final String oauthNonce, long timestamp, final String cityName) {

        final String encodedCityName = encodeCityName(cityName);

        final List<String> parameters = new ArrayList<>();

        parameters.add(OAUTH_CONSUMER_KEY + CONSUMER_KEY);
        parameters.add(OAUTH_NONCE + oauthNonce);
        parameters.add(OAUTH_SIGNATURE_METHOD + HMAC_SHA1);
        parameters.add(OAUTH_TIMESTAMP + timestamp);
        parameters.add(OAUTH_VERSION + "1.0");
        parameters.add(LOCATION + encodedCityName);
        parameters.add(FORMAT_JSON);

        Collections.sort(parameters);

        return parameters;
    }

    private String encodeCityName(final String cityName) {

        String encodedCityName;

        try {
            encodedCityName = URLEncoder.encode(cityName, UTF8_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error while encoding city name: " + e.getMessage());
        }

        return encodedCityName;
    }

    private String makeSignature(final List<String> parameters) {

        String signature = "";

        final StringBuilder parametersList = new StringBuilder();

        for (int i = 0; i < parameters.size(); i++) {
            if (i > 0) {
                parametersList.append("&");
            }
            parametersList.append(parameters.get(i));
        }

        try {
            final String signatureString = "GET&" +
                    URLEncoder.encode(URL, UTF8_ENCODING) + "&" +
                    URLEncoder.encode(parametersList.toString(), UTF8_ENCODING);

            final SecretKeySpec signingKey = new SecretKeySpec((CONSUMER_SECRET + "&").getBytes(), HMACSHA1);

            final Mac mac = Mac.getInstance(HMACSHA1);
            mac.init(signingKey);

            byte[] rawHMAC = mac.doFinal(signatureString.getBytes());

            final Base64.Encoder encoder = Base64.getEncoder();

            signature = encoder.encodeToString(rawHMAC);

        } catch (Exception e) {
            throw new RuntimeException("Unable to append signature: " + e.getMessage());
        }

        return signature;
    }

    private String buildAuthorizationLine(final String oauthNonce, long timestamp, final String signature) {

        StringBuilder authorizationLineBuilder = new StringBuilder();

        authorizationLineBuilder.append("OAuth ")
                .append(OAUTH_CONSUMER_KEY).append("\"").append(CONSUMER_KEY).append("\", ")
                .append(OAUTH_NONCE).append("\"").append(oauthNonce).append("\", ")
                .append(OAUTH_TIMESTAMP).append("\"").append(timestamp).append("\", ")
                .append(OAUTH_SIGNATURE_METHOD).append("\"").append(HMAC_SHA1).append("\", ")
                .append(OAUTH_SIGNATURE).append("\"").append(signature).append("\", ")
                .append(OAUTH_VERSION).append("\"1.0\"");

        return authorizationLineBuilder.toString();
    }

    private HttpHeaders makeHeaders(final String authorizationLine) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION_HEADER, authorizationLine);
        headers.add(YAHOO_APP_ID_HEADER, APP_ID);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
