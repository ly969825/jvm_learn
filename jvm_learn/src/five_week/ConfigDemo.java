package five_week;

import five_week.anno.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigDemo {
    @Bean
    public Student student() {
        return new Student(1, "HHH", null, null);
    }

}
