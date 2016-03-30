package hillbillies.model;

import hillbillies.part1.facade.Facade;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;
import static org.junit.Assert.*;
import org.junit.*;

public class unitTestCases {
	
	//TODO tests atacking op timing and attackers
	//TODO movement tests
	
	public Unit testunit, testunit2;
	
	@Before
	public void setUp() throws IllegalArgumentException, ModelException {
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
	public void isValidPosition_LegalCase() {
		assertTrue(Unit.isValidPosition(new double[] {2.5, 3.5, 5.5}));
	}
	
	@Test
	public void isValidPosition_IllegalCase() {
		assertFalse(Unit.isValidPosition(new double[] {-2.5, 3.5, 5.5}));
		assertFalse(Unit.isValidPosition(new double[] {2.5, 3.5, 50.5}));
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
		testunit.setUnitPosition(new double[] {0.0, 0.0, 0.0});
		Facade ea = new Facade();
		//testunit.startWalking(1, 1, 1);
		ea.moveToAdjacent(testunit, 0, 1, 0);
		//testunit.startPathfinding(new int[] {1, 1, 1});
		System.out.println(testunit.getPosition()[0]);
		assertEquals(1.0, testunit.getPosition()[0], Util.DEFAULT_EPSILON);
		assertEquals(1.0, testunit.getPosition()[1], Util.DEFAULT_EPSILON);
		assertEquals(1.0, testunit.getPosition()[2], Util.DEFAULT_EPSILON);
	}
	
	/*
	 * Fighting
	 */
	
	@Test
	public void fighting() {
		testunit.startAttacking(testunit2);
		System.out.println(testunit2.getHitpoints());
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
	 * factions
	 */
	
	@Test
	public void addUnitToFaction() throws IllegalArgumentException, ModelException {
		int[] pos = {0,0,0};
		Unit alive = new Unit("Emil", pos, 50, 50, 50, 50, false);
		Faction f = new Faction();
		
		f.addUnitToFaction(alive);
		assertEquals(1, f.unitsInFaction.size());
	}
}
