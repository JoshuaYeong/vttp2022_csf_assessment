package vttp2022.assessment.csf.orderbackend.models;

import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.Data;

// IMPORTANT: You can add to this class, but you cannot delete its original content
@Data
public class Order {

	private Integer orderId;
	private String name;
	private String email;
	private Integer size;
	private String sauce;
	private Boolean thickCrust;
	private List<String> toppings = new LinkedList<>();
	private String comments;

	public void setOrderId(Integer orderId) { this.orderId = orderId; }
	public Integer getOrderId() { return this.orderId; }

	public void setName(String name) { this.name = name; }
	public String getName() { return this.name; }

	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return this.email; }

	public void setSize(Integer size) { this.size = size; }
	public Integer getSize() { return this.size; }

	public void setSauce(String sauce) { this.sauce = sauce; }
	public String getSauce() { return this.sauce; }

	public void setThickCrust(Boolean thickCrust) { this.thickCrust = thickCrust; }
	public Boolean isThickCrust() { return this.thickCrust; }

	public void setToppings(List<String> toppings) { this.toppings = toppings; }
	public List<String> getToppings() { return this.toppings; }
	public void addTopping(String topping) { this.toppings.add(topping); }

	public void setComments(String comments) { this.comments = comments; }
	public String getComments() { return this.comments; }

	public static Order create(String json) {	

		JsonReader reader = Json.createReader(new StringReader(json));
		JsonObject data = reader.readObject();

		Order order = new Order();
		order.setOrderId(data.getInt("order_id"));
		order.setName(data.getString("name"));
		order.setEmail(data.getString("email"));
		order.setSize(data.getInt("pizza_size"));
		order.setThickCrust(data.getBoolean("thick_crust"));
		order.setSauce(data.getString("sauce"));
		// order.setToppings(data.getString("toppings"));
		order.setComments(data.getString("comments"));
		return order;
	}

	public JsonObject toJson() {
		return Json.createObjectBuilder()
			.add("order_id", orderId)
			.add("name", name)
			.add("email", email)
			.add("pizza_size", size)
			.add("thick_crust", thickCrust)
			.add("sauce", sauce)
			// .add("toppings", toppings)
			.add("comments", comments)
			.build();
	}

}
