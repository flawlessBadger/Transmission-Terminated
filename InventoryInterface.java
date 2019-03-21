public interface InventoryInterface {
    boolean moveItem(InventoryInterface destination,String name);
    //void addItem(String name, Item item);
    void addItem(String name, String description, Boolean isMovable);
    void useItem(String name);
    int getItemUses(String name);
}

