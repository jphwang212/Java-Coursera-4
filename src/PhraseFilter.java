public class PhraseFilter implements Filter {
    private String type;
    private String phrase;
    public PhraseFilter(String where, String search){
        type = where;
        phrase = search;
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
}
