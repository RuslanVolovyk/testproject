package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.PropertyResourceBundle;

public class Urls {

    public static String getBaseGateUrl() {
        String mainConfig = "D:\\ProjectJava\\com.tinkoff.autotest\\src\\main\\resources\\apiConfig.properties";
        try {
            return new PropertyResourceBundle(new FileReader(mainConfig)).getString("BaseUrlKordamentha") + "api";
        } catch (IOException e) {
            throw new NoSuchElementException("File mainConfig: " + mainConfig + " not found");
        }
    }

    public static String getAccountCheckPasswordSiignInUrl() {
        return getBaseGateUrl() + "/account/checkPasswordSignIn";
    }
}
