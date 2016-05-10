package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.PositionExpression;

public class MoveToStatement extends Statement{

	private PositionExpression exp;

	public MoveToStatement(PositionExpression e) {
		super(false);
		this.exp = e;
	}
	
	public double[] getPositionExpression(Unit unit) {
		System.out.println(exp.evaluate(unit).getType());
		return (double[]) exp.evaluate(unit).getType();
	}

	@Override
	public void execute(Unit unit) {
		int[] target = {(int) getPositionExpression(unit)[0] , (int) getPositionExpression(unit)[1], 
			(int) getPositionExpression(unit)[2]};
		
		unit.startPathfinding(target);
		setExecutedTrue();
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		int[] target = {(int) getPositionExpression(unit)[0] , (int) getPositionExpression(unit)[1], 
				(int) getPositionExpression(unit)[2]};
		
		if (isExcecuted() || ! isWellFormed()) {
			return false;
		} else if (! unit.isInsideWorld(target)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		return true;
	}

}
