package gb.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import gb.utils.GBDatabaseUtil;
import gb.vo.GbOrderVO;

@WebServlet("/JoinGbServlet")
public class JoinGbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Gson gson = new Gson();

        List<GbOrderVO> gbOrders = new ArrayList<>();

        try (Connection conn = GBDatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM gb_order")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GbOrderVO gbOrder = new GbOrderVO();
                gbOrder.setGb_id(rs.getInt("gb_id"));
                gbOrder.setUid(rs.getInt("uid"));
                gbOrder.setGb_order_id(rs.getInt("gb_order_id"));


                gbOrders.add(gbOrder);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", "true");

        JsonArray gbOrderArray = new JsonArray();
        for (GbOrderVO gbOrder : gbOrders) {
            JsonObject gbOrderObject = new JsonObject();
            gbOrderObject.addProperty("gb_id", gbOrder.getGb_id());
            gbOrderObject.addProperty("uid", gbOrder.getUid());
            gbOrderObject.addProperty("gb_order_id", gbOrder.getGb_order_id());
            // Set other fields in gbOrderObject

            gbOrderArray.add(gbOrderObject);
        }
        jsonObject.add("gbOrders", gbOrderArray);

        String message = gson.toJson(jsonObject);
        response.getWriter().write(message);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
