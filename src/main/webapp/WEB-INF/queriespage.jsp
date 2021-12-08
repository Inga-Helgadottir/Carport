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
                    <th scope="col">QueryId</th>
                    <th scope="col">Status</th>
                    <th scope="col">Price</th>
                    <th scope="col">Message</th>
                    <th scope="col">UserId</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${sessionScope.queries}" varStatus="status">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.status}</td>
                        <td>${item.price}</td>
                        <td>${item.message}</td>
                        <td>${item.user_id}</td>
                        <td>
                            <button type="submit" name="queryId" value="${item.id}" class="btn btn-success">Administre Forespørgsel</button>
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