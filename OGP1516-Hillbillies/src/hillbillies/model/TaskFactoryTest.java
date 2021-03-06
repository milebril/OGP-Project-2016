package hillbillies.model;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.facade.IFacade;
import hillbillies.part3.programs.TaskParser;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

public class TaskFactoryTest {

	private Facade facade;

	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;

	@Before
	public void setup() {
		this.facade = new Facade();
	}
	
	@Test
	public void executeWorkTask() throws ModelException {
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 1, 1, 1}, 50, 50, 50, 50, false);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work here;", facade.createTaskFactory(), null);
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		// test name
		assertEquals("work task", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));

		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world, 50, 0.02);
		
		System.out.println(world.getCubeType(1, 1, 1));
		// work task has been executed
		assertEquals(TYPE_AIR, facade.getCubeType(world, 1, 1, 1));
		// work task is removed from scheduler
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
	}
	
	@Test
	public void executeMoveToTask() throws ModelException {
		int[][][] types = new int[3][3][3];
		
		for (int x = 0; x < types.length; x++) {
			for (int y = 0; y < types[0].length; y++) {
				for (int z = 0; z < types[0][0].length; z++) {
					if (z == 0) {
						types[x][y][z]= TYPE_ROCK;
					} else {
						types[x][y][z]= TYPE_AIR;
					}
				}
			}
		}

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 1}, 50, 50, 50, 50, false);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"walk task\"\npriority: 1\nactivities: moveTo (2, 2, 1);", facade.createTaskFactory(), null);
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		// test name
		assertEquals("walk task", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));

		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world, 50, 0.02);
		
		System.out.println(world.getCubeType(1, 1, 1));
		// walk task has been executed
		assertEquals(2.5, unit.getPosition()[0], Util.DEFAULT_EPSILON);
		assertEquals(2.5, unit.getPosition()[1], Util.DEFAULT_EPSILON);
		assertEquals(1.5, unit.getPosition()[2], Util.DEFAULT_EPSILON);
		// walk task is removed from scheduler
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
	}
	
	@Test
	public void executePrintTask() throws ModelException {
		int[][][] types = new int[3][3][3];
		
		World world = facade.createWorld(types , new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 1}, 50, 50, 50, 50, false);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);
		
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"print task\"\npriority: 1\nactivities: print (2, 2, 1);", facade.createTaskFactory(), null);
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		// test name
		assertEquals("print task", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));

		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world, 50, 0.02);
	}
	
	@Test
	public void executeAttackTask() throws ModelException {
		int[][][] types = new int[3][3][3];
		
		World world = facade.createWorld(types , new DefaultTerrainChangeListener());
		Unit attacker = facade.createUnit("Test", new int[] { 0, 0, 0}, 50, 50, 50, 50, false);
		facade.addUnit(attacker, world);
		Unit defender = facade.createUnit("Test", new int[] { 0, 1, 0}, 50, 50, 50, 50, false);
		facade.addUnit(defender, world);
		
		int hitpoints = defender.getHitpoints();
		System.out.println(defender.getHitpoints());
		System.out.println(defender.getPosition()[0] + " " + defender.getPosition()[1]);
		
		Faction faction = facade.getFaction(attacker);

		Scheduler scheduler = facade.getScheduler(faction);
		
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"print task\"\npriority: 1\nactivities: attack enemy;", facade.createTaskFactory(), null);
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		// test name
		assertEquals("print task", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));

		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world, 50, 0.02);
		
		System.out.println(defender.getHitpoints());
		System.out.println(defender.getPosition()[0] + " " + defender.getPosition()[1]);
		
		assertFalse(hitpoints == defender.getHitpoints());
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
