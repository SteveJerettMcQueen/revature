package entity;

public class ExpenseReimbursementType {

	private Integer typeid;
	private String type;

	public Integer getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursementType [typeid=" + typeid + ", type=" + type + "]";
	}

}