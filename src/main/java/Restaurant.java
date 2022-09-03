import java.time.Clock;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;

    public LocalTimeUtils localTimeUtils;
    private List<Item> menu = new ArrayList<Item>();

    private Item item;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.localTimeUtils = new LocalTimeUtils();
    }

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime, LocalTimeUtils localTimeUtils) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.localTimeUtils = localTimeUtils;
    }

    public boolean isRestaurantOpen() {
        LocalTime currentTime = getCurrentTime();
        if( openingTime.isBefore(currentTime)  && closingTime.isAfter(currentTime)){
            return true;
        }

        return false;
    }

    public LocalTime getCurrentTime(){ return  localTimeUtils.getCurrentTime(); }

    public List<Item> getMenu() {
        return menu;

    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public int orderTotalCost(List<String> items) {

        int totalCost=0;
        for(int i=0;i< items.size();i++){

            for(Item menuItem: menu) {
                if(menuItem.getName().equals(items.get(i))) {
                    totalCost+=menuItem.getPrice();
                }
            }

        }
        return totalCost;
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }


}
