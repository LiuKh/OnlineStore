package com.store.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.store.dao.managementDao;
import com.store.domain.Function;
import com.store.domain.Role;
import com.store.domain.User;
import com.store.util.DBCPUtil;

public class managementDaoImpl implements managementDao {

	private QueryRunner qr = new QueryRunner(DBCPUtil.getDataSource());
	
	@Override
	public User login(String username, String password) {
		try {
			return qr.query("select * from users where username=? and password=?", new BeanHandler<User>(User.class),username,password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Role> findByUser(User user) {
		if(user == null || user.getId() == null)
			throw new IllegalArgumentException("no user");
		
		try {
			return qr.query("select * from roles where id in (select r_id from urr where u_id=?)", new BeanListHandler<Role>(Role.class),user.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Function> findByRole(Role role) {
		if(role == null || role.getId() == null)
			throw new IllegalArgumentException("no user");
		
		try {
			return qr.query("select * from functions where id in (select f_id from rfr where r_id=?)", new BeanListHandler<Function>(Function.class),role.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
