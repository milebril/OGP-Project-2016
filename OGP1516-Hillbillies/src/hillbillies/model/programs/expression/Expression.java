package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.ExprType;

public abstract class Expression<T extends ExprType>{
	
	public abstract T evaluate(Unit unit);
	
}
