package hillbillies.model.item;

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
public class Log extends Item{

	public Log(double[] position) throws IllegalArgumentException {
		super(position);
	}
	
}
