<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Se alle forespørgelser
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <h1 class="mb-5 mt-4 h1">Forespørgelser</h1>
        <section class="queries">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Brugernavn</th>
                    <th scope="col">Email</th>
                    <th scope="col">Telefon</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Status</th>
                    <th scope="col">Besked</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Dato</th>
                    <th scope="col">Carport id</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${sessionScope.getAllList}" varStatus="status">
                        <tr>
                            <th scope="row">${item.userName}</th>
                            <td>${item.userEmail}</td>
                            <td>${item.userPhone}</td>
                            <td>${item.quantity}</td>
                            <td>${item.status}</td>
                            <td>${item.msg}</td>
                            <td>${item.singlePrice}</td>
                            <td>${item.created}</td>
                            <td>${item.carportId}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <table class="table table-striped mt-4">
                <thead>
                <tr>
                    <th scope="col">Carport id</th>
                    <th scope="col">Navn</th>
                    <th scope="col">Type</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Bredde</th>
                    <th scope="col">Højde</th>
                    <th scope="col">Tag hæltning</th>
                    <th scope="col">Skur lengde</th>
                    <th scope="col">Skur bredde</th>
                    <th scope="col">Total pris</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${sessionScope.getAllList}" varStatus="status">
                        <tr>
                            <th scope="row">${item.carportId}</th>
                            <td>${item.carportName}</td>
                            <td>${item.carportType}</td>
                            <td>${item.length}</td>
                            <td>${item.width}</td>
                            <td>${item.height}</td>
                            <td>${item.roofAngle}</td>
                            <td>${item.shedLength}</td>
                            <td>${item.shedWidth}</td>
                            <td>${item.totalPrice}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </section>

    </jsp:body>
</t:genericpage>