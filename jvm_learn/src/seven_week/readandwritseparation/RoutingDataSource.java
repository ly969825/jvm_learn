package seven_week.readandwritseparation;


import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RoutingDataSource {
    DataSources value() default DataSources.MASTER;
}