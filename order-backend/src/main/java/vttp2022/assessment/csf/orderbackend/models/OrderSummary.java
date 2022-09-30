package vttp2022.assessment.csf.orderbackend.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import lombok.Data;

// IMPORTANT: You can add to this class, but you cannot delete its original content
@Data
public class OrderSummary {
	private Integer orderId;
	private String name;
	private String email;
	private Float amount;

	public void setOrderId(Integer orderId) { this.orderId = orderId; }
	public Integer getOrderId() { return this.orderId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setAmount(Float amount) { this.amount = amount; }
	public Float getAmount() { return this.amount; }

	public static OrderSummary create(SqlRowSet rs) {
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setOrderId(rs.getInt("order_id"));
		orderSummary.setName(rs.getString("name"));
		orderSummary.setEmail(rs.getString("email"));
		return orderSummary;
	}

}
