<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.dkt.CircuitStudy.*"%>
<%!//Circuit ckt = new Circuit();cktDb.readObject(ckt);
	CircuitDatabase cktDb = new CircuitDatabase();%>

<%
	Circuit ckt = new Circuit();
	cktDb.readObject(ckt);
	if (ckt.getComponentsCount() == 0) {
		cktDb.readObject(ckt);

		/* Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Cookie cookie = null;
			for (Cookie c : cookies) {
				if (c.getName().equals("circuit")) {
					cookie = c;
					break;
				}
			}
			if (cookie != null) {
				ckt.initFromString(cookie.getValue());
			}
		}*/
	}
	if (request.getParameter("action") == null) {
		if (request.getParameter("add") != null) {
			//process add command..
			String cn = request.getParameter("compName");
			int t = Integer.parseInt(request.getParameter("compType"));
			if (t == 1) {
				ckt.addComponent(new Resistance(cn));
				cktDb.writeObject(ckt);
				//				Cookie cookie = new Cookie("circuit", ckt.toString());
				//				response.addCookie(cookie);
			}
			response.sendRedirect("ComponentManagement.jsp");
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Component Management</title>
<link rel="stylesheet" type="text/css" href="styles.css" />
</head>
<body>
	<%
		if (request.getParameter("action") == null
				&& (request.getParameter("add") == null && request
						.getParameter("list") == null)) {
	%>
	<a href="ComponentManagement.jsp?action=add">Add Component Form</a>

	<a class="list" href="ComponentManagement.jsp?action=list">List
		Components Form</a>
	<%
		} else if (request.getParameter("action") != null) {
			if (request.getParameter("action").equals("add")) {
	%>
	<form action="ComponentManagement.jsp">
		<label for="compName">Component Name</label> <input type="text"
			name="compName" value="" /> <br /> <label for="compType">Component
			Type</label> <select name="compType">
			<option value="1">Resistor</option>
			<option value="2">Capacitor</option>
		</select> <br /> <input type="submit" name="add" value="Add Component" /> <input
			type="submit" name="list" value="List Components" />
	</form>
	<%
		} else if (request.getParameter("action").equals("list")) {

				//process list command..
				ckt.printAll(out);

			}
		}
	%>




</body>
</html>