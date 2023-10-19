package com.business.zee;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.Hipipages.HipiChalesPages;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.DBObject;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.util.JSON;
import com.relevantcodes.extentreports.LogStatus;


import bsh.ParseException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.path.json.JsonPath;
import net.bytebuddy.dynamic.scaffold.MethodGraph.NodeList;
import org.json.*;
import groovy.json.JsonSlurper;


public class Sample {
	
	public static boolean SwipeUntilFindElement(By locator, String direction) throws Exception {

		boolean checkLocator, eletFound = false;
		if (direction.equalsIgnoreCase("UP")) {
			for (int i = 1; i < 25; i++) {
				PartialSwipe("UP", 1);
				waitTime(5000);
				checkLocator = verifyIsElementDisplayed(locator);
				if (checkLocator) {
					eletFound = true;
					break;
				}
			}
		}
		return eletFound;
	}
	public static boolean verifyIsElementDisplayed(By by) {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		List<AndroidElement> list = driver.findElements(by);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (list.size() == 0) {
			return false;
		} else {
			return list.get(0).isDisplayed();
		}
	}
	public static void PartialSwipe(String direction, int count) {
		TouchAction touchAction = new TouchAction(driver);
		String dire = direction;

		try {

			if (dire.equalsIgnoreCase("LEFT")) {

				for (int i = 0; i < count; i++) {
					Dimension size = driver.manage().window().getSize();
					int startx = (int) (size.width * 0.4);
					int endx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					// getDriver().swipe(startx, starty, endx, starty, 1000);
					touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
					//						logger.info("Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
					//						extent.extentLogger("SwipeLeft",
					//								"Swiping screen in " + " " + dire + " direction" + " " + (i + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("RIGHT")) {

				for (int j = 0; j < count; j++) {
					Dimension size = driver.manage().window().getSize();
					int endx = (int) (size.width * 0.4);
					int startx = (int) (size.width * 0.1);
					int starty = size.height / 2;
					touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(endx, starty)).release().perform();
					//						logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					//						extent.extentLogger("SwipeRight",
					//								"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
				}
			} else if (dire.equalsIgnoreCase("UP")) {

				for (int j = 0; j < count; j++) {
					Dimension size = driver.manage().window().getSize();
					int starty = (int) (size.height * 0.75);
					int endy = (int) (size.height * 0.05);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, starty))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx, endy)).release().perform();
					//						logger.info("Swiping screen in " + dire + " direction" + " " + (j + 1) + " times");
					//						extent.extentLogger("SwipeUp",
					//								"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");

				}
			}else if (dire.equalsIgnoreCase("DOWN")) {

				for (int j = 0; j < count; j++) {

					Dimension size = driver.manage().window().getSize();
					int starty = (int) (size.height * 0.8);
					int endy = (int) (size.height * 0.5);
					int startx = size.width / 2;
					touchAction.press(PointOption.point(startx, endy))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(startx, starty)).release().perform();
					//						logger.info("Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					//						extent.extentLogger("SwipeDown",
					//								"Swiping screen in " + " " + dire + " direction" + " " + (j + 1) + " times");
					waitTime(5000);
				}
			}

		} catch (Exception e) {
			//				logger.error(e);

		}
	}
	public static void longPress(By element) throws Exception {
		AndroidTouchAction touch = new AndroidTouchAction(driver);
		touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(findElement(element))))
		.release().perform();
	}
	public static void Back(int x) {

		for (int i = 0; i < x; i++) {
			driver.navigate().back();
			//					logger.info("Back button is tapped");
			//					extent.extentLogger("Back", "Back button is tapped");
			waitTime(2000);
		}
	}



	public static void type(By byLocator, String text, String FieldName) {

		try {
			WebElement element = findElement(byLocator);
			element.sendKeys(text);
			//			Actions a = new Actions(getDriver());
			//			a.sendKeys(text);
			//			a.perform();
			text = text.split("\n")[0];
			//			logger.info("Typed the value " + text + " into " + FieldName);
			//			extent.extentLogger("", "Typed the value " + text + " into " + FieldName);
		} catch (Exception e) {
			//			logger.error(e);
		}
	}


	public static boolean verifyElementPresentAndClick(By byLocator, String validationtext) throws Exception {

		try {
			WebElement element = driver.findElement(byLocator);
			//			softAssert.assertEquals(element.isDisplayed(), true, "" + validationtext + " " + " is displayed");
			//			logger.info("" + validationtext + " " + "is displayed");
			//			extent.extentLogger("checkElementPresent", "" + validationtext + " is displayed");
						findElement(byLocator).click();
			//			logger.info("Clicked on " + validationtext);
			//			extent.extentLoggerPass("click", "Clicked on " + validationtext);
			return true;
		} catch (Exception e) {
			//			e.printStackTrace();
			//			softAssert.assertEquals(false, true, "Element" + validationtext + " " + " is not visible");
			//			logger.error("Element " + validationtext + " " + " is not visible");
			//			extent.extentLoggerFail("checkElementPresent", "" + validationtext + " is not displayed");
			//			screencapture();
			//			softAssert.assertAll();
			return false;
		}
	}

	public static void click(By byLocator, String validationtext) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			element.click();
			//			logger.info("Clicked on " + validationtext);
			//			extent.extentLogger("click", "Clicked on " + validationtext);
		} catch (Exception e) {
			//			System.out.println("Hi");
			//			screencapture();
		}
	}

	public static boolean verifyElementExist(By byLocator, String str) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				//				extent.extentLogger("checkElementPresent", "" + str + " is displayed");
				//				logger.info("" + str + " is displayed");
				return true;
			}
		} catch (Exception e) {
			//			extent.extentLogger("checkElementPresent", "" + str + " is not displayed");
			//			logger.info(str + " is not displayed");
			return false;
		}
		return false;
	}
	public static boolean waitUntilElementDisplayed (By locator, int countWait) throws Exception {
		int iterator=0;
		while (verifyElementDisplayed(locator)== false) {
			if (iterator <= countWait) {
				waitTime(1000);
				iterator++;
			}
			else {
				return false;
			}
		}
		return true;
	}

	public static void waitTime(int x) {

		try {
			Thread.sleep(x);
		} catch (Exception e) {

		}
	}
	public static boolean verifyElementDisplayed(By byLocator) throws Exception {
		try {
			WebElement element = findElement(byLocator);
			if (element.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	public static WebElement findElement(By byLocator) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,5);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		return element;
	}


	static	List<String> Events=new ArrayList<String>();
	//static String[]  ListofEvents={"launch","user_video_start","view","completed"};
	static List<String> ListofEvents= new ArrayList<String>();
	static	AndroidDriver<AndroidElement> driver;

	public static void LaunchHipiApp() throws MalformedURLException {

		//		startServer();

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
		//			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		capabilities.setCapability(MobileCapabilityType.UDID, "3200c679bc1396c7");
		//			capabilities.setCapability(MobileCapabilityType.APP, "D:\\App\\app-production-release (130).apk");

		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.zee5.hipi");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.zee5.hipi.presentation.splash.SplashActivity");

		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), capabilities);

		System.out.println(" App Launch Successfully");
	}



	public static InetAddress localhost;
	public static String ipAdress = "";	

	public static void openCharles(String[] args) {
		try {
			// print a message
//			System.out.println("Executing notepad.exe");
			// create a process and execute notepad.exe
			//		         Process process = Runtime.getRuntime().exec("notepad.exe");
			Process process = Runtime.getRuntime().exec("\"C:\\Program Files\\Charles\\Charles.exe\"");
			localhost = InetAddress.getLocalHost();
			ipAdress = localhost.getHostAddress().trim();
			System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
			Thread.sleep(20000);

			Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/session/clear");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/recording/start");

			// print another message
			System.out.println("Notepad should now open");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String charleslogsName = "";
	public static String charlesName = "";
	public static String filePath = "";
	
	static String filePathxml = System.getProperty("user.dir") + "/Excel_Reports/" + charlesName;
	public static void saveandcloseCharles(String fileName) {
		try {
			DateFormat date = new SimpleDateFormat("ddmmyyHHMMss");
			Date date2 = new Date();
			String date1 = date.format(date2);

			charlesName = fileName + date1 + ".xml";
			System.out.println(charlesName);
//			charleslogsName = fileName + date1 + ".chls";
//			System.out.println(charleslogsName);
			String filePathxml1 = System.getProperty("user.dir") + "/Excel_Reports/" + charlesName;
//			String filePathxml1 =  "C:\\Charles\\" + charlesName;
			System.out.println(filePathxml1);
			String filePathlogs = System.getProperty("user.dir") + "/Excel_Reports/" + charleslogsName;

			ipAdress = localhost.getHostAddress().trim();
			Thread.sleep(5000);
			Runtime.getRuntime().exec("curl -o " + filePathxml1 + " -x http://" + ipAdress + ":8888 http://control.charles/session/export-xml");
			Thread.sleep(5000);
			//			Runtime.getRuntime().exec("curl -o " + filePathlogs + " -x http://" + ipAdress + ":8888 http://control.charles/session/download");
			Thread.sleep(10000);
			Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/quit");
			Thread.sleep(2000);
			System.out.println("Close Charles");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void quitApp(){

		driver.quit();
		System.out.println("Quit App");
	}

	public static File Newest() {

		File fl = new File("C:/Users/User/Desktop/Prac/zee/Excel_Reports");
		File choice = null;
		if (fl.listFiles().length>0) {
			File[] files = fl.listFiles(new FileFilter() {
				public boolean accept(File file) {
					return file.isFile();
				}
			});
			long lastMod = Long.MIN_VALUE;

			for (File file : files) {
				if (file.lastModified() > lastMod) {
					choice = file;
					lastMod = file.lastModified();
				}
			}
			System.out.println(choice);
		}
		return choice;
	}   


	public static void MappingCharles() throws IOException
	{

		File file = Newest();

		BufferedReader br = new BufferedReader(new FileReader(file));

		JSONObject json=XML.toJSONObject(br); 
		String nn = json.toString();
		System.out.println(nn);
		List<Object> jsonList= JsonPath.from(nn).getList("charles-session.transaction");
		//		System.out.println(jsonList);
//		System.out.println(jsonList.size());
		Map<String, String> map = new HashMap<String, String>();

		for(int i=0;i<jsonList.size();i++)
		{
			String jsonPathNew = "charles-session.transaction[" + i + "].request.body[1]";
//			System.out.println("************" +jsonPathNew + "***********");
			String str = JsonPath.from(nn).get(jsonPathNew);
			str = str.substring(1, str.length() - 1);
			String[] keyValuePairs = str.split(",");
			String[] entry = null;
			for (int j = 0; j < keyValuePairs.length; j++)
			{
				entry = keyValuePairs[j].split(":");
				map.put(entry[0].trim(), entry[1].trim());
			}
			Set set = map.entrySet();
			Iterator iew =set.iterator();

			while(iew.hasNext()) {
				Map.Entry me = (Map.Entry)iew.next();
				if(me.getKey().toString().contains("event")) {
					System.out.println(me.getKey() + ": " + me.getValue());
					String events = me.getValue().toString().replace("\"", "");
					Events.add(events);
				}

				//					 System.out.print(me.getKey() + ": ");
				//					 System.out.print(me.getValue());
			}
		}

		
		ListofEvents.add("launch");
		ListofEvents.add("user_video_start");
		ListofEvents.add("completed");
		ListofEvents.add("view");
		ListofEvents.add("like");
		ListofEvents.add("unlike");
		ListofEvents.add("skip");
		ListofEvents.add("user_video_end");
		ListofEvents.add("comment");
		ListofEvents.add("share");
		ListofEvents.add("favorite");
		ListofEvents.add("unfavorite");
		ListofEvents.add("download");
		ListofEvents.add("shop_button_click");
		ListofEvents.add("shoppable_product_click");
		ListofEvents.add("No Decision");
		ListofEvents.add("follow");
		ListofEvents.add("unfollow");
		ListofEvents.add("notinterested");
		ListofEvents.add("videoreported");
		ListofEvents.add("search_event");
		ListofEvents.add("search_result_click_event");
		ListofEvents.add("shoppable_product_impression");
		
		
	     HashSet<String> removeDuplicateEvent=new LinkedHashSet<String>();
	     removeDuplicateEvent.addAll(Events);
	     Events.clear();
	     Events.addAll(removeDuplicateEvent);
       System.out.println(Events.size());
		for(int i=0;i<ListofEvents.size();i++)
		{
			if(Events.contains(ListofEvents.get(i))) {
				System.out.println(ListofEvents.get(i)+" is triggered");
			}else {
				System.out.println(ListofEvents.get(i)+" is not triggered");
			}
			
		}
//	       System.out.println("event size"+ Events.size());
//	       System.out.println("custom events size"+ ListofEvents.size());
	       
	       
	       
		
         
//		String launch = "launch";
//		String videostart = "user_video_start";
//		String completed = "completed";
//		String view = "view";
//		String like = "like";
//		String unlike = "unlike";
//		String skip = "skip";
//		String user_video_end = "user_video_end";
//		String comment = "comment";
//		String share = "share";
//		String favorite = "favorite";
//		String unfavorite = "unfavorite";
//		String download = "download";
//		String shop_button_click = "shop_button_click";
//		String shoppable_product_click = "shoppable_product_click";
//		String noDecision = "No Decision";
//		String follow = "follow";
//		String unfollow = "unfollow";
//		String notinterested =  "notinterested";
//		String videoreported =  "videoreported";
//		String search_event = "search_event";
//		String search_result_click_event = "search_result_click_event";
//		String shoppable_product_impression = "shoppable_product_impression";
//		
//		
//		
//		
//		
//		
//
//		for(int i=0;i<Events.size();i++)
//		{
//			String events = Events.get(i).replace("\"", "");
//
////			System.out.println(Events.get(i));
////			System.out.println(events);
//		
//			if (events.equals(launch) | events.equals(videostart)|
//					events.equals(noDecision) | events.equals(view) | 
//					events.equals(completed) | events.equals(like) | 
//					events.equals(unlike) | events.equals(comment) | 
//					events.equals(share) |  events.equals(follow) | 
//					events.equals(unfollow) | events.equals(notinterested) | 
//					events.equals(favorite) | events.equals(unfavorite) |
//					events.equals(videoreported) | events.equals(search_event) | 
//					events.equals(search_result_click_event) | events.equals(shop_button_click) |
//					events.equals(shoppable_product_impression) | events.equals(shoppable_product_click) ){ 
//					System.out.println(Events.get(i)+" is trrigred");
//				
//			}else {
//				System.out.println(Events.get(i)+" is not trrigred");
//		
//			}
//		}
	}	
	
	
	
		
/*public static void ChalsTCs() throws Exception {
		
		waitUntilElementDisplayed(HipiChalesPages.objMaybeLater, 1);
		if(verifyElementExist(HipiChalesPages.objMaybeLater, "Maybe Later"))
		{
			click(HipiChalesPages.objMaybeLater, "Maybe Later");
			System.out.println("Update Popup is displayed");
		}else{
			System.out.println("Update Popup is not displayed");
		}

//		waitTime(60000);
		verifyElementPresentAndClick(HipiChalesPages.objPlayerScreen,"Player Screen");
		System.out.println("Click on Player Screen");
		verifyElementPresentAndClick(HipiChalesPages.objLikeICON, "Like Icon");
		System.out.println("Click on Like Icon");
		waitTime(5000);
		verifyElementPresentAndClick(HipiChalesPages.objLikeICON, "Unlike Icon");
		System.out.println("Click on Unlike Icon");
		waitTime(5000);
		SwipeUntilFindElement(HipiChalesPages.objSaveLook, "UP");
		click(HipiChalesPages.objSaveLook, "Save look Button");
		System.out.println("Click on Save look Button");
		waitUntilElementDisplayed(HipiChalesPages.objProductImage, 5);
		verifyElementPresentAndClick(HipiChalesPages.objProductImage, "Product Image");
		System.out.println("Click on Product Image");
		waitTime(10000);
		Back(1);
		click(HipiChalesPages.objShopButton, "Shop Button");
		System.out.println("Click on Shop Button");
		waitTime(4000);
		verifyElementPresentAndClick(HipiChalesPages.objProductImageThumbnail, "Product Image Thumbnail");
		System.out.println("Click on Product Image Thumbnail");
		waitTime(5000);
		Back(2);
//		verifyElementPresentAndClick(HipiChalesPages.objPlayerScreen,"Player Screen");
//		verifyElementPresentAndClick(HipiChalesPages.objFollowButton, "Follow Button");
//		System.out.println("Click on Follow Button");
//		waitTime(5000);
//		verifyElementPresentAndClick(HipiChalesPages.objCommentICON, "Comment Icon");
//		System.out.println("Click on Comment Icon");
//		waitUntilElementDisplayed(HipiChalesPages.objAddAComment, 10);
//		
//		click(HipiChalesPages.objAddAComment, "Add A Comment");
//		System.out.println("Click on Add A Comment");
//		waitTime(3000);
//		type(HipiChalesPages.objAddAComment, "Hi", "Add A Comment");
//		System.out.println("Type Comment");
//		click(HipiChalesPages.objCommentPostIcon, "Comment Post Icon");
//		waitTime(2000);
//		verifyElementPresentAndClick(HipiChalesPages.objShareICON ,"Share icon");
//		System.out.println("Click on Share icon");
//		waitTime(5000);
//		Back(1);
//		longPress(HipiChalesPages.objPlayerScreen);
//		
//		waitTime(2000);
//		verifyElementPresentAndClick(HipiChalesPages.objAddToFavourites, "Add To Favourites");
//		System.out.println("Click on Add To Favourites");
//		waitTime(2000);
//		verifyElementPresentAndClick(HipiChalesPages.objAddToFavourites, "Add To Favourites");
//		System.out.println("Click on Add To Favourites");
//		waitTime(2000);
//		verifyElementPresentAndClick(HipiChalesPages.objSaveVideo, "Save Video");
//		System.out.println("Click on Save Video");
//		waitTime(2000);
//		if (verifyElementExist(HipiChalesPages.objCreateAllow, "Allow")) {
////		logger.info("Permission dialog:"+ "Allow ZEE5 to take pictures and record video?");
//		
//		click(HipiChalesPages.objCreateAllow, "Allow button clicked");
//		System.out.println("Click on Allow button clicked");
////		logger.info("Allow ZEE5 to take pictures and record video-"+ "Allow button clicked");
//		}else {
////			logger.info("Permission dialog popup not displayed");
//		}
//		waitTime(30000);
//		waitTime(2000);
//		verifyElementPresentAndClick(HipiChalesPages.objNotInterested, "Not Interested");
//		System.out.println("Click on Not Interested");
	
	} */
	


	public static void main(String[] arg) throws IOException{

//		openCharles(arg); //Step 1
//		
//		LaunchHipiApp();  //Step 2
//		
		//ChalsTCs();		  //Step 3
//		
//		waitTime(50000);  
//		
//		saveandcloseCharles(charlesName);  //Step 4
//		
//		quitApp(); //Step 5
//		
		MappingCharles();  //Step 6
		
		
		
	}
	
	

}

