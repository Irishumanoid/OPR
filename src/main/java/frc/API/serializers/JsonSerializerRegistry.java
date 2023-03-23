package frc.API.serializers;

import com.google.gson.GsonBuilder;

import frc.API.type.Match;

public class JsonSerializerRegistry {

    private static GsonBuilder gson = new GsonBuilder();
    
    public JsonSerializerRegistry(GsonBuilder builder) {

        gson = builder;

    }

    public static void registerSerializers() {
        gson.registerTypeAdapter(Match.class, new MatchJsonAdapter());
        //gson.registerTypeAdapter(Match.class, new MatchJsonSerializer());
        //gson.registerTypeAdapter(Match.class, new MatchJsonDeserializer());
        System.out.println("Serializers supposedly registered!");
    }

}
