package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling illegal names for units 
 *
 */
public class IllegalNameException extends RuntimeException{
	/**
	 * Var registering the string involved in this illegal name exception
	 */
	private final String name;
	
	/**
	 * 
	 * @param	name 
	 * 		  	The string of the new illegal name exception
	 * @post 	The string of the new illegal name exception is set to the given string.
	 * 			| new.getName() == name
	 */
	public IllegalNameException(String name) {
		this.name = name;
	}
	
	/**
	 * Return the string registered for the illegal name exception 
	 */
	@Basic @Immutable
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2008524175821070107L;
}
