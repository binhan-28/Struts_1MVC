<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sample</title>
	<style>
		body {
			background : #ccffff;
			width: 100%;
			height: 100%;
		}
		div.body-container {
			padding-left : 27px;
			padding-right : 27px;
		}
		div.welcome-logout {
			display : flex;
			width : 100%;
			padding-top : 2%;
			padding-bottom : 2%;
		}
		div.welcome-logout .welcome {
			width : auto;
		}
		div.welcome-logout .logout {
			width : auto;
			position : absolute;
			right : 1%;
		}
		div.color-border {
			padding-bottom : 1%
		}
		hr.color-line {
			background-color : #3366ff;
			size : 100px;
			width : 100%;
			height: 20px;			
		}
		div.condition-search {
			display : flex;
			height : 40px;
			padding-top : 20px;
			background-color : #ffff99;
		}
		div.condition-search .label-cus-name {
			display : flex; 
			position : absolute;
			width: 22%
		}
		div.condition-search .label-cus-name .label-customer-name{
			padding-left : 10%;
			width : 30%
		}
		div.condition-search .label-cus-name #txtCustomerName {
			height : 35%;
			width : 60%;
			margin-left : 10%;
		}
		div.condition-search .sex {
			position: absolute;
			display: flex;
			left : 38%;
		}
		div.condition-search .sex .span-sex{
			width : 130px;
		}
		div.condition-search .birthday {
			position : absolute;
			left : 60%;
		}
		div.condition-search .search {
			width : auto;
			position : absolute;
			right : 1%;
		}
		div.extension {
			display : flex;
			padding-top : 2%;
			padding-bottom : 2%;
		}
		div.extension-left {
			display : flex;
		}
		div.extension-left .button-previous{
			display : flex;
		}
		div.extension-left .label-previous{
			padding-left : 20%;
		}
		div.extension-right {
			display : flex;
			width : auto;
			position : absolute;
			right : 2%;
		}
		div.extension-right .button-next {
			display : flex;
		}
		div.extension-right .label-next{
			padding-right : 20%;
		}
		table.table-result-search {
			width : 100%;
			height : 100%;
		}
		div.extension-add-delete {
			display : flex;
			width : 100%;
			padding-top : 1%;
		}
		div.extension-add-delete #btnAddnew {
			width : 150px;
		}
		div.extension-add-delete #btnDelete{
			margin-left : 100%;
			width : 100px;
		}
		div.result-search .table-result-search {
			border : 3px solid #339966;
			text-align : left;
		}
		div.result-search .table-result-search .header-table-result-search {
			background-color : #339966;
		}
		div.result-search .table-result-search .a{
			background-color: white;
		}
		div.result-search .table-result-search .header-table-result-search .thAll {
			width : 30px;
		}
		div.result-search .table-result-search .thCustomerID {
			width : 10%;
		}
		div.result-search .table-result-search .thCustomerName {
			width : 30%;
		}
		div.result-search .table-result-search .thSex {
			width : 10%;
		}
		div.result-search .table-result-search .thBirthday {
			width : 10%;
		}
		tr:nth-child(even) {
		  background-color: white;
		}
	</style>
	<script type = "text/javascript">
		function checkAddress(){
			let isCheck = document.getElementById("chkAll");
			const nodeList = document.querySelectorAll("#chkDetail");
			if (isCheck.checked == true){
				for (let i = 0; i < nodeList.length; i++){
					nodeList[i].checked = true;
				}
			} else if (isCheck.checked == false) {
				for (let i = 0; i < nodeList.length; i++){
					nodeList[i].checked = false;
				}
			}
		}
		
	</script>
