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
        <form action="" class="d-flex flex-column mt-5"><!--------------ADD ACTION--------------------------------->
            <input type="number" name="height" placeholder="300" value="300">
            <input type="number" name="width" placeholder="width">
            <input type="number" name="length" placeholder="length">
            <input type="number" name="shedLength" placeholder="shed length">
            <input type="number" name="shedWidth" placeholder="shed width">
            <input type="number" name="roofAngle" placeholder="roof angle">
            <button type="submit">button</button>
        </form>
<%--        String name = request.getParameter("name");--%> 
    </jsp:body>
</t:genericpage>
