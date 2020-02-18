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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVHelperTest extends BaseTest {
    public ICSVHelper csvHelper;
    private  static  final String regExFloat = "^[0-9]*[.,][0-9]+$";


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

    @Test()
    public void testString(){
        String a = "Двигатель 3.7 бензиновый (304 л.с.)";
        Pattern pattern = Pattern.compile("[0-9]*[.,][0-9]");
        Matcher matcher = pattern.matcher("Двигатель 3.7 бензиновый (304 л.с.)");
       if(matcher.find())
           System.out.println("volume: " +matcher.group());
    }

    @Test()
    public void testStringYear(){
        String ab = "Машина 2002 года выпуска, была куплена в 2011 году";
        ab = ab.substring(0,ab.indexOf(","));
        System.out.println(ab);
        Pattern pattern = Pattern.compile("[0-9]*[.,]?[0-9]");
        Matcher matcher = pattern.matcher(ab);
        if(matcher.find())
            System.out.println("year: " +matcher.group());
    }
}
