package com.dkt.CircuitStudy;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComponentTest {

	@Test
	public void testToString() {
		Component c = new Resistance("TestComponent");
		assertEquals("{\"name\":\"TestComponent\",\"impedence\":0}", c.toString());
//		fail("Not yet implemented"); // TODO
	}

}
