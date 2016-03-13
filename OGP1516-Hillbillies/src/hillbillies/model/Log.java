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
		//TODO placed on passable cube?
		//TODO add to array list
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
	public boolean canHaveAsWeight(int weight) {
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
	public static boolean isValidPosition(double[] position) {
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
	 * cast an array of integers in an array of doubles
	 * @param arrayInInt
	 * @return arrayInDouble
	 */
	private double[] castIntToDouble (int[] arrayInInt){
		double[] arrayInDouble = {(double) arrayInInt[0],(double) arrayInInt[1],(double) arrayInInt[2]};
		return arrayInDouble;
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
	 * 
	 */
	public void advanceTime(double dt){
		//TODO advance time
	}
	
////////////////////////////////////////////Carried////////////////////////////////////////////
	
	/**
	 * Make a log start being carried.
	 */
	public void startBeingCarried() throws IllegalStateException{
		if (this.isCarried == true)
			throw new IllegalStateException();
		this.isCarried = true;
	}
	
	/**
	 * Make a log stop being carried.
	 */
	public void stopBeingCarried(){
		this.isCarried = false;
	}
	
	/**
	 * Boolean registering if a log is carried or not.
	 */
	private boolean isCarried = false; 
	
	//TODO add weight to unit
	//TODO move boulder witch same vector as unit
	
////////////////////////////////////////////Falling////////////////////////////////////////////	
	
	//TODO faling
	
////////////////////////////////////////////Dropped////////////////////////////////////////////	
	
	//TODO being dropped
	
}
