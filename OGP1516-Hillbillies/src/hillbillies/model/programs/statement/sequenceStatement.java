package hillbillies.model.programs.statement;

import java.util.List;

import hillbillies.model.Unit;

public class sequenceStatement extends Statement{

	private List<Statement> statements;
	
	@Override
	public void execute(Unit unit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canExecute(Unit unit) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public List<Statement> getSequence() {
		return statements;
	}
}
