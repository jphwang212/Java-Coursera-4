import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String endTitle1 = q1.getInfo().split(" ")[q1.getInfo().split(" ").length-1];
        String endTitle2 = q2.getInfo().split(" ")[q2.getInfo().split(" ").length-1];
        int diff = endTitle1.compareTo(endTitle2);
        if(diff == 0){
            diff = Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return diff;
    }

}
