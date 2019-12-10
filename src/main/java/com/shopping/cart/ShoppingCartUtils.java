/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shopping.cart;

/**
 *
 * @author Alpesh
 */
public class ShoppingCartUtils {

    private static final int LINE_LIMIT = 100; // 100 character for one line 

    // 
    private ShoppingCartUtils() {
        // Dont want to create the object of this class
        // Its just util class
    }

    public void printLine(String lineCharacter) {
        System.out.println(String.format("%" + LINE_LIMIT + "s", lineCharacter).replace(" ", lineCharacter));
    }
}
