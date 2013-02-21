import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 20/2/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
enum Algorithm {
    BFS,DFS,AS1,AS2
}
public class Solver {
    LinkedList<State> visitOrder;
    Hashtable<reducedState,Integer> visited;
    PriorityQueue<State> frontierList;
    Problem problem;
    double solTime;
    Algorithm solutionAlgo;
    public Solver(Problem p, Algorithm alg){
        visitOrder = new LinkedList<State>();
        visited = new Hashtable<reducedState, Integer>();
        solTime = 0;
        problem = p;
        solutionAlgo = alg;
        switch (solutionAlgo){
            case BFS:
                frontierList = new PriorityQueue<State>(500,(Comparator)new BFSComparator());
                break;
            case DFS:
                frontierList = new PriorityQueue<State>(500,(Comparator)new DFSComparator());
                break;
            default:
                System.out.println("unknown Algo");
                System.exit(-1);
        }
    }
    public boolean solve() {
        double curSpeed = problem.initialSpeed;
        frontierList.add(new State(problem.start,curSpeed,0,0));
        State curState = null;
        //solTime = 0 - 1/curSpeed; //since time = 0 at start
        int niter = 0;
        double g,f;
        while (!frontierList.isEmpty()){
            curState = frontierList.poll();
            curSpeed = curState.speedAtLocation;
            g=curState.g;
            f=curState.f;
            niter++;
            //visit
            visitOrder.add(curState);
            visited.put(new reducedState(curState.stateLocation, curState.speedAtLocation), 1);
            //g += 1/curSpeed;
            String stateLogString= curState.toLogString();
            Logger.appendStateLog(stateLogString);
            if(curState.stateLocation == problem.goal) {
                return true;
            }
            if(problem.mud.contains(curState.stateLocation)) {
                curSpeed -= problem.decrementalSpeedReductionOnMud;
                if(curSpeed<=0)
                    continue;
            }

            //expand
            StringBuilder searchLogSB = new StringBuilder("Iteration = "+niter+"\n"+"Current Node: " + stateLogString+"\n");
            searchLogSB.append("Child List:\n");
            LinkedList<Location> children= expand(curState.stateLocation);
            int childIndex = 0;
            if(children!=null) {
                for(Location child:children) {
                    searchLogSB.append(String.format("index = %d %s\n",childIndex++,new State(child,curSpeed,(g+1/curSpeed),(g+1/curSpeed)+hcost(child)).toLogString()));
                    if(visited.containsKey(new reducedState(child,curSpeed))) {
                          continue;
                    }
                    frontierList.add(new State(child,curSpeed,(g+1/curSpeed),(g+1/curSpeed)+hcost(child)));
                }
            }
            searchLogSB.append("Frontier List:\n");
            childIndex = 0;
            for(State x:frontierList) {
                 searchLogSB.append(String.format("index = %d %s\n",childIndex++,x.toLogString()));
            }
        }
        return false;
    }



    LinkedList<Location> expand(Location loc)
    {
        LinkedList<Location> retVal = new LinkedList<Location>();
        retVal.add(new Location(loc.xcood,loc.ycood-1));   //north
        retVal.add(new Location(loc.xcood+1,loc.ycood));   //e
        retVal.add(new Location(loc.xcood,loc.ycood+1));   //s
        retVal.add(new Location(loc.xcood-1,loc.ycood));   //w
        for(Location tl:retVal) {
            if(tl.xcood < 0 || tl.ycood < 0 || tl.xcood > problem.width || tl.ycood > problem.height )
                retVal.remove(tl);
            if(!(problem.mud.contains(tl) || problem.white.contains(tl)))
               retVal.remove(tl);
        }
        //Collections.reverse(retVal);
        return retVal;
    }
    double hcost(Location x){
        switch (solutionAlgo) {
            case BFS:
            case DFS:
                return 0;
            default:
                return 0;
        }
    }
}
