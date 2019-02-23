package entity;

import java.sql.Timestamp;

public class UserSubmittedExpenseReimbursement {

	private UserSubmittedExpenseReimbursementPK userid;
	private Timestamp submitted;
	private ExpenseReimbursement expenseReimbursement;
	private ExpenseReimbursementUser expenseReimbursementUser;

	public UserSubmittedExpenseReimbursementPK getUserid() {
		return userid;
	}

	public void setUserid(UserSubmittedExpenseReimbursementPK userid) {
		this.userid = userid;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public ExpenseReimbursement getExpenseReimbursement() {
		return expenseReimbursement;
	}

	public void setExpenseReimbursement(ExpenseReimbursement expenseReimbursement) {
		this.expenseReimbursement = expenseReimbursement;
	}

	public ExpenseReimbursementUser getExpenseReimbursementUser() {
		return expenseReimbursementUser;
	}

	public void setExpenseReimbursementUser(ExpenseReimbursementUser expenseReimbursementUser) {
		this.expenseReimbursementUser = expenseReimbursementUser;
	}

	@Override
	public String toString() {
		return "UserSubmittedExpenseReimbursement [userid=" + userid + ", submitted=" + submitted
				+ ", expenseReimbursement=" + expenseReimbursement + ", expenseReimbursementUser="
				+ expenseReimbursementUser + "]";
	}
}