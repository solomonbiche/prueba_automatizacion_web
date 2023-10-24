package auto.framework.selenium.pages;

import auto.framework.selenium.utils.LogUtil;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BasePage <P>{
    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @Autowired
    protected JavascriptExecutor javascriptExecutor;

    @Autowired
    protected LogUtil logUtil;

    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isAt();

    /**
     * These Methods perform a WebDriver wait for the presence or visibility of an element depending on the type of
     * the type T of the attribute elementAttr. Given WebDriverWait returns the element/elements it is waiting for, we
     * can use this in other basic methods such as click or sendkeys to make sure the element can be interacted with.
     * @param elementAttr reference to the element or the element itself.
     * @param <T> By or WebElement.
     * @return reference to WebElement we waited for.
     */

    private <T> WebElement waitElement(T elementAttr) {
        if (elementAttr
            .getClass()
            .getName()
            .contains("By")) {
            return this.wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            return this.wait.until(ExpectedConditions.elementToBeClickable((WebElement) elementAttr));
        }
    }

    protected  <T> List<WebElement> waitElements(T elementAttr) {
        if (elementAttr
            .getClass()
            .getName()
            .contains("By")) {
            return this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
        } else {
            return this.wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) elementAttr));
        }
    }

    /**
     * Click of an element using waitElement to make sure element can be interacted with.
     * @param elementAttr element to be clicked.
     * @param <T> type can be By or WebElement.
     * @return this
     */
    protected <T> P click(T elementAttr) {
        System.out.println("Clicking element "+elementAttr.toString());
        try{
            waitElement(elementAttr).click();
        } catch (org.openqa.selenium.StaleElementReferenceException e){
            waitElement(elementAttr).click();
        }
        return (P)this;
    }

    /**
     * JavaScript click using a By reference to the element and a visibility WebDriverWait.
     * @param by reference to the element.
     */
    protected void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", this.wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    /**
     * Writes text into the element.
     * @param elementAttr reference to the element.
     * @param text text to be written.
     * @param <T> type can be By or WebElement.
     * @return this
     */
    protected <T> P write(T elementAttr, String text) {
        if (text == null || text.equalsIgnoreCase("")){
            this.keyboard(elementAttr, Keys.ENTER);
        }else{
            waitElement(elementAttr).sendKeys(text);
        }
        return (P)this;
    }

    /**
     * Sends keyboard press to an input element.
     * @param elementAttr reference to the element.
     * @param k Key to be sent.
     * @param <T> type can be By or WebElement.
     * @return this
     */
    protected <T> P keyboard(T elementAttr, Keys k) {
        waitElement(elementAttr).sendKeys(k);
        return (P)this;
    }

    /**
     * Gets text from referenced element.
     * @param elementAttr reference to the element to get text from.
     * @param <T> type can be By or WebElement.
     * @return text from the referenced element.
     */
    protected <T> String readText(T elementAttr) {
        return waitElement(elementAttr).getText();
    }

    protected <T> boolean isDisplayed(T elementAttr){
        return waitElement(elementAttr).isDisplayed();
    }

    @SneakyThrows
    protected <T> String readTextErrorMessage(T elementAttr) {
//        Thread.sleep(2000); //This needs to be improved.
        return waitElement(elementAttr).getText();
    }

    //Close popup if exists
    protected P handlePopup(By by) throws InterruptedException {
        waitElements(by);
        List<WebElement> popup = driver.findElements(by);
        if (!popup.isEmpty()) {
            popup
                .get(0)
                .click();
            Thread.sleep(200);
        }
        return (P)this;
    }

    protected <T> P pause() throws InterruptedException {
        Thread.sleep(1000);
        return (P)this;
    }
}
