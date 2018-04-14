/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

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
    
    private ArrayList<Fixture> fixture;
    private Double fitness;
    
    public Schedule(Data data) {
        fitness = (double) -1;
        fixture = new ArrayList<>();
        initialize(data);
    }
    
    private void initialize(Data data) {
        for (Team team : data.getTeamList()) {
            for (Team opponent : data.getTeamList()) {
                if (team == opponent)
                    continue;
                Date d = data.getDates().get((int) (data.getDates().size() * Math.random()));
                Location l = data.getLocationList().get((int) (data.getLocationList().size() * Math.random()));
                
                fixture.add(new Fixture(d, team, opponent, l));
            }
        }
    }

    public ArrayList<Fixture> getFixures() {
        return fixture;
    }

    public void setFixures(ArrayList<Fixture> fixures) {
        this.fixture = fixures;
    }

    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }    
    
    public int size(){
        return fixture.size();
    }
}
