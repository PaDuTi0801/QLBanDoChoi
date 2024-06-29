/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Utils;

import java.text.DecimalFormat;

/**
 *
 * @author hquan
 */
public class Xpro {

    public static String formatMoney(double amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(amount);
    }

 public static double formatCurrencyString(String currencyString) {
    String cleanedString = currencyString.replace(",", "").replace(".", "").replace(" VND", "").trim();
    
    try {
        return Double.parseDouble(cleanedString);
    } catch (NumberFormatException e) {
        e.printStackTrace();
        return 0; 
    }
}


    public static String toUpperFirstChar(String ten) {
        if (ten == null || ten.trim().isEmpty()) {
            return "";
        }

        String[] arrS = ten.split(" ");
        StringBuilder name = new StringBuilder();

        for (String item : arrS) {
            if (!item.isEmpty()) {
                String so1 = item.substring(0, 1).toUpperCase();
                item = so1 + item.substring(1).toLowerCase();
                name.append(item).append(" ");
            }
        }

        return name.toString().trim();
    }
   
}
