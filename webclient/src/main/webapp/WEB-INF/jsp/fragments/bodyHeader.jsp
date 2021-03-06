<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header navbar-brand"><spring:message code="app.title"/></div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form:form class="navbar-form" action="logout" method="post">
                        <sec:authorize access="isAuthenticated()">
                        <input type="submit" class="btn btn-primary" value=<spring:message code="app.logout"/>>
                        </sec:authorize>
                    </form:form>
                </li>
                <jsp:include page="lang.jsp"/>
            </ul>
        </div>
    </div>
</div>
