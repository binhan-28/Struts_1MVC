<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>'
<%@taglib uri = "/WEB-INF/struts-html.tld" prefix = "html" %>
<%@taglib uri = "/WEB-INF/struts-logic.tld"  prefix = "logic"%>
<%@taglib uri = "/WEB-INF/struts-bean.tld" prefix = "bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sample</title>
	<script type = "text/javascript">
		function clearLabelAndInputFields() {
			document.getElementById("lblErrorMessage").innerHTML = "";
		}
	</script>
	<style>
		body {
			background : #ccffff;
			width: 100%;
			height: 100%;
		}
		div.login {
			text-align : center;
			padding-top : 80px;
		}
		table {
			margin : auto;
		}
		td#txtUserID {
			text-align : left;
		}
		td#txtPassword {
			text-align : left;
		}	
		p {
			font-size : 20px;
		}
	</style>
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
				<span>Login</span>
			</div>
			<div class = "login">
				<div>
					<p style = "color : #3366ff"><b>LOGIN</b></p>
				</div>
				<div>
					<%-- <p style = "color : red; display : block" id = "lblErrorMessage">${errorMsg}</p> --%>
					<logic:messagesPresent message="true">
						<font color = "red">
							<html:messages id="message" message="true">			
					            <bean:write name = "message"/>
						    </html:messages>
						</font>
					</logic:messagesPresent>
					
				</div>		
				<div>
					<html:form action="Login.do" method ="post">
						<table border = 0>
							<tr>
								<td id = "txtUserID" >User Id:</td>
								<td>
									<input type = "text" name = "uerID" maxlength = "8"/> 
									<%-- <html:text property = "useID"/>	 --%>
								</td>
							</tr>
							<tr>
								<td id = "txtPassword" >Password:</td>
								<td>
									<input type = "password" name = "password" maxlength = "8"/>	
									<%-- <html:password property="password"/> --%>
								</td>
							</tr>
							<tr>
								<td>
									<button id="btnLogin" type="submit">Login</button>
								</td>
								<td>
									<button id="btnClear" type ="reset" onclick = clearLabelAndInputFields()>Clear</button>
								</td>
							</tr>
						</table>
					</html:form>
				</div>		
			</div>
		</div>
		<%--footer section --%>
			<div>	
				<jsp:include page = "include/footer.jsp"></jsp:include>
			</div>
	</div>
</body>
</html>