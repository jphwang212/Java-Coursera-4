
/**
 * Filter for minimum or maximum magnitude.
 *
 * @jphwang212
 * @20230531
 */
public class MinMagFilter implements Filter
{
    private double magMin;
    private double magMax;
    private String filterName;
    
    public MinMagFilter(double min, double max, String name) {
        magMin = min;
        magMax = max;
        filterName = name;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax);
    } 
    public String getName(){
        return filterName;
    }
}
