package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypeBool;

public class IsPassableExpression extends BooleanExpression{

	private UnitExpression exp;
	
	public IsPassableExpression(UnitExpression e) {
		this.exp = e;
	}
	
	public UnitExpression getExpression() {
		return exp;
	}
	
	@Override
	public TypeBool evaluate(Unit unit) {
		Unit temp = (Unit) getExpression().evaluate(unit).getType();
		double[] position = {temp.getPosition()[0], temp.getPosition()[1], temp.getPosition()[2]};
		int[] intPos = {(int) position[0], (int) position[1], (int) position[2]};
		
		return new TypeBool((unit.getWorld().isCubePassable(intPos[0], intPos[1], intPos[2])));
	}

}
