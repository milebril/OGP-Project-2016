package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.Expression;
import hillbillies.model.programs.expression.PositionExpression;

public class workStatement extends Statement {

	private PositionExpression exp;
	
	public workStatement(PositionExpression e) {
		super(false);
		System.out.println("workStatement entry");
		this.exp = e;
	}
	

	
	@Override
	public void execute(Unit unit) {
		System.out.println("voer uit");
		unit.startWorking(exp.evaluate(unit));
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
