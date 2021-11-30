<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         shoppingcart page
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:forEach var="carport" items="${sessionScope.shoppingCart}" varStatus="status">
            <div>
                <div>
                    <h2 class="">Indkøbskurv:</h2>
                    <img class="" src="${pageContext.request.contextPath}/image/${carport.name}" alt="cupcakefoto">
                    Carport ${carport.type} ${carport.width}cm x${carport.length}cm ${carport.name}
                    pr. stk ${carport.price} + evt fragt
                    <br>
                    varenr. ${carport.id}
                </div>

                <div>
                    <form method="get" action="fc/updateCommand">
                        <label for="antal">Antal</label>
                        <input type="number" name="antal" id="antal" placeholder="${carport.id}" value="" min="0"
                               max="10">
                            <%--<input type="hidden" name="queriedId" value="${carport.id}"/> --%>
                        <button type="submit" name="opdater" value="${carport.id}">Opdater</button>
                        <button type="submit" name="remove" value="${carport.id}">Remove</button>
                    </form>
                </div>
            </div>
            <%---------------add to cart command -----------------------------------------------------------------------%>
        </c:forEach>
        <div>
            <p class="">Subtotal:
                <span class="">${sessionScope.total}kr</span>
            </p>
            <a href="createorder" class="">Tjek ud</a>

        </div>

        <%---------------here-----------------------------------------------------------------------%>


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