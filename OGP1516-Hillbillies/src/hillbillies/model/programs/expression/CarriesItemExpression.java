package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class CarriesItemExpression extends BooleanExpression {

	private UnitExpression unitExp;
	
	public CarriesItemExpression(UnitExpression unitExp) {
		this.unitExp = unitExp;
	}
	
	public UnitExpression getExpression() {
		return unitExp;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		return new TypeBool(((Unit) unitExp.evaluate(unit).getType()).isCarryingItem());
	}

	public String toString() {
		return "carries Item";
	}
}
