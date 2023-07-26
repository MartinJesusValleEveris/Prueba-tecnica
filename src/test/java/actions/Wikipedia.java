package actions;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import runner.TestRunner;

import java.io.File;

public class Wikipedia {


    public boolean checkDate(String date) {
        WebElement textAutomation = TestRunner.driver.findElement(By.xpath("//p[33]"));
        if (textAutomation.getText().contains(date)){
            return true;
        }else return false;
    }

    public void takeSreenshoot(){

        try {
            File SrcFile = ((TakesScreenshot) TestRunner.driver).getScreenshotAs(OutputType.FILE);
            //Move image file to new destination
            File DestFile = new File("target/WikipediaScreenshot.png");
            //Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);
        }catch (Exception e ){
            e.printStackTrace();
        }

    }
}