</head>
<body>
	<div class = "body-container">
		<%--header section --%>
		<div>	
			<jsp:include page = "include/header.jsp"></jsp:include>
		</div>
			<%--end header section --%>
		<div class = "container">
			<div>
				<span>Login > Search Customer</span>
			</div>
			<form action = "${pageContext.request.contextPath}/search" class = "condition-search" method = "post">
				<div class = "welcome-logout">
					<div class = "welcome">
							<span id = "lblUserName">Welcome ${userName}</span> 
						</div>
					<div class = "logout">
						<a href = "${pageContext.request.contextPath}/login" id = "llblLogout">Log Out</a>
					</div>
				</div>
				<div class = "color-border">
					<hr class = "color-line">
				</div>
				<div class = "condition-search">
					<div class = "label-cus-name">
						<span class = "label-customer-name">Customer Name</span>
						<input type = "text" id = "txtCustomerName" maxlength = "50" name = "CustomerName">
					</div>
					<div class = "sex">
						<span class = "span-sex">Sex</span>
						<select name = "Sex" class = "sex" id = "cboSex">
							<option value = "blank"></option>
							<option value = "0">Male</option>
							<option value = "1">Female</option>
						</select>
					</div>
					<div class = "birthday">
						<span class = "label-birthday">Birthday</span>
						<input type = "text" class = "birthday-from" id = "txtBirthdayForm" maxlength = "10" name = "birthdayFrom">
						<span>~</span>
						<input type = "text" class = "birthday-to" id = "txtBirthdayTo" maxlength = "10" name = "birthdayTo">
					</div>
					<div class = "search">
						<button class = "search" type = "submit" id = "btnSearch" name = "button" value = "btnSearch">Search</button>
					</div>
				</div>
				<div class = "extension">
					<div class = "extension-left">
						<div class = "button-previous">
							<button type = "submit" id = "btnFirst" name = "button" value = "btnFirst"
								<c:if test="${page == 1}">
									<c:out value = "disabled"/>
								</c:if>			
							><span> << </span></button>
							<button type = "submit" id = "btnPrevious" name = "button" value = "btnPrevious"
								<c:if test="${page == 1}">
									<c:out value = "disabled"/>
								</c:if>
							><span> < </span></button>
						</div>
						<div class = "label-previous">
							<span>Previous</span>
						</div>
					</div>
					<div class = "extension-right">
						<div class = "label-next">
							<span>Next</span>
						</div>
						<div class = "button-next">
							<button type = "submit" id = "btnNext" name = "button" value = "btnNext"
								<c:if test="${page >= limitPage}">
									<c:out value = "disabled"/>
								</c:if>
							><span> > </span></button>
							<button type = "submit" id = "btnLast" name = "button" value = "btnLast"
								<c:if test="${page >= limitPage}">
									<c:out value = "disabled"/>
								</c:if>
							><span> >> </span></button>
						</div>
					</div>
				</div>
				<div class = "result-search">
					<table class = "table-result-search" id = "table-result-search">
						<tr class = "header-table-result-search">
							<th class = "thAll"><input type = "checkbox" id = "chkAll" onchange = "checkAddress()"></th>
							<th><span>Customer ID </span></th>
							<th><span>Customer Name </span></th>
							<th><span>Sex </span></th>
							<th><span>Birthday </span></th>
							<th><span>Address </span></th>
						</tr>
						<c:forEach var = "item" items = "${listCus}">
							<tr>
								<td class = "thDetail"><input type = "checkbox" id = "chkDetail" ></td>
								<td class = "thCustomerID"><a id = "llblCustomerID" href = "${pageContext.request.contextPath}/edit?Id=0">${item.customerId}</a></td>
								<td class = "thCustomerName"><span id = "lblCustomerName">${item.customerName}</span></td>
								<td class = "thSex">
										<c:choose>
											<c:when test = "${item.sex == '1' }">
												<span id = "lblSex">Female</span>
											</c:when>
											
											<c:when test = "${item.sex == '0' }">
												<span id = "lblSex">Male</span>
											</c:when>
										</c:choose>
								</td>
								<td class = "thBirthday"><span id = "lblBirthday">${item.birthday}</span></td>
								<td class = "thAddress"><span id = "lblAddress">${item.address}</span></td>
							</tr>
						</c:forEach>
					</table>			
				</div>
				<div class = "extension-add-delete">
					<div class = "add-new">
							<button id = "btnAddnew" name = "button" value = "btnAddNew">Add new</button>
					</div>
					<div class = "delete">
							<button id = "btnDelete" name = "button" value = "btnDelete"
								<c:if test="${sizeList == 0}">
									<c:out value = "disabled"/>
								</c:if>
							>Delete</button>
					</div>
				</div>
			</form>	
		</div>
		<%--footer section --%>
		<div>	
			<jsp:include page = "include/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>