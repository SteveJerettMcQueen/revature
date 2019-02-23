package entity;

public class ExpenseReimbursementSystemAccessPK {

	private String username, password;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExpenseReimbursementSystemAccessPK)) {
			return false;
		}
		ExpenseReimbursementSystemAccessPK castOther = (ExpenseReimbursementSystemAccessPK) other;
		return this.username.equals(castOther.username) && this.password.equals(castOther.password);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.password.hashCode();
		return hash;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursementSystemAccessPK [username=" + username + ", password=" + password + "]";
	}

}