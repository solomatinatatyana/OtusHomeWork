package tests.HomeWork4Tests.utils;

import tests.HomeWork4Tests.dto.Offer;

import java.util.List;

public interface ICSVHelper {
    List<String> readFromFile();
    void writeToFile(List<Offer> offerList);
}
