<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
        Se ordre information
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
                    <td>${requestScope.order.user.name}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>Adresse:</td>
                    <td>${requestScope.order.user.address}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>By:</td>
                    <td>${requestScope.order.user.city}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>Telefon nr :</td>
                    <td>${requestScope.order.user.telephone}</td><!------------------------------------------->
                </tr>
                <tr>
                    <td>Email:</td>
                    <td>${requestScope.order.user.email}</td><!------------------------------------------->
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
                        <td>${requestScope.order.id}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Oprettet:</td>
                        <td>${requestScope.order.created}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td>${requestScope.order.status}</td><!------------------------------------------->
                    </tr>
                    </tbody>
                </table>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Carport</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Bredde:</td>
                        <td>
                                ${requestScope.order.carport.width}
                        </td>
                    </tr>
                    <tr>
                        <td>Længde:</td>
                        <td>
                                ${requestScope.order.carport.length}
                        </td>
                    </tr>
                    <tr>
                        <td>skur Længde:</td>
                        <td>
                                ${requestScope.order.carport.shed_length}
                        </td>
                    </tr>
                    <tr>
                        <td>skur Bredde:</td>
                        <td>
                                ${requestScope.order.carport.shed_width}
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
                        <td>${requestScope.order.price}</td>
                    </tr>
                    </tbody>
                </table>
                    <%---svg--%>
                <form method="get" action="${pageContext.request.contextPath}/fc/showsvg">
                    <input type="hidden" name="length" value="${requestScope.order.carport.length}">
                    <input type="hidden" name="width" value="${requestScope.order.carport.width}">
                    <input type="hidden" name="shed_length" value="${requestScope.order.carport.shed_length}">
                    <input type="hidden" name="shed_width" value="${requestScope.order.carport.shed_width}">
                    <button type="submit" id="showHide" class="btn btn-primary">Vis tegning over carport</button>
                </form>


                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Varenummer</th>
                        <th scope="col">Vare</th>
                        <th scope="col">Beskrevelse</th>
                        <th scope="col">Længde</th>
                        <th scope="col">Antal</th>
                        <th scope="col">Enhed</th>
                        <th scope="col">Pris</th>
                        <th scope="col">Total</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${requestScope.order.BOM}" var="material">
                        <tr>
                            <td>${material.material_id}</td>
                            <td>${material.width}x${material.height} mm. ${material.category}</td>
                            <td>${material.description}</td>
                            <td>${material.length}</td>
                            <td>${material.quantity}</td>
                            <td>Stk.</td>
                            <td>${material.cost}</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </section>

    </jsp:body>
</t:genericpage>