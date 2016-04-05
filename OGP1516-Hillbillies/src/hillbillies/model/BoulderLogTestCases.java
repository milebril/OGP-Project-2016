package hillbillies.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.part2.listener.TerrainChangeListener;

public class BoulderLogTestCases {

	public Log log;
	public Boulder boulder;
	public World testWorld;
	
	@Before
	public void setUp() throws Exception {
		TerrainChangeListener modelListener = new TerrainChangeListener() {
			
			@Override
			public void notifyTerrainChanged(int x, int y, int z) {
				// TODO Auto-generated method stub
			}
		};
		testWorld = new World(new int [50][50][50], modelListener);
		 log = new Log( new double[] {5,5,5});
		 boulder = new Boulder( new double[] {4,4,4});
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
		//TODO
	}
	
	@Test
	public void advanceTime() {
		//TODO
	}
}
