package dto;

import entity.ExpenseReimbursementSystemAccess;
import entity.ExpenseReimbursementSystemRole;

public class ExpenseReimbursementUserDTO {

	private Integer eruid;
	private String email;
	private String firstname;
	private String lastname;
	private ExpenseReimbursementSystemRole role;
	private ExpenseReimbursementSystemAccess access;

	public Integer getEruid() {
		return eruid;
	}

	public void setEruid(Integer eruid) {
		this.eruid = eruid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public ExpenseReimbursementSystemRole getRole() {
		return role;
	}

	public void setRole(ExpenseReimbursementSystemRole role) {
		this.role = role;
	}

	public ExpenseReimbursementSystemAccess getAccess() {
		return access;
	}

	public void setAccess(ExpenseReimbursementSystemAccess access) {
		this.access = access;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursementUserDTO [eruid=" + eruid + ", email=" + email + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", role=" + role + ", access=" + access + "]";
	}
}
