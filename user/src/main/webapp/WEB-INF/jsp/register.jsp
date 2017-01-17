<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datetimepicker/2.4.7/jquery.datetimepicker.css">

<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="login"><spring:message code="app.title"/></a>
    </div>
</div>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3>
                <c:if test="${register}">
                    <spring:message code="app.registration"/>
                </c:if>
                <c:if test="${not register}">
                    <spring:message code="profile.editing"/>
                </c:if>
            </h3>

            <div class="view-box">
                <form:form modelAttribute="user" class="form-horizontal" method="post" enctype="multipart/form-data"
                           action="${register ? 'register' : 'edit'}" charset="utf-8" accept-charset="UTF-8">

                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.name"/></label>
                        <div class="col-xs-8">
                            <form:input path="name" id="name"/>
                            <form:errors path="name" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.surname"/></label>
                        <div class="col-xs-8">
                            <form:input path="surname" id="surname"/>
                            <form:errors path="surname" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.dateOfBirth"/></label>
                        <div class="col-xs-8">
                            <form:input path="birthday" type="text" name="birthday" id="birthday"/>
                            <form:errors path="birthday" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.sex"/></label>
                        <div class="col-xs-8">
                            <spring:message code="sex.male" var="male"/>
                            <spring:message code="sex.female" var="female"/>
                            <form:radiobutton path="sex" id="sex-male" value="MALE" label="${male}"/>
                            <form:radiobutton path="sex" id="sex-female" value="FEMALE" label="${female}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.city"/></label>
                        <div class="col-xs-8">
                            <form:input path="city" id="city"/>
                            <form:errors path="city" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.email"/> </label>
                        <div class="col-xs-8">
                            <form:input path="email" id="email"/>
                            <form:errors path="email" cssClass="error"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.password"/></label>
                        <div class="col-xs-8">
                            <form:password path="password" id="password"/>
                            <form:errors path="password" cssClass="error"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-xs-2"><spring:message code="profile.photo"/></label>
                        <div class="col-xs-8">
                            <input name="avatar" type="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-2 col-xs-10">
                            <c:if test="${register}">
                                <button type="submit" class="btn btn-primary">
                                    <spring:message code="app.register"/>
                                </button>
                                <a href="login" role="button" class="btn btn-primary">
                                    <spring:message code="common.cancel"/>
                                </a>
                            </c:if>
                            <c:if test="${not register}">
                                <button type="submit" class="btn btn-primary">
                                    <spring:message code="common.save"/>
                                </button>
                                <a href="profile" role="button" class="btn btn-primary">
                                    <spring:message code="common.cancel"/>
                                </a>
                            </c:if>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>

<script type="text/javascript" src="webjars/datetimepicker/2.4.7/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript">
    $(function () {
        $.datetimepicker.setLocale('ru');

        $('#birthday').datetimepicker({
            timepicker: false,
            format: 'Y-m-d',
            formatDate: 'Y-m-d'
        });
    });
</script>
</html>