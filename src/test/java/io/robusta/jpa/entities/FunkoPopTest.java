package io.robusta.jpa.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.robusta.jpa.EmFactory;

public class FunkoPopTest {
	
	Universe lotr;
	Universe starWars;
	Universe starTrek;
	FunkoPop gandalf;
	FunkoPop aragorn;
	FunkoPop yoda;
	FunkoPop spock;
	
	@Before
	public void start(){
		System.out.println("Running @Before");
		lotr = new Universe("LOTR");
		starWars = new Universe("Star Wars");
		starTrek = new Universe("Star Trek");
		gandalf = new FunkoPop("Gandalf", lotr);
		aragorn = new FunkoPop("Aragorn", lotr);
		yoda = new FunkoPop("Yoda", starWars);
		spock = new FunkoPop("Spock", starTrek);
		
	}

	@Test
	public void testToString() {
		assertTrue(gandalf.toString().equals("Gandalf (LOTR)"));
	}

}
