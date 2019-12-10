package com.shoppingcart;

import com.shopping.cart.DiscountSlab;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alpesh
 */
public class DiscountSlabTest {

    private DiscountSlab instance;

    @Before
    public void setUp() {
        // Do somthing before all the unit test execute
    }

    /**
     * Test of toString method, of class DiscountSlab.
     */
    @Test
    public void testDiscountSlab() {
        System.out.println("testDiscountSlab");

        // With  constructor 
        try {
            // Try with empty string
            new DiscountSlab("");
            fail("It should be failed because there is no value in the Discount slab");
        } catch (Exception exception) {
            assertTrue(exception instanceof IllegalArgumentException);
            assertEquals(DiscountSlab.HELP, exception.getMessage());
        }

        try {
            // Try with invalid string string
            new DiscountSlab("invalid string ");
            fail("It should be failed because there is invalid Discount slab");
        } catch (Exception exception) {
            assertTrue(exception instanceof IllegalArgumentException);
            assertEquals(DiscountSlab.HELP, exception.getMessage());
        }

        try {
            // Try with invalid value in  string
            new DiscountSlab("0-5000ssss = 10");
            fail("It should be failed because there is invalid Discount slab");
        } catch (Exception exception) {
            assertTrue(exception instanceof IllegalArgumentException);
            assertEquals(DiscountSlab.HELP, exception.getMessage());
        }

        try {
            // Try with valid value in  string but wrong limits
            instance = new DiscountSlab("10000 - 5000 = 10");
            fail("It should be failed because limits are wrong");
        } catch (Exception exception) {
            assertTrue(exception instanceof IllegalArgumentException);
            assertEquals("Please pass the lower limit less than upper limit", exception.getMessage());
        }

        // Try with valid value in  string
        instance = new DiscountSlab("5000 - 10000 = 10");
        System.out.println("Printing : " + instance);
        assertEquals(5000l, (long) instance.getLower());
        assertEquals(10000l, (long) instance.getUpper());
        assertEquals(10, (byte) instance.getDiscount());

        // Check for effective ness
        assertFalse(instance.isEffective(4000l));
        assertTrue(instance.isEffective(10000l));

        // Try with valid value in  string
        DiscountSlab instance1 = new DiscountSlab("10000 - ~ = 20");
        System.out.println("Printing : " + instance1);
        assertEquals(10000l, (long) instance1.getLower());
        assertEquals(Long.MAX_VALUE, (long) instance1.getUpper());
        assertEquals(20, (byte) instance1.getDiscount());

        // Check for effective ness
        assertFalse(instance1.isEffective(4000l));
        assertFalse(instance1.isEffective(10000l));
        assertTrue(instance1.isEffective(15000l));
        // check discount conflicts with other
        assertFalse(instance.isConflict(instance1));

        instance1 = new DiscountSlab("4000 - 11000 = 10");
        System.out.println("Printing : " + instance1);
        assertEquals(4000l, (long) instance1.getLower());
        assertEquals(11000l, (long) instance1.getUpper());
        assertEquals(10, (byte) instance1.getDiscount());

        // Check for effective ness
        assertFalse(instance1.isEffective(4000l));
        assertTrue(instance1.isEffective(10000l));
        assertTrue(instance1.isEffective(11000l));
        // check discount conflicts with other
        assertTrue(instance.isConflict(instance1));

        // Created Conflict slab
        instance1 = new DiscountSlab("6000 - 8000 = 10");
        assertTrue(instance.isConflict(instance1));
        // Created independent Slab
        instance1 = new DiscountSlab("11000 - 20000 = 10");
        assertFalse(instance.isConflict(instance1));

    }

}
