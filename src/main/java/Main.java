import com.todo.repository.TaskRepository;
import com.todo.service.TaskService;
import com.todo.service.TimeService;
import lombok.var;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.todo");
        //ApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        var bean = applicationContext.getBean(TaskService.class);
        var bean2 = applicationContext.getBean(TimeService.class);
        var bean1 = applicationContext.getBean(TaskRepository.class);
        //bean.getAll(TaskOrder.ID.getFieldName(), 0, 10).forEach(System.out::println);

        bean2.stop(3L);


//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        Long date = System.currentTimeMillis();
//        Thread.currentThread().join(1000);
//        Long date1 = System.currentTimeMillis();
//        System.out.println((date1 - date)/1000);
//        System.out.println();
//        System.out.println();
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());



    }


}
