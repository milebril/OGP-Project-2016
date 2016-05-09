package hillbillies.model.programs.statement;

import hillbillies.model.Unit;

//TODO evaluation voor expresion && excecute statements
//Source location voor error messages

public abstract class Statement {
	
	private boolean isExecuted;
	
	public Statement(boolean isExecuted) {
		this.isExecuted = isExecuted;
	}
	
	public boolean isExcecuted() {
		return this.isExecuted;
	}
	
	public void setExecutedTrue() {
		this.isExecuted = true;
	}
	
	public Object whoAmI() {
		return this.getClass();
	}
	
	
	public abstract void execute(Unit unit);
	
	public abstract boolean canExecute(Unit unit);
	
	public abstract boolean isWellFormed();
	
}
