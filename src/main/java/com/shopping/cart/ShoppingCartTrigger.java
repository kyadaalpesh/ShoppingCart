package com.shopping.cart;

/**
 *
 * @author Alpesh
 */
public class ShoppingCartTrigger {

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

        // If possible add the customer bill as well ( Detail Format: 
        shoppingCart.addCustomerBill(new CustomterBill("Alpesh Regular 5000"));
        shoppingCart.addCustomerBill(new CustomterBill("Shailesh Regular 10000"));
        shoppingCart.addCustomerBill(new CustomterBill("Darious Regular 15000"));

        // Do shoping
        shoppingCart.shoping();
    }
}
