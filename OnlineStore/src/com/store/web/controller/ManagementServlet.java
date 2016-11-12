package com.store.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.store.commons.Page;
import com.store.domain.Product;
import com.store.domain.Category;
import com.store.service.BusinessService;
import com.store.service.impl.BusinessServiceImpl;
import com.store.util.IdGenerator;
import com.store.util.WebUtil;

public class ManagementServlet extends HttpServlet {
	
	private BusinessService bs = new BusinessServiceImpl();

	public ManagementServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String opValue = request.getParameter("op");
		if("addCategory".equals(opValue)){
			addCategory(request, response);
		}else if("showCategories".equals(opValue)){
			showCategories(request, response);
		}else if("addProduct".equals(opValue)){
			addProdcut(request, response);
		}else if("addProductIntoDB".equals(opValue)){
			addProductIntoDB(request, response);
		}else if("showProducts".equals(opValue)){
			showProducts(request, response);
		}
		
	}

	private void showProducts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		Page page = bs.findPruductPageRecords(num);
		page.setUrl("/servlet/management/ManagementServlet?op=showProducts");
		request.setAttribute("page", page);
		request.getRequestDispatcher("/management/listProducts.jsp").forward(request, response);
	}

	private void addProductIntoDB(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		if(!ServletFileUpload.isMultipartContent(request))
			throw new RuntimeException("error");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> files =  new ArrayList<FileItem>();
		try {
			files = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			throw new RuntimeException();
		}
		
		
		Product product = new Product();
		for(FileItem file : files){
			if(file.isFormField()){
				try {
					processFormField(file, product);
				} catch (Exception e) {
					throw new RuntimeException();
				}
			}else{
				try {
					processUploadField(file, product);
				} catch (Exception e) {
					throw new RuntimeException();
				}
			}
		}
		
		bs.addProduct(product);
		response.setHeader("Refresh", "2;URL=" + request.getContextPath()+"/management/index.jsp");
		response.getWriter().write("seccess   redirect to main page in 2 seconds");
	}

	private void processUploadField(FileItem file, Product product) throws Exception {
		String storageDirectory = getServletContext().getRealPath("/photos");
		File root = new File(storageDirectory);
		if(!root.exists())
			root.mkdirs();
		
		String filename = file.getName();
		if(filename != null){
			//revise file name
			filename = IdGenerator.generateGUID() + "." + FilenameUtils.getExtension(filename);
			product.setFileName(filename);
		}
		
		String childPath = generateChildDirectory(storageDirectory, filename);
		product.setPath(childPath);
		
		file.write(new File(storageDirectory, childPath+"/"+filename));
	}

	private void processFormField(FileItem file, Product product) throws Exception {
		String fieldName = file.getFieldName();
		String fieldValue = file.getString("UTF-8");
		
		if("categoryId".equals(fieldName)){
			product.setCategory(bs.findCategoryById(fieldValue));
		}
		
		try {
			BeanUtils.setProperty(product, fieldName, fieldValue);
		}catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private String generateChildDirectory(String storageDirectory,
			String filename) {
		int hashCode = filename.hashCode();
		int dir1 = hashCode&0xf;
		int dir2 = (hashCode&0xf0)>>4;
		
		String path = dir1 + File.separator + dir2;
		File file = new File(storageDirectory, path);
		if(!file.exists())
			file.mkdirs();
		return path;
	}

	private void addProdcut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = bs.findAllCategories();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/management/addProduct.jsp").forward(request, response);
	}

	private void showCategories(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Category> list = bs.findAllCategories();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/management/List.jsp").forward(request, response);
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Category c = WebUtil.fillBean(request, Category.class);
		bs.addCategory(c);
		
		response.sendRedirect(request.getContextPath() + "/message.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {

	}

}
