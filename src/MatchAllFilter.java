import java.util.*;
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }

    public void addFilter(Filter f){
        filters.add(f);
    }
    public boolean satisfies(QuakeEntry qe){
        for(Filter filter : filters){
            if(!filter.satisfies(qe)){
                return false;
            }
        }
        return true;
    }
    public String getName(){
        StringBuilder output = new StringBuilder();
        for(Filter f : filters){
            output.append(f.getName() + ' ');
        }
        return output.toString();
    }
}
