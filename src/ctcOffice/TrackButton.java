package ctcOffice;

import javax.swing.*;
import java.awt.*;
import trackModel.TrackBlock.*;

/**
 * Created by Tom on 10/12/2016.
 */
public class TrackButton extends JToggleButton
{
    public String line;
    public int block;

    public TrackButton(String s)
    {
        super(s);
    }

    public String toString()
    {
        return line + " Line: Block " + block;
    }

    /**
     * Sets the color of the TrackButton to match the status of the corresponding block
     * @param status The status of the track block
     */
    public void setStatus(BlockStatus status) {
        if (status == BlockStatus.UNOCCUPIED) {
            this.setBackground(Color.LIGHT_GRAY);
        } else if (status == BlockStatus.CLOSED) {
            this.setBackground(Color.BLACK);
        } else {
            this.setBackground(Color.BLUE);
        }
    }
}
