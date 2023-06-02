package guiPowerPlant;

public class Battery {

    private final int capacity;
    private int currEnergy;

    public Battery(int capacity){
        this.capacity = capacity;
        currEnergy = capacity;
    }

    public synchronized void chargeBattery(int energy){
        if (energy == 0) return;
        int newEnergy = currEnergy+energy;
        currEnergy = Math.min(newEnergy, capacity);
    }

    public synchronized void emptyBattery(){
        currEnergy = 0;
    }

    public synchronized boolean isFullyCharged(){
        return capacity == currEnergy;
    }
}
