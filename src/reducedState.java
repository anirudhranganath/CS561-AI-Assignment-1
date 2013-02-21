/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 21/2/13
 * Time: 11:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class reducedState {
    Location stateLocation;
    double speedAtLocation;

    public reducedState(Location sL,double speed){
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
