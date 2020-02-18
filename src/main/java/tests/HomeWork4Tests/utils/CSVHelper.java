package tests.HomeWork4Tests.utils;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tests.HomeWork4Tests.dto.Offer;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CSVHelper implements ICSVHelper{
    private Logger log = LogManager.getLogger(CSVHelper.class);
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

            log.info("Записываем список собранных объявлений в файл [{}]", CSV_LOCATION);
            beanWriter.write(offerList);
            log.info("Запись в файл окончена");
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
