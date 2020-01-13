package config.listeners;

import org.testng.*;

public class MyTestListener implements ISuiteListener{

    public void onStart(ISuite suite) {
        System.out.println("START [" + suite.getXmlSuite().getName()+  "] SUITE");
    }

    public void onFinish(ISuite suite) {
        System.out.println("END OF [" + suite.getXmlSuite().getName()+  "] SUITE");
    }
}
