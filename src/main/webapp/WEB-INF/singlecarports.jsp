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

        <h4 class="singleCarportH4">Enkelt carporte</h4>
        <div class="allSingleCarports">
            <c:forEach var="carport" items="${sessionScope.shoppingcart}" varStatus="status">
                <div class="oneSingleCarport">
                    <form action="fc/addtocart" method="get" id="" class="">
                        <img src="${pageContext.request.contextPath}/image/${carport.name}.png" alt="carport billede">
                        <div class="aboutCarport">
                            <h2>${carport.name}</h2>
                            <p>varenr. ${carport.id}</p>
                            <p>Carport</p>
                            <p>${carport.type}</p>
                            <p>${carport.width}cm</p>
                            <p>x${carport.length}cm</p>
                            <p>pr. stk ${carport.price} + evt fragt</p>
                        </div>
                        <form method="get" action="fc/addtocart">
                            <label for="quantity">Antal</label>
                            <input type="number" name="quantity" id="quantity" placeholder="1" value="" min="0" max="10">
                                <%--<input type="hidden" name="queriedId" value="${carport.id}"/> --%>
                            <button type="submit" name="carportID" value="${carport.id}">LÆG I KURV</button>
                        </form>
                    </form>
            </c:forEach>
        </div>

    </jsp:body>
</t:genericpage>
