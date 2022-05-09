package Model;

public class Service {
    private String nameService;
    private int price;

    public Service() {
    }

    public Service(String nameService, int price) {
        this.nameService = nameService;
        this.price = price;
    }

    @Override
    public String toString() {
        return nameService + "-" +price;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }



}
