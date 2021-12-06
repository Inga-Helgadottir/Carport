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

        <h2 class="display-4">Standard Carporte</h2>
        <div class="allSingleCarports">
            <c:forEach var="carport" items="${applicationScope.standartcarporte}">
                <div class="oneSingleCarport">
                    <img src="${pageContext.request.contextPath}/image/${carport.name}.png" alt="carport billede">
                    <p>Varenr. #1000${carport.id}</p>
                    <div class="aboutCarport">
                        <p>Carport ${carport.type} ${carport.width}cm x${carport.length}cm pr. stk ${carport.price} + evt fragt</p>
                    </div>
                    <form method="get" action="${pageContext.request.contextPath}/fc/addtocart">
                        <label for="quantity">Antal</label>
                        <input type="number" name="quantity" id="quantity" value="${carport.quantity}" min="1" max="10">

                        <button class="btn btn-success my-2" type="submit" name="carportID" value="${carport.id}">Læg I Kurv</button>
                    </form>
                </div>
            </c:forEach>

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
