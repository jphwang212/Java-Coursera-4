public class DistanceFilter implements Filter {
    private Location location;
    private double distanceMin;
    private double distanceMax;
    private String filterName;
    public DistanceFilter(double min, double max, Location loc, String name){
        distanceMin = min;
        distanceMax = max;
        location = loc;
        filterName = name;
    }
    public boolean satisfies(QuakeEntry qe){
        double distance = location.distanceTo(qe.getLocation());
        return (distance >= distanceMin && distance <= distanceMax);
    }
    public String getName(){
        return filterName;
    }
}
