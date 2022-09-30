package vttp2022.assessment.csf.orderbackend.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.assessment.csf.orderbackend.models.Order;
import vttp2022.assessment.csf.orderbackend.models.OrderSummary;

@Repository
public class OrderRepository {

    private static final String SQL_INSERT_INTO_ORDERS = "INSERT into orders(order_id, name, email, pizza_size, thick_crust, sauce, toppings, comments) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_ALL_ORDERS_BY_EMAIL = "SELECT * FROM orders WHERE email = ?";

    @Autowired
    private JdbcTemplate template;

    public boolean insertOrder(Order ord) {

        int added = template.update(SQL_INSERT_INTO_ORDERS, 
            ord.getOrderId(),
            ord.getName(),
            ord.getEmail(),
            ord.getSize(),
            ord.isThickCrust(),
            ord.getSauce(),
            ord.getToppings(),
            ord.getComments()
            );

        return 1 == added;
    }

    public List<OrderSummary> getOrderByEmail(String email) {

        List<OrderSummary> orderSummaries = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_ORDERS_BY_EMAIL, email);

        while (rs.next()) {
            OrderSummary oSummary = OrderSummary.create(rs);
            // Order order = Order.create(rs);
            orderSummaries.add(oSummary);
        }

        return orderSummaries;
    }

}
