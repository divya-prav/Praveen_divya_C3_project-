import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant findRestaurantByName(String restaurantName) throws  restaurantNotFoundException{
        Optional<Restaurant> checkRestaurant = restaurants.stream().filter(r -> r.getName().equals(restaurantName)).findFirst();
        if(checkRestaurant.isPresent()) {
            return checkRestaurant.get();
        }
        else throw new restaurantNotFoundException("Restaurant not found");


    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        if(restaurantToBeRemoved!=null){
            restaurants.remove(restaurantToBeRemoved);
            return restaurantToBeRemoved;
        }
        else throw new restaurantNotFoundException("Restaurant not found");

    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
