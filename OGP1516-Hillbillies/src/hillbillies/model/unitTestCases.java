package hillbillies.model;

import hillbillies.part2.facade.Facade;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.part3.facade.IFacade;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static org.junit.Assert.*;
import org.junit.*;

public class unitTestCases {
	
	//TODO tests atacking op timing and attackers ->Zie hun tests
	//TODO movement tests
	
	public Unit testunit, testunit2;
	public World testworld;
	public Facade facade;
	
	@Before
	public void setUp() throws IllegalArgumentException, ModelException {
		facade = new Facade();
		
		TerrainChangeListener modelListener = new TerrainChangeListener() {
			@Override
			public void notifyTerrainChanged(int x, int y, int z) {				
			}
		};
		testworld = new World(new int [50][50][50], modelListener);
		
		
		int[] pos = {0,0,0};
		testunit = new Unit("Emil", pos, 50, 50, 50, 50, false);
		testunit.putUnitInCenter(testunit.castIntToDouble(pos));
		testunit2 = new Unit("Emil", pos, 50, 50, 50, 50, false);
		testunit2.putUnitInCenter(testunit.castIntToDouble(pos));
	}
	/*
	 *  Name
	 */
	
	@Test
	public void isValidName_TrueCase() {
		assertTrue(Unit.isValidName("Emil"));
	}
	
	@Test
	public void isValidName_FalseCase() {
		assertFalse(Unit.isValidName("E"));
		assertFalse(Unit.isValidName("emil"));
		assertFalse(Unit.isValidName("Emil!"));
	}
	
	@Test
	public void setName_LegalCase() throws IllegalNameException {
		testunit.setName("Emil O'trol");
		assertEquals("Emil O'trol", testunit.getName());
	}
	
	/*
	 * Weight
	 *
	@Test
	public void isValidWeight_TrueCase() {
		assertTrue(Unit.isValidWeight(75));
	}
	
	@Test
	public void isValidWeight_FalseCase() {
		assertFalse(Unit.isValidWeight(-5));
		assertFalse(Unit.isValidWeight(250));
	}
	
	@Test
	public void setWeight_LegalCase() {
		testunit.setWeight(89);
		assertEquals(89, testunit.getWeight());
	}
	*/
	
	/*
	 * Strenght
	 */
	
	@Test
	public void isValidStrength_TrueCase() {
		assertTrue(Unit.isValidStrength(88));
	}
	
	@Test
	public void isValidStrength_FalseCase() {
		assertFalse(Unit.isValidStrength(0));
		assertFalse(Unit.isValidStrength(205));
	}
	
	@Test
	public void setStrength_LegalCase() {
		testunit.setStrength(3);
		assertEquals(3, testunit.getStrength());
	}
	
	/*
	 * Agility
	 */
	
	@Test
	public void isValidAgility_TrueCase() {
		assertTrue(Unit.isValidAgility(156));
	}
	
	@Test
	public void isValidAgility_FalseCase() {
		assertFalse(Unit.isValidAgility(-50));
		assertFalse(Unit.isValidAgility(300));
	}
	
	@Test
	public void setAgility_LegalCase() {
		testunit.setAgility(25);
		assertEquals(25, testunit.getAgility());
	}
	
	/*
	 * Toughness
	 */
	
	@Test
	public void isValidToughness_TrueCase() {
		assertTrue(Unit.isValidToughness(60));
	}
	
	@Test
	public void isValidToughness_FalseCase() {
		assertFalse(Unit.isValidToughness(-8));
		assertFalse(Unit.isValidToughness(201));
	}
	
	@Test
	public void setToughness_LegalCase() {
		testunit.setToughness(105);
		assertEquals(105, testunit.getToughness());
	}
	
	/*
	 * Orientation
	 */
	
	/*
	 * Hitpoints
	 */
	
