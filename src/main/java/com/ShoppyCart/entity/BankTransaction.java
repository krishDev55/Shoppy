package com.ShoppyCart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class BankTransaction {
	@Id
	private String id; // "pay_QAUgbuwOj5E8gf",
	private String transactionId;
	private long accountNo;
	private double  amount; 
	private String  type;
	private String  timestamp;
	private String order_id; // "order_QAUbBuM8SRTQ29",
	private String entity ;// "payment",
	private String status;// "captured";
	private boolean captured; // true,
	private long contact ;// "+917057553768",
	private String invoice_id ; // null,
	private String currency; // "INR",
	private boolean international ; // false,
	private String email ; //"komal@gmail.com",
	private int fee;
	private String description;
	private String created_at; //1742790120,
	private int amount_refunded ; // 0,
	private String bank ;// "BARB_R",
	private String error_reason ;// null,
	private String error_description ; // null,
	
//			//	  "notes": {
//			    "address": "Pune"
//			};
//			"acquirer_data": {
//			    "bank_transaction_id": "7454633"
//			},
//			private double  amount ; //15000,
			private String refund_status ; //null,
			private String wallet; // null,
			private String method ; // "netbanking",
			private String vpa ; //null,
			private String  error_source; // null,
			private String error_step ; //null,
			private double  tax ; //54,
			private String card_id ; //null,
			private String  error_code ;// null,
	
	
	
	public String getTransactionId() {
		return transactionId;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public double getAmount() {
		return amount;
	}
	public String getType() {
		return type;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public String getDescription() {
		return description;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getId() {
		return id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public String getEntity() {
		return entity;
	}
	public String getStatus() {
		return status;
	}
	public boolean isCaptured() {
		return captured;
	}
	public long getContact() {
		return contact;
	}
	public String getInvoice_id() {
		return invoice_id;
	}
	public String getCurrency() {
		return currency;
	}
	public boolean isInternational() {
		return international;
	}
	public String getEmail() {
		return email;
	}
	public int getFee() {
		return fee;
	}
	public String getCreated_at() {
		return created_at;
	}
	public int getAmount_refunded() {
		return amount_refunded;
	}
	public String getBank() {
		return bank;
	}
	public String getError_reason() {
		return error_reason;
	}
	public String getError_description() {
		return error_description;
	}
	public String getRefund_status() {
		return refund_status;
	}
	public String getWallet() {
		return wallet;
	}
	public String getMethod() {
		return method;
	}
	public String getVpa() {
		return vpa;
	}
	public String getError_source() {
		return error_source;
	}
	public String getError_step() {
		return error_step;
	}
	public double getTax() {
		return tax;
	}
	public String getCard_id() {
		return card_id;
	}
	public String getError_code() {
		return error_code;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCaptured(boolean captured) {
		this.captured = captured;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public void setInvoice_id(String invoice_id) {
		this.invoice_id = invoice_id;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public void setInternational(boolean international) {
		this.international = international;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public void setAmount_refunded(int amount_refunded) {
		this.amount_refunded = amount_refunded;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setError_reason(String error_reason) {
		this.error_reason = error_reason;
	}
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}
	public void setWallet(String wallet) {
		this.wallet = wallet;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setVpa(String vpa) {
		this.vpa = vpa;
	}
	public void setError_source(String error_source) {
		this.error_source = error_source;
	}
	public void setError_step(String error_step) {
		this.error_step = error_step;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	@Override
	public String toString() {
		return "BankTransaction [id=" + id + ", transactionId=" + transactionId + ", accountNo=" + accountNo
				+ ", amount=" + amount + ", type=" + type + ", timestamp=" + timestamp + ", order_id=" + order_id
				+ ", entity=" + entity + ", status=" + status + ", captured=" + captured + ", contact=" + contact
				+ ", invoice_id=" + invoice_id + ", currency=" + currency + ", international=" + international
				+ ", email=" + email + ", fee=" + fee + ", description=" + description + ", created_at=" + created_at
				+ ", amount_refunded=" + amount_refunded + ", bank=" + bank + ", error_reason=" + error_reason
				+ ", error_description=" + error_description + ", refund_status=" + refund_status + ", wallet=" + wallet
				+ ", method=" + method + ", vpa=" + vpa + ", error_source=" + error_source + ", error_step="
				+ error_step + ", tax=" + tax + ", card_id=" + card_id + ", error_code=" + error_code + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
