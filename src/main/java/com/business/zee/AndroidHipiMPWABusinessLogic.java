package com.business.zee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.UserType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import com.deviceDetails.DeviceDetails;
import com.driverInstance.DriverInstance;
import com.emailReport.GmailInbox;
import com.excel.Time_ExcelUpdate;
import com.extent.ExtentReporter;
import com.hipi.AndroidMPWAPages.MPWADiscoverPage;
import com.hipi.AndroidMPWAPages.MPWAHamburgermenuPage;
import com.hipi.AndroidMPWAPages.MPWAHashtagPage;
import com.hipi.AndroidMPWAPages.MPWAHomePage;
import com.hipi.AndroidMPWAPages.MPWALoginPage;
import com.hipi.AndroidMPWAPages.MPWAProfilePage;
import com.hipi.AndroidMPWAPages.MPWAShopPage;
import com.propertyfilereader.PropertyFileReader;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.zee5.AndroidHipiPages.HipiDiscoverPage;
import com.zee5.AndroidHipiPages.HipiFeedPage;
import com.zee5.AndroidHipiPages.HipiHashtagDetailPage;
import com.zee5.AndroidHipiPages.HipiLoginPage;
import com.zee5.AndroidHipiPages.HipiProfilePage;
import com.zee5.AndroidHipiPages.HipiSettingsPage;
import com.zee5.AndroidHipiPages.HipiShopPage;
import com.zee5.Applicaster.HIPI.AMDHomePage;
import com.zee5.Applicaster.HIPI.AMDLoginScreen;
import com.zee5.Applicaster.HIPI.HipiHomePage;
import com.zee5.iOSmWebHipiPages.IOSHipiMwebDiscoverPage;
import com.zee5.iOSmWebHipiPages.IOSHipiMwebHomePage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AndroidHipiMPWABusinessLogic extends Utilities {

	public AndroidHipiMPWABusinessLogic(String Application) {
		new DriverInstance(Application);
		init();
	}
	public SoftAssert softAssert = new SoftAssert();
	private int timeout;

	/** Retry Count */
	private int retryCount;
	static ExtentReporter extent = new ExtentReporter();

	/** The Constant logger. */
	// final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver ;

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	@Override
	public int getTimeout() {
		return timeout;
	}

	String pUserType = getParameterFromXML("userType");

	/** Array of App */
	static ArrayList<String> AppMyProfile = new ArrayList<String>();
	static HashSet<String> contentsInWatchList = new HashSet<String>();
	static HashSet<String> contentsInReminders = new HashSet<String>();
	static ArrayList<String> AppSubscription = new ArrayList<String>();
	static ArrayList<String> AppTransaction = new ArrayList<String>();

	public com.business.zee.Zee5ApplicasterHipiNeoBusinessLogic Zee5ApplicasterHipiNeoBusinessLogic;

	@Override
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	GmailInbox gmail = new GmailInbox();

	String SVODEpisode = getParameterFromXML("SVODEpisode");

	public void init() {

		PropertyFileReader handler = new PropertyFileReader("properties/Execution.properties");
		setTimeout(Integer.parseInt(handler.getproperty("TIMEOUT")));
		setRetryCount(Integer.parseInt(handler.getproperty("RETRY_COUNT")));
		// logger.info("Loaded the following properties" + " TimeOut :" +
		// getTimeout() + " RetryCount :" + getRetryCount());
	}

	/**
	 * Function to Relaunch the driver
	 */
	public void relaunch(boolean clearData) throws Exception {
		HeaderChildNode("Relaunch the app");
		logger.info("Relaunching the application");
		extent.extentLogger("Relaunch", "Relaunching the application");
		waitTime(10000);
		getDriver().quit();
		relaunch = clearData;
		new com.business.zee.Zee5ApplicasterHipiNeoBusinessLogic("chrome");
		getURl("hipi.co.in");
		//https://zee5-hipi-web-dev2-alb-130221696.ap-south-1.elb.amazonaws.com/feed/for-you
//		Swipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objUnmute, 20);	
		
		waitTime(5000);
	}

	/**
	 * Function to quit the driver
	 */
	public void tearDown() {
		getDriver().quit();

		String pUserType = getParameterFromXML("userType");
		String RegisteredEmail = getParameterFromXML("RegisteredEmail");
		String RegisteredEmailPassword = getParameterFromXML("RegisteredEmailPassword");
		String UnRegisteredMobile = getParameterFromXML("UnRegisteredMobile");
		String RegisteredMobile = getParameterFromXML("RegisteredMobile");
		String RegisteredMobilePassword = getParameterFromXML("RegisteredMobilePassword");
		String PromoCode = getParameterFromXML("PromoCode");
		String NonsubscribedUserName = getParameterFromXML("NonsubscribedUserName");
		String NonsubscribedPassword = getParameterFromXML("NonsubscribedPassword");
		String SubscribedUserName = getParameterFromXML("SubscribedUserName");
		String SubscribedPassword = getParameterFromXML("SubscribedPassword");
		String FirstName = getParameterFromXML("FirstName");
		String LastName = getParameterFromXML("LastName");

	}

	// Retrieve the Mobile Device Name
	String getOEMName = DeviceDetails.OEM;

	public void Discover() throws Exception {

		extent.HeaderChildNode("Discover");

		System.out.println("Start Discover");
		waitTime(3000);
		Swipe("UP", 1);
		waitTime(4000);
		verifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		waitTime(4000);
		verifyElementPresentAndClick(MPWADiscoverPage.objSearchTab, "Search Tab");
		waitTime(3000);
		type(MPWADiscoverPage.objSearchTab, "shivangi", "Send text ");

	}
	public void SearchRelatedTcs(String userName,String TCsNumber) throws Exception {
		Time_ExcelUpdate.TestCaseIDNode(TCsNumber);
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.TestCaseSummaryNode1("Top 5 Suggestion Results");
		extent.HeaderChildNode("Top 5 Suggestion Results");
		waitTime(3000);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		waitTime(5000);
		TimeStampverifyElementPresentAndClick(MPWADiscoverPage.objSearchTab, "Search Tab");
		waitTime(3000);
		TimeStamptype(MPWADiscoverPage.objSearchTab, userName, "Type value");
		//		TimeStampclick(MPWADiscoverPage.objSearchTab, "Search Tab");
		//		TimeStamptype(MPWADiscoverPage.objSearchTab, " ", "Type value");

		waitTime(5000);
		boolean Listresults = verifyIsElementDisplayed(MPWADiscoverPage.objListOfSearchResult);
		//	  	extent.extentLoggerPass("", "");
		extent.extentLoggerPass("", "");
		if (Listresults) {
			int ListofsearchResults = getDriver().findElements(MPWADiscoverPage.objListOfSearchResult).size();
			if (ListofsearchResults == 0) {
				extent.extentLoggerFail("Verify share options", "Share Options are not available");
				logger.info("Share Options are not available");
			} else {
				for (int i = 1; i <= ListofsearchResults; i++) {
					String TotalsearchResults = getText(MPWADiscoverPage.objsearchResults(i));
					logger.info("List of Search results: \"" + TotalsearchResults + "\"");

					Time_ExcelUpdate.TestCaseSummaryNode1(TotalsearchResults);
					extent.extentLoggerPass("List of Search results ",
							"List of Search results : \"" + TotalsearchResults + "\"");

				}
			}
		} else {
			logger.info("List of Search results  are not displayed after clicking on Search button");
			//			extent.extentLoggerFail("Share through options screen",
			//					"Share Options are not displayed after clicking on Share CTA");
		}

		Time_ExcelUpdate.TestCaseIDNode("TC028");
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.TestCaseSummaryNode1("Top Users Pofile");
		extent.HeaderChildNode("Top Users Pofile");

		TimeStampclick(MPWADiscoverPage.objSearchResult, "Search result");
		waitTime(3000);
		//		
		boolean Profileresults = verifyIsElementDisplayed(MPWADiscoverPage.objListOfSearchUserProfiles);
		//	  	extent.extentLoggerPass("", "");
		extent.extentLoggerPass("", "");
		if (Profileresults) {
			int ListofProfileresults = getDriver().findElements(MPWADiscoverPage.objListOfSearchUserProfiles).size();
			if (ListofProfileresults == 0) {
				extent.extentLoggerPass("", "");
				logger.info("");
			} else {
				for (int i = 1; i <= ListofProfileresults; i++) {
					String TotalListofProfileresults = getText(MPWADiscoverPage.objListFirstuserProfile(i));
					logger.info("Top Users Pofile: \"" + TotalListofProfileresults + "\" is available ");

					Time_ExcelUpdate.TestCaseSummaryNode1(TotalListofProfileresults);
					extent.extentLoggerPass("Top Users Pofile ",
							"Top Users Pofile : \"" + TotalListofProfileresults + "\" is available");
				}
			}
		} else {
			logger.info("Top Users Pofile are not displayed ");
		}
		//		
		//		Time_ExcelUpdate.ModuleNode("Discover");
		//		Time_ExcelUpdate.TestCaseSummaryNode1("List of Videos");
		//		extent.HeaderChildNode("List of Videos");
		//		PartialSwipe("UP", 1);
		//		boolean Videothumbnail = verifyIsElementDisplayed(HipiDiscoverPage.objVideosInVideosTab);
		////		extent.extentLoggerPass("", "");
		//		if (Videothumbnail) {
		//			int listofVideothumbnail = getDriver().findElements(HipiDiscoverPage.objVideosInVideosTab).size();
		//			if (listofVideothumbnail == 0) {
		////				extent.extentLoggerFail("", "");
		//				
		//				logger.info("");
		//			} else {
		//				for (int i = 1; i <= listofVideothumbnail; i++) {																			
		//					String totallistofVideothumbnail = getText(HipiDiscoverPage.objListOfVideos(i));
		//					logger.info("llist Of video User Name " + totallistofVideothumbnail + "\" is available ");
		//					
		//					Time_ExcelUpdate.TestCaseSummaryNode1(totallistofVideothumbnail);
		//					extent.extentLoggerPass("llist Of video User Name ",
		//							"llist Of video User Name : \"" + totallistofVideothumbnail + "\" is available");
		//
		//				}
		//			}
		//		} else {
		//			logger.info("llist Of video User Name are not displayed after clicking on Search result CTA");
		//		}
		//		
		//		
		//		
		////		Time_ExcelUpdate.TestCaseIDNode("TC029");
		//		Time_ExcelUpdate.ModuleNode("Discover");
		//		Time_ExcelUpdate.TestCaseSummaryNode1("List of Hashhtags");
		//		extent.HeaderChildNode("List of Hashhtags");
		//		
		//		boolean Hashhtagsresults = verifyIsElementDisplayed(HipiDiscoverPage.objHashhtags);
		//		extent.extentLoggerPass("", "");
		////		extent.extentLoggerPass("", "");
		//		if (Hashhtagsresults) {
		//		int HashhtagsearchResult = getDriver().findElements(HipiDiscoverPage.objHashhtags).size();
		//		if (HashhtagsearchResult == 0) {
		//			extent.extentLoggerFail("", "");
		//			logger.info("");
		//		} else {
		//			for (int i = 1; i <= HashhtagsearchResult; i++) {
		//				String listHashhtagsearchResult = getText(HipiDiscoverPage.objListOfHashhtags(i));
		//				logger.info("List of Hashhtags : \"" + listHashhtagsearchResult + "\" is available ");
		//				
		//				Time_ExcelUpdate.TestCaseSummaryNode1(listHashhtagsearchResult);
		//				extent.extentLoggerPass("List of Hashhtags ",
		//						"List of Hashhtags : \"" + listHashhtagsearchResult + "\" is available ");
		//
		//			}
		//		}
		//	} else {
		//		logger.info("List of Hashhtags are not displayed after clicking on Hashhtags CTA");
		//	}
		//		Time_ExcelUpdate.ModuleNode("Discover");
		//		Time_ExcelUpdate.TestCaseSummaryNode1("List of Sounds");
		//		extent.HeaderChildNode("List of Sounds");
		//		waitTime(4000);
		//		TimeStampSwipe("UP", 2);
		//		if (TimeStampverifyElementExist(HipiDiscoverPage.objFirstSongInSoundTab, "Sound names")) {
		//			boolean SoundNamesTopTab = verifyIsElementDisplayed(HipiDiscoverPage.objFirstSongInSoundTab);
		//			extent.extentLoggerPass("", "" );
		//			if (SoundNamesTopTab) {
		//				int listOfSoundNameTopTab = getDriver().findElements(HipiDiscoverPage.objFirstSongInSoundTab).size();
		//				if (listOfSoundNameTopTab == 0) {
		////					extent.extentLoggerPass("", "");
		//					logger.info("");
		//				} else {
		//					for (int i = 1; i <= listOfSoundNameTopTab; i++) {																			
		//						String TotallistOfSoundNameTopTab = getText(HipiDiscoverPage.objListofFirstSongInSoundTab(i));
		//						logger.info("llist Of Sound Name" + TotallistOfSoundNameTopTab + "\" is available ");
		//						
		//						Time_ExcelUpdate.TestCaseSummaryNode1(TotallistOfSoundNameTopTab);
		//						extent.extentLoggerPass("llist Of Sound Name",
		//								"llist Of Sound Name" + TotallistOfSoundNameTopTab + "\" is available");
		//					}
		//				}
		//			} else {
		//				logger.info("Sound are not displayed after clicking on Sound CTA");
		//			}
		//		}else {
		//			logger.info("Sound are not displayed after clicking on Sound CTA");
		//		}
		//		
		//		
		//		
		//
		////		Time_ExcelUpdate.TestCaseIDNode("TC030");
		//		Time_ExcelUpdate.ModuleNode("Discover");
		//		Time_ExcelUpdate.TestCaseSummaryNode1("Users Tab Results");
		//		extent.HeaderChildNode("Users Tab Results");
		//		
		//		TimeStampclick(HipiDiscoverPage.objUserTab, "User Tab");
		//		waitTime(3000);
		//		
		//		boolean UserTabresults = verifyIsElementDisplayed(HipiDiscoverPage.objListOfSearchUserProfiles);
		////		extent.extentLoggerPass("", "");
		//	extent.extentLoggerPass("", "");
		//	if (UserTabresults) {
		//		int UserTabSearchresults = getDriver().findElements(HipiDiscoverPage.objListOfSearchUserProfiles).size();
		//		if (UserTabSearchresults == 0) {
		//			extent.extentLoggerFail("", "");
		//			logger.info("");
		//		} else {
		//			for (int i = 1; i <= 2; i++) {																			
		//				String listofUserTabSearchresults = getText(HipiDiscoverPage.objListFirstuserProfile(i));
		//				logger.info("Users Tab Results : \"" + listofUserTabSearchresults + "\" is available ");
		//				
		//				Time_ExcelUpdate.TestCaseSummaryNode1(listofUserTabSearchresults);
		//				extent.extentLoggerPass("Users Tab Results ",
		//						"Users Tab Results : \"" + listofUserTabSearchresults + "\" is available");
		//
		//			}
		//		}
		//	} else {
		//		logger.info("Users Tab Results  are not displayed after clicking on User CTA");
		//	}
		////		Time_ExcelUpdate.TestCaseIDNode("TC031");
		//		Time_ExcelUpdate.ModuleNode("Discover");
		//		Time_ExcelUpdate.TestCaseSummaryNode1("Videos Tab");
		//		extent.HeaderChildNode("Videos Tab");
		//		TimeStampclick(HipiDiscoverPage.objVideoTab, "Video Tab");
		//		waitTime(3000);
		//		extent.extentLoggerPass("", "");
		////		extent.extentLoggerFail("", "");
		//		TimeStampverifyElementExist(HipiDiscoverPage.objrelatedvioedosSound,"");
		//		List<WebElement> listOfCountrys = getDriver().findElements(HipiDiscoverPage.objrelatedvioedosSound);
		//		int size = listOfCountrys.size();
		//		System.out.println(size);
		//		logger.info("Numbers of  " + size);	
		//		Time_ExcelUpdate.TestCaseSummaryNode1("Numbers of " + size);
		//		boolean UserNames = verifyIsElementDisplayed(HipiDiscoverPage.objVideosInVideosTab);
		//		extent.extentLoggerPass("", "Numbers of " + size);
		//		if (UserNames) {
		//			int listOfUserName = getDriver().findElements(HipiDiscoverPage.objVideosInVideosTab).size();
		//			if (listOfUserName == 0) {
		////				extent.extentLoggerFail("", "");
		//				
		//				logger.info("");
		//			} else {
		//				for (int i = 1; i <= listOfUserName; i++) {																			
		//					String TotallistOfUserName = getText(HipiDiscoverPage.objListOfVideos(i));
		//					logger.info("llist Of video User Name" + TotallistOfUserName + "\" is available ");
		//					
		//					Time_ExcelUpdate.TestCaseSummaryNode1(TotallistOfUserName);
		//					extent.extentLoggerPass("llist Of video User Name",
		//							"llist Of video User Name : \"" + TotallistOfUserName + "\" is available");
		//
		//				}
		//			}
		//		} else {
		//			logger.info("llist Of video User Name are not displayed after clicking on Video CTA");
		//		}
		//		
		////		Time_ExcelUpdate.TestCaseIDNode("TC032");
		//		Time_ExcelUpdate.ModuleNode("Discover");
		//		Time_ExcelUpdate.TestCaseSummaryNode1("Sounds Tab");
		//		extent.HeaderChildNode("Sounds Tab");
		//		TimeStampclick(HipiDiscoverPage.objSoundtabinSearchresultpage, "Sound Tab");
		//		if (TimeStampverifyElementExist(HipiDiscoverPage.objFirstSongInSoundTab, "Sound names")) {
		//			boolean SoundNames = verifyIsElementDisplayed(HipiDiscoverPage.objFirstSongInSoundTab);
		//			extent.extentLoggerPass("", "Numbers of " + size);
		//			if (SoundNames) {
		//				int listOfSoundName = getDriver().findElements(HipiDiscoverPage.objFirstSongInSoundTab).size();
		//				if (listOfSoundName == 0) {
		//					extent.extentLoggerPass("", "");
		//					logger.info("");
		//				} else {
		//					for (int i = 1; i <= listOfSoundName; i++) {																			
		//						String TotallistOfSoundName = getText(HipiDiscoverPage.objListofFirstSongInSoundTab(i));
		//						logger.info("llist Of Sound Name" + TotallistOfSoundName + "\" is available ");
		//						
		//						Time_ExcelUpdate.TestCaseSummaryNode1(TotallistOfSoundName);
		//						extent.extentLoggerPass("llist Of Sound Name",
		//								"llist Of Sound Name" + TotallistOfSoundName + "\" is available");
		//					}
		//				}
		//			} else {
		//				logger.info("Sound are not displayed after clicking on Sound CTA");
		//			}
		//		}else {
		//			logger.info("Sound are not displayed after clicking on Sound CTA");
		//		}
		//		
		////		Time_ExcelUpdate.TestCaseIDNode("TC032");
		//		Time_ExcelUpdate.ModuleNode("Discover");
		//		Time_ExcelUpdate.TestCaseSummaryNode1("Hashtags Tab");
		//		extent.HeaderChildNode("Hashtags Tab");
		//		TimeStampclick(HipiDiscoverPage.objHashtagsTab, "Hashtags Tab");
		//		
		//		if (TimeStampverifyElementExist(HipiDiscoverPage.objFirstHashtagsInSoundTab, "Hashtags names")) {
		//			boolean HashtagsNames = verifyIsElementDisplayed(HipiDiscoverPage.objFirstHashtagsInSoundTab);
		//			extent.extentLoggerPass("", "Numbers of " + size);
		//			if (HashtagsNames) {
		//				int listOfHashtagsName = getDriver().findElements(HipiDiscoverPage.objFirstHashtagsInSoundTab).size();
		//				if (listOfHashtagsName == 0) {
		//					extent.extentLoggerPass("", "");
		//					logger.info("");
		//				} else {
		//					for (int i = 1; i <= listOfHashtagsName; i++) {																			
		//						String TotallistOfHashtagsName = getText(HipiDiscoverPage.objListofFirstHashtagsInSoundTab(i));
		//						logger.info("llist Of Sound Name" + TotallistOfHashtagsName + "\" is available ");
		//						
		//						Time_ExcelUpdate.TestCaseSummaryNode1(TotallistOfHashtagsName);
		//						extent.extentLoggerPass("llist Of Sound Name",
		//								"llist Of Sound Name" + TotallistOfHashtagsName + "\" is available");
		//					}
		//				}
		//			} else {
		//				logger.info("Sound are not displayed after clicking on Sound CTA");
		//			}
		//		}else {
		//			logger.info("Sound are not displayed after clicking on Sound CTA");
		//		}
		//		
		//		TimeStampnavigateToHomePage();
		//		
	}



	public void loginTime(String userType) throws Exception {

		// navigateToIntroScreen_DisplaylangScreen();
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC005");
		//					Time_ExcelUpdate.SlNoNode("2");
		Time_ExcelUpdate.ModuleNode("Login Module");
		Time_ExcelUpdate.TestCaseSummaryNode1("Login Time");
		Time_ExcelUpdate.ExpectedProcessingTime("7.5 Sec");

		extent.HeaderChildNode("Launching hipi ");
		//					extent.HeaderChildNode("NonSubscribed User");

		TimeStampSwipe("UP", 1);

		waitUntilElementDisplayed(MPWAHomePage.objProfileIcon, 10);
		//click on Profile icon
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
		if (TimeStampverifyElementExist(MPWAProfilePage.objThreeDots, "Three Dots")) {
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objThreeDots, "Three Dots");
			waitTime(3000);
			//			SwipeUntilFindElement(HipiSettingsPage.objLogOut, "Log out Button");
			//			TimeStampSwipe("UP", 2);
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objLogOut, "Log out");
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objYesCTA, "Yes CTA");
			waitTime(4000);
			TimeStampverifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
		}
		waitTime(4000);
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objUsePhoneOrEmail,"Use Phone Or Email");

		String Username = getParameterFromXML("NonsubscribedUserName");
		String Password = getParameterFromXML("NonsubscribedPassword");

		waitTime(5000);
		//					hideKeyboard();
		//					TimeStampverifyElementPresentAndClick(MPWALoginPage.objEmailOption, "Email Option");
		//					waitTime(4000);
		//					TimeStampclick(MPWALoginPage.objEmailIdField, "Email Field");
		//					waitTime(4000);
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objEmailIdField, "Email Field");
		waitTime(4000);

		TimeStamptype(MPWALoginPage.objEmailIdField, Username, "Email Field");
		TimeStamphideKeyboard();
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objProceed, "Proceed Button");
		waitUntilElementDisplayed(MPWALoginPage.objLoginWithPassword, 2);
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objLoginWithPassword, "Login With Password");

		TimeStampverifyElementPresentAndClick(MPWALoginPage.objPasswordField, "Password Field");
		//					hideKeyboard();

		TimeStamptype(MPWALoginPage.objPasswordField, Password, "Password field");
		//					
		TimeStamphideKeyboard();
		waitTime(5000);
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objLoginBtn, "Login Button");
		//					TimeStamptype(MPWALoginPage.objEmailIdField, Username, "Email Field");
		//					TimeStamphideKeyboard();
		//					TimeStampverifyElementPresentAndClick(MPWALoginPage.objLoginBtn, "Login Button");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//					extent.extentLogger("", "<b>Start Time : " + start+"<b>");
		if (waitUntilElementDisplayed(MPWAHomePage.objForYou, 2)) {
			logger.info("User is able to see Home screen");
			//						extent.extentLogger("", "User is able to see Home screen");
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//						extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			extent.extentLogger("", "<b>Processing time: " + processingTime+"<b>");

			//						extent.HeaderChildNode("<b>Processing time: " + processingTime+"<b>");
		}
		else {
			logger.error("Processing time calculation failed");
			Time_ExcelUpdate.timeStampNode("Processing time calculation failed");
			extent.extentLoggerFail("", "Processing time calculation failed");

		}


		//					waitUntilElementDisplayed(HipiHomePage.objForYou, 2000);
		//click on feed button
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objHomeIcon, "Home Icon");

	}
	public void DiscoverScreenLoadTime() throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC006");
		//			Time_ExcelUpdate.SlNoNode("4");
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.ExpectedProcessingTime("2.5 Sec");
		TimeStampSwipe("UP", 1);
		Time_ExcelUpdate.TestCaseSummaryNode1("Discover screen load time");
		extent.HeaderChildNode("Discover screen load time");
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//			extent.extentLogger("", "<b>Start Time : " + getDateDetails(startTime)+"<b>");
		if(waitUntilElementDisplayed(MPWADiscoverPage.objFirstProfileName, 10)) {
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			extent.extentLogger("", "<b>Processing time: " + processingTime+"<b>");

			extent.HeaderChildNode("<b>Discover screen Processing time: " + processingTime+"<b>");
		}else {
			Time_ExcelUpdate.timeStampNode("Discover screen is not displayed");
			extent.extentLoggerFail("", "Discover screen is not displayed");	
		}
		TimeStampnavigateToHomePage();
		//			TimeStampBack(1);
		//			waitTime(5000);

	}

	public void	AutoSuggestionTime(String userName) throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC007");
		//			Time_ExcelUpdate.SlNoNode("4");
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.ExpectedProcessingTime("2 Sec");

		Time_ExcelUpdate.TestCaseSummaryNode1("Auto suggestion time.");
		extent.HeaderChildNode("Auto suggestion time.");
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		waitUntilElementDisplayed(MPWADiscoverPage.objBannersBelowTheSearchField, 5);
		TimeStampverifyElementPresentAndClick(MPWADiscoverPage.objSearchTab, "Send text ");
		waitTime(4000);
		TimeStamptype(MPWADiscoverPage.objSearchTab, userName, "Type value");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//			Date startTime = new Date();
		//			logger.info("Start Time : " + getDateDetails(startTime));
		//			extent.extentLogger("", "<b>Start Time : " + getDateDetails(startTime)+"<b>");
		if(waitUntilElementDisplayed(MPWADiscoverPage.objListOfSearchResult, 10)) {
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			//			extent.extentLogger("", "<b>Processing time: " + processingTime+"<b>");
			extent.extentLogger("", "<b> Auto suggestion Processing time: " + processingTime+"<b>");
			//			extent.HeaderChildNode("<b> Auto suggestion Processing time: " + processingTime+"<b>");
		}else {
			Time_ExcelUpdate.timeStampNode("Auto suggestion result is not displayed");
			extent.extentLoggerFail("", "Auto suggestion result is not displayed");
		}
		TimeStampnavigateToHomePage();
		//			TimeStampBack(1);
		//			waitTime(5000);
	}

	public void TimeStampnavigateToHomePage() throws Exception {
		boolean flag;
		for (int i = 1; i < 10; i++) {
			flag = verifyElementDisplayed(MPWAHomePage.objForYou);

			//For You is display its store in T
			////For You is NOT display its store in F
			//				Back(1);
			System.out.println(flag);
			if (flag) {
				//					if (verifyElementDisplayed(HipiShopPage.objSavedMomentTab)) {
				//						TimeStampBack(1);
				//					}
				waitTime(4000);
				break;
			} else {
				TimeStampBack(1);
				TimeStampSwipe("DOWN", 1);
				waitTime(5000);
			}
		}
		if (verifyElementExist(MPWAHomePage.objHandiAnimation, "Hand Animation")) {
			click(MPWAHomePage.objHomeIcon, "Home Icon");
		}
		waitTime(5000);
	}


	public void OnClickingBannerScreenLoadTime(String userType) throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC008");
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.ExpectedProcessingTime("2.5 Sec");

		Time_ExcelUpdate.TestCaseSummaryNode1("On clicking Banner, screen load time.");
		extent.HeaderChildNode("On clicking Banner, screen load time.");
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		//			waitTime(5000);
		waitUntilElementDisplayed(MPWADiscoverPage.objBannersBelowTheSearchField, 5);
		TimeStampverifyElementPresentAndClick(MPWADiscoverPage.objBannersBelowTheSearchField, "Video Banner");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//			Date startTime = new Date();
		//			logger.info("Start Time : " + getDateDetails(startTime));
		//			extent.extentLogger("", "<b>Start Time : " + getDateDetails(startTime)+"<b>");
		if(waitUntilElementDisplayed(MPWADiscoverPage.objBannerHeader, 10)) {
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			extent.extentLogger("", "<b>Processing time: " + processingTime+"<b>");
			//			extent.HeaderChildNode("<b>Banner screen Processing time: " + processingTime+"<b>");
			//			TimeStampBack(1);
			//			waitTime(5000);
			//			TimeStampBack(1);
			//			waitTime(5000);
		}else {
			Time_ExcelUpdate.timeStampNode("On clicking Banner, screen load time is not displayed");
			extent.extentLoggerFail("", "On clicking Banner, screen load time is not displayed");
		}
		TimeStampnavigateToHomePage();
	}

	public void hashtagDetailPageTime() throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC009");
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.ExpectedProcessingTime("2.5 Sec");

		Time_ExcelUpdate.TestCaseSummaryNode1("hashtag detail page time.");
		extent.HeaderChildNode("hashtag detail page time.");
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		waitUntilElementDisplayed(MPWADiscoverPage.objBannersBelowTheSearchField, 5);
		TimeStampclick(MPWADiscoverPage.objMoreButtonTrendingOnHipi, "More Button");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//			Date startTime = new Date();
		//			logger.info("Start Time : " + getDateDetails(startTime));
		//			extent.extentLogger("", "<b>Start Time : " + getDateDetails(startTime)+"<b>");
		if(waitUntilElementDisplayed(MPWADiscoverPage.objBannerHeader, 10)) {
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			extent.extentLogger("", "<b>Hashtag detail page Processing time: " + processingTime+"<b>");
			//			extent.HeaderChildNode("<b>Hashtag detail page Processing time: " + processingTime+"<b>");
			//			TimeStampBack(1);
			//			waitTime(5000);
			//			TimeStampBack(1);
			//			waitTime(5000);
		}else {
			Time_ExcelUpdate.timeStampNode("hashtag detail page time is not displayed");
			extent.extentLoggerFail("", "hashtag detail page time is not displayed");
		}
		TimeStampnavigateToHomePage();
	} 

	public void BannerLoadTime() throws Exception {

		Time_ExcelUpdate.TestCaseIDNode("MPWA TC010");
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.TestCaseSummaryNode1("Carrousel/ Banner load time.");
		extent.HeaderChildNode("Carrousel/ Banner load time.");
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//			Date startTime = new Date();
		//			logger.info("Start Time : " + getDateDetails(startTime));
		//			extent.extentLogger("", "<b>Start Time : " + getDateDetails(startTime)+"<b>");
		if(waitUntilElementDisplayed(MPWADiscoverPage.objBannersBelowTheSearchField, 10)) {
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			extent.extentLogger("", "<b>Banner load Processing time: " + processingTime+"<b>");
			//			extent.HeaderChildNode("<b>Banner load Processing time: " + processingTime+"<b>");
			//			TimeStampBack(1);
			//			waitTime(5000);
		}else {
			Time_ExcelUpdate.timeStampNode("Carrousel/ Banner load time is not displayed");
			extent.extentLoggerFail("", "Carrousel/ Banner load time is not displayed");
		}
		TimeStampnavigateToHomePage();

	}

	public void SearchScreenTimePostSearchingAKeyword(String name) throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC011");
		Time_ExcelUpdate.ModuleNode("Discover");
		Time_ExcelUpdate.ExpectedProcessingTime("4.5 Sec");

		Time_ExcelUpdate.TestCaseSummaryNode1("Search screen time post searching a keyword");
		extent.HeaderChildNode("Search screen time post searching a keyword");
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		waitUntilElementDisplayed(MPWADiscoverPage.objBannersBelowTheSearchField, 5);
		TimeStampverifyElementPresentAndClick(MPWADiscoverPage.objSearchTab, "Search Box");
		waitTime(4000);
		TimeStamptype(MPWADiscoverPage.objSearchTab, name, "Type value");
		//			TimeStamphideKeyboard();
		waitTime(3000);
		TimeStampclick(MPWADiscoverPage.objListOfSearchResult, "Search result");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//			Date startTime = new Date();
		//			logger.info("Start Time : " + getDateDetails(startTime));
		//			extent.extentLogger("", "<b>Start Time : " + getDateDetails(startTime)+"<b>");
		if(waitUntilElementDisplayed(MPWADiscoverPage.objTopButton, 10)) {
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			extent.extentLogger("", "<b>Search screen time post searching a keyword Processing time: " + processingTime+"<b>");
			//			extent.HeaderChildNode("<b>Search screen time post searching a keyword Processing time: " + processingTime+"<b>");
			//			TimeStampBack(1);
			//			waitTime(5000);
			//			TimeStampBack(1);
			//			waitTime(5000);
		}else {
			Time_ExcelUpdate.timeStampNode("Search screen time post searching a keyword is not displayed");
			extent.extentLoggerFail("", "Search screen time post searching a keyword is not displayed");
		}
		TimeStampnavigateToHomePage();
	}
	public String getDateDetails(Date date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateString = dateFormat.format(date).toString();
		return dateString;
	}	
	String description = null;
	String description1 = null;
	public void VideoStartUpTimeAfterSwipe() throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC009");
		Time_ExcelUpdate.ModuleNode("Feed");
		Time_ExcelUpdate.ExpectedProcessingTime("1.1 Sec");
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		Time_ExcelUpdate.TestCaseSummaryNode1("Video start up time after swipe.");
		extent.HeaderChildNode("Video start up time after swipe.");
		description = getText(HipiHomePage.objDescription);
		TimeStampSwipe("DOWN", 1);
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		Date startTime = new Date();
		logger.info("Start Time : " + getDateDetails(startTime));
		description1 = getText(HipiHomePage.objDescription);
		if (!description.equals(description1)) {
			logger.info("user able to swipe Down Screen");
		}else {
			logger.info("user not able to swipe Down Screen");

		}
		//			extent.extentLogger("", "<b>Start Time : " + getDateDetails(startTime)+"<b>");
		//			waitUntilElementDisplayed(HipiHomePage.objProgressBarStart, 10);
		Instant end=Instant.now();
		logger.info("End Time : " + end);
		//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
		Duration processingTime = Duration.between(start, end);
		logger.info("Processing time: " + processingTime);
		String Processingtimesec=Long.toString(processingTime.getSeconds());
		String Processingtimemilli=Long.toString(processingTime.toMillis());	
		Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
		extent.extentLogger("", "<b>Video start up time after swipe Processing time: " + processingTime+"<b>");
		//			extent.HeaderChildNode("<b>Video start up time after swipe Processing time: " + processingTime+"<b>");


	}

	public void searchResults(String SearchItem,String TCID) throws Exception {

		extent.HeaderChildNode("Search Result for "+SearchItem);
		String Suggestions1="";
		String Suggestions2="";
		String Profile1="";
		String Profile2="";
		String Video1="";
		String Video2="";
		String Video3="";
		String Video4="";
		String Hashtags1="";
		String Hashtags2="";
		String Sounds1="";
		String Sounds2="";

		logger.info("Search Result for "+SearchItem);
		Time_ExcelUpdate.TestCaseIDNode(TCID);
		Time_ExcelUpdate.ModuleNode("Search Suggetions: "+SearchItem);
		Time_ExcelUpdate.TestCaseSummaryNode1("Search Suggetions: "+SearchItem);
		TimeStampSwipe("UP", 1);
		//			waitTime(2000);
		waitUntilElementDisplayed(MPWAHomePage.objDiscoverButton, 5);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Discover Button");
		waitUntilElementDisplayed(MPWADiscoverPage.objBannersBelowTheSearchField, 5);
		TimeStampverifyElementPresentAndClick(MPWADiscoverPage.objSearchTab, "Search Tab");
		waitTime(3000);
		TimeStamptype(MPWADiscoverPage.objSearchTab, SearchItem, "Type value");
		waitTime(5000);

		if(TimeStampverifyElementExist(MPWADiscoverPage.objsearchResults(1), "suggestions")) {
			Suggestions1=getText(MPWADiscoverPage.objsearchResults(1));
			System.out.println(Suggestions1);
			Suggestions2=getText(MPWADiscoverPage.objsearchResults(2));
			System.out.println(Suggestions2);
			waitTime(3000);


			Time_ExcelUpdate.TestCaseSummaryNode1("1 "+Suggestions1);
			extent.extentLogger("","Search  1st Suggestion done");

			Time_ExcelUpdate.TestCaseSummaryNode1("2 "+Suggestions2);
		}else {

			//Time_ExcelUpdate.TestCaseSummaryNode1("Suggestion  not present");

			logger.info(TCID + "Suggestion  not present");
		}

		TimeStampverifyElementPresentAndClick(MPWADiscoverPage.objListOfSearchResult, "Search result");
		////			waitTime(3000);
		waitUntilElementDisplayed(MPWADiscoverPage.objListFirstuserProfile(1), 10);
		////			TimeStampwaitForElementDisplayedFastPolling(IOSHiPiNeoDiscoverPage.objiosProfileInDiscoverpage(1), 100, "results after search");
		extent.extentLogger("","Search done");

		Time_ExcelUpdate.ModuleNode("Top Users Section :"+SearchItem);
		Time_ExcelUpdate.TestCaseSummaryNode1("Top Users Result: "+SearchItem);
		Profile1=getText(MPWADiscoverPage.objListFirstuserProfile(1));
		System.out.println(Profile1);
		Profile2=getText(MPWADiscoverPage.objListFirstuserProfile(2));
		System.out.println(Profile2);
		waitTime(3000);


		Time_ExcelUpdate.TestCaseSummaryNode1("1 "+Profile1);
		extent.extentLogger("","Top Users 1 st profile name fetched");

		Time_ExcelUpdate.TestCaseSummaryNode1("2 "+Profile2);
		extent.extentLogger("","Top Users 2nd profile name fetched");


		Time_ExcelUpdate.ModuleNode("Top Videos Section:"+SearchItem);
		Time_ExcelUpdate.TestCaseSummaryNode1("Top Videos Result:"+SearchItem);

		////			PartialSwipe("UP", 1);
		//			if(TimeStampverifyElementExist(HipiDiscoverPage.objListOfVideos(1), "Videos in under Top section ")) {
		//			Video1=getText(HipiDiscoverPage.objListOfVideos(1));
		//			System.out.println(Video1);
		//			Video2=getText(HipiDiscoverPage.objListOfVideos(2));
		//			System.out.println(Video2);
		if(TimeStampverifyElementExist(MPWADiscoverPage.objListOfVideos(1), "Videos in under Top section ")) {
			waitTime(3000);
			TimeStampclick(MPWADiscoverPage.objListOfVideos(1),"1 st video");
			waitUntilElementDisplayed(MPWAHomePage.objUserNameInFeedScreen, 5);
			String Video1Username=getText(MPWAHomePage.objUserNameInFeedScreen);
			TimeStampBack(1);
			waitTime(5000);

			TimeStampclick(MPWADiscoverPage.objListOfVideos(2),"2nd video");
			waitUntilElementDisplayed(MPWAHomePage.objUserNameInFeedScreen, 5);
			String Video2Username=getText(MPWAHomePage.objUserNameInFeedScreen);
			TimeStampBack(1);
			waitTime(5000);

			TimeStampclick(MPWADiscoverPage.objListOfVideos(3),"3 rd video");
			waitUntilElementDisplayed(MPWAHomePage.objUserNameInFeedScreen, 5);
			String Video3Username=getText(MPWAHomePage.objUserNameInFeedScreen);
			TimeStampBack(1);
			waitTime(5000);

			Time_ExcelUpdate.TestCaseSummaryNode1("1 st video is from  "+Video1Username);
			extent.extentLogger("","Top videos 1 st video name fetched");

			Time_ExcelUpdate.TestCaseSummaryNode1("2 nd video is from "+Video2Username);
			extent.extentLogger("","Top videos 2nd video name fetched");

			Time_ExcelUpdate.TestCaseSummaryNode1("3 rd video is from "+Video3Username);
			extent.extentLogger("","Top videos 3rd video name fetched");
			//			
		}else {
			Time_ExcelUpdate.TestCaseIDNode(TCID);
			Time_ExcelUpdate.TestCaseSummaryNode1("Videos  not present");
			extent.extentLoggerFail("","Videos  not present");

			Time_ExcelUpdate.TestCaseSummaryNode1("Videos  not present");
			//				extent.extentLoggerFail("","Videos  not present");
		}
		//			waitTime(4000);
		//			PartialSwipe("UP", 1);
		Time_ExcelUpdate.ModuleNode("Top Hashtag Section: "+SearchItem);
		////			TimeStampSwipeTillElement(IOSHiPiNeoDiscoverPage.objiosHashtagSectionUnderTopresults, "Hashtags");
		waitTime(3000);
		if(TimeStampverifyElementExist(MPWADiscoverPage.objListOfHashhtags(1), "Hashtags")) {

			Hashtags1=getText(MPWADiscoverPage.objListOfHashhtags(1));
			System.out.println(Hashtags1);
			Hashtags2=getText(MPWADiscoverPage.objListOfHashhtags(2));
			System.out.println(Hashtags2);
			waitTime(3000);


			Time_ExcelUpdate.TestCaseSummaryNode1("1."+Hashtags1);
			extent.extentLogger("","1 st Hashtag fetched");

			Time_ExcelUpdate.TestCaseSummaryNode1("2."+Hashtags2);
			extent.extentLogger("","2 nd Hashtag fetched");


		}else {

			Time_ExcelUpdate.TestCaseSummaryNode1("Hashtag  not present");
			logger.info("Hashtag  not present");


			Time_ExcelUpdate.TestCaseSummaryNode1("Hashtag  not present");
			//				extent.extentLoggerFail("","Hashtag  not present");
		}
		//			
		//			
		waitTime(5000);
		TimeStampclick(MPWADiscoverPage.objUsersButton, "User Tab");
		waitUntilElementDisplayed(MPWADiscoverPage.objListUserProfile(1), 5);
		Time_ExcelUpdate.ModuleNode("Users Section: "+SearchItem);
		if(TimeStampverifyElementExist(MPWADiscoverPage.objListUserProfile(1), "Results under Users Tab")) {
			waitTime(3000);
			String UsersProfile1=getText(MPWADiscoverPage.objListUserProfile(1));
			String UsersProfile2=getText(MPWADiscoverPage.objListUserProfile(2));
			String UsersProfile3=getText(MPWADiscoverPage.objListUserProfile(3));
			String UsersProfile4=getText(MPWADiscoverPage.objListUserProfile(4));
			String UsersProfile5=getText(MPWADiscoverPage.objListUserProfile(5));


			Time_ExcelUpdate.TestCaseSummaryNode1("1."+UsersProfile1);
			extent.extentLogger("","1 st Result under Users Tab");

			Time_ExcelUpdate.TestCaseSummaryNode1("2."+UsersProfile2);
			extent.extentLogger("","2 nd Result under Users Tab");

			Time_ExcelUpdate.TestCaseSummaryNode1("3."+UsersProfile3);
			extent.extentLogger("","3rd Result under Users Tab");

			Time_ExcelUpdate.TestCaseSummaryNode1("4."+UsersProfile4);
			extent.extentLogger("","4th Result under Users Tab");

			Time_ExcelUpdate.TestCaseSummaryNode1("5."+UsersProfile5);
			extent.extentLogger("","5 th Result under Users Tab");


		}else {

			//Time_ExcelUpdate.TestCaseSummaryNode1("Users tap result  not present");
			extent.extentLoggerFail("","Users tap result  not present");

			Time_ExcelUpdate.TestCaseSummaryNode1("Users tap result  not present");
			extent.extentLoggerFail("","Users tap result  not present");

			Time_ExcelUpdate.TestCaseSummaryNode1("Users tap result  not present");
			extent.extentLoggerFail("","Users tap result  not present");

			Time_ExcelUpdate.TestCaseSummaryNode1("Users tap result  not present");
			extent.extentLoggerFail("","Users tap result  not present");

			Time_ExcelUpdate.TestCaseSummaryNode1("Users tap result  not present");
			extent.extentLoggerFail("","Users tap result  not present");

		}

		/*	
			waitTime(5000);

			Time_ExcelUpdate.ModuleNode("Videos Section: "+SearchItem);
			Time_ExcelUpdate.TestCaseSummaryNode1("Videos section in search Result: "+SearchItem);


				TimeStampclick(MPWADiscoverPage.objVideosButton, "Video Tab");
				waitTime(3000);	

				if(TimeStampverifyElementExist(MPWADiscoverPage.objListOfVideosInVideoStab(1),"Videos Result")){
				waitTime(3000);
				TimeStampclick(MPWADiscoverPage.objListOfVideosInVideoStab(1),"1 st video");
				waitUntilElementDisplayed(MPWAHomePage.objUserNameInFeedScreen, 5);
				Video1=getText(MPWAHomePage.objUserNameInFeedScreen);
				TimeStampBack(1);
				waitUntilElementDisplayed(MPWADiscoverPage.objListOfVideosInVideoStab(1), 10);

				TimeStampclick(MPWADiscoverPage.objListOfVideosInVideoStab(2),"2nd video");
				waitUntilElementDisplayed(MPWAHomePage.objUserNameInFeedScreen, 5);
				 Video2=getText(MPWAHomePage.objUserNameInFeedScreen);
				TimeStampBack(1);
				waitUntilElementDisplayed(MPWADiscoverPage.objListOfVideosInVideoStab(1), 10);

				TimeStampclick(MPWADiscoverPage.objListOfVideosInVideoStab(3),"3 rd video");
				waitUntilElementDisplayed(MPWAHomePage.objUserNameInFeedScreen, 5);
				 Video3=getText(MPWAHomePage.objUserNameInFeedScreen);
				TimeStampBack(1);
				waitUntilElementDisplayed(MPWADiscoverPage.objListOfVideosInVideoStab(1), 10);
				TimeStampclick(MPWADiscoverPage.objListOfVideosInVideoStab(4),"4 th video");
				waitUntilElementDisplayed(MPWAHomePage.objUserNameInFeedScreen, 5);
				Video4=getText(MPWAHomePage.objUserNameInFeedScreen);
				TimeStampBack(1);
				waitUntilElementDisplayed(MPWADiscoverPage.objListOfVideosInVideoStab(1), 10);


				Time_ExcelUpdate.TestCaseSummaryNode1("1 st video is from  "+Video1);
				extent.extentLogger(""," in videos section 1 st video name fetched");

				Time_ExcelUpdate.TestCaseSummaryNode1("2 nd video is from "+Video2);
				extent.extentLogger(""," in videos section 2 nd video name fetched");



				Time_ExcelUpdate.TestCaseSummaryNode1("3 rd video is from "+Video3);
				extent.extentLogger(""," in videos section 3 rd video name fetched");

				Time_ExcelUpdate.TestCaseSummaryNode1("4 th video is from "+Video4);
				extent.extentLogger(""," in videos section 4 th video name fetched");
				}else {

					Time_ExcelUpdate.TestCaseSummaryNode1("Videos  not present");
					extent.extentLoggerFail("","Videos  not present");


				}
		 */
		waitTime(5000);

		Time_ExcelUpdate.ModuleNode("HashTag Section: "+SearchItem);
		Time_ExcelUpdate.TestCaseSummaryNode1("HashTag Section in Search results: "+SearchItem);

		////			TimeStampverifyElementExist(IOSHiPiNeoDiscoverPage.objiosTabnameInDiscoverSearchResultsPage("Hashtags"), "HashTags Tab in search result ");
		////				TimeStampclick(IOSHiPiNeoDiscoverPage.objiosTabnameInDiscoverSearchResultsPage("Hashtags"), "Hashtags");
		TimeStampclick(MPWADiscoverPage.objHashhtagsButton, "Hashtags Tab");	
		waitUntilElementDisplayed(MPWADiscoverPage.objListofFirstHashtags(1), 5);
		if(TimeStampverifyElementExist(MPWADiscoverPage.objListofFirstHashtags(1), "Hashtag result")) {
			String HashTag1=getText(MPWADiscoverPage.objListofFirstHashtags(1));
			System.out.println(HashTag1);
			String HashTag2=getText(MPWADiscoverPage.objListofFirstHashtags(2));
			System.out.println(HashTag2);

			String HashTag3=getText(MPWADiscoverPage.objListofFirstHashtags(3));
			System.out.println(HashTag3);

			String HashTag4=getText(MPWADiscoverPage.objListofFirstHashtags(4));
			System.out.println(HashTag4);

			String HashTag5=getText(MPWADiscoverPage.objListofFirstHashtags(5));
			System.out.println(HashTag5);

			Time_ExcelUpdate.TestCaseSummaryNode1("1. "+HashTag1);
			extent.extentLogger(""," in HashTag section 1 st HashTag name fetched");

			Time_ExcelUpdate.TestCaseSummaryNode1("2. "+HashTag2);
			extent.extentLogger(""," in HashTag section 2 nd HashTag name fetched");


			Time_ExcelUpdate.TestCaseSummaryNode1("3. "+HashTag3);
			extent.extentLogger(""," in HashTag section 3 rd HashTag name fetched");


			Time_ExcelUpdate.TestCaseSummaryNode1("4. "+HashTag4);
			extent.extentLogger(""," in HashTag section 4 th HashTag name fetched");


			Time_ExcelUpdate.TestCaseSummaryNode1("5. "+HashTag5);
			extent.extentLogger(""," in HashTag section 5 th HashTag name fetched");


		}else {

			Time_ExcelUpdate.TestCaseSummaryNode1("HashTag  not present");
			extent.extentLoggerFail("","HashTag  not present");


		}
		//				TimeStampBack(1);
		//				waitTime(5000);
		//				TimeStampBack(1);
		//				waitTime(5000);
		TimeStampnavigateToHomePage();


	}

	public void TimeStamplogOut() throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC023");
		Time_ExcelUpdate.ModuleNode("Profile");
		Time_ExcelUpdate.TestCaseSummaryNode1("Logout Time");
		Time_ExcelUpdate.ExpectedProcessingTime("5 Sec");
		extent.HeaderChildNode("Logout Time");
		TimeStampSwipe("UP", 1);
		//			TimeStampnavigateToHomePage();
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objProfileIcon,"Profile Icon");
		//			waitUntilElementDisplayed(HipiProfilePage.objUsePhoneOrEmail, 20);
		if (TimeStampverifyElementExist(MPWALoginPage.objUsePhoneOrEmail,"Use Phone Or Email")) {
			logger.info("User is all ready Logout application");	
			//				extent.extentLoggerPass("", "User is all ready Logout application");
			TimeStampBack(2);
		}else {
			waitUntilElementDisplayed(MPWAProfilePage.objThreeDots, 5);
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objThreeDots, "Three Dots");
			waitUntilElementDisplayed(MPWAProfilePage.objLogOut, 1);

			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objLogOut, "Log out");
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objYesCTA, "Yes CTA");
			Instant start = Instant.now();
			//			extent.extentLogger("", "clicked on hipi at Time----- "+ start);
			if(waitForElementDisplayediOS(MPWAHomePage.objForYou, 10, "for you button in feed")) {	
				Instant end=Instant.now();
				logger.info("End Time : " + end);
				//			extent.extentLogger("", "<b>End Time : " + end+"<b>");
				Duration processingTime = Duration.between(start, end);
				logger.info("Processing time: " + processingTime);

				String Processingtimesec=Long.toString(processingTime.getSeconds());
				String Processingtimemilli=Long.toString(processingTime.toMillis());	
				Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
				extent.extentLogger("", "<b>Filter download Processing time: " + processingTime+"<b>");
			}else {
				Time_ExcelUpdate.timeStampNode("User is not able to Logout");
				extent.extentLoggerFail("", "User is not able to Logout");
			}
			if(TimeStampverifyElementExist(MPWAHomePage.objHomeIcon, "Home ICON")) {
				logger.info("User is able to Logout application");	
				//				extent.extentLoggerPass("", "User is able to Logout application");	
			}else {
				logger.info("User is not able to Logout application");	
				//				extent.extentLoggerFail("", "User is not able to Logout application");
			}
		}
	}
	public void launchApptime() throws Exception {

		Time_ExcelUpdate.TestCaseIDNode("MPWA TC001");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("FeedArrival");
		Time_ExcelUpdate.TestCaseSummaryNode1("Feed Screen launch time.");
		Time_ExcelUpdate.ExpectedProcessingTime("3.75 Sec");
		extent.HeaderChildNode("Launching hipi PWA");
		
		

	    Map<String, Object> params = new HashMap();

	    params.put("url", "hipi.co.in");

	        params.put("package", "com.android.chrome");
	    getDriver().executeScript("mobile:deepLink", new Object[]{params});
		//			extent.HeaderChildNode("Launch hipi app");
	    
	    
		/*getURl("https://hipi.co.in/");
		Instant start = Instant.now();
		//			extent.extentLogger("", "clicked on hipi at Time----- "+ start);
		extent.extentLogger("", "clicked on hipi at Time----- "+ start);
		logger.info("clicked on hipi at Time----- "+ start);
		//			waitTime(10000);
		//			TimeStampSwipe("UP", 1);
		//			waitTime(4000);
		waitUntilElementDisplayed(MPWAHomePage.objUnmute, 20);		
		//			screencapture();
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds");
		System.out.println("Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds" );
		//			extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in milliseconds : "+timeElapsed.toMillis()+" milliSeconds");

		String Processingtimesec=Long.toString(timeElapsed.getSeconds());
		String Processingtimemilli=Long.toString(timeElapsed.toMillis());	
		Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
		extent.extentLogger("", "Feed screen seen  at Time----- " + end);*/

	}

	public void SwipeUpForNextVideoAnimation(String userType) throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC004");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Feed");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("Verify Swipe up for next video Animation");

		waitUntilElementDisplayed(MPWAHomePage.objSwipeUpForNextVideo, 20);

		if(TimeStampverifyElementExist(MPWAHomePage.objSwipeUpForNextVideo, "Swipe Up For Next Video")){
			logger.info("User is able to see Swipe Up For Next Video Animation");	
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to see Swipe Up For Next Video Animation");
			extent.extentLoggerPass("", "User is able to see Swipe Up For Next Video Animation");
		}else {
			logger.info("User is able to see Swipe Up For Next Video Animation");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to see Swipe Up For Next Video Animation");

		}
	}




	public void getURl(String url) throws Exception {
		extent.HeaderChildNode("Launch hipi.co.in");
		Time_ExcelUpdate.ModuleNode("MPWA--URL Loading");
		Time_ExcelUpdate.TestCaseSummaryNode1("Launch hipi.co.in");
		getDriver().quit();
		waitTime(3000);
		new Zee5ApplicasterHipiNeoBusinessLogic("Chrome");

		waitTime(5000);
		//			try {
		//				getDriver().removeApp("com.zee5.hipi", null);
		//				System.out.println("App uninstalled");
		//			} catch (Exception e) {
		//				System.out.println("App not present");
		//			}
		//			getDriver().quit();
		//			waitTime(10000);
		//			new Zee5mWebiOSHipiNeoSanityBusinessLogic("safari");
		//			waitTime(4000);

		TimeStampclick(MPWAHomePage.objAndroidmwebSearchAddress, "searchtab in safari");
		waitTime(3000);
		TimeStamptype(MPWAHomePage.objAndroidmwebSearchAddress, url, "Typing url");
		waitTime(3000);
		TimeStampclick(MPWAHomePage.objAndroidmwebSearchButtonInKeyboardORclickURL,"search button in keyboard");

	}


	//			Instant start = Instant.now();
	////			extent.extentLogger("", "clicked on hipi at Time----- "+ start);
	//			extent.extentLogger("", "clicked on hipi at Time----- "+ start);
	//			logger.info("clicked on hipi at Time----- "+ start);
	////			waitTime(10000);
	////			TimeStampSwipe("UP", 1);
	////			waitTime(4000);
	//			TimeStampwaitForElementDisplayediOS(MPWAHomePage.objUnmute, 20, "for you button in feed");		
	////			screencapture();
	//			Instant end = Instant.now();
	//			Duration timeElapsed = Duration.between(start, end);
	//			extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds");
	//			System.out.println("Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds" );
	////			extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in milliseconds : "+timeElapsed.toMillis()+" milliSeconds");
	//			
	//			String Processingtimesec=Long.toString(timeElapsed.getSeconds());
	//			String Processingtimemilli=Long.toString(timeElapsed.toMillis());	
	//			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
	//			extent.extentLogger("", "Feed screen seen  at Time----- " + end);

	//		}


	public void commentWithApp(String userType) throws Exception {
		//Zee5ApplicasterHipiNeoBusinessLogic = new com.business.zee.Zee5ApplicasterHipiNeoBusinessLogic("zee");

		Time_ExcelUpdate.TestCaseIDNode("MPWA TC018");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Comment: with App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("User is navigate to App when click on comment: with App");

		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objCommentButton, 5);
		TimeStampclick(MPWAHomePage.objCommentButton, "Comment Button");
		waitUntilElementDisplayed(MPWAHomePage.objOpenApp, 10);
		TimeStampclick(MPWAHomePage.objOpenApp, "Open App Button");
		waitTime(20000);
		getDriver().context("NATIVE_APP");
		waitTime(5000);
		waitUntilElementDisplayed(HipiHomePage.objMusicButtonBelowLeftSide, 20);

		if(TimeStampverifyElementExist(HipiHomePage.objMusicButtonBelowLeftSide, "Description")) {
			logger.info("User is able to navigate the App when click on comment Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the App when click on comment Button");
		}else {
			logger.info("User is not able to navigate the App when click on comment Button");	
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the App when click on comment Button");
			extent.extentLoggerFail("", "User is not able to navigate the App when click on comment Button");

		}

		getDriver().context("WEBVIEW_1");
		waitTime(4000);
		//			getDriver().quit();
	}


	public void CreatorWithApp(String userType) throws Exception
	{
		//			getDriver().context("WEBVIEW_1");
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC019");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Creator: with App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("User is navigate to App when click on Creator: with App");

		//			new AndroidHipiMPWABusinessLogic("chrome");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
		TimeStampSwipe("UP", 1);
		waitTime(4000);
		TimeStampclick(MPWAHomePage.objCreatorButton, "Creator Button");

		waitUntilElementDisplayed(MPWAHomePage.objOpenApp, 10);
		TimeStampclick(MPWAHomePage.objOpenApp, "Open App Button");
		waitTime(10000);
		getDriver().context("NATIVE_APP");
		waitTime(5000);
		waitUntilElementDisplayed(HipiLoginPage.objMaybeLater, 1);
		if(TimeStampverifyElementExist(HipiLoginPage.objMaybeLater, "Maybe Later"))
		{
			TimeStampclick(HipiLoginPage.objMaybeLater, "Maybe Later");
			System.out.println("Update Popup is displayed");
		}else{
			System.out.println("Update Popup is not displayed");
		}
		waitUntilElementDisplayed(HipiHomePage.objDescription, 20);

		if(TimeStampverifyElementExist(HipiHomePage.objDescription, "Description")) {
			logger.info("User is able to navigate the App when click on Creator Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the App when click on Creator Button");
		}else {
			logger.info("User is not able to navigate the App when click on Creator Button");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the App when click on Creator Button");
			extent.extentLoggerFail("", "User is not able to navigate the App when click on Creator Button");

		}

	}		

	public void openOptionINMPWA(String userType) throws Exception {


		Time_ExcelUpdate.TestCaseIDNode("MPWA TC020");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Open Option in feed Screen: with App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("User is navigate to App when click on Option Option: with App");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objOpenButton, 10);

		TimeStampclick(MPWAHomePage.objOpenButton, "Open Button");
		waitTime(10000);
		getDriver().context("NATIVE_APP");
		waitUntilElementDisplayed(HipiLoginPage.objMaybeLater, 1);
		if(TimeStampverifyElementExist(HipiLoginPage.objMaybeLater, "Maybe Later"))
		{
			TimeStampclick(HipiLoginPage.objMaybeLater, "Maybe Later");
			System.out.println("Update Popup is displayed");
		}else{
			System.out.println("Update Popup is not displayed");
		}
		waitUntilElementDisplayed(HipiHomePage.objDescription, 20);

		if(TimeStampverifyElementExist(HipiHomePage.objDescription, "Description")) {
			logger.info("User is able to navigate the App when click on Open Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the App when click on Open Button");
		}else {
			logger.info("User is not able to navigate the App when click on Open Button");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the App when click on Open Button");
			extent.extentLoggerFail("", "User is not able to navigate the App when click on Open Button");

		}
	}
	public void editProfile(String userType) throws Exception {

		Time_ExcelUpdate.TestCaseIDNode("MPWA TC021");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Edit Profile Option in Profile Screen: with App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("Click on Edit Profile Option in Profile Screen: with App");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objOpenButton, 10);

		TimeStampverifyElementPresentAndClick(MPWAHomePage.objProfileIcon,"Profile Icon");
		waitUntilElementDisplayed(MPWAProfilePage.objThreeDots, 5);
		TimeStampverifyElementPresentAndClick(MPWAProfilePage.objEditProfile, "Edit Profile");
		waitUntilElementDisplayed(MPWAHomePage.objOpenApp, 10);
		TimeStampclick(MPWAHomePage.objOpenApp, "Open App Button");
		waitTime(10000);
		getDriver().context("NATIVE_APP");
		if(TimeStampverifyElementExist(HipiLoginPage.objMaybeLater, "Maybe Later"))
		{
			TimeStampclick(HipiLoginPage.objMaybeLater, "Maybe Later");
			System.out.println("Update Popup is displayed");
		}else{
			System.out.println("Update Popup is not displayed");
		}
		waitUntilElementDisplayed(HipiHomePage.objDescription, 5);

		if(TimeStampverifyElementExist(HipiHomePage.objDescription, "Description")) {
			logger.info("User is able to navigate the App when click on Edit Profile Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the App when click on Edit Profile Button");
		}else {
			logger.info("User is not able to navigate the App when click on Edit Profile Button");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the App when click on Edit Profile Button");
			extent.extentLoggerFail("", "User is not able to navigate the App when click on Edit Profile Button");

		}
	}




	public void withOutAppCommentButton(String userType) throws Exception {


		Time_ExcelUpdate.TestCaseIDNode("MPWA TC022");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Comment: with out App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("User is navigate to App when click on comment: with out App");
		Runtime.getRuntime().exec("adb uninstall com.zee5.hipi");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objOpenButton, 10);

		waitUntilElementDisplayed(MPWAHomePage.objCommentButton, 5);
		TimeStampclick(MPWAHomePage.objCommentButton, "Comment Button");
		waitUntilElementDisplayed(MPWAHomePage.objOpenApp, 10);
		TimeStampclick(MPWAHomePage.objOpenApp, "Open App Button");
		waitTime(5000);
		getDriver().context("NATIVE_APP");
		waitTime(5000);

		waitUntilElementDisplayed(MPWAHomePage.objInstallButton, 20);

		if(TimeStampverifyElementExist(MPWAHomePage.objInstallButton, "Install Button")) {
			logger.info("User is able to navigate the Play Store App when click on Comment Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the Play Store App when click on Comment Button");
		}else {
			logger.info("User is not able to navigate the Play Store App when click on Comment Button");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the Play Store App when click on Comment Button");
			extent.extentLoggerFail("", "User is not able to navigate the Play Store App when click on Comment Button");

		}	
	}


	public void withOutAppCreatorButton(String userTyupe) throws Exception {

		Time_ExcelUpdate.TestCaseIDNode("MPWA TC023");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Creator: with out App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("User is navigate to App when click on Creator: with out App");
		Runtime.getRuntime().exec("adb uninstall com.zee5.hipi");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objCreatorButton, 10);
		TimeStampclick(MPWAHomePage.objCreatorButton, "Creator Button");

		waitUntilElementDisplayed(MPWAHomePage.objOpenApp, 10);
		TimeStampclick(MPWAHomePage.objOpenApp, "Open App Button");
		waitTime(5000);
		getDriver().context("NATIVE_APP");
		waitTime(5000);

		waitUntilElementDisplayed(MPWAHomePage.objInstallButton, 20);

		if(TimeStampverifyElementExist(MPWAHomePage.objInstallButton, "Install Button")) {
			logger.info("User is able to navigate the Play Store App when click on Creator Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the Play Store App when click on Creator Button");
		}else {
			logger.info("User is not able to navigate the Play Store App when click on Creator Button");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the Play Store App when click on Creator Button");
			extent.extentLoggerFail("", "User is not able to navigate the Play Store App when click on Creator Button");

		}	


	}

	public void openOptionINMPWAWithoutApp(String userType) throws Exception {


		Time_ExcelUpdate.TestCaseIDNode("MPWA TC024");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Open Option in feed Screen: with out App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("User is navigate to App when click on Option Option: with out App");
		Runtime.getRuntime().exec("adb uninstall com.zee5.hipi");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objOpenButton, 10);
		TimeStampclick(MPWAHomePage.objOpenButton, "Open Button");
		waitTime(10000);
		getDriver().context("NATIVE_APP");
		waitTime(5000);

		waitUntilElementDisplayed(MPWAHomePage.objInstallButton, 20);

		if(TimeStampverifyElementExist(MPWAHomePage.objInstallButton, "Install Button")) {
			logger.info("User is able to navigate the Play Store App when click on open Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the Play Store App when click on open Button");
		}else {
			logger.info("User is not able to navigate the Play Store App when click on open Button");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the Play Store App when click on open Button");
			extent.extentLoggerFail("", "User is not able to navigate the Play Store App when click on open Button");

		}			
	}


	public void WithOutAppEditProfile(String userType) throws Exception {

		Time_ExcelUpdate.TestCaseIDNode("MPWA TC025");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("Click on Edit Profile Option in Profile Screen: with out App");
		//			Time_ExcelUpdate.TestCaseSummaryNode1("Verify Swipe up for next video Animation");
		extent.HeaderChildNode("Click on Edit Profile Option in Profile Screen: with out App");

		Runtime.getRuntime().exec("adb uninstall com.zee5.hipi");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
		TimeStampSwipe("UP", 1);
		waitUntilElementDisplayed(MPWAHomePage.objOpenButton, 10);
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objProfileIcon,"Profile Icon");
		waitUntilElementDisplayed(MPWAProfilePage.objThreeDots, 5);
		TimeStampverifyElementPresentAndClick(MPWAProfilePage.objEditProfile, "Edit Profile");
		waitUntilElementDisplayed(MPWAHomePage.objOpenApp, 10);
		TimeStampclick(MPWAHomePage.objOpenApp, "Open App Button");
		waitTime(10000);
		getDriver().context("NATIVE_APP");
		waitTime(5000);

		waitUntilElementDisplayed(MPWAHomePage.objInstallButton, 20);

		if(TimeStampverifyElementExist(MPWAHomePage.objInstallButton, "Install Button")) {
			logger.info("User is able to navigate the Play Store App when click on Edit Profile Button");	
			//				extent.extentLoggerPass("", "User is able to Logout application");
			TimeStampclick(MPWAHomePage.objInstallButton, "Install Button");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is able to navigate the Play Store App when click on Edit Profile Button");
		}else {
			logger.info("User is not able to navigate the Play Store App when click on Edit Profile Button");	
			//				extent.extentLoggerFail("", "User is not able to Logout application");
			Time_ExcelUpdate.TestCaseSummaryNode1("User is not able to navigate the Play Store App when click on Edit Profile Button");
			extent.extentLoggerFail("", "User is not able to navigate the Play Store App when click on Edit Profile Button");

		}			

	}


	public void launchApp_And_Check_ProgressBar() throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC002");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.ModuleNode("FeedArrival");
		Time_ExcelUpdate.TestCaseSummaryNode1("Feed Screen Progress bar launch time.");
		//			Time_ExcelUpdate.ExpectedProcessingTime("3.75 Sec");
		extent.HeaderChildNode("Launching hipi and check Progress bar");

		//			extent.HeaderChildNode("Launch hipi app");
		getDriver().quit();
		waitTime(3000);
		new Zee5ApplicasterHipiNeoBusinessLogic("Chrome");

		Instant start = Instant.now();
		//			extent.extentLogger("", "clicked on hipi at Time----- "+ start);
		//			extent.extentLogger("", "clicked on hipi at Time----- "+ start);

		TimeStampwaitForElementDisplayediOS(MPWAHomePage.objProgressBar, 10, "Progress Bar");		
		//			screencapture();
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds");
		System.out.println("Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds" );
		extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in milliseconds : "+timeElapsed.toMillis()+" milliSeconds");

		String Processingtimesec=Long.toString(timeElapsed.getSeconds());
		String Processingtimemilli=Long.toString(timeElapsed.toMillis());	
		Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
		extent.extentLogger("", "Feed screen seen  at Time----- " + end);

	}

	public void verifyProgressBar() throws Exception {
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC003");
		//			Time_ExcelUpdate.SlNoNode("1");
		Time_ExcelUpdate.TestCaseSummaryNode1("Verify Progress bar is moving");
		Time_ExcelUpdate.ModuleNode("Verify Progress bar is moving");
		//			Time_ExcelUpdate.ExpectedProcessingTime("3.75 Sec");
		extent.HeaderChildNode("Launching hipi and check Progress bar");

		//			extent.HeaderChildNode("Launch hipi app");
		TimeStampwaitForElementDisplayediOS(MPWAHomePage.objProgressBar, 10, "Progress Bar");	

		String str1 = getAttributValue("style", MPWAHomePage.objProgressBar);
		System.out.println(str1);
		waitTime(5000);
		String str2 = getAttributValue("style", MPWAHomePage.objProgressBarID);
		System.out.println(str2);
		if (!str1.equals(str2)) {
			Time_ExcelUpdate.TestCaseSummaryNode1("progress bar is moving Properly");
			System.out.println("progress bar is moving Properly");
		}else {
			Time_ExcelUpdate.TestCaseSummaryNode1("progress bar is not moving Properly");
			System.out.println("progress bar is not moving Properly");
		}


		//			Instant start = Instant.now();
		//			extent.extentLogger("", "clicked on hipi at Time----- "+ start);
		//			extent.extentLogger("", "clicked on hipi at Time----- "+ start);


		////			screencapture();
		//			Instant end = Instant.now();
		//			Duration timeElapsed = Duration.between(start, end);
		//			extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds");
		//			System.out.println("Time taken in Seconds : "+timeElapsed.getSeconds()+" Seconds" );
		//			extent.HeaderChildNode("the arrival time of feed screen from splash screen " +"Time taken in milliseconds : "+timeElapsed.toMillis()+" milliSeconds");
		//			
		//			String Processingtimesec=Long.toString(timeElapsed.getSeconds());
		//			String Processingtimemilli=Long.toString(timeElapsed.toMillis());	
		//			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
		//			extent.extentLogger("", "Feed screen seen  at Time----- " + end);

	}




	public void loginWithPhoneNumberTime(String userType) throws Exception {

		// navigateToIntroScreen_DisplaylangScreen();
		Time_ExcelUpdate.TestCaseIDNode("MPWA TC005");
		//						Time_ExcelUpdate.SlNoNode("2");
		Time_ExcelUpdate.ModuleNode("Login Module");
		Time_ExcelUpdate.TestCaseSummaryNode1("Login Time");
		Time_ExcelUpdate.ExpectedProcessingTime("7.5 Sec");

		extent.HeaderChildNode("Launching hipi ");
		//						extent.HeaderChildNode("NonSubscribed User");

		TimeStampSwipe("UP", 1);

		waitUntilElementDisplayed(MPWAHomePage.objProfileIcon, 10);
		//click on Profile icon
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
		if (TimeStampverifyElementExist(MPWAProfilePage.objThreeDots, "Three Dots")) {
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objThreeDots, "Three Dots");
			waitTime(3000);
			//			SwipeUntilFindElement(HipiSettingsPage.objLogOut, "Log out Button");
			//			TimeStampSwipe("UP", 2);
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objLogOut, "Log out");
			TimeStampverifyElementPresentAndClick(MPWAProfilePage.objYesCTA, "Yes CTA");
			waitTime(4000);
			TimeStampverifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
		}
		waitTime(4000);
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objUsePhoneOrEmail,"Use Phone Or Email");

		String Username = getParameterFromXML("PhoneNumber");
		String Password = getParameterFromXML("Password");

		waitTime(5000);
		//						hideKeyboard();
		//						TimeStampverifyElementPresentAndClick(MPWALoginPage.objEmailOption, "Email Option");
		//						waitTime(4000);
		//						TimeStampclick(MPWALoginPage.objEmailIdField, "Email Field");
		//						waitTime(4000);
		//						TimeStampverifyElementPresentAndClick(MPWALoginPage.objEmailIdField, "Email Field");
		//						waitTime(4000);
		waitUntilElementDisplayed(MPWALoginPage.objPhoneNumberTextField, 2);
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objPhoneNumberTextField, "Phone Number Text Field");
		TimeStamptype(MPWALoginPage.objPhoneNumberTextField, Username, "Phone Number Field");
		TimeStamphideKeyboard();

		TimeStampverifyElementPresentAndClick(MPWALoginPage.objPasswordField, "Password Field");
		//						hideKeyboard();

		TimeStamptype(MPWALoginPage.objPasswordField, Password, "Password field");
		//						
		TimeStamphideKeyboard();
		waitTime(5000);
		TimeStampverifyElementPresentAndClick(MPWALoginPage.objLoginBtn, "Login Button");
		//						TimeStamptype(MPWALoginPage.objEmailIdField, Username, "Email Field");
		//						TimeStamphideKeyboard();
		//						TimeStampverifyElementPresentAndClick(MPWALoginPage.objLoginBtn, "Login Button");
		Instant start=Instant.now();
		logger.info("Start Time : " + start);
		//						extent.extentLogger("", "<b>Start Time : " + start+"<b>");
		if (waitUntilElementDisplayed(MPWAHomePage.objForYou, 2)) {
			logger.info("User is able to see Home screen");
			//							extent.extentLogger("", "User is able to see Home screen");
			Instant end=Instant.now();
			logger.info("End Time : " + end);
			//							extent.extentLogger("", "<b>End Time : " + end+"<b>");
			Duration processingTime = Duration.between(start, end);
			logger.info("Processing time: " + processingTime);
			String Processingtimesec=Long.toString(processingTime.getSeconds());
			String Processingtimemilli=Long.toString(processingTime.toMillis());	
			Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
			extent.extentLogger("", "<b>Processing time: " + processingTime+"<b>");

			//							extent.HeaderChildNode("<b>Processing time: " + processingTime+"<b>");
		}else {
			logger.error("Processing time calculation failed");
			Time_ExcelUpdate.timeStampNode("Processing time calculation failed");
			extent.extentLoggerFail("", "Processing time calculation failed");	
		}


		//						waitUntilElementDisplayed(HipiHomePage.objForYou, 2000);
		//click on feed button
		TimeStampverifyElementPresentAndClick(MPWAHomePage.objHomeIcon, "Home Icon");

	}





	public void FeedTCs() throws Exception {
		extent.HeaderChildNode("Feed T001");
		System.out.println("T001");
		extent.extentLogger("Feed", "T001");

		verifyElementPresentAndClick(MPWAHomePage.objUnmute, "Unmute ");
		//			verifyElementPresent(MPWAHomePage.objProgressBar,"Progress Bar");
		//			
		//			String str1 = getAttributValue("style", MPWAHomePage.objProgressBar);
		//			System.out.println(str1);
		//			waitTime(5000);
		//			String str2 = getAttributValue("style", MPWAHomePage.objProgressBarID);
		//			System.out.println(str2);
		//			if (!str1.equals(str2)) {
		//				Time_ExcelUpdate.TestCaseSummaryNode1("progress bar is moving Properly");
		//				System.out.println("progress bar is moving Properly");
		//			}else {
		//				Time_ExcelUpdate.TestCaseSummaryNode1("progress bar is not moving Properly");
		//				System.out.println("progress bar is not moving Properly");
		//			}


		verifyElementPresentAndClick(MPWAHomePage.objLikeIcon, "Like Icon");
		waitTime(3000);
		//			verifyElementPresentAndClick(MPWAHomePage.objCommentButton, "Comment icon");
		waitTime(3000);
		//			SwipeUntilFindElement(MPWAHomePage.objShopButton, "UP");
		Swipe("UP", 1);
		Swipe("DOWN", 1);
		waitUntilElementDisplayed(MPWAHomePage.objShopButton, 10);
		verifyElementPresentAndClick(MPWAHomePage.objShopButton, "Shop Button");

		waitTime(3000);
		verifyElementPresent(MPWAHomePage.objOutfit, "Outfit");
		verifyElementPresentAndClick(MPWAHomePage.objSoundname,"Sound name");
		waitTime(4000);

	}


	public void PausePlayer() throws Exception {
		AndroidTouchAction touch = new AndroidTouchAction(getDriver());
		int heightOfScreen = getDriver().manage().window().getSize().getHeight();
		int widthOfScreen = getDriver().manage().window().getSize().getWidth();
		int x = widthOfScreen / 2;
		int y = heightOfScreen / 2;
		touch.tap(PointOption.point(x, y)).perform();
	}
	public void pauseVideo() throws Exception {
		for (int i = 1; i <= 10; i++) {
			try {
				if (verifyIsElementDisplayed(HipiHomePage.objplaybutton)) {
					System.out.println("Video is already paused");
					break;
				} else {
					PausePlayer();

					if (verifyIsElementDisplayed(HipiHomePage.objplaybutton, "playbutton")) {
						System.out.println("Video Paused");
						extent.extentLogger("", "Video Paused");
						break;
					}else{
						Swipe("UP", 1);
						PausePlayer();
						if (verifyIsElementDisplayed(HipiHomePage.objplaybutton)) {
							System.out.println("Video is already paused");
							break;
						}

					}
				}
			} catch (Exception e) {
				if (verifyIsElementDisplayed(HipiFeedPage.objplaybutton)) {
					System.out.println("Video is already paused");
					break;
				} else {
					PausePlayer();

					if (verifyIsElementDisplayed(HipiFeedPage.objplaybutton, "playbutton")) {
						System.out.println("Video Paused");
						extent.extentLogger("", "Video Paused");
						break;
					}else{
						Swipe("UP", 1);
						PausePlayer();
						if (verifyIsElementDisplayed(HipiFeedPage.objplaybutton)) {
							System.out.println("Video is already paused");
							break;
						}

					}
				}
			}

		}

	}

	
	
	
	
	public void LoginAndNivigateToHipi(String loginMethod) throws Exception {
		extent.HeaderChildNode("Login Functionality");
		String pUserType = getParameterFromXML("userType");

		extent.extentLogger("Login Type", "Login Type : " + pUserType);

		switch (loginMethod) {
		case "Guest":
			extent.HeaderChildNode("Guest User");
			extent.extentLogger("Accessing the application as Guest user", "Accessing the application as Guest user");
			waitTime(5000);
//			if(verifyElementExist(HipiLoginPage.objMaybeLater, "Maybe Later"))
//			{
//				TimeStampclick(HipiLoginPage.objMaybeLater, "Maybe Later");
//				System.out.println("Update Popup is displayed");
//			}else{
//				System.out.println("Update Popup is not displayed");
//			}
			waitUntilElementDisplayed(MPWAHomePage.objForYou, 10);
			break;

		case "NonSubscribedUser":
			extent.HeaderChildNode("Non Sub User");
			// navigateToIntroScreen_DisplaylangScreen();
//			Time_ExcelUpdate.TestCaseIDNode("TC002");
////			Time_ExcelUpdate.SlNoNode("2");
//			Time_ExcelUpdate.ModuleNode("Login Module");
//			Time_ExcelUpdate.TestCaseSummaryNode1("Login Time");
			Swipe("UP", 1);

			waitUntilElementDisplayed(MPWAHomePage.objProfileIcon, 10);
			//click on Profile icon
			verifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
			if (verifyElementExist(MPWAProfilePage.objThreeDots, "Three Dots")) {
				verifyElementPresentAndClick(MPWAProfilePage.objThreeDots, "Three Dots");
				waitTime(3000);
				//			SwipeUntilFindElement(HipiSettingsPage.objLogOut, "Log out Button");
				//			TimeStampSwipe("UP", 2);
				verifyElementPresentAndClick(MPWAProfilePage.objLogOut, "Log out");
				verifyElementPresentAndClick(MPWAProfilePage.objYesCTA, "Yes CTA");
				waitTime(4000);
				verifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
			}
			waitTime(4000);
			verifyElementPresentAndClick(MPWALoginPage.objUsePhoneOrEmail,"Use Phone Or Email");

			String Username = getParameterFromXML("NonsubscribedUserName");
			String Password = getParameterFromXML("NonsubscribedPassword");

			waitTime(5000);
			//					hideKeyboard();
			//					TimeStampverifyElementPresentAndClick(MPWALoginPage.objEmailOption, "Email Option");
			//					waitTime(4000);
			//					TimeStampclick(MPWALoginPage.objEmailIdField, "Email Field");
			//					waitTime(4000);
			verifyElementPresentAndClick(MPWALoginPage.objEmailIdField, "Email Field");
			waitTime(4000);

			type(MPWALoginPage.objEmailIdField, Username, "Email Field");
			hideKeyboard();
			verifyElementPresentAndClick(MPWALoginPage.objProceed, "Proceed Button");
			waitUntilElementDisplayed(MPWALoginPage.objLoginWithPassword, 2);
			verifyElementPresentAndClick(MPWALoginPage.objLoginWithPassword, "Login With Password");

			verifyElementPresentAndClick(MPWALoginPage.objPasswordField, "Password Field");
			//					hideKeyboard();

			type(MPWALoginPage.objPasswordField, Password, "Password field");
			//					
			hideKeyboard();
			waitTime(5000);
			verifyElementPresentAndClick(MPWALoginPage.objLoginBtn, "Login Button");
			//					TimeStamptype(MPWALoginPage.objEmailIdField, Username, "Email Field");
			//					TimeStamphideKeyboard();
			//					TimeStampverifyElementPresentAndClick(MPWALoginPage.objLoginBtn, "Login Button");
			Instant start=Instant.now();
			logger.info("Start Time : " + start);
			//					extent.extentLogger("", "<b>Start Time : " + start+"<b>");
			if (waitUntilElementDisplayed(MPWAHomePage.objForYou, 2)) {
				logger.info("User is able to see Home screen");
				//						extent.extentLogger("", "User is able to see Home screen");
				Instant end=Instant.now();
				logger.info("End Time : " + end);
				//						extent.extentLogger("", "<b>End Time : " + end+"<b>");
				Duration processingTime = Duration.between(start, end);
				logger.info("Processing time: " + processingTime);
				String Processingtimesec=Long.toString(processingTime.getSeconds());
				String Processingtimemilli=Long.toString(processingTime.toMillis());	
				Time_ExcelUpdate.timeStampNode(Processingtimesec+"."+Processingtimemilli + " Sec");
				extent.extentLogger("", "<b>Processing time: " + processingTime+"<b>");

				//						extent.HeaderChildNode("<b>Processing time: " + processingTime+"<b>");
			}
			else {
				logger.error("Processing time calculation failed");
				Time_ExcelUpdate.timeStampNode("Processing time calculation failed");
				extent.extentLoggerFail("", "Processing time calculation failed");

			}
		}
	}
			
			
			
			
			
	
	
	



	public void refreshTheListOnContents(String userType) throws Exception {

		extent.HeaderChildNode("Guest User - Check if User is able to refresh the list on contents in \"For You\" tab of Hipi");
		System.out.println("T003");
		extent.extentLogger("Feed", "T003");

		String userName = getText(MPWAHomePage.objUserName);

		Swipe("DOWN", 1);

		String userName1 = getText(MPWAHomePage.objUserName);

		if (!userName.equals(userName1)) {
			logger.info("User is able to refresh the list on contents in For You tab of Hipi Feed");	
			extent.extentLoggerPass("", "User is able to refresh the list on contents in For You tab of Hipi Feed");
		}else {
			logger.info("User is unable to refresh the list on contents in For You tab of Hipi Feed");	
			extent.extentLoggerFail("", "User is unable to refresh the list on contents in For You tab of Hipi Feed");
		}
	}



	public void pauseAndPlayTheVideo(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("Guest User - Check if User is able to pause and play the video in \"For You\" tab");
			System.out.println("T004");
			extent.extentLogger("Feed", "T004");
		}else {
			extent.HeaderChildNode("T007 - Logged in User - Check if user is able to resume a paused video by tapping on it");
			System.out.println("T007");
			extent.extentLogger("", "T007");
		}

		PausePlayer();
		pauseVideo();

		if (verifyElementExist(HipiHomePage.objplaybutton, "Play button")) {
			logger.info("Hipi feed screen:" + "Paused the Player");
			extent.extentLoggerPass("Hipi feed screen:", "Paused the Player");
			click(HipiHomePage.objplaybutton, "Play button");
		} else {
			extent.extentLoggerFail("Hipi feed screen:", "Dint pause the player");
			logger.info("Hipi feed screen:" + "Dint pause the player");
		}
	}

	public void ForYouAndFollowingTabs__T005(String userType) throws Exception {

		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T005 - Guest User - Check if  \"For You\" and \"Following\" tabs are displayed at top of the screen in \"Feed\" section");
			System.out.println("T005");
			extent.extentLogger("For You", "T005");
		}else {
			extent.HeaderChildNode("T019 - Logged in User - Check if  \"For You\" and \"Following\" tabs are displayed at top of the screen in \"Feed\" section");
			System.out.println("T019");
			extent.extentLogger("For You", "T019");
		}	

		if (verifyElementPresent(MPWAHomePage.objForYou, "For you")) {
			logger.info("User is able to verify the For you option in top section,Expected Behaviour");
			extent.extentLoggerPass("For You","User is able to verify the For you option in top section,Expected Behaviour");
		} else {
			logger.info("User is not able to verify the For you option in top section");
			extent.extentLoggerFail("For You","User is not able to verify the For you option in top section");

		}
		if (verifyElementPresent(MPWAHomePage.objFollowingButton, "Following Option")) {
			logger.info("User is able to verify the Following option in top section,Expected Behaviour");
			extent.extentLoggerPass("For You","User is able to verify the Following option in top section,Expected Behaviour");
		} else {
			logger.info("User is not able to verify the Following option in top section");
			extent.extentLoggerFail("For You","User is not able to verify the Following option in top section");
		}
	}
	public void validateDescription() throws Exception {
		if(verifyElementPresent(MPWAHomePage.objUserName, "Description")){
			logger.info("Hipi feed screen:" + "User is redirect back to Feed from  ever section");
			extent.extentLoggerPass("Hipi feed screen:", "User is redirect back to Feed from  ever section");
		} else {
			extent.extentLoggerFail("Hipi feed screen:", "User is redirect back to Feed from  ever section");
			logger.info("Hipi feed screen:" + "User is redirect back to Feed from  ever section");
		}

	}

	public void tappingOnBbackFromAnyKeySection__T006(String userType) throws Exception {

		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T006 - Logged in User - Check by tapping on back from any key section, user is navigated to Feed screen");
			System.out.println("T006");
			extent.extentLogger("", "T006");
			}else {
				extent.HeaderChildNode("T021 - Guest User - Verify for user is able to see \"SHOP\" button when they coming from any other tabs (Discover/Me) and watching shoppable video.");
				System.out.println("T021");
				extent.extentLogger("", "T021");
			}

			verifyElementPresentAndClick(HipiHomePage.objDiscoverButton, "Discover Button");
			waitTime(3000);
			Back(1);
			waitTime(3000);
			validateDescription();
			verifyElementPresentAndClick(AMDHomePage.objProfileIcon, "Profile Icon");
			waitTime(3000);
			Back(1);
			waitTime(3000);
			validateDescription();
			verifyElementPresentAndClick(HipiHomePage.objVideoCreateIcon, "Create button");	
			waitTime(5000);
			Back(1);
			waitTime(3000);
			validateDescription();
	
	}

	public void  userIsAbleToSeeTheShareIconForYouScreen__T012(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T012 - Guest User -  Check if user is able to see the \"Share\" icon \"For You\" screen");
			System.out.println("T012");
			extent.extentLogger("For You >> Share", "T012");

			if (verifyElementPresent(MPWAHomePage.objShareICON, "Share ICON")){
				logger.info("User is able to see the Share ICON");	
				extent.extentLoggerPass("", "User is able to see the Share ICON");	
			}else {
				logger.info("User is not able to see the Share ICON");	
				extent.extentLoggerFail("", "User is not able to see the Share ICON");
			}
		}	
	}



	public void CheckTheLoginPopUp(String userType) throws Exception {

		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T013- Guest User -  Check if user is able to see the \"Share\" icon \"For You\" screen");
			System.out.println("T013");
			extent.extentLogger("For You >> Share", "T013");

			verifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
			waitTime(4000);
			if(verifyElementPresent(MPWALoginPage.objLoginORSignup, "Email Field")) {
				logger.info("User is able to see the Email Field");	
				extent.extentLoggerPass("", "User is able to see the Email Field");	
			}else {
				logger.info("User is not able to see the Email Field");	
				extent.extentLoggerFail("", "User is not able to see the Email Field");
			}	
		}
	}


	
	
	
	
	
	public void verifyUnmuteButton(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T001- Guest User -  Check if user is able to see the Unmute icon \"For You\" screen");
			System.out.println("T001");
			extent.extentLogger("For You", "T001");
		}else {
			extent.HeaderChildNode("T002- Logged in User -  Check if user is able to see the Unmute icon \"For You\" screen");
			System.out.println("T002");
			extent.extentLogger("For You", "T002");
		}
		
		if(verifyElementPresent(MPWAHomePage.objUnmute, "Unmute Button")) {
			logger.info("User is able to see the Unmute Button");	
			extent.extentLoggerPass("", "User is able to see the Unmute Button");	
		}else {
			logger.info("User is not able to see the Unmute Button");	
			extent.extentLoggerFail("", "User is not able to see the Unmute Button");
		}		
		TimeStampnavigateToHomePage();
	}
	
	
	public void verifyForYou(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T003- Guest User -  Check if user is able to see the For You icon \"For You\" screen");
			System.out.println("T003");
			extent.extentLogger("For You", "T003");
		}else {
			extent.HeaderChildNode("T004- Logged in User -  Check if user is able to see the For You  icon \"For You\" screen");
			System.out.println("T004");
			extent.extentLogger("For You", "T004");
		}	
		if(verifyElementPresent(MPWAHomePage.objForYou, "For You Button")) {
			logger.info("User is able to see the For You Button");	
			extent.extentLoggerPass("", "User is able to see the For You Button");	
		}else {
			logger.info("User is not able to see the For You Button");	
			extent.extentLoggerFail("", "User is not able to see the For You Button");
		}		
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void verifyFollowing(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T005- Guest User -  Check if user is able to see the Following icon \"For You\" screen");
			System.out.println("T005");
			extent.extentLogger("Following", "T005");
		}else {
			extent.HeaderChildNode("T006- Logged in User -  Check if user is able to see the Following  icon \"For You\" screen");
			System.out.println("T006");
			extent.extentLogger("Following", "T006");
		}	
		if(verifyElementPresent(MPWAHomePage.objFollowingButton, "Following Button")) {
			logger.info("User is able to see the Following Button");	
			extent.extentLoggerPass("", "User is able to see the Following Button");	
		}else {
			logger.info("User is not able to see the Following Button");	
			extent.extentLoggerFail("", "User is not able to see the Following Button");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	public void PlusIcon(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T007- Guest User -  Check if user is able to see the + icon \"For You\" screen");
			System.out.println("T007");
			extent.extentLogger("+ Icon", "T007");
		}else {
			extent.HeaderChildNode("T008- Logged in User -  Check if user is able to see the +  icon \"For You\" screen");
			System.out.println("T008");
			extent.extentLogger("+ Icon", "T008");
		}	
		if(verifyElementPresent(MPWAHomePage.objplusICON, "+ Button")) {
			logger.info("User is able to see the + Button");	
			extent.extentLoggerPass("", "User is able to see the + Button");	
		}else {
			logger.info("User is not able to see the + Button");	
			extent.extentLoggerFail("", "User is not able to see the + Button");
		}		
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void verifyLikeIcon(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T009- Guest User -  Check if user is able to see the Like icon \"For You\" screen");
			System.out.println("T009");
			extent.extentLogger("Like Icon", "T009");
		}else {
			extent.HeaderChildNode("T010- Logged in User -  Check if user is able to see the Like  icon \"For You\" screen");
			System.out.println("T010");
			extent.extentLogger("Like Icon", "T010");
		}	
		
		if(verifyElementPresent(MPWAHomePage.objLikeIcon, "Like Button")) {
			logger.info("User is able to see the Like Button");	
			extent.extentLoggerPass("", "User is able to see the Like Button");	
		}else {
			logger.info("User is not able to see the Like Button");	
			extent.extentLoggerFail("", "User is not able to see the Like Button");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void verifyProfileThumbnail(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T011- Guest User -  Check if user is able to see the Profile Thumbnail icon For You screen");
			System.out.println("T011");
			extent.extentLogger("Profile Thumbnail icon", "T011");
		}else {
			extent.HeaderChildNode("T012- Logged in User -  Check if user is able to see the Profile Thumbnail icon For You screen");
			System.out.println("T012");
			extent.extentLogger("Profile Thumbnail icon", "T012");
		}	

		if(verifyElementPresent(MPWAHomePage.objProfileThumbnail, "Profile Thumbnail")) {
			logger.info("User is able to see the Profile Thumbnail Button");	
			extent.extentLoggerPass("", "User is able to see the Profile Thumbnail Button");	
		}else {
			logger.info("User is not able to see the Profile Thumbnail Button");
			extent.extentLoggerFail("", "User is not able to see the Profile Thumbnail Button");
		}
		TimeStampnavigateToHomePage();
}
	
	
	
	public void verifyComment(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T013- Guest User -  Check if user is able to see the Comment icon For You screen");
			System.out.println("T013");
			extent.extentLogger("Comment icon", "T013");
		}else {
			extent.HeaderChildNode("T014- Logged in User -  Check if user is able to see the Comment  icon For You screen");
			System.out.println("T014");
			extent.extentLogger("Comment icon", "T014");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objCommentButton, "Comment Button")) {
			logger.info("User is able to see the Comment Button");	
			extent.extentLoggerPass("", "User is able to see the Comment Button");	
		}else {
			logger.info("User is not able to see the Comment Button");
			extent.extentLoggerFail("", "User is not able to see the Comment Button");
		}
		TimeStampnavigateToHomePage();
	}
	
	
	
	
	public void verifyShareIcon(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T015- Guest User -  Check if user is able to see the Share icon For You screen");
			System.out.println("T015");
			extent.extentLogger("Share  icon", "T015");
		}else {
			extent.HeaderChildNode("T016- Logged in User -  Check if user is able to see the Share  icon For You screen");
			System.out.println("T016");
			extent.extentLogger("Share  iconLike Icon", "T016");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objShareICON, "Share Button")) {
			logger.info("User is able to see the Share Button");	
			extent.extentLoggerPass("", "User is able to see the Share Button");	
		}else {
			logger.info("User is not able to see the Share Button");
			extent.extentLoggerFail("", "User is not able to see the Share Button");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void verifyEmbedIcon(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T017- Guest User -  Check if user is able to see the Embed icon For You screen");
			System.out.println("T017");
			extent.extentLogger("Embed  icon", "T017");
		}else {
			extent.HeaderChildNode("T018- Logged in User -  Check if user is able to see the Embed  icon For You screen");
			System.out.println("T018");
			extent.extentLogger("Embed  icon", "T018");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objEmbedIcon, "Embed Button")) {
			logger.info("User is able to see the Embed Button");	
			extent.extentLoggerPass("", "User is able to see the Embed Button");	
		}else {
			logger.info("User is not able to see the Embed Button");
			extent.extentLoggerFail("", "User is not able to see the Embed Button");
		}	
		TimeStampnavigateToHomePage();
	}
	
	public void verifyUserName(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T019- Guest User -  Check if user is able to see the User Name icon For You screen");
			System.out.println("T019");
			extent.extentLogger("User Name  icon", "T019");
		}else {
			extent.HeaderChildNode("T020- Logged in User -  Check if user is able to see the User Name  icon For You screen");
			System.out.println("T020");
			extent.extentLogger("User Name  icon", "T020");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objUserName, "User Name")) {
			logger.info("User is able to see the User Name Button");	
			extent.extentLoggerPass("", "User is able to see the User Name Button");	
		}else {
			logger.info("User is not able to see the User Name Button");
			extent.extentLoggerFail("", "User is not able to see the User Name Button");
		}	
		TimeStampnavigateToHomePage();
	}
	
	public void verifyFollowIcon(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T021- Guest User -  Check if user is able to see the Follow icon For You screen");
			System.out.println("T021");
			extent.extentLogger("Follow icon", "T021");
		}else {
			extent.HeaderChildNode("T022- Logged in User -  Check if user is able to see the Follow icon For You screen");
			System.out.println("T022");
			extent.extentLogger("Follow icon", "T022");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objFollowIcon, "Follow icon")) {
			logger.info("User is able to see the Follow Button");	
			extent.extentLoggerPass("", "User is able to see the Follow Button");	
		}else {
			logger.info("User is not able to see the Follow Button");
			extent.extentLoggerFail("", "User is not able to see the Follow Button");
		}	
		TimeStampnavigateToHomePage();
	}
	
	public void verifyVideoDescription(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T023- Guest User -  Check if user is able to see the Video Description For You screen");
			System.out.println("T023");
			extent.extentLogger("Video Description", "T023");
		}else {
			extent.HeaderChildNode("T024- Logged in User -  Check if user is able to see the Video Description For You screen");
			System.out.println("T024");
			extent.extentLogger("Video Description", "T024");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objVideoDescription, "Video Description")) {
			logger.info("User is able to see the Video Description");	
			extent.extentLoggerPass("", "User is able to see the Video Description");	
		}else {
			logger.info("User is not able to see the Video Description");
			extent.extentLoggerFail("", "User is not able to see the Video Description");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void verifySoundName(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T025- Guest User -  Check if user is able to see the Sound Description For You screen");
			System.out.println("T025");
			extent.extentLogger("Sound Name", "T025");
		}else {
			extent.HeaderChildNode("T026- Logged in User -  Check if user is able to see the Sound Description For You screen");
			System.out.println("T026");
			extent.extentLogger("Sound Name", "T026");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objSoundname, "Sound Description")) {
			logger.info("User is able to see the Sound Description");	
			extent.extentLoggerPass("", "User is able to see the Sound Description");	
		}else {
			logger.info("User is not able to see the Sound Description");
			extent.extentLoggerFail("", "User is not able to see the Sound Description");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	public void verifyHomeIcon(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T027- Guest User -  Check if user is able to see the HomeIcon For You screen");
			System.out.println("T027");
			extent.extentLogger("Home Icon", "T027");
		}else {
			extent.HeaderChildNode("T028- Logged in User -  Check if user is able to see the HomeIcon For You screen");
			System.out.println("T028");
			extent.extentLogger("Home Icon", "T028");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objHomeIcon, "Home Icon")) {
			logger.info("User is able to see the Home Icon");	
			extent.extentLoggerPass("", "User is able to see the Home Icon");	
		}else {
			logger.info("User is not able to see the Home Icon");
			extent.extentLoggerFail("", "User is not able to see the Home Icon");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void verifyExploreIcon(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T029- Guest User -  Check if user is able to see the Explore Icon For You screen");
			System.out.println("T029");
			extent.extentLogger("Explore Icon", "T029");
		}else {
			extent.HeaderChildNode("T030- Logged in User -  Check if user is able to see the  Explore Icon For You screen");
			System.out.println("T030");
			extent.extentLogger("Explore Icon", "T030");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objDiscoverButton, "Explore Icon")) {
			logger.info("User is able to see the Explore Icon");	
			extent.extentLoggerPass("", "User is able to see the Explore Icon");	
		}else {
			logger.info("User is not able to see the Explore Icon");
			extent.extentLoggerFail("", "User is not able to see the Explore Iconn");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	public void verifyCreateIcon(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T031- Guest User -  Check if user is able to see the Create Icon For You screen");
			System.out.println("T031");
			extent.extentLogger("Create Icon", "T031");
		}else {
			extent.HeaderChildNode("T032- Logged in User -  Check if user is able to see the  Create Icon For You screen");
			System.out.println("T032");
			extent.extentLogger("Create Icon", "T032");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objCreatorButton, "Create Icon")) {
			logger.info("User is able to see the Create Icon");	
			extent.extentLoggerPass("", "User is able to see the Create Icon");	
		}else {
			logger.info("User is not able to see the Create Icon");
			extent.extentLoggerFail("", "User is not able to see the Create Iconn");
		}
		TimeStampnavigateToHomePage();
	}
	
	public void verifyProfileIcon(String userType) throws Exception {	
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T033- Guest User -  Check if user is able to see the Profile Icon For You screen");
			System.out.println("T033");
			extent.extentLogger("Profile Icon", "T033");
		}else {
			extent.HeaderChildNode("T034- Logged in User -  Check if user is able to see the  Profile Icon For You screen");
			System.out.println("T034");
			extent.extentLogger("Profile Icon", "T034");
		}	
	
		if(verifyElementPresent(MPWAHomePage.objProfileIcon, "Profile Icon")) {
			logger.info("User is able to see the Profile Icon");	
			extent.extentLoggerPass("", "User is able to see the Profile Icon");	
		}else {
			logger.info("User is not able to see the Profile Icon");
			extent.extentLoggerFail("", "User is not able to see the Profile Iconn");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void videoUIRepate(String userType) throws Exception {
	
		extent.HeaderChildNode("Video is repeting");

		
		waitUntilElementDisplayed(MPWAHomePage.objUnmute, 5);		
		for(int i=0; i<=5;i++) {
			
			click(MPWAHomePage.objEmbedIcon, "Embed Icon");
			waitTime(5000);	
			String videoid = getText(MPWAHomePage.objVideoUIID);
			String Username = getParameterFromXML("NonsubscribedUserName");
			creatCSV(videoid,Username,getListOfDevicesConnected());
			
//			extent.extentLoggerPass("", videoid+","+Username+"," +getListOfDevicesConnected()+",");
//			Time_ExcelUpdate.TestCaseSummaryNode1( videoid+","+Username+"," +getListOfDevicesConnected()+",");
//			Back(1);
			click(MPWAHomePage.objCloseButtonEmbed, "Close Button");
			waitTime(5000);
			TimeStampSwipe("UP", 1);
			waitTime(5000);
		}
		
	}
	static final String xlpath2 = System.getProperty("user.dir") + "/Description_Data"+"/"+ "TextData" +".txt";
	
	public static void creatCSV(String description,String Username,String UDID) throws IOException  {

		List list_file1 = new ArrayList();

		    String lineText ;
		    Path path = Paths.get(xlpath2);
			String content = null;
			 try {
				 List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		            content = String.join(System.lineSeparator(), lines);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
//			System.out.println(content);
			
			boolean areEqual = true;
			
			while(content  !=null) {
				
				 if(content.contains(description + "," + Username + "," + UDID  + ",")) {
					System.out.println("Fail");
//					areEqual = false;
					extent.extentLoggerFail("", "User is able to see same Video ID");
//					Time_ExcelUpdate.TestCaseSummaryNode1(description+","+Username+"," +getListOfDevicesConnected()+",");
					logger.info("User is able to see same Video ID");
					Time_ExcelUpdate.TestCaseSummaryNode1("Video is not getting repeated on Android Hipi "+ description+","+Username+"," +getListOfDevicesConnected()+",");

					break;
				}else {
					System.out.println("Pass");
					areEqual = true;
					extent.extentLoggerPass("", "User is not able to see same Video ID");
					logger.info("User is not able to see same Video ID");
					Time_ExcelUpdate.TestCaseSummaryNode1("Video is getting repeated on Android Hipi "+ description+","+Username+"," +getListOfDevicesConnected()+",");

					break;
				}
				 
			}
			System.out.println(description + "," + Username + "," + UDID + ",");
			
			try {
//				File dir = new File(System.getProperty("user.dir") + "\\Analysed_Reports");
				File dir = new File(System.getProperty("user.dir") + "/Description_Data"+"/");
				System.out.println("===creating Excel");
				
				if (!dir.isDirectory()) {
					dir.mkdir();
				}
				FileWriter file = new FileWriter(xlpath2,true);
				BufferedWriter br = new BufferedWriter(file);
				br.write("\n");
				br.write(description+ ",");
				br.write(Username+ ",");
				br.write(UDID+ ",");					
				br.flush();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
				}
		}			
	
	
	public static String getListOfDevicesConnected() {
		String deviceID = null;
		try {
			String cmd2 = "adb devices";
			Process p1 = Runtime.getRuntime().exec(cmd2);
			BufferedReader br = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			String s = "";
			Thread.sleep(1000);
			while (!(s = br.readLine()).isEmpty()) {
				if (!s.equals("List of devices attached")) {
					if(!s.contains(".")) {
						deviceID = s.replaceAll("device", "").trim();
						System.out.println(deviceID);
					}
				}
			}
			return deviceID;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return deviceID;
	}
	
	
	
	
	public void clickOnPlushIconGuestUser(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T035- Guest in User -  Check if user is able to Click on + Icon For You screen");
			System.out.println("T035");
			extent.extentLogger("+ Icon", "T035");
		
			verifyElementPresentAndClick(MPWAHomePage.objProfileThumbnail, "Plush Icon");
			waitTime(3000);
			verifyLoginPopup();	
			TimeStampnavigateToHomePage();
	}
}
	
	
	public void clickOnPlushIcon(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T036- Logged in User -  Check if user is able to Click on + Icon For You screen");
			System.out.println("T036");
			extent.extentLogger("+ Icon", "T036");
		
			click(MPWAHomePage.objProfileThumbnail, "Plush Icon");
			waitTime(8000);
			
			if(verifyElementExist(MPWAHomePage.objplusICON, "+ Icon")) {
				logger.info("User is able to see the + Icon");	
				extent.extentLoggerFail("", "User is able to see the + Icon");	
			}else {
				logger.info("User is not able to see the + Icon");
				extent.extentLoggerPass("", "User is not able to see the + Iconn");
			}	
			TimeStampnavigateToHomePage();
	}
}
	
	public void verifyLoginPopup() throws Exception {
		waitUntilElementDisplayed(MPWALoginPage.objLoginORSignup, 2);
		if(verifyElementPresent(MPWALoginPage.objLoginORSignup, "Login or Signup")) {
			logger.info("User is able to see the Login or Signup");	
			extent.extentLoggerPass("", "User is able to see the Login or Signup");	
		}else {
			logger.info("User is not able to see the Login or Signup");
			extent.extentLoggerFail("", "User is not able to see the Login or Signup");
		}	
		click(MPWALoginPage.objCloseIcon, "Close Icon");
		waitUntilElementDisplayed(MPWAHomePage.objForYou, 2);
	}
	
	
	
	public void userProfileThumbnail(String userType) throws Exception {

		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T037- Guest in User -  Check if user is able to Click on Profile Thumbnail For You screen");
			System.out.println("T037");
			extent.extentLogger("Profile Thumbnail", "T037");

			click(MPWAHomePage.objProfileThumbnail, "Profile Thumbnail");
			waitTime(3000);
			verifyLoginPopup();	

		}	
	}
	
	
	public void userProfileThumbnailNonSubUser(String userType) throws Exception {

		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T038- Logged in User -  Check if user is able to Click on Profile Thumbnail For You screen");
			System.out.println("T038");
			extent.extentLogger("Profile Thumbnail", "T038");
		}else {
			extent.HeaderChildNode("T037- Guest in User -  Check if user is able to Click on Profile Thumbnail For You screen");
			System.out.println("T037");
			extent.extentLogger("Profile Thumbnail", "T037");
		}

			click(MPWAHomePage.objProfileThumbnail, "Profile Thumbnail");
			waitTime(3000);	
			verifyUserProfile();
			
		}
	
	
	
	public void verifyUserProfile() throws Exception {
		waitUntilElementDisplayed(MPWAProfilePage.objProfileImage, 2);
		if(verifyElementPresent(MPWAProfilePage.objProfileImage, "Profile Image")) {
			logger.info("User is able to see the Profile Image");	
			extent.extentLoggerPass("", "User is able to see the Profile Image");	
		}else {
			logger.info("User is not able to see the Profile Image");
			extent.extentLoggerFail("", "User is not able to see the Profile Image");
		}	
//		Back(1);
//		waitUntilElementDisplayed(MPWAHomePage.objForYou, 2);
	}
	
	
	public void verifyuserName(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T040- Logged in User -  Check if user is able to Click on User Name For You screen");
			System.out.println("T040");
			extent.extentLogger("User Name", "T040");
		}else {
			extent.HeaderChildNode("T039- Guest in User -  Check if user is able to Click on User Name For You screen");
			System.out.println("T039");
			extent.extentLogger("User Name", "T039");
		}
		Swipe("DOWN", 1);
		verifyElementPresentAndClick(MPWAHomePage.objUserName, "User Name");
		waitTime(3000);
		verifyUserProfile();	
		TimeStampnavigateToHomePage();
	}

	
	
	public void clickOnFollowNonSubUser(String userType) throws Exception {
		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T042- Logged in User -  Check if user is able to Click on Follow icon For You screen");
			System.out.println("T042");
			extent.extentLogger("Follow icon", "T042");
			
			
			verifyElementPresentAndClick(MPWAHomePage.objFollowIcon, "Follow Icon");
			waitTime(2000);
			if(verifyElementExist(MPWAHomePage.objFollowIcon, "Follow Icon")) {
				logger.info("User is able to see the Follow Icon");	
				extent.extentLoggerFail("", "User is able to see the Follow Icon");	
			}else {
				logger.info("User is not able to see the Follow Icon");
				extent.extentLoggerPass("", "User is not able to see the Follow Icon");
			}	
			TimeStampnavigateToHomePage();
		}
	}
	
	
	
	
	
	public void clickOnFollowGuestUser(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T041- Guest in User -  Check if user is able to Click on Follow icon For You screen");
			System.out.println("T041");
			extent.extentLogger("Follow icon", "T041");
			
			Swipe("UP", 1);
			verifyElementPresentAndClick(MPWAHomePage.objFollowIcon, "Follow Icon");
			waitTime(2000);
			verifyLoginPopup();
			TimeStampnavigateToHomePage();
		}
	}
	
	
	
	
	public void clickOnCommentIcon(String userType) throws Exception {
		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T044- Logged in User -  Check if user is able to Click on Comment icon For You screen");
			System.out.println("T044");
			extent.extentLogger("Comment icon", "T044");
		}else {
			extent.HeaderChildNode("T043- Guest in User -  Check if user is able to Click on Comment icon For You screen");
			System.out.println("T043");
			extent.extentLogger("Comment icon", "T043");
		}
		
		verifyElementPresentAndClick(MPWAHomePage.objCommentButton, "Comment Icon");

		if(verifyElementPresent(MPWAHomePage.objOpenApp, "Open app")) {
			logger.info("User is able to see the Open app");	
			extent.extentLoggerPass("", "User is able to see the Open app");	
		}else {
			logger.info("User is not able to see the Open app");
			extent.extentLoggerFail("", "User is not able to see the Open app");
		}		
		click(MPWAHomePage.objNotNow, "Not now ");
		TimeStampnavigateToHomePage();
	}
	
	
	
	
	public void clickOnShareIcon(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T045- Guest in User -  Check if user is able to Click on Share icon For You screen");
			System.out.println("T045");
			extent.extentLogger("Share icon", "T045");
		}else {
			extent.HeaderChildNode("T046- Logged in User -  Check if user is able to Click on Share icon For You screen");
			System.out.println("T046");
			extent.extentLogger("Share icon", "T046");
		}
		verifyElementPresentAndClick(MPWAHomePage.objShareICON, "Share Icon");
		waitUntilElementDisplayed(MPWAHomePage.objShareScreen, 2);
		if(verifyElementPresent(MPWAHomePage.objShareScreen, "Share Popup")) {
			logger.info("User is able to see the Share Popup");	
			extent.extentLoggerPass("", "User is able to see the Share Popup");	
		}else {
			logger.info("User is not able to see the Share Popup");
			extent.extentLoggerFail("", "User is not able to see the Share Popup");
		}	
		TimeStampnavigateToHomePage();
		}
	
	
	public void verifyEmbed(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T047- Guest in User -  Check if user is able to Click on Embed icon For You screen");
			System.out.println("T047");
			extent.extentLogger("Embed icon", "T047");
		}else {
			extent.HeaderChildNode("T048- Logged in User -  Check if user is able to Click on Embed icon For You screen");
			System.out.println("T048");
			extent.extentLogger("Embed icon", "T048");
		}
		
		verifyElementPresentAndClick(MPWAHomePage.objEmbedIcon, "Embed Button");
		waitTime(3000);
		
		if(verifyElementPresent(MPWAHomePage.objVideoUIID, "Embed Popup")) {
			logger.info("User is able to see the Embed Popup");	
			extent.extentLoggerPass("", "User is able to see the Embed Popup");	
		}else {
			logger.info("User is not able to see the Embed Popup");
			extent.extentLoggerFail("", "User is not able to see the Embed Popup");
		}		
		click(MPWAHomePage.objCloseButtonEmbed, "Close button");
		TimeStampnavigateToHomePage();
	}
	
	
	
	
	public void verifyMoreIcon(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T049- Guest in User -  Check if user is able to see More icon For You screen");
			System.out.println("T049");
			extent.extentLogger("More icon", "T049");
		}else {
			extent.HeaderChildNode("T050- Logged in User -  Check if user is able to see More icon For You screen");
			System.out.println("T050");
			extent.extentLogger("More icon", "T050");
		}
		
		
		verifyElementPresentAndClick(MPWAHomePage.objMoreIcon, "More Icon");
		waitTime(3000);
		
		if(verifyElementPresent(MPWAHomePage.objLessIcon, "Less Icon")) {
			logger.info("User is able to click on more icon");	
			extent.extentLoggerPass("", "User is able to click on more icon");	
		}else {
			logger.info("User is not able to click on more icon");
			extent.extentLoggerFail("", "User is not able to click on more icon");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	
	public void verifyOpenButton(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T053- Guest in User -  Check if user is able to Click on Open Button For You screen");
			System.out.println("T053");
			extent.extentLogger("Open icon", "T053");
		}else {
			extent.HeaderChildNode("T054- Logged in User -  Check if user is able to Click on Open Button For You screen");
			System.out.println("T054");
			extent.extentLogger("Open icon", "T054");
		}
		if(verifyElementPresent(MPWAHomePage.objOpenButtonAndInstallButton, "Open Button")) {
			logger.info("User is able to see Open button");	
			extent.extentLoggerPass("", "User is able to see Open button");	
		}else {
			logger.info("User is not able to see Open button");
			extent.extentLoggerFail("", "User is not able to see Open button");
		}
		TimeStampnavigateToHomePage();
	}
	
	
	public void verifyGetTheFullExperienceOnTheHipiApp(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T055- Guest in User -  Check if user is able to Click on Get the full experience on the Hipi app For You screen");
			System.out.println("T055");
			extent.extentLogger("Get the full experience on the Hipi app", "T055");
		}else {
			extent.HeaderChildNode("T056- Logged in User -  Check if user is able to Click on Get the full experience on the Hipi app For You screen");
			System.out.println("T056");
			extent.extentLogger("Get the full experience on the Hipi app", "T056");
		}
		
		if(verifyElementPresent(MPWAHomePage.objGetTheFullExperienceOnTheHipiApp, "Get the full experience on the Hipi app")) {
			logger.info("User is able to see Get the full experience on the Hipi app");	
			extent.extentLoggerPass("", "User is able to see Get the full experience on the Hipi app");	
		}else {
			logger.info("User is not able to see Get the full experience on the Hipi app");
			extent.extentLoggerFail("", "User is not able to see Get the full experience on the Hipi app");
		}
		
		
		
	}
	
	
	
	public void verifyHashtag(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T057- Guest in User -  Check if user is able to Click on hashtag For You screen");
			System.out.println("T057");
			extent.extentLogger("Hashtag", "T057");
		}else {
			extent.HeaderChildNode("T058- Logged in User -  Check if user is able to Click on hashtag For You screen");
			System.out.println("T058");
			extent.extentLogger("Hashtag", "T058");
		}
		
		if(verifyElementExist(MPWAHomePage.objMoreIcon, "More Icon")) {
			click(MPWAHomePage.objMoreIcon, "More Icon");
		}else {
			logger.info("User is not able to see More icon");
		}
		waitTime(3000);
		verifyElementPresentAndClick(MPWAHomePage.objHashTag, "HashTag");
		waitTime(5000);
		if(verifyElementPresent(MPWAHashtagPage.objHashTagPage, "Hashtag page")) {
			logger.info("User is able to see Hashtag page when click on hashtag in home screen");	
			extent.extentLoggerPass("", "User is able to see Hashtag page when click on hashtag in home screen");	
		}else {
			logger.info("User is not able to see Hashtag page when click on hashtag in home screen");
			extent.extentLoggerFail("", "User is not able to see Hashtag page when click on hashtag in home screen");
		}
		TimeStampnavigateToHomePage();
	}
	
	
	public void verifySoundname(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T059- Guest in User -  Check if user is able to Click on sound For You screen");
			System.out.println("T059");
			extent.extentLogger("Sound name", "T059");
		}else {
			extent.HeaderChildNode("T060- Logged in User -  Check if user is able to Click on sound For You screen");
			System.out.println("T060");
			extent.extentLogger("Sound name", "T060");
		}
		
		verifyElementPresentAndClick(MPWAHomePage.objSoundname, "Sound name");
		waitTime(3000);
		
		if(verifyElementPresent(MPWAHashtagPage.objHashTagPage, "Sound page")) {
			logger.info("User is able to see Sound page when click on Sound in home screen");	
			extent.extentLoggerPass("", "User is able to see Sound page when click on Sound in home screen");	
		}else {
			logger.info("User is not able to see Sound page when click on Sound in home screen");
			extent.extentLoggerFail("", "User is not able to see Sound page when click on Sound in home screen");
		}
		TimeStampnavigateToHomePage();	
	}
	
	
	
	
	public void verifyLessIcon(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T061- Guest in User -  Check if user is able to Click on Less icon For You screen");
			System.out.println("T061");
			extent.extentLogger("Less icon", "T061");
		}else {
			extent.HeaderChildNode("T062- Logged in User -  Check if user is able to Click on Less icon For You screen");
			System.out.println("T062");
			extent.extentLogger("Less icon", "T062");
		}
		
		if(verifyElementExist(MPWAHomePage.objMoreIcon, "More Icon")) {
			click(MPWAHomePage.objMoreIcon, "More Icon");
		}else {
			logger.info("User is not able to see More icon");
		}
		verifyElementPresentAndClick(MPWAHomePage.objLessIcon, "Less Icon");
		waitTime(3000);
		
		if(verifyElementPresent(MPWAHomePage.objMoreIcon, "Less Icon")) {
			logger.info("User is able to click on more icon");	
			extent.extentLoggerPass("", "User is able to click on more icon");	
		}else {
			logger.info("User is not able to click on more icon");
			extent.extentLoggerFail("", "User is not able to click on more icon");
		}	
		TimeStampnavigateToHomePage();
	}
	
	
	public void verifyExploreIconclickable(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T063- Guest in User -  Check if user is able to Click on Explore icon For You screen");
			System.out.println("T063");
			extent.extentLogger("Explore icon", "T063");
		}else {
			extent.HeaderChildNode("T064- Logged in User -  Check if user is able to Click on Explore icon For You screen");
			System.out.println("T064");
			extent.extentLogger("Explore icon", "T064");
		}
		
		verifyElementPresentAndClick(MPWAHomePage.objDiscoverButton, "Explore button");
		
		waitTime(4000);
		if(verifyElementPresent(MPWADiscoverPage.objBannersBelowTheSearchField, "Discover Banner screen")) {
			logger.info("User is able to click on Explore icon and navigate to discover page");	
			extent.extentLoggerPass("", "User is able to click on Explore icon and navigate to discover page");	
		}else {
			logger.info("User is not able to click on Explore icon and navigate to discover page");
			extent.extentLoggerFail("", "User is not able to click on Explore icon and navigate to discover page");
		}		
		
		TimeStampnavigateToHomePage();
	
	}
	
	
	public void verifyCreateIconClickable(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T065- Guest in User -  Check if user is able to Click on Create icon For You screen");
			System.out.println("T065");
			extent.extentLogger("Create icon", "T065");
		}else {
			extent.HeaderChildNode("T066- Logged in User -  Check if user is able to Click on Create icon For You screen");
			System.out.println("T066");
			extent.extentLogger("Create icon", "T066");
		}
		waitTime(4000);
		verifyElementPresentAndClick(MPWAHomePage.objCreatorButton, "Create button");
		waitTime(4000);
		
		if(verifyElementPresent(MPWAHomePage.objOpenApp, "Open app")) {
			logger.info("User is able to see the Open app when click on create icon");	
			extent.extentLoggerPass("", "User is able to see the Open app when click on create icon");	
		}else {
			logger.info("User is not able to see the Open app when click on create icon");
			extent.extentLoggerFail("", "User is not able to see the Open app when click on create icon");
		}		
		click(MPWAHomePage.objNotNow, "Not now");
		TimeStampnavigateToHomePage();
	}
	
	
	public void veridyProfileIcon(String userType) throws Exception {

		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T067- Guest in User -  Check if user is able to Click on Profile icon For You screen");
			System.out.println("T067");
			extent.extentLogger("Profile icon", "T067");

			verifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
			waitTime(4000);
			verifyLoginPopup();	
			TimeStampnavigateToHomePage();
		}
	}

	
	public void veridyProfileIconNonSub(String userType) throws Exception {

		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T068- Logged in User -  Check if user is able to Click on Profile icon For You screen");
			System.out.println("T068");
			extent.extentLogger("Profile icon", "T068");

			verifyElementPresentAndClick(MPWAHomePage.objProfileIcon, "Profile Icon");
			waitTime(4000);
			verifyUserProfile();
			TimeStampnavigateToHomePage();
		}
	}


	public void verifyFollowingclickableGuestUser(String userType) throws Exception {

		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T069- Guest in User -  Check if user is able to Click on Following icon For You screen");
			System.out.println("T069");
			extent.extentLogger("Following icon", "T069");

			verifyElementPresentAndClick(MPWAHomePage.objFollowingButton, "Following Button");
			waitTime(4000);
			if(verifyElementPresent(MPWAHomePage.objLoginFollowingScreen, "Login Button Following Screen")) {
				logger.info("User is able to see the Login Button when click on Following icon");	
				extent.extentLoggerPass("", "User is able to see the Login Button when click on Following icon");	
			}else {
				logger.info("User is not able to see the Login Button when click on Following icon");
				extent.extentLoggerFail("", "User is not able to see the Login Button when click on Following icon");
			}	
			click(MPWAHomePage.objForYou, "For you");
			TimeStampnavigateToHomePage();
		}
	}
	
	
	
	public void verifyFollowingclickableNonUser(String userType) throws Exception {

		if(userType.equalsIgnoreCase("NonSubscribedUser")) {
			extent.HeaderChildNode("T070- Logged in User -  Check if user is able to Click on Following icon For You screen");
			System.out.println("T070");
			extent.extentLogger("Following icon", "T070");
			
			verifyElementPresentAndClick(MPWAHomePage.objFollowingButton, "Following Button");
			waitUntilElementDisplayed(MPWAHomePage.objUserName, 2);
			if(verifyElementPresent(MPWAHomePage.objUserName, "User Name")) {
				logger.info("User is able to see the User Name when click on Following icon");	
				extent.extentLoggerPass("", "User is able to see the User Name when click on Following icon");	
			}else {
				logger.info("User is not able to see the User Name when click on Following icon");
				extent.extentLoggerFail("", "User is not able to see the User Name when click on Following icon");
			}	
			click(MPWAHomePage.objForYou, "For you");
			TimeStampnavigateToHomePage();
		}
			
	}
	
	//============================================Shop Module=====================================================
	
	public void verifyShopButton(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T071- Guest in User -  Check if user is able to See Shop icon For You screen");
			System.out.println("T071");
			extent.extentLogger("Shop icon", "T071");
		}else {
			extent.HeaderChildNode("T072- Logged in User -  Check if user is able to See Shop icon For You screen");
			System.out.println("T072");
			extent.extentLogger("Shop icon", "T072");
		}
		
		waitUntilElementDisplayed(MPWAHomePage.objShopButton, 10);
		if(verifyElementPresent(MPWAHomePage.objShopButton, "Shop Button")) {
			logger.info("User is able to see the Shop Button");	
			extent.extentLoggerPass("", "User is able to see the Shop Button");	
		}else {
			logger.info("User is not able to see the Shop Button");	
			extent.extentLoggerFail("", "User is not able to see the Shop Button");
		}	
	}
	
	
	public void clickonShopButton(String userType) throws Exception  {
		{
			
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T073- Guest in User -  Check if user is able to click Shop icon For You screen");
				System.out.println("T073");
				extent.extentLogger("Shop icon", "T073");
			}else {
				extent.HeaderChildNode("T074- Logged in User -  Check if user is able to click Shop icon For You screen");
				System.out.println("T074");
				extent.extentLogger("Shop icon", "T072");
			}
			
			verifyElementPresentAndClick(MPWAHomePage.objShopButton, "Shop button");
			waitUntilElementDisplayed(MPWAShopPage.objInThisVideo, 2);
			if(verifyElementPresent(MPWAShopPage.objInThisVideo, "In This Video")) {
				logger.info("User is able to click the Shop Button");	
				extent.extentLoggerPass("", "User is able to click the Shop Button");	
			}else {
				logger.info("User is not able to click the Shop Button");	
				extent.extentLoggerFail("", "User is not able to click the Shop Button");
			}	
		}
	}
	
	
		public void verifySavedMomentsTab(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T075- Guest in User -  Check if user is able to see Saved Moments TAB Shop screen");
				System.out.println("T075");
				extent.extentLogger("Saved Moments TAB", "T075");
			}else {
				extent.HeaderChildNode("T076- Logged in User -  Check if user is able to see Saved Moments TAB Shop screen");
				System.out.println("T076");
				extent.extentLogger("Saved Moments TAB", "T076");
			}
			
			if(verifyElementPresent(MPWAShopPage.objSavedMomentsTab, "Saved Moments TAB")) {
				logger.info("User is able to see the Saved Moments TAB");	
				extent.extentLoggerPass("", "User is able to see the Saved Moments TAB");	
			}else {
				logger.info("User is not able to see the Saved Moments TAB");	
				extent.extentLoggerFail("", "User is not able to see the Saved Moments TAB");
			}	
		}
	
	
	public void verifyArtisteTitle(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T077- Guest in User -  Check if user is able to see Artiste Title Shop screen");
			System.out.println("T077");
			extent.extentLogger("Artiste Title", "T077");
		}else {
			extent.HeaderChildNode("T078- Logged in User -  Check if user is able to see Artiste Title Shop screen");
			System.out.println("T078");
			extent.extentLogger("Artiste Title", "T078");
		}
		if(verifyElementPresent(MPWAShopPage.objArtisteTitle, "Artiste Title ")) {
			logger.info("User is able to see the Artiste Title ");	
			extent.extentLoggerPass("", "User is able to see the Artiste Title ");	
		}else {
			logger.info("User is not able to see the Artiste Title ");	
			extent.extentLoggerFail("", "User is not able to see the Artiste Title");
		}	
	}
	
	
	public void verifyLessButton(String userType) throws Exception {
		
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T079- Guest in User -  Check if user is able to see Less Button Shop screen");
			System.out.println("T079");
			extent.extentLogger("Less Button", "T079");
		}else {
			extent.HeaderChildNode("T080- Logged in User - Check if user is able to see Less Button Shop screen");
			System.out.println("T080");
			extent.extentLogger("Less Button", "T080");
		}
		if(verifyElementPresent(MPWAShopPage.objLessButton, "Less Button")) {
			logger.info("User is able to see the Less Button");	
			extent.extentLoggerPass("", "User is able to see the Less Button");	
		}else {
			logger.info("User is not able to see the Less Button");	
			extent.extentLoggerFail("", "User is not able to see the Less Button");
		}	
		
		
		
	}
		
		public void verifyMoresButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T081- Guest in User - Check if user is able to see Expand Button Shop screen");
				System.out.println("T081");
				extent.extentLogger("More Button", "T081");
			}else {
				extent.HeaderChildNode("T082- Logged in User - Check if user is able to see Expand Button Shop screen");
				System.out.println("T082");
				extent.extentLogger("Less Button", "T082");
			}
			verifyElementPresentAndClick(MPWAShopPage.objLessButton, "Less Button");
			waitUntilElementDisplayed(MPWAShopPage.objExpandButton, 2);
			if(verifyElementPresent(MPWAShopPage.objExpandButton, "Expand Button")) {
				logger.info("User is able to see the Expand Button");	
				extent.extentLoggerPass("", "User is able to see the Expand Button");	
			}else {
				logger.info("User is not able to see the Expand Button");	
				extent.extentLoggerFail("", "User is not able to see the Expand Button");
			}	
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T085- Guest in User - Check if user is able to Click on Expand Button Shop screen");
				System.out.println("T085");
				extent.extentLogger("Expand Button", "T085");
			}else {
				extent.HeaderChildNode("T086- Logged in User - Check if user is able to Click on Expand Button Shop screen");
				System.out.println("T086");
				extent.extentLogger("Expand Button", "T086");
			}
			verifyElementPresentAndClick(MPWAShopPage.objExpandButton, "Expand Button");
			if(verifyElementPresent(MPWAShopPage.objLessButton, "Less Button")) {
				logger.info("user is able to Click on Expand Button Shop screen");	
				extent.extentLoggerPass("", "user is able to Click on Expand Button Shop screen");	
			}else {
				logger.info("User is not able to Click on Expand Button Shop screen");	
				extent.extentLoggerFail("", "User is not able to Click on Expand Button Shop screen");
			}	
			
			
	}
	
		public void verifyOutfiTab(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T087- Guest in User - Check if user is able to See Outfit tab Shop screen");
				System.out.println("T087");
				extent.extentLogger("Outfit tab", "T087");
			}else {
				extent.HeaderChildNode("T088- Logged in User - Check if user is able to See Outfit tab Shop screen");
				System.out.println("T088");
				extent.extentLogger("Outfit tab", "T088");
			}
			
			
			if(verifyElementPresent(MPWAShopPage.objOutfitTab, "Outfit tab")) {
				logger.info("user is able to See Outfit tab Shop screen");	
				extent.extentLoggerPass("", "user is able to See  Outfit tab Shop screen");	
			}else {
				logger.info("User is not able to See Outfit tab Shop screen");	
				extent.extentLoggerFail("", "User is not able to See Outfit tab Shop screen");
			}	
	}


		public void verifyAccessoriesTab(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T089- Guest in User - Check if user is able to See Accessories tab Shop screen");
				System.out.println("T089");
				extent.extentLogger("Accessories tab", "T089");
			}else {
				extent.HeaderChildNode("T090- Logged in User - Check if user is able to See AccessoriesTab Shop screen");
				System.out.println("T090");
				extent.extentLogger("Accessories tab", "T090");
			}
			
			
			if(verifyElementPresent(MPWAShopPage.objAccessoriesTab, "Accessories tab")) {
				logger.info("user is able to See Accessories tab Shop screen");	
				extent.extentLoggerPass("", "user is able to See  Accessories tab Shop screen");	
			}else {
				logger.info("User is not able to See Accessories tab Shop screen");	
				extent.extentLoggerFail("", "User is not able to See Accessories tab Shop screen");
			}	
	}

	
		public void verifyBeautyTab(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T091- Guest in User - Check if user is able to See Beauty tab Shop screen");
				System.out.println("T091");
				extent.extentLogger("Beauty tab", "T091");
			}else {
				extent.HeaderChildNode("T092- Logged in User - Check if user is able to See Beauty Tab Shop screen");
				System.out.println("T092");
				extent.extentLogger("Beauty tab", "T092");
			}
			
			
			if(verifyElementPresent(MPWAShopPage.objBeautyTab, "Beauty tab")) {
				logger.info("user is able to See Beauty tab Shop screen");	
				extent.extentLoggerPass("", "user is able to See  Beauty tab Shop screen");	
			}else {
				logger.info("User is not able to See Beauty tab Shop screen");	
				extent.extentLoggerFail("", "User is not able to See Beauty tab Shop screen");
			}	
	}

		public void verifyHariTab(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T093- Guest in User - Check if user is able to See Hair tab Shop screen");
				System.out.println("T093");
				extent.extentLogger("Hair tab", "T093");
			}else {
				extent.HeaderChildNode("T094- Logged in User - Check if user is able to See Hair Tab Shop screen");
				System.out.println("T094");
				extent.extentLogger("Hair tab", "T094");
			}
			
			
			if(verifyElementPresent(MPWAShopPage.objHairTab, "Hair tab")) {
				logger.info("user is able to See Hair tab Shop screen");	
				extent.extentLoggerPass("", "user is able to See  Hair tab Shop screen");	
			}else {
				logger.info("User is not able to See Hair tab Shop screen");	
				extent.extentLoggerFail("", "User is not able to See Hair tab Shop screen");
			}	
	}
	
		String productDetails = null;
		 String productDetails1 = null;
		 String productDetails2 = null;
		 String productDetails3 = null;
	
	public void  verifyAccessoriesTabClickable(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T095- Guest in User - Check if user is able to Click Accessories Tab tab Shop screen");
			System.out.println("T095");
			extent.extentLogger("Accessories tab", "T095");
		}else {
			extent.HeaderChildNode("T096- Logged in User - Check if user is able to Click Accessories Tab Shop screen");
			System.out.println("T096");
			extent.extentLogger("Accessories tab", "T096");
		}
		
		verifyElementPresentAndClick(MPWAShopPage.objAccessoriesTab, "Accessories Tab");
		verifyElementPresentAndClick(MPWAShopPage.objOutfitTab, "Outfit Tab");
		
		productDetails = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails);
		verifyElementPresentAndClick(MPWAShopPage.objAccessoriesTab, "Accessories Tab");
		verifyProductNameAndImageAndDescriptionAndBuynow();
		productDetails1 = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails1);
		if (!productDetails.equals(productDetails1)) {
			logger.info("user is able to Click Accessories Tab tab Shop screen");	
			extent.extentLoggerPass("", "user is able to Click Accessories Tab tab Shop screen");	
		}else {
			logger.info("User is not able to Click Accessories Tab tab Shop screen");	
			extent.extentLoggerFail("", "User is not able to Click Accessories Tab tab Shop screen");
		}	
	}
	
	
	
	public void  verifyBeautyTabClickable(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T097- Guest in User - Check if user is able to Click Beauty tab Shop screen");
			System.out.println("T097");
			extent.extentLogger("Beauty tab", "T097");
		}else {
			extent.HeaderChildNode("T098- Logged in User - Check if user is able to Click Beauty Tab Shop screen");
			System.out.println("T098");
			extent.extentLogger("Beauty tab", "T098");
		}
	
		productDetails1 = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails1);
		verifyElementPresentAndClick(MPWAShopPage.objBeautyTab, "Beauty Tab");
		verifyProductNameAndImageAndDescriptionAndBuynow();
		productDetails2 = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails2);
		if (!productDetails1.equals(productDetails2)) {
			logger.info("user is able to Click Beauty Tab tab Shop screen");	
			extent.extentLoggerPass("", "user is able to Click Beauty Tab tab Shop screen");	
		}else {
			logger.info("User is not able to Click Beauty Tab tab Shop screen");	
			extent.extentLoggerFail("", "User is not able to Click Beauty Tab tab Shop screen");
		}	
	}
	
	
	
	public void  verifyHairTabClickable(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T099- Guest in User - Check if user is able to Click Hair tab Shop screen");
			System.out.println("T099");
			extent.extentLogger("Hair tab", "T099");
		}else {
			extent.HeaderChildNode("T100- Logged in User - Check if user is able to Click Hari Tab Shop screen");
			System.out.println("T100");
			extent.extentLogger("Hari tab", "T100");
		}
		
		productDetails2 = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails2);
		verifyElementPresentAndClick(MPWAShopPage.objHairTab, "Hari Tab");
		verifyProductNameAndImageAndDescriptionAndBuynow();
		productDetails3 = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails3);
		if (!productDetails2.equals(productDetails3)) {
			logger.info("user is able to Click Hari Tab tab Shop screen");	
			extent.extentLoggerPass("", "user is able to Click Hari Tab tab Shop screen");	
		}else {
			logger.info("User is not able to Click Hari Tab tab Shop screen");	
			extent.extentLoggerFail("", "User is not able to Click Hari Tab tab Shop screen");
		}	
	}
	
	public void  verifyOutfitTaClickable(String userType) throws Exception {
		if(userType.equalsIgnoreCase("Guest")) {
			extent.HeaderChildNode("T0101- Guest in User - Check if user is able to Click Outfit tab Shop screen");
			System.out.println("T0101");
			extent.extentLogger("Outfit tab", "T0101");
		}else {
			extent.HeaderChildNode("T0102- Logged in User - Check if user is able to Click Outfit Tab Shop screen");
			System.out.println("T0102");
			extent.extentLogger("Hari tab", "T0102");
		}
		
		productDetails3 = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails3);
		verifyElementPresentAndClick(MPWAShopPage.objOutfitTab, "Outfit Tab");
		verifyProductNameAndImageAndDescriptionAndBuynow();
		productDetails = getText(MPWAShopPage.objProductDescription);
		System.out.println(productDetails);
		if (!productDetails.equals(productDetails3)) {
			logger.info("user is able to Click Outfit Tab tab Shop screen");	
			extent.extentLoggerPass("", "user is able to Click Outfit Tab tab Shop screen");	
		}else {
			logger.info("User is not able to Click Outfit Tab tab Shop screen");	
			extent.extentLoggerFail("", "User is not able to Click Outfit Tab tab Shop screen");
		}	
		
	}
		
		public void verifyProductNameAndImageAndDescriptionAndBuynow() throws Exception {
			
			verifyElementPresent(MPWAShopPage.objProductImage, "Product Image");
			
			verifyElementPresent(MPWAShopPage.objProductDescription, "Product Description");
			
			verifyElementPresent(MPWAShopPage.objProductName, "Product Name");
			
			verifyElementPresent(MPWAShopPage.objBuyNow, "Buy Now");
			
			
			
		}
		

		
	
		public void  verifyProductNameAndImageAndDescriptionAndBuynowShopPage(String userType) throws Exception {
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0103- Guest in User - Check if user is able to See Product Image Shop screen");
				System.out.println("T0103");
				extent.extentLogger("Product Image", "T0103");
			}else {
				extent.HeaderChildNode("T0104- Logged in User - Check if user is able to See Product Image Shop screen");
				System.out.println("T0104");
				extent.extentLogger("Product Image", "T0104");
			}
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0105- Guest in User - Check if user is able to See Product Name Shop screen");
				System.out.println("T0105");
				extent.extentLogger("Product Name", "T0105");
			}else {
				extent.HeaderChildNode("T0106- Logged in User - Check if user is able to See Product Name Shop screen");
				System.out.println("T0106");
				extent.extentLogger("Product Name", "T0106");
			}
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0107- Guest in User - Check if user is able to See Product Description Shop screen");
				System.out.println("T0107");
				extent.extentLogger("Product Description", "T0107");
			}else {
				extent.HeaderChildNode("T0108- Logged in User - Check if user is able to See Product Description Shop screen");
				System.out.println("T0108");
				extent.extentLogger("Product Description", "T0108");
			}
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0109- Guest in User - Check if user is able to See Buy now Shop screen");
				System.out.println("T0109");
				extent.extentLogger("Buy now", "T0109");
			}else {
				extent.HeaderChildNode("T0110- Logged in User - Check if user is able to See Buy now Shop screen");
				System.out.println("T0110");
				extent.extentLogger("Buy now", "T0110");
			}
			verifyProductNameAndImageAndDescriptionAndBuynow();	
		}
		
		
		
		//Hamburger Menu
		public void verifyHamburgerMenu(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0111- Guest User - Check if user is able to see the Hamburger menu  For You screen");
				System.out.println("T0111");
				extent.extentLogger("Hamburger menu", "T0111");
			}else {
				extent.HeaderChildNode("T0112- Logged in User - Check if user is able to see the Hamburger menu For You screen");
				System.out.println("T0112");
				extent.extentLogger("Hamburger menu", "T0112");
			}
			if(verifyElementPresent(MPWAHomePage.objhamburgerMenu, "HamburgerMenu")) {
				logger.info("User is able to see the Hamburger Menu");	
				extent.extentLoggerPass("", "User is able to see the Hamburger Menu");	
			}else {
				logger.info("User is not able to see the Hamburger Menu");	
				extent.extentLoggerFail("", "User is not able to see the Hamburger Menu");
			}	
		}
		
		
		// Click on Hamburger Menu
		public void clickOnHamburgerMenu(String userType) throws Exception {
				
				if(userType.equalsIgnoreCase("Guest")) {
					extent.HeaderChildNode("T0113- Guest User - Check if user is able to click on the Hamberguer menu  For You screen");
					System.out.println("T0113");
					extent.extentLogger("Hamburger menu", "T0113");
				}else {
					extent.HeaderChildNode("T0114- Logged in User - Check if user is able to click on the Hamburger menu For You screen");
					System.out.println("T0114");
					extent.extentLogger("Hamburger menu", "T0114");
				}
				verifyElementPresentAndClick(MPWAHomePage.objhamburgerMenu, "Hamburger Menu");
				waitUntilElementDisplayed(MPWAHamburgermenuPage.objCrossIcon, 2);
				if(verifyElementPresent(MPWAHamburgermenuPage.objCrossIcon, "Cross Icon")) {
					logger.info("User is able to MPWAHamburgermenuPage the Hamburger Menu");	
					extent.extentLoggerPass("", "User is able to click the Hamburger Menu");	
				}else {
					logger.info("User is not able to click the Hamburger Menu");	
					extent.extentLoggerFail("", "User is not able to click the Hamburger Menu");
				}	
			}
			
		//Cross Icon 
		public void verifyCrossIcon(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0115- Guest User - Check if user is able to see cross icon post clicking on the hamburger menu  For You screen");
				System.out.println("T0115");
				extent.extentLogger("Cross Icon", "T0115");
			}else {
				extent.HeaderChildNode("T0116- Logged in User - Check if user is able to see the cross icon post clicking on the hamburger menu For You screen");
				System.out.println("T0116");
				extent.extentLogger("Cross Icon", "T0116");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objCrossIcon, "CrossIcon")) {
				logger.info("User is able to see the Cross Icon");	
				extent.extentLoggerPass("", "User is able to see the Cross Icon");	
			}else {
				logger.info("User is not able to see the Cross Icon");	
				extent.extentLoggerFail("", "User is not able to see the Cross Icon");
			}	
		}

		//Click on cross icon should be last
		public void clickOnCrossIcon(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0117- Guest User - Check if user is able to click on the cross icon on For You screen");
				System.out.println("T0117");
				extent.extentLogger("Cross Icon", "T0117");
			}else {
				extent.HeaderChildNode("T0118- Logged in User - Check if user is able to click on the  cross icon on For You screen");
				System.out.println("T0118");
				extent.extentLogger("Cross Icon", "T0118");
			}
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objCrossIcon, "CrossIcon");
			waitUntilElementDisplayed(MPWAHomePage.objhamburgerMenu, 2);
			if(verifyElementPresent(MPWAHomePage.objhamburgerMenu, "Hamburger Menu")) {
				logger.info("User is able to click the Cross Icon");	
				extent.extentLoggerPass("", "User is able to click the Cross Icon");	
			}else {
				logger.info("User is not able to click the Cross Icon");	
				extent.extentLoggerFail("", "User is not able to click the Cross Icon");
			}	
		}
		

		//About button
		public void verifyAboutButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0119- Guest User - Check if user is able to see the About option on For You screen");
				System.out.println("T0119");
				extent.extentLogger("About Button", "T0119");
			}else {
				extent.HeaderChildNode("T0110- Logged in User - Check if user is able to see the About option on For You screen");
				System.out.println("T0110");
				extent.extentLogger("About Button", "T0110");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objAboutButton, "About Button")) {
				logger.info("User is able to see the About Button");	
				extent.extentLoggerPass("", "User is able to see the About button");	
			}else {
				logger.info("User is not able to see the About Button");	
				extent.extentLoggerFail("", "User is not able to see the About Button");
			}	
		}
		
		
		//Newsroom button
		public void verifyNewsroomButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0121- Guest User - Check if user is able to see the Newsroom option on For You screen");
				System.out.println("T0121");
				extent.extentLogger("Newsroom Button", "T0121");
			}else {
				extent.HeaderChildNode("T0122- Logged in User - Check if user is able to see the Newsroom option on For You screen");
				System.out.println("T0122");
				extent.extentLogger("Newsroom Button", "T0122");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objNewsroomButton, "Newsroom Button")) {
				logger.info("User is able to see the Newsroom Button");	
				extent.extentLoggerPass("", "User is able to see the Newsroom button");	
			}else {
				logger.info("User is not able to see the Newsroom Button");	
				extent.extentLoggerFail("", "User is not able to see the Newsroom Button");
			}	
		}
		
		
		
		//Blog button
		public void verifyBlogButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0123- Guest User - Check if user is able to see the Blog option on For You screen");
				System.out.println("T0123");
				extent.extentLogger("Blog Button", "T0123");
			}else {
				extent.HeaderChildNode("T0124- Logged in User - Check if user is able to see the Blog option on For You screen");
				System.out.println("T0124");
				extent.extentLogger("Blog Button", "T0124");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objBlogButton, "Blog Button")) {
				logger.info("User is able to see the Blog Button");	
				extent.extentLoggerPass("", "User is able to see the Blog button");	
			}else {
				logger.info("User is not able to see the Blog Button");	
				extent.extentLoggerFail("", "User is not able to see the Blog Button");
			}	
		}
		
		
		//Ads button
		public void verifyAdsButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0125- Guest User - Check if user is able to see the Ads option on For You screen");
				System.out.println("T0125");
				extent.extentLogger("Ads Button", "T0125");
			}else {
				extent.HeaderChildNode("T0126- Logged in User - Check if user is able to see the Ads option on For You screen");
				System.out.println("T0126");
				extent.extentLogger("Ads Button", "T0126");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objAdsButton, "Ads Button")) {
				logger.info("User is able to see the Ads Button");	
				extent.extentLoggerPass("", "User is able to see the Ads button");	
			}else {
				logger.info("User is not able to see the Ads Button");	
				extent.extentLoggerFail("", "User is not able to see the Ads Button");
			}	
		}
		
		
		
		//Community Guidelines button
		public void verifyCommunityGuidelinesButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0127- Guest User - Check if user is able to see the Community Guidelines option on For You screen");
				System.out.println("T0127");
				extent.extentLogger("Community Guidelines Button", "T0127");
			}else {
				extent.HeaderChildNode("T0128- Logged in User - Check if user is able to see the Community Guidelines option on For You screen");
				System.out.println("T0128");
				extent.extentLogger("Community Guidelines Button", "T0128");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objCommunityGuidelinesButton, "Community Guidelines Button")) {
				logger.info("User is able to see the Community Guidelines Button");	
				extent.extentLoggerPass("", "User is able to see the Community Guidelines button");	
			}else {
				logger.info("User is not able to see the Community Guidelines Button");	
				extent.extentLoggerFail("", "User is not able to see the Community Guidelines Button");
			}	
		}
		
		
		//Privacy Policy button
		public void verifyPrivacyPolicyButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0129- Guest User - Check if user is able to see the Privacy Policy option on For You screen");
				System.out.println("T0129");
				extent.extentLogger("Privacy Policy Button", "T0129");
			}else {
				extent.HeaderChildNode("T0130- Logged in User - Check if user is able to see the Privacy Policy option on For You screen");
				System.out.println("T0130");
				extent.extentLogger("Privacy Policys Button", "T0130");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objPrivacyPolicyButton, "Privacy Policys Button")) {
				logger.info("User is able to see the Privacy Policys Button");	
				extent.extentLoggerPass("", "User is able to see the Privacy Policys Button");	
			}else {
				logger.info("User is not able to see the Privacy Policys Button");	
				extent.extentLoggerFail("", "User is not able to see the Privacy Policys Button");
			}	
		}
		
		
		//Terms Of Use button
		public void verifyTermsofUseButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0131- Guest User - Check if user is able to see the Terms of Use option on For You screen");
				System.out.println("T0131");
				extent.extentLogger("Terms Of Use", "T0131");
			}else {
				extent.HeaderChildNode("T0132- Logged in User - Check if user is able to see the Terms of Use option on For You screen");
				System.out.println("T0132");
				extent.extentLogger("Terms Of Use", "T0132");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objTermsOfUseButton, "Terms Of Use Button")) {
				logger.info("User is able to see the Terms Of Use Button");	
				extent.extentLoggerPass("", "User is able to see the Terms Of Use button");	
			}else {
				logger.info("User is not able to see the Terms Of Use Button");	
				extent.extentLoggerFail("", "User is not able to see the Terms Of Use Button");
			}	
		}
		
		//Grievance button
		public void verifyGrievanceButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0133- Guest User - Check if user is able to see the Grievance option on For You screen");
				System.out.println("T0133");
				extent.extentLogger("Grievance Button", "T0133");
			}else {
				extent.HeaderChildNode("T0134- Logged in User - Check if user is able to see the Grievance option on For You screen");
				System.out.println("T0134");
				extent.extentLogger("Grievance Button", "T0134");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objGrievanceButton, "Grievance Button")) {
				logger.info("User is able to see the Grievance Button");	
				extent.extentLoggerPass("", "User is able to see the Grievance button");	
			}else {
				logger.info("User is not able to see the Grievance Button");	
				extent.extentLoggerFail("", "User is not able to see the Grievance Button");
			}	
		}
		
		
		//Contact us Policy button
		public void verifyContactusButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0135- Guest User - Check if user is able to see the Contact us option on For You screen");
				System.out.println("T0135");
				extent.extentLogger("Contact us Button", "T0135");
			}else {
				extent.HeaderChildNode("T0136- Logged in User - Check if user is able to see the Contact us option on For You screen");
				System.out.println("T0136");
				extent.extentLogger("Contact us Button", "T0136");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objContactUsButton, "Contact us Button")) {
				logger.info("User is able to see the Contact us Button");	
				extent.extentLoggerPass("", "User is able to see the Contact us Button");	
			}else {
				logger.info("User is not able to see the Contact us Button");	
				extent.extentLoggerFail("", "User is not able to see the Contact us Button");
			}	
		}
		
		
		
		//Install button
		public void verifyInstallButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0137- Guest User - Check if user is able to see the \"Installing our app won't take any space on your phone.\" text with Install button on For You screen");
				System.out.println("T0137");
				extent.extentLogger("Install Button", "T0137");
			}else {
				extent.HeaderChildNode("T0138- Logged in User - Check if user is able to see the \"Installing our app won't take any space on your phone.\" text with Install button on For You screen");
				System.out.println("T0138");
				extent.extentLogger("Install Button", "T0138");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objInstallButton, "Install Button")) {
				logger.info("User is able to see the Install Button");	
				extent.extentLoggerPass("", "User is able to see the InstallButton");	
			}else {
				logger.info("User is not able to see the Install Button");	
				extent.extentLoggerFail("", "User is not able to see the Install Button");
			}	
		}
		
		//Hipi Stunner Text validation
		public void verifyHipiStunnerText(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0139- Guest User - Check if user is able to validate the Hipi Stunner text on For You screen");
				System.out.println("T0139");
				extent.extentLogger("Hipi Stunner Text", "T0139");
			}else {
				extent.HeaderChildNode("T0140- Logged in User - Check if user is able to validate the Hipi Stunner text on For You screen");
				System.out.println("T0140");
				extent.extentLogger("Hipi Stunner Text", "T0140");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objHipiStunnerText, "Hipi Stunner Text")) {
				logger.info("User is able to see the Hipi Stunner Text");	
				extent.extentLoggerPass("", "User is able to see the Hipi Stunner Text");	
			}else {
				logger.info("User is not able to see the Hipi Stunner Text");	
				extent.extentLoggerFail("", "User is not able to see the Hipi Stunner Text");
			}	
		}
		
		
		
		
		
		
		//Hipi G.O.A.T. Text validation
		public void verifyHipiGOATText(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0141- Guest User - Check if user is able to validate the Hipi G.O.A.T. text on For You screen");
				System.out.println("T0141");
				extent.extentLogger("Hipi G.O.A.T. Text", "T0141");
			}else {
				extent.HeaderChildNode("T0142- Logged in User - Check if user is able to validate the Hipi G.O.A.T. text on For You screen");
				System.out.println("T0142");
				extent.extentLogger("Hipi G.O.A.T. Text", "T0142");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objHipiGOATText, "Hipi G.O.A.T. Text")) {
				logger.info("User is able to see the Hipi G.O.A.T. Text");	
				extent.extentLoggerPass("", "User is able to see the Hipi G.O.A.T. Text");	
			}else {
				logger.info("User is not able to see the Hipi G.O.A.T. Text");	
				extent.extentLoggerFail("", "User is not able to see the Hipi G.O.A.T. Text");
			}	
		}
		
		
		
		
		//Hipi LipSyncBattle validation
		public void verifyHipiHipiLipSyncBattleText(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0143- Guest User - Check if user is able to validate the Hipi LipSyncBattle text on For You screen");
				System.out.println("T0143");
				extent.extentLogger("Hipi LipSyncBattle Text", "T0143");
			}else {
				extent.HeaderChildNode("T0144- Logged in User - Check if user is able to validate the Hipi LipSyncBattle text on For You screen");
				System.out.println("T0144");
				extent.extentLogger("Hipi LipSyncBattle Text", "T0144");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objHipiLipSyncBattleText, "Hipi LipSyncBattle Text")) {
				logger.info("User is able to see the Hipi LipSyncBattle Text");	
				extent.extentLoggerPass("", "User is able to see the Hipi LipSyncBattle Text");	
			}else {
				logger.info("User is not able to see the Hipi LipSyncBattle Text");	
				extent.extentLoggerFail("", "User is not able to see the Hipi LipSyncBattle Text");
			}	
		}
		
		//Zee cine award text validation
		public void verifyZeeCineAwardText(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0145- Guest User - Check if user is able to validate the Zee cine award text on For You screen");
				System.out.println("T0145");
				extent.extentLogger("Zee cine award Text", "T0145");
			}else {
				extent.HeaderChildNode("T0146- Logged in User - Check if user is able to validate the Zee cine award on For You screen");
				System.out.println("T0146");
				extent.extentLogger("Zee cine award Text", "T0146");
			}
			if(verifyElementPresent(MPWAHamburgermenuPage.objZeeCineAwardText, "Zee cine award Text")) {
				logger.info("User is able to see the Zee cine award Text");	
				extent.extentLoggerPass("", "User is able to see the Zee cine award Text");	
			}else {
				logger.info("User is not able to see the Zee cine award Text");	
				extent.extentLoggerFail("", "User is not able to see the Zee cine award Text");
			}	
		}


	
		//About button click
		public void clickOnAboutButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0147- Guest User - Check if user is able to click on the About option and navigated to respective screen.");
				System.out.println("T0147");
				extent.extentLogger("About Button", "T0147");
			}else {
				extent.HeaderChildNode("T0148- Logged in USer - Check if user is able to click on the About option and navigated to respective screen.");
				System.out.println("T0148");
				extent.extentLogger("About Button", "T0148");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objAboutButton, "About Button");
			waitTime(3000);
