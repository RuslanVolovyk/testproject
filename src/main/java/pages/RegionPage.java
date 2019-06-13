package pages;

import org.openqa.selenium.By;

public class RegionPage {

    private By StPetersburgRegion = By.xpath("//span[text()='г. Санкт-Петербург']");

    public By getStPetersburgRegion() {
        return StPetersburgRegion;
    }
}
