package auto.framework.selenium.pages.google;

import auto.framework.selenium.annotations.LazyComponent;
import auto.framework.selenium.pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Value;

@LazyComponent
public class GoogleMain extends BasePage<GoogleMain> {
    @Value("${application.url}")
    private String baseURL;

    @FindBy(how = How.XPATH, using = "//img[@alt='Google']")
    private WebElement googleImg;

    @FindBy(how = How.ID, using = "APjFqb")
    private WebElement textArea;

    @FindBy(how = How.ID, using = "L2AGLb")
    private WebElement acceptAll;

    @FindBy(how = How.XPATH, using = "//h3[(text() = 'Automatización - Wikipedia, la enciclopedia libre' or . = 'Automatización - Wikipedia, la enciclopedia libre')]")
    private WebElement wikiLinkAuto;
    @FindBy(how = How.XPATH, using = "//div[@id='mw-content-text']/div/p[33]")
    private WebElement dateFirstProcess;

    //*********Page Methods*********

    public GoogleMain goToURL(){
        driver.get(baseURL);
        return this;
    }
    public GoogleMain searchWithText(String text) {
    return write(textArea,text).keyboard(textArea, Keys.ENTER);

    }

    @Override public boolean isAt() {
        return isDisplayed(googleImg);
    }

    public GoogleMain clickAcceptAll() {
        return click(acceptAll);
    }

    public GoogleMain clickWikiLink() {
        return click(wikiLinkAuto);
    }

    public GoogleMain clickFindDateProcess(){

        return click(dateFirstProcess);
    }



}

