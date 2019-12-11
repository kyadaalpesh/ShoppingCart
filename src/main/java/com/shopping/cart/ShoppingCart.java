package com.shopping.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Alpesh
 */
public class ShoppingCart {

    public static final String CURRENCY = "$";

    private final Set<DiscountSlab> availableDiscountSlabs = new TreeSet<>();
    private final List<CustomterBill> customerBills = new ArrayList<>();
    // For reading user inputs
    private final Scanner scanner = new Scanner(System.in);

    public Set<DiscountSlab> getAvailableDiscountSlabs() {
        return availableDiscountSlabs;
    }

    public List<CustomterBill> getCustomerBills() {
        return customerBills;
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
        customerPurchaseDetails();

        // Calculate Bills
        calculateBills();
        // Good bye message
        ShoppingCartUtils.printTextInCenter("Bye Bye " + System.getProperty("user.name") + "!!, See you again.");
        ShoppingCartUtils.printLine("*");
    }

    /**
     *
     */
    private void printDiscountSlab() {
        ShoppingCartUtils.printLine("*");
        ShoppingCartUtils.printTextInCenter("Following are available discount slabs ");
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
        userMessage += "Enter " + counter + " For add new discount slab or press any key to countine :";
        ShoppingCartUtils.printLine("*");
        System.out.print(userMessage);
        int userInput;
        try {
            userInput = Integer.parseInt(scanner.nextLine());
        } catch (Exception exception) {
            userInput = counter + 1; // to countinue
        }
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
        String userInput = scanner.nextLine();
        try {
            addDiscountSlab(new DiscountSlab(userInput));
            // Back to printing page
            printDiscountSlab();
        } catch (Exception exception) {
            System.out.println("ERROR: " + exception.getMessage());
            addDiscountSlabFromUser();
        }
    }

    /**
     *
     * @param customterBill
     */
    public void addCustomerBill(CustomterBill customterBill) {
        customerBills.add(customterBill);
    }

    /**
     * Entering Customer bill detail
     */
    private void customerPurchaseDetails() {
        ShoppingCartUtils.printLine("*");
        ShoppingCartUtils.printTextInCenter("Following are available customer purchase details");
        ShoppingCartUtils.printLine("*");
        int counter = 1;
        String userMessage = "";
        if (customerBills.isEmpty()) {
            ShoppingCartUtils.printTextInCenter("There is no customer purchase details available.");
        } else {
            for (CustomterBill customerBill : customerBills) {
                ShoppingCartUtils.printTextInCenter("| " + counter++ + ") ", customerBill.toString(), "|");
            }
            userMessage += "Enter 0-" + (counter - 1) + " to remove particular purchase detail, ";
        }
        userMessage += "Enter " + counter + " For add new purchase detail or press any key to countine :";
        ShoppingCartUtils.printLine("*");
        System.out.print(userMessage);

        int userInput;
        try {
            userInput = Integer.parseInt(scanner.nextLine());
        } catch (Exception exception) {
            userInput = counter + 1; // to countinue
        }
        if (userInput == counter) {
            // Add the new purchage detail
            addPurchaseDetailFromUser();
        } else if (userInput < counter) {
            // Remove particular purchase detail
            customerBills.remove(userInput - 1);
            // Ask again to print purchage detail
            customerPurchaseDetails();
        } else {
            // Countine process....
        }
    }

    /**
     *
     */
    private void addPurchaseDetailFromUser() {
        System.out.print(CustomterBill.HELP + ":");
        String userInput = scanner.nextLine();
        try {
            addCustomerBill(new CustomterBill(userInput));
            // Back to printing page
            customerPurchaseDetails();
        } catch (Exception exception) {
            System.out.println("ERROR: " + exception.getMessage());
            addPurchaseDetailFromUser();
        }
    }

    /**
     *
     */
    protected void calculateBills() {
        ShoppingCartUtils.printLine("*");
        ShoppingCartUtils.printTextInCenter("Generating the bill details");
        ShoppingCartUtils.printLine("*");
        ShoppingCartUtils.printTextInCenter("| Customer Name/Type", "Purchase Amount", "Bill Amount |");
        ShoppingCartUtils.printLine("*");
        for (CustomterBill customerBill : customerBills) {

            for (DiscountSlab availableDiscountSlab : availableDiscountSlabs) {
                if (availableDiscountSlab.isEffective(customerBill.getPurchaseAmount())) {
                    customerBill.setBillAmount(
                            customerBill.getPurchaseAmount() - (customerBill.getPurchaseAmount() * availableDiscountSlab.getDiscount() / 100));
                    break;// we got the discount
                }
            }
            // Print the billing detail
            ShoppingCartUtils.printTextInCenter(
                    "| " + customerBill.getCustomerName() + " " + customerBill.getCustomerType(),
                    customerBill.getPurchaseAmount() + CURRENCY,
                    customerBill.getBillAmount() + CURRENCY + " |");
        }
        ShoppingCartUtils.printLine("*");
    }
}
