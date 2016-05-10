package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;

public class PositionOfExpression extends PositionExpression{

	private UnitExpression exp;
	
	public PositionOfExpression(UnitExpression e) {
		this.exp = e;
	}
	
	public UnitExpression getExpression() {
		return exp;
	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		return new TypePosition(((Unit) getExpression().evaluate(unit).getType()).getPosition());
	}

	public String toString() {
		return "Position of unit";
	}
	
}
