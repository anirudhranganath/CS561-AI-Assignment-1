import java.util.*;
import java.io.*;

public class Main {
    public static float doubleEpsilon = 0.01f;
    public static float decrementalSpeedReductionOnMud = 0.1f;
    public static Algorithm algoinUse = Algorithm.BFS;
    public static void main(String[] args) throws IOException{
        // Testing input
        Problem x = new Problem();
        x.loadInputFromFile("input_example.txt");
        Solver s = new Solver(x,algoinUse);
        s.solve();
        Logger.outputLogInfo("out.txt");
    }
}
