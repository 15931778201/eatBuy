package com.sxdx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sxdx.dao.OrderInfoDAO;
import com.sxdx.dao.BaseDAO;
import com.sxdx.vo.FoodOrderInfo;


public class confirmServlet extends HttpServlet{
	public confirmServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String username=request.getParameter("name");
		OrderInfoDAO orderDAO=new OrderInfoDAO();
		Connection conn=null;
		PreparedStatement ps=null;
		int rs=0;
		conn=BaseDAO.getConn();
		try {
			ps=conn.prepareStatement( "update foodOrderInfo set state='confirm' where customerName=?;");
			ps.setString(1, username);
			rs = ps.executeUpdate();
			if(rs>0)
			{
				 PrintWriter out = response.getWriter();
				 out.print("<script>" + "alert('确认订单成功');"+"</script>");
				 out.close();
			}
			else
			{
				 PrintWriter out = response.getWriter();
				 out.print("<script>" + "alert('确认订单失败');");
				 out.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}