	@SuppressWarnings("static-access")
	@Test
	public void iValidHitpoints_TrueCase() {
		assertTrue(testunit2.isValidHitpoints(49));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void isValidHitpoints_FalseCase() {
		assertFalse(testunit2.isValidHitpoints(-5));
	}
	
	@Test
	public void increaseHitpoints_LegalCase() {
		testunit2.setHitpoints(25);
		testunit2.increaseHitpoints(30);
		assertEquals(50, testunit2.getHitpoints());
		
		testunit2.setHitpoints(25);
		testunit2.increaseHitpoints(5);
		assertEquals(30, testunit2.getHitpoints());
	}
	
	@Test
	public void decreaseHitpoints_LegalCase() {
		testunit2.setHitpoints(25);
		testunit2.decreaseHitpoints(30);
		assertEquals(0, testunit2.getHitpoints());
		
		testunit2.setHitpoints(25);
		testunit2.decreaseHitpoints(5);
		assertEquals(20, testunit2.getHitpoints());
	}
	/*
	 * Stamina
	 */
	
	@SuppressWarnings("static-access")
	@Test
	public void iValidStamina_TrueCase() {
		assertTrue(testunit2.isValidStamina(49));
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void isValidHitpointStamina_FalseCase() {
		assertFalse(testunit2.isValidStamina(-5));
	}
	
	@Test
	public void increaseStamina_LegalCase() {
		testunit2.setStamina(25);
		testunit2.increaseStamina(30);
		
		assertEquals(50, testunit2.getStamina());
		
		testunit2.setStamina(25);
		testunit2.increaseStamina(5);
		assertEquals(30, testunit2.getStamina());
	}
	
	@Test
	public void decreaseStamina_LegalCase() {
		testunit2.setStamina(25);
		testunit2.decreaseStamina(30);
		assertEquals(0, testunit2.getStamina());
		
		testunit2.setStamina(25);
		testunit2.decreaseStamina(5);
		assertEquals(20, testunit2.getStamina());
	}
	
	/*
	 * Position
	 */
	
	@Test
	public void isValidPosition_LegalCase() throws ModelException {
		
		facade.addUnit(testunit, testworld);
		assertTrue(testunit.isValidPosition(new double[] {2.5, 3.5, 5.5})); //TODO addUnit + facade in construcot
	}
	
	@Test
	public void isValidPosition_IllegalCase() {
		assertFalse(testunit.isValidPosition(new double[] {-2.5, 3.5, 5.5}));
		assertFalse(testunit.isValidPosition(new double[] {2.5, 3.5, 50.5}));
	}
	
	@Test
	public void setUnitPosition_LegalCase(){
		testunit.setUnitPosition(new double[] {35.5, 10.5, 0.5});
		assertEquals(35.5, testunit.getPosition()[0], Util.DEFAULT_EPSILON);
		assertEquals(10.5, testunit.getPosition()[1], Util.DEFAULT_EPSILON);
		assertEquals(0.5, testunit.getPosition()[2], Util.DEFAULT_EPSILON);
	}
	
	/*
	 * Movement
	 */
	
	@Test
	public void correctPathfindeing() throws ModelException{
	}
	
	/*
	 * Fighting
	 */
	
	@Test
	public void fighting() {
		//testunit.startAttacking(testunit2);
		//System.out.println(testunit2.getHitpoints());
	}
	
	/*
	 * Death
	 */
	
	@Test
	public void unitDies_ValidCase() {
		int hp = testunit2.getHitpoints();
		testunit2.decreaseHitpoints(hp);
		assertFalse(testunit2.isUnitAlive());
	}
	
	@Test
	public void newUnitLives_TrueCase() throws IllegalArgumentException, ModelException {
		int[] pos = {0,0,0};
		Unit alive = new Unit("Emil", pos, 50, 50, 50, 50, false);
		testunit2.putUnitInCenter(testunit.castIntToDouble(pos));
		
		assertTrue(alive.isUnitAlive());
	}
	
	/*
	 * Experience
	 */
	
	@Test
	public void unitLevels() throws IllegalArgumentException, ModelException  {
		int[] pos = {0,0,0};
		Unit test = new Unit("Emil", pos, 50, 50, 50, 50, false);
		
		Unit test2 = new Unit("Emil", pos, 50, 50, 50, 50, false);
		
		test.setExperience(50);
		
		assertFalse(test.equals(test2));
		
		
	}
	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(IFacade facade2, World world, double time, double step) throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			facade2.advanceTime(world, step);
		facade2.advanceTime(world, time - n * step);
	}
}
