package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.part2.facade.Facade;
import hillbillies.part2.facade.IFacade;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class BoulderLogTestCases {

	public Log log;
	public Boulder boulder;
	public World testWorld;
	public Unit testunit;
	
	@Before
	public void setUp() throws Exception {
		TerrainChangeListener modelListener = new TerrainChangeListener() {
			
			@Override
			public void notifyTerrainChanged(int x, int y, int z) {
				// TODO Auto-generated method stub
			}
		};
		testWorld = new World(new int [50][50][50], modelListener);
		 log = new Log(new double[] {5,5,5});
		 boulder = new Boulder( new double[] {4,4,4});
		 
		 int[] pos = {0,0,0};
		testunit = new Unit("Emil", pos, 50, 50, 50, 50, false, testWorld);
	}

	@Test
	public void weight() {
		assertTrue(10 <= log.getWeight());
		assertTrue(50 >= log.getWeight());
		
		assertTrue(10 <= boulder.getWeight());
		assertTrue(50 >= boulder.getWeight());
	}

	@Test 
	public void position() {
		assertTrue(log.isValidPosition(log.getPosition()));
		assertTrue(boulder.isValidPosition(boulder.getPosition()));
		
		assertTrue(log.isValidPosition(new double[] {1,1,1}));
		assertFalse(log.isValidPosition(new double[] {1520,2541,25891}));
		
		assertTrue(boulder.isValidPosition(new double[] {1,1,1}));
		assertFalse(boulder.isValidPosition(new double[] {1520,2541,25891}));
	}
	
	@Test
	public void carried() {
		testunit.startCarryingBoulder(boulder);
		boulder.startBeingCarried(testunit);
		
		assertEquals(boulder.getCarrier(), testunit);
		assertTrue(testunit.isCarryingBoulder());
		
		testunit.stopCarryingBoulder();
		assertEquals(boulder.getCarrier(), null);
		assertFalse(testunit.isCarryingBoulder());
		
		testunit.startCarryingLog(log);
		log.startBeingCarried(testunit);
		
		assertEquals(log.getCarrier(), testunit);
		assertTrue(testunit.isCarryingLog());
		
		testunit.stopCarryingLog();
		assertEquals(log.getCarrier(), null);
		assertFalse(testunit.isCarryingLog());
	}
	
	@Test
	public void excistsInwWorldOnCreation() throws ModelException {
		Facade facade = new Facade();
		
		int[][][] types = new int[3][3][3];
		types[1][1][1] = 1;
		types[2][2][2] = 2;
		types[1][1][2] = 3;
		//types[2][2][0] = 1;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		
		/*
		 * Boulder
		 */
		
		//TODO deze zouden moeten gaan werken als falling klopt hoop ik...
		
		assertEquals(world.getSetOfBoulders().size(), 0);
		
		Unit unit = new Unit("Emil", new int[] {1,1,1}, 50, 50, 50, 50, false, world);
		facade.addUnit(unit, world);
		assertTrue(facade.isSolidConnectedToBorder(world, 1, 1, 0));
		assertTrue(facade.isSolidConnectedToBorder(world, 1, 1, 1));
		facade.workAt(unit, 1, 1, 1);
		advanceTimeFor(facade, world, 50, 0.02);
		//There should now be a boulder at position {1,1,1}
		assertEquals(world.getSetOfBoulders().size(), 1);
		
		//System.out.println();
		
		/*
		 * Log
		 */
		
		
	}
	
	@Test
	public void excistsInwWorldOnCreationLog() throws ModelException {
		System.out.println("\n");
		System.out.println("Begin van Log");
		
		Facade facade = new Facade();
		
		int[][][] types = new int[3][3][3];
		types[1][1][1] = 2;
		types[2][2][2] = 1;
		types[1][1][2] = 3;
		types[2][2][0] = 1;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
	
		Unit unit = new Unit("Emil", new int[] {1,1,1}, 50, 50, 50, 50, false, world);
		facade.addUnit(unit, world);
		assertTrue(facade.isSolidConnectedToBorder(world, 1, 1, 0));
		assertTrue(facade.isSolidConnectedToBorder(world, 1, 1, 1));
		facade.workAt(unit, 1, 1, 1);
		advanceTimeFor(facade, world, 50, 0.02);
		//There should now be a boulder at position {1,1,1}
		assertEquals(world.getSetOfLogs().size(), 1);
		
	}
	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(IFacade facade, World world, double time, double step) throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			facade.advanceTime(world, step);
		facade.advanceTime(world, time - n * step);
	}
}
