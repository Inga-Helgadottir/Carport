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
        <section class="allTables2 d-flex flex-row">
            <table class="table table-striped tableWidth firstTable">
                <thead>
                <tr>
                    <th scope="col">Kundeoplysninger</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
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
            <div class="tableWidth">
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
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Bredde:</td>
                        <td>
                            420 cm<!------------------------------------------->
                        </td>
                    </tr>
                    <tr>
                        <td>Længde:</td>
                        <td>
                            600 cm<!------------------------------------------->
                        </td>
                    </tr>
                    <tr>
                        <td>Tag:</td>
                        <td>Plasmo Ecolite blåtonet</td><!------------------------------------------->
                    </tr>
                    </tbody>
                </table>

                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Pris (Dkk):</td>
                        <td>Afventer tilbud</td>
                    </tr>
                    </tbody>
                </table>
                <div class="d-flex justify-content-between">
                    <button class="btn btn-success yesNoBtn">Accepter</button>
                    <button class="btn btn-danger yesNoBtn">Annuller</button>
                </div>
            </div>
        </section>
    </jsp:body>
</t:genericpage>
