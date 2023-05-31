
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
    
    public MinMagFilter(double min, double max) {
        magMin = min;
        magMax = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return (qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax);
    } 

}
