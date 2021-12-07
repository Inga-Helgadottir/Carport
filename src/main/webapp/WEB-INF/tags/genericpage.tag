<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><jsp:invoke fragment="header"/></title>
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/images/fogLogo.png">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/navBar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customerpage.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/singlecarports.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/showHide.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/onSale.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/seeAllQueries.css">
    <meta name="theme-color" content="#7952b3">
</head>
<body>
    <!--
        This header is inspired by this bootstrap
        example: https://getbootstrap.com/docs/5.0/examples/pricing/
    -->
    <header>
        <nav class="navBar row">
            <div class="navBarDiv col-lg-9 col-md-8 col-sm-7">
                <a href="<%=request.getContextPath()%>">
                    <img src="<%=request.getContextPath()%>/images/fogLogo.png" alt="fog logo" class="navImg">
                </a>
                <%--        husk at tilføje href til alle links----------------------------------------------%>
                <a class="aTag" href="#">Byggematerialer</a>
                <a class="aTag" href="${pageContext.request.contextPath}/fc/standartcarportpage">Carporte</a>
                <a class="aTag" href="${pageContext.request.contextPath}/fc/salepage">Tilbud</a>
            </div>
            <div class="navBarDiv2 col-lg-3 col-md-4 col-sm-5">
                <c:set var="thisPage" value="${pageContext.request.servletPath}"/>
                <c:set var="isNotLoginPage" value="${!fn:endsWith(thisPage,'loginpage.jsp')}"/>
                <c:set var="isNotRegisterPage" value="${!fn:endsWith(thisPage,'registerpage.jsp')}"/>

                <c:if test="${isNotLoginPage && isNotRegisterPage}">
                    <c:if test="${sessionScope.role == 'customer' }">
                        <a class="textAndIcon" href="${pageContext.request.contextPath}/fc/logoutcommand">Logout<i class="far fa-user myIcon"></i></a>
                        <a class="textAndIcon" href="${pageContext.request.contextPath}/fc/cartpage">Inkøbskurv<i class="fas fa-shopping-cart myIcon"></i></a>
                    </c:if>

                    <c:if test="${sessionScope.role == 'employee'}">
                        <a class="textAndIcon" href="${pageContext.request.contextPath}/fc/logoutcommand">Logout<i class="far fa-user myIcon"></i></a>
                        <a class="queries" href="${pageContext.request.contextPath}/fc/queries">Forespørgelser</a>
                    </c:if>

                    <c:if test="${sessionScope.user == null }">
                        <a class="textAndIcon" href="${pageContext.request.contextPath}/fc/logincommand">Log in<i class="fas fa-user myIcon"></i></a>
                        <a class="textAndIcon" href="${pageContext.request.contextPath}/fc/registercommand">Sign up<i class="far fa-user myIcon"></i></a>
                    </c:if>
                </c:if>

                <c:if test="${sessionScope.user != null }">
                    <p class="userEmail">User: ${sessionScope.user.email}</p>
                </c:if>
            </div>
        </nav>
    </header>

<div id="body" class="container" style="min-height: 20vh;">
    <jsp:doBody/>
</div>

<!-- Footer -->
<div class="container">
    <br>
    <hr>
    <br>
    <jsp:invoke fragment="footer"/>
</div>
    <script src="https://kit.fontawesome.com/2677b557d6.js"></script>
</body>
</html>