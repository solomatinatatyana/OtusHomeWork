import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;


public class FirstTest {
    private static final Logger logger = LogManager.getLogger(Test.class);
    @Test
    public void test(){
        logger.info("JAVA_HOME: {}", System.getProperty("java.home"));
        logger.info("OS_NAME: {}", System.getProperty("os.name"));
        logger.warn("OS_NAME: {}", System.getProperty("os.name"));
        logger.error("OS_NAME: {}", System.getProperty("os.name"));
    }
}
