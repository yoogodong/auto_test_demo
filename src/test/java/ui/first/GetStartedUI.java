package ui.first;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class GetStartedUI {

    private static final Logger log = Logger.getLogger(GetStartedUI.class.getName());
    private static WebDriver driver;
    private static String originWindow;

    @BeforeClass
    public static void setUp() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        System.out.println("关闭浏览器");
        driver.close();
        driver.switchTo().window(originWindow).close();
        log.info(driver.getTitle());
        log.info(driver.getCurrentUrl());
    }

    @Test
    public void name() throws InterruptedException {
        driver.get("http://baidu.com");
        driver.findElement(By.id("kw")).sendKeys("人工智能");
        driver.findElement(By.id("su")).click();

        while (true) {
            log.info("找达内");
            List<WebElement> link = driver.findElements(By.partialLinkText("达内"));
            if (link.size() == 0) {
                log.info("找下一页");
                WebElement nextPage = driver.findElement(By.partialLinkText("下一页"));
                log.info("点击下一页");
                nextPage.click();
                continue;
            }
            log.info("达内");
            originWindow = driver.getWindowHandle();
            link.get(0).click();
            break;
        }
    }
}
