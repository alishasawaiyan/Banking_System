

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

@WebServlet("/BalanceServlet")
public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3309/sys";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "root", "1234");
			
			String query = "select * from Account where num=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,num);	
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Result</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #A8D6EF; margin: 0; padding: 20px; }");
                out.println("h1 { color: #333; text-align: center; margin-top: 50px; }");
                out.println("h2 { color: #333; text-align: center; margin-top: 50px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
				out.println("<h1>Details </h1>");
				out.println("<h2>Name: "+ rs.getString(2) +"</h2>");
				out.println("<h2>Balance: "+ rs.getInt(3) +"</h2>");
				out.println("</body>");
	             out.println("</html>");
			}
			else {
				out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Result</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #A8D6EF; margin: 0; padding: 20px; }");
                out.println("h1 { color: #333; text-align: center; margin-top: 50px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
				out.println("<h1>Failed</h1>");
				out.println("</body>");
	             out.println("</html>");
	             RequestDispatcher rd = request.getRequestDispatcher("checkBalance.html");
				rd.include(request, response);
		    }
		}
		catch(Exception e){
			out.println("Exception: "+ e.getMessage());
		}
	}

}
