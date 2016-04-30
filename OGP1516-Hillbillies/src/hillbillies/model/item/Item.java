package hillbillies.model.item;

	import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Unit;

import java.io.ObjectInputStream.GetField;
	import java.util.Random;
	/**
	 * A class that deals with a boulder and all the properties of the boulder 
	 * in a given game world.
	 * 
	 * @invar  Each Boulder can have its weight as weight.
	 *       | canHaveAsWeight(this.getWeight())
	 * @invar Each boulder should be above a solid terrainType or should be falling.
	 * 	
	 * @author Emil Peters
	 * @author Sjaan Vandebeek
	 *
	 */
	public class Item {

	////////////////////////////////////////////Constructor////////////////////////////////////////////
		/**
		 * Initialize this new Boulder with given weight.
		 * 
		 * @param  Weight
		 *         The weight for this new Boulder.
		 * @post   The weight of this new Boulder is equal to the given
		 *         weight.
		 *       | new.getWeight() == Weight
		 * @throws ExceptionName_Java
		 *         This new Boulder cannot have the given weight as its weight.
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
		public Item(double[] position) throws IllegalArgumentException {
			Random random = new Random();
			int weight = random.nextInt(40) + 10;
			if (! canHaveAsWeight(weight))
				throw new IllegalArgumentException();
			this.weight =  weight;
			
			this.setPosition(position);
		}
	 
	////////////////////////////////////////////Weight////////////////////////////////////////////

		/**
		 * Return the weight of this Boulder.
		 */
		@Basic @Raw @Immutable
		public int getWeight() {
			return this.weight;
		}
		
		/**
		 * Check whether this Boulder can have the given weight as its weight.
		 *  
		 * @param  Weight
		 *         The weight to check.
		 * @return 
		 *       
		*/
		@Raw
		private boolean canHaveAsWeight(int weight) {
			if( getMinWeight() <= weight && weight <= getMaxWeight())
				return true;
			return false;
		}
		
		/**
		 * Return the highest weight for all boulders
		 */
		private static int getMaxWeight(){
			return 50;
		}
		
		/**
		 * Return the lowest weight for all boulders
		 */
		private static int getMinWeight(){
			return 10;
		}
			
		/**
		 * Variable registering the weight of this Boulder.
		 */
		private final int weight;

	////////////////////////////////////////////Position////////////////////////////////////////////	

		/**
		 * Return the position of this boulder.
		 */
		@Basic @Raw
		public double[] getPosition() {
			return this.position;
		}
		
		/**
		 * Check whether the given position is a valid position for
		 * any boulder.
		 *  
		 * @param  position
		 *         The position to check.
		 * @return if all the three elements of the list lays between the minimum
		 * 		   and maximum value for a coordinate return true. 
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
		 * Set the position of this boulder to the given position.
		 * 
		 * @param  boulder
		 *         The new position for this boulder.
		 * @post   The position of this new log is equal to
		 *         the given position.
		 *       | new.getPosition() == boulder
		 * @throws illegalArgumentException
		 *         The given position is not a valid position for any
		 *         boulder.
		 *       | ! isValidPosition(getPosition())
		 */
		@Raw
		public void setPosition(double[] position) throws IllegalArgumentException {
			if (! isValidPosition(position))
				throw new IllegalArgumentException();
			this.position = position;
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
		 * cast an array of doubles in an array of integers
		 * @param arrayInDouble
		 * @return arrayInInt
		 */
		private static int[] castDoubleToInt (double[] arrayInDoubles){
			int[] arrayInInt = {(int) arrayInDoubles[0],(int) arrayInDoubles[1],(int) arrayInDoubles[2]};
			return arrayInInt;
		}
		
		/**
		 * Variable registering the position of this boulder.
		 */
		private double[] position;
	////////////////////////////////////////////Advance Time////////////////////////////////////////////	
		
		/**
		 * advance the time over the time period dt.
		 * @post the position of the stone is updated.
		 */
		public void advanceTimeOfItem(double dt, int[][][] terrainTypes){
			//System.out.println(this.isCarried);
			
			if (this.isCarried == true){
				this.position = this.unitCarryingBoulder.getPosition();
			}
			if ( !this.isCarried  && this.canFall(terrainTypes)){ //TODO terug aanzetten als canFallw erkt
				this.fall(dt, terrainTypes);
			}
			
			if (!this.canFall(terrainTypes) && getPosition()[2] != 0.5) {
				this.setPosition(new double[] {getPosition()[0], getPosition()[1], (int) getPosition()[2] + 0.5});
			}
		}
	////////////////////////////////////////////Carried////////////////////////////////////////////
		
		/**
		 * Make a boulder start being carried.
		 */
		public void startBeingCarried(Unit unit) throws IllegalStateException{
			if (this.isCarried == true || this.unitCarryingBoulder != null)
				throw new IllegalStateException();
			this.isCarried = true;
			this.unitCarryingBoulder = unit;
			unit.setWeight(unit.getWeight() + this.getWeight());
			unit.startCarryingItem(this);
		}
		
		/**
		 * Make a boulder stop being carried.
		 */
		public void stopBeingCarried(){
			this.isCarried = false;
			getCarrier().setWeight(getCarrier().getWeight() - this.getWeight());
			this.unitCarryingBoulder = null;
		}
		
		/**
		 * return the carrier of this boulder.
		 * @return the carrier if the boulder is carried or null when there is no carrier.
		 */
		public Unit getCarrier(){
			return this.unitCarryingBoulder;
		}
		
		/**
		 * variable registering which unit is carrying the boulder.
		 */
		public Unit unitCarryingBoulder = null;
		
		/**
		 * Boolean registering if a boulder is carried or not.
		 */
		private boolean isCarried = false; 
			
	////////////////////////////////////////////Falling////////////////////////////////////////////	
		/**
		 * check if a boulder can fall.
		 */
		//TODO hier zit een fout dus, we moeten optijd stoppen, en zorgen dat we niet uit de wereld valln!
		private boolean canFall(int[][][] terrainTypes){
			if(isCarried) {
				return false;
			}
			
			if (this.getPosition()[2] <= 0.5) {
				return false;
			}
			
			int[] position = castDoubleToInt(this.getPosition());
			
			if (position[2] - 1 >= 0) {
				if ((terrainTypes[position[0]][position[1]][position[2]-1] == 1 ||
						terrainTypes[position[0]][position[1]][position[2]-1] == 2) &&
						getPosition()[2] % position[2] <= 0.5) {
					return false;
				}
			}

			
			return true;
		}
		
		/**
		 * return the speed of falling in m/s.
		 */
		private static int speedOfFalling() {
			return 3;
		}
		
		/**
		 * move the boulder over a given time period dt.
		 */
		private void fall(double dt, int[][][] terrainTypes) {		
			this.setPosition(new double[] {getPosition()[0], getPosition()[1], getPosition()[2] - speedOfFalling() * dt});
		}
	}
