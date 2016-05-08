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
		
		if (f.getAmountOfUnitsInFaction() >= 2) {
			for (Unit temp : f.getUnitsInFaction()) {
				if (! temp.equals(unit)) {
					return new TypeUnit(temp);
				}
			}
		}
		
		return new TypeUnit(null);
	}

}
