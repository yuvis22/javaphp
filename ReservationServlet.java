import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String country = request.getParameter("Country");
        String phone = request.getParameter("Phone");
        String roomType = request.getParameter("RoomType");
        String bed = request.getParameter("Bed");
        String noOfRoom = request.getParameter("NoofRoom");
        String meal = request.getParameter("Meal");
        String cin = request.getParameter("cin");
        String cout = request.getParameter("cout");

        if (name == null || email == null || country == null || name.isEmpty() || email.isEmpty() || country.isEmpty()) {
            request.setAttribute("message", "Fill the proper details");
            request.setAttribute("status", "error");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            try (Connection conn = DBConnection.getConnection()) {
                String sql = "INSERT INTO roombook(Name, Email, Country, Phone, RoomType, Bed, NoofRoom, Meal, cin, cout, stat, nodays) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'NotConfirm', DATEDIFF(?, ?))";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, country);
                stmt.setString(4, phone);
                stmt.setString(5, roomType);
                stmt.setString(6, bed);
                stmt.setString(7, noOfRoom);
                stmt.setString(8, meal);
                stmt.setString(9, cin);
                stmt.setString(10, cout);
                stmt.setString(11, cout);
                stmt.setString(12, cin);

                int result = stmt.executeUpdate();
                if (result > 0) {
                    request.setAttribute("message", "Reservation successful");
                    request.setAttribute("status", "success");
                } else {
                    request.setAttribute("message", "Something went wrong");
                    request.setAttribute("status", "error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "Database error");
                request.setAttribute("status", "error");
            }
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }
}
