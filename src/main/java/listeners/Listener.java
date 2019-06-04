package listeners;

import core.InitialDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

public class Listener implements ITestListener, ISuiteListener {

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        InitialDriver.getInstance().destroy();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot();
        InitialDriver.getInstance().destroy();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        InitialDriver.getInstance().destroy();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private void attachScreenshot() {
        WebDriver driver = InitialDriver.getInstance().driver;
        if (driver == null) {
            System.out.println("Driver not found");
        } else {
            Allure.getLifecycle().addAttachment("Failure screenshot", "image/png", "", ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        }
    }

    @Override
    public void onStart(ISuite suite) {
    }

    @Override
    public void onFinish(ISuite suite) {
        InitialDriver.getInstance().destroy();
    }
}
