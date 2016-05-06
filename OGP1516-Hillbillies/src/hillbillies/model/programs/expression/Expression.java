package hillbillies.model.programs.expression;

import hillbillies.model.Unit;

public abstract class Expression<T extends ExprType>{
	
	public abstract T evaluate(Unit unit);
	
}
