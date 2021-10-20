package ct;

import ct.domain.Order;
import ct.domain.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@AllArgsConstructor
@SpringBootApplication
@EnableJpaAuditing
public class Application implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


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
