<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="javax.naming.*" %>
<%@ page import="service.AdminApiService" %>
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
                AdminApiService service = (AdminApiService) initialContext.lookup("java:global/admin-api/AdminApiServiceImpl");
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