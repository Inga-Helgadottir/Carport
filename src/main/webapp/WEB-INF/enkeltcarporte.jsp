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
        <c:forEach var="carport" items="${applicationScope.enkeltcarporte}" varStatus="status">
            <img class="img-fluid w-25" src="<c:url value='/IMAGES/ProductPage${carport.id}.png'/>"
                 alt="carport" type="submit"/><br>
            ID:${carport.id}<br>
            Price:${carport.price}<br>
            Dimensions:${carport.width}cm x ${carport.length}cm<br>
            <c:if test="${carport.shed_length || carport.shed_width > 0}">
                Shed Dimensions:${carport.shed_width}cm x ${carport.shed_length}cm<br>
            </c:if>
            Standard INFO:${carport.info}<br>
            <form action="${pageContext.request.contextPath}/fc/addToCart" method="post">
                <button type="submit" class=" btn btn-danger" name="CarportID" id="CarportID"
                        value="${carport.id}">tilføj til kurv
                </button>
            </form>
        </c:forEach>

    </jsp:body>
</t:genericpage>
