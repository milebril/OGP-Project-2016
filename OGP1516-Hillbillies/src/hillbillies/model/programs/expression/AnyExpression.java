package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public class AnyExpression extends UnitExpression{

	public AnyExpression() {
		
	}
	
	@Override
	public TypeUnit evaluate(Unit unit) {
		Unit closest = null;
		
		for (Unit temp : unit.getWorld().getSetOfUnits()) {
			if (closest == null) {
				closest = temp;
			} else if (Unit.getDistance(unit.getPosition(), temp.getPosition()) <
						Unit.getDistance(unit.getPosition(), closest.getPosition())) {
				closest = temp;
			}
		}
		
		if (closest == null) {
			return new TypeUnit(null);
		}
		
		return new TypeUnit(closest);
		
	}

}
