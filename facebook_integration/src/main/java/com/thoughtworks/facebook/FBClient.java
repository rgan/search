package com.thoughtworks.facebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class FBClient {
    private static final String APP_ID = "";
    private static final String REDIRECT_URL = "http://facebooksearchtest.appspot.com/index.jsp";
    private static final String BASE_URL = "https://graph.facebook.com";
    private String accessToken;
    private static final String APP_SECRET = "";


    public String getPersonData(String id, boolean friends) throws Exception {
        return doGet(BASE_URL + "/" + id + "?fields=id,name,likes" + (friends ? ",friends" : "") + "&access_token=" + accessToken);
    }

    public boolean setupAccessToken(String code) throws Exception {
        StringBuilder builder = new StringBuilder(BASE_URL + "/oauth/access_token?");
        builder.append("client_id=").append(APP_ID);
        builder.append("&redirect_uri=");
        builder.append(REDIRECT_URL);
        builder.append("&client_secret=");
        builder.append(APP_SECRET);
        builder.append("&code=");
        builder.append(code);
        accessToken = parseAccessToken(doGet(builder.toString()));
        if (accessToken != null) {
            return true;
        }
        return false;
    }

    private String doGet(String urlToOpen) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlToOpen);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public String loginUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL + "/oauth/authorize?");
        builder.append("client_id=").append(APP_ID);
        builder.append("&redirect_uri=");
        builder.append(REDIRECT_URL);
        return builder.toString();
    }

    public String parseAccessToken(String token) {
        if (token.indexOf("access_token=") > -1 && token.indexOf("&expires") > 0) {
            return token.substring(13, token.indexOf("&expires"));
        }
        return null;
    }
}
