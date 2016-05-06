package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.Expression;

public class workStatement extends Statement {

	private Expression expression;
	
	public workStatement(Expression exp) {
		super(false);
		System.out.println("workStatement entry");
		this.expression = exp;
	}
	

	
	@Override
	public void execute(Unit unit) {
		System.out.println("voer uit");
		unit.startWorking(new int[] {1,1,1});
		setExecutedTrue();
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		if (isExcecuted()) {
			return false;
		}
		return true;
	}
	

}
