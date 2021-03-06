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
                <li><a href="friends"><spring:message code="profile.friends"/></a></li>
                <li class="active"><a><spring:message code="profile.findFriends"/></a></li>
            </ul>
        </div>
    </div>
    <div class="container">
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
            <div class="shadow">
                <h3><spring:message code="profile.findFriends"/></h3>

                <div class="view-box">

                    <form:form class="form-horizontal" method="get" action="users/find" charset="UTF-8"
                               accept-charset="UTF-8">
                        <div class="form-group">
                            <label class="control-label col-xs-2"><spring:message code="profile.name"/></label>
                            <div class="col-xs-8">
                                <input name="name" id="name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-xs-2"><spring:message code="profile.surname"/></label>
                            <div class="col-xs-8">
                                <input name="surname" id="surname"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-offset-2 col-xs-10">
                                <button type="submit" class="btn btn-primary"><spring:message code="common.search"/></button>
                            </div>
                        </div>
                    </form:form>

                    <c:if test="${!empty result}">
                        <table class="table table-striped display">
                            <tr>
                                <th width="80"><spring:message code="profile.name"/></th>
                                <th width="80"><spring:message code="profile.surname"/></th>
                                <th></th>
                            </tr>
                            <c:forEach items="${result}" var="user">
                                <tr>
                                    <td>${user.name}</td>
                                    <td>${user.surname}</td>
                                    <td><a href="profile?userId=${user.id}"><spring:message code="common.show"/></a></td>
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