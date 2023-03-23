package frc.API.api;



public class APIKey implements IAPIKey {
    private String key;

    public APIKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
    
}
