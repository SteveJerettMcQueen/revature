package dto;

public class UserSubmittedExpenseReimbursementDTO {

	private Integer submitter;
	private Integer erid;

	public Integer getSubmitter() {
		return submitter;
	}

	public void setSubmitter(Integer submitter) {
		this.submitter = submitter;
	}

	public Integer getErid() {
		return erid;
	}

	public void setErid(Integer erid) {
		this.erid = erid;
	}

	@Override
	public String toString() {
		return "UserSubmittedExpenseReimbursementDTO [submitter=" + submitter + ", erid=" + erid + "]";
	}
}
