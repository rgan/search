package com.thoughtworks.facebook;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class PersonFactory {
    private static final int MAX_DEPTH = 4;
    private static final int MAX_FRIENDS = 5;

    private FBClient FBClient;

    public PersonFactory(FBClient FBClient) {
        this.FBClient = FBClient;
    }

    public Person me() throws Exception {
        return from("me", FBClient.getPersonData("me", true));
    }

    public Person from(String id, String personJson, int depth) throws Exception {
        final JSONObject personJsonObject = JSONObject.fromObject(personJson);
        Set<Person> friends = new HashSet<Person>();
        if (depth <= MAX_DEPTH && personJsonObject.get("friends") != null) {
            final JSONObject friendsJsonObject = JSONObject.fromObject(personJsonObject.get("friends"));
            List<MorphDynaBean> items = JSONArray.toList(friendsJsonObject.getJSONArray("data"));
            for (MorphDynaBean item : items) {
                final String friendId = (String) item.get("id");
                if (friendId != null) {
                    String data = getDataWithFriends(friendId);
                    if (data == null) {
                       data = FBClient.getPersonData(friendId, false);
                    }
                    Person friend = from(friendId, data, depth + 1);
                    friends.add(friend);
                }
                if (friends.size() > MAX_FRIENDS) {
                    break;
                }
            }
        }
        return new Person(id,
                (String) personJsonObject.get("name"),
                parseLikes((JSONObject) personJsonObject.get("likes")),
                friends);
    }

    private String getDataWithFriends(String friendId) {
        try {
            String data= FBClient.getPersonData(friendId, true);
            JSONObject jsonObject = JSONObject.fromObject(data);
            if (jsonObject.get("error") != null) {
                return null;
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Like> parseLikes(JSONObject jsonObject) {
        List<Like> result = new ArrayList<Like>();
        if (jsonObject != null && jsonObject.get("data") != null) {
            List<MorphDynaBean> items = JSONArray.toList(jsonObject.getJSONArray("data"));

            for (MorphDynaBean item : items) {
                result.add(new Like((String) item.get("name"), (String) item.get("category")));
            }
        }
        return result;
    }

    public Person from(String id, String json) throws Exception {
        return from(id, json, 1);
    }
}
