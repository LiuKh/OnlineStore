package com.store.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.store.commons.Page;
import com.store.constant.Constants;
import com.store.domain.Category;
import com.store.domain.Customer;
import com.store.domain.Order;
import com.store.domain.OrderItem;
import com.store.domain.Product;
import com.store.service.BusinessService;
import com.store.service.impl.BusinessServiceImpl;
import com.store.util.DBCPUtil;
import com.store.util.IdGenerator;
import com.store.util.WebUtil;
import com.store.web.bean.Cart;
import com.store.web.bean.CartItem;

public class ClientServlet extends HttpServlet {
	
	BusinessService bs = new BusinessServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opValue = request.getParameter("op");
		
		if("showIndex".equals(opValue)){
			showWelcomePage(request, response);
		}else if("showOneCategory".equals(opValue)){
			showOneCategory(request, response);
		}else if("details".equals(opValue)){
			showProductDetials(request, response);
		}else if("addToCart".equals(opValue)){
			addToCart(request, response);
		}else if("changeNum".equals(opValue)){
			changeNumber(request, response);
		}else if("deleteProduct".equals(opValue)){
			deleteProduct(request, response);
		}else if("removeAll".equals(opValue)){
			removeAll(request, response);
		}else if("Registrition".equals(opValue)){
			registCustomer(request, response);
		}else if("cLogin".equals(opValue)){
			cLogin(request, response);
		}else if("logout".equals(opValue)){
			cLogout(request, response);
		}else if("addOrder".equals(opValue)){
			addOrder(request, response);
		}else if("showOrders".equals(opValue)){
			showOrders(request, response);
		}
	}
	
	private void showOrders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Customer c = (Customer) session.getAttribute(Constants.CUSTOMER_LOGIN_SESSION_NAME);
		List<Order> os = bs.findOrderByCustomer(c.getId());
		request.setAttribute("MyOrders", os);
		request.getRequestDispatcher("/showOrders.jsp").forward(request, response);
		
	}

	private void addOrder(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
//		request.getSession().setMaxInactiveInterval(30*60);
		Customer c = (Customer) session.getAttribute(Constants.CUSTOMER_LOGIN_SESSION_NAME);
		if(c == null){
			response.sendRedirect(request.getContextPath() + "/cLogin.jsp");
			return;
		}else{
			Cart cart = (Cart) session.getAttribute(Constants.HTTPSESSION_CART);
			if(cart == null){
				response.getWriter().write("Session timeout!   redirect to main page in 2 seconds");
				response.setHeader("Refresher", "2;URL=" + request.getContextPath());
			}else{
				Order order = new Order();
				order.setOrdernum(IdGenerator.generateOrderNumber());
				order.setQuantity(cart.getTotalNumberOfProducts());
				order.setMoney(cart.getMoney());
				order.setStatus(0);
				order.setC(c);
				List<OrderItem> list = new ArrayList();
				for(Map.Entry<String, CartItem> me : cart.getMap().entrySet()){
					String productId = me.getKey();
					
					CartItem item = me.getValue();
					OrderItem oitem = new OrderItem();
					oitem.setItemId(IdGenerator.generateGUID());
					oitem.setProduct(item.getProduct());
					oitem.setQuantity(item.getNumberOfProducts());
					oitem.setPrice(item.getItem_money());
					oitem.setOrder(order);
					list.add(oitem);
				}
				
				order.setItems(list);
				bs.addOrder(order);
				request.setAttribute("order", order);
				request.getRequestDispatcher("/payment.jsp").forward(request, response);
			}
		}
			
	}

	private void cLogout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute(Constants.CUSTOMER_LOGIN_SESSION_NAME);
		response.sendRedirect(request.getContextPath());
	}

	private void cLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Customer c = bs.customerLogin(username, password);
		if(c==null){
			response.setHeader("Refresh", "2;URL=" + request.getContextPath()+"/cLogin.jsp");
			response.getWriter().write("Fail to login! Redirect to Login Page in 2 seconds");
			return;
		}
//		request.getSession().setMaxInactiveInterval(30*60);
		request.getSession().setAttribute(Constants.CUSTOMER_LOGIN_SESSION_NAME, c);
		response.setHeader("Refresh", "3;URL=" + request.getContextPath());
		response.getWriter().write("Successful to login! Redirect to Login Page in 3 seconds");
	}

	private void registCustomer(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Customer c = WebUtil.fillBean(request, Customer.class);
		bs.addCustomer(c);
		response.setHeader("Refresh", "3;URL=" + request.getContextPath());
		response.getWriter().write("Register Successfully! Redirect to main page in 3 seconds");
	}

	private void removeAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Cart cart = (Cart) request.getSession().getAttribute(Constants.HTTPSESSION_CART);
		cart.getMap().clear();
		
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	private void deleteProduct(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String productId = request.getParameter("productId");
		
		Cart cart = (Cart) request.getSession().getAttribute(Constants.HTTPSESSION_CART);
		cart.getMap().remove(productId);
		
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	private void changeNumber(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String num = request.getParameter("num");
		String productId = request.getParameter("productId");
		
		Cart cart = (Cart) request.getSession().getAttribute(Constants.HTTPSESSION_CART);
		cart.getMap().get(productId).setNumberOfProducts(Integer.parseInt(num));;
		
		response.sendRedirect(request.getContextPath() + "/cart.jsp");
	}

	private void addToCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String productId = request.getParameter("productId");
		Product p = bs.findProductById(productId);
		
		HttpSession session = request.getSession();
//		session.setMaxInactiveInterval(30*60);
		Cart cart = (Cart) session.getAttribute(Constants.HTTPSESSION_CART);
		if(cart == null){
			cart = new Cart();
			session.setAttribute(Constants.HTTPSESSION_CART, cart);
		}
		
		cart.addProduct(p);
		response.setHeader("Refresh", "2;URL=" + request.getContextPath());
		response.getWriter().write("seccuss!  Redirect to main page in 2 seconds");
	}

	private void showProductDetials(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String productId = request.getParameter("id");
		Product p = bs.findProductById(productId);
		request.setAttribute("product", p);
		
		request.getRequestDispatcher("/productPage.jsp").forward(request, response);
	}

	private void showOneCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> clist = bs.findAllCategories();
		request.setAttribute("clist", clist);
		
		String num = request.getParameter("num");
		String categoryId = request.getParameter("categoryId");
		Page page = bs.findPruductPageRecords(num, categoryId);
		page.setUrl("/servlet/client/ClientServlet?op=showOneCategory&categoryId=" + categoryId);
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/listInMainpage.jsp").forward(request, response);
	}

	private void showWelcomePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> clist = bs.findAllCategories();
		request.setAttribute("clist", clist);
		
		String num = request.getParameter("num");
		Page page = bs.findPruductPageRecords(num);
		page.setUrl("/servlet/client/ClientServlet?op=showIndex");
		request.setAttribute("page", page);
		
		request.getRequestDispatcher("/listInMainpage.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
