<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <title>Banking Application</title>
    <link rel="stylesheet" type="text/css" href="resources/css/headerStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/formStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/footerStyle.css">
    <link rel="stylesheet" type="text/css" href="resources/css/tableStyle.css">
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

        <h1 align="center">Fund Transfer</h1>
                 <hr>
	<table style="width:100%">
		<tr>
			<th><a href="sortByNumber.mm">Account Number</a></th>
			<th><a href="sortByName.mm">Holder Name</a></th>
			<th><a href="sortByBalance.mm">Account Balance</a></th>
			<th><a href="sortBySalary.mm">Salary</a></th>
			<th>Over Draft Limit</th>
			<th>Type Of Account</th>
		</tr>
		<jstl:if test="${account!=null}">
			<tr>
				<td>${account.bankAccount.accountNumber}</td>
				<td>${account.bankAccount.accountHolderName }</td>
				<td>${account.bankAccount.accountBalance}</td>
				<td> <%-- <td>${account.salary==true?"Yes":"No"}</td> --%>
					<jstl:catch var="exception">${account.salary==true?"Yes":"No"}</jstl:catch>
					<jstl:if test="${not empty exception}">null</jstl:if>
				</td>
				<td><!-- <td>${"N/A"}</td> -->
					<jstl:catch var="exception">${account.odLimit}</jstl:catch>
					<jstl:if test="${not empty exception}">null</jstl:if>
				</td>	
				
				<td>${account.bankAccount.type}</td>
			</tr>
		</jstl:if>
		<jstl:if test="${accounts!=null}">
			<jstl:forEach var="account" items="${accounts}">
				<tr>
					<td>${account.bankAccount.accountNumber}</td>
					<td>${account.bankAccount.accountHolderName }</td>
					<td>${account.bankAccount.accountBalance}</td>
					<td> <%-- <td>${account.salary==true?"Yes":"No"}</td> --%>
						<jstl:catch var="exception">${account.salary==true?"Yes":"No"}</jstl:catch>
						<jstl:if test="${not empty exception}">null</jstl:if>
					</td>
					<td><!-- <td>${"N/A"}</td> -->
						<jstl:catch var="exception">${account.odLimit}</jstl:catch>
						<jstl:if test="${not empty exception}">null</jstl:if>
					</td>
					<td>${account.bankAccount.type}</td>
				</tr>
			</jstl:forEach>
		</jstl:if>
	</table>
  <footer>Copyscape © 2018 Money Money Bank. All rights reserved. Terms of Use.</footer>
    <!-- <object  type="text/html" data="resources/include/footer.html"></object> -->
    
	
</body>
</html>







