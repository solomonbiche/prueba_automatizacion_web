package auto.framework.selenium.utils;

import auto.framework.selenium.annotations.LazyComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;

@LazyComponent
public class LogUtil {
    public static LogEntries getLogs(WebDriver driver) {
        return driver
            .manage()
            .logs()
            .get(LogType.BROWSER);
    }

    public void isLoginErrorLog(WebDriver driver, String logtext) {
        //Check logs (works only Chrome and Edge)
        LogEntries logEntries = driver
            .manage()
            .logs()
            .get(LogType.BROWSER);
        Assert.assertTrue(logEntries
            .getAll()
            .stream()
            .anyMatch(logEntry -> logEntry
                .getMessage()
                .contains(logtext)));
    }
}
