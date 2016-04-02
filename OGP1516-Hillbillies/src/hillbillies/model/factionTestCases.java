package hillbillies.model;

import static org.junit.Assert.*;

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
				// TODO Auto-generated method stub
				
			}
		};
		testWorld = new World(new int [5][5][5], modelListener);
	}

	/*
	 * factions
	 */
	
	@Test
	public void add_removeUnitToFaction() throws IllegalArgumentException, ModelException {
		int[] pos = {0,0,0};
		Unit alive = new Unit("Emil", pos, 50, 50, 50, 50, false);
		Faction f = new Faction();
		
		f.addUnitToFaction(alive);
		assertEquals(1, f.getUnitsInFaction().size());
		
		f.removeUnitFromFaction(alive);
		assertEquals(1, f.getUnitsInFaction().size());
	}

}
