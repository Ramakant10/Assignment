package Calc.Assignment;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Calc.Pages.Calculator_Function;

/**
 * Unit test for simple App.
 */

public class AppTest 

{
	static WiniumDriver windowsDriverclient;

	public static void main(String []args) throws MalformedURLException{

		try {
			FileInputStream fileInput = null;
			fileInput = new FileInputStream(System.getProperty("user.dir")+"\\calculator.properties");
			Properties cal_propery = new Properties();
			cal_propery.load(fileInput);
			File classPathRoot = new File (System.getProperty("user.dir"));
			String foldername = classPathRoot.getAbsolutePath();
			Runtime.getRuntime().exec("cmd /c start "+foldername+"\\Winium.Desktop.Driver.exe");
			DesktopOptions options = new DesktopOptions();
			options.setApplicationPath("C:\\Windows\\System32\\calc.exe");
			String ipAddress = cal_propery.getProperty("IPAddress");
			windowsDriverclient = new WiniumDriver(new URL(ipAddress), options);
			Calculator_Function calculator_Function = PageFactory.initElements(windowsDriverclient, Calculator_Function.class);
			Thread.sleep(1000);
			calculator_Function.Calc_Navigation();
			Thread.sleep(1000);
			calculator_Function.Calc_Scientific();
			Thread.sleep(2000);
			calculator_Function.Calc_History();

			String num1 = cal_propery.getProperty("Number_1");
			String num2 = cal_propery.getProperty("Number_2");
			String operator = cal_propery.getProperty("Operator");
			String power = cal_propery.getProperty("Power");
			Integer finalNumber1 = Integer.parseInt(num1);
			Integer finalNumber2 = Integer.parseInt(num2);
			Integer Intpower = Integer.parseInt(power);
			calculator_Function.Calc_EditMode(num1);
			Thread.sleep(1000);
			if(cal_propery.getProperty("Operator").equalsIgnoreCase("Square"))
				for(int j = 1; j<= Intpower; j++)
				{
					calculator_Function.Calc_Operator(windowsDriverclient,operator);
					Thread.sleep(1000);
				}else {
					calculator_Function.Calc_Operator(windowsDriverclient,operator);
					Thread.sleep(1000);
					calculator_Function.Calc_EditMode(num2);
					Thread.sleep(1000);	
				}
			calculator_Function.Calc_Equal(); 
			String Result = calculator_Function.Calc_Result();
			Result = Result.replace(",", "");
			Integer ActualResult = Integer.parseInt(Result);

			switch (operator)
			{
			case "Minus":

				Integer Expected_Subtraction = finalNumber1 - finalNumber2; 
				System.out.println("Expected is "+Expected_Subtraction+" where actual is "+ActualResult);
				Assert.assertEquals(ActualResult, Expected_Subtraction,"tested");
				break;

			case "Plus":
				Integer Expected_sum = Integer.sum(finalNumber1, finalNumber2);
				System.out.println("Expected is "+Expected_sum+" where actual is "+ActualResult);
				Assert.assertEquals(ActualResult, Expected_sum,"tested");
				break;

			case "Modulo":
				Expected_sum = finalNumber1%finalNumber2;
				System.out.println("Expected is "+Expected_sum+" where actual is "+ActualResult);
				Assert.assertEquals(ActualResult, Expected_sum,"tested");
				break;

			case "Square":
				int Expected_result =0;
				for (int i = 1; i<=Intpower ; i++)
				{
					Expected_result = (int) Math.pow(finalNumber1, 2);
					finalNumber1 = Expected_result;
				}
				System.out.println("exp is :" + Expected_result);
				if(ActualResult.equals(Expected_result))
					System.out.println("Expected is "+Expected_result+" where actual is "+ActualResult);
				//Assert.assertEquals(ActualResult, Expected_result,"tested");
				break; 
			}
			calculator_Function.Calc_Close();
			Runtime.getRuntime().exec("wmic process where \"name='Winium.Desktop.Driver.exe'\" delete");
		} catch (Exception e) {
			e.getMessage();

		}
	}
}
