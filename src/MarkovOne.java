import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {
    private String myText;
    private Random myRandom;

    public MarkovOne() {
        myRandom = new Random();
    }

    public void setRandom(int seed){
        myRandom = new Random(seed);
    }

    public void setTraining(String s){
        myText = s.trim();
    }

    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int k=0; k < numChars; k++){
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }

    public ArrayList<Character> getFollows(String key){
        ArrayList<Character> following = new ArrayList<Character>();
        for(int i = 0; i < (myText.length() - key.length()); i++){
            if(myText.substring(i, key.length()).equals(key)){
                following.add(myText.charAt(i + key.length() + 1));
            }
        }
        return following;
    }
}
