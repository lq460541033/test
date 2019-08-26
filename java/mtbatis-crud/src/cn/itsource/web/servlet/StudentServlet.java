package cn.itsource.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsource.dao.IStudentDao;
import cn.itsource.dao.impl.StudentDaoImpl;
import cn.itsource.domain.Student;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IStudentDao dao = new StudentDaoImpl();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		/**
		 * 我怎么知道哪一个请求过来是执行哪一个方法？
		 * 	每次请求让它传一个参数(cmd)过来
		 *  cmd=delete -> 执行删除方法 
		 *  cmd=input -> 执行跳转方法
		 *  cmd=edit -> 执行编辑方法
		 *  cmd=list 或者 没有cmd -> 执行查询
		 **/
		String cmd = req.getParameter("cmd");
		if("delete".equals(cmd)){
			this.delete(req, resp);
		}else if("input".equals(cmd)){
			this.input(req, resp);
		}else if("edit".equals(cmd)){
			this.edit(req, resp);
		}else{
			this.list(req, resp);
		}
	}
	
	
	/**
	 *  显示所有数据
	 * */
	protected void list(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//1.接收参数（查询所有数据，不需要接收参数）
		//2.到dao层拿到用户数据
		List<Student> stus = dao.queryAll();
		//3.将拿到的数据放到作用域中
		req.setAttribute("stus", stus);
		//4.跳转：使用请求转发   因为要传参，文件在WEB-INF中
		req.getRequestDispatcher("/WEB-INF/student/list.jsp").forward(req, resp);
	}
	
	
	/**
	 *  添加和修改
	 * */
	protected void edit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 这个方法 是做添加与修改
		 * 	接收id
		 *  如果id不等于空，并且不等于空字符串，代表id存在
		 *  id存在：就是修改
		 *  id不存在：就是添加
		 */
		//接收参数用来封装对象
		String id = req.getParameter("id");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//创建一个Student对象，把获取到的username和password封装到该对象中
		Student stu = new Student();
		stu.setUsername(username);
		stu.setPassword(password);
		//判断 如果id不等于空，并且不等于空字符串，代表id存在
		if(id!=null && !"".equals(id.trim())){
			stu.setId(Long.valueOf(id));
			//执行修改  
			dao.update(stu);
		}else {
			dao.save(stu);
		}
		//跳转
		resp.sendRedirect("/mtbatis-crud/StudentServlet");
	}
	
	/**
	 *  删除
	 * */
	protected void delete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//接收参数（删除不需要接收参数）
		//要删除数据，需要获得前端穿过来的id，因为删除是根据id删除
		String id = req.getParameter("id");
		//调用删除方法 删除的时候需要传入一个Long型的而获得的id是一个String类型，转换类型
		dao.delete(Long.valueOf(id));
		//跳转    数据删除后跳转到StudentServlet中的list方法
		resp.sendRedirect("/mtbatis-crud/StudentServlet");
	}
	
	/**
	 *  跳转(中转的servlet)  这个方法用于在edit方法是跳转到哪一个前端页面
	 * */
	protected void input(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 又要做添加，又要做修改。
		 * 	如果id不等于空，并且不等于空字符串，代表id存在
		 *  id存在：代表修改
		 *  id不存在：代表添加
		 */
		String id = req.getParameter("id");
		if(id!=null && !"".equals(id.trim())){
			Student stu = dao.queryOne(Long.parseLong(id));
			//把edit方法中添加和修改后的数据设置到作用域中
			req.setAttribute("stu", stu);
		}
		//跳转：请求转发 ->1.在WEB-INF中
		req.getRequestDispatcher("/WEB-INF/student/edit.jsp").forward(req, resp);
	}

}
