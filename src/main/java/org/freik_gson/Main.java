package org.freik_gson;

import com.google.gson.Gson;
import org.freik_gson.json.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = NamedItems.getGsonParser();
        gson.fromJson("", RValue.class);
    }
}