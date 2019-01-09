<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Banking Application</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="resources/css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/footerStyle.css">
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
		<form action="addNewSA">
			<div id="accountNo">
			    <label>Account Number: </label> 
				<input type="text" name="accountNumber" id="accountNumber" value="${requestScope.account.bankAccount.accountNumber}" readonly="readonly" />
				<br>
			</div>
			
			<label>Enter Account Holder Name: </label> 
			<input type="text" name="txtAccHN" id="txtAccHN" value="${requestScope.account.bankAccount.accountHolderName }" />
			<br />
			
			<label>Enter Account Balance: </label>
			<input type="number" name="txtBalance" id="txtBalance" value="${requestScope.account.bankAccount.accountBalance}" />
			<br />
			
			<input type="hidden" id="salary" value="${requestScope.account.salary}">
			
			<label>Salaried?: </label>
			<input type="radio" name="rdSalary" id="yes" value="Yes" /> Yes
			<input type="radio" name="rdSalary" id="no" value="No" /> No 
			<br />
			
			<input type="submit" id="submit" value="Submit" /> 
			<input type="submit" id="update" value="Update" /> 
			<input type="reset" value="Clear" />
		</form>
	</div>
	
	<script type="text/javascript">
		$(function(){
			if($("#accountNumber").val()!=""){
				$("#submit").hide();
				//$("#txtAccHN").attr('readonly', true);
				$("#txtBalance").attr('readonly', true);
				if($("#salary").val()=="true"){
					 $("#yes").attr('checked', 'checked');
				}else{
					 $("#no").attr('checked', 'checked');
				}
			}else if($("#accountNumber").val()==""){
				$("#accountNo").hide();
				$("#update").hide();
			}
		});
	</script>
<footer>Copyscape © 2018 Money Money Bank. All rights reserved. Terms of Use.</footer>
    <!-- <object  type="text/html" data="resources/include/footer.html"></object> -->
    
</body>
</html>










