package io.robusta.jpa.business;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import io.robusta.jpa.entities.FunkoPop;
import io.robusta.jpa.entities.Universe;

public class FunkoPopServiceTest {
	
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
	    public void testGetFunkoPopsToShelter() {
	        yoda.setWaterproof(true);
	        spock.setWaterproof(false);
	        aragorn.setWaterproof(false);
	        gandalf.setWaterproof(true);
	        
	        boolean goodWeather = true;
	        boolean badWeather = false;

	        List<FunkoPop> expectedShelteredPops = new ArrayList<>();
	        expectedShelteredPops.add(spock);
	        expectedShelteredPops.add(aragorn);

	        List<FunkoPop> popMockFindAll = new ArrayList<>();
	        popMockFindAll.add(spock);
	        popMockFindAll.add(aragorn);
	        popMockFindAll.add(gandalf);
	        popMockFindAll.add(yoda);

	        // create mock
	        ExternalService externalMock = mock(ExternalService.class);
	        FunkoPopService myService = mock(FunkoPopService.class);

	        when(externalMock.isWeatherGood()).thenReturn(Optional.of(badWeather));
	        when(myService.findAll()).thenReturn(popMockFindAll);
	        when(myService.getFunkoPopsToShelter()).thenCallRealMethod(); // <<<<<
	        // use mock in test....

	        myService.externalService = externalMock;
	        List<FunkoPop> actual = myService.getFunkoPopsToShelter();

	        assertEquals("testing sheltered FunkoPop", expectedShelteredPops, actual);

	    }
}
