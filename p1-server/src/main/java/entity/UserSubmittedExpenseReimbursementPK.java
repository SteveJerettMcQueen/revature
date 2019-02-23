package entity;

public class UserSubmittedExpenseReimbursementPK {

	private Integer submitter;
	private Integer erid;

	public Integer getSubmitter() {
		return this.submitter;
	}

	public void setSubmitter(Integer submitter) {
		this.submitter = submitter;
	}

	public Integer getErid() {
		return this.erid;
	}

	public void setErid(Integer erid) {
		this.erid = erid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserSubmittedExpenseReimbursementPK)) {
			return false;
		}
		UserSubmittedExpenseReimbursementPK castOther = (UserSubmittedExpenseReimbursementPK) other;
		return this.submitter.equals(castOther.submitter) && this.erid.equals(castOther.erid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.submitter.hashCode();
		hash = hash * prime + this.erid.hashCode();
		return hash;
	}

	@Override
	public String toString() {
		return "UserSubmittedExpenseReimbursementPK [submitter=" + submitter + ", erid=" + erid + "]";
	}

}