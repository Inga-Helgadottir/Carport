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
                            <td>Status:</td>
                            <td>${requestScope.query.status}</td><!------------------------------------------->
                        </tr>
                        </tbody>
                    </table>
                    <form action="" method="get">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th scope="col">Carport</th>
                                <th scope="col">
                                    Lås <input class="checkbox" type="checkbox" name="orderCheck" value="" onclick="">
                                    <!--------------------------------->
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Bredde:</td>
                                <td>
                                    <input type="number" value="${requestScope.query.carport.width}" class="input">
                                    <!------------------------------------------->
                                    cm
                                </td>
                            </tr>
                            <tr>
                                <td>Længde:</td>
                                <td>
                                    <input type="number" value="${requestScope.query.carport.length}" class="input">
                                    <!------------------------------------------->
                                    cm
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <button class="btn btn-primary btnWidth" name="carport_id"
                                value="${requestScope.query.carport.id}">Opdater mål
                        </button>
                    </form>
                </div>
            </div>

            <div class="d-flex flex-row">
                <div class="half mb-5">
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
                            <td>${requestScope.query.price}</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Dækningsgrad:</td>
                            <td class="d-flex justify-content-between">
                                <div>
                                    <input type="number" value="80.8" class="input2" name="coverage">
                                    <input type="hidden" value="${requestScope.query.id}" name="query_id">
                                    <input type="hidden" value="${requestScope.query.carport.id}" name="carport_id">
                                </div>
                                <button class="btn btn-primary">Opdater dækningsgrad</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Dækningsbidrag:</td>
                            <td>1830.69</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Tilbudspris ex. moms:</td>
                            <td>4119.05</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Tilbudspris incl. moms:</td>
                            <td>5148.81</td><!------------------------------------------->
                        </tr>
                        </tbody>
                    </table>
                    <button id="showHide" class="btn btn-primary">Vis tegning over carport</button>
                    <div id="showHideDiv">
                        <p>Indsæt tegningen her</p>
                    </div>
                </div>
            </div>

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
                    <td>skal regnes ud ?</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <script>
            let btn = document.getElementById("showHide");
            let div = document.getElementById("showHideDiv");
            btn.addEventListener("click", ()=>{
                if(div.style.display == "block"){
                    div.style.display = "none";
                }else{
                    div.style.display = "block";
                }
            });
        </script>
    </jsp:body>
</t:genericpage>