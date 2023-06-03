import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if((qe.getLocation().distanceTo(from) / 1000) < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        double currDepth = 0.0;
        for(QuakeEntry qe : quakeData){
            currDepth = qe.getDepth();
            if(currDepth > minDepth && currDepth < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(where.equals("start")){
                if(qe.getInfo().substring(0, phrase.length()).equals(phrase)){
                    answer.add(qe);
                }
            } else if(where.equals("end")){
                if(qe.getInfo().endsWith(phrase)){
                    answer.add(qe);
                }

            } else if(where.equals("any")){
                if(qe.getInfo().contains(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> bigQuakes = filterByMagnitude(list, 5.0);
        System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry qe : bigQuakes){
            System.out.println(qe);
        }
        System.out.println("Found " + bigQuakes.size() + " quakes that match that criteria");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String testSource = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(testSource);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
//        Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> quakesInRange = filterByDistanceFrom(list, 1000.0, city);
        for(QuakeEntry qe : quakesInRange){
            System.out.println((qe.getLocation().distanceTo(city) / 1000) + " | " + qe.getInfo());
        }
        System.out.println("Found " + quakesInRange.size() + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        ArrayList<QuakeEntry> quakesInDepth = filterByDepth(list, -4000.0, -2000.0);
        System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry qe : quakesInDepth){
            System.out.println(qe);
        }
        System.out.println("Found " + quakesInDepth.size() + " quakes that match that criteria");

    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> quakesFiltered = filterByPhrase(list, "any", "Can");
        System.out.println("read data for "+list.size()+" quakes");
        for(QuakeEntry qe : quakesFiltered){
            System.out.println(qe);
        }
        System.out.println("Found " + quakesFiltered.size() + " quakes that match that criteria");

    }

    public static void main(String[] args) {
        EarthQuakeClient ec = new EarthQuakeClient();
        ec.quakesByPhrase();
//        ec.quakesOfDepth();
    }
}
