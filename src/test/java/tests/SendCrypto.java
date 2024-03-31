import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TrustWalletSendCryptoTest {

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
            // Test Case: Send cryptocurrency via Trust Wallet
            // Assuming user is already logged in and has sufficient balance

            // Click on the cryptocurrency to send
            MobileElement cryptoCurrency = driver.findElement(By.xpath("//android.widget.TextView[@text='Bitcoin']")); // Change to the desired cryptocurrency
            cryptoCurrency.click();

            // Click on the "Send" button
            MobileElement sendButton = driver.findElement(By.id("com.wallet.crypto.trustapp:id/action_send"));
            sendButton.click();

            // Enter recipient address
            MobileElement recipientAddressField = driver.findElement(By.id("com.wallet.crypto.trustapp:id/recipient_edittext"));
            recipientAddressField.sendKeys("18cBEMRxXHqzWWCxZNtU91F5sbUNKhL5PX"); // Replace with recipient's address

            // Enter amount
            MobileElement amountField = driver.findElement(By.id("com.wallet.crypto.trustapp:id/amount_edittext"));
            amountField.sendKeys("0.001"); // Replace with the desired amount to send

  
