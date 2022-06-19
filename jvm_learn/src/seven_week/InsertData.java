package seven_week;

import java.sql.*;

/**
 * 插入一百万条数据
 */
public class InsertData {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&rewriteBatchedStatements=true", "root", "root");
            String sql = "insert into user (user_id,user_name,age) values (?,?,?)";
            long startTs = System.currentTimeMillis();
            ps = conn.prepareStatement(sql);
//            for (int i =0; i<1000000;i++ ){
//                ps.setLong(1,i);
//                ps.setString(2,"小米");
//                ps.setInt(3,20);
//                ps.executeUpdate();
//            }
            for (int i =0; i<1000000;i++ ){
                ps.setLong(1,i);
                ps.setString(2,"小米");
                ps.setInt(3,20);
                ps.addBatch();
            }
            ps.executeBatch();
            long endTs = System.currentTimeMillis();
            System.out.println((endTs-startTs)/1000);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
