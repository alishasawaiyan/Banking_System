

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pswd = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3309/mydb";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "root", "1234");
			
			String query = "select * from users where uname=? and pswd=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,uname);
			ps.setString(2, pswd);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Login Result</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #A8D6EF; margin: 0; padding: 20px; }");
                out.println("h1 { color: #333; text-align: center; margin-top: 50px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
				out.println("<h1>Welcome " + rs.getString(1) + "</h1>");
				 out.println("</body>");
	             out.println("</html>");
				RequestDispatcher rd = request.getRequestDispatcher("home.html");
				rd.include(request, response);
			}
			else {
				out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Login Result</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #A8D6EF; margin: 0; padding: 20px; }");
                out.println("h1 { color: #333; text-align: center; margin-top: 50px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
				out.println("<h1>Wrong Creditials</h1>");
				 out.println("</body>");
	                out.println("</html>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		}
		catch(Exception e) {
			out.println("Exception: "+ e.getMessage());
		}
	}
}
