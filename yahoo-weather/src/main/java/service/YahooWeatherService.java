package service;

/**
 * Сервис приложения
 */
public interface YahooWeatherService {
    /**
     *  Создание запроса на Yahoo, парсинг ответа и отправка в JMS очередь
     *
     * @param text  название города, по которому будет производиться запрос
     */
    void serve(String text);
}
