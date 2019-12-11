package com.shopping.cart;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Alpesh
 */
public class ShoppingCart {

    public static String Currency = "$";

    private final Set<DiscountSlab> availableDiscountSlabs = new TreeSet<>();
    // For reading user inputs
    private final Scanner scanner = new Scanner(System.in);

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        ShoppingCart shoppingCart = new ShoppingCart();

        // Adding default discount slabs : lowerLimit-upperLimit=discount i.e 5000-10000=10
        shoppingCart.addDiscountSlab(new DiscountSlab("0-5000=0"));
        shoppingCart.addDiscountSlab(new DiscountSlab("5000-10000=10"));
        shoppingCart.addDiscountSlab(new DiscountSlab("10000-~=20"));
        // Do shoping
        shoppingCart.shoping();
    }

    /**
     *
     * @param discountSlab
     * @return
     */
    synchronized public boolean addDiscountSlab(DiscountSlab discountSlab) {

        // check conflict with availabe discount
        for (DiscountSlab availableDiscountSlab : availableDiscountSlabs) {
            if (availableDiscountSlab.isConflict(discountSlab)) {
                throw new IllegalArgumentException("Provided slab is conflict with : " + availableDiscountSlab);
            }
        }
        // If no conflict is there then add into slab
        return availableDiscountSlabs.add(discountSlab);
    }

    public void shoping() {
        ShoppingCartUtils.printLine("*");
        ShoppingCartUtils.printTextInCenter("Hello " + System.getProperty("user.name") + "!!, Welcome to Shopping cart.");
        ShoppingCartUtils.printLine("*");
        // Print the avalable discounts 
        printDiscountSlab();

        // Enter Billing Detail
        billingDetails();
    }

    /**
     *
     */
    private void printDiscountSlab() {
        ShoppingCartUtils.printTextInCenter("Following are avalable discount slabs");
        ShoppingCartUtils.printLine("*");
        int counter = 1;
        Map<Integer, DiscountSlab> mappingOfDiscountSlabs = new HashMap<>();
        String userMessage = "";
        if (availableDiscountSlabs.isEmpty()) {
            ShoppingCartUtils.printTextInCenter("There is no discount slabs available.");
        } else {
            for (DiscountSlab availableDiscountSlab : availableDiscountSlabs) {
                mappingOfDiscountSlabs.put(counter, availableDiscountSlab);
                ShoppingCartUtils.printTextInCenter(counter++ + ") " + availableDiscountSlab);
            }
            userMessage += "Enter 0-" + (counter - 1) + " to remove particular slab, ";
        }
        userMessage += "Enter " + counter + " For add new discount slab or press enter to countine :";
        ShoppingCartUtils.printLine("*");
        System.out.print(userMessage);
        int userInput = scanner.nextInt();
        if (userInput == counter) {
            // Add the slab
            addDiscountSlabFromUser();
        } else if (userInput < counter) {
            // Remove particular slab
            DiscountSlab discountSlab = mappingOfDiscountSlabs.get(userInput);
            availableDiscountSlabs.remove(discountSlab);
            // Ask again to print the slabs
            printDiscountSlab();
        } else {
            // Countine process....
        }

    }

    /**
     *
     */
    private void addDiscountSlabFromUser() {
        System.out.print(DiscountSlab.HELP + ":");
        String userInput = scanner.next();
        try {
            addDiscountSlab(new DiscountSlab(userInput));
            // Back to printing page
            printDiscountSlab();
        } catch (Exception exception) {
            System.out.println("ERROR: " + exception.getMessage());
            addDiscountSlabFromUser();
        }
    }

    private void billingDetails() {

    }
}
