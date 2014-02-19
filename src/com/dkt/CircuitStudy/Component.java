package com.dkt.CircuitStudy;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONString;
import org.json.JSONStringer;
import org.json.JSONWriter;

import sun.org.mozilla.javascript.internal.json.JsonParser;

public abstract class Component {
	protected String name;

	public abstract int getImpedence();
	public abstract JSONObject toJSONObject();

	List<EndPoint> epList = new ArrayList<EndPoint>();

	public Component(String componentName) {
		name = componentName;

	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
