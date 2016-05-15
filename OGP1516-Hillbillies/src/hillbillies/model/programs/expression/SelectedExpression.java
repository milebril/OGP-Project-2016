package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;

public class SelectedExpression extends PositionExpression{
	
	public SelectedExpression() {
	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		return new TypePosition(null);
	}
	//TODO deze
}	
