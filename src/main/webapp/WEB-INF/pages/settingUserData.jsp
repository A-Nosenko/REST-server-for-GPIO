<%@ include file="/WEB-INF/pages/jspf/taglib.jspf" %>
<html>
<head>
  <link href="<c:url value="/resources/images/head.ico" />" rel="shortcut icon" type="image/x-icon">
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type = "text/css">

  <title>User data</title>
</head>
<body>

<div align="center">
<span class="error">${error}</span><br/>

<sf:form action="/updateUserData" method="post">
  <input type="hidden" name="login" value="${pageContext.request.remoteUser}"/>
  <br/>
  <br/>
  <br/>
  New login:<br/>
  <input type="text" name="newLogin"/>
  <br/><br/>
  Password:<br/>
  <input type="password" name="password"/>
  <br/><br/>
  New password:<br/>
  <input type="password" name="newPassword"/>
  <br/><br/>
  Repeat password:<br/>
  <input type="password" name="newPasswordRe"/>
  <br/><br/>
  <button type="submit" class="button">Ok</button>
</sf:form>
</div>

<div align="center">
  <sf:form action="/" method="get">
    <button type="submit" class="button">Return</button>
  </sf:form>
</div>
</body>
</html>
