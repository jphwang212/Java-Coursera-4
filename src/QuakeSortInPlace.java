
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
        double maxDepth = 0.0;
        int answer = from;
        for(int i = from; i < quakeData.size(); i++){
            double currDepth = quakeData.get(i).getDepth();
            if(currDepth > maxDepth){
                maxDepth = currDepth;
                answer = i;
            }
        }
        return answer;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
        for(int i = 0; i < 70; i++){
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry holdMin = in.get(minIdx);
            in.set(minIdx, in.get(i));
            in.set(i, holdMin);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
        for(int i = 0; i < (quakeData.size() - numSorted); i++){
            if(quakeData.get(i).getMagnitude() > quakeData.get(i + 1).getMagnitude()){
                QuakeEntry maxHold = quakeData.get(i);
                quakeData.set(i, quakeData.get(i + 1));
                quakeData.set(i + 1, maxHold);
            }
        }
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
        for(int i = 1; i < in.size(); i++){
            onePassBubbleSort(in, i);
        }
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for(int i = 1; i < in.size(); i++){
            if(checkinSortedOrder(in)){
                break;
            }
            onePassBubbleSort(in, i);
            count++;
        }
        System.out.println("Number of passes = " + count);
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
        int count = 0;
        for(int i = 0; i < in.size(); i++){
            if(checkinSortedOrder(in)){
                break;
            }
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry minHold = in.get(minIdx);
            in.set(minIdx, in.get(i));
            in.set(i, minHold);
            count++;
        }
        System.out.println("Number of passes = " + count);
    }

    public boolean checkinSortedOrder(ArrayList<QuakeEntry> quakes){
        for(int i = 1; i < quakes.size(); i++){
             if(quakes.get(i - 1).getMagnitude() > quakes.get(i).getMagnitude()){
                 return false;
             }
        }
        return true;
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
//        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
//        String source = "data/earthquakeDataSampleSix2.atom";
        String source = "data/earthQuakeDataDec6sample1.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
//        sortByMagnitude(list);
        sortByLargestDepth(list);
//        sortByMagnitudeWithBubbleSort(list);
//        sortByMagnitudeWithBubbleSortWithCheck(list);
//        sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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

    public static void main(String[] args) {
        QuakeSortInPlace inst = new QuakeSortInPlace();
        inst.testSort();
    }
}
/**
 * 3 7 2 8 1 4 | 2 3 7 1 4 8
 * 3 2 7 8 1 4 | 2 3 1 7 4 8
 * 3 2 7 1 8 4 | 2 3 1 4 7 8
 * 3 2 7 1 4 8 |
 */