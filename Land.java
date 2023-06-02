package guiPowerPlant;

import java.awt.Panel;
import java.awt.GridLayout;
import java.awt.Font;
import java.util.ArrayList;

public class Land extends Panel{

    private final int rows, columns;
    private Parcel selectedParcel;
    private final ArrayList<Parcel> parcelGrid = new ArrayList<>();
    private final ArrayList<Producer> producersList = new ArrayList<>();

    public Land(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        setLayout(new GridLayout(rows,columns, 3 ,3));
        fillLand();
    }

    private void fillLand(){
        for(int i = 0; i < rows*columns ; i++){
            Parcel newParcel;
            if(Math.random() >= 0.7) {
                newParcel = new WaterSurface();
                parcelGrid.add(newParcel);
            }
            else{
                newParcel = new GrassSurface();
                parcelGrid.add(newParcel);
            }
            // add to grid
            add(newParcel);
        }
    }

    public void selectParcel(Parcel parcel) {
        if(selectedParcel != null){
            selectedParcel.setFont(new Font("Serif", Font.BOLD, 14));
        }
        parcel.setFont(new Font("Serif", Font.BOLD, 20));
        selectedParcel = parcel;
    }

    public int addProducerToParcel(Producer producer){
        if(selectedParcel == null) return -1;
        if(selectedParcel instanceof Producer) {
            producersList.remove((Producer)selectedParcel);
            ((Producer) selectedParcel).stopProducer();
        }

        int position = parcelGrid.indexOf(selectedParcel);

        parcelGrid.set(position, producer);
        producersList.add(producer);

        signalHydroPowerPlants();

        remove(position);
        add(producer, position);

        selectedParcel.setFont(new Font("Serif", Font.BOLD, 14));
        selectedParcel = null;

        revalidate();

        return 0;
    }

    // proveri je l treba synchronized
    private void signalHydroPowerPlants() {
        for(Producer producer : producersList){
            if(producer.getText().equals("H")){
                updateWaterSurfaceCount((HydroPowerPlant)producer);
            }
        }
    }
    // proveri je l treba synchronized
    private void updateWaterSurfaceCount(HydroPowerPlant powerPlant) {

        // index = i*columns + j
        int position = parcelGrid.indexOf(powerPlant);
        int counter = 0;
        int currRow=position/columns, currCol=position%columns;

        for(int i = currRow-1; i<=currRow+1; i++){
            for(int j= currCol-1; j<=currCol+1; j++){
                if(i>=0 && i<rows && j>=0 && j<columns) {
                    if(parcelGrid.get(i*columns+j).getText().equals("~")) counter++;
                }
            }
        }

        powerPlant.setWaterSurfaceCount(counter);
    }

    public void shutDown() {
        for(Producer producer : producersList) producer.stopProducer();
    }
}



