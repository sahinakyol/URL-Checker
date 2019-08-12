package org.quark.service;


import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class RequestService {

    public HashMap<String,String> urlChecker(List urlList) {
        HashMap<String,String> urlResponseRequestResult = new HashMap<>();
        urlList.forEach(URLItem -> {
            String status = "";
            URL url = null;
            try {
                url = new URL("https://"+URLItem.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection con = null;
            try {
                System.out.println("Requested to :"+ URLItem.toString());
                con = (HttpURLConnection) Objects.requireNonNull(url).openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Objects.requireNonNull(con).setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            try {
                status = String.valueOf(con.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlResponseRequestResult.put((String) URLItem,status);
        });
        return urlResponseRequestResult;
    }

}
