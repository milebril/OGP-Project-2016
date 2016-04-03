package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import java.util.Random;

/**
 * A class that deals with logs and all the properties of the logs
 * in a given game world.
 * 
 * @invar  Each log can have its weight as weight.
 *       | canHaveAsWeight(this.getWeight())
 * @invar  The position of each log must be a valid position for any
 *         log.
 *       | isValidPosition(getPosition())
 * @author Emil Peters
 * @author Sjaan Vandebeek
 */
public class Log {
	
////////////////////////////////////////////Constructor////////////////////////////////////////////	
	/**
	 * Initialize this new log with given weight.
	 * 
	 * @param  Weight
	 *         The weight for this new log.
	 * @post   The weight of this new log is equal to the given
	 *         weight.
	 *       | new.getWeight() == Weight
	 * @throws IllegalArgumentException
	 *         This new log cannot have the given weight as its weight.
	 *       | ! canHaveAsWeight(this.getWeight())
	 *       
	 * Initialize this new log with given position.
	 *
	 * @param  position
	 *         The position for this new log.
	 * @effect The position of this new log is set to
	 *         the given position.
	 *       | this.setPosition(position)
	 */
	public Log(double[] position) throws IllegalArgumentException {
		Random random = new Random();
		int weight = random.nextInt(40) + 10;
		if (! canHaveAsWeight(weight))
			throw new IllegalArgumentException();
		this.weight =  weight;
	}
 
////////////////////////////////////////////Weight////////////////////////////////////////////
	
	/**
	 * Return the weight of this log.
	 */
	@Basic @Raw @Immutable
	public int getWeight() {
		return this.weight;
	}
	
	/**
	 * Check whether this log can have the given weight as its weight.
	 *  
	 * @param  Weight
	 *         The weight to check.
	 * @return if the weight of the log lays between the minimum and the maximum
	 * 		   weight return true.  
	*/
	@Raw
	private boolean canHaveAsWeight(int weight) {
		if( getMinWeight() <= weight && weight <= getMaxWeight())
			return true;
		return false;
	}
	
	/**
	 * Return the highest weight for all logs
	 */
	private static int getMaxWeight(){
		return 50;
	}
	
	/**
	 * Return the lowest weight for all logs
	 */
	private static int getMinWeight(){
		return 10;
	}
		
	/**
	 * Variable registering the weight of this log.
	 */
	private final int weight;
	
////////////////////////////////////////////Position////////////////////////////////////////////	
	
	/**
	 * Return the position of this log.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any log.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return if all the three elements of the list lays between the minimum
	 * 		   and maximum value for a coordinate return true.
	 *       
	*/
	public boolean isValidPosition(double[] position) {
		if (position.length > 3)
			return false;
		for (int i=0; i < position.length; i++) {
			if (position[i] < getMinValueCoordinate() || position[i] > getMaxValueCoordinate())
				return false;
		}
		return true;
	}
	
	/**
	 * Set the position of this log to the given position.
	 * 
	 * @param  position
	 *         The new position for this log.
	 * @post   The position of this new log is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         log.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw
	public void setPosition(double[] position) throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
		
	/**
	 * cast an array of doubles in an array of integers
	 * @param arrayInDouble
	 * @return arrayInInt
	 */
	private int[] castDoubleToInt (double[] arrayInDoubles){
		int[] arrayInInt = {(int) arrayInDoubles[0],(int) arrayInDoubles[1],(int) arrayInDoubles[2]};
		return arrayInInt;
	}
	
	/**
	 * Return the value of the lowest coordinate value.
	 */
	private static int getMinValueCoordinate(){
		return 0;
	}
	
	/**
	 * Return the value of the lowest coordinate value.
	 */
	private static int getMaxValueCoordinate(){
		return 49;
	}
	
	/**
	 * Variable registering the position of this log.
	 */
	private double[] position;
////////////////////////////////////////////Advance Time////////////////////////////////////////////	
	
	/**
	 * advance the time over the time period dt.
	 * @post the position of the log is updated.
	 */
	public void advanceTimeOfLog(double dt, int[][][] terrainTypes){
		if (this.isCarried == true){
			this.position = this.unitCarryingLog.getPosition();
		}
		if ( !this.isCarried && this.canFall(terrainTypes)){
			this.fall(dt, terrainTypes);
		}
	}

////////////////////////////////////////////Carried////////////////////////////////////////////
	
	/**
	 * Make a log start being carried.
	 */
	public void startBeingCarried(Unit unit) throws IllegalStateException{
		if (this.isCarried == true || this.unitCarryingLog != null)
			throw new IllegalStateException();
		this.isCarried = true;
		this.unitCarryingLog = unit;
		unit.setWeight(unit.getWeight() + this.getWeight());
	}
	
	/**
	 * Make a log stop being carried.
	 */
	public void stopBeingCarried(){
		this.isCarried = false;
		this.unitCarryingLog.setWeight(this.unitCarryingLog.getWeight() - this.getWeight());
		this.unitCarryingLog = null;
	}
	
	/**
	 * return the carrier of this log.
	 * @return the carrier if the log is carried or null when there is no carrier.
	 */
	public Unit getCarrier(){
		return this.unitCarryingLog;
	}
	
	/**
	 * variable registering which unit is carrying the log.
	 */
	public Unit unitCarryingLog = null;
	
	/**
	 * Boolean registering if a log is carried or not.
	 */
	private boolean isCarried = false; 

	
////////////////////////////////////////////Falling////////////////////////////////////////////	
	/**
	 * check if a log can fall.
	 */
	private boolean canFall(int[][][] terrainTypes){
		int[] position = castDoubleToInt(this.getPosition());
		if(position[2] == 0) return false;
		if(terrainTypes[position[0]][position[1]][position[2]-1] == 0) return true;
		return false;
	}
	
	/**
	 * return the speed of falling in m/s.
	 */
	private static int speedOfFalling() {
		return 3;
	}
	
	/**
	 * move the log over a given time period dt.
	 */
	private void fall(double dt, int[][][] terrainTypes) {
		double[] positionInInt = this.getPosition();
		double distanceToFall = speedOfFalling() * dt;
		if(distanceToFall < 1)
			positionInInt[2] -= distanceToFall;
		else {
			int[] position = castDoubleToInt(this.getPosition());
			int a = 0;
			for( int i = 0; i < distanceToFall; i++){
				if(terrainTypes[position[0]][position[1]][position[2]- i] == 0)
					a++;
			}
			distanceToFall = a + distanceToFall % (int)distanceToFall;
			positionInInt[2] -= distanceToFall;
		}
		this.setPosition(positionInInt);
	}
}
