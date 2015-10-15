<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="wwb" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>

<script>
    $(function () {
        $("#birthDate").datepicker({ dateFormat: 'yy/mm/dd',
                                    changeYear: true,
                                    yearRange: "1900:2012"});
    });
</script>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <form:form modelAttribute="client" method="post"  class="form-horizontal">

    <wwb:inputField label="First Name" name="firstName"/>
    <wwb:inputField label="Last Name" name="lastName"/>
    <wwb:inputField label="Address" name="address"/>
    <wwb:inputField label="Birth Date" name="birthDate"/>

    <div class="form-actions">
        <button type="submit">Add Client</button>
    </div>
    </form:form>



<%--
<form:form commandName="client" cssClass="form-horizontal registrationForm">

    <div class="form-group">
        <label for="firstName" class="col-sm-2 control-label">First Name:</label>
        <div class="col-sm-10">
            <form:input path="firstName" cssClass="form-control" />
            &lt;%&ndash;<form:errors path="firstName" />&ndash;%&gt;
        </div>
    </div>
    <div class="form-group">
        <label for="lastName" class="col-sm-2 control-label">Last Name:</label>
        <div class="col-sm-10">
            <form:input path="lastName" cssClass="form-control" />
            &lt;%&ndash;<form:errors path="lastName" />&ndash;%&gt;
        </div>
    </div>

    <div class="form-group">
        <label for="address" class="col-sm-2 control-label">Address:</label>
        <div class="col-sm-10">
            <form:input path="address" cssClass="form-control" />
                &lt;%&ndash;<form:errors path="lastName" />&ndash;%&gt;
        </div>
    </div>

    <div class="form-group">
        <label for="birthDate" class="col-sm-2 control-label">BirthDate:</label>
        <div class="col-sm-10">
            <form:input path="birthDate" cssId="birthDate" cssClass="form-control" />
            &lt;%&ndash;<joda:format value="${visit.pet.birthDate}" pattern="yyyy/MM/dd"/>&ndash;%&gt;
             <wwb:inputField label="birthDate" name="birthDate" />
                &lt;%&ndash;<form:errors path="lastName" />&ndash;%&gt;
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-2">
            <input type="submit" value="Save" class="btn btn-lg btn-primary" />
        </div>
    </div>
</form:form>

--%>



<jsp:include page="../fragments/footer.jsp"/>
    </div>
</body>

</html>
