package component;

import component.domain.Order;
import component.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    private OrderRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Order order = Order.builder()
                .receiverPhone("138")
                .receiverName("张三丰")
                .shipmentAddress("长沙市开福区")
                .build();

        Order saved = repository.save(order);
        System.out.println(saved);
    }
}
