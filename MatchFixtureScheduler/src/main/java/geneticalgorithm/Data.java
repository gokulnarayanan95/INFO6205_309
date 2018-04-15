package geneticalgorithm;

import geneticalgorithm.domain.Location;
import geneticalgorithm.domain.Team;

import java.util.*;

/**
 * This class holds the complete data set for the algorithm to be applied.
 * This class contains the following:
 * - The teams which are playing for this season
 * - Possible dates the matches can be scheduled
 * - Possible locations where the matches can be scheduled
 * - Weather condition for each day at each location (Probability for Rain)
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/13/2018
 */
public class Data {
    /**
     * The member variables for holding the data
     */
    private ArrayList<Date> dates;
    private ArrayList<Team> teamList;
    private ArrayList<Location> locationList;
    private HashMap<String, HashMap<Location, Integer>> weather;

    /**
     * Constructor. Creates an instance for the class Data.
     */
    Data() {
        dates = new ArrayList<>();
        teamList = new ArrayList<>();
        locationList = new ArrayList<>();
        weather = new HashMap<>();
        initializeData();
    }

    /**
     * Getter method for {@code ArrayList<Date> dates}
     *
     * @return The value of dates variable
     */
    public ArrayList<Date> getDates() {
        return dates;
    }

    /**
     * Getter method for {@code ArrayList<Team> teamList}
     *
     * @return The value of teamList variable
     */
    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    /**
     * Getter method for {@code ArrayList<Location> locationList}
     *
     * @return The value of locationList variable
     */
    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    /**
     * Getter method for {@code HashMap<Date, HashMap<Location, Integer>> weather}
     *
     * @return The value of weather variable
     */
    public HashMap<String, HashMap<Location, Integer>> getWeather() {
        return weather;
    }

    /**
     * This function initializes all the member variable with default values. In this case the data.
     */
    void initializeData() {
        int count = 0;
        Date currDate = new Date();
        Calendar today = Calendar.getInstance();
        today.setTime(currDate);
        dates.clear();
        while (count < 60) {
            dates.add(today.getTime());
            today.add(Calendar.DAY_OF_YEAR, 1);
            count++;
        }


        Location location1 = new Location("Chennai");
        Location location2 = new Location("Banglore");
        Location location3 = new Location("Kolkata");
        Location location4 = new Location("Delhi");
        Location location5 = new Location("Mumbai");
        Location location6 = new Location("Punjab");
        Location location7 = new Location("Rajastan");
        Location location8 = new Location("Hyderabad");

        locationList.clear();
        locationList.addAll(Arrays.asList(location1, location2, location3, location4, location5, location6, location7, location8));

        Team team1 = new Team("Chennai Super Kings", location1);
        Team team2 = new Team("Royal Challengers Banglore", location2);
        Team team3 = new Team("Kolkata Knight Riders", location3);
        Team team4 = new Team("Delhi Daredevils", location4);
        Team team5 = new Team("Mumbai Indians", location5);
        Team team6 = new Team("Kings XI Punjab", location6);
        Team team7 = new Team("Rajasthan Royals", location7);
        Team team8 = new Team("Sunrisers Hyderabad", location8);


        teamList.clear();
        teamList.addAll(Arrays.asList(team1, team2, team3, team4, team5, team6, team7, team8));

        weather.clear();
        for (Date d : dates) {
            HashMap<Location, Integer> map = new HashMap<>();
            weather.put(d.toString(), map);
            for (Location l : locationList) {
                map.put(l, (new Random()).nextInt(100));
            }
        }
    }
}