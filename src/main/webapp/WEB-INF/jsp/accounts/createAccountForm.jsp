<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="wwb" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>
<body>


<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2> Creating new account </h2>

    <form:form modelAttribute="account" method="post"
               class="form-horizontal">
        <div class="control-group" id="owner">
            <label class="control-label">Client name</label>

            <c:out value="${account.client.firstName} ${account.client.lastName}"/>
        </div>
            <wwb:inputField label="Initial money ammount" name="moneyAmount"/>
             <%--<form:input label="Money" path="moneyAmount"/>--%>

        <div class="form-actions">
            <button type="submit">Add Account</button>

        </div>
    </form:form>

    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
