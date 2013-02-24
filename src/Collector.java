/**
 * Created with IntelliJ IDEA.
 * User: anirudh
 * Date: 23/2/13
 * Time: 2:36 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;
import java.lang.*;

public class Collector<E> {
    Algorithm mode;
    public LinkedList<E> collections;
    public Collector(Algorithm m) {
        mode = m;
        collections= new LinkedList<E>();
    }

    public void add(E object) {
        switch (mode)      {
            case BFS:
                collections.addLast(object);
                break;
            case DFS:
                collections.addFirst(object);
                break;
        }
    }

    public E poll(){
        return collections.removeFirst();
    }

    public int size() {
        return collections.size();
    }

    public Iterator<E> iterator() {
        return collections.iterator();
    }

    public boolean isEmpty() {
        return collections.isEmpty();
    }

    public boolean contains(E obj) {
        return collections.contains(obj);
    }
}
