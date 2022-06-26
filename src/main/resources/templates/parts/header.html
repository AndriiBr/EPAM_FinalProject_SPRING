<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session" />
<%-- Deprecated --%>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="localization/locale" />
<%-- A custom property loader was used to work with Cyrillic (UTF-8 format) --%>

<!DOCTYPE html>
<html lang="${language}">

<head>
    <title>Header</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general_css_modules/general_header.css"
          type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general_css_modules/input_form_style.css"
          type="text/css">

</head>
<body>

<div class="general_header">
    <div class="main_page_frame">
        <div class="main_page">
            <a href="${pageContext.request.contextPath}/">${requestScope.localization.getString("main.text.main_page")}</a>
        </div>
    </div>
    <div class="ml-3">
        <% if (session.getAttribute("login") != null) { %>
        <a href="http://localhost:8080/cabinet">${sessionScope.login}: ${requestScope.localization.getString("main.link.cabinet")}</a><br/>
        <a href="http://localhost:8080/logout">${requestScope.localization.getString("main.link.logout")}</a><br/>
        <% } else { %>
        <a href="http://localhost:8080/login">${requestScope.localization.getString("main.link.login")}</a><br/>
        <% } %>
    </div>

    <div class="language_switch">
        <div class="lang_frame">
            <form name="lang_switch" method="get">
                <select id="language" name="language" onchange="submit()">
                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>UA</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
                </select>
            </form>
        </div>
    </div>
</div>

</body>
</html>