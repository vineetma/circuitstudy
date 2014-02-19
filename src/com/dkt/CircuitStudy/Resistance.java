package com.dkt.CircuitStudy;

import org.json.JSONObject;

public class Resistance extends Component {
	protected int impedence;

	public Resistance(String name, int imp) {
		super(name);
		impedence = imp;
		EndPoint ep1 = new EndPoint(name.toLowerCase() + "_1", this);
		epList.add(ep1);
		ep1 = new EndPoint(name.toLowerCase() + "_2", this);
		epList.add(ep1);
	}

	public Resistance(String name) {
		this(name, 0);
	}

	public int getImpedence() {

		return 10;
	}

	public String toString() {
		JSONObject jo = new JSONObject();
		jo.put("impedence", impedence);
		jo.put("name", getName());
		return jo.toString();
	}

	public JSONObject toJSONObject() {
		JSONObject jo = new JSONObject();
		jo.put("class", "Resistance");
		jo.put("impedence", impedence);
		jo.put("name", getName());
		return jo;

	}
}
