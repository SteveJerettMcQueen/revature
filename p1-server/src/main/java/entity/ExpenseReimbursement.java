package entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

public class ExpenseReimbursement {

	private Integer erid;
	private BigDecimal amount;
	private String description;
	private byte[] receipt;
	private Timestamp resolved;
	private ExpenseReimbursementStatus status;
	private ExpenseReimbursementType type;
	private ExpenseReimbursementUser resolver;

	public Integer getErid() {
		return this.erid;
	}

	public void setErid(Integer erid) {
		this.erid = erid;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return this.receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public Timestamp getResolved() {
		return this.resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public ExpenseReimbursementStatus getStatus() {
		return this.status;
	}

	public void setStatus(ExpenseReimbursementStatus status) {
		this.status = status;
	}

	public ExpenseReimbursementType getType() {
		return this.type;
	}

	public void setType(ExpenseReimbursementType type) {
		this.type = type;
	}

	public ExpenseReimbursementUser getResolver() {
		return this.resolver;
	}

	public void setResolver(ExpenseReimbursementUser resolver) {
		this.resolver = resolver;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursement [erid=" + erid + ", amount=" + amount + ", description=" + description
				+ ", receipt=" + Arrays.toString(receipt) + ", resolved=" + resolved + ", status=" + status + ", type="
				+ type + ", resolver=" + resolver + "]";
	}

}