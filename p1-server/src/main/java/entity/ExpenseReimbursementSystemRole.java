package entity;

public class ExpenseReimbursementSystemRole {

	private Integer roleid;
	private String role;

	public Integer getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursementSystemRole [roleid=" + roleid + ", role=" + role + "]";
	}

}