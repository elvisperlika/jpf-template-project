package pcd1.ass012;

public interface Barrier {

    void await();

    boolean isBroken();

    void reset();
}
