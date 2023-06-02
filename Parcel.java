package guiPowerPlant;

import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;

public class Parcel extends Label {

    public Parcel (char mark, Color color){
        super("" + mark);
        setFont(new Font("Serif", Font.BOLD, 14));
        setAlignment(CENTER);
        setForeground(Color.WHITE);
        setBackground(color);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component source = (Component) e.getSource();
                Land land = (Land) source.getParent();
                land.selectParcel(Parcel.this);
            }
        });
    }
    public void setBackgroundColor(Color color){
        setBackground(color);
    }
}
