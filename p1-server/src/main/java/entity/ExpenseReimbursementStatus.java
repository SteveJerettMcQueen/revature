package entity;

public class ExpenseReimbursementStatus {

	private Integer statusid;
	private String status;

	public Integer getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Integer statusid) {
		this.statusid = statusid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursementStatus [statusid=" + statusid + ", status=" + status + "]";
	}

}