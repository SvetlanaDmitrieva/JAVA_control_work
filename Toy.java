public class Toy {
    private String ID;
    private String name;
    private Integer quantity;
    private Integer numberWins;
    private Double frequency;
                
    public Toy(String ID, String name, Integer quantity,Integer numberWins, Double frequency ){
        this.ID = ID;
        this.name = name;
        this.quantity  = quantity ;
        this.numberWins = numberWins;
        this.frequency = frequency;
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public Integer getquantity() {
        return quantity ;
    }
    public void setquantity(Integer quantity) {
        this.quantity  = quantity ;
    }
    public Integer getnumberWins() {
        return numberWins ;
    }
    public void setnumberWins(Integer numberWins) {
        this.numberWins = numberWins;
    }
    public Double getfrequency() {
        return frequency;
    }
    public void setfrequency (Double frequency) {
        this.frequency = frequency;
    }
}