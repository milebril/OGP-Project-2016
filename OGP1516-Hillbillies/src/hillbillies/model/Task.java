package hillbillies.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.programs.statement.Statement;

/**
 * a class that deals with all the tasks that a scheduler can consist of.
 * 
 * @author Computer Recycling
 * @invar  The priority of each task must be a valid priority for any
 *         task.
 *       | isValidPriority(getPriority())
 *       
 * @invar  Each Task can have its name as name.
 *       | canHaveAsName(this.getName())
 *       
 * @invar  The assignedUnit of each task must be a valid assignedUnit for any
 *         task.
 *       | isValidAssignedUnit(getAssignedUnit())
 *       
 * @invar  The list of schedulers of each Task must be a valid list of schedulers for any
 *         Task.
 *       | isValidListOfSchedulers(getListOfSchedulers())
 *       
 * @author Emil Peters
 * @author Sjaan Vandebeek
 */
public class Task {
	
	/**
	 * @param  priority
	 *         The priority for this new task.
	 * @pre    The given priority must be a valid priority for any task.
	 *       | isValidPriority(priority)
	 * @post   The priority of this new task is equal to the given
	 *         priority.
	 *       | new.getPriority() == priority
	 * Initialize this new Task with given name.
	 * 
	 * @param  name
	 *         The name for this new Task.
	 * @pre    This new Task can have the given name as its name.
	 *       | canHaveAsName(name)
	 * @post   The name of this new Task is equal to the given
	 *         name.
	 *       | new.getName() == name
	 *       
	 * Initialize this new task with given assignedUnit.
	 * 
	 * @param  assignedUnit
	 *         The assignedUnit for this new task.
	 * @pre    The given assignedUnit must be a valid assignedUnit for any task.
	 *       | isValidAssignedUnit(assignedUnit)
	 * @post   The assignedUnit of this new task is equal to the given
	 *         assignedUnit.
	 *       | new.getAssignedUnit() == assignedUnit
	 */
	public Task(String name, int priority, Statement activities){
		setPriority(priority);
		assert this.canHaveAsName(name);
			this.name = name;
		//TODO constructor
		this.activity = activities;
		
		this.listOfSchedulers = new HashSet<Scheduler>();
	}
	
	private Statement activity;
	
//////////////////////////////////// priority ////////////////////////////////////
	
	/**
	 * Return the priority of this task.
	 */
	@Basic @Raw
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Check whether the given priority is a valid priority for
	 * any task.
	 *  
	 * @param  priority
	 *         The priority to check.
	 * @return 
	 *       | result == true
	*/
	public static boolean isValidPriority(int priority) {
		return true;
	}

	/**
	 * Set the priority of this task to the given priority.
	 * 
	 * @param  priority
	 *         The new priority for this task.
	 * @pre    The given priority must be a valid priority for any
	 *         task.
	 *       | isValidPriority(priority)
	 * @post   The priority of this task is equal to the given
	 *         priority.
	 *       | new.getPriority() == priority
	 */
	@Raw
	public void setPriority(int priority) {
		assert isValidPriority(priority);
		this.priority = priority;
	}

	/**
	 * Variable registering the priority of this task.
	 */
	private int priority;
	
//////////////////////////////////// name ////////////////////////////////////
	/**
	 * Return the name of this Task.
	 */
	@Basic @Raw @Immutable
	public String getName() {
		return this.name;
	}
	
	/**
	 * Check whether this Task can have the given name as its name.
	 *  
	 * @param  name
	 *         The name to check.
	 * @return 
	 *       | result == 
	*/
	@Raw
	public boolean canHaveAsName(String name) {
		return false;
	}
	
	/**
	 * Variable registering the name of this Task.
	 */
	private final String name;
	
//////////////////////////////////// Unit ////////////////////////////////////		
	/**
	 * Return the assignedUnit of this task.
	 */
	@Basic @Raw
	public Unit getAssignedUnit() {
		return this.assignedUnit;
	}
	
	/**
	 * Check whether the given assignedUnit is a valid assignedUnit for
	 * any task.
	 *  
	 * @param  assignedUnit
	 *         The assignedUnit to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidAssignedUnit(Unit assignedUnit) {
		return false;
	}
	
	/**
	 * Set the assignedUnit of this task to the given assignedUnit.
	 * 
	 * @param  assignedUnit
	 *         The new assignedUnit for this task.
	 * @pre    The given assignedUnit must be a valid assignedUnit for any
	 *         task.
	 *       | isValidAssignedUnit(assignedUnit)
	 * @post   The assignedUnit of this task is equal to the given
	 *         assignedUnit.
	 *       | new.getAssignedUnit() == assignedUnit
	 */
	@Raw
	public void setAssignedUnit(Unit assignedUnit) {
		assert isValidAssignedUnit(assignedUnit);
		this.assignedUnit = assignedUnit;
	}
	
	/**
	 * Variable registering the assignedUnit of this task.
	 */
	private Unit assignedUnit;
	
//////////////////////////////////// State of performance ////////////////////////////////////
	/**
	 * Return the State Of performance of this task.
	 */
	@Basic @Raw
	public boolean getStateOfPerformance() {
		return this.StateOfPerformance;
	}

	/**
	 * Set the State Of performance of this task to the given State Of performance.
	 * 
	 * @param  StateOfperformance
	 *         The new StateOfperformance for this task.
	 * @post   The StateOfperformance of this task is equal to the given
	 *         StateOfperformance.
	 *       | new.getStateOfperformance() == StateOfperformance
	 */
	@Raw
	public void setStateOfPerformance(boolean StateOfPerformance) {
		this.StateOfPerformance = StateOfPerformance;
	}

	/**
	 * Variable registering the State Of performance of this task.
	 */
	private boolean StateOfPerformance = false;
	
//////////////////////////////////// List of Scheduler ////////////////////////////////////	
	/**
	 * Return the list of schedulers of this Task.
	 */
	@Basic @Raw
	public Set<Scheduler> getListOfSchedulers() {
		return this.listOfSchedulers;
	}
	
	/**
	 * Check whether the given list of schedulers is a valid list of schedulers for
	 * any Task.
	 *  
	 * @param  list of schedulers
	 *         The list of schedulers to check.
	 * @return 
	 *       | result == 
	*/
	public static boolean isValidListOfSchedulers(List<Task> listOfSchedulers) {
		//TODO wat is een valid list?
		return false;
	}
	
	/**
	 * Variable registering the list of schedulers of this Task.
	 */
	public Set<Scheduler> listOfSchedulers;

	
	/*
	 * Executing the Task
	 */
	
	public void execute(Unit unit) { //TODO zet de unit die deze task uitvoert
		if (activity.canExecute(unit))
			this.activity.execute(unit);
			
	}
	
}
