package hillbillies.model.programs.statement;

import java.util.List;

import hillbillies.model.Unit;

public class sequenceStatement extends Statement{

	private List<Statement> statements;
	
	public sequenceStatement(List<Statement> statements) {
		super(false);
		this.statements = statements;
	}
	
	@Override
	public void execute(Unit unit) {
		for (Statement st : statements) {
			if (! st.isExcecuted()) {
				st.execute(unit);
				return;
			}
		}
		
		setExecutedTrue();
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		if (isExcecuted()) {
			return false;
		}
		
		for (Statement st : statements) {
			if (! st.isExcecuted()) {
				return st.canExecute(unit);
			}
		}
		
		return true;
	}

	
	public List<Statement> getSequence() {
		return statements;
	}

	@Override
	public boolean isWellFormed() {
		for (Statement st : statements) {
			if (! st.isWellFormed()) {
				return false;
			}
		}
		return true;
	}
}
