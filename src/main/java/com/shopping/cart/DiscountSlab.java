package com.shopping.cart;

import java.util.Objects;

/**
 *
 * @author Alpesh
 */
public class DiscountSlab implements Comparable<DiscountSlab> {

    // Final help for adding new discount slab
    public static final String HELP = "Please pass the discount slab as : lowerLimit-upperLimit=discount(%). i.e 5000-10000=10";

    private Long lower;
    private Long upper;
    private Byte discount;

    // Add direct value supports 
    /**
     *
     * @param lower
     * @param upper
     * @param discount
     */
    public DiscountSlab(Long lower, Long upper, Byte discount) {
        this.lower = lower;
        this.upper = upper;
        this.discount = discount;
        // Validate the slab
        this.validate();
    }

    // Added String based constructor
    /**
     *
     * @param discountSlabString String in format of
     * lowerLimit-upperLimit=discount i.e 5000-10000=10
     */
    public DiscountSlab(String discountSlabString) {
        if (discountSlabString == null) {
            throw new IllegalArgumentException(HELP);
        }
        // Remove all spaces 
        discountSlabString = discountSlabString.replace(" ", "");

        if (discountSlabString.matches("[0-9]+-([0-9]+|[~]+)=[0-9]+")) {
            String[] limitDiscount = discountSlabString.split("[=]");

            // Split the limit
            String[] limits = limitDiscount[0].split("[-]");
            // Read the limits
            this.lower = Long.parseLong(limits[0]);
            if ("~".equals(limits[1])) {
                this.upper = Long.MAX_VALUE;
            } else {
                this.upper = Long.parseLong(limits[1]);
            }

            // Read the Discount parts
            this.discount = Byte.parseByte(limitDiscount[1]);
        } else {
            throw new IllegalArgumentException(HELP);
        }

        // Validate the slab
        this.validate();
    }

    private void validate() {
        if (lower < 0 || upper < 0 || discount < 0) {
            throw new IllegalArgumentException("Please pass discount slab values as positive numbers");
        }

        if (this.lower >= this.upper) {
            throw new IllegalArgumentException("Please pass the lower limit less than upper limit");
        }

        if (this.discount > 100) {
            throw new IllegalArgumentException("Discount must be in 0-100");
        }
    }

    public Long getLower() {
        return lower;
    }

    public void setLower(Long lower) {
        this.lower = lower;
    }

    public Long getUpper() {
        return upper;
    }

    public void setUpper(Long upper) {
        this.upper = upper;
    }

    public Byte getDiscount() {
        return discount;
    }

    public void setDiscount(Byte discount) {
        this.discount = discount;
    }

    /**
     *
     * @param billAmount
     * @return
     */
    public boolean isEffective(Long billAmount) {
        return (this.lower < billAmount && billAmount <= this.upper);
    }

    /**
     *
     * @param discountSlab
     * @return
     */
    public boolean isConflict(DiscountSlab discountSlab) {

        // Exact match 
        if (this.lower.equals(discountSlab.lower) || this.upper.equals(discountSlab.upper)) {
            return true;
        }
        // Overlapping 
        if (this.lower < discountSlab.lower && discountSlab.lower < this.upper
                || this.lower < discountSlab.upper && discountSlab.upper < this.upper) {
            return true;
        }
        return discountSlab.lower < this.lower && this.lower < discountSlab.upper
                || discountSlab.lower < this.upper && this.upper < discountSlab.upper;
    }

    @Override
    public int compareTo(DiscountSlab discountSlab) {
        return this.lower.compareTo(discountSlab.getLower());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.lower);
        hash = 97 * hash + Objects.hashCode(this.upper);
        hash = 97 * hash + Objects.hashCode(this.discount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiscountSlab other = (DiscountSlab) obj;
        if (!Objects.equals(this.lower, other.lower)) {
            return false;
        }
        if (!Objects.equals(this.upper, other.upper)) {
            return false;
        }
        return Objects.equals(this.discount, other.discount);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("DiscountSlab [%-30s] [%10s]",
                lower + ShoppingCart.CURRENCY + " - " + upper + ShoppingCart.CURRENCY,
                discount + "%");

    }
}
