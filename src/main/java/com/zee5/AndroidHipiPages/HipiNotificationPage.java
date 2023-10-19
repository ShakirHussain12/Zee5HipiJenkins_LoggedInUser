package com.zee5.AndroidHipiPages;

import org.openqa.selenium.By;

public class HipiNotificationPage {

	
	public static By objNotificationscreen= By.xpath("//*[contains(@resource-id,'recyclerView')]");
	
	/////////Shakir/////////
	public static By objNotificationPage = By.xpath("//*[@resource-id='com.zee5.hipi:id/tabTitleTxt' and (@text='Updates' or @text='Messages')]");
	public static By objNotificationBtn = By.xpath("//*[@resource-id='com.zee5.hipi:id/navigation_notification']");
	public static By objUpdatesTab = By.xpath("//*[@text='Updates']");
	public static By objMessagesTab = By.xpath("//*[@text='Messages']");
	public static By objMessages = By.xpath("//*[@resource-id='com.zee5.hipi:id/rootItem']");
	public static By objUnreadNotification = By.xpath("//*[@resource-id='com.zee5.hipi:id/badge_text_view']");
	
}
