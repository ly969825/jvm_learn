package seven_week.readandwritseparation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DynamicDataSource extends AbstractRoutingDataSource {
    private Logger log= (Logger) LoggerFactory.getLogger(DynamicDataSource.class);

    protected Object determineCurrentLookupKey() {
        return DataSourceKeyHolder.getCurrentKey();
    }
 
    @Override
    public Connection getConnection() throws SQLException {
        Connection connection = super.getConnection();
        if (DataSourceKeyHolder.getCurrentKey() != null) {
            log.info("Datasource route to {}, key={}", connection, DataSourceKeyHolder.getCurrentKey());
        }
        return connection;
    }
}