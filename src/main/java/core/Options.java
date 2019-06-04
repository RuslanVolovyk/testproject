package core;

import org.openqa.selenium.chrome.ChromeOptions;

abstract class Options {

    ChromeOptions chromeOptionsLocal() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-notifications");
        return options;
    }
}
