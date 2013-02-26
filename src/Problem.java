import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 8/2/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */

public class Problem {
    //int map[][];
    LinkedList<Location> white;
    LinkedList<Location> mud;
    Location start;
    Location goal;
    float initialSpeed;
    float decrementalSpeedReductionOnMud;
    int width;
    int height;

    Problem()
    {
        initialSpeed = Search.initialSpeed;
        decrementalSpeedReductionOnMud = Search.decrementalSpeedReductionOnMud;
        white = new LinkedList<Location>();
        mud = new LinkedList<Location>();
    }

    public void loadInputFromFile(String filePath) {
        BufferedReader br = null;
        String input = "";
        char inchar;
        try {
            br = new BufferedReader(new FileReader(filePath));
            width = Integer.parseInt(br.readLine().substring(6));
            height = Integer.parseInt(br.readLine().substring(7));
            for(int h=0;h<height;h++) {
                input = br.readLine();
                for(int w = 0; w<width ; w++ ) {
                    //System.out.println(input);
                    inchar = input.charAt(w);
                    if(inchar==' ')
                        white.add(new Location(w,h));
                    else if (inchar == 'M')
                        mud.add(new Location(w,h));
                    else if (inchar == 'S')
                        start = new Location(w,h)  ;
                    else if (inchar == 'G')
                        goal = new Location(w,h);
                    else if (inchar !='*' && inchar != '\n')
                        System.out.println("Unexpected Input: " + Character.toString(inchar) +" in line "+Integer.toString(h+3));
                }
            }
        }
        catch (IOException e) {
            System.out.println("Input file not found, or permission denied");
            System.exit(-1);
            //e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        /*System.out.println(Integer.toString(width) +" "+ Integer.toString(height));
        System.out.println("start: "+Integer.toString(start.xcood) +","+ Integer.toString(start.ycood));
        System.out.println("goal: "+Integer.toString(goal.xcood) +","+ Integer.toString(goal.ycood));
        for(Location x:mud)
        {
            System.out.println(x.xcood+" "+x.ycood);
        }*/
    }
}
