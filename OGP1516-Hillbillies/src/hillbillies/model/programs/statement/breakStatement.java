package hillbillies.model.programs.statement;

import hillbillies.model.Unit;

public class BreakStatement extends Statement{
	
	public BreakStatement() {
		super(true);
	}

	@Override
	public void execute(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return false;
	}
}
