import java.util.*;
import java.io.*;

enum Algorithm {
    BFS,DFS,AS1,AS2, BS1,BS2
}

class Search {
    public static float doubleEpsilon = 0.01f;
    public static float decrementalSpeedReductionOnMud = 0.1f;
    public static Algorithm algoinUse = Algorithm.BS1;
    public static int bsSize =10;
    public static float initialSpeed = 2.0f;
    public static String ifile = "input_example.txt";
    public static String ofile = "out.txt";
    public static void main(String[] args) throws IOException{
        // Testing input
        processArgs(args);
        Problem x = new Problem();
        x.loadInputFromFile(ifile);
        Solver s = new Solver(x,algoinUse);
        s.solve();
        Logger.outputLogInfo(ofile);
    }

    public static void processArgs(String[] args) {
        int itereval = 0;
        int algochoice = 0;
        for(int i=0;i<args.length;i++) {
            itereval = 0;
            if(algochoice == 0){
                if(args[i].equals("BFS")) {
                    algoinUse = Algorithm.BFS;
                    algochoice = 1;
                    itereval = 1;
                }
                else if(args[i].equals("DFS")) {
                    algoinUse = Algorithm.DFS;
                    algochoice = 1;
                    itereval = 1;
                }
                else if(args[i].equals("AStar")) {
                    algoinUse = Algorithm.AS2;
                    algochoice = 1;
                    itereval = 1;
                }
                else if(args[i].equals("Beam")) {
                    algoinUse = Algorithm.BS2;
                    algochoice = 1;
                    if(args[i+1].equals("-k")) {
                        bsSize = Integer.getInteger(args[i+2]);
                        i+=2;
                    }
                    else {
                        System.out.println("`-k %value` Expected. I got: "+args[i+1]+" "+args[i+2]);
                        System.exit(-1);
                    }
                    itereval = 1;
                }
                else {
                    System.out.println("Algorithm Expected. I got: "+args[i]);
                    System.exit(-1);
                }
            }
            else {  //algo is settled
                //System.out.println("ott"+ args[i]);
                if(args[i].equals("-s")){
                    initialSpeed = Float.parseFloat(args[i+1]);
                    i++;
                }
                if(args[i].equals("-i")){
                    ifile = args[i+1];
                    i++;
                }
                if(args[i].equals("-o")){
                    ofile = args[i+1];
                    i++;
                }
            }
        }
    }
}
