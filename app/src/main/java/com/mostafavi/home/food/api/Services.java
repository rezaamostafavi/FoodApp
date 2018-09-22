package com.mostafavi.home.food.api;

import com.mostafavi.home.food.Data.Food;
import com.mostafavi.home.food.Data.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Services {

    public class Urls {
        public static final String domain = "http://mostafavi.atspace.co.uk";
        public static final String foods = domain + "/food.php";
    }

    public static class Parser {
        public static List<Food> getFoods(String json) {
            List<Food> foods = new ArrayList<>();
            try {
                JSONObject data = new JSONObject(json);
                JSONArray array = data.optJSONArray("foods");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.optJSONObject(i);
                    Food food = getFood(obj.toString());
                    if (food != null)
                        foods.add(food);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return foods;
        }
    }

    private static Food getFood(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            Food food = new Food();
            food.setName(obj.optString("name"));
            food.setImage(obj.optString("image"));
            food.setDescription(obj.optString("description"));
            food.setLike(obj.optInt("like"));
            food.setDateTime(obj.optLong("date"));
            JSONObject jUer = obj.optJSONObject("user");
            food.setUser(getUser(jUer.toString()));
            return food;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User getUser(String json) {
        try {
            JSONObject jUser = new JSONObject(json);
            User user = new User();
            user.setName(jUser.optString("name"));
            user.setProfilePicture(jUser.optString("image"));
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new User();
    }
}
