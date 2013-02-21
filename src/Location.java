/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 8/2/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Location {
    int xcood;
    int ycood;

    public Location(int x, int y) {
        xcood = x;
        ycood = y;
    }

    public int getXcood ( )  {
        return xcood;
    }
    public int getYcood ( )  {
        return ycood;
    }
    public void setXcood(int xc) {
        xcood = xc;
    }
    public void setYcood(int yc) {
        xcood = yc;
    }
}
