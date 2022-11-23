package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/registerServlet")
public class Register extends HttpServlet{
	Connection connection = null;
	String query = null;
	String dbUser;
	String dbPw;
	String url;
	int status = 1;
	
	

	public void init(ServletConfig config) throws ServletException{
		url = "jdbc:mysql://localhost:3306/pwmanager";
		dbUser = "root";
		dbPw = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection  = DriverManager.getConnection(url,dbUser,dbPw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
		
	}

	protected void doPost(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmation = req.getParameter("confirm");
		System.out.println(username);
		PrintWriter writer = resp.getWriter();
		
		if (username.length() < 3) {
            RequestDispatcher rs = req.getRequestDispatcher("register.jsp");
            writer.println("<p style='color:red;'>Username can not be shorter than 3 characters!</p>");
            rs.include(req, resp);
		} else if (password.length() < 6) {
            RequestDispatcher rs = req.getRequestDispatcher("register.jsp");
            writer.println("<p style='color:red;'>Password must be minimum 6 characters long!</p>");
            rs.include(req, resp);
			
		}else {
			if (password.equals(confirmation)) {
			
				try {
					
					resp.setContentType("text/html");
					query = "Insert into users (username, password, status) values (?, ?, ?)";
					PreparedStatement stmt =connection.prepareStatement(query);
					stmt.setString(1, username);
					stmt.setString(2, password);
					stmt.setInt(3, status);
					stmt.executeUpdate();
					
					 writer.println("<meta http-equiv='refresh' content='5;URL=login.jsp'>");
					 writer.println("<p style='color:green;'>Successfully Registered!</p><p"
					 		+ " style='color:green;'> Redirecting to login page</p>");
					connection.close();
			        
				}catch (SQLIntegrityConstraintViolationException e) {
				            
		            RequestDispatcher rs = req.getRequestDispatcher("register.jsp");
		            writer.println("<p style='color:red;'>Username is taken!</p>");
		            rs.include(req, resp);	            
				}
				catch (Exception e) {
	
					e.printStackTrace();
				}
			} else {
	            RequestDispatcher rs = req.getRequestDispatcher("register.jsp");
	            writer.println("<p style='color:red;'>Password does not match!</p>");
	            rs.include(req, resp);
			}
		}
	}
}
