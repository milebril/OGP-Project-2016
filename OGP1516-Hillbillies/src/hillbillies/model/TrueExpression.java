package hillbillies.model;

import hillbillies.model.programs.expression.BooleanExpression;
import hillbillies.model.programs.expression.type.TypeBool;

public class TrueExpression extends BooleanExpression{

	public TrueExpression() {
		
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		return new TypeBool(true);
	}

}
