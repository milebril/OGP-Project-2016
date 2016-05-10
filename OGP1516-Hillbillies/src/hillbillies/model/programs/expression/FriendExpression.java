package hillbillies.model.programs.expression;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public class FriendExpression extends UnitExpression{

	public FriendExpression() {
		
	}
	
	@Override
	public TypeUnit evaluate(Unit unit) {
		Faction f = unit.getFaction();
		Unit closest = null;
		
		if (f.getAmountOfUnitsInFaction() >= 2) {
			for (Unit temp : f.getUnitsInFaction()) {
				if (! temp.equals(unit)) {
					if (closest == null) {
						closest = temp;
					} else if (Unit.getDistance(unit.getPosition(), temp.getPosition()) <
								Unit.getDistance(unit.getPosition(), closest.getPosition())) {
						closest = temp;
					}
				}
			}
		}
		
		return new TypeUnit(null);
	}
	
	public String toString() {
		return "Friend.";
	}
}
