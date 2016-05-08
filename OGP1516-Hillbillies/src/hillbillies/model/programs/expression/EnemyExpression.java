package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public class EnemyExpression extends UnitExpression{
	
	public EnemyExpression() {
		
	}
	
	@Override
	public TypeUnit evaluate(Unit unit) {
		for (Unit u : unit.getWorld().getSetOfUnits()) {
			if (!u .equals(unit) && u.getFaction() != unit.getFaction()) {
				return new TypeUnit(u);
			}
		}
		
		return new TypeUnit(null);
	}
	
}
