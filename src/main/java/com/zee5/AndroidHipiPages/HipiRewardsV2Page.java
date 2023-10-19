package com.zee5.AndroidHipiPages;

import org.openqa.selenium.By;

public class HipiRewardsV2Page {

	//Rewards scratch card
		public static By objUnlockAward = By.xpath("//*[@resource-id='com.zee5.hipi:id/firstText' and contains(@text,'unlocked')]");
		
		//Scratch the card below
		public static By objScratchCard = By.xpath("//*[@resource-id='com.zee5.hipi:id/secondText' and contains(@text,'Scratch')]");
		
		//How it works
		public static By objHowItWorks = By.xpath("//*[@resource-id='com.zee5.hipi:id/howItWorksTextView']");
		
		//Earned coins
		public static By objEarnedCoins = By.xpath("//*[@resource-id='com.zee5.hipi:id/earnedTextView']");
		
		//Earned label
		public static By objEarnedLabel = By.xpath("//*[@resource-id='com.zee5.hipi:id/earnedLabel']");
		
		//Coins won layout
		public static By objCoinsWonLayout = By.xpath("//*[@resource-id='com.zee5.hipi:id/wonCoinsLayout']");
		
		//Card view layout
		public static By objCardViewLayout = By.xpath("//*[@resource-id='com.zee5.hipi:id/coinsCardViewLayout']");
		
		//Redeem now button
		public static By objRedeemNowBtn = By.xpath("//*[@resource-id='com.zee5.hipi:id/redeemNowTextView']");
		
		//Redeem later button
		public static By objRedeemLaterBtn = By.xpath("//*[@resource-id='com.zee5.hipi:id/redeemLaterTextView']");
		
		//Android Settings Search bar
		public static By objSettingsSearchBar = By.xpath("//*[@resource-id='com.android.settings:id/search_action_bar']");
		
		//Privacy auto suggestion
		public static By objPrivacyAutoSuggestion = By.xpath("//*[@resource-id='android:id/title' and @text='Privacy']");
		
		//Ads option
		public static By objAdsOption = By.xpath("//*[@text='Manage ads personalization on this device']");
		
		//Reset advertising ad
		public static By objResetAdId = By.xpath("//*[@text='Reset advertising ID']");
		
		//Confirm button
		public static By objConfirmBtn = By.xpath("//*[@resource-id='android:id/button1' and @text='CONFIRM']");

}
