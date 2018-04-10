/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.security.MessageDigest;

/**
 *
 * @author Administrador
 */
 public class Criptografia{

     public static String geraCriptografia(String senhaOriginal) {
          StringBuilder hexString = null;
         try {   
            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senhaOriginal.getBytes("UTF-8"));
            hexString = new StringBuilder();
            for (byte b : messageDigest) {
        hexString.append(String.format("%02X", 0xFF & b));
                 }
           }
         catch(Exception e){
             
         }
         return hexString.toString();
    }
 }