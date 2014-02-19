package com.dkt.CircuitStudy;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircuitTest {

	@Test
	public void testToString() {
		Circuit ckt = new Circuit();
		ckt.addComponent(new Resistance("R1"));
		assertEquals("{\"0\":\"{\"name\":\"R1\",\"impedence\":0}\"}", ckt.toString());
//		fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testInitFromString() {
		Circuit ckt = new Circuit();
		ckt.addComponent(new Resistance("R177"));
		String s1 = ckt.toString();
		
		ckt.initFromString(s1);
		assertEquals(1, ckt.getComponentsCount());
//		fail("Not yet implemented"); // TODO
	}

}
