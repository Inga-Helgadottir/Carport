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
                        <td>2</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Fulde navn:</td>
                        <td>Kunde</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Adresse:</td>
                        <td>Kundegade 1</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Postnummer:</td>
                        <td>3720</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>By:</td>
                        <td>Rønne</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Telefon nr :</td>
                        <td>10203040</td><!------------------------------------------->
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td>q@q.dk</td><!------------------------------------------->
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
                            <td>15</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Oprettet:</td>
                            <td>2021-12-02 17:17:06.0</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Ændret:</td>
                            <td>2021-12-02 17:17:06.0</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Status:</td>
                            <td>request</td><!------------------------------------------->
                        </tr>
                        </tbody>
                    </table>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Carport</th>
                            <th scope="col">
                                Lås <input class="checkbox" type="checkbox" name="orderCheck" value=""><!--------------------------------->
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Bredde:</td>
                            <td>
                                <input type="number" value="420" class="input"><!------------------------------------------->
                                cm
                            </td>
                        </tr>
                        <tr>
                            <td>Længde:</td>
                            <td>
                                <input type="number" value="600" class="input"><!------------------------------------------->
                                cm
                            </td>
                        </tr>
                        <tr>
                            <td>Tag:</td>
                            <td>Plasmo Ecolite blåtonet</td><!------------------------------------------->
                        </tr>
                        </tbody>
                    </table>
                    <button class="btn btn-primary btnWidth">Opdater mål</button>
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
                            <td>2288.36</td><!------------------------------------------->
                        </tr>
                        <tr>
                            <td>Dækningsgrad:</td>
                            <td class="d-flex justify-content-between">
                                <div>
                                    <input type="number" value="80.8" class="input2"><!------------------------------------------->
                                    %
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
                <tr>
                    <td>1601</td><!------------------------------------------->
                    <td>97x97 mm. trykimo. stolpe</td><!------------------------------------------->
                    <td>Stolper nedgraves 90cm. i jord</td><!------------------------------------------->
                    <td>3000 mm</td><!------------------------------------------->
                    <td>6</td><!------------------------------------------->
                    <td>Stk.</td><!------------------------------------------->
                    <td>112.5</td><!------------------------------------------->
                    <td>675.0</td><!------------------------------------------->
                </tr>
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