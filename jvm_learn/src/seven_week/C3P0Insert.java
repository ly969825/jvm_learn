package seven_week;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class C3P0Insert {

    public static void main(String[] args) {
        long startTs = System.currentTimeMillis();
        Connection conn = C3P0Pool.getConn();
        String sql = "insert into user (user_id,user_name,age) values (?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 1000000; i++) {
                ps.setLong(1, i);
                ps.setString(2, "小米");
                ps.setInt(3, 20);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Pool.getCloseConnect(ps,conn);
            long endTs = System.currentTimeMillis();
            System.out.println((endTs-startTs)/1000);
        }
    }
}
