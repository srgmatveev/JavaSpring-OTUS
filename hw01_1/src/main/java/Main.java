import dao.PersonDao;
import dao.PersonDaoSimpleImpl;
import domain.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.PersonService;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/spring-context.xml");
        System.out.println("Spring app started...");
        PersonService personService = (PersonService) context.getBean("personService");
        Person ivan = personService.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());


    }
}
