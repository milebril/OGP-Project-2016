package hillbillies.model.programs.statement;

import hillbillies.model.Unit;

//TODO evaluation voor expresion && excecute statements
//Source location voor error messages

public abstract class Statement {
	
	private boolean isExecuted;
	
	public boolean isExcecuted() {
		return isExecuted;
	}
	
	public void setExecutedTrue() {
		this.isExecuted = true;
	}
	
	
	public abstract void execute(Unit unit);
	
	public abstract boolean canExecute(Unit unit);
	
}
