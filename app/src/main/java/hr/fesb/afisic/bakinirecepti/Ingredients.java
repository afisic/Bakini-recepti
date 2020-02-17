package hr.fesb.afisic.bakinirecepti;

public class Ingredients {
    private String shoppingList;
    private String name;
    private Double amount;
    private String unit;
    private int id;

    public Ingredients(String name, Double amount, String unit){
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.id = id;
    }
    public Ingredients(int id, String name, Double amount, String unit){
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        this.id = id;
    }

    public Ingredients() {

    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public Double getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setShoppingList(String shoppingList) {
        this.shoppingList = shoppingList;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
