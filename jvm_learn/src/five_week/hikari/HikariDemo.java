package five_week.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariDemo {

    public static DataSource dataSource(){
        HikariConfig hikariConfig =new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/mysql");
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setPassword("root");
        hikariConfig.setPassword("root");
        return new HikariDataSource(hikariConfig);
    }

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = dataSource();
        Connection connection = dataSource.getConnection();
        String sql = "SELECT * FROM STUDENT";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id"));
        }
    }
}
