/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.domain;

import geneticalgorithm.Data;
import geneticalgorithm.domain.Fixture;
import geneticalgorithm.domain.Location;
import geneticalgorithm.domain.Team;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author naray
 */
public class Schedule {
    
    private HashMap<Date,HashMap<Location, Integer>> weathers;
    private HashMap<Team,Integer> matchesplayed;
    private HashMap<Team,Integer> homematches;
    private HashMap<Location,Integer> matchesInLocation;
    private ArrayList<Fixture> fixtureList;
    private Double fitness;
    private int conflicts;
    
    public Schedule(Data data) {
        fitness = (double) -1;
        fixtureList = new ArrayList<>();
        initialize(data);
    }
    
    private void initialize(Data data) {
        weathers=data.getWeather();
        for(Team team : data.getTeamList()){
        homematches.put(team,0);
        matchesplayed.put(team,0);
        }
        for(Location location:data.getLocationList()){
            matchesInLocation.put(location,0);
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

    public ArrayList<Fixture> getFixures() {
        return fixtureList;
    }

    public void setFixures(ArrayList<Fixture> fixures) {
        this.fixtureList = fixures;
    }

    public Double getFitness() {
        fitness = computeFitness();
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }    
    
    public int size(){
        return fixtureList.size();
    }
    
    private double computeFitness(){
        conflicts=0;
        
        
         
        for(int i=0; i<fixtureList.size();i++){
            Fixture f1= fixtureList.get(i);
            int weatherIndex=weathers.get(f1.getDate()).get(f1.getLocation());
            if(weatherIndex>30&&weatherIndex<60)
                conflicts++;
            if(weatherIndex>60&&weatherIndex<80)
                conflicts=conflicts+2;
            if(weatherIndex>80)
                conflicts=conflicts+3;
            
            
            
            matchesplayed.put(f1.getHomeTeam(),matchesplayed.get(f1.getHomeTeam())+1);
            matchesplayed.put(f1.getAwayTeam(),matchesplayed.get(f1.getAwayTeam())+1);
            matchesInLocation.put(f1.getLocation(),matchesInLocation.get(f1.getLocation())+1);
            homematches.put(f1.getHomeTeam(),homematches.get(f1.getHomeTeam())+1);
            
            
           Date currentDate =f1.getDate();
          LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
          localDateTime = localDateTime.plusDays(1);
          Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            
            
            
            for(int j=i+1;j<fixtureList.size();j++){
               
                Fixture f2= fixtureList.get(j);
                if(f1.getDate()==f2.getDate()){
                    conflicts++;
                }
                
                if(currentDatePlusOneDay==f2.getDate()){
                    if(f1.getAwayTeam()==f2.getAwayTeam()||f1.getAwayTeam()==f2.getHomeTeam()||f1.getHomeTeam()==f2.getHomeTeam()||f1.getHomeTeam()==f2.getAwayTeam()){
                        conflicts++;
                    }
                }
              
            }
        }
        
        
        Iterator<Map.Entry<Team, Integer>> entries = matchesplayed.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Team, Integer> entry = entries.next();
    
            if(entry.getValue()!=14){
            conflicts++;
        }
    }
        Iterator<Map.Entry<Location, Integer>> entries2 = matchesInLocation.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Location, Integer> entry2 = entries2.next();
    
            if(entry2.getValue()!=7){
            conflicts++;
        }
    }
        
         Iterator<Map.Entry<Team, Integer>> entries3 = homematches.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Team, Integer> entry3 = entries3.next();
    
            if(entry3.getValue()!=7){
            conflicts++;
        }
    }
        
               
        return (double)1/(1+conflicts);
    }
    public int getConflicts(){
        return conflicts;
    } 
}
