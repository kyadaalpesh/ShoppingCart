package com.shopping.cart;

import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alpesh
 */
public class ShoppingCartTest {

    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart();
        // Adding default discount slabs : lowerLimit-upperLimit=discount i.e 5000-10000=10
        shoppingCart.addDiscountSlab(new DiscountSlab("0-5000=0"));
        shoppingCart.addDiscountSlab(new DiscountSlab("5000-10000=10"));
        shoppingCart.addDiscountSlab(new DiscountSlab("10000-~=20"));

        // If possible add the customer bill as well ( Detail Format: 
        shoppingCart.addCustomerBill(new CustomterBill("Alpesh Regular 5000"));
        shoppingCart.addCustomerBill(new CustomterBill("Shailesh Regular 10000"));
        shoppingCart.addCustomerBill(new CustomterBill("Darious Regular 15000"));
    }

    /**
     * Test of calculateBills method, of class ShoppingCart.
     */
    @Test
    public void testCalculateBills() {
        System.out.println("calculateBills");

        // Cross check the data
        Set<DiscountSlab> availableDiscountSlabs = shoppingCart.getAvailableDiscountSlabs();
        assertEquals(3, availableDiscountSlabs.size());

        List<CustomterBill> customterBills = shoppingCart.getCustomerBills();
        assertEquals(3, customterBills.size());

        // Adding new  discount slab 
        try {
            // This discount slab is conflict with ( 0-5000)
            shoppingCart.addDiscountSlab(new DiscountSlab("4000-6000=5"));
            fail("It must be failed becuase it got conflict with existing discount slab");
        } catch (Exception exception) {
            assertTrue(exception instanceof IllegalArgumentException);
            assertEquals("Provided slab is conflict with : DiscountSlab [0$ - 5000$                    ] [        0%]", exception.getMessage());
        }
        // Now calculate the bills
        shoppingCart.calculateBills();

        // Cross check the result
        CustomterBill customterBill = customterBills.get(0);
        assertEquals("Alpesh", customterBill.getCustomerName());
        assertEquals(5000, (long) customterBill.getPurchaseAmount());
        assertEquals(5000, (long) customterBill.getBillAmount()); // Applicable to 0% discount

        customterBill = customterBills.get(1);
        assertEquals("Shailesh", customterBill.getCustomerName());
        assertEquals(10000, (long) customterBill.getPurchaseAmount());
        assertEquals(9000, (long) customterBill.getBillAmount()); // Applicable to 10% discount

        customterBill = customterBills.get(2);
        assertEquals("Darious", customterBill.getCustomerName());
        assertEquals(15000, (long) customterBill.getPurchaseAmount());
        assertEquals(12000, (long) customterBill.getBillAmount()); // Applicable to 20% discount

    }

}
