<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header"> Demo Page for Customer Roles </jsp:attribute>

    <jsp:attribute name="footer"> </jsp:attribute>

    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        Role: ${sessionScope.role}
        <br><br><h2>Carporte</h2>
        <div class="dropdown">
            <button class="dropbtn">Dropdown</button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/fc/standartcarportpage">Enkelt Carporte</a>
                <a href="${pageContext.request.contextPath}/fc/dobbeltcarporte">Dobbelt Carporte</a>
                <a href="${pageContext.request.contextPath}/fc/ekspertip">Expertens Tips</a>
            </div>
        </div>
    </jsp:body>
</t:genericpage>

