package tests.HomeWork4Tests.archive;

import config.BaseTest;
import org.testng.annotations.Test;
import tests.HomeWork4Tests.dto.Offer;
import tests.HomeWork4Tests.utils.CSVHelperImpl;
import tests.HomeWork4Tests.utils.ICSVHelper;
import tests.HomeWork4Tests.utils.SimpleDateHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVHelperImplTest extends BaseTest {
    public ICSVHelper csvHelper;
    private String CSV_NAME_FILE = "offers_"+SimpleDateHelper.getCurrentDateTime("yyyy-MM-dd_hh-mm-ss");
    private String CSV_PATH = "src/main/resources/offers/";
    private String CSV_LOCATION;

    @Test()
    public void test(){
        csvHelper = new CSVHelperImpl();

        List<Offer> OfferList = new ArrayList<>();
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
        String ab = "Машина была куплена в 2011 году";
        Pattern pattern = Pattern.compile("[0-9]*[.,]?[0-9]");
        Matcher matcher = pattern.matcher(ab);
        if(matcher.find())
            System.out.println("year: " +matcher.group());
    }

    @Test()
    public void test3(){
        String ab = "String   12212   skhsk'hks";
        ab = ab.replaceAll("\\s{2,}"," ");
        System.out.println(ab);
    }

    @Test()
    public void test4(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
        System.out.println(format.format(date));
        System.out.println(SimpleDateHelper.getCurrentDateTime("yyyy-MM-dd_hh-mm-ss"));
        CSV_LOCATION = CSV_PATH + CSV_NAME_FILE + ".csv";
        System.out.println(CSV_LOCATION);
    }

    @Test()
    public void test5() throws IOException {
        CSV_LOCATION = CSV_PATH + CSV_NAME_FILE + ".csv";
        System.out.println(CSV_LOCATION);
        File file = new File(CSV_LOCATION);
        file.createNewFile();
    }

}
