package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.OrderDao;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.util.DBCPUtil;

public class OrderDaoImpl implements OrderDao {

	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public void save(Order o) {
		try {
			qr.update("insert into orders (ordernum,quantity,money,status,customerId) values (?,?,?,?,?)", o.getOrdernum(),o.getQuantity(),o.getMoney(),o.getStatus(),o.getC().getId());
			
			List<OrderItem> list = o.getItems();
			for(OrderItem item : list){
				qr.update("insert into orderItem (id,quantity,price,productId,ordernum) values (?,?,?,?,?)", item.getItemId(), item.getQuantity(), item.getPrice(), item.getProduct().getId(), item.getOrder().getOrdernum());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Order findByOrderNum(String ordernum) {
		try {
			Order o = qr.query("select * from where ordernum=?", new BeanHandler<Order>(Order.class), ordernum);
			return o;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Order> findByCustomerId(String customerId) {
		try {
			return qr.query("select * from orders where customerId=? order by ordernum desc", new BeanListHandler<Order>(Order.class), customerId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
