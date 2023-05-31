import java.util.*;
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 5);
        for(QuakeEntry qe : largestQuakes){
            System.out.println(qe);
        }
    }
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        int idxLargest = 0;
        double largestMag = 0;
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).getMagnitude() > largestMag){
                largestMag = data.get(i).getMagnitude();
                idxLargest = i;
            }
        }
        return idxLargest;
    }
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(int i = 0; i < howMany; i++){
            int idxLargest = indexOfLargest(quakeData);
            answer.add(quakeData.get(idxLargest));
            quakeData.remove(idxLargest);
        }
        return answer;
    }

    public static void main(String[] args) {
        LargestQuakes inst = new LargestQuakes();
        inst.findLargestQuakes();
    }
}
