<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session" />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization/authentication/authLocale" var="auth"/>


<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login_page/auth_style.css"
          type="text/css">

</head>
<body>
<jsp:include page="/view/parts/navbar.jsp"/>

<section class="vh-100">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-6 text-black">
                <div class="d-flex align-items-center justify-content-center mt-5 pt-5 px-5 ms-xl-4 mb-0">
                    <img class="mt-0" src="${pageContext.request.contextPath}/img/login_page/login_screen_user_logo.png"
                         alt="Logo"
                         width="170">
                </div>

                <div class="d-flex align-items-center justify-content-center h-custom-4 px-5 ms-xl-4 mt-2 mb-5 pb-5 pt-2 pt-xl-0 mt-xl-n5">
                    <div class="message_success">
                        <h4>
                            <fmt:message key="auth.message.registration.success" bundle="${auth}"/>
                        </h4>
                        <div class="d-flex justify-content-center user_message">
                            <h3>
                                ${sessionScope.user.login}
                            </h3>
                        </div>
                        <div class="d-flex justify-content-center user_message">
                            <a class="link-info" href="${pageContext.request.contextPath}/shop/list">
                                <fmt:message key="auth.main_page" bundle="${auth}"/>
                            </a>
                        </div>
                    </div>
                </div>

            </div>
            <div class="col-sm-6 px-0 d-none d-sm-block">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img3.webp"
                     alt="Login image" class="w-100 vh-100" style="object-fit: cover; object-position: left;">
            </div>
        </div>
    </div>
</section>


<div class="wrapper fadeInDown">
    <div id="formContent">

        <div class="fadeIn first">
            <img src="${pageContext.request.contextPath}/img/login_page/login_screen_user_logo.png" id="icon" alt="User Icon"/>
        </div>

        <div class="message_success">
            ${requestScope.localization.getString("login.text.account_for")}<br/>
        </div>
        <div class="user_message">
            ${sessionScope.login}
        </div>
        <div class="message_success">
            ${requestScope.localization.getString("login.text.create_success")}
        </div>

        <div id="formFooter">
            <a class="underlineHover" href="http://localhost:8080/">${requestScope.localization.getString("main.text.main_page")}</a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
</body>
</html>