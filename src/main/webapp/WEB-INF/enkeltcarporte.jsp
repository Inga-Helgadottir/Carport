<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">
         enkelt carporte
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <h4>Enkelt carporte</h4><br>
        <c:forEach var="carport" items="${applicationScope.enkeltcarporte}" varStatus="status">
            <form action="fc/addtocart" method="get" id="" class="">
                <img src="${pageContext.request.contextPath}/image/${carport.name}.png" alt="1" >
                varenr. ${carport.id}
                <br>
                Carport ${carport.type} ${carport.width}cm x${carport.length}cm ${carport.name}
                 pr. stk ${carport.price} + evt fragt
                <br>
                <form method="get" action="fc/addtocart">
                    <label for="antal">Antal</label>
                    <input type="number" name="antal" id="antal" placeholder="1" value="" min="0" max="10">
                    <button type="submit">LÆG I KURV</button>
                </form>
            </form>
            <br><br>
        </c:forEach>

    </jsp:body>
</t:genericpage>
