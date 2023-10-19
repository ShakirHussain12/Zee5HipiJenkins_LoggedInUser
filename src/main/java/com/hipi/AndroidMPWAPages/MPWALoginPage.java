package com.hipi.AndroidMPWAPages;

import org.openqa.selenium.By;

public class MPWALoginPage {

	
	public static By objUsePhoneOrEmail = By.xpath("(//*[@class='flex justify-center items-center text-sm md:text-base w-full text-gray-600 font-semibold'])[1] | //*[contains(@text,'Use P')]");

	public static By objEmailOption = By.xpath("//*[@class=' py-2 w-1/2 flex justify-center align-center cursor-pointer']");

	public static By objEmailIdField = By.xpath("//*[@placeholder='Email address'] | //*[@name='phone'] | //*[@resource-id='email']");

	public static By objPhoneNumberTextField = By.xpath("(//*[@name='phone'])[1]");

	public static By objPasswordField = By.xpath("//*[@placeholder='Password'] | //*[@class='android.widget.EditText' and ./parent::*[@id='__next']]");

	//public static By objLoginBtn = By.xpath("//*[@text=' Log in']");

	public static By objLoginBtn = By.xpath("(//*[contains(@text,'Log in')])[2]");
	
// Log in
	public static By objProceed = By.xpath("//*[@text=' Proceed'] | //*[@text='Proceed']");

	public static By objLoginWithPassword = By.xpath("(//*[@class='cursor-pointer'])[2] | //*[contains(@text,'Login with password')]");

	public static By objLoginORSignup = By.xpath("//*[contains(@text,'Login')]");

	
	public static By objCloseIcon = By.xpath("//*[@resource-id='close_button_image']");
}
