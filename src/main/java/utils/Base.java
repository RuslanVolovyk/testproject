package utils;

public class Base {

    public static final String DRIVER_NAME = (String) new JSONReader().readJson("/config").get("browser.driver");
    public static long TIME_OUT = (Long) new JSONReader().readJson("/config").get("element.wait");
    public static final long DELAY = (Long) new JSONReader().readJson("/config").get("element.poling");
    public static final long EXIST = (Long) new JSONReader().readJson("/config").get("element.exist");
    public static final String DRIVER_VERSION = new JSONReader().readJson("/config").get("driver.version").toString();
    public static final String DRIVER_PATH = (String) new JSONReader().readJson("/config").get("driver.path");
}
