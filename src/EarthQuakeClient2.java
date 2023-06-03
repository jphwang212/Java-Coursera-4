import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        }
        
        return answer;
    } 

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
//        Location japan = new Location(35.42, 139.43);
//        Location denverCO = new Location(39.7392, -104.9903);
//        Filter f = new DistanceFilter(0, 1000000, denverCO, "distance");
        Filter f = new MinMagFilter(3.5, 4.5, "magnitude");
        ArrayList<QuakeEntry> m7  = filter(list, f); 
//        for (QuakeEntry qe: m7) {
//            System.out.println(qe);
//        }
        Filter f1 = new DepthFilter(-55000.0, -20000.0, "depth");
//        Filter f1 = new PhraseFilter("end", "Japan", "phrase");
//        Filter f1 = new PhraseFilter("end", "a", "phrase");
        ArrayList<QuakeEntry> m8 = filter(m7, f1);
        for (QuakeEntry qe : m8){
            System.out.println(qe);
        }
        System.out.println("Filtered length = " + m8.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        Filter f1 = new MinMagFilter(1.0, 4.0, "magnitude");
        Filter f2 = new DepthFilter(-180000, -30000, "depth");
        Filter f3 = new PhraseFilter("any", "o", "phrase");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for(QuakeEntry qe : filtered){
            System.out.println(qe);
        }
        System.out.println("Filters used are: " + maf.getName());
        System.out.println("Filtered length = " + filtered.size());
    }
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        Filter f1 = new MinMagFilter(0.0, 5.0, "magnitude");
//        Location tulsaOK = new Location(36.1314, -95.9372);
        Location billundDM = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(0, 3000000, billundDM, "distance");
//        Filter f2 = new DepthFilter(-100000.0, -10000.0, "depth");
        Filter f3 = new PhraseFilter("any", "e", "phrase");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> filtered = filter(list, maf);
        for(QuakeEntry qe : filtered){
            System.out.println(qe);
        }
        System.out.println("Filtered length = " + filtered.size());
    }

    public static void main(String[] args) {
        EarthQuakeClient2 inst = new EarthQuakeClient2();
//        inst.quakesWithFilter();
//        inst.testMatchAllFilter();
        inst.testMatchAllFilter2();
    }
}
