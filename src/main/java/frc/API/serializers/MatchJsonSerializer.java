package frc.API.serializers;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import frc.API.type.Match;

/**
 * PLACEHOLDER, RETURNS NULL
 * @deprecated
 */
public class MatchJsonSerializer implements JsonSerializer<Match> {
    
    public JsonElement serialize(Match src, Type typeOfSrc, JsonSerializationContext context) {

        Gson gson = new GsonBuilder().create();
        gson.toJson(src);
        System.out.println("#############");
        return null;
    }
    
}
