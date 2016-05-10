package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public class ThisExpression extends UnitExpression{
	
	public ThisExpression() {
		
	}
	
	@Override
	public TypeUnit evaluate(Unit unit) {
		return new TypeUnit(unit);
	}
	
	public String toString() {
		return "This";
	}
	
}
