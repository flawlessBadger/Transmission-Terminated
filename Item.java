 

public class Item {
    private String description;


    private int timesUsed = 0;
    private Boolean isMovabel;

    public Item(String description, Boolean isMovable){
        this.description = description;
        this.isMovabel = isMovable;
    }

    public String getDescription() {
        return description;
    }
    public Boolean getMovabel() {
        return isMovabel;
    }
    public void use(){
        timesUsed++;
    }
    public int getTimesUsed(){
        return timesUsed;
    }
}
