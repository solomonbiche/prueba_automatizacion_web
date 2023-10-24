package auto.framework.selenium.utils;

import auto.framework.selenium.annotations.LazyComponent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

@LazyComponent
public class BrowserOps {

    private final String download = "src"+ File.separator
            +"main"+File.separator
            +"resources"+File.separator
            +"download";

    public ChromeOptions getChromeOptions() {
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.default_directory", this.download);

        ChromeOptions co = new ChromeOptions();
        co.setAcceptInsecureCerts(true);
        co.setExperimentalOption("prefs", prefs);
        co.addArguments("--ignore-certificate-errors","disable-infobars", "--disable-notifications");
        //co.addArguments("--headless", "--disable-gpu","--window-size=1920,1200", "--disable-dev-shm-usage", "--no-sandbox");
        return co;
    }

    public DesiredCapabilities getAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browsername", "chrome");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
        return capabilities;
    }

    public UiAutomator2Options getUiAutomator2Options(){
        UiAutomator2Options options = new UiAutomator2Options()
                .setAvd("pixel3-emulator")
                .withBrowserName("chrome");
                /*.setApp("/home/myapp.apk");*/
        return options;
    }

    public FirefoxOptions getFireFoxOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("devtools.console.stdout.content", true);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);

        firefoxOptions
            .setProfile(firefoxProfile)
            .setCapability("moz:loggingPrefs", logPrefs);
        return firefoxOptions;
    }
}
