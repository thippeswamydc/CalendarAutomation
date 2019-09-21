package org.demoqa;
/*
 * 
 * author: thippu
 * email: thippeswamydcts@gmail.com
 * 
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Executor 
{
	
	@Test(description="this method is start for all pages in this package")
	void start()
	{
		WebDriver chromeDriver=new ChromeDriver();
		DatePicker dpicker=new DatePicker(chromeDriver);
		dpicker.ChooseDate(20, 10, 1994);//My birthday Yo wish me Yo.:)
	}
}
