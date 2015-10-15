<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="wwb" tagdir="/WEB-INF/tags" %>


<html lang="en">



<jsp:include page="../fragments/staticFiles.jsp"/>
<body>


<script>
    $( document ).ready(function() {
        $('#accountFrom option:first').attr("selected", "selected");
        $('#accountTo option:first').attr("selected", "selected");

    })
</script>

<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>
        Make a transfer
    </h2>

    <form:form commandName="transaction/new" modelAttribute="transaction" method="post"
               class="form-horizontal">

    <table>
        <tr>
            <td>
                <div class="control-group">
                    <wwb:selectField name="accountFrom" label="Account From " names="${accountFrom}" size="8"/>
                </div>
            </td>
            <td>
                <div class="control-group">
                    <wwb:selectField name="accountTo" label="Account To " names="${accountTo}" size="8"/>
                </div>
            </td>
        </tr>
    </table>
    <wwb:inputField label="Money ammount" name="moneyTransferAmount"/>
    <wwb:inputField label="Enter message" name="message"/>


    <div class="form-actions">
        <button type="submit">Make a transfer</button>

    </div>

</div>
</form:form>



<jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
