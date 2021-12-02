<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         shoppingcart page
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>


        <div class="container my-5">
            <h2 class="display-4"> Indkøbskurv</h2>

            <div class="row mt-4">
                <hr class="border-white">

                <c:forEach var="carport" items="${sessionScope.shoppingcart}" varStatus="status">
                    <div class="col-12">
                        <div class="mb-4">
                            <img class="" src="${pageContext.request.contextPath}/image/${carport.name}.png"
                                 alt="cupcakefoto">
                            <p class=""> varenr. ${carport.id}<br> Carport ${carport.type} ${carport.width}cm
                                x${carport.length}cm ${carport.name} pr. stk ${carport.price} + evt fragt </p>

                            <form method="post" action="${pageContext.request.contextPath}/fc/updatecommand">
                                <label for="quantity">Antal</label>
                                <input type="number" name="quantity" id="quantity" value="${carport.quantity}" min="1"
                                       max="10">
                                <input type="hidden" name="carportID" value="${carport.id}"/>
                                <button class="btn btn-primary" type="submit" name="update" value="${status.index}">Opdater</button>
                                <button class="btn btn-primary" type="submit" name="remove" value="${status.index}">Fjern</button>
                            </form>
                        </div>
                    </div>
                    <hr class="border-white">

                </c:forEach>
            </div>
        </div>


        <div>
            <p class="">Subtotal:
                <span class="">${sessionScope.total}kr</span>
            </p>
            <c:if test="${sessionScope.shoppingcart.size() > 0}">
                <a href="${pageContext.request.contextPath}/fc/createorderstandard" class="btn btn-primary">Tjek ud</a>
            </c:if>


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