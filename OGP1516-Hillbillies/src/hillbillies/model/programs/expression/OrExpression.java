package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class OrExpression extends BooleanExpression{
	private BooleanExpression left;
	private BooleanExpression right;
	
	public OrExpression(BooleanExpression left, BooleanExpression right) {
		this.left = left;
		this.right = right;
	}
	
	public BooleanExpression getLeftExpression() {
		return left;
	}
	
	public BooleanExpression getRightExpression() {
		return right;
	}

	@Override
	public TypeBool evaluate(Unit unit) {
		return new TypeBool((boolean) getLeftExpression().evaluate(unit).getType() ||
				(boolean) getRightExpression().evaluate(unit).getType());
	}
	
	public String toString() {
		return getLeftExpression().toString() + " or " + getRightExpression().toString();
	}
}
