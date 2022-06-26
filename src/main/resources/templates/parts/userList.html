<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization/user/userLocale" var="user"/>


<div class="container mt-5 pt-5 mb-1 pb-1">
    <div class="shadow p-3 mb-5 bg-body rounded">
        <table class="table table-hover align-middle mb-0 bg-white">
            <thead class="bg-light">
            <tr>
                <th class="col-2 text-center fs-5" scope="col" id="login">
                    <fmt:message key="user.login" bundle="${user}"/>
                </th>

                <th class="col-2 text-center fs-5" scope="col" id="email">
                    <fmt:message key="user.email" bundle="${user}"/>
                </th>

                <th class="col-2 text-center fs-5" scope="col" id="name">
                    <fmt:message key="user.name" bundle="${user}"/>
                </th>

                <th class="col-2 text-center fs-5" scope="col" id="balance">
                    <fmt:message key="user.balance" bundle="${user}"/>
                </th>

                <th class="col-2 text-center fs-5" scope="col" id="role">
                    <fmt:message key="user.role" bundle="${user}"/>
                </th>

                <c:if test="${sessionScope.user.role eq '3'}">
                    <th class=" col-2 text-center fs-5" scope="col" id="buy">
                        <fmt:message key="user.status" bundle="${user}"/>
                    </th>
                </c:if>
            </tr>
            </thead>

            <tbody class="table-group-divider">
            <c:forEach var="userM" items="${requestScope.userList}">
                <tr>
                    <td class="text-center">
                        <div class="fw-bold fs-5">
                                ${userM.login}
                        </div>
                    </td>

                    <td class="text-center">
                        <div class="fs-5">
                                ${userM.email}
                        </div>
                    </td>

                    <td class="text-center">
                        <div class="fs-5">
                                ${userM.name}
                        </div>
                    </td>

                    <td class="text-center">
                        <div class="fw-bold fs-5">
                                ${userM.balance}
                        </div>
                    </td>

                    <td class="text-center">
                            <c:set var="key" value="${userM.role}"/>
                            <c:forEach var="role" items="${requestScope.roleList}">
                                <c:choose>
                                    <c:when test="${userM.role == role.id}">

                                        <c:choose>
                                            <c:when test="${userM.role eq '3'}">
                                                <span class="badge text-bg-primary fs-6">
                                            </c:when>
                                            <c:when test="${userM.role eq '2'}">
                                                <span class="badge text-bg-success fs-6">
                                            </c:when>
                                            <c:when test="${userM.role eq '0'}">
                                                <span class="badge text-bg-danger fs-6">
                                            </c:when>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${language == 'ua'}">
                                                <c:out value="${role.roleUa}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="${role.roleEn}"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </span>
                    </td>

                    <c:if test="${sessionScope.user.role eq '3'}">
                        <td class="text-center">
                            <form method="post" action="${pageContext.request.contextPath}/admin/user-list/block">
                            <c:choose>
                                <c:when test="${userM.role eq '2'}">
                                    <button type="submit"
                                            id="user_block"
                                            name="user_block"
                                            value="${userM.id}"
                                            class="btn btn-danger mx-1">
                                            <fmt:message key="user.block" bundle="${user}"/>
                                    </button>
                                </c:when>
                                <c:when test="${userM.role eq '0'}">
                                    <button type="submit"
                                            id="user_block"
                                            name="user_block"
                                            value="${userM.id}"
                                            class="btn btn-success mx-1">
                                        <fmt:message key="user.unblock" bundle="${user}"/>
                                    </button>
                                </c:when>
                            </c:choose>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/addUrlParameter.js"></script>
