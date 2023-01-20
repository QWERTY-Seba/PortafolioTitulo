/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Seba
 */
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
public class Credentials {
    static String clientId = "Ad_GvF4ieMHIVweZcTgzhvX57GII5tMQcH64WB-_jCaTCWOCULKu1ueXKXvKY14PYYFnRCbiOl6bFtVU";
    static String secret = "EJWPnoMoTTlQ8hqycALHJINW3dgJKgt3RwxkbcE8i8oodcSFOhzRV_Y-Tiy5r5MgIFddymPB7cK4bVy8";
    
    // Creating a sandbox environment
    private static PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, secret);
    
    // Creating a client for the environment
    static PayPalHttpClient client = new PayPalHttpClient(environment);
}
