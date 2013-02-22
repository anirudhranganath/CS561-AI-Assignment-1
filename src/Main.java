import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException{
        // Testing input
        Problem x = new Problem();
        x.loadInputFromFile("my_sample_input.txt");
        Solver s = new Solver(x,Algorithm.BFS);
        s.solve();
        Logger.outputLogInfo("out.txt");


        /*testing comparators
        Comparator<Location> comparator = new DFSComparator();
        PriorityQueue<Location> container =
                new PriorityQueue<Location>(10, comparator);
        container.add(new Location(3,4));
        container.add(new Location(4,5));
        for(Location x:container){
            System.out.println(container.poll().xcood);
            System.out.println(container.poll().xcood);
            if(container.poll()==null) {
                System.out.println("cool");
            }
        }*/
    }
}
