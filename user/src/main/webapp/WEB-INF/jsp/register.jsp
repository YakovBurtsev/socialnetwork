<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="login">Социальная сеть</a>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3>
                Регистрация
            </h3>

            <div class="view-box">
                <form:form modelAttribute="user" class="form-horizontal" method="post"
                           action="register" charset="utf-8" accept-charset="UTF-8">

                    <div class="form-group">
                        <label class="control-label col-xs-2">Имя</label>
                        <div class="col-xs-8">
                            <form:input path="name" id="name"/>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">Фамилия</label>
                        <div class="col-xs-8">
                            <form:input path="surname" id="name"/>
                            <form:errors path="surname" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">Email</label>
                        <div class="col-xs-8">
                            <form:input path="email" id="email"/>
                            <form:errors path="email" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2">Пароль</label>
                        <div class="col-xs-8">
                            <form:password path="password" id="password"/>
                            <form:errors path="password" cssClass="error"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>