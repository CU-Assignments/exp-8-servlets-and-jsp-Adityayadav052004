import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String studentId = request.getParameter("studentId");
    String status = request.getParameter("status");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

      PreparedStatement ps = con.prepareStatement("INSERT INTO attendance (student_id, status) VALUES (?, ?)");
      ps.setString(1, studentId);
      ps.setString(2, status);
      ps.executeUpdate();

      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<h2>Attendance Recorded</h2>");

      con.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
