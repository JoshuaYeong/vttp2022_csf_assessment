package vttp2022.assessment.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import vttp2022.assessment.csf.orderbackend.models.Order;

public class OrderRowMapper implements RowMapper<Order> {

    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Order order = new Order();
		order.setOrderId(rs.getInt("order_id"));
		order.setName(rs.getString("name"));
		order.setEmail(rs.getString("email"));
		order.setSize(rs.getInt("pizza_size"));
		order.setThickCrust(rs.getBoolean("thick_crust"));
		order.setSauce(rs.getString("sauce"));
		// order.setToppings(rs.getString("toppings"));
		order.setComments(rs.getString("comments"));
		return order;
    }
    
}
