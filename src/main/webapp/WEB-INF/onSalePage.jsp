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

       <h1 class="mb-5 mt-4 h1">Tilbud</h1>
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
    </jsp:body>
</t:genericpage>