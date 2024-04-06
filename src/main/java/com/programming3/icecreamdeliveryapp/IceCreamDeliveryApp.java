/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.programming3.icecreamdeliveryapp;
/**
 *
 * @author Shifan Samsudeen (ESOFT Student ID: E216444 / Kingston Student ID:	2377544)
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Flavor {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flavor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Topping {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Topping(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Syrup {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Syrup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

// Product class representing the ice cream combination
class IceCream {
    private List<Flavor> flavors = new ArrayList<>();
    private List<Topping> toppings = new ArrayList<>();
    private List<Syrup> syrups = new ArrayList<>();
    private int quantity;
    private String name;
    
    public void addFlavor(Flavor flavor) {
        flavors.add(flavor);
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void addSyrup(Syrup syrup) {
        syrups.add(syrup);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void display() {
        System.out.println("Ice Cream Combination: " + name);
        System.out.println("Flavors: " + flavors);
        System.out.println("Toppings: " + toppings);
        System.out.println("Syrups: " + syrups);
        System.out.println("Quantity: " + quantity);
    }

    @Override
    public String toString() {
        return "Ice Cream Combination: " + name +
                "\nFlavors: " + flavors +
                "\nToppings: " + toppings +
                "\nSyrups: " + syrups +
                "\nQuantity: " + quantity;
    }
}

// Builder interface for building complex orders
interface IceCreamBuilder {
    void buildFlavors();
    void buildToppings();
    void buildSyrups();
    void buildOrderQuantity();
    void buildName();
    IceCream getIceCream();
}

// Concrete builder implementing IceCreamBuilder
class CustomIceCreamBuilder implements IceCreamBuilder {
    private IceCream iceCream = new IceCream();

    @Override
    public void buildFlavors() {
        System.out.print("Choose Ice Cream Flavor (Vanilla/Chocolate/Strawberry): ");
        String flavorName = new Scanner(System.in).nextLine();
        if ( !( flavorName.equalsIgnoreCase("Vanilla") || 
                flavorName.equalsIgnoreCase("Chocolate") ||
                flavorName.equalsIgnoreCase("Strawberry")) ){
            System.out.println("Invalid Ice Cream Flavor. Defaulting to Vanilla"); 
            flavorName = "Vanilla";
        }
        iceCream.addFlavor(new Flavor(flavorName));
    }

    @Override
    public void buildToppings() {
        System.out.print("Choose Toppings (Sprinkles / Coconut Flakes / Cereal): ");
        String topping = new Scanner(System.in).nextLine();
        if ( !( topping.equalsIgnoreCase("Sprinkles") || 
                topping.equalsIgnoreCase("Coconut Flakes") ||
                topping.equalsIgnoreCase("Cereal")) ){
            System.out.println("Invalid Ice Cream Topping. Defaulting to Sprinkles"); 
            topping = "Sprinkles";
        }
        iceCream.addTopping(new Topping(topping)); 
    }

    @Override
    public void buildSyrups() {
        System.out.print("Choose Syrup (Chocolate / Caramel): ");
        String syrup = new Scanner(System.in).nextLine(); 
        if ( !( syrup.equalsIgnoreCase("Chocolate") || 
                syrup.equalsIgnoreCase("Caramel")) ){
            System.out.println("Invalid Ice Cream Topping. Defaulting to Chocolate"); 
            syrup = "Chocolate";
        }
        iceCream.addSyrup(new Syrup(syrup));
    }
    
    @Override
    public void buildOrderQuantity() {
        System.out.print("Enter Quantity: ");
        int quantity = new Scanner(System.in).nextInt();
        iceCream.setQuantity(quantity);
    }

    @Override
    public void buildName() {
        System.out.print("Enter A Name For Your IceCream Customization: ");
        String customName = new Scanner(System.in).nextLine();
        iceCream.setName(customName);
    }

    @Override
    public IceCream getIceCream() {
        return iceCream;
    }
}

class IceCreamDirector {
    public IceCream construct(IceCreamBuilder builder) {
        builder.buildFlavors();
        builder.buildToppings();
        builder.buildSyrups();
        builder.buildOrderQuantity();
        builder.buildName();
        return builder.getIceCream();
    }
}

// Observer interface for real-time order updates
interface OrderObserver {
    void update(String status);
}

// Concrete Observer implementing OrderObserver
class RealTimeOrderObserver implements OrderObserver {
    @Override
    public void update(String status) {
        System.out.println("Order Status Update: " + status);
    }
}

// Subject interface for order status changes
interface OrderSubject {
    void addObserver(OrderObserver observer);
    void removeObserver(OrderObserver observer);
    void notifyObservers(String status);
}

// Concrete Subject implementing OrderSubject
class IceCreamOrder implements OrderSubject {
    private List<OrderObserver> observers = new ArrayList<>();
    private String status;

    public void setStatus(String status) {
        this.status = status;
        notifyObservers(status);
    }

    @Override
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String status) {
        for (OrderObserver observer : observers) {
            observer.update(status);
        }
    }
}

// Strategy interface for payment processing
interface PaymentStrategy {
    void processPayment(double amount);
}

// Concrete Strategies implementing PaymentStrategy
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) { 
        System.out.println("Processing Credit Card Payment: $" + amount);
    }
}

class DigitalWalletPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) { 
        System.out.println("Processing Digital Wallet Payment: $" + amount);
    }
}

// Context class using the Strategy pattern
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount) {
        paymentStrategy.processPayment(amount);
    }
}

// Chain of Responsibility pattern: Handler interface for processing order customization requests
interface CustomizationHandler {
    void handleRequest(IceCream iceCream);
}

// Concrete Handlers implementing CustomizationHandler
class FlavorHandler implements CustomizationHandler {
    @Override
    public void handleRequest(IceCream iceCream) {
        System.out.println("Customizing flavor...");
        System.out.print("Want to mix a Flavor (YES/NO): ");
        String isRequest = new Scanner(System.in).nextLine();
        if(isRequest.equalsIgnoreCase("YES")){
            System.out.print("Choose New Ice Cream Flavor (Vanilla/Chocolate/Strawberry): ");
            String newFlavorName = new Scanner(System.in).nextLine();

            if (!(newFlavorName.equalsIgnoreCase("Vanilla") ||
                    newFlavorName.equalsIgnoreCase("Chocolate") ||
                    newFlavorName.equalsIgnoreCase("Strawberry"))) {
                System.out.println("Invalid Ice Cream Flavor. Defaulting!");
            }else{
                iceCream.addFlavor(new Flavor(newFlavorName));    
            }    
        }else{
            System.out.println("Flavour Status Complete!");
        } 
    }
}

class ToppingHandler implements CustomizationHandler {
    @Override
    public void handleRequest(IceCream iceCream) {
        System.out.println("Customizing topping...");
        System.out.print("Want to mix a Topping (YES/NO): ");
        String isRequest = new Scanner(System.in).nextLine();
        if(isRequest.equalsIgnoreCase("YES")){
            System.out.print("Choose Mixing Topping (Sprinkles / Coconut Flakes / Cereal): ");
            String newToppingName = new Scanner(System.in).nextLine();
            if (!(newToppingName.equalsIgnoreCase("Sprinkles") ||
                    newToppingName.equalsIgnoreCase("Coconut Flakes") ||
                    newToppingName.equalsIgnoreCase("Cereal"))) {
                System.out.println("Invalid Ice Cream Topping. Defaulting!");
            }else{
                iceCream.addTopping(new Topping(newToppingName));    
            }    
        }else{
            System.out.println("Topping Status Complete!");
        } 
    }
}

class SyrupHandler implements CustomizationHandler {
    @Override
    public void handleRequest(IceCream iceCream) {
        System.out.println("Customizing syrup...");
        System.out.print("Want to mix a Syrup (YES/NO): ");
        String isRequest = new Scanner(System.in).nextLine();
        if(isRequest.equalsIgnoreCase("YES")){
            System.out.print("Choose Syrup (Chocolate / Caramel): ");
            String newSyrupName = new Scanner(System.in).nextLine();
            if (!(newSyrupName.equalsIgnoreCase("Chocolate") ||
                    newSyrupName.equalsIgnoreCase("Caramel") )) {
                System.out.println("Invalid Ice Cream Syrup. Defaulting!");
            }else{
                iceCream.addSyrup(new Syrup(newSyrupName));    
            }    
        }else{
            System.out.println("Syrup Status Complete!");
        } 
    }
}

// State interface representing the different states of an order
interface OrderState {
    void processOrder(IceCreamOrder iceCreamOrder);
}

// Concrete States implementing OrderState
class PlacedOrderState implements OrderState {
    @Override
    public void processOrder(IceCreamOrder iceCreamOrder) {
        iceCreamOrder.setStatus("Order Placed");
    }
}

class PreparationOrderState implements OrderState {
    @Override
    public void processOrder(IceCreamOrder iceCreamOrder) {
        iceCreamOrder.setStatus("In Preparation");
    }
}

class DeliveryOrderState implements OrderState {
    @Override
    public void processOrder(IceCreamOrder iceCreamOrder) {
        iceCreamOrder.setStatus("Out for Delivery");
    }
}

// Context class using the State pattern
class OrderContext {
    private OrderState orderState;

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void processOrder(IceCreamOrder iceCreamOrder) {
        orderState.processOrder(iceCreamOrder);
    }
}

// Command interface for representing user actions
interface IceCreamCommand {
    void execute();
}

// Concrete Commands implementing IceCreamCommand
class PlaceOrderCommand implements IceCreamCommand {
    private IceCreamOrder iceCreamOrder;

    public PlaceOrderCommand(IceCreamOrder iceCreamOrder) {
        this.iceCreamOrder = iceCreamOrder;
    }

    @Override
    public void execute() {
        iceCreamOrder.setStatus("Order Placed");
    }
}

// Concrete Commands implementing IceCreamCommand
class ProvideFeedbackCommand implements IceCreamCommand {
    private IceCreamOrder iceCreamOrder;
    private String feedback;  // New variable for feedback

    public ProvideFeedbackCommand(IceCreamOrder iceCreamOrder, String feedback) {
        this.iceCreamOrder = iceCreamOrder;
        this.feedback = feedback;
    }

    @Override
    public void execute() {
        iceCreamOrder.setStatus("Feedback Provided: " + feedback);
    }
}

// Invoker class using the Command pattern
class IceCreamInvoker {
    private IceCreamCommand iceCreamCommand;

    public void setIceCreamCommand(IceCreamCommand iceCreamCommand) {
        this.iceCreamCommand = iceCreamCommand;
    }

    public void executeCommand() {
        iceCreamCommand.execute();
    }
}

// Decorator interface for enhancing orders with additional features
interface IceCreamDecorator {
    void addAdditionalFeatures(IceCream iceCream);
}

// Concrete Decorators implementing IceCreamDecorator
class GiftWrappingDecorator implements IceCreamDecorator {
    @Override
    public void addAdditionalFeatures(IceCream iceCream) {
        iceCream.setName(iceCream.getName() + " with Gift Wrapping");
    }
}

class SpecialPackagingDecorator implements IceCreamDecorator {
    @Override
    public void addAdditionalFeatures(IceCream iceCream) {
        iceCream.setName(iceCream.getName() + " with Special Packaging");
    }
}

class Favorites {
    public static void addToFavorites() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Add to favorites (YES/NO): ");
        String addToFavorites = scanner.nextLine();

        if (addToFavorites.equalsIgnoreCase("YES")) {
            System.out.println("Added to favorites!");
        }
    }
}

class Loyalty {
    public static double checkLoyaltyPoints(double paymentAmount) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you have IceCreamShop loyalty card (YES/NO): ");
        String loyaltyCheck = scanner.nextLine();
        double loyaltyPoints = 0;

        if (loyaltyCheck.equalsIgnoreCase("YES")) {
            // Ask for loyalty card number
            System.out.print("Enter Loyalty Card Number: ");
            String loyaltyCardNumber = scanner.nextLine();
            // Calculate 2% of the payment amount and add as loyalty points
            loyaltyPoints = 0.02 * paymentAmount;
            System.out.println("Loyalty points earned: " + loyaltyPoints);
        }
        return loyaltyPoints;
    }
}

class SeasonalOffers {
    public static void displaySeasonalOffer() {
        System.out.println("Next seasonal offer: Aluth Avurudu - 10% off for all flavors");
    }
}

class Rating {
    public static void getCustomerRating() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your rating 1-5 (1 is lower and 5 is higher): ");
        String rating = scanner.nextLine();
        System.out.println("Thank you for rating us: " + rating);
    }
}

class DeliveryOption {
    public static String chooseDeliveryOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select your option (Pickup / Delivery): ");
        return scanner.nextLine();
    }

    public static String getDeliveryLocation(String deliveryOption) {
        Scanner scanner = new Scanner(System.in);
        String deliveryLocation = "";
        if (deliveryOption.equalsIgnoreCase("Delivery")) {
            // Ask for delivery location
            System.out.print("Select your delivery location (Kadawatha / Kiribathgoda / Kelaniya): ");
            deliveryLocation = scanner.nextLine();
            // Determine and display delivery time based on location
            int deliveryTime = calculateDeliveryTime(deliveryLocation);
            System.out.println("Within " + deliveryTime + " minutes delivery possible to " + deliveryLocation);
        }
        return deliveryLocation;
    }

    private static int calculateDeliveryTime(String deliveryLocation) {
        int deliveryTime;
        switch (deliveryLocation.toLowerCase()) {
            case "kadawatha":
                deliveryTime = 10;
                break;
            case "kiribathgoda":
                deliveryTime = 20;
                break;
            case "kelaniya":
                deliveryTime = 30;
                break;
            default:
                System.out.println("Sorry, we cannot deliver to your requested location. Changing order to pickup mode.");
                deliveryTime = 0; // Set to 0 as the order will be for pickup
                break;
        }
        return deliveryTime;
    }
}

public class IceCreamDeliveryApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Builder pattern: Build custom ice cream
        IceCreamBuilder customIceCreamBuilder = new CustomIceCreamBuilder();
        IceCreamDirector director = new IceCreamDirector();
        IceCream customIceCream = director.construct(customIceCreamBuilder);
        
        // Observer pattern: Observe order status
        OrderObserver realTimeObserver = new RealTimeOrderObserver();
        IceCreamOrder iceCreamOrder = new IceCreamOrder();
        iceCreamOrder.addObserver(realTimeObserver);
        
        // Strategy pattern: Choose payment strategy
        System.out.print("Choose Payment Method (Credit Card/Digital Wallet): ");
        String paymentMethod = scanner.nextLine();
        PaymentStrategy paymentStrategy;
        if (paymentMethod.equalsIgnoreCase("Credit Card")) {
            paymentStrategy = new CreditCardPayment();
        } else if (paymentMethod.equalsIgnoreCase("Digital Wallet")) {
            paymentStrategy = new DigitalWalletPayment();
        } else {
            System.out.println("Invalid payment method. Defaulting to Credit Card Payment.");
            paymentStrategy = new CreditCardPayment();
        }
        PaymentContext paymentContext = new PaymentContext(paymentStrategy);
        System.out.print("Enter Payment Amount: ");
        double paymentAmount = scanner.nextDouble();
        paymentContext.processPayment(paymentAmount);
        
        // Chain of Responsibility pattern: Customize ice cream
        CustomizationHandler flavorHandler = new FlavorHandler();
        CustomizationHandler toppingHandler = new ToppingHandler();
        CustomizationHandler syrupHandler = new SyrupHandler();
        flavorHandler.handleRequest(customIceCream);
        toppingHandler.handleRequest(customIceCream);
        syrupHandler.handleRequest(customIceCream);
        
        // State pattern: Process order through different states
        OrderState placedOrderState = new PlacedOrderState();
        OrderState preparationOrderState = new PreparationOrderState();
        OrderState deliveryOrderState = new DeliveryOrderState();
        OrderContext orderContext = new OrderContext();
        orderContext.setOrderState(placedOrderState);
        orderContext.processOrder(iceCreamOrder);
        
        // Command pattern: Execute commands
        IceCreamCommand placeOrderCommand = new PlaceOrderCommand(iceCreamOrder);
        System.out.print("Provide Feedback: ");
        String userFeedback = scanner.nextLine();
        IceCreamCommand provideFeedbackCommand = new ProvideFeedbackCommand(iceCreamOrder, userFeedback);
        IceCreamInvoker invoker = new IceCreamInvoker();
        invoker.setIceCreamCommand(placeOrderCommand);
        invoker.executeCommand();
        invoker.setIceCreamCommand(provideFeedbackCommand);
        invoker.executeCommand();
        
        // Decorator pattern: Add additional features
        IceCreamDecorator giftWrappingDecorator = new GiftWrappingDecorator();
        IceCreamDecorator specialPackagingDecorator = new SpecialPackagingDecorator();
        System.out.print("Do you want to add Gift Wrapping? (YES/NO): ");
        String addGiftWrapping = scanner.nextLine();
        if (addGiftWrapping.equalsIgnoreCase("YES")) {
            giftWrappingDecorator.addAdditionalFeatures(customIceCream);
        }
        System.out.print("Do you want to add Special Packaging? (YES/NO): ");
        String addSpecialPackaging = scanner.nextLine();
        if (addSpecialPackaging.equalsIgnoreCase("YES")) {
            specialPackagingDecorator.addAdditionalFeatures(customIceCream);
        }
        
        // Customer Favorites
        Favorites.addToFavorites();
    
        // Customer Loyalty
        double loyaltyPoints = Loyalty.checkLoyaltyPoints(paymentAmount);

        // Seasonal Offers
        SeasonalOffers.displaySeasonalOffer();
        
         // Rating
        Rating.getCustomerRating();
        
        // Delivery Option
        String deliveryOption = DeliveryOption.chooseDeliveryOption();
        String deliveryLocation = DeliveryOption.getDeliveryLocation(deliveryOption);

        
        // Display Ice Cream Details
        System.out.println("");
        System.out.println("");
        System.out.println("------------------ Your ice-cream details ------------------ ");
        System.out.println(customIceCream);
        System.out.println("");
        System.out.println("Delivery Location: " + deliveryLocation);
        System.out.println("PaymentMethod: " + paymentMethod);
        System.out.println("Loyalty Points Earned: " + loyaltyPoints);
        System.out.println("Thank You for using our online ordering services");
        System.out.println("");
    }
}
