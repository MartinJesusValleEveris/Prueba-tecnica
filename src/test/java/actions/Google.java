package actions;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import runner.TestRunner;


public class Google {


    public void openUrl(String  url){
        TestRunner.driver.get(url);
        WebElement btnBack = TestRunner.driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        btnBack.click();
    }

    public void selectInputSearch(String word){
        WebElement inputSearch = TestRunner.driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
        inputSearch.click();
        inputSearch.sendKeys(word);
        inputSearch.sendKeys(Keys.ENTER);
    }

    public void selectWikipediaLink(){
        WebElement linkWikipedia= TestRunner.driver.findElement(By.xpath("//div[@class='yuRUbf']//a[contains(@href,'https://es.wikipedia.org/wiki/Automatizaci%C3%B3n')]"));
        linkWikipedia.click();
    }
}
