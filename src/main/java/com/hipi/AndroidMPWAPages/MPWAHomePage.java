package com.hipi.AndroidMPWAPages;

import org.openqa.selenium.By;

public class MPWAHomePage {
	
	public static By objDiscoverButton = By.xpath("(//*[@class='flex flex-col text-white text-xs items-center'])[2] | //*[@resource-id='Explore_icon_for_You'] | //*[@text='Explore'] | (//div[@name='Explore_icon_for_You'])[1]");
	
	public static By objProfileIcon = By.xpath("//*[@class='flex flex-col  items-center justify-between'] | //*[@resource-id='Profile_Icon_for_You']");
	
	public static By objForYou = By.xpath("//*[@text='For You']");
		
	public static By objHomeIcon = By.xpath("(//*[@class='flex flex-col text-white text-xs items-center'])[1] | //*[@resource-id='vector']");
	
	public static By objLikeIcon = By.xpath("(//*[@id='like'])[1] | //*[@class='android.widget.Image' and (./preceding-sibling::* | ./following-sibling::*)[@id='Number_of_Likes']]");
	
	public static By objUserNameInFeedScreen = By.xpath("//*[@class=' mb-1 mt-1.5 font-semibold text-sm flex '] | //*[@resource-id='Username_for_You']");

	public static By objSwipeUpForNextVideo = By.xpath("//*[@class='flex py-2 px-4 bg-gray text-white font-medium mt-12'] | //*[@text='Swipe up for next video']/preceding-sibling::*");

	public static By objCommentButton = By.xpath("//*[@class='android.widget.Image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[@class='android.view.View' and ./*[@id='UserProfile_for_You']]]]");

	public static By objOpenApp = By.xpath("//*[@class='flex justify-center items-center text-sm md:text-base w-full font-semibold'] | //*[@id='Open_Icon_for_You']");

	public static By objCreatorButton = By.xpath("//*[@class='relative py-3  px-1 text-center flex flex-col text-white text-xs  items-center'] | //*[@resource-id='Create_Icon_for_You']");

	public static By objOpenButton = By.xpath("(//*[@text='Open'])[2]| //*[@class='font-semibold text-sm border border-hipired rounded py-1 px-2 mr-1 bg-hipired text-white'] | //*[@text='Open']");

	public static By objInstallButton = By.xpath("//*[@contentDescription='Install']");

	public static By objProgressBar=By.xpath("//*[@class='absolute top-0 left-0 h-1 bg-gray-100'] | //*[@resource-id='exo_progress']");

	public static By objProgressBarID=By.xpath("//*[@css='SPAN.absolute.top-0.left-0.h-1.bg-gray-100']");

	public static By objCancelButton=By.xpath("//*[@resource-id='com.daemon.shelper:id/CANCEL']");

	public static By objAndroidmwebSearchAddress=By.xpath("//*[@resource-id='com.android.chrome:id/search_box_text'] | //*[@resource-id='com.android.chrome:id/url_bar']");
	//*[@resource-id='com.android.chrome:id/line_2']
	public static By objAndroidmwebSearchButtonInKeyboardORclickURL=By.xpath("//*[@resource-id='com.android.chrome:id/line_2']");
	
	
	public static By objUnmute=By.xpath("//*[@text='Unmute'] | //*[@id='Unmute_Icon_for_You']"); 
	
	
	public static By objShopButton=By.xpath("//*[@id='ShopButton_Icon_for_You']"); 
	
	
	public static By objOutfit=By.xpath("//*[@id='Outfit Tab_shop']"); 
	
	public static By objSoundname=By.xpath("//*[@id='Soun_Name_Description_for_You']"); 
	
	public static By objUserName=By.xpath("//*[@id='Username_for_You']"); 
	
	
	public static By objPauseIcon=By.xpath("//*[@id='volumne_indicator']");
	
	public static By objFollowingButton =By.xpath("//*[@id='Following_Icon_for_You']");
	
	public static By objShareICON =By.xpath("//*[@id='Share_for_You'] | //*[@class='android.widget.Image' and ./parent::*[@id='Share_for_You']]");
	
	public static By objplusICON =By.xpath("//*[@resource-id='+_for_You']");
	
	
	public static By objProfileThumbnail =By.xpath("//*[@id='UserProfile_for_You']");
	
	public static By objEmbedIcon =By.xpath("//*[@id='Embed_for_You']");
	
	public static By objFollowIcon =By.xpath("//*[@resource-id='Follow_for_You']");
	
	public static By objVideoDescription =By.xpath("//*[@id='Hashtag_Description_for_You']");
	
	public static By objOpenButtonAndInstallButton=By.xpath("//*[@resource-id='Open_Icon_for_You']");
	
	public static By objVideoUIID=By.xpath("//*[@resource-id='com.android.chrome:id/subtitle_preview'] | //*[contains(@text,'<blockquote')]");
	
	public static By objCloseButtonEmbed =By.xpath("//*[@id='close_button_image']");
	
	
	public static By objShareScreen =By.xpath("//*[@resource-id='com.android.chrome:id/subtitle_preview']");
	
	public static By objMoreIcon =By.xpath("//*[contains(@text,'..MORE')] | //*[@text='..MORE']");
	
	public static By objLessIcon =By.xpath("//*[contains(@text,'..LESS')] | //*[@text='..LESS']");
	
	public static By objHashTag =By.xpath("//*[contains(@text,'#')]");
	
	public static By objGetTheFullExperienceOnTheHipiApp =By.xpath("//*[contains(@text,'Get the')]");
	
	public static By objNotNow =By.xpath("//*[contains(@text,'Not now')]");
	
	public static By objLoginFollowingScreen =By.xpath("//*[contains(@text,'Login')]");
	
	public static By objhamburgerMenu =By.xpath("//*[@resource-id='Hamburger Menu_Feed']");
	
	public static By objHandiAnimation =By.xpath("//*[@class='android.widget.Image' and ./parent::*[./parent::*[@id='swipe_up']] and (./preceding-sibling::* | ./following-sibling::*)[@class='android.widget.TextView']]");
	
	
	public static By objAcceptAllCookies = By.className("android.widget.Button");
	
}




