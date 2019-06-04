package elements;

import core.Element;

public class Windows extends Element {

    public void navigate(String url) {
        driver.navigate().to(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
