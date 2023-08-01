package gb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GBDatabaseUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FurrEver";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    // 取得資料庫連接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // 關閉資料庫連接和相關資源
    public static void closeResources(Connection conn, PreparedStatement pstmt) {
        closeResources(conn, pstmt, null);
    }

    public static void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
//以下/////////////////////////////////////////////

 // 讀取圖片的二進制數據
    public static byte[] readImage(ServletContext context, String imagePath) throws IOException {
        try (InputStream is = context.getResourceAsStream(imagePath);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            int bytesRead;
            byte[] buffer = new byte[4096];
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }

            return baos.toByteArray();
        }
    }

//以上。/////////////////////////////////////////////////
}
