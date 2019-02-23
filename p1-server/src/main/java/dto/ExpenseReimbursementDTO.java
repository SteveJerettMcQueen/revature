package dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;

import entity.ExpenseReimbursementStatus;
import entity.ExpenseReimbursementType;

public class ExpenseReimbursementDTO {

	private Integer erid;
	private Integer eruid;
	private Integer resolver;
	private BigDecimal amount;
	private String description;
	private byte[] receipt;
	private Timestamp resolved;
	private ExpenseReimbursementStatus status;
	private ExpenseReimbursementType type;

	public Integer getErid() {
		return erid;
	}

	public void setErid(Integer erid) {
		this.erid = erid;
	}

	public Integer getEruid() {
		return eruid;
	}

	public void setEruid(Integer eruid) {
		this.eruid = eruid;
	}

	public Integer getResolver() {
		return resolver;
	}

	public void setResolver(Integer resolver) {
		this.resolver = resolver;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public ExpenseReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ExpenseReimbursementStatus status) {
		this.status = status;
	}

	public ExpenseReimbursementType getType() {
		return type;
	}

	public void setType(ExpenseReimbursementType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ExpenseReimbursementDTO [erid=" + erid + ", eruid=" + eruid + ", resolver=" + resolver + ", amount="
				+ amount + ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", resolved="
				+ resolved + ", status=" + status + ", type=" + type + "]";
	}

}
