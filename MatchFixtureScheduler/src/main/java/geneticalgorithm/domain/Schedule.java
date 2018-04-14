package geneticalgorithm.domain;

import geneticalgorithm.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Data structure for storing the Schedule
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/10/2018
 */
public class Schedule {

    /**
     * This hash-map stores the probability of rain for each day at each location
     */
    private HashMap<Date, HashMap<Location, Integer>> weathers;

    /**
     * This stores the number of matches played by each team
     */
    private HashMap<Team, Integer> matchesplayed;

    /**
     * This stores the number of matches played in home-ground by each team
     */
    private HashMap<Team, Integer> homematches;

    /**
     * This stores the number of matches played at each location
     */
    private HashMap<Location, Integer> matchesInLocation;

    /**
     * This stores the list of all fixtures in this schedule
     */
    private ArrayList<Fixture> fixtureList;

    /**
     * This stores the fitness of this schedule
     */
    private Double fitness;

    /**
     * This stores the total number of conflicts in this schedule
     */
    private int conflicts;

    /**
     * Creates an instance of Schedule based on the data sent
     *
     * @param data Data set using which the schedule has to be made
     */
    public Schedule(Data data) {
        fitness = (double) -1;
        fixtureList = new ArrayList<>();
        initialize(data);
    }

    /**
     * This method initializes schedule randomly based on the total data-set
     *
     * @param data Data set using which the schedule has to be made
     */
    private void initialize(Data data) {
        weathers = data.getWeather();
        for (Team team : data.getTeamList()) {
            homematches.put(team, 0);
            matchesplayed.put(team, 0);
        }
        for (Location location : data.getLocationList()) {
            matchesInLocation.put(location, 0);
        }
        for (Team team : data.getTeamList()) {
            for (Team opponent : data.getTeamList()) {
                if (team == opponent)
                    continue;
                Date d = data.getDates().get((int) (data.getDates().size() * Math.random()));
                Location l = data.getLocationList().get((int) (data.getLocationList().size() * Math.random()));

                fixtureList.add(new Fixture(d, team, opponent, l));
            }
        }
    }

    /**
     * Getter method for {@code fixtures}
     *
     * @return List of all fixtures in this schedule
     */
    public ArrayList<Fixture> getFixures() {
        return fixtureList;
    }

    /**
     * This method calculates and returns the fitness of this schedule
     *
     * @return fitness of this schedule
     */
    public Double getFitness() {
        fitness = computeFitness();
        return fitness;
    }

    /**
     * This method can be used to get the total number of conflicts in this schedule
     *
     * @return number of conflicts
     */
    public int getConflicts() {
        return conflicts;
    }

    /**
     * This method gives the total number of fixtures in this schedule
     *
     * @return size of the fixtures list
     */
    public int size() {
        return fixtureList.size();
    }

    /**
     * This method calculates the fitness of this schedule with respect to the criteria needed.
     *
     * @return fitness of this schedule
     */
    private double computeFitness() {
        conflicts = 0;


        for (int i = 0; i < fixtureList.size(); i++) {
            Fixture f1 = fixtureList.get(i);
            int weatherIndex = weathers.get(f1.getDate()).get(f1.getLocation());
            if (weatherIndex > 30 && weatherIndex < 60)
                conflicts++;
            if (weatherIndex > 60 && weatherIndex < 80)
                conflicts = conflicts + 2;
            if (weatherIndex > 80)
                conflicts = conflicts + 3;


            matchesplayed.put(f1.getHomeTeam(), matchesplayed.get(f1.getHomeTeam()) + 1);
            matchesplayed.put(f1.getAwayTeam(), matchesplayed.get(f1.getAwayTeam()) + 1);
            matchesInLocation.put(f1.getLocation(), matchesInLocation.get(f1.getLocation()) + 1);
            homematches.put(f1.getHomeTeam(), homematches.get(f1.getHomeTeam()) + 1);


            Date currentDate = f1.getDate();
            LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            localDateTime = localDateTime.plusDays(1);
            Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());


            for (int j = i + 1; j < fixtureList.size(); j++) {

                Fixture f2 = fixtureList.get(j);
                if (f1.getDate() == f2.getDate()) {
                    conflicts++;
                }

                if (currentDatePlusOneDay == f2.getDate()) {
                    if (f1.getAwayTeam() == f2.getAwayTeam() || f1.getAwayTeam() == f2.getHomeTeam() || f1.getHomeTeam() == f2.getHomeTeam() || f1.getHomeTeam() == f2.getAwayTeam()) {
                        conflicts++;
                    }
                }

            }
        }


        Iterator<Map.Entry<Team, Integer>> entries = matchesplayed.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Team, Integer> entry = entries.next();

            if (entry.getValue() != 14) {
                conflicts++;
            }
        }
        Iterator<Map.Entry<Location, Integer>> entries2 = matchesInLocation.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Location, Integer> entry2 = entries2.next();

            if (entry2.getValue() != 7) {
                conflicts++;
            }
        }

        Iterator<Map.Entry<Team, Integer>> entries3 = homematches.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Team, Integer> entry3 = entries3.next();

            if (entry3.getValue() != 7) {
                conflicts++;
            }
        }


        return (double) 1 / (1 + conflicts);
    }
}