//			String url = getText(MPWAHomePage.objAndroidmwebSearchAddress);
//			System.out.println(url);
			getURL();
			
			if(url.equals("hipi.co.in/about")) {
				logger.info("User is able to click the About Button");	
				extent.extentLoggerPass("", "User is able to click the About Button");	
			}else {
				logger.info("User is not able to click the About Button");	
				extent.extentLoggerFail("", "User is not able to click the About Button");
			}	
			Back(1);
		}
		String url ;
		public void getURL() throws Exception {
			waitTime(15000);
			url= getText(MPWAHomePage.objAndroidmwebSearchAddress);
			System.out.println(url);
			
			
		}
		public void NavigatetoHamburgerMenu(String userType) throws Exception {
			waitTime(5000);
			if (verifyElementExist(MPWAHomePage.objHandiAnimation, "Hand Animation")) {
				click(MPWAHomePage.objHomeIcon, "Home Icon");
			}
			
//			waitTime(4000);
			waitUntilElementDisplayed(MPWAHomePage.objhamburgerMenu, 2);
			verifyElementPresentAndClick(MPWAHomePage.objhamburgerMenu, "Hamburger Menu");
			waitUntilElementDisplayed(MPWAHamburgermenuPage.objCrossIcon, 2);
			
			
		}
		
		
		//Newsroom button click
		public void clickOnNewsroomButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0149- Guest User - Check if user is able to click on  the Newsroom option and navigated to respective screen.");
				System.out.println("T0149");
				extent.extentLogger("Newsroom Button", "T0149");
			}else {
				extent.HeaderChildNode("T0150- Logged in User - Check if user is able to click on the Newsroom option and navigated to respective screen.");
				System.out.println("T0150");
				extent.extentLogger("Newsroom Button", "T0150");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objNewsroomButton, "Newwsroom Button");
			getURL();
			if(url.equals("newsroom.hipi.co.in")) {
				logger.info("User is able to click the Newsroom Button");	
				extent.extentLoggerPass("", "User is able to click the Newsroom Button");	
			}else {
				logger.info("User is not able to click the Newsroom Button");	
				extent.extentLoggerFail("", "User is not able to click the Newsroom Button");
			}	
			Back(1);
		}
		
		
		//Blog button click
		public void clickOnBlogButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0151- Guest User - Check if user is able to click on the Blog option and navigated to respective screen.");
				System.out.println("T0151");
				extent.extentLogger("Blog Button", "T0151");
			}else {
				extent.HeaderChildNode("T0152- Logged in User - Check if user is able to click on  the Blog option and navigated to respective screen.");
				System.out.println("T0152");
				extent.extentLogger("Blog Button", "T0152");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objBlogButton, "Blog Button");
			getURL();
			if(url.equals("blog.hipi.co.in")) {
				logger.info("User is able to click the Blog Button");	
				extent.extentLoggerPass("", "User is able to click the Blog Button");	
			}else {
				logger.info("User is not able to click the Blog Button");	
				extent.extentLoggerFail("", "User is not able to click the Blog Button");
			}	
			Back(1);
		}
		
		
		
		//Ads button click
		public void clickOnAdsButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0153- Guest User - Check if user is able to click on the Ads option and navigated to respective screen.");
				System.out.println("T0153");
				extent.extentLogger("Ads Button", "T0153");
			}else {
				extent.HeaderChildNode("T0154- Logged in User - Check if user is able to click on  the Ads option and navigated to respective screen.");
				System.out.println("T0154");
				extent.extentLogger("Ads Button", "T0154");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objAdsButton, "Ads Button");
			getURL();
			if(url.equals("hipi.co.in/ads")) {
				logger.info("User is able to click the Ads Button");	
				extent.extentLoggerPass("", "User is able to click the Ads Button");	
			}else {
				logger.info("User is not able to click the Ads Button");	
				extent.extentLoggerFail("", "User is not able to click the Ads Button");
			}	
			Back(1);
		}

		//Community Guidelines button click
		public void clickOnCommunityGuidelinesButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0155- Guest User - Check if user is able to click on  the Community Guidelines option and navigated to respective screen.");
				System.out.println("T0155");
				extent.extentLogger("Community Guidelines Button", "T0155");
			}else {
				extent.HeaderChildNode("T0156- Logged in User - Check if user is able to click on the Community Guidelines option and navigated to respective screen.");
				System.out.println("T0156");
				extent.extentLogger("Community Guidelines Button", "T0156");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objCommunityGuidelinesButton, "Community Guidelines Button");
			getURL();
			if(url.equals("hipi.co.in/community-guidelines.html")) {
				logger.info("User is able to click the Community Guidelines Button");	
				extent.extentLoggerPass("", "User is able to click the Community Guidelines Button");	
			}else {
				logger.info("User is not able to click the Community Guidelines Button");	
				extent.extentLoggerFail("", "User is not able to click the Community Guidelines Button");
			}	
			Back(1);
		}
		
		//Privacy Policy button click
		public void clickOnPrivacyPolicyButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0157- Guest User - Check if user is able to click the Privacy Policy option and navigated to respective screen.");
				System.out.println("T0157");
				extent.extentLogger("Privacy Policys Button", "T0157");
			}else {
				extent.HeaderChildNode("T0158- Logged in User - Check if user is able to click on the Privacy Policy option and navigated to respective screen.");
				System.out.println("T0158");
				extent.extentLogger("Privacy Policys Button", "T0158");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objPrivacyPolicyButton, "Privacy Policys Button");
			getURL();
			if(url.equals("hipi.co.in/privacy-policy.html")) {
				logger.info("User is able to click the Privacy Policys Button");	
				extent.extentLoggerPass("", "User is able to click the Privacy Policys Button");	
			}else {
				logger.info("User is not able to click the Privacy Policys Button");	
				extent.extentLoggerFail("", "User is not able to click the Privacy Policys Button");
			}	
			Back(1);
		}

		
		
		//Terms Of Use button click
		public void clickOnTermsOfUseButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0159- Guest User - Check if user is able to click on the Terms of Use option and navigated to respective screen.");
				System.out.println("T0159");
				extent.extentLogger("Terms Of Use Button", "T0169");
			}else {
				extent.HeaderChildNode("T0160- Logged in User - Check if user is able to click on the Terms of Use option and should navigate to respective screen.");
				System.out.println("T0160");
				extent.extentLogger("Terms Of Use Button", "T0160");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objTermsOfUseButton, "Terms Of Use Button");
			getURL();
			if(url.equals("hipi.co.in/terms-conditions.html")) {
				logger.info("User is able to click the Terms Of Use Button");	
				extent.extentLoggerPass("", "User is able to click the Terms Of Use Button");	
			}else {
				logger.info("User is not able to click the Terms Of Use Button");	
				extent.extentLoggerFail("", "User is not able to click the Terms Of Use Button");
			}	
			Back(1);
		}
		
		
		//Grievance button click
		public void clickOnGrievanceeButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0161- Guest User - Check if user is able to click on the Grievance option and should navigate to respective screen.");
				System.out.println("T0161");
				extent.extentLogger("Grievance Button", "T0161");
			}else {
				extent.HeaderChildNode("T0162- Logged in User - Check if user is able to click the Grievance option and should navigate to respective screen.");
				System.out.println("T0162");
				extent.extentLogger("Grievance Button", "T0162");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objGrievanceButton, "Grievance Button");
			getURL();
			if(url.equals("hipi.co.in/grievance")) {
				logger.info("User is able to click the Grievance Button");	
				extent.extentLoggerPass("", "User is able to click the Grievance Button");	
			}else {
				logger.info("User is not able to click the Grievance Button");	
				extent.extentLoggerFail("", "User is not able to click the Grievance Button");
			}	
			Back(1);
		}
		
		
		//Contact us button click
		public void clickOnContactusButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0163- Guest User - Check if user is able to click on the Contact us option and should navigate to respective screen.");
				System.out.println("T0163");
				extent.extentLogger("Contact us Button", "T0163");
			}else {
				extent.HeaderChildNode("T0164 Logged in User - Check if user is able to click on the Contact us option and should navigate to respective screen.");
				System.out.println("T0164");
				extent.extentLogger("Contact us Button", "T0164");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objContactUsButton, "Contact us Button");
			getURL();
			if(url.equals("hipi.co.in/contact-us")) {
				logger.info("User is able to click the Contact us Button");	
				extent.extentLoggerPass("", "User is able to click the Contact us Button");	
			}else {
				logger.info("User is not able to click the Contact us Button");	
				extent.extentLoggerFail("", "User is not able to click the Contact us Button");
			}	
			Back(1);
		}

		
		//Click Hipi Stunner
		public void clickOnHipiStunnerButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0169- Guest User - Check if user is able to click on the Hipi Stunner button and should navigate to respective screen.");
				System.out.println("T0169");
				extent.extentLogger("Hipi Stunner", "T0169");
			}else {
				extent.HeaderChildNode("T0170 Logged in User - Check if user is able to click on the Hipi Stunner button and should navigate to respective screen.");
				System.out.println("T0170");
				extent.extentLogger("Hipi Stunner", "T0170");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objHipiStunnerText, "Hipi Stunner Text Button");
			getURL();
			if(url.equals("hipi.co.in/stunner")) {
				logger.info("User is able to click the Hipi Stunner Text Button");	
				extent.extentLoggerPass("", "User is able to click the Hipi Stunner Text Button");	
			}else {
				logger.info("User is not able to click the Hipi Stunner Text Button");	
				extent.extentLoggerFail("", "User is not able to click the Hipi Stunner Text Button");
			}	
			Back(1);
		}
		
		//Click on Hipi GOAT
		public void clickOnHipiGOATButton(String userType) throws Exception {
			
			if(userType.equalsIgnoreCase("Guest")) {
				extent.HeaderChildNode("T0171- Guest User - Check if user is able to click on the Hipi G.O.A.T. button and should navigate to respective screen.");
				System.out.println("T0171");
				extent.extentLogger("Hipi G.O.A.T.", "T0171");
			}else {
				extent.HeaderChildNode("T0172 Logged in User - Check if user is able to click on the Hipi G.O.A.T. button and should navigate to respective screen.");
				System.out.println("T0172");
				extent.extentLogger("Hipi G.O.A.T.", "T0172");
			}
			NavigatetoHamburgerMenu(userType);
			verifyElementPresentAndClick(MPWAHamburgermenuPage.objHipiGOATText, "Hipi GOAT Button");
			getURL();
			if(url.equals("hipi.co.in/goat")) {
				logger.info("User is able to click the Hipi GOAT Button");	
				extent.extentLoggerPass("", "User is able to click the Hipi GOAT Button");	
			}else {
				logger.info("User is not able to click the Hipi GOAT Button");	
				extent.extentLoggerFail("", "User is not able to click the Hipi GOAT Button");
			}
			Back(1);
		}
		
		
		
		//Click on Hipi LipSyncBattle
				public void clickOnHipiLipSyncBattleButton(String userType) throws Exception {
					
					if(userType.equalsIgnoreCase("Guest")) {
						extent.HeaderChildNode("T0173- Guest User - Check if user is able to click on the Hipi LipSyncBattle  button and should navigate to respective screen.");
						System.out.println("T0173");
						extent.extentLogger("Hipi LipSyncBattle", "T0173");
					}else {
						extent.HeaderChildNode("T0174 Logged in User - Check if user is able to click on the Hipi LipSyncBattle button and should navigate to respective screen.");
						System.out.println("T0174");
						extent.extentLogger("Hipi LipSyncBattle", "T0174");
					}
					NavigatetoHamburgerMenu(userType);
					verifyElementPresentAndClick(MPWAHamburgermenuPage.objHipiLipSyncBattleText, "Hipi LipSyncBattle Button");
					getURL();
					if(url.equals("hipi.co.in/dramebaaz")) {
						logger.info("User is able to click the Hipi LipSyncBattle Button");	
						extent.extentLoggerPass("", "User is able to click the Hipi LipSyncBattle Button");	
					}else {
						logger.info("User is not able to click the Hipi LipSyncBattle Button");	
						extent.extentLoggerFail("", "User is not able to click the Hipi LipSyncBattle Button");
					}
					Back(1);
				}
		
		
		
		
				//Click on Zee Cine Award
				public void clickOnZeeCineAwardButton(String userType) throws Exception {
					
					if(userType.equalsIgnoreCase("Guest")) {
						extent.HeaderChildNode("T0175- Guest User - Check if user is able to click on the Zee cine award  button and should navigate to respective screen.");
						System.out.println("T0175");
						extent.extentLogger("Zee cine award", "T0175");
					}else {
						extent.HeaderChildNode("T0176 Logged in User - Check if user is able to click on the Zee cine award button and should navigate to respective screen.");
						System.out.println("T0176");
						extent.extentLogger("Zee cine award", "T0176");
					}
					NavigatetoHamburgerMenu(userType);
					verifyElementPresentAndClick(MPWAHamburgermenuPage.objZeeCineAwardText, "Zee Cine Award");
					getURL();
					if(url.equals("hipi.co.in/hipi-app-for-zee-cine-awards-2023-voting")) {
						logger.info("User is able to click the Zee Cine Award");	
						extent.extentLoggerPass("", "User is able to click the Zee Cine Award");	
					}else {
						logger.info("User is not able to click the Zee Cine Award");	
						extent.extentLoggerFail("", "User is not able to click the Zee Cine Award");
					}	
					Back(1);
				}
		
				
				
				public void InstallingOurApp(String userType) throws Exception {
					
					if(userType.equalsIgnoreCase("Guest")) {
						extent.HeaderChildNode("T0167- Guest User - Check if user is able to See the \"Installing our app won', \"'\", 't take any space on your phone.\" button .");
						System.out.println("T0167");
						extent.extentLogger("Installing our app", "T0167");
					}else {
						extent.HeaderChildNode("T0168- Logged in User - Check if user is able to See the \"Installing our app won', \"'\", 't take any space on your phone.\" button .");
						System.out.println("T0168");
						extent.extentLogger("Installing our app", "T0168");
					}
					
					NavigatetoHamburgerMenu(userType);
					
					if(verifyElementPresent(MPWAHamburgermenuPage.objInstallingOurAppText, "Installing Our App Text")) {
						logger.info("User is able to see the Installing Our App Text");	
						extent.extentLoggerPass("", "User is able to see the Installing Our App Text");	
					}else {
						logger.info("User is not able to see the Installing Our App Text");	
						extent.extentLoggerFail("", "User is not able to see the Installing Our App Text");
					}	
					
				}
				
				
				
		
}
	

	
	
	
	
	


