<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div>
		<h2>Reports</h2>
		<form:form action="search" modelAttribute="search" method="POST">
			<table>
				<tr>
					<td>Plan Name:</td>
					<td>
						<form:select path="planName">
							<form:option value="">-Select-</form:option>
							<form:options items="${names}"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Plan Status:</td>
					<td>
						<form:select path="planStatus">
							<form:option value="">-Select-</form:option>
							<form:options items="${status}"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td>
						<form:select path="gender">
							<form:option value="">-Select-</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Female">Female</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>Start date:</td>
					<td><form:input type="date" path="planStartDate"/></td>
					<td>Start date:</td>
					<td><form:input path="planEndDate" type="date"/></td>
				</tr>
				<tr>
					<td>
						<a href="/" style="text-decoration : none; 
							border: 2px solid red; border-color: black;
							padding: 1px 10px; border-radius: 5px
							">Reset</a>
					</td>
					<td>
						<input type="submit" value="Submit"/>
					</td>
				</tr>
			</table>
		</form:form>
		<hr>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>HOLDER NAME</th>
						<th>GENDER</th>
						<th>PLAN NAME</th>
						<th>PLAN STATUS</th>
						<th>START DATE</th>
						<th>END DATE</th>
						<th>BENEFIT AMOUNT</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${plans}" var="plan">
						<tr>
							<td>${plan.citizenId}</td>
							<td>${plan.citizenName}</td>
							<td>${plan.gender}</td>
							<td>${plan.planName}</td>
							<td>${plan.planStatus}</td>
							<td>${plan.planStartDate}</td>
							<td>${plan.planEndDate}</td>
							<td>${plan.benefitAmount}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty plans}">
  						<p>No records are found!</p>
					</c:if>
				</tbody>
			</table>
		<hr>
		Export : <a href="excel" style="text-decoration : none; 
							border: 2px solid red; border-color: black;
							padding: 1px 10px; border-radius: 5px">Excel</a>
				 <a href="pdf" style="text-decoration : none; 
							border: 2px solid red; border-color: black;
							padding: 1px 10px; border-radius: 5px">Pdf</a>
	</div>
	

</body>
</html>