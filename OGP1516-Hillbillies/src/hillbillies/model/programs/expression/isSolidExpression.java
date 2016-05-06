package hillbillies.model.programs.expression;

import hillbillies.model.Unit;

public class IsSolidExpression extends BooleanExpression{
	
	private UnitExpression expression;
	
	public IsSolidExpression(UnitExpression exp) {
		expression = exp;
	}

	public UnitExpression getExpression() {
		return expression;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		double[] position = ((Unit) getExpression().evaluate(unit).getType()).getPosition();
		int[] intPosition = {(int) position[0], (int) position[1], (int) position[2]};
		return new TypeBool(! (unit.getWorld().isCubePassable(intPosition[0], intPosition[1], intPosition[2])));
	}
}
