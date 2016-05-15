package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;

public class HereExpression extends PositionExpression {

	public HereExpression() {
		
	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		return new TypePosition(unit.getPosition());
	}
	
	public String toString() {
		return "Here.";
	}

}
