package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
    private static final Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) {

        logger.info("JAVA_HOME: {}", System.getProperty("java.home"));
        logger.info("OS_NAME: {}", System.getProperty("os.name"));
    }

}
