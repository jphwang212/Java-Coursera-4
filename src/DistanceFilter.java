public class DistanceFilter implements Filter {
    private Location location;
    private double distanceMin;
    private double distanceMax;
    public DistanceFilter(double min, double max, Location loc){
        distanceMin = min;
        distanceMax = max;
        location = loc;
    }
    public boolean satisfies(QuakeEntry qe){
        double distance = location.distanceTo(qe.getLocation());
        return (distance >= distanceMin && distance <= distanceMax);
    }
}
