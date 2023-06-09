import java.util.ArrayList;
import java.util.Random;

public class MarkovModel {
    private int numCharsUsed;
    private String myText;
    private Random myRandom;

    public MarkovModel(int numChars) {
        myRandom = new Random();
        numCharsUsed = numChars;
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
        int index = myRandom.nextInt(myText.length() - numCharsUsed);
        String key = myText.substring(index, index + numCharsUsed);
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        for(int k=0; k < numChars - numCharsUsed; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    public ArrayList<String> getFollows(String key){
        ArrayList<String> following = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length()){
            int start = myText.indexOf(key, pos);
            if(start == -1){
                break;
            }
            if(start + key.length() >= myText.length() - 1){
                break;
            }
            String next = myText.substring(start + key.length(), start + key.length() + 1);
            following.add(next);
            pos = start + key.length();
        }
        return following;
    }
}
