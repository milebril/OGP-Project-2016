package hillbillies.model.item;

import be.kuleuven.cs.som.annotate.*;

import java.io.ObjectInputStream.GetField;
import java.util.Random;
/**
 * A class that deals with a boulder and all the properties of the boulder 
 * in a given game world.
 * 	
 * @author Emil Peters
 * @author Sjaan Vandebeek
 *
 */
public class Boulder extends Item {

	public Boulder(double[] position) throws IllegalArgumentException {
		super(position);
	}

}
