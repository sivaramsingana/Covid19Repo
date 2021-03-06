<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Covid19 Patient Tracker Portal</title>
</head>
<body>
<div style="width: 1280px;min-height: 85px;background-color: rgb(158,215,222);">
<h1 style="color:darkslateblue;">COVID19 PATIENT TRACKER</h1>
<p style="font-family: verdana;font-size: 12px;">Please use this page for tracking covid 19 patient details and their status.</p>
<img src="data:image/jpeg;base64,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"
 style="width: 261px;float: right;height: 86px;position: relative;top: -88px;right: 49px;"/>
</div>
<div style="min-width:1280px ; min-height: 30px;color: olive; background-color:blanchedalmond; font-family: verdana;font-size: 13px;">
<div style="float: right;">
<a href="Index.jsp" style="width:5px">Home</a>
<a href="Register.jsp" style="padding: 7px;">Register Patient</a>
<a href="Updatestatus.jsp" style="padding: 7px;">Update Patient Status</a>
<a href="Search.jsp" style="padding: 7px;">Search patient</a>
<a href="Help.jsp" style="padding: 7px;">Help</a>
<a href="Contact.jsp" style="padding-right: 60px;">Contact</a>
</div>
</div>

<form action="Update" method="post">
<p>*These updates can be done at this moment.Thank you for your using our portal. </p>
<table>
<tr>
<td>Case Number</td><td><input type="number" name="casenum"></td>
</tr>
<tr>
<td>Test Result</td><td><select name="testresult"><option selected="selected">No</option><option>Yes</option></select></td>
</tr>
<tr>
<td>Recovery Status</td><td><select name="recoverstatus"><option selected="selected">No</option><option>Yes</option></select></td>
</tr>
<tr>
<td>Quarantine Status</td><td><select name="Qtnstatus"><option selected="selected">No</option><option>Yes</option></select></td>
</tr>
<tr>
<td><input type="submit" name="Update details" /></td>
</tr>

<tr><td>
	<% if(request.getAttribute("msg")!=null)
	{
	out.print(request.getAttribute("msg"));
	}
	%>
	</td></tr>


</table>
</form>


</body>
</html>