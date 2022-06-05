package five_week;

import five_week.anno.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 实现 Spring Bean 的装配
 */
public class SpringDemo1 {

    public static void main(String[] args) {
        //方法一 和 方法二 使用注解和xml配置
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//         Student student = (Student) context.getBean("student");
//         System.out.println(student.toString());

        //方法三使用配置类
        ApplicationContext context=new AnnotationConfigApplicationContext(ConfigDemo.class);
        Student student = context.getBean(Student.class);
        System.out.println(student.getApplicationContext()+"-----"+student);
    }
}
