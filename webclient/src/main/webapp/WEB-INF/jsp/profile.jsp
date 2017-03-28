<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="col-md-2">
        <!-- It can be fixed with bootstrap affix http://getbootstrap.com/javascript/#affix-->
        <div id="sidebar" class="well sidebar-nav">
            <ul class="nav nav-pills nav-stacked">
                <c:if test="${authorized}">
                    <li class="active"><a><spring:message code="profile.home"/></a></li>
                </c:if>
                <c:if test="${not authorized}">
                    <li><a href="profile"><spring:message code="profile.home"/></a></li>
                </c:if>
                <li><a href="friends"><spring:message code="profile.friends"/></a></li>
                <li><a href="users"><spring:message code="profile.findFriends"/></a></li>
            </ul>
        </div>
    </div>
    <div class="container">

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">

            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${user.name} ${user.surname}</h3>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-3 col-lg-3 " align="center"><img alt="User Pic"
                                                                            src="/resources/images/noavatar.png"
                                                                            class="img-circle img-responsive"></div>

                        <div class=" col-md-9 col-lg-9 ">
                            <table class="table table-user-information">
                                <tbody>
                                <tr>
                                    <td><spring:message code="profile.dateOfBirth"/>:</td>
                                    <td>${user.birthday}</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="profile.sex"/></td>
                                    <c:if test="${user.sex == 'MALE'}">
                                        <td><spring:message code="sex.male"/></td>
                                    </c:if>
                                    <c:if test="${user.sex == 'FEMALE'}">
                                        <td><spring:message code="sex.female"/></td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td><spring:message code="profile.city"/></td>
                                    <td>${user.city}</td>
                                </tr>
                                <tr>
                                    <td><spring:message code="profile.email"/></td>
                                    <td>${user.email}</td>
                                </tr>
                                </tbody>
                            </table>
                            <c:if test="${authorized}">
                                <a href="edit" class="btn btn-primary"><spring:message code="profile.edit"/> </a>
                            </c:if>
                            <c:if test="${not authorized}">
                                <c:if test="${not isFriend}">
                                    <c:if test="${not isSent}">
                                        <button type="submit" class="btn btn-primary" onclick="send_request(${user.id})">
                                            <spring:message code="friends.add"/>
                                        </button>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>

<script type="text/javascript" src="resources/js/profile.js"></script>

</html>
