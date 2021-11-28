<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         enkelt carporte
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h4>Enkelt carporte</h4><br>
        <c:forEach var="carport" items="${requestScope.enkeltcarporte}" varStatus="status">
            ${carport.}

        </c:forEach>

    </jsp:body>
</t:genericpage>
