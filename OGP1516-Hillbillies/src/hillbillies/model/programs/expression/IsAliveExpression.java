package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class IsAliveExpression extends BooleanExpression{
	
	private UnitExpression exp;
	
	public IsAliveExpression(UnitExpression e) {
		this.exp = e;
	}
	
	public UnitExpression getExpression() {
		return exp;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		return new TypeBool(((Unit) exp.evaluate(unit).getType()).isUnitAlive());
	}

}
