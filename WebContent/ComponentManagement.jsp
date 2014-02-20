<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.OutputStream"%>
<%@page import="sun.org.mozilla.javascript.internal.json.JsonParser"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.InputStream"%>
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
	} else {
		//we come here because we have get parameter action..
		// also we should check if value of action is "add" than only rest 
		// of the things should be done
		InputStream is = request.getInputStream();
		// alternatively, one can convert inputstream to string using IOUtils
		//  IOUtils.toString(is, "UTF-8");
		if (is != null) {
			/* lets get input stream reader to get access to the body of the
				request */
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String objStr = null;
			String s = null;

			/* Retrieve the whole of string */
			while ((s = br.readLine()) != null) {
				if (objStr == null)
					objStr = s;
				else
					objStr = objStr + s;
			}
			if (request.getParameter("action").equals("add")) {
				if (objStr != null && objStr.length() > 0) {
					JSONObject js = new JSONObject(objStr);
					int type = js.getInt("type");
					String name = js.getString("name");
					if (type == 1) { /* If type is 1, it is resistance */
						int impedence = js.getInt("impedence");
						ckt.addComponent(new Resistance(name, impedence));
						cktDb.writeObject(ckt);
					}
					/* Lets get access to the response body */
					//OutputStream os = response.getOutputStream();
					PrintWriter pw = new PrintWriter(out);
					response.setContentType("application/json");
					/* Write the status of the processing to this output */
					JSONObject jso = new JSONObject();
					jso.put("status", true);
					pw.print(jso.toString());
					return;
				}
			} else if(request.getParameter("action").equals("list")) {
				ckt.printAll(out);
				
				return;
			}
			
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