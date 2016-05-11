package hillbillies.model.programs.statement;

import hillbillies.model.Unit;
import hillbillies.model.programs.expression.BooleanExpression;

public class IfStatement extends Statement{
	
	private Statement ifBody;
	private Statement elseBody;
	private BooleanExpression exp;
	
	public IfStatement(BooleanExpression e, Statement ifBody, Statement elseBody) {
		super(false);
		this.exp = e;
		this.ifBody = ifBody;
		this.elseBody = elseBody;
	}

	@Override
	public void execute(Unit unit) {
		boolean condition = (boolean) exp.evaluate(unit).getType();
		
		if (condition) {
			ifBody.execute(unit);
		} else {
			elseBody.execute(unit);
		}
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		if (isExcecuted()) {
			return false;
		}
		if (! ifBody.isExcecuted() || ! elseBody.isExcecuted()) {
			if ((boolean) exp.evaluate(unit).getType()) {
				return ifBody.canExecute(unit);
			} else {
				return elseBody.canExecute(unit);
			}
		}
		return true;
	}

	@Override
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return ifBody.isWellFormed() && elseBody.isWellFormed();
	}
}
