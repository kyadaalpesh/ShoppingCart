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

    /**
     *
     * @param lineCharacter
     */
    public static void printLine(String lineCharacter) {
        System.out.println(String.format("%" + LINE_LIMIT + "s", lineCharacter).replace(" ", lineCharacter));
    }

    public static void printTextInCenter(String message) {
        printTextInCenter("|", message, "|");
    }

    /**
     *
     * @param prefix
     * @param message
     * @param suffix
     */
    public static void printTextInCenter(String prefix, String message, String suffix) {
        int remainingLenght = LINE_LIMIT - message.length();
        if (remainingLenght > 1) {
            System.out.println(
                    String.format("%-" + (int) (remainingLenght / 2) + "s%" + message.length() + "s %" + (int) (remainingLenght / 2) + "s", prefix, message, suffix));
        } else {
            System.out.println(message);
        }
    }

    /**
     *
     * @param percentage
     * @return
     */
    public static int getWidth(int percentage) {
        return (int) (LINE_LIMIT * percentage / 100);
    }
}
