package tests.HomeWork4Tests.utils;

import Helpers.SimpleDateHelper;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import tests.HomeWork4Tests.dto.Offer;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CSVHelper implements ICSVHelper{

    private final String CSV_LOCATION = "src/main/resources/Offers.csv";

    public CSVHelper() { }

    @Override
    public List<String> readFromFile() {
        return null;
    }

    @Override
    public void writeToFile(List<Offer> offerList) {
        try {
            File file = new File(CSV_LOCATION);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            ColumnPositionMappingStrategy<Offer> mappingStrategy = new ColumnPositionMappingStrategy<>();
            mappingStrategy.setType(Offer.class);
            String[] columns = new String[] {"Car", "Model", "Volume", "Year", "Price", "Deeplink"};
            mappingStrategy.setColumnMapping(columns);

            StatefulBeanToCsvBuilder<Offer> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<Offer> beanWriter = builder.withMappingStrategy(mappingStrategy).build();

            beanWriter.write(offerList);
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
