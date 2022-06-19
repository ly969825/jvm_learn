package seven_week;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Pool {

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");

    public static Connection getConn() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    //释放资源
    public static void getCloseConnect(PreparedStatement pres, Connection conn) {
        if (pres != null) {
            try {
                pres.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
