/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.controller;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;

public class LoginController {

    private boolean status = false;
    private final HashMap<String, String> loginInfo = new HashMap<>();
    private final HashMap<String, String> loginErrors = new HashMap<>();

    public LoginController() {
        loginInfo.put("admin", encryptPassword("password"));
    }

    /**
     * @param password
     * @return 
     */
    private String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return sha1;
    }

    public boolean isLoggedIn() {
        return status;
    }

    /**
     * @param hash
     * @return 
     */
    private String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    /**
     * @return 
     */
    public HashMap<String, String> getLoginErrors(){
        return loginErrors;
    }
    
    /**
     * @param username
     * @param password
     * @return 
     */
    public boolean login(String username, String password){
        if(!loginInfo.containsKey(username)){
            loginErrors.put("LC"+Thread.currentThread().getStackTrace()[2].getLineNumber(), "Username could not be found.");
            return false;
        }
        String encryptedPassword = encryptPassword(password);
        if(!encryptedPassword.equals(loginInfo.get(username))){
            loginErrors.put("LC"+Thread.currentThread().getStackTrace()[2].getLineNumber(), "Could not login.");
            return false;
        }
        status = true;
        return true;
    }
}
