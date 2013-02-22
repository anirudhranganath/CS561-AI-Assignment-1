/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 21/2/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */

import java.io.*;
import java.lang.*;

public class Logger {
    public static StringBuilder stateLog = new StringBuilder("");
    public static StringBuilder searchLog = new StringBuilder("Search log\n");
    public static int nIter;
    public static int pathLength;

    public static void appendStateLog(String x){
        stateLog.append(x+"\n");
    }

    public static void appendSearchLog(String x){
        searchLog.append(x+"\n\n");
    }

    public static void outputLogInfo(String filePath) throws IOException{
        String initString = String.format("Number of Iteration = %d\nPath Length = %d\n",nIter,pathLength);
        BufferedWriter  owrite;
        if(filePath==""){
            owrite = new BufferedWriter(new OutputStreamWriter(System.out));
        }
        else {
            owrite= new BufferedWriter(new FileWriter(filePath));
        }
        owrite.write(initString);
        owrite.write(stateLog.toString());
        owrite.write("---------------------------------------\n");
        owrite.write(searchLog.toString());
        owrite.flush();
    }
}
