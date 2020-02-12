package tests.HomeWork4Tests.dto;

public class Offer {
    private String deepLink;
    private String year;
    private String price;
    private String car;
    private String model;
    private String volume;

    public Offer() { }

    public Offer(String deepLink, String year, String price, String type, String model, String volume) {
        this.deepLink = deepLink;
        this.year = year;
        this.price = price;
        this.car = type;
        this.model = model;
        this.volume = volume;
    }

    public Offer(Offer offer) {
        this.deepLink = offer.deepLink;
        this.model = offer.model;
        this.price = offer.price;
        this.car = offer.car;
        this.volume = offer.volume;
        this.year = offer.year;
    }

    public String getDeepLink() { return deepLink; }

    public void setDeepLink(String deepLink) { this.deepLink = deepLink; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public String getType() { return car; }

    public void setType(String type) { this.car = type; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }

    @Override
    public String toString() {
        return "Car{" +
                "deepLink='" + deepLink + '\'' +
                ", year='" + year + '\'' +
                ", price='" + price + '\'' +
                ", car='" + car + '\'' +
                ", model='" + model + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
