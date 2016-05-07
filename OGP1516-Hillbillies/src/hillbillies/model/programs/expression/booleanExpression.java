package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public abstract class BooleanExpression extends Expression<TypeBool>{

	public abstract TypeBool evaluate(Unit unit);
		
}
