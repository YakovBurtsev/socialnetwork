<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
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
                <li><a href="profile"><spring:message code="profile.home"/></a></li>
                <li class="active"><a><spring:message code="profile.friends"/></a></li>
                <li><a href="users"><spring:message code="profile.findFriends"/></a></li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
            <div class="shadow">
                <h3><spring:message code="profile.friends"/></h3>

                <div class="view-box">
                    <c:if test="${!empty friends}">
                        <table class="table table-striped display">
                            <tr>
                                <th width="80"><spring:message code="profile.name"/></th>
                                <th width="80"><spring:message code="profile.surname"/></th>
                                <th width="80"></th>
                                <th width="80"></th>
                            </tr>
                            <c:forEach items="${friends}" var="user">
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.surname}</td>
                                    <td>
                                        <a href="profile?userId=${user.id}" class="btn btn-primary">
                                            <spring:message code="common.show"/>
                                        </a>
                                    </td>
                                    <td>
                                        <form method="post" action="friends?friendId=${user.id}">
                                            <input type="hidden" name="_method" value="delete">
                                            <spring:message code="friends.delete" var="delete_from_friends"/>
                                            <input type="submit" class="btn btn-danger" value="${delete_from_friends}">
                                        </form>
                                    </td>
                                </tr>

                            </c:forEach>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>


</body>

</html>