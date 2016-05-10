package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class NotExpression extends BooleanExpression{
	
	private BooleanExpression exp;
	
	public NotExpression(BooleanExpression e) {
		this.exp = e;
	}
	
	public BooleanExpression getExpression() {
		return exp;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		return new TypeBool(! ((boolean) getExpression().evaluate(unit).getType()));
	}

	public String toString() {
		return "Not " + getExpression().toString(); 
	}
	
}
