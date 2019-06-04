package core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

class JSWaiter extends Element {

    private WebDriverWait jsWait = new WebDriverWait(driver, EXIST);
    private JavascriptExecutor jsExec = (JavascriptExecutor) driver;

    //Wait for Angular Load
    private void waitForAngularLoad() {
        String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";
        //Wait for ANGULAR to load
        ExpectedCondition<Boolean> angularLoad = driver -> {
            assert driver != null;
            return Boolean.valueOf(((JavascriptExecutor) driver)
                    .executeScript(angularReadyScript).toString());
        };
        //Get Angular is Ready
        boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());
        //Wait ANGULAR until it is Ready!
        if (!angularReady) {
            jsWait.until(angularLoad);
        }
    }

    //Wait Until JS Ready
    void waitUntilJSReady() {
        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").toString().equals("complete");
        };
        //Get JS is Ready
        boolean jsReady = jsExec.executeScript("return document.readyState").toString().equals("complete");
        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("JS in NOT Ready!");
            //Wait for Javascript to load
            jsWait.until(jsLoad);
        }
    }


    //Wait Until Angular and JS Ready
    private void waitUntilAngularReady() {
        //First check that ANGULAR is defined on the page. If it is, then wait ANGULAR
        Boolean angularUnDefined = (Boolean) jsExec.executeScript("return window.angular === undefined");
        if (!angularUnDefined) {
            Boolean angularInjectorUnDefined = (Boolean) jsExec.executeScript("return angular.element(document).injector() === undefined");
            if (!angularInjectorUnDefined) {
                //Pre Wait for stability (Optional)
                sleep(50);
                //Wait Angular Load
                waitForAngularLoad();
                //Wait JS Load
                waitUntilJSReady();
                //Post Wait for stability (Optional)
                sleep(50);
            }
        }
    }

    //Wait Until JQuery Angular and JS is ready
    void waitJQueryAngular() {
        try {
            waitUntilAngularReady();
        } catch (Exception ex) {
            System.out.println("JS exception: " + ex.getMessage());
        }
    }

    private static void sleep(Integer seconds) {
        long secondsLong = (long) seconds;
        try {
            Thread.sleep(secondsLong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
