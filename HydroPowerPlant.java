package guiPowerPlant;

import java.awt.Color;

public class HydroPowerPlant extends Producer{

    private int waterSurfaceCount;
    public HydroPowerPlant(Battery battery) {
        super('H', Color.BLUE, 1500, battery);
    }

    public void setWaterSurfaceCount(int waterCount) { this.waterSurfaceCount = waterCount;}

    @Override
    protected int produceEnergy() {
        return Math.max(waterSurfaceCount, 0);
    }
}
