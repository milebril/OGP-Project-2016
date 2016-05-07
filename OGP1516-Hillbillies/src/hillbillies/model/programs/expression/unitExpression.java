package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public abstract class UnitExpression extends Expression<TypeUnit>{
	
	public abstract TypeUnit evaluate(Unit unit);
}
