import java.util.*;
import java.io.*;
/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 20/2/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */

public class Solver {
    LinkedList<State> visitOrder;
    LinkedList<State> visited;
    Collector<State> frontierList;
    Problem problem;
    float solTime;
    Algorithm solutionAlgo;
    Comparator<State> comparator;

    public Solver(Problem p, Algorithm alg){
        visitOrder = new LinkedList<State>();
        visited = new LinkedList<State> ();
        solTime = 0f;
        problem = p;
        solutionAlgo = alg;
        frontierList = new Collector<State>(solutionAlgo);
    }
    public boolean solve() throws IOException{
        float curSpeed = problem.initialSpeed;
        frontierList.add(new State(problem.start,curSpeed,0,hCost(problem.start,curSpeed)));
        State curState = null;
        //solTime = 0 - 1/curSpeed; //since time = 0 at start
        int niter = 0;
        float g,f;
        while (!frontierList.isEmpty()){
            curState = frontierList.poll();
            curSpeed = curState.speedAtLocation;
            if(Math.abs(curSpeed-0)< Search.doubleEpsilon || curSpeed <= 0)
                continue;
            g=curState.g;
            f=curState.f;
            niter++;
            //visit
            visitOrder.add(curState);
            visited.add(curState);
            //g += 1/curSpeed;
            String stateLogString= curState.toLogString();
            //Logger.appendStateLog(stateLogString);
            if(curState.stateLocation.equals(problem.goal)) {
                Logger.nIter = niter-1;
                LinkedList<State> path = new LinkedList<State>();
                State cstate=curState;
                while(cstate!=null){
                    path.add(cstate);
                    cstate = cstate.parent;
                }
                Collections.reverse(path);
                for(State st:path) {
                    Logger.appendStateLog(st.toLogString());
                }
                Logger.pathLength = path.size();
                return true;
            }
            //expand
            StringBuilder searchLogSB = new StringBuilder("Iteration = "+niter+"\n"+"Current Node: " + stateLogString+"\n");
            searchLogSB.append("Child List:\n");
            //System.out.println(String.format("Expanding %d %d",curState.stateLocation.xcood,curState.stateLocation.ycood));
            LinkedList<Location> children= expand(curState.stateLocation);
            int childIndex = 0;
            if(children!=null) {
                for(Location child:children) {
                    searchLogSB.append(String.format("index = %d %s\n",childIndex++,new State(child,curSpeed,(g+1/curSpeed),(g+1/curSpeed)+ hCost(child,curSpeed)).toLogString()));
                    int visitflag = 0;
                    if(problem.mud.contains(child)) {
                        for(State vis:visited){
                            if (vis.hasBeenBefore(new State(child,curSpeed-problem.decrementalSpeedReductionOnMud,0,0)))  {
                                visitflag =1 ;
                                break;
                            }
                        }
                        if(visitflag==0){
                            for(State vis:frontierList.collections){
                                if (vis.hasBeenBefore(new State(child,curSpeed-problem.decrementalSpeedReductionOnMud,0,0)))  {
                                    visitflag =1 ;
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        for(State vis:visited){
                            if (vis.hasBeenBefore(new State(child,curSpeed,0,0)))  {
                                visitflag =1 ;
                                break;
                            }
                        }
                        if(visitflag==0){
                            for(State vis:frontierList.collections){
                                if (vis.hasBeenBefore(new State(child,curSpeed,0,0)))  {
                                    visitflag =1 ;
                                    break;
                                }
                            }
                        }
                    }
                    if(visitflag==0) {
                        State tstate;
                        float h = hCost(child,curSpeed);
                        if(problem.mud.contains(child))  {
                            tstate = new State(child,curSpeed-problem.decrementalSpeedReductionOnMud,(g+1/curSpeed),(g+1/curSpeed)+h);
                        }
                        else{
                            tstate = new State(child,curSpeed,(g+1/curSpeed),(g+1/curSpeed)+h);
                        }
                        tstate.parent = curState;
                        frontierList.add(tstate);
                    }
                }
            }
            searchLogSB.append("Frontier List:\n");
            childIndex = 0;
            for(State x:frontierList.collections) {
                 searchLogSB.append(String.format("index = %d %s\n",childIndex++,x.toLogString()));
            }
            if(niter<=Search.iterationsToPrint) {
                Logger.appendSearchLog(searchLogSB.toString());
            }
        }
        Logger.nIter = niter;
        return false;
    }



    LinkedList<Location> expand(Location loc)
    {
        LinkedList<Location> retVal = new LinkedList<Location>();
        retVal.add(new Location(loc.xcood,loc.ycood-1));   //north
        retVal.add(new Location(loc.xcood+1,loc.ycood));   //e
        retVal.add(new Location(loc.xcood,loc.ycood+1));   //s
        retVal.add(new Location(loc.xcood-1,loc.ycood));   //w
        ListIterator<Location> lister= retVal.listIterator();
        while(lister.hasNext()) {
            Location tl = lister.next();
            //System.out.println(Integer.toString(tl.xcood)+Integer.toString(tl.ycood));
            if(tl.xcood < 0 || tl.ycood < 0 || tl.xcood > problem.width || tl.ycood > problem.height )  {
                lister.remove();
            }
            else if(!(problem.mud.contains(tl) || problem.white.contains(tl) || problem.start.equals(tl) || problem.goal.equals(tl))){
                //System.out.println(Integer.toString(tl.xcood)+Integer.toString(tl.ycood));
                lister.remove();
            }
        }
        //Collections.reverse(retVal);
        for(Location tl:problem.white){
            //System.out.println(Integer.toString(tl.xcood)+Integer.toString(tl.ycood));
        }
        return retVal;
    }
    float hCost(Location x,float curSpeed) throws IOException{
        int x1 = x.xcood;
        int y1 = x.ycood;
        int x2 = problem.goal.xcood;
        int y2 = problem.goal.ycood;
        float retVal = 0;
        switch (solutionAlgo) {
            case BFS:
            case DFS:
                return 0;
            case AS1:
            case BS1:
                retVal =  (float)Math.sqrt(Math.pow(x2-x1,2)+Math.pow(y2-y1,2));
                break;
            case AS2:
            case BS2:
                retVal = (float)(Math.abs(x2-x1)+Math.abs(y2-y1));
                break;
            default:
                return 0;
        }
        if(problem.mud.contains(x))
            retVal = retVal/(curSpeed-problem.decrementalSpeedReductionOnMud);
        else
            retVal = retVal/curSpeed;
        return retVal;
    }
}
