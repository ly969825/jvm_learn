package five_week;

import five_week.anno.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Student/Klass/School 实现自动配置和 Starter
 */
public class SpringDemo2 {

    public static void main(String[] args) {
        //方法三使用配置类
        ApplicationContext context=new AnnotationConfigApplicationContext(ConfigDemo.class);
        Student student = context.getBean(Student.class);
        System.out.println(student.toString());
    }
}
