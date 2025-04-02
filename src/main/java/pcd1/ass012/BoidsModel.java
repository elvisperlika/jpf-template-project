package pcd1.ass012;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoidsModel {

    private final double width;
    private final double height;
    private final double maxSpeed;
    private final double perceptionRadius;
    private final double avoidRadius;
    private List<Boid> boids;
    private double separationWeight;
    private double alignmentWeight;
    private double cohesionWeight;

    public BoidsModel(int nBoids,
                      double initialSeparationWeight,
                      double initialAlignmentWeight,
                      double initialCohesionWeight,
                      double width,
                      double height,
                      double maxSpeed,
                      double perceptionRadius,
                      double avoidRadius) {
        separationWeight = initialSeparationWeight;
        alignmentWeight = initialAlignmentWeight;
        cohesionWeight = initialCohesionWeight;
        this.width = width;
        this.height = height;
        this.maxSpeed = maxSpeed;
        this.perceptionRadius = perceptionRadius;
        this.avoidRadius = avoidRadius;

        boids = Collections.synchronizedList(generateBoids(nBoids));
    }

    private List<Boid> generateBoids(int nBoids) {
        List<Boid> boids = new ArrayList<>();
        // Random rand = new Random(0);
//        for (int i = 0; i < nBoids; i++) {
//            P2d pos = new P2d(-width / 2 + i * width, -height / 2 + i * height);
//            V2d vel = new V2d(i * maxSpeed / 2 - maxSpeed / 4, i * maxSpeed / 2 - maxSpeed / 4);
//            boids.add(new Boid(pos, vel));
//        }
        return boids;
    }

    public List<Boid> getBoids() {
        return new ArrayList<>(boids);
    }

    public double getMinX() {
        return -width / 2;
    }

    public double getMaxX() {
        return width / 2;
    }

    public double getMinY() {
        return -height / 2;
    }

    public double getMaxY() {
        return height / 2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSeparationWeight() {
        return separationWeight;
    }

    public void setSeparationWeight(double value) {
        this.separationWeight = value;
    }

    public double getCohesionWeight() {
        return cohesionWeight;
    }

    public void setCohesionWeight(double value) {
        this.cohesionWeight = value;
    }

    public double getAlignmentWeight() {
        return alignmentWeight;
    }

    public void setAlignmentWeight(double value) {
        this.alignmentWeight = value;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getAvoidRadius() {
        return avoidRadius;
    }

    public double getPerceptionRadius() {
        return perceptionRadius;
    }

    public void resetBoids(int sizeBoids) {
        boids.clear();
        boids = generateBoids(sizeBoids);
    }
}
