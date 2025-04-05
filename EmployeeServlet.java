import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String empId = request.getParameter("id");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

      PreparedStatement ps = con.prepareStatement("SELECT * FROM employees WHERE id = ?");
      ps.setString(1, empId);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        out.println("<p>ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + "</p>");
      }

      con.close();
    } catch (Exception e) {
      e.printStackTrace(out);
    }
  }
}
