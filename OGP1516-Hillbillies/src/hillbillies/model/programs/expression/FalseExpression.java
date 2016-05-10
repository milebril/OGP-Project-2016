package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class FalseExpression extends BooleanExpression{

	public FalseExpression() {

	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		return new TypeBool(false);
	}
	
	public String toString() {
		return "False.";
	}
}
