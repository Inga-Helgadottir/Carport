<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>


    <jsp:body>


        <div class="container my-5">
            <h2 class="display-4"> Quick-byg carport, Fladt Tag</h2>

            <div class="row mt-4">
                <hr class="border-white">

                <div class="col-5">
                    <div class="mb-4">
                        <form class="form" name="" action="${pageContext.request.contextPath}/fc/sendrequest"
                              method="post">
                            <div class="form-group my-2">
                                <label for="width">Bredde, carport</label>
                                <input class="form-control" id="width" name="width" placeholder="vælg bredde"
                                       type="number"
                                       min="2400" max="6000" step="300"/> <small
                                    class="form-text text-muted">centimeter</small>
                            </div>
                            <div class="form-group my-2">
                                <label for="length">Længde, carport</label>
                                <input class="form-control" id="length" name="length" placeholder="vælg længde"
                                       type="number"
                                       min="2400" max="7800" step="300"/><small
                                    class="form-text text-muted">centimeter</small>
                            </div>
                            <div class="form-group my-2">
                                <label for="shedWidth">Bredde, skur</label>
                                <input class="form-control" id="shedWidth" name="shedWidth"
                                       placeholder="vælg skur bredde"
                                       type="number" min="2100" max="7200" step="300" />
                                <small class="form-text text-muted">centimeter</small>
                            </div>
                            <div class="form-group my-2">
                                <label for="shedLength">Længde, skur</label>
                                <input class="form-control" id="shedLength" name="shedLength"
                                       placeholder="vælg skur længde"
                                       type="number" min="1500" max="6900" step="300"/>
                                <small class="form-text text-muted">centimeter</small>
                            </div>
                            <div class="form-group my-2">
                                <label for="roof">Tag</label>
                                <select class="form-control" id="roof" name="roof">
                                    <option value="Betontag, sort">Beton, sort</option>
                                    <option value="Betontag, rød">Beton, rød</option>
                                    <option value="Eternittag, sort">Eternit, sort</option>
                                    <option value="Eternittag, grå">eternit, grå</option>
                                </select>
                            </div>
                            <div class="form-group my-2">
                                <label for="message">Besked</label>
                                <textarea class="form-control" id="message" name="message" rows="3"></textarea>
                                <small id="note" class="form-text text-muted">(Valgfrit) besked ved henvendelse.</small>
                            </div>
                            <input onclick="myFunction()" type="submit" class="btn btn-success my-2" name="submitCustom"
                                   value="Send forespørgsel"
                                   <c:if test="${sessionScope.user == null}">disabled="disabled"</c:if> />
                        </form>
                    </div>

                </div>
                <div class="row col-6 offset-1">

                </div>
            </div>
        </div>

        <script>
            function myFunction() {
                alert("Du har sendt en forespørgsel");
            }
        </script>
    </jsp:body>
</t:genericpage>