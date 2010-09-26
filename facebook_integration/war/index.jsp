<%@ page import="com.thoughtworks.facebook.FacebookClient" %>
<%@ page import="com.thoughtworks.facebook.Person" %>
<%@ page import="com.thoughtworks.facebook.PersonFactory" %>
<%@ page import="com.thoughtworks.facebook.PersonUIAdaptor" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="styles.css"/>
</head>
<body>
<%
    FacebookClient facebookClient = new FacebookClient();
    String code = (String) request.getParameter("code");
    if (code != null) {
        if (facebookClient.setupAccessToken(code)) {

            Person person = new PersonFactory(facebookClient).me();
            //String url = "http://www.toysrus.com/search/index.jsp?kw=" + URLEncoder.encode(likes, "UTF-8");
%>
<%=new PersonUIAdaptor(person).describe()%>
<%
        }
    }
%>

<br>
<a href="<%=facebookClient.loginUrl()%>">Login to Facebook</a>


</body>
</html>