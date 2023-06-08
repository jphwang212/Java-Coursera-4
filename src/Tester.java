import edu.duke.FileResource;

import java.util.ArrayList;

public class Tester {
    public void testGetFollows(){
        MarkovOne mk = new MarkovOne();
        mk.setTraining("this is a test yes this is a test.");
        ArrayList<String> follows = mk.getFollows("t");
        System.out.println(follows.size());
    }
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String trainText = fr.asString();
        MarkovOne mk = new MarkovOne();
        mk.setTraining(trainText);
        ArrayList<String> follows = mk.getFollows("t");
        System.out.println("Size = " + follows.size());
    }

    public static void main(String[] args){
        Tester test = new Tester();
//        test.testGetFollows();
        test.testGetFollowsWithFile();
    }
}
