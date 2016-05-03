package hillbillies.model.programs.expression;

import hillbillies.model.Unit;

public class isSolidExpression extends booleanExpression{
	
	private positionExpression expression;
	
	public isSolidExpression(positionExpression exp) {
		expression = exp;
	}

	public positionExpression getExpression() {
		return expression;
	}
	
	@Override
	public boolean evaluate(Unit unit) {
		// TODO Auto-generated method stub
		return (! unit.getWorld().isCubePassable(expression[0], expression[1], expression[2]))
	}
	
	
	
	
}
