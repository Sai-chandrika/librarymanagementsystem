package com.example.librarymanagementsystem.utility;

import com.example.librarymanagementsystem.customexception.DataMisMatchException;

/**
 * @Author ➤➤➤ PavaniBv
 * @Date ➤➤➤ 09/01/24
 * @Time ➤➤➤ 5:02 pm
 * @Project ➤➤➤ librarymanagementsystem
 */
public class GenericAppValidator {

    private GenericAppValidator() {

    }

    public static Boolean isNotNull(Object object) {
        if (object != null) {
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

    public static Boolean isNull(Object object) {
        if (object == null) {
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

    public static Boolean isValidContactNumber(String contact){

        for (char c:contact.toCharArray()){
            if (Character.isDigit(c) || c == '+' || c == '-') {
            }
            else return false;
        }
        return contact.length()>=10;
    }

    public static Boolean isValidEmail(String email){
        if (email.matches(".*[A-Z].*")){
            throw new DataMisMatchException("Email should not contain uppercase characters");
        }
        if (email.contains("@") && (email.endsWith(".com") || email.endsWith(".net"))){
            return true;
        }
        return false;
    }

    public static Boolean isValidPassword(String password){

        if (!(password.matches(".*\\d.*")&&
                password.matches(".*[A-z].*") &&
                password.matches(".*[a-z].*") &&
                password.matches(".*[^a-zA-z0-9].*")&&
                password.length()>=8))
            throw new DataMisMatchException("Password must contain at least one numeric ,one special, one uppercase, one lowercase and one digit and length should greater than 8 ");
        return true;
    }
}
