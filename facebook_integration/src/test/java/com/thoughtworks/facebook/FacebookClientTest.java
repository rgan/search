package com.thoughtworks.facebook;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FacebookClientTest {

    @Test
    public void shouldGetLoginUrl() throws Exception {
       assertEquals("https://graph.facebook.com/oauth/authorize?client_id=160270380651471&redirect_uri=http://facebooksearchtest.appspot.com/index.jsp", new FacebookClient().loginUrl());
    }

    @Test
    public void shouldParseAccessToken() throws Exception {
        String token = "access_token=160270380651471|2.bXlcuGUDEkyIIqjRLR5lSg__.3600.1285293600-100001569193092|Mxi-FZeq8wFcYrzBk-j1JGCBwcM&expires=3829";
        assertEquals("160270380651471|2.bXlcuGUDEkyIIqjRLR5lSg__.3600.1285293600-100001569193092|Mxi-FZeq8wFcYrzBk-j1JGCBwcM", new FacebookClient().parseAccessToken(token));
    }
}
