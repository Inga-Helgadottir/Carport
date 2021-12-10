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
        <section class="allTables d-flex flex-row">
            <table class="table table-striped seeOrderTable">
                <thead>
                <tr>
                    <th class="thTd" scope="col">Ref nr.</th>
                    <th class="thTd" scope="col">Seneste ændring</th>
                    <th class="thTd" scope="col">Carport bredde</th>
                    <th class="thTd" scope="col">Carport længde</th>
                    <th class="thTd" scope="col">Tag</th>
                    <th class="thTd" scope="col">Redskabsrum</th>
                    <th class="thTd" scope="col">Status</th>
                </tr>
                </thead>
                <tbody>
                <tr><!------------------------------------------------------------------------------------------------->
                    <td class="thTd">15</td>
                    <td class="thTd">2021-12-01 17:17:06.0</td>
                    <td class="thTd">420 cm</td>
                    <td class="thTd">600 cm</td>
                    <td class="thTd">Plastmo Rcolite blåtonet</td>
                    <td class="thTd">Nej</td>
                    <td class="thTd d-flex justify-content-between">
                        request
                        <button id="showHide" class="btn btn-primary seeOrderBtn">Se bestilling</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
        <section id="showHideDiv">
            <!--button shows the order here--------------------------------------------------------------------------->
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
