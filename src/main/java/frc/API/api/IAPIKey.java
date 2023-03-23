package frc.API.api;

/**
 * An object that represents a BlueAlliance API key, required for requesting data from the API.
 * API keys are obtained at the official BlueAlliance website.
 * https://www.thebluealliance.com/
 */
public interface IAPIKey {
    /** Returns a string representing a BlueAlliance API key. */
    public String getKey();
}
