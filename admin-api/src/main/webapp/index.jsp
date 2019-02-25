<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.naming.*" %>
<%@ page import="service.Service" %>
<html>
    <head>
        <title>Admin-api</title>
    </head>
    <body>
        <form id="search form">
            <p><input type="text" name="city">
            <input type="submit" value="submit">
        </form>
        <% String city;
            String result;
            try {
                InitialContext initialContext = new InitialContext();
                Service service = (Service) initialContext.lookup("java:global/admin-api/AdminApiService");
                city = request.getParameter("city");

                if (!"".equals(city)) {
                    result = service.serve(city);
                } else {
                    result = "enter city";
                }
            } catch (NamingException e) {
                result = "Error: " + e.getMessage();
            }
        %>
        <%= result %>
    </body>
</html>