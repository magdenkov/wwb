<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<spring:url value="/resources/images/banner-graphic.png" var="banner"/>
<img src="${banner}"/>--%>

<h2>WILD WEST BANK</h2>

<div class="navbar" style="width: 690px;">
    <div class="navbar-inner">
        <ul class="nav">
            <li style="width: 120px;"><a href="<spring:url value="/clients.html" htmlEscape="true" />"><i class="icon-user"></i>
                Clients</a></li>
            <li style="width: 110px;"><a href="<spring:url value="/accounts.html" htmlEscape="true" />"><i
                    class="icon-th-list"></i>Accounts</a></li>
            <li style="width: 160px;"><a href="<spring:url value="/transactions.html" htmlEscape="true" />"><i
                    class="icon-random"></i>Transactions log</a></li>
            <li style="width: 140px;"><a href="<spring:url value="/makeTransfer.html" htmlEscape="true" />"><i
                    class="icon-retweet"></i>Make Transfer</a></li>

        </ul>
    </div>
</div>
	
