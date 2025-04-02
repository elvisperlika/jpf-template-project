package pcd.ass01;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoidsSimulatorController {

    private final BoidsModel model;
    private final List<Worker> workers = new ArrayList<>();
    private final int CORES = Runtime.getRuntime().availableProcessors();
    private final int N_WORKERS = 2;
    private Monitor managerMonitor = new Monitor();
    private CycleBarrier calculateVelocityCycleBarrier;
    private CycleBarrier updateVelocityCycleBarrier;
    private CycleBarrier updatePositionCycleBarrier;

    public BoidsSimulatorController(BoidsModel model) {
        this.model = model;
        initWorkers();
    }

    private void initWorkers() {
        workers.clear();

        List<List<Boid>> partitions = new ArrayList<>();
        for (int i = 0; i < N_WORKERS; i++) {
            partitions.add(new ArrayList<>());
        }

        int i = 0;
        for (Boid boid : model.getBoids()) {
            if (i == partitions.size()) {
                i = 0;
            }
            partitions.get(i).add(boid);
            i++;
        }

        managerMonitor = new Monitor();
        calculateVelocityCycleBarrier = new CycleBarrierImpl(N_WORKERS);
        updateVelocityCycleBarrier = new CycleBarrierImpl(N_WORKERS);
        updatePositionCycleBarrier = new CycleBarrierImpl(N_WORKERS + 1); // + 1 is the Main Thread

        i = 0;
        for (List<Boid> part : partitions) {
            workers.add(new Worker("W" + i,
                    part,
                    model,
                    managerMonitor,
                    calculateVelocityCycleBarrier,
                    updateVelocityCycleBarrier,
                    updatePositionCycleBarrier
            ));
            i++;
        }
        startWorkers();
    }

    private void startWorkers() {
        workers.forEach(Worker::start);
    }

    public void runSimulation() {
        while (true) {
            managerMonitor.startWork();
            if (updatePositionCycleBarrier.isBrokening()) {
                updatePositionCycleBarrier.await();
            }
        }
    }

}
