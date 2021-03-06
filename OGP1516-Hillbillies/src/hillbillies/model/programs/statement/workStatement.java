package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.Expression;
import hillbillies.model.programs.expression.PositionExpression;

public class WorkStatement extends Statement {

	private PositionExpression exp;
	
	public WorkStatement(PositionExpression e) {
		super(false);
		System.out.println("workStatement entry " + e);
		this.exp = e;
	}
	
	public double[] getPositionExpression(Unit unit) {
		return (double[]) exp.evaluate(unit).getType();
	}
	
	@Override
	public void execute(Unit unit) {
		System.out.println("voer uit");
		unit.startWorking(new int[] {(int) getPositionExpression(unit)[0] , (int) getPositionExpression(unit)[1], 
				(int) getPositionExpression(unit)[2]});
		setExecutedTrue();
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		if (isExcecuted()) {
			return false;
		}
		return true;
	}



	@Override
	public boolean isWellFormed() {
		return true;
	}

}
