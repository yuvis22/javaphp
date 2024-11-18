import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            // Room booking count
            String roombookSql = "SELECT COUNT(*) FROM roombook";
            PreparedStatement roombookStmt = conn.prepareStatement(roombookSql);
            ResultSet roombookRs = roombookStmt.executeQuery();
            int roombookRow = roombookRs.next() ? roombookRs.getInt(1) : 0;

            // Total rooms count
            String roomSql = "SELECT COUNT(*) FROM room";
            PreparedStatement roomStmt = conn.prepareStatement(roomSql);
            ResultSet roomRs = roomStmt.executeQuery();
            int roomRow = roomRs.next() ? roomRs.getInt(1) : 0;

            // Staff count
            String staffSql = "SELECT COUNT(*) FROM staff";
            PreparedStatement staffStmt = conn.prepareStatement(staffSql);
            ResultSet staffRs = staffStmt.executeQuery();
            int staffRow = staffRs.next() ? staffRs.getInt(1) : 0;

            // Room type data for chart
            int[] roomTypeCounts = new int[4];
            String[] roomTypes = { "Superior Room", "Deluxe Room", "Guest House", "Single Room" };
            for (int i = 0; i < roomTypes.length; i++) {
                String chartSql = "SELECT COUNT(*) FROM roombook WHERE RoomType=?";
                PreparedStatement chartStmt = conn.prepareStatement(chartSql);
                chartStmt.setString(1, roomTypes[i]);
                ResultSet chartRs = chartStmt.executeQuery();
                roomTypeCounts[i] = chartRs.next() ? chartRs.getInt(1) : 0;
            }

            // Profit data for Morris.js
            String paymentSql = "SELECT cout, finaltotal FROM payment";
            PreparedStatement paymentStmt = conn.prepareStatement(paymentSql);
            ResultSet paymentRs = paymentStmt.executeQuery();
            StringBuilder chartData = new StringBuilder();
            double totalProfit = 0;
            while (paymentRs.next()) {
                String date = paymentRs.getString("cout");
                double profit = paymentRs.getDouble("finaltotal") * 0.1; // 10% of final total
                chartData.append("{ date: '").append(date).append("', profit: ").append(profit).append(" }, ");
                totalProfit += profit;
            }

            if (chartData.length() > 0) {
                chartData.setLength(chartData.length() - 2); // Remove trailing comma
            }

            // Pass data to JSP
            request.setAttribute("roombookRow", roombookRow);
            request.setAttribute("roomRow", roomRow);
            request.setAttribute("staffRow", staffRow);
            request.setAttribute("roomTypeCounts", roomTypeCounts);
            request.setAttribute("chartData", chartData.toString());
            request.setAttribute("totalProfit", totalProfit);

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
