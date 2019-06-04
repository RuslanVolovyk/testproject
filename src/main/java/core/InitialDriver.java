package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static utils.Base.*;

public class InitialDriver extends Options {

    private InitialDriver() {
    }

    public static InitialDriver getInstance() {
        if (driverThread.get() == null) {
            synchronized (InitialDriver.class) {
                driverThread.set(new InitialDriver());
            }
        }
        return driverThread.get();
    }

    private static ThreadLocal<InitialDriver> driverThread = new ThreadLocal<>();

    public WebDriver driver;

    WebDriver getDriver() {
        if (driver == null) {
            driver = initialDriver();
            return driver;
        } else {
            return driver;
        }
    }

    private synchronized WebDriver initialDriver() {
        if ("CHROME".equals(DRIVER_NAME)) {
            if (!DRIVER_VERSION.equals("0")) {
                WebDriverManager.chromedriver().version(DRIVER_VERSION).setup();
            } else {
                System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

            }
            driver = new ChromeDriver(chromeOptionsLocal());
        }

        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        EventHandler handler = new EventHandler() {
        };
        EventLogger log = new EventLogger() {
        };
        driver = eventDriver.register(log);
        driver = eventDriver.register(handler);
        return driver;
    }

    public void destroy() {
        if (driver != null) {
            getDriver().quit();
            driver = null;
        }
    }
}
