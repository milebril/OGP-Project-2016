package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;

public class NextToExpression extends PositionExpression{

	private PositionExpression exp;
	
	public NextToExpression(PositionExpression e) {
		this.exp = e;
	}
	
	public PositionExpression getExpression() {
		return exp;
	}
	
	@Override
	public TypePosition evaluate(Unit unit) {
		double[] pos = (double[]) (getExpression().evaluate(unit).getType());
		int[] intPos = {(int) pos[0], (int) pos[1], (int) pos[2]};
		for (int[] tempPos : Unit.getAdjacentCubes(intPos)) {
			if (unit.getWorld().isCubePassable(tempPos[0], tempPos[1], tempPos[2])) {
				double[] position = {tempPos[0] + 0.5, tempPos[1] + 0.5, tempPos[2] + 0.5};
				return new TypePosition(position);
			}
		}
		
		return new TypePosition(null);
	}

	public String toString() {
		return "Is next to";
	}
}
