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
    <sf:form action="/logout" method="post">
        <button type="submit" class="button_reduced">Log out</button>
    </sf:form>

</div>

<div align="left">

    <sf:form action="/settingUserData" method="get">
        <button type="submit" class="button">User setting</button>
    </sf:form>

    <sf:form action="/settingRelayNames" method="get">
        <button type="submit" class="button">Relays setting</button>
    </sf:form>

</div>

<div align="center">
    <sf:form action="/" method="get">
        <button type="submit" class="button">Refresh</button>
    </sf:form>
</div>

<c:forEach items="${relaysList}" var="relay">
<table width="90%" align="center">
    <thead>
    <tr>
        <td width="10%"></td>
        <td width="10%"></td>
        <td width="40%"></td>
        <td width="20%"></td>
        <td width="10%"></td>

    </tr>
    </thead>
        <tr>

            <td>
                <h3> ${relay.id} </h3>
                <h4> ${relay.technicalName} </h4>
            </td>
            <td>
                <img width="100%" src=${relay.enabled ? "resources/images/on.png" : "resources/images/off.png"} >
            </td>

            <td>
                <h3>${relay.customName}</h3> <br/>


            </td>
            <sf:form method="post">
            <td>
                <c:if test="${relay.enabled and relay.withTimer}">
                Time to go:
                <fmt:formatDate value="${relay.dateToGo}" pattern="dd-MM-yyyy HH:mm:ss" /></h3>
                <br/>
                <br/>
                </c:if>
                <label for="hour">HOUR:</label>
                <input type="number" size="4" id="hour" name="hour" min="0" max="24" step="1" value="0">
                <label for="min">MIN:</label>
                <input type="number" size="4" id="min" name="min" min="0" max="55" step="5" value="0">
                <br/>
                <br/>
                <c:if test="${relay.enabled}">
                    <button type="submit" formaction="/addTime" class="button">Add time</button>
                </c:if>
            </td>

            <td>
                <input type="hidden" name="id" value=${relay.id}>
                <input type="hidden" name="status" value=${relay.enabled ? false : true}>
                <button type="submit" formaction="/switchRelay" class="button">${relay.enabled ? "Off" : "On"}</button>
                </sf:form>
            </td>

        </tr>
    <hr color="white"/>
    </c:forEach>
</table>
<div align="center">
    <sf:form action="/" method="get">
        <button type="submit" class="button">Refresh</button>
    </sf:form>
</div>
</body>
</html>
