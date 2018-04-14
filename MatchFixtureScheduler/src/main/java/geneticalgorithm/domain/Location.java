package geneticalgorithm.domain;

/**
 * Data structure for storing Locations
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/10/2018
 */
public class Location {
    /**
     * Location of the match
     */
    String locationName;

    /**
     * Creates an instance with passed location
     *
     * @param locationName Name of the location
     */
    public Location(String locationName) {
        this.locationName = locationName;
    }

    /**
     * Creates an instance with default values
     */
    public Location() {
    }

    /**
     * Getter method for {@code String locationName}
     *
     * @return The value of the variable locationName
     */
    public String getLocationName() {
        return locationName;
    }
}
