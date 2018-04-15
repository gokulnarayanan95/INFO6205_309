
import geneticalgorithm.Data;
import geneticalgorithm.Population;
import geneticalgorithm.domain.Fixture;
import geneticalgorithm.domain.Location;
import geneticalgorithm.domain.Schedule;
import geneticalgorithm.domain.Team;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author naray
 */
public class GeneticAlgorithmTest {
    Data data;
    
    @Test
    public void FitnessTest1(){
        
        
        Population testPop= new Population(10,data,false);
        ArrayList<Schedule> scheduleList= testPop.getSchedules();
        scheduleList.add(new Schedule());
        Schedule s1=  scheduleList.get(0);
        
        Location location1 = new Location("Chennai");
        Location location2 = new Location("Banglore");
        Location location3 = new Location("Kolkata");
        
        Team team1 = new Team("Chennai Super Kings", location1);
        Team team2 = new Team("Royal Challengers Banglore", location2);
        Team team3 = new Team("Kolkata Knight Riders", location3);
        Date date1 = new Date();
        Date date2 =new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        Date date5 =new Date();
        Date date6 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        try{
         date1 = format.parse("4/15/2018");
         date2 = format.parse("4/16/2018");
        date3 = format.parse("4/17/2018");
        date4 = format.parse("4/18/2018");
        date5 = format.parse("4/19/2018");
        date6 = format.parse("4/20/2018");
        
        }
        catch(Exception e){
            
        }
        Fixture f1= new Fixture(date1, team1, team2, location1);
         Fixture f2= new Fixture(date2, team1, team3, location1);
          Fixture f3= new Fixture(date3, team2, team1, location2);
           Fixture f4= new Fixture(date4, team2, team3, location2);
            Fixture f5= new Fixture(date5, team3, team2, location3);
             Fixture f6= new Fixture(date6, team3, team1, location3);
            ArrayList<Fixture> fixtureList= new ArrayList<>();
            fixtureList.add(f1); fixtureList.add(f2); fixtureList.add(f3);
            fixtureList.add(f4); fixtureList.add(f5); fixtureList.add(f6); 
            
         s1.getFixtureList().add(f1);
         s1.getFixtureList().add(f2);
         s1.getFixtureList().add(f3);
         s1.getFixtureList().add(f4);
         s1.getFixtureList().add(f5);
         s1.getFixtureList().add(f6);
         
          int expected=0;
         double ex= 1/(1+expected);
         assertEquals(ex,s1.computeFitness(), .001);
         
    }
    
    @Test
    public void FitnessTest2(){
        
        
        Population testPop= new Population(10,data,false);
        ArrayList<Schedule> scheduleList= testPop.getSchedules();
        scheduleList.add(new Schedule());
        Schedule s1=  scheduleList.get(0);
        
        Location location1 = new Location("Chennai");
        Location location2 = new Location("Banglore");
        Location location3 = new Location("Kolkata");
        
        Team team1 = new Team("Chennai Super Kings", location1);
        Team team2 = new Team("Royal Challengers Banglore", location2);
        Team team3 = new Team("Kolkata Knight Riders", location3);
        Date date1 = new Date();
        Date date2 =new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        Date date5 =new Date();
        Date date6 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        try{
         date1 = format.parse("4/15/2018");
         date2 = format.parse("4/16/2018");
        date3 = format.parse("4/17/2018");
        date4 = format.parse("4/18/2018");
        date5 = format.parse("4/18/2018");
        date6 = format.parse("4/20/2018");
        
        }
        catch(Exception e){
            
        }
        Fixture f1= new Fixture(date1, team1, team2, location1);
         Fixture f2= new Fixture(date2, team1, team3, location1);
          Fixture f3= new Fixture(date3, team2, team1, location2);
           Fixture f4= new Fixture(date4, team2, team3, location2);
            Fixture f5= new Fixture(date5, team3, team2, location3);
             Fixture f6= new Fixture(date6, team3, team1, location3);
             
        s1.getFixtureList().add(f1);
         s1.getFixtureList().add(f2);
         s1.getFixtureList().add(f3);
         s1.getFixtureList().add(f4);
         s1.getFixtureList().add(f5);
         s1.getFixtureList().add(f6);
         
         int expected=1;
         double ex= 1/(1+expected);
         assertEquals(ex,s1.computeFitness(), .001);
         
    }
    
    @Test
    public void FitnessTest3(){
        
        
        Population testPop= new Population(10,data,false);
        ArrayList<Schedule> scheduleList= testPop.getSchedules();
        
        scheduleList.add(new Schedule());
        Schedule s1=  scheduleList.get(0);
        
        Location location1 = new Location("Chennai");
        Location location2 = new Location("Banglore");
        Location location3 = new Location("Kolkata");
        
        Team team1 = new Team("Chennai Super Kings", location1);
        Team team2 = new Team("Royal Challengers Banglore", location2);
        Team team3 = new Team("Kolkata Knight Riders", location3);
        Date date1 = new Date();
        Date date2 =new Date();
        Date date3 = new Date();
        Date date4 = new Date();
        Date date5 =new Date();
        Date date6 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        try{
         date1 = format.parse("4/15/2018");
         date2 = format.parse("4/16/2018");
        date3 = format.parse("4/18/2018");
        date4 = format.parse("4/18/2018");
        date5 = format.parse("4/18/2018");
        date6 = format.parse("4/20/2018");
        
        }
        catch(Exception e){
            
        }
        Fixture f1= new Fixture(date1, team1, team2, location1);
         Fixture f2= new Fixture(date2, team1, team3, location1);
          Fixture f3= new Fixture(date3, team2, team1, location2);
           Fixture f4= new Fixture(date4, team2, team3, location2);
            Fixture f5= new Fixture(date5, team3, team2, location3);
             Fixture f6= new Fixture(date6, team3, team2, location3);
             
         s1.getFixtureList().add(f1);
         s1.getFixtureList().add(f2);
         s1.getFixtureList().add(f3);
         s1.getFixtureList().add(f4);
         s1.getFixtureList().add(f5);
         s1.getFixtureList().add(f6);
         
         int expected=3;
         double ex= 1/(1+expected);
         assertEquals(ex,s1.computeFitness(), .001);
         
    }
}
