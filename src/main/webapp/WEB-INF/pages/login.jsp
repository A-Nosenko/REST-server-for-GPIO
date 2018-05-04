<%@ include file="/WEB-INF/pages/jspf/taglib.jspf" %>
<html>
<head>

    <link href="<c:url value="/resources/images/head.ico" />" rel="shortcut icon" type="image/x-icon">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type = "text/css">

    <title>Login</title>
</head>
<body>
<br/>
<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
<div align="center" style="vertical-align: middle">
    <sf:form action="/login" method="post">

        <input name="username" type="text" placeholder = "LOGIN" autofocus="true"/><br/><br/>
        <input name="password" type="password" placeholder="PASSWORD"/><br/><br/>


        <span class="error">${error}</span><br/>
        <c:if test = "${pageContext.request.remoteUser == null}">
            <input id="remember_me" name="remember-me" type="checkbox"/>
            <label for = "remember_me">Remember me</label><br/><br/>
            <button type="submit" class="button">Sign in</button></c:if>

        <c:if test = "${pageContext.request.remoteUser != null}">
            <h3>Welcome, ${pageContext.request.remoteUser} !</h3>
            <button value="${contextPath}/logout" class="button">Log out</button></c:if>
    </sf:form>
</div>
</body>
</html>
