package hillbillies.model.programs.expression;

import hillbillies.model.Unit;

public abstract class BooleanExpression extends Expression<TypeBool>{

	public abstract TypeBool evaluate(Unit unit);
}
