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
                    <li class="active"><a>Моя страница</a></li>
                </c:if>
                <c:if test="${not authorized}">
                    <li><a href="profile">Моя страница</a></li>
                </c:if>
                <li><a href="friends">Друзья</a></li>
                <li><a href="users">Поиск друзей</a></li>
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
                                    <td>Дата рождения:</td>
                                    <td>${user.birthday}</td>
                                </tr>
                                <tr>
                                    <td>Пол</td>
                                    <c:if test="${user.sex == 'MALE'}">
                                        <td>Мужской</td>
                                    </c:if>
                                    <c:if test="${user.sex == 'FEMALE'}">
                                        <td>Женский</td>
                                    </c:if>
                                </tr>
                                <tr>
                                    <td>Город</td>
                                    <td>${user.city}</td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td>${user.email}</td>
                                </tr>
                                </tbody>
                            </table>
                            <c:if test="${authorized}">
                                <a href="edit" class="btn btn-primary">Редактировать профиль</a>
                                <a href="delete" class="btn btn-danger">Удалить профиль</a>
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

</html>
