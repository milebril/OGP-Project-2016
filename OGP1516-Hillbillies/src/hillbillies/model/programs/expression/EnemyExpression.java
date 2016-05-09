package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public class EnemyExpression extends UnitExpression{
	
	public EnemyExpression() {
		
	}
	
	@Override
	public TypeUnit evaluate(Unit unit) {
		Unit closest = null;
		
		for (Unit u : unit.getWorld().getSetOfUnits()) {
			if (!u .equals(unit) && u.getFaction() != unit.getFaction()) {
				if (closest == null) {
					closest = u;
				} else if (Unit.getDistance(unit.getPosition(), u.getPosition()) <
							Unit.getDistance(unit.getPosition(), closest.getPosition())) {
					closest = u;
				}
			}
		}
		
		return new TypeUnit(null);
	}
	
}
