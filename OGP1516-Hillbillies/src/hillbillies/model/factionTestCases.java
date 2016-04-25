package hillbillies.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class factionTestCases {
	
	public World testWorld;

	@Before
	public void setUp() throws ModelException  {
		TerrainChangeListener modelListener = new TerrainChangeListener() {
			
			@Override
			public void notifyTerrainChanged(int x, int y, int z) {				
			}
		};
		testWorld = new World(new int [5][5][5], modelListener);
	}

	@Test
	public void add_removeUnitToFaction() throws IllegalArgumentException, ModelException {
		int[] pos = {0,0,0};
		Unit alive = new Unit("Emil", pos, 50, 50, 50, 50, false, testWorld);
		Faction f = new Faction(testWorld);
		
		f.addUnitToFaction(alive);
		assertEquals(1, f.getUnitsInFaction().size());
		
		f.removeUnitFromFaction(alive);
		assertEquals(0, f.getUnitsInFaction().size());
	}
	
	@Test
	public void add_removeFactionsFromWorld() throws ModelException {
		Faction f = new Faction(testWorld);
		testWorld.createFaction(f);
		
		assertEquals(1, testWorld.getAmountOfFaction());
		
		//TODO maken we een remove faction functie??
	}
	
	@Test
	public void cannotAddMoreUnits() throws IllegalArgumentException, ModelException {
		Faction f = new Faction(testWorld);
		for(int i=0; i<50; i++) {
			Unit unit = createRandomUnit();
			f.addUnitToFaction(unit);
		}
		
		assertEquals(50, f.getAmountOfUnitsInFaction());
		assertFalse(f.canAddUnit()); //we can not add more uits than 50
		
	}
	
	private Random rand = new Random();
	
	private Unit createRandomUnit() throws IllegalArgumentException, ModelException {
		int x = rand.nextInt(50);
		int y = rand.nextInt(50);
		int z = rand.nextInt(50);
		
		int weight = rand.nextInt(100);
		int strength = rand.nextInt(75) + 25;
		int agility = rand.nextInt(75) + 25;
		int toughness = rand.nextInt(75) + 25;
		
		Unit newUnit = new Unit("New Unit", new int[] {x, y, z}, weight, agility, strength, toughness, false, testWorld);
		
		return newUnit;
	}

}
