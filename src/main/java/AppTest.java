import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AppTest {
    @Test
    public static void appTest() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        VisualGridRunner runner = new VisualGridRunner(1);
        Eyes eyes = new Eyes(runner);
        setUp(eyes);
        try {
            mainPage(webDriver, eyes, Constants.v1URL);
            filterPage(webDriver,eyes,Constants.v1URL);
            productPage(webDriver, eyes, Constants.v1URL);
        } finally {
            tearDown(webDriver, runner);
        }
    }

    public static void setUp(Eyes eyes) {
        Configuration config = new Configuration();
        config.setApiKey("Cz993z81tkt6le8OOroo101fCAKeQOMHD9bKCy2ggMRK8k110");
        config.setBatch(new BatchInfo("Holiday Shopping"));
        config.addBrowser(1200, 800, BrowserType.CHROME);
        config.addBrowser(1200, 800, BrowserType.FIREFOX);
        config.addBrowser(1200, 800, BrowserType.EDGE_CHROMIUM);
        config.addBrowser(1200, 800, BrowserType.SAFARI);
        config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
        eyes.setConfiguration(config);
    }

    private static void tearDown(WebDriver webDriver, VisualGridRunner runner) {
        webDriver.quit();
        TestResultsSummary allTestResults = runner.getAllTestResults(false);
        System.out.println(allTestResults);
    }

    public static void mainPage(WebDriver webDriver, Eyes eyes, String URL) {
        try {
            webDriver.get(URL);
            eyes.open(webDriver, "AppliFashion", "Test 1", new RectangleSize(1200, 800));
            eyes.check(Target.window().fully().withName("main page"));
            eyes.closeAsync();
        } finally  {
            eyes.abortAsync();
        }

    }
    public static void filterPage(WebDriver webDriver, Eyes eyes, String URL) {
        try {
            webDriver.get(URL);
            eyes.open(webDriver, "AppliFashion", "Test 2", new RectangleSize(1200, 800));
            //webDriver.findElement(By.xpath("//input[@id='colors__Black']/../span[1]")).click();
            //webDriver.findElement(By.id("filterBtn")).click();
            //eyes.check(Target.region(By.id("product_grid")).withName("filter by color"));
            eyes.closeAsync();
        } finally  {
            eyes.abortAsync();
        }

    }
    public static void productPage(WebDriver webDriver, Eyes eyes, String URL) {
        try {
            webDriver.get(URL);
            eyes.open(webDriver, "AppliFashion", "Test 3", new RectangleSize(1200, 800));
            //webDriver.findElement(By.xpath("//h3[text()='Appli Air x Night']/..")).click();
            //eyes.check(Target.window().fully().withName("product details"));
            eyes.closeAsync();
        } finally  {
            eyes.abortAsync();
        }

    }
}
