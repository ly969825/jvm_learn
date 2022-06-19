package seven_week.readandwritseparation;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class AbstractRoutingDataSourceDemo extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String language = LocaleContextHolder.getLocale().getLanguage();

        return DatasourceHolder.getLookupKey();
    }
}
