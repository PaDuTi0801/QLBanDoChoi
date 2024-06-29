/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpt.Utils;

import java.security.MessageDigest;

/**
 *
 * @author hquan
 */
public class PasswordHash {

    public static String passwordHasd(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(pass.getBytes());
            byte[] rpt = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : rpt) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        System.out.println(  passwordHasd("1234"));
        
    }
}
