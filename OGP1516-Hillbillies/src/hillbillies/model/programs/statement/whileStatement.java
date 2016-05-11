package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.BooleanExpression;

public class WhileStatement extends Statement{
	
	private BooleanExpression condition;
	private Statement body;

	public WhileStatement(BooleanExpression condition, Statement body) {
		super(false);
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void execute(Unit unit) {
		//TODO Deze
	}

	@Override
	public boolean canExecute(Unit unit) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isWellFormed() {
		return body.isWellFormed();
	}
}
