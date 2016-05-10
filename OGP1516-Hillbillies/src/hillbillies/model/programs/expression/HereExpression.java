package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;
import hillbillies.model.programs.expression.type.TypeUnit;

public class HereExpression extends PositionExpression {

	public HereExpression() {
		
	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		return new TypePosition(unit.getPosition()); //TODO soort ven positie expressie maken, heb geen idee of dit werkt!!!!!
	}
	
	public String toString() {
		return "Here.";
	}
	
}
