package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;

public abstract class PositionExpression extends Expression<TypePosition>{
	
	public abstract TypePosition evaluate(Unit unit);

}
