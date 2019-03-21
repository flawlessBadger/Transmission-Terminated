 

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Room implements InventoryInterface {


    private String name;
    private String description;
    private boolean isAccesible;
    private String blockedResponse;
    //    private ArrayList<Item> items;
    private HashMap<String, Item> items;
    private HashMap<String, Room> exits;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        items = new HashMap<>();
        exits = new HashMap<>();
        isAccesible = true;
    }

    public void makeInaccessible(String blockedResponse) {
        this.blockedResponse = blockedResponse;
        isAccesible = false;
    }


    public boolean moveItem(InventoryInterface destination, String name) {
        if (items.containsKey(name) && items.get(name).getMovabel()) {
            destination.addItem(name, items.get(name).getDescription(), items.get(name).getMovabel());
            items.remove(name);
            return true;
        } else return false;
    }

    public void addItem(String name, String description, Boolean isMovable) {
        items.put(name, new Item(description, isMovable));
    }
    
    public void addExit(String direction, Room room){
        exits.put(direction,room);
    }

    public boolean contains(String name) {
        return items.containsKey(name);
    }

    public String getItemDesc(String name) {
        return items.get(name).getDescription();
    }


    public Room getExit(String direction) {

        return exits.getOrDefault(direction, null);

    }

    public HashMap<String, Room> getExits() {

        return exits;

    }

    public String getName() {
        return name;
    }

    public void setAccessibility(boolean b) {
        isAccesible = b;
    }

    public boolean getAccessibility() {
        return isAccesible;
    }

    public String getBlockedResponse() {
        return blockedResponse;
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
