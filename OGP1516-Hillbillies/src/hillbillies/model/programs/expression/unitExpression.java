package hillbillies.model.programs.expression;

import hillbillies.model.Unit;

public abstract class UnitExpression extends Expression<TypeUnit>{
	
	public abstract TypeUnit evaluate(Unit unit);
}
