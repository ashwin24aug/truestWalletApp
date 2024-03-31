import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TrustWalletReceiveCryptoTest {

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
            // Test Case: Receive cryptocurrency via Trust Wallet
            // Assuming user is already logged in and has generated a receiving address

            // Click on the cryptocurrency to receive
            MobileElement cryptoCurrency = driver.findElement(By.xpath("//android.widget.TextView[@text='Bitcoin']")); // Change to the desired cryptocurrency
            cryptoCurrency.click();

            // Click on the "Receive" button
            MobileElement receiveButton = driver.findElement(By.id("com.wallet.crypto.trustapp:id/action_receive"));
            receiveButton.click();

            // Verify the receiving address
            MobileElement receivingAddress = driver.findElement(By.id("com.wallet.crypto.trustapp:id/receiving_address_textview"));
            String address = receivingAddress.getText();
            System.out.println("Receiving Address: " + address);

            // Optionally, you can copy the receiving address to clipboard or perform additional verification
            // Example:
            MobileElement copyButton = driver.findElement(By.id("com.wallet.crypto.trustapp:id/copy_button"));
            copyButton.click();
            // Verify that the receiving address is copied to the clipboard
            // Example:
            String clipboardText = driver.getClipboardText();
            assert clipboardText.equals(address);
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
