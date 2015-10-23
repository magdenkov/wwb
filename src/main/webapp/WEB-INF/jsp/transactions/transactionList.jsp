<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="wwb" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">


<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
  <jsp:include page="../fragments/bodyHeader.jsp"/>



  <h2>Transactions</h2>

    <c:if test="${param.success eq true}">
    <div class="alert alert-success">new transaction has been made successfully!</div>
    </c:if>

  <datatables:table id="transactions" data="${transactions}" row="transaction" theme="bootstrap2" cssClass="table table-striped" pageable="false" info="false">
  <datatables:column title="ID">
    <c:out value="${transaction.id} "></c:out>
  </datatables:column>
  <datatables:column title="Account id From">
      <c:out value="${transaction.accountFrom.id} ${transaction.accountFrom.client.firstName} ${transaction.accountFrom.client.lastName}"></c:out>
    </datatables:column>
    <datatables:column title="Account id To">
      <c:out value="${transaction.accountTo.id} ${transaction.accountTo.client.firstName} ${transaction.accountTo.client.lastName}"></c:out>
    </datatables:column>
    <datatables:column title="money ammount">
      <fmt:formatNumber type="currency"   value="${transaction.moneyTransferAmount}" />
    </datatables:column>
    <datatables:column title="message">
      <c:out value="${transaction.message}"></c:out>
    </datatables:column>
    <datatables:column title="tDate">
     <joda:format value="${transaction.tDate}" pattern="yyyy-MM-dd HH:mm"/>

    </datatables:column>
  </datatables:table>

  <div class="container">


    <jsp:include page="../fragments/footer.jsp"/>
  </div>
</body>

</html>
