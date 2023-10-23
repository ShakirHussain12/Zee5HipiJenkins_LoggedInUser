package com.zee5.Android_HipiScripts;

import org.apache.http.impl.conn.tsccm.WaitingThread;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.excel.Time_ExcelUpdate;
import com.utility.Utilities;

public class Android_MWEB {
	private com.business.zee.AndroidHipiMPWABusinessLogic AndroidHipiMPWABusinessLogic;

	@BeforeTest
	public void AppLaunch() throws Exception {
		System.out.println("Launching MWEB App");
		Utilities.relaunch = true; // Clear App Data on First Launch
		AndroidHipiMPWABusinessLogic = new com.business.zee.AndroidHipiMPWABusinessLogic("Chrome");
		
	}
	
	@Test(priority = 0)
	@Parameters({ "userType"})
	public void launchApptime(String userType) throws Exception {
//		Thread.sleep(5000);
		System.out.println("Enter Url");
		//AndroidHipiMPWABusinessLogic.relaunch(true);
//		AndroidHipiMPWABusinessLogic.getURl("https://www.hipi.co.in/hashtag-feed/hipikaromorekaro");
//		Thread.sleep(5000);
		AndroidHipiMPWABusinessLogic.launchApptime();	
//		AndroidHipiMPWABusinessLogic.launchApp_And_Check_ProgressBar();
//		AndroidHipiMPWABusinessLogic.verifyProgressBar();

	}
	/*
	@Test(priority = 1)
	@Parameters({ "userType"})
	public void Login(String userType) throws Exception {

		AndroidHipiMPWABusinessLogic.SwipeUpForNextVideoAnimation(userType);
		AndroidHipiMPWABusinessLogic.loginTime(userType);
		
//		AndroidHipiMPWABusinessLogic.loginWithPhoneNumberTime(userType);

	}
	
	@Test(priority = 2)
	@Parameters({ "userType"})
	public void DiscoverScreenLoadTime(String userType) throws Exception {

		AndroidHipiMPWABusinessLogic.DiscoverScreenLoadTime();
	} 	

	@Test(priority = 3)
	@Parameters({ "userType"})
	public void AutoSuggestionTime(String userType) throws Exception {

		AndroidHipiMPWABusinessLogic.AutoSuggestionTime("kundali");

	}
	@Test(priority = 4)
	@Parameters({ "userType"})
	public void OnClickingBannerScreenLoadTime(String userType) throws Exception {

		AndroidHipiMPWABusinessLogic.OnClickingBannerScreenLoadTime(userType);

	}

	@Test(priority = 5)
	@Parameters({ "userType"})
	public void hashtagDetailPageTime(String userType) throws Exception {

		AndroidHipiMPWABusinessLogic.hashtagDetailPageTime();

	}

	@Test(priority =6)
	@Parameters({ "userType"})
	public void BannerLoadTime(String userType) throws Exception {

		AndroidHipiMPWABusinessLogic.BannerLoadTime();

	}
	@Test(priority = 7)
	@Parameters({ "userType"})
	public void SearchScreenTimePostSearchingAKeyword(String userType) throws Exception {

		AndroidHipiMPWABusinessLogic.SearchScreenTimePostSearchingAKeyword("kundali");
		
	}
	
	@Test(priority = 8)
	@Parameters({ "userType"})
	public void Search(String userType) throws Exception {
		AndroidHipiMPWABusinessLogic.searchResults("Shivangi ","MPWA  TC012");
		AndroidHipiMPWABusinessLogic.searchResults("shraddha arya","MPWA  TC013");
		AndroidHipiMPWABusinessLogic.searchResults("punjabi singers","MPWA  TC014");
		AndroidHipiMPWABusinessLogic.searchResults("bollywood actress","MPWA  TC015");
		AndroidHipiMPWABusinessLogic.searchResults("punjabi videos", "MPWA  TC016");
		AndroidHipiMPWABusinessLogic.searchResults("funny video", "MPWA  TC017");
		//AndroidHipiMPWABusinessLogic.searchResults("xxx", "MPWA  TC015");
	}
	

	@Test(priority = 9)
	@Parameters({ "userType"})
	public void WithAppWithOutAppTCs(String userType) throws Exception {

//	AndroidHipiMPWABusinessLogic.commentWithApp(userType);
//	AndroidHipiMPWABusinessLogic.relaunch(false);
	AndroidHipiMPWABusinessLogic.CreatorWithApp(userType);
	AndroidHipiMPWABusinessLogic.relaunch(false);
	AndroidHipiMPWABusinessLogic.openOptionINMPWA(userType);
	AndroidHipiMPWABusinessLogic.relaunch(false);
	AndroidHipiMPWABusinessLogic.editProfile(userType);
//	AndroidHipiMPWABusinessLogic.relaunch(false);
//	AndroidHipiMPWABusinessLogic.withOutAppCommentButton(userType);
//	AndroidHipiMPWABusinessLogic.relaunch(false);
//	AndroidHipiMPWABusinessLogic.withOutAppCreatorButton(userType);
//	AndroidHipiMPWABusinessLogic.relaunch(false);
//	AndroidHipiMPWABusinessLogic.openOptionINMPWAWithoutApp(userType);
//	AndroidHipiMPWABusinessLogic.relaunch(false);
//	AndroidHipiMPWABusinessLogic.WithOutAppEditProfile(userType);
	
	}
	@Test(priority = 10)
	@Parameters({ "userType"})
	public void logout(String userType) throws Exception {
		AndroidHipiMPWABusinessLogic.relaunch(false);
		AndroidHipiMPWABusinessLogic.TimeStamplogOut();
	}
*/
	
	
	
	
	
	
	@Test(priority = 9)
	@Parameters({ "userType"})
	public void FeedP0TCs(String userType) throws Exception {
		
		AndroidHipiMPWABusinessLogic.relaunch(true);
		AndroidHipiMPWABusinessLogic.LoginAndNivigateToHipi(userType);
		
//		AndroidHipiMPWABusinessLogic.verifyUnmuteButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyForYou(userType);
//		AndroidHipiMPWABusinessLogic.verifyFollowing(userType);
//		AndroidHipiMPWABusinessLogic.PlusIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyLikeIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyProfileThumbnail(userType);
//		AndroidHipiMPWABusinessLogic.verifyComment(userType);
//		AndroidHipiMPWABusinessLogic.verifyShareIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyEmbedIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyUserName(userType);
//		AndroidHipiMPWABusinessLogic.verifyFollowIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyVideoDescription(userType);
//		AndroidHipiMPWABusinessLogic.verifySoundName(userType);
//		AndroidHipiMPWABusinessLogic.verifyHomeIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyExploreIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyCreateIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyProfileIcon(userType);
//		AndroidHipiMPWABusinessLogic.clickOnPlushIconGuestUser(userType);
//		AndroidHipiMPWABusinessLogic.clickOnPlushIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyuserName(userType);
//		AndroidHipiMPWABusinessLogic.clickOnFollowNonSubUser(userType);
//		AndroidHipiMPWABusinessLogic.clickOnFollowGuestUser(userType);	
//		AndroidHipiMPWABusinessLogic.clickOnShareIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyEmbed(userType);
//		AndroidHipiMPWABusinessLogic.verifyMoreIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyOpenButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyHashtag(userType);
//		AndroidHipiMPWABusinessLogic.verifySoundname(userType);
//		AndroidHipiMPWABusinessLogic.verifyLessIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyExploreIconclickable(userType);
//		AndroidHipiMPWABusinessLogic.veridyProfileIcon(userType);
//		AndroidHipiMPWABusinessLogic.veridyProfileIconNonSub(userType);
//		AndroidHipiMPWABusinessLogic.verifyFollowingclickableGuestUser(userType);
//		AndroidHipiMPWABusinessLogic.verifyFollowingclickableNonUser(userType);
		
		//Shop
//		AndroidHipiMPWABusinessLogic.verifyShopButton(userType);
//		AndroidHipiMPWABusinessLogic.clickonShopButton(userType);
//		AndroidHipiMPWABusinessLogic.verifySavedMomentsTab(userType);
//		AndroidHipiMPWABusinessLogic.verifyArtisteTitle(userType);
//		AndroidHipiMPWABusinessLogic.verifyLessButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyMoresButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyOutfiTab(userType);
//		AndroidHipiMPWABusinessLogic.verifyAccessoriesTab(userType);
//		AndroidHipiMPWABusinessLogic.verifyBeautyTab(userType);
//		AndroidHipiMPWABusinessLogic.verifyHariTab(userType);
//		AndroidHipiMPWABusinessLogic.verifyAccessoriesTabClickable(userType);
//		AndroidHipiMPWABusinessLogic.verifyBeautyTabClickable(userType);
//		AndroidHipiMPWABusinessLogic.verifyHairTabClickable(userType);
//		AndroidHipiMPWABusinessLogic.verifyOutfitTaClickable(userType);
//		AndroidHipiMPWABusinessLogic.verifyProductNameAndImageAndDescriptionAndBuynowShopPage(userType);
		
		
		//Hamburger Menu
		
//		AndroidHipiMPWABusinessLogic.verifyHamburgerMenu(userType);
//		AndroidHipiMPWABusinessLogic.clickOnHamburgerMenu(userType);
//		AndroidHipiMPWABusinessLogic.verifyCrossIcon(userType);
//		AndroidHipiMPWABusinessLogic.verifyAboutButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyNewsroomButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyBlogButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyAdsButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyCommunityGuidelinesButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyPrivacyPolicyButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyTermsofUseButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyGrievanceButton(userType);
//		AndroidHipiMPWABusinessLogic.verifyContactusButton(userType);
//		
//		AndroidHipiMPWABusinessLogic.verifyHipiStunnerText(userType);
//		AndroidHipiMPWABusinessLogic.verifyHipiGOATText(userType);
//		AndroidHipiMPWABusinessLogic.verifyHipiHipiLipSyncBattleText(userType);
//		AndroidHipiMPWABusinessLogic.verifyZeeCineAwardText(userType);
//		AndroidHipiMPWABusinessLogic.clickOnCrossIcon(userType);
//		AndroidHipiMPWABusinessLogic.clickOnAboutButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnNewsroomButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnBlogButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnAdsButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnCommunityGuidelinesButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnPrivacyPolicyButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnTermsOfUseButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnGrievanceeButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnContactusButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnHipiStunnerButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnHipiGOATButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnHipiLipSyncBattleButton(userType);
		AndroidHipiMPWABusinessLogic.clickOnZeeCineAwardButton(userType);
		
		
		AndroidHipiMPWABusinessLogic.InstallingOurApp(userType);
		AndroidHipiMPWABusinessLogic.verifyInstallButton(userType);
		
		
		
//		AndroidHipiMPWABusinessLogic.verifyCreateIconClickable(userType);
//		AndroidHipiMPWABusinessLogic.clickOnCommentIcon(userType);
		
//		AndroidHipiMPWABusinessLogic.userProfileThumbnailNonSubUser(userType);//Nedd to Dis
//		AndroidHipiMPWABusinessLogic.videoUIRepate(userType);
		
		
//		AndroidHipiMPWABusinessLogic.verifyGetTheFullExperienceOnTheHipiApp(userType);
	}
	
	
	
	
	
	
	
	
	@AfterTest						
	public void tearDownApp() {		
		System.out.println("Quit the App");
		AndroidHipiMPWABusinessLogic.tearDown();	
		Time_ExcelUpdate.AutoFitColumn();
	}		
}
