package tests.HomeWork5Tests;

public class Contact {
    private String communicationMethod;
    private String communicationText;

    public Contact() { }

    public String getCommunicationMethod() {
        return communicationMethod;
    }

    public void setCommunicationMethod(String communicationMethod) {
        this.communicationMethod = communicationMethod;
    }

    public String getCommunicationText() {
        return communicationText;
    }

    public void setCommunicationText(String communicationText) {
        this.communicationText = communicationText;
    }

    public Contact(String communicationMethod, String communicationText) {
        this.communicationMethod = communicationMethod;
        this.communicationText = communicationText;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "communicationMethod='" + communicationMethod + '\'' +
                ", communicationText='" + communicationText + '\'' +
                '}';
    }
}
