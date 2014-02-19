package com.dkt.CircuitStudy;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.sun.xml.internal.bind.v2.runtime.Name;

public class Circuit implements DBInterface {

	List<Component> compList = new ArrayList<Component>();
	List<Connection> connecList = new ArrayList<Connection>();

	public void addComponent(Component c) {
		compList.add(c);

	}

	public boolean makeConnection(String n, EndPoint ep1, EndPoint ep2) {
		Connection c = new Connection(n, ep1, ep2);
		connecList.add(c);

		return true;

	}

	public void printAll(JspWriter out) {
		for (Component c : compList) {
			try {
				out.println("name: " + c.getName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void printAll(PrintStream out) {
		for (Component c : compList) {
				out.println("name: " + c.getName());
		}
	}
	public int getComponentsCount() {
		return compList.size();
	}

	public String toString() {
		JSONArray jo = new JSONArray();
		for (Component c : compList) {
			// outString = "Component={" + c.toString()+"}:" + outString;
			jo.put(c.toJSONObject());

		}
		return jo.toString();
	}

	public boolean initFromString(String js) {
		JSONArray json = new JSONArray(js);
		for (int i = 0; i < json.length(); i++) {
			JSONObject jo = json.getJSONObject(i);
			if (jo.get("class").equals("Resistance")) {
				compList.add(new Resistance(jo.getString("name")));
			}
		}
		return true;
	}

	@Override
	public boolean writeToDb(java.sql.Connection conn) {
		// TODO Auto-generated method stub
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql;
			int type = 0;
			for(Component c: compList) {
				sql = "select name from component where name='"+c.getName()+"'";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) continue;
				if(c instanceof Resistance)
					type = 1;
				else type = 0;
				sql = "insert into component (name, type) values('"+c.getName()+"', '"+type+"')";
				stmt.executeUpdate(sql);
				ResultSet keys = stmt.getGeneratedKeys();
				keys.next(); 
				int id = keys.getInt(1);
				if(type == 1) {
					sql = "insert into resistance (id, impedence) values('"+id+"', '"+c.getImpedence()+"')";
					stmt.executeUpdate(sql);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean readFromDb(java.sql.Connection conn) {
		// TODO Auto-generated method stub
		java.sql.Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT component.id as cid, type, name, impedence FROM component left join resistance on component.id=resistance.id";
			java.sql.ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Component c = null;
				switch(rs.getInt("type")) {
				case 1:
					c = new Resistance(rs.getString("name"), rs.getInt("impedence"));
					break;
				default:
					break;
				}
				if(c != null) {
					compList.add(c);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
