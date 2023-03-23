package frc.API.serializers;

import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import frc.API.type.Match;

public class MatchJsonAdapter extends TypeAdapter<Match> {

    public Match read(JsonReader reader) throws IOException {

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        return new MatchJsonDeserializer().deserialize(new JsonPrimitive(reader.nextString()), Match.class, new GsonContextImpl());
    }
 
    public void write(JsonWriter writer, Match value) throws IOException {
        if (value == null) {
            writer.nullValue();
            return;
        }
        
        //String xy = value.getX() + "," + value.getY();
        //writer.value(xy);
    }

    private class GsonContextImpl implements JsonDeserializationContext {
        
        @SuppressWarnings("unchecked")
        @Override
        public <T> T deserialize(JsonElement json, Type typeOfT) throws JsonParseException {
            // TODO Auto-generated method stub
            return (T) new GsonBuilder().create().fromJson(json, typeOfT);
        }
    }
    
}
