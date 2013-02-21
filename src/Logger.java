/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 21/2/13
 * Time: 2:34 AM
 * To change this template use File | Settings | File Templates.
 */

import java.lang.*;

public class Logger {
    public static StringBuilder stateLog = new StringBuilder("");
    public static StringBuilder searchLog = new StringBuilder("");
    public int nIter;
    public int pathLength;

    public static void appendStateLog(String x){
        stateLog.append(x+"\n");
    }
}
