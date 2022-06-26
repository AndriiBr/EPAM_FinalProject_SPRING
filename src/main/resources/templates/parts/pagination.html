<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://servlet.project" prefix="upd" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization/locale" var="locale"/>

<div class="container my-0 py-0">
    <nav aria-label="Page navigation example">
        <div class="row mb-2">
            <div class="col">
                <ul class="pagination justify-content-start mb-5 pb-2">
                    <li class="page-item">
                        <button class="page-link disabled disabled">Elements</button>
                    </li>

                    <c:forEach items="${[3, 5, 7, 10, 15]}" var="r">
                        <c:choose>
                            <c:when test="${requestScope.recordsPerPage eq r}">
                                <li class="page-item active">
                                    <button class="page-link"
                                            onclick="executeUrlParameter('recordsPerPage', ${r})">${r}</button>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <button class="page-link"
                                            onclick="executeUrlParameter('recordsPerPage', ${r})">${r}</button>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                </ul>
            </div>
            <div class="col">
                <ul class="pagination justify-content-center mb-5 pb-2">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq '1'}">
                            <li class="page-item disabled">
                                <button class="page-link"
                                        onclick="executeUrlParameter('currentPage', ${requestScope.currentPage - 1})">
                                    <fmt:message key="edition.previous" bundle="${locale}"/>
                                </button>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <button class="page-link"
                                        onclick="executeUrlParameter('currentPage', ${requestScope.currentPage - 1})">
                                    <fmt:message key="edition.previous" bundle="${locale}"/>
                                </button>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                        <c:choose>
                            <c:when test="${requestScope.currentPage eq i}">
                                <li class="page-item active">
                                    <button class="page-link"
                                            onclick="executeUrlParameter('currentPage', ${i})">${i}</button>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <button class="page-link"
                                            onclick="executeUrlParameter('currentPage', ${i})">${i}</button>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:choose>
                        <c:when test="${requestScope.currentPage ne requestScope.numberOfPages}">
                            <li class="page-item">
                                <button class="page-link"
                                        onclick="executeUrlParameter('currentPage', ${requestScope.currentPage + 1})">
                                    <fmt:message key="edition.next" bundle="${locale}"/>
                                </button>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item  disabled">
                                <button class="page-link"
                                        onclick="executeUrlParameter('currentPage', ${requestScope.currentPage + 1})">
                                    <fmt:message key="edition.next" bundle="${locale}"/>
                                </button>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <div class="col">
                <div class="d-flex justify-content-end mt-2">
                    <fmt:message key="tag.last_update" bundle="${locale}"/>
                    <upd:lastUpdate/>
                </div>
            </div>
        </div>
    </nav>
</div>

<script src="${pageContext.request.contextPath}/js/addUrlParameter.js"></script>
