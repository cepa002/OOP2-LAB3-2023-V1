package guiPowerPlant;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergySystem extends Frame {

    private final Land land;
    private final Battery battery;

    private void populateWindow() {
        add(land, BorderLayout.CENTER);
        Panel topPanel = new Panel();
        Button buttonDodaj = new Button("Dodaj");

        buttonDodaj.addActionListener((ae) -> {
            Producer producer = new HydroPowerPlant(battery);
            int state = land.addProducerToParcel(producer);
            if(state < 0) producer.stopProducer();
        });
        topPanel.add(buttonDodaj);
        add(topPanel, BorderLayout.NORTH);
    }

    public EnergySystem(int rows, int column, int capacity){

        land = new Land(rows, column);
        battery = new Battery(capacity);

        setSize(500, 500);
        setResizable(false);
        setTitle("Energetski Sistem");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                land.shutDown();
            }
        });
        populateWindow();
        setVisible(true);
    }


    public static void main(String[] args) {
        new EnergySystem(5,5,100);
    }
}
