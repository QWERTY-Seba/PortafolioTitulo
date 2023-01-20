/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Seba
 */
public class ABS {
    
    public static Reader conn(String url,CloseableHttpClient c) throws IOException{
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = c.execute(request);
        InputStream source = response.getEntity().getContent(); 
        Reader reader = new InputStreamReader(source);
        
        return reader;
    }
    
    public static <T> List<T> get(String url, Class<T[]> clase){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        T[] arr = null ; 
        try{
            arr = new Gson().fromJson(conn(url, httpClient), clase);
        }catch(Exception et){
            et.printStackTrace();
        }finally{
            try{
                httpClient.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return Arrays.asList(arr);
    }
    
    public static File get_file(String url, String nombre) throws IOException{
        
        File tempFile = File.createTempFile("Portafolio-", "-Test.pdf");
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        
        if (entity != null) {
            FileOutputStream fos = new FileOutputStream(tempFile);
            entity.writeTo(fos);
        }
        return tempFile;
    }
    
    
    
    public static void post(String url, Object o){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPost request = new HttpPost(url); 
            StringEntity params = new StringEntity(new Gson().toJson(o));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            
        }catch(Exception et){
            et.printStackTrace();
        }finally{
            try{
                httpClient.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public static void put(String url, Object o){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try{
            HttpPut request = new HttpPut(url); 
            StringEntity params = new StringEntity(new Gson().toJson(o));
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
            
        }catch(Exception et){
            et.printStackTrace();
        }finally{
            try{
                httpClient.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
}
