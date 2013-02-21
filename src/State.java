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

    public State(Location sL,double speed){
        stateLocation = sL;
        speed = speedAtLocation;
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
}
