<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="en">

<jsp:include page="fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <h1>Welcome to wild west bank!</h1>
    <div class="container">
        <spring:url value="/resources/images/wwb.png" htmlEscape="true" var="wwbImage"/>
        <img src="${wwbImage}"/>
        <jsp:include page="fragments/footer.jsp"/>
    </div>
</div>
</body>

</html>
