package hillbillies.model.programs.expression;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.type.TypePosition;

public class LiteralPositionExpression extends PositionExpression{

	private double[] position = {0,0,0};
	
	public LiteralPositionExpression(int x, int y, int z) {
		System.out.println("[creating Lit Pos]");
		position[0] = x+0.5;
		position[1] = y+0.5;
		position[2] = z+0.5;
	}
	
	@Override	
	public TypePosition evaluate(Unit unit) {
		return new TypePosition(position);
	}
	
}
