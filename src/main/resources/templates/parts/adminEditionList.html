<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : sessionScope.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization/locale" var="locale"/>

<div class="container mt-5 pt-5 mb-1 pb-1">
    <div class="shadow p-3 mb-5 bg-body rounded">
        <div class="d-flex justify-content-end">
            <form method="get" action="${pageContext.request.contextPath}/admin/edition/new-edition">
                <button type="submit" class="btn btn-success btn-sm mx-2 mb-3">
                    <fmt:message key="edition.add" bundle="${locale}"/>
                </button>
            </form>
        </div>
        <table class="table table-hover align-middle mb-0 bg-white">
            <thead class="bg-light">
            <tr>
                <th class="col-2 text-center fs-5" scope="col" id="title_image">
                    <fmt:message key="edition.magazine" bundle="${locale}"/>
                </th>

                <th class="col-5 text-center fs-5" scope="col" id="title">
                    <a class="nav-link" onclick="executeUrlParameter('orderBy', 'title')">
                        <fmt:message key="edition.title" bundle="${locale}"/>
                    </a>
                </th>

                <th class="col-2 text-center" scope="col" id="genres">
                    <form action="${pageContext.request.contextPath}/shop/list" method="get">
                        <select class="form-select" id="genreFilter" name="genreFilter"
                                onchange="executeUrlParameter('genreFilter', this.options[selectedIndex].value)">
                            <option value="0" ${requestScope.genreFilter eq '0' ? 'selected' : ''} >
                                <fmt:message key="edition.genre" bundle="${locale}"/>
                            </option>
                            <c:forEach items="${requestScope.genresList}" var="genre">
                                <c:set var="genreId" value="${genre.id}"/>
                                <c:choose>
                                    <c:when test="${language == 'ua'}">
                                        <option value="${genreId}" ${requestScope.genreFilter eq genreId ? 'selected' : ''}>
                                                ${genre.genreUa}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${genreId}" ${requestScope.genreFilter eq genreId ? 'selected' : ''}>
                                                ${genre.genreEn}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </form>
                </th>

                <th class="nav-item text-center fs-5" scope="col" id="price">
                    <a class="nav-link" onclick="executeUrlParameter('orderBy', 'price')">
                        <fmt:message key="edition.price" bundle="${locale}"/>
                    </a>
                </th>

                <c:if test="${sessionScope.user.role eq '3'}">
                    <th class=" col-2 text-center fs-5" scope="col" id="edit">
                        <fmt:message key="edition.edit" bundle="${locale}"/>
                    </th>
                    <th class=" col-2 text-center fs-5" scope="col" id="delete">
                        <fmt:message key="edition.delete" bundle="${locale}"/>
                    </th>
                </c:if>
            </tr>
            </thead>

            <tbody class="table-group-divider">
            <c:forEach var="edition" items="${requestScope.editionList}">
                <tr>
                    <td>
                        <div class="d-flex align-items-center">
                            <img src="${edition.imagePath}" alt="${edition.titleEn}" style="width: 160px"
                                 onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/img/empty_title_placeholder/No_Image_Placeholder.jpg';"/>
                        </div>
                    </td>

                    <td>
                        <p class="fw-bold">
                            <c:choose>
                                <c:when test="${language == 'ua'}">
                                    <c:out value="${edition.titleUa}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${edition.titleEn}"/>
                                </c:otherwise>
                            </c:choose>
                        </p>
                        <p class="text-muted mb-0 pe-5">
                            <c:choose>
                                <c:when test="${language == 'ua'}">
                                    <c:out value="${edition.textUa}"/>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${edition.textEn}"/>
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </td>

                    <td class="text-center">
                        <span class="badge text-bg-success fs-6">
                        <c:set var="key" value="${edition.genreId}"/>
                        <c:forEach var="genre" items="${requestScope.genresList}">
                            <c:choose>
                                <c:when test="${genre.id == key}">
                                    <c:choose>
                                        <c:when test="${language == 'ua'}">
                                            <c:out value="${genre.genreUa}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="${genre.genreEn}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                        </span>
                    </td>

                    <td class="text-center">
                        <div class="fw-bold fs-5">
                                ${edition.price}
                        </div>
                    </td>

                    <c:if test="${sessionScope.user.role eq '3'}">
                        <%--Edit button--%>
                        <td class="text-center">
                            <button class="btn btn-warning btn-sm fw-bold"
                                    onclick="document.getElementById('${edition.id}').style.display='block'">
                                <fmt:message key="edition.edit" bundle="${locale}"/>
                            </button>
                            <div id="${edition.id}" class="modal">
                    <span onclick="document.getElementById('${edition.id}').style.display='none'" class="close"
                          title="Close Modal">×</span>
                                <div class="d-flex justify-content-center">
                                    <form class="container mt-5 pt-5 px-5 mb-1 pb-1"
                                          action="${pageContext.request.contextPath}/admin/edition/edit"
                                          method="get">
                                        <div class="shadow p-3 mb-5 mx-5 px-5 bg-body rounded">
                                            <h1>
                                                <c:choose>
                                                    <c:when test="${language == 'ua'}">
                                                        <c:out value="${edition.titleUa}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${edition.titleEn}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </h1>
                                            <img src="${edition.imagePath}" alt="${edition.id}" width="150" height="200"
                                                 onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/img/empty_title_placeholder/No_Image_Placeholder.jpg';"/>

                                            <p>
                                                <fmt:message key="edition.edit_text" bundle="${locale}"/>
                                            </p>

                                            <div class="clearfix">
                                                <button type="button"
                                                        onclick="document.getElementById('${edition.id}').style.display='none'"
                                                        class="btn btn-secondary mx-1">
                                                    <fmt:message key="edition.cancel" bundle="${locale}"/>
                                                </button>
                                                <button type="submit" name="edit_edition_id" value="${edition.id}"
                                                        class="btn btn-warning mx-1">
                                                    <fmt:message key="edition.edit" bundle="${locale}"/>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </td>

                        <%--Delete button--%>
                        <td class="text-center">
                            <button class="btn btn-danger btn-sm fw-bold"
                                    onclick="document.getElementById('${edition.id}2').style.display='block'">
                                <fmt:message key="edition.delete" bundle="${locale}"/>
                            </button>

                            <div id="${edition.id}2" class="modal">
                    <span onclick="document.getElementById('${edition.id}2').style.display='none'" class="close"
                          title="Close Modal">×</span>
                                <div class="d-flex justify-content-center">
                                    <form class="container mt-5 pt-5 px-5 mb-1 pb-1"
                                          action="${pageContext.request.contextPath}/admin/edition/delete"
                                          method="post">
                                        <div class="shadow p-3 mb-5 mx-5 px-5 bg-body rounded">
                                            <h1>
                                                <c:choose>
                                                    <c:when test="${language == 'ua'}">
                                                        <c:out value="${edition.titleUa}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${edition.titleEn}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </h1>
                                            <img src="${edition.imagePath}" alt="${edition.id}" width="150" height="200"
                                                 onerror="this.onerror=null;this.src='${pageContext.request.contextPath}/img/empty_title_placeholder/No_Image_Placeholder.jpg';"/>

                                            <p>
                                                <fmt:message key="edition.delete_text" bundle="${locale}"/>
                                            </p>

                                            <div class="clearfix">
                                                <button type="button"
                                                        onclick="document.getElementById('${edition.id}2').style.display='none'"
                                                        class="btn btn-secondary mx-1">
                                                    <fmt:message key="edition.cancel" bundle="${locale}"/>
                                                </button>
                                                <button type="submit" name="edition_id" value="${edition.id}"
                                                        class="btn btn-danger mx-1">
                                                    <fmt:message key="edition.delete" bundle="${locale}"/>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/addUrlParameter.js"></script>


