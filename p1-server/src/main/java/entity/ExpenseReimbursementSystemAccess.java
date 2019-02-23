package entity;

public class ExpenseReimbursementSystemAccess {

	private ExpenseReimbursementSystemAccessPK ersaid;

	public ExpenseReimbursementSystemAccessPK getErsaid() {
		return ersaid;
	}

	public void setErsaid(ExpenseReimbursementSystemAccessPK ersaid) {
		this.ersaid = ersaid;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursementSystemAccess [ersaid=" + ersaid + "]";
	}
}