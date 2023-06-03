public class DepthFilter implements Filter {
    private double depthMin;
    private double depthMax;
    private String filterName;
    public DepthFilter(double min, double max, String name){
        depthMin = min;
        depthMax = max;
        filterName = name;
    }
    public boolean satisfies(QuakeEntry qe){
        return (qe.getDepth() >= depthMin && qe.getDepth() <= depthMax);
    }
    public String getName(){
        return filterName;
    }
}
