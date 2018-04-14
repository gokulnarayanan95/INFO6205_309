package geneticalgorithm;

import geneticalgorithm.domain.Location;
import geneticalgorithm.domain.Team;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author naray
 */
public class Data {
    private ArrayList<Location> locations;
    private ArrayList<Team> Teams;
    private ArrayList<Date> dates;
    private ArrayList<Team> teamList;
    private ArrayList<Location> locationList;
    private HashMap<Date,HashMap<Location, Integer>> weather;
    
    public Data(){
        dates = new ArrayList<Date>();
        teamList= new ArrayList<Team>();
        locationList= new ArrayList<Location>();
        weather=  new HashMap<Date,HashMap<Location, Integer>>();
    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<Team> teamList) {
        this.teamList = teamList;
    }

    public ArrayList<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(ArrayList<Location> locationList) {
        this.locationList = locationList;
    }

    public HashMap<Date, HashMap<Location, Integer>> getWeather() {
        return weather;
    }
    
    public void initializeData(){
        int count=0;
        Date currDate = new Date();
        Calendar today = Calendar.getInstance();
        today.setTime(currDate);
        while(count < 60){
            dates.add(today.getTime());
            today.add(Calendar.DAY_OF_YEAR, 1);
            count++;
        }
        
    
        Location location1=new Location("Chennai");
        Location location2=new Location("Banglore");
        Location location3=new Location("Kolkata");
        Location location4=new Location("Delhi");
        Location location5=new Location("Mumbai");
        Location location6=new Location("Punjab");
        Location location7=new Location("Rajastan");
        Location location8=new Location("Hyderabad");
        
        
        locations= new ArrayList<>(Arrays.asList(location1,location2,location3,location4,location5,location6,location7,location8));
        
        Team team1=new Team("Chennai Super Kings",location1);
        Team team2=new Team("Royal Challengers Banglore",location2);
        Team team3=new Team("Kolkata Knight Riders",location3);
        Team team4=new Team("Delhi Daredevils",location4);
        Team team5=new Team("Mumbai Indians",location5);
        Team team6=new Team("Kings X1 Punjab",location6);
        Team team7=new Team("Rajastan Royals",location7);
        Team team8=new Team("Sunrisers Hyderabad",location8);
        
        
        Teams=new ArrayList<>(Arrays.asList(team1,team2,team3,team4,team5,team6,team7,team8));
        
        for (Date d : dates) {
            HashMap<Location, Integer> map = new HashMap<>();
            weather.put(d, map);
            for (Location l : locations) {
                map.put(l, (new Random()).nextInt(100));
            }
        }
    }   
}