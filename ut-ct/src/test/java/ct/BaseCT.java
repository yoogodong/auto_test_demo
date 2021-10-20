package ct;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = Application.class)
@Transactional
//@Profile()
//@TestConfiguration
public class BaseCT {
}
