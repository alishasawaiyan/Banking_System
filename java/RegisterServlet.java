

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pswd = request.getParameter("password");
		String cpswd = request.getParameter("cpassword");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3309/mydb";
		
		if(pswd.equals(cpswd)) {
			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url, "root", "1234");
				
				String query = "insert into users values(?,?)";
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1, uname);
				ps.setString(2, pswd);
				
				int count = ps.executeUpdate();
				if(count>0) {
					out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"UTF-8\">");
                out.println("<title>Registration Result</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; background-color: #A8D6EF; margin: 0; padding: 20px; }");
                out.println("h1 { color: #333; text-align: center; margin-top: 50px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Registered Successfully!!!</h1>");
                out.println("</body>");
                out.println("</html>");
			}
				else {
					out.println("<!DOCTYPE html>");
		            out.println("<html>");
		            out.println("<head>");
		            out.println("<meta charset=\"UTF-8\">");
		            out.println("<title>Registration Result</title>");
		            out.println("<style>");
		            out.println("body { font-family: Arial, sans-serif; background-color: #f7f7f7; margin: 0; padding: 20px; }");
		            out.println("h2 { color: red; text-align: center; margin-top: 50px; }");
		            out.println("</style>");
		            out.println("</head>");
		            out.println("<body>");
		            out.println("<h1>Didn't get Registerd...</h1>");
		            out.println("</body>");
		            out.println("</html>");
				}
					
			}
			catch(Exception e) {
				out.println("Exception: "+ e.getMessage());
			}
		}
		else {
			out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<title>Registration Result</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background-color: #A8D6EF; margin: 0; padding: 20px; }");
            out.println("h2 { color: red; text-align: center; margin-top: 50px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Gadhe same password dalna tha dono box m!!!</h2>");
            out.println("</body>");
            out.println("</html>");
		}
	}

}
