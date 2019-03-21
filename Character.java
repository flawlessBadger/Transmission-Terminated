 

import java.util.ArrayList;
import java.util.HashMap;

public class Character implements InventoryInterface {

//    private ArrayList<Item> items;
    private HashMap<String, Item> items;


    public Character() {
        items = new HashMap<>();
    }

    public boolean moveItem(InventoryInterface destination, String name) {
        if (items.containsKey(name)) {
            destination.addItem(name, items.get(name).getDescription(), items.get(name).getMovabel());
            items.remove(name);
            return true;
        } else return false;

    }


    public void addItem(String name, String description, Boolean isMovable) {
        items.put(name, new Item(description, isMovable));
    }

    public boolean contains(String name) {
        return items.containsKey(name);
    }

    public String getItemDesc(String name) {
        return items.get(name).getDescription();
    }
        public Item getItem(String name) {
        return items.get(name);
    }
    public HashMap<String, Item> getItems() {
        return items;
    }
    public void useItem(String name) {
         if(items.containsKey(name))
            items.get(name).use();
    }
    public int getItemUses(String name) {
         if(items.containsKey(name))
            return items.get(name).getTimesUsed();
         return -1;
    }



}
