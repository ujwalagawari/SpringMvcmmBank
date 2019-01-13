<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="core" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
	<title>Banking Application</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/footerStyle.css">
    <style type="text/css">
	.error{
		color:red 
	}
</style>
</head>
<body>
	<div class="header">
            <h1>Money Money Bank </h1>
            <div class="details">
                    <p>Contac us - 22450 57111<p>
                            <p>Email - <i style="color: blue">mm_bank@gmail.com</i></p>
            </div>
        </div>
        <nav id="menu">
            <ul>
            	<jsp:include page="homeLink.html"></jsp:include>
			</ul>
        </nav>

        <h1 align="center">Registration Form</h1>
                 <hr>
        <div align="center">
		<spring:form action="addNewCA" modelAttribute="account">
			<div id="accountNo">
			    <label>Account Number: </label> 
				<spring:input path="bankAccount.accountNumber" id="accountNumber" value="${account.bankAccount.accountNumber}" readonly="readonly" />
				<spring:errors path="bankAccount.accountNumber" cssClass="error"/>
				<br>
			</div>
			
			<label>Enter Account Holder Name: </label> 
			<spring:input path="bankAccount.accountHolderName" id="txtAccHN" value="${account.bankAccount.accountHolderName }" />
			<spring:errors path="bankAccount.accountHolderName" cssClass="error"/>
			<br />
			
			<label>Enter Account Balance: </label>
			<spring:input path="bankAccount.accountBalance" id="txtBalance" value="${account.bankAccount.accountBalance}" />
			<spring:errors path="bankAccount.accountBalance" cssClass="error"/>
			<br />
			
			<div id="od">
				<label>Over Draft Limit </label>
				<spring:input path="odLimit" value="1000" />
				<spring:errors path="odLimit" cssClass="error"/>
			</div>
			<br />
			
			<input type="submit" id="submit" value="Submit" /> 
			<input type="submit" id="update" value="Update" /> 
			<input type="reset" value="Clear" />
		</spring:form>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$("#od").hide();
			if($("#accountNumber").val()!=0){
				$("#submit").hide();
			}else if($("#accountNumber").val()==0){
				$("#accountNo").hide();
				$("#update").hide();
			}
		});
	</script>
<footer>Copyscape © 2018 Money Money Bank. All rights reserved. Terms of Use.</footer>
    <!-- <object  type="text/html" data="resources/include/footer.html"></object> -->
    
</body>
</html>










