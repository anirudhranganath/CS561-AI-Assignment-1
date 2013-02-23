/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 20/2/13
 * Time: 6:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class State {
    Location stateLocation;
    double speedAtLocation;
    double g;
    double f;
    public State parent;

    public State(Location sL,double speed,double tg,double tf){
        stateLocation = sL;
        speedAtLocation = speed;
        g=tg;
        f=tf;
        parent = null;
    }

    public Location getStateLocation() {
        return stateLocation;
    }

    public void setStateLocation(Location stateLocation) {
        this.stateLocation = stateLocation;
    }

    public double getSpeedAtLocation() {
        return speedAtLocation;
    }

    public void setSpeedAtLocation(double speedAtLocation) {
        this.speedAtLocation = speedAtLocation;
    }

    public String toLogString(){
        StringBuilder ret = new StringBuilder("x = ");
        ret.append(Integer.toString(stateLocation.xcood)+" y = ");
        ret.append(Integer.toString(stateLocation.ycood)+" speed = ");
        ret.append(Double.toString(speedAtLocation)+" g = ");
        ret.append(Double.toString(g)+" f = ");
        ret.append(Double.toString(f));
        return ret.toString();
    }

    public boolean hasBeenBefore(Object obj) {
        System.out.println("sp" + Double.toString(speedAtLocation - ((State) obj).speedAtLocation));
        return (obj instanceof State
                && stateLocation.equals(((State) obj).stateLocation)
                && (Math.abs(speedAtLocation - ((State) obj).speedAtLocation)<Main.doubleEpsilon));
    }
}
