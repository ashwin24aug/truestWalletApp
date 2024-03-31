import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrustWalletMultipleWalletsTest {

    public static void main(String[] args) throws Exception {
        // Set the desired capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "device-name");
        caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.wallet.crypto.trustapp");
        caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".launch.WalletActivity");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        // Set the Appium server URL
        URL appiumServerURL = new URL("http://localhost:4723/wd/hub");

        // Initialize the AndroidDriver
        AndroidDriver<MobileElement> driver = new AndroidDriver<>(appiumServerURL, caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            // Test Case 1: Open Trust Wallet mobile app
            // Already opened by launching the app

            // Test Case 2: Enter passcode
            // Code to enter passcode and navigate to the main wallet should be added here

            // Test Case 3: Click on "Settings" icon
            MobileElement settingsIcon = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='More options']"));
            settingsIcon.click();

            // Test Case 4: Click on "Wallets"
            MobileElement walletsOption = driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Wallets']"));
            walletsOption.click();

            // Test Case 5: Click on + sign on top right
            MobileElement addWalletButton = driver.findElement(By.id("com.wallet.crypto.trustapp:id/add_wallet_button"));
            addWalletButton.click();

            // Test Case 6: Click on "Create a new wallet"
            MobileElement createNewWalletOption = driver.findElement(By.xpath("//android.widget.TextView[@text='Create a new wallet']"));
            createNewWalletOption.click();

            // Test Case 7: Click on "Back up manually" option on "Back up secret phrase" screen
            MobileElement backupManuallyButton = driver.findElement(By.id("com.wallet.crypto.trustapp:id/buttonManualBackup"));
            backupManuallyButton.click();

            // Test Case 8: On next screen, tap all the checkboxes and hit "Continue" button
            List<MobileElement> checkboxes = driver.findElements(By.id("com.wallet.crypto.trustapp:id/checkbox"));
            for (MobileElement checkbox : checkboxes) {
                checkbox.click();
            }
            MobileElement continueButton = driver.findElement(By.id("com.wallet.crypto.trustapp:id/buttonContinue"));
            continueButton.click();

            // Test Case 9: Click on "Continue" button
            continueButton.click();

            // Test Case 10: Verify details on "Confirm secret phrase" screen
            MobileElement confirmButton = driver.findElement(By.id("com.wallet.crypto.trustapp:id/buttonConfirm"));
            assert confirmButton.isDisplayed();
            assert !confirmButton.isEnabled(); // Confirm button should be disabled until all phrases are selected

            // Test Case 11: Select all correct phrases and click "Confirm" button
            List<MobileElement> phrases = driver.findElements(By.id("com.wallet.crypto.trustapp:id/phrase_word"));
            for (MobileElement phrase : phrases) {
                phrase.click();
            }
            assert confirmButton.isEnabled(); // Confirm button should be enabled after selecting all phrases
            confirmButton.click();

            // Test Case 12: Verify that "Main wallet1" is displayed to user
            MobileElement mainWallet = driver.findElement(By.xpath("//android.widget.TextView[@text='Main wallet1']"));
            assert mainWallet.isDisplayed();

            // Test Case 13: Click on "Settings" icon
            settingsIcon.click();

            // Test Case 14: Click on "Wallets"
            walletsOption.click();

            // Test Case 15: Newly created wallet should be displayed in the wallet list
            MobileElement newlyCreatedWallet = driver.findElement(By.xpath("//android.widget.TextView[@text='Main wallet1']"));
            assert newlyCreatedWallet.isDisplayed();
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
