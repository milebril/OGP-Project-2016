package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.Expression;

public class PrintStatement extends Statement{

	private Expression exp;
	
	public PrintStatement(Expression e) {
		super(false);
		this.exp = e;
	}

	@Override
	public void execute(Unit unit) {
		exp.toString();
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
