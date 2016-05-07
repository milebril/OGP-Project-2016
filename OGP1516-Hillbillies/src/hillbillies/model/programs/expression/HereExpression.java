package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeUnit;

public class HereExpression extends UnitExpression{

	public HereExpression() {
		
	}
	
	@Override
	public TypeUnit evaluate(Unit unit) {
		return new TypeUnit(unit.getPosition()); //TODO soort ven positie expressie maken
	
}
