import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TrustWalletTest {

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
            // Test Case 1: Verify "Create a new wallet" button is available and enabled
            MobileElement createWalletButton = driver.findElementById("com.wallet.crypto.trustapp:id/create_wallet_button");
            assert createWalletButton.isDisplayed();
            assert createWalletButton.isEnabled();

            // Test Case 2: Click on "Create a new wallet" button
            createWalletButton.click();

            // Test Case 3: Enter 6 digit passcode
            for (int i = 1; i <= 6; i++) {
                MobileElement digitButton = driver.findElementByXPath("//android.widget.TextView[@text='" + i + "']");
                digitButton.click();
            }

            // Test Case 4: Reenter the passcode
            for (int i = 1; i <= 6; i++) {
                MobileElement digitButton = driver.findElementByXPath("//android.widget.TextView[@text='" + i + "']");
                digitButton.click();
            }

            // Test Case 5: Click on "Back up manually" option on "Back up secret phrase" screen
            MobileElement backupManuallyButton = driver.findElementById("com.wallet.crypto.trustapp:id/buttonManualBackup");
            backupManuallyButton.click();

            // Test Case 6: On next screen, tap all the checkboxes and hit "Continue" button
            List<MobileElement> checkboxes = driver.findElementsById("com.wallet.crypto.trustapp:id/checkbox");
            for (MobileElement checkbox : checkboxes) {
                checkbox.click();
            }
            MobileElement continueButton = driver.findElementById("com.wallet.crypto.trustapp:id/buttonContinue");
            continueButton.click();

            // Test Case 7: Click on "Continue" button
            continueButton.click();

            // Test Case 8: Verify details on "Confirm secret phrase" screen
            MobileElement confirmButton = driver.findElementById("com.wallet.crypto.trustapp:id/buttonConfirm");
            assert confirmButton.isDisplayed();
            assert !confirmButton.isEnabled(); // Confirm button should be disabled until all phrases are selected

            // Test Case 9: Select all correct phrases and click "Confirm" button
            List<MobileElement> phrases = driver.findElementsById("com.wallet.crypto.trustapp:id/phrase_word");
            for (MobileElement phrase : phrases) {
                phrase.click();
            }
            assert confirmButton.isEnabled(); // Confirm button should be enabled after selecting all phrases
            confirmButton.click();

            // Test Case 10: Verify that "Main wallet" is displayed to user
            MobileElement mainWallet = driver.findElementByXPath("//android.widget.TextView[@text='Main wallet']");
            assert mainWallet.isDisplayed();
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
