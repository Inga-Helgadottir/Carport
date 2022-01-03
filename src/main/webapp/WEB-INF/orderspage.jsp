<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         Se alle ordre
    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>
        <h1 class="mb-5 mt-4 h1">Ordre</h1>
        <form action="${pageContext.request.contextPath}/fc/ordreinfo" method="get" class="queries">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Ref nr.</th>
                    <th scope="col">Carport, længde</th>
                    <th scope="col">Carport, bredde</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Skur, længde</th>
                    <th scope="col">Skur, bredde</th>
                    <th scope="col">Status</th>
                    <th scope="col">query id</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${requestScope.orderlist}">
                    <tr>

                        <td>${order.id}</td>
                        <td>${order.carport.length}</td>
                        <td>${order.carport.width}</td>
                        <td>${order.price}</td>
                        <td>${order.carport.shed_length}</td>
                        <td>${order.carport.shed_width}</td>
                        <td>${order.status}</td>
                        <td>${order.query.id}</td>
                        <td>
                            <button type="submit" name="order_id" value="${order.id}"
                                    class="btn btn-success">Se Ordre
                            </button>

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