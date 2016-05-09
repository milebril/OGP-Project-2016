package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class IsSolidExpression extends BooleanExpression{
	
	private PositionExpression expression;
	
	public IsSolidExpression(PositionExpression exp) {
		expression = exp;
	}

	public PositionExpression getExpression() {
		return expression;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		double[] position = (double[]) getExpression().evaluate(unit).getType();
		int[] intPosition = {(int) position[0], (int) position[1], (int) position[2]};
		return new TypeBool(! (unit.getWorld().isCubePassable(intPosition[0], intPosition[1], intPosition[2])));
	}
}
