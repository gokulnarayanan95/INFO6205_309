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
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author naray
 */
public class Schedule {
    
    private ArrayList<Fixture> fixtureList;
    private Double fitness;
    private int conflicts;
    public Schedule(Data data) {
        fitness = (double) -1;
        fixtureList = new ArrayList<>();
        initialize(data);
    }
    
    private void initialize(Data data) {
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
            for(int j=i+1;j<fixtureList.size();j++){
                Fixture f1= fixtureList.get(i);
                Fixture f2= fixtureList.get(j);
                if(f1.getDate()==f2.getDate())
                    conflicts++;
            }
        }
        return (double)1/(1+conflicts);
    }
    public int getConflicts(){
        return conflicts;
    } 
}
