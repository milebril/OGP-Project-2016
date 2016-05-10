package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.item.Log;
import hillbillies.model.programs.expression.type.TypePosition;

public class LogPositionExpression extends PositionExpression{
	
	public LogPositionExpression() {

	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		Log closest = null;
		for (Log l : unit.getWorld().getSetOfLogs()) {
			if (closest == null) {
				closest = l;
			} else {
				if (Unit.getDistance(unit.getPosition(), l.getPosition()) < Unit.getDistance(unit.getPosition(), closest.getPosition())) {
					closest = l;
				}
			}
		}
		
		if ( closest == null) {
			return null;
		}
		
		return new TypePosition(closest.getPosition());
	}
	
	public String toString() {
		return "Log"; 
	}
}
