package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class IsFriendExpression extends BooleanExpression{
	
	private UnitExpression exp;
	
	public IsFriendExpression(UnitExpression e) {
		this.exp = e;
	}
	
	public UnitExpression getExpression() {
		return exp;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		return new TypeBool(((Unit) getExpression().evaluate(unit).getType()).getFaction() == unit.getFaction());
	}
	
	public String toString() {
		return "Is friend";
	}
}
