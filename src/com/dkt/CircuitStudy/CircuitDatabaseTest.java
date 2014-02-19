package com.dkt.CircuitStudy;

import static org.junit.Assert.*;

import org.junit.Test;

public class CircuitDatabaseTest {

	@Test
	public void testWriteObject() {
		CircuitDatabase cktDb = new CircuitDatabase();
		Circuit ckt = new Circuit();
		ckt.addComponent(new Resistance("R4", 400));
		ckt.addComponent(new Resistance("R5", 500));
		ckt.addComponent(new Resistance("R6", 600));
		//function to be tested
		cktDb.writeObject(ckt);
		
		Circuit ckt2 = new Circuit();
		
		//function to verify after write..
		cktDb.readObject(ckt2);
		ckt.printAll(System.out);
		
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testReadObject() {
		Circuit ckt = new Circuit();
		CircuitDatabase cktDb = new CircuitDatabase();
		cktDb.readObject(ckt);
		ckt.printAll(System.out);
		fail("Not yet implemented"); // TODO
	}

}
