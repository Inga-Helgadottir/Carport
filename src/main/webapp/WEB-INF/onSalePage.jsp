<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Tilbud
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.role == 'customer'}">
            <h1 class="mb-5 mt-4 h1">Dine tilbud</h1>
            <form method="get" class="queries">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Tilbud</th>
                        <th scope="col">Pris før</th>
                        <th scope="col">Status</th>
                        <th scope="col">Carport navn</th>
                        <th scope="col">Carport type</th>
                        <th scope="col">Carport mål</th>
                        <th scope="col">Skur mål</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${sessionScope.queryMsgs}" varStatus="status">
                        <tr>
                            <th scope="row">
                                <input class="checkbox" type="checkbox" name="orderCheck" value="${item.queryId}/${item.orderId}/${item.msg}" onclick="onlyOne(this)">
                                    ${item.msg}
                            </th>
                            <td>${item.singlePrice}</td>
                            <td>${item.status}</td>
                            <td>${item.carportName}</td>
                            <td>${item.carportType}</td>
                            <td>${item.length} x ${item.width} x ${item.height}</td>
                            <c:choose>
                                <c:when test="${item.shedLength == 0}">
                                    <td>Uden skur</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${item.shedLength} x ${item.shedWidth} x ${item.height}</td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="d-flex justify-content-between">
                    <button type="reset" href="${pageContext.request.contextPath}/fc/acceptofferpage" class="btn-success myBtn">Ja tak</button>
                    <button type="reset" href="${pageContext.request.contextPath}/fc/rejectofferpage" class="btn-danger myBtn">Nej tak</button>
                </div>
            </form>
                <!--------------------------------------------------------------------------------------------------------------------------------------------------------------------->
        </c:if>
       <h2 class="mb-5 mt-4 h2">Andre tilbud</h2>
        <div class="onSaleItems">
            <div class="onSaleItem">
                <h3 class="text-center h3">Ugens tilbud</h3>
                <img src="${pageContext.request.contextPath}/images/tilbudsfeed.jpg" alt="tilbud" class="onSaleImg">
                <p class="appliesUntil d-flex flex-column text-center ">Gælder i perioden <span>06-12-2021 - 20-01-2022</span></p>
                <button class="saleBtn btn btn-primary" type="submit">SE TILBUD</button>
            </div>
            <!------------------------------------------------------------------------------------------------------------------------>
            <div class="onSaleItem">
                <h3 class="text-center h3">Ugens tilbud</h3>
                <img src="${pageContext.request.contextPath}/images/tilbudsfeed.jpg" alt="tilbud" class="onSaleImg">
                <p class="appliesUntil d-flex flex-column text-center">Gælder i perioden <span>06-12-2021 - 20-01-2022</span></p>
                <button class="saleBtn btn btn-primary" type="submit">SE TILBUD</button>
            </div>
            <div class="onSaleItem">
                <h3 class="text-center h3">Ugens tilbud</h3>
                <img src="${pageContext.request.contextPath}/images/tilbudsfeed.jpg" alt="tilbud" class="onSaleImg">
                <p class="appliesUntil d-flex flex-column text-center">Gælder i perioden <span>06-12-2021 - 20-01-2022</span></p>
                <button class="saleBtn btn btn-primary" type="submit">SE TILBUD</button>
            </div>
            <div class="onSaleItem">
                <h3 class="text-center h3">Ugens tilbud</h3>
                <img src="${pageContext.request.contextPath}/images/tilbudsfeed.jpg" alt="tilbud" class="onSaleImg">
                <p class="appliesUntil d-flex flex-column text-center">Gælder i perioden <span>06-12-2021 - 20-01-2022</span></p>
                <button class="saleBtn btn btn-primary" type="submit">SE TILBUD</button>
            </div>
            <div class="onSaleItem">
                <h3 class="text-center h3">Ugens tilbud</h3>
                <img src="${pageContext.request.contextPath}/images/tilbudsfeed.jpg" alt="tilbud" class="onSaleImg">
                <p class="appliesUntil d-flex flex-column text-center">Gælder i perioden <span>06-12-2021 - 20-01-2022</span></p>
                <button class="saleBtn btn btn-primary" type="submit">SE TILBUD</button>
            </div>
            <div class="onSaleItem">
                <h3 class="text-center h3">Ugens tilbud</h3>
                <img src="${pageContext.request.contextPath}/images/tilbudsfeed.jpg" alt="tilbud" class="onSaleImg">
                <p class="appliesUntil d-flex flex-column text-center">Gælder i perioden <span>06-12-2021 - 20-01-2022</span></p>
                <button class="saleBtn btn btn-primary" type="submit">SE TILBUD</button>
            </div>
            <!-------------------------------------------------------------------------------------------------------------------->
        </div>

        <script>
            function onlyOne(checkbox) {
                //selects all the checkboxes
                let checkboxes = document.getElementsByName("orderCheck");
                //loops through them
                checkboxes.forEach((item) => {
                    //if this box is checked it unchecks all the others
                    if (item !== checkbox) item.checked = false;
                })
            }
        </script>
    </jsp:body>
</t:genericpage>