<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header"> Demo Page for Customer Roles </jsp:attribute>

    <jsp:attribute name="footer"> </jsp:attribute>

    <jsp:body>
        <h1>Hello ${sessionScope.email} </h1>
        Role: ${sessionScope.role}
        ID: ${sessionScope.userID}
        <br><br><h2>Carporte</h2>
        <div class="dropdown">
            <button class="dropbtn">Dropdown</button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/fc/standartcarportpage">Standart Carporte</a>
                <a href="${pageContext.request.contextPath}/fc/quickbuildpage">Quick Byg</a>
                <a href="${pageContext.request.contextPath}/fc/ekspertip">Expertens Tips</a>
            </div>
        </div>

        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>

        <c:if test="${not empty param.msg}">
            <p style="font-size: large">${param.msg}</p>
        </c:if>
    </jsp:body>
</t:genericpage>