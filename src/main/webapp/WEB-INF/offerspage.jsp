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
        <form action="${pageContext.request.contextPath}/fc/offerinfo" method="get" class="queries">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">Ref nr.</th>
                    <th scope="col">Carport, længde</th>
                    <th scope="col">Carport, bredde</th>
                    <th scope="col">Tag</th>
                    <th scope="col">Skur, længde</th>
                    <th scope="col">Skur, bredde</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <c:if test="${requestScope.offer != null}">
                        <td>${requestScope.offer.id}</td>
                        <td>${requestScope.offer.carport.length}</td>
                        <td>${requestScope.offer.carport.width}</td>
                        <td>${requestScope.offer.carport.roof_angle}</td>
                        <td>${requestScope.offer.carport.shed_length}</td>
                        <td>${requestScope.offer.carport.shed_width}</td>
                        <td>${requestScope.offer.status}</td>
                        <td>
<<<<<<< HEAD
                            <button type="submit" name="query_id" value="${requestScope.offer.id}" class="btn btn-success">Se
=======
                            <button type="submit" name="query_id" value="${requestScope.offer.id}"
                                    class="btn btn-success">Se
>>>>>>> timmy
                                Forespørgsel
                            </button>
                        </td>
                    </c:if>
<<<<<<< HEAD

=======
>>>>>>> timmy
                </tr>
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