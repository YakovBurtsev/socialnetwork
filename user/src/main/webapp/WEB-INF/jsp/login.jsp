<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand"><spring:message code="app.title"/></div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form:form class="navbar-form" role="form" action="spring_security_check" method="post">
                        <div class="form-group">
                            <input type="text" placeholder="email" class="form-control" name='email'>
                        </div>
                        <div class="form-group">
                            <input type="password" placeholder="пароль" class="form-control" name='password'>
                        </div>
                        <button type="submit" class="btn btn-success"><spring:message code="app.login"/></button>
                    </form:form>
                </li>
                <jsp:include page="fragments/lang.jsp"/>
            </ul>
        </div>
    </div>
</div>

<div class="jumbotron">
    <div class="container">
        <p></p>

        <p><a class="btn btn-primary btn-lg" role="button" href="register"><spring:message code="app.register"/></a></p>
        <p>Стек технологий:
            <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
            <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
            <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
            <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
            <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
            <a href="http://www.slf4j.org/">SLF4J</a>,
            <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
            <a href="http://ru.wikipedia.org/wiki/JSP">JSP</a>,
            <a href="http://en.wikipedia.org/wiki/JavaServer_Pages_Standard_Tag_Library">JSTL</a>,
            <a href="http://www.webjars.org/">WebJars</a>,
            <a href="http://junit.org/">JUnit</a>,
            <a href="http://getbootstrap.com/">Bootstrap</a>.</p>
    </div>
</div>
<div class="container">
    <div class="lead">
        <p>Java Enterprise проект Социальная сеть</p>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>