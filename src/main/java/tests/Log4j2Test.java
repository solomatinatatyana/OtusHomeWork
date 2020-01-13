package tests;

import config.BaseTest;
import config.MyTestListener;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Test(groups = "smoke")
public class Log4j2Test extends BaseTest {
    private static final Logger logger = LogManager.getLogger(Log4j2Test.class);

    @Test()
    public void testLogger(){
        logger.info("INFO");
        logger.warn("WARN");
        logger.error("ERROR");
        logger.debug("DEBUG");
    }

}
