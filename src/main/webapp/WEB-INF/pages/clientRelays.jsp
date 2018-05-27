<%@ include file="/WEB-INF/pages/jspf/taglib.jspf" %>
<html>
<head>

    <link href="<c:url value="/resources/images/head.ico" />" rel="shortcut icon" type="image/x-icon">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css">

    <title>Controls</title>
</head>
<body>
<div align="right">
    <h3>Welcome, ${pageContext.request.remoteUser} !</h3>
    <sf:form action="${contextPath}/logout" method="post">
        <button type="submit" class="button_reduced">Log out</button>
    </sf:form>

</div>

<table width="90%" align="center">
    <thead>
    <tr>
        <td width="10%"></td>
        <td width="10%"></td>
        <td width="80%"></td>
        <td width="10%"></td>

    </tr>
    </thead>
    <c:forEach items="${relaysList}" var="relay">

        <tr>
            <td>
                <h3>${relay.technicalName}</h3> <br/>
            </td>
            <td>
                <img width="100%" src=${relay.enabled ? "/resources/images/on.png" : "/resources/images/off.png"}/>
            </td>

            <td>
                <h3>${relay.customName}</h3> <br/>

            </td>

            <td>
                <sf:form action="/switchRelay" method="post">
                    <input type="hidden" name="id" value=${relay.id}>
                    <input type="hidden" name="status" value=${relay.enabled ? false : true}>
                    <button type="submit" class="button">${relay.enabled ? "Off" : "On"}</button>
                </sf:form>
            </td>

            </div>
        </tr>
    </c:forEach>
</table>
</body>
</html>
