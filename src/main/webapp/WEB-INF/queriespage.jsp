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
        <form action="${pageContext.request.contextPath}/fc/queryinfo" method="get" class="queries">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Ref nr.</th>
                    <th scope="col">Kunde nr.</th>
                    <th scope="col">Telefon nr.</th>
                    <th scope="col">Price</th>
                    <th scope="col">Message</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${requestScope.queries}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.user_id}</td>
                        <td>${item.user.telephone}</td>
                        <td>${item.price}</td>
                        <td>${item.message}</td>
                        <td>${item.status}</td>
                        <td>
                            <button type="submit" name="query_id" value="${item.id}" class="btn btn-success">Se Forespørgsel</button>
                            <input type="hidden" name="user_id" value="${item.user.id}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>
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