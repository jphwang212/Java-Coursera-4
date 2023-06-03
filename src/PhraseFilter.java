public class PhraseFilter implements Filter {
    private String type;
    private String phrase;
    private String filterName;
    public PhraseFilter(String where, String search, String name){
        type = where;
        phrase = search;
        filterName = name;
    }
    public boolean satisfies(QuakeEntry qe){
        String entry = qe.getInfo();
        if(type.equals("start")){
            return entry.substring(0, phrase.length()).equals(phrase);
        } else if(type.equals("end")){
            return entry.endsWith(phrase);
        } else if(type.equals("any")){
            return entry.contains(phrase);
        }
        return false;
    }
    public String getName(){
        return filterName;
    }
}
