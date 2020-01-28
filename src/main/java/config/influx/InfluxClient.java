package config.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class InfluxClient {
    private final static String SHOW_DATABASES = "SHOW DATABASES";
    private final static String CREATE_DATABASE = "CREATE DATABASE ";
    private final InfluxDB db;

    public InfluxClient(InfluxConfig config){
        db = InfluxDBFactory.connect(config.getHost(), config.getUser(), config.getPass());
        System.out.println(db.ping().isGood() ? "Influx Connected" : "Influx Connection Failed");
    }

    public void createDbIfNeeded(String dbName){
        Query q  = new Query(SHOW_DATABASES);
        QueryResult queryResult = db.query(q);
        QueryResult.Result result = queryResult.getResults().get(0);
        boolean dbExists = result.getSeries().get(0).getValues().stream().anyMatch(val-> val.contains(dbName));

        if(!dbExists){
            System.out.println("Create database " + dbName);
            db.query(new Query(CREATE_DATABASE + dbName));
        }
        db.setDatabase(dbName);
    }

    public void writePoint(Point point){
        db.write(point);
    }
}
