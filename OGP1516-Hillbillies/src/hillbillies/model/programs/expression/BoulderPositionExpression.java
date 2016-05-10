package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.item.Boulder;
import hillbillies.model.programs.expression.type.TypePosition;

public class BoulderPositionExpression extends PositionExpression{

	public BoulderPositionExpression() {

	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		Boulder closest = null;
		for (Boulder b : unit.getWorld().getSetOfBoulders()) {
			if (closest == null) {
				closest = b;
			} else {
				if (Unit.getDistance(unit.getPosition(), b.getPosition()) < Unit.getDistance(unit.getPosition(), closest.getPosition())) {
					closest = b;
				}
			}
		}
		
		if ( closest == null) {
			return null;
		}
		
		return new TypePosition(closest.getPosition());
		
	}
	
	public String toString() {
		return "Boulder";
	}

}
