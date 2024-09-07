

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
        int amt = Integer.parseInt(request.getParameter("amt"));
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3309/sys";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "root", "1234");
			
			String query = "update Account set balance=balance+? where num=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, amt);
			ps.setInt(2,num);	
			
			int count = ps.executeUpdate();
			if(count>0) {
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
				out.println("<h1>Deposit Complete</h1>");
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
	             RequestDispatcher rd = request.getRequestDispatcher("deposit.html");
				rd.include(request, response);
		    }
		}
		catch(Exception e){
			out.println("Exception: "+ e.getMessage());
		}
	}

}
