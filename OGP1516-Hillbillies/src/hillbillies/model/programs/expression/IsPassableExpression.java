package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class IsPassableExpression extends BooleanExpression{

	private PositionExpression exp;
	
	public IsPassableExpression(PositionExpression e) {
		this.exp = e;
	}
	
	public PositionExpression getExpression() {
		return exp;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		double[] position = (double[]) exp.evaluate(unit).getType();
		int[] intPos = {(int) position[0], (int) position[1], (int) position[2]};
		
		return new TypeBool((unit.getWorld().isCubePassable(intPos[0], intPos[1], intPos[2])));
	}

	public String toString() {
		return "Is passable";
	}
}
