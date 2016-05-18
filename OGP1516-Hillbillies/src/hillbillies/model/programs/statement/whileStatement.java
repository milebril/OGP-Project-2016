package hillbillies.model.programs.statement;

import org.hamcrest.core.IsInstanceOf;

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
		boolean condition = (boolean) this.condition.evaluate(unit).getType();
		
		while(condition) {
			if (body instanceof BreakStatement) {
				break;
			}
			body.execute(unit);
		}
	}

	@Override
	public boolean canExecute(Unit unit) {
		return body.canExecute(unit);
	}

	@Override
	public boolean isWellFormed() {
		return body.isWellFormed();
	}
}
