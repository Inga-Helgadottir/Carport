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


        <section class="allTables d-flex flex-column">
            <div class="d-flex flex-row">
                    <%---customer info--%>
                <table class="table table-striped tableLeft half table1">
                    <thead>
                    <tr>
                        <th scope="col">Kundeoplysninger</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Kunde nr.:</td>
                        <td>${requestScope.query.user.id}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Fulde navn:</td>
                        <td>${requestScope.query.user.name}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Adresse:</td>
                        <td>${requestScope.query.user.address}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>By:</td>
                        <td>${requestScope.query.user.city}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Telefon nr :</td>
                        <td>${requestScope.query.user.telephone}</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td>${requestScope.query.user.email}</td><!------------------------------------------->
                    </tr>
                    </tbody>
                </table>
                    <%---query info--%>
                <div class="d-flex flex-column half">
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
                            <td>${requestScope.query.id}</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Oprettet:</td>
                            <td>${requestScope.query.created}</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Status:</td>
                            <td>${requestScope.query.status}</td><!------------------------------------------->
                        </tr>
                        </tbody>
                    </table>

                        <%---update dimensions--%>
                    <form action="${pageContext.request.contextPath}/fc/updateDimensions" method="get">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Carport</th>
                                <th scope="col">
                                    <!--------------------------------->
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Bredde:</td>
                                <td>
                                    <input name="width" min="2400" max="6000" step="300" type="number"
                                           value="${requestScope.query.carport.width}" class="input">
                                    <!------------------------------------------->
                                    mm
                                </td>
                            </tr>
                            <tr>
                                <td>Længde:</td>
                                <td>
                                    <input name="length" min="2400" max="7800" step="300" type="number"
                                           value="${requestScope.query.carport.length}" class="input">
                                    <!------------------------------------------->
                                    mm
                                </td>
                            </tr>

                            <tr>
                                <td>skur Bredde:</td>
                                <td>
                                    <input name="shed_width" type="number"
                                           value="${requestScope.query.carport.shed_width}" class="input">
                                    <!------------------------------------------->
                                    mm
                                </td>
                            </tr>
                            <tr>
                                <td>skur Længde:</td>
                                <td>
                                    <input name="shed_length"  type="number"
                                           value="${requestScope.query.carport.shed_length}" class="input">
                                    <!------------------------------------------->
                                    mm
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <input name="query_id" type="hidden" value="${requestScope.query.id}">
                        <button name="carport_id" onclick="myFunction()" type="submit" class="btn btn-primary btnWidth"
                                value="${requestScope.query.carport.id}">Opdater mål
                        </button>
                    </form>
                </div>
            </div>

            <div class="d-flex flex-row">
                <div class="half mb-5">
                        <%---update price--%>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Pris</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Indkøbspris ex. moms:</td>
                            <td id="inkobsprisExM">${requestScope.query.price}</td>
                            <!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Dækningsgrad:</td>
                            <td class="d-flex justify-content-between">
                                <div>
                                    <input type="number" value="30.0" class="input2" name="coverage" id="daekningsgrad">
                                    <input type="hidden" value="${requestScope.query.id}" name="query_id">
                                    <input type="hidden" value="${requestScope.query.carport.id}" name="carport_id">
                                </div>
                                <button type="submit" class="btn btn-primary" onclick="calc()">Opdater dækningsgrad
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>Dækningsbidrag:</td>
                            <td id="daekningsbidrag"></td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Tilbudspris ex. moms:</td>
                            <td id="tilbudsprisExM"></td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Tilbudspris incl. moms:</td>
                            <td id="tilbudsprisInclM"></td><!------------------------------------------->
                        </tr>
                        </tbody>
                    </table>
                        <%---svg--%>
                    <form method="get" action="${pageContext.request.contextPath}/fc/showsvg">
                        <input type="hidden" name="length" value="${requestScope.query.carport.length}">
                        <input type="hidden" name="width" value="${requestScope.query.carport.width}">
                        <input type="hidden" name="shed_length" value="${requestScope.query.carport.shed_length}">
                        <input type="hidden" name="shed_width" value="${requestScope.query.carport.shed_width}">
                        <button type="submit" id="showHide" class="btn btn-primary">Vis tegning over carport</button>
                    </form>
                        <%---send offer--%>
                    <form method="get" action="${pageContext.request.contextPath}/fc/sendoffer">
                        <input type="hidden" name="query_id" value="${requestScope.query.id}">
                        <input type="hidden" name="carport_id" value="${requestScope.query.carport.id}">
                        <input type="hidden" id="offerprice" name="offerprice">
                        <button type="submit" class="btn btn-success my-2">Send Forespørgsel</button>
                    </form>
                </div>
            </div>
                <%---BOM--%>
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
                <c:forEach items="${requestScope.query.BOM}" var="material">
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
        </section>

        <script>
            calc();

            function calc() {
                let ipem = document.getElementById("inkobsprisExM").textContent;
                let dg = document.getElementById("daekningsgrad").value;
                let db = document.getElementById("daekningsbidrag");
                let tpem = document.getElementById("tilbudsprisExM");
                let tpim = document.getElementById("tilbudsprisInclM");
                let ipemInt = parseInt(ipem);
                let dgp = dg / 100;
                let dgtp = ipem * dgp;
                let um = ipemInt + dgtp;
                let mm = 1.25 * um;
                db.innerText = dgtp;
                tpem.innerText = um;
                tpim.innerText = mm;
                document.getElementById("offerprice").value = mm;
            }


        </script>
        <script>
            function myFunction() {
                alert("Ny Stykliste Udregnes");
            }
        </script>

    </jsp:body>
</t:genericpage>