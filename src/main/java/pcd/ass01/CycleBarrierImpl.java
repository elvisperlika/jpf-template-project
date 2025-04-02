package pcd.ass01;

public class CycleBarrierImpl extends BarrierImpl {

    public CycleBarrierImpl(int parties) {
        super(parties);
        this.count = 0;
    }

    @Override
    public synchronized void await() {
        broken = false;
        count++;
        if (count == parties) {
            broken = true;
            notifyAll();
            count = 0;
        } else {
            while (count < parties && !broken) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
