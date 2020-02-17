package tests.HomeWork4Tests;

import Helpers.SimpleDateHelper;
import config.BaseTest;
import org.testng.annotations.Test;
import sun.java2d.pipe.SpanShapeRenderer;
import tests.HomeWork4Tests.dto.Offer;
import tests.HomeWork4Tests.utils.CSVHelper;
import tests.HomeWork4Tests.utils.ICSVHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVHelperTest extends BaseTest {
    public ICSVHelper csvHelper;


    @Test()
    public void test(){
        csvHelper = new CSVHelper();

        List<Offer> OfferList = new
                ArrayList<>();
        Offer emp1 = new Offer
                ("Mahafuj", "24", "HTc", "75000", "qqqq", "qqqqq");
        Offer emp2 = new Offer
                ("Aman", "24", "microsoft", "79000", "qqqq", "qqqq");
        OfferList.add(emp1);
        OfferList.add(emp2);

        csvHelper.writeToFile(OfferList);
        System.out.println(new Date().toString());
    }
}
