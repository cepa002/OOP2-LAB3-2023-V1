package guiPowerPlant;

import java.awt.Color;

public abstract class Producer extends Parcel implements Runnable{

    private final int baseTime;
    private Battery battery;
    private Thread thread;

    public Producer(char mark, Color color, int baseTime, Battery battery) {
        super(mark, color);
        this.baseTime = baseTime;
        this.battery = battery;
        thread = new Thread(this);
        thread.start();
    }

    protected abstract int produceEnergy();

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                int productionTime = baseTime + (int)(Math.random()*300);
                Thread.sleep(productionTime);

                int producedEnergy = produceEnergy();
                if (producedEnergy > 0) {
                    battery.chargeBattery(producedEnergy);
                    setForeground(Color.RED);
                }

                // wait and set old Parcel color
                Thread.sleep(300);
                setForeground(Color.WHITE);
            }

        } catch(InterruptedException e) {}
    }

    public synchronized void stopProducer(){
        if(thread == null) return;
        thread.interrupt();
    }

}
