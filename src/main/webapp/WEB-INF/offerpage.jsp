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
        <section class="allTables d-flex flex-row">
            <table class="table table-striped tableWidth firstTable">
                <thead>
                <tr>
                    <th scope="col">Kundeoplysninger</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>Fulde navn:</td>
                    <td>${requestScope.offer.user.name}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>Adresse:</td>
                    <td>${requestScope.offer.user.address}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>By:</td>
                    <td>${requestScope.offer.user.city}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>Telefon nr :</td>
                    <td>${requestScope.offer.user.telephone}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${requestScope.offer.user.email}</td><!------------------------------------------->
                </tr>
                </tbody>
            </table>
            <div class="tableWidth">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Specifikationer</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Ref. nr.:</td>
                        <td>${requestScope.offer.id}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Oprettet:</td>
                        <td>${requestScope.offer.created}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td>${requestScope.offer.status}</td><!------------------------------------------->
                    </tr>
                    </tbody>
                </table>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Carport</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Bredde:</td>
                        <td>
                                ${requestScope.offer.carport.width}
                        </td>
                    </tr>
                    <tr>
                        <td>Længde:</td>
                        <td>
                                ${requestScope.offer.carport.length}
                        </td>
                    </tr>
                    </tbody>
                </table>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Pris (Dkk):</td>
                        <td>${requestScope.offer.price}</td>
                    </tr>
                    </tbody>
                </table>
                <form action="${pageContext.request.contextPath}/fc/processoffer" method="get">
                    <input type="hidden" name="carport_id" value="${requestScope.offer.carport.id}">
                    <input type="hidden" name="query_id" value="${requestScope.offer.id}">
                    <input type="hidden" name="user_id" value="${requestScope.offer.user.id}">
                    <input type="hidden" name="offerprice" value="${requestScope.offer.price}">
                    <div class="d-flex justify-content-between">

                        <button name="accept" value="accept" class="btn btn-success yesNoBtn">Accepter</button>
                        <button name="annul" value="annul" class="btn btn-danger yesNoBtn">Annuller</button>

                    </div>
                </form>
            </div>
        </section>

    </jsp:body>
</t:genericpage>