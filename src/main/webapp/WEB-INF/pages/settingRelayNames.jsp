<%@ include file="/WEB-INF/pages/jspf/taglib.jspf" %>
<html>
<head>
  <link href="<c:url value="/resources/images/head.ico" />" rel="shortcut icon" type="image/x-icon">
  <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type = "text/css">

  <title>Setting</title>
</head>
<body>

<div align="center">
  <sf:form action="/" method="get">
    <button type="submit" class="button">Return</button>
  </sf:form>
</div>

<c:forEach items="${relaysList}" var="relay">
<table width="90%" align="center">
  <thead>
  <tr>
    <td width="10%"></td>
    <td width="10%"></td>
    <td width="80%"></td>
    <td width="10%"></td>

  </tr>
  </thead>
  <tr>
    <td>
      <h3> ${relay.id} </h3>
      <h4> ${relay.technicalName} </h4>
    </td>
    <td>
      <img width="100%" src=${relay.enabled ? "/resources/images/on.png" : "/resources/images/off.png"}/>
    </td>
    <sf:form action="/setRelayName" method="post">
    <td>
      <input type="text" name="customName" value=${relay.customName}>
    </td>

    <td>
        <input type="hidden" name="id" value=${relay.id}>
        <button type="submit" class="button">Rename</button>
    </td>
    </sf:form>
  </tr>
  <hr color="white"/>
  </c:forEach>
</table>
<div align="center">
  <sf:form action="/" method="get">
    <button type="submit" class="button">Return</button>
  </sf:form>
</div>
</body>
</html>
