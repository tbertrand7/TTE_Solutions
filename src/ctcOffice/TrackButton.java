package ctcOffice;

import javax.swing.*;
import java.awt.*;

import trackModel.TrackBlock;
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
     * Sets the color of the TrackButton to match the status of the corresponding block.
     * Sets tooltip text if train is present
     * @param block The track block represented by the button
     */
    public void setStatus(TrackBlock block) {
        if (block.status == BlockStatus.UNOCCUPIED) {
            this.setBackground(Color.LIGHT_GRAY);
            this.setToolTipText(null);
        } else if (block.status == BlockStatus.CLOSED) {
            this.setBackground(Color.BLACK);
        } else {
            this.setBackground(Color.BLUE);
            if (block.trainID > 0)
                this.setToolTipText(""+block.trainID);
        }
    }
}
