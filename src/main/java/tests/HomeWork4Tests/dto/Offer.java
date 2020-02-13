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

    public String getCar() { return car; }

    public void setCar(String car) { this.car = car; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }

    public static OfferBuilder anOffer() {return new OfferBuilder();}

    public static final class OfferBuilder{
        private String deepLink;
        private String year;
        private String price;
        private String car;
        private String model;
        private String volume;

        public OfferBuilder withDeepLink(String deepLink) {
            this.deepLink = deepLink;
            return this;
        }

        public OfferBuilder withYear(String year) {
            this.year = year;
            return this;
        }

        public OfferBuilder withPrice(String price) {
            this.price = price;
            return this;
        }

        public OfferBuilder withCar(String car) {
            this.car = car;
            return this;
        }

        public OfferBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public OfferBuilder withVolume(String volume) {
            this.volume = volume;
            return this;
        }

        public Offer build(){
            Offer offer = new Offer();
            offer.setCar(car);
            offer.setPrice(price);
            offer.setModel(model);
            offer.setDeepLink(deepLink);
            offer.setVolume(volume);
            offer.setYear(year);
            return offer;
        }
    }

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
