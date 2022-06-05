package five_week.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class JDBCUtils {
    private static final String QUERY_SQL = "SELECT * FROM STUDENT WHERE ID = ?";
    private static final String INSERT_SQL = "INSERT INTO STUDENT VALUES (? ,?)";
    private static final String UPDATE_SQL = "UPDATE STUDENT SET NAME=? WHERE ID = ?";
    private static final String DELETE_SQL = "DELETE FROM STUDENT WHERE ID = ?";


    public static void excutorSql(String sql) {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement statements=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "root");

            //单条处理
            //statement = connection.createStatement();
            //boolean execute = statement.execute(sql);
            //System.out.println(execute);

            //批量处理
            statements = connection.prepareStatement(sql);
             boolean execute = statements.execute();

             //查询
            statements.setInt(1,1);
            ResultSet resultSet = statements.executeQuery(QUERY_SQL);

            //修改
            statements.setString(1,"小花");
            statements.setInt(2,1);
            statements.executeUpdate(UPDATE_SQL);

            //插入
            statements.setInt(1,1);
            statements.setString(2,"小花");
            statements.executeUpdate(INSERT_SQL);

            //删除
            statements.setInt(1,1);
            statements.executeUpdate(DELETE_SQL);

            System.out.println(execute);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
