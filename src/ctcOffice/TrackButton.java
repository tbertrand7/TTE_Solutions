package ctcOffice;

import javax.swing.*;
import java.awt.*;

import trackModel.TrackBlock;
import trackModel.TrackBlock.*;

/**
 * Extension of JToggleButton class for buttons displayed on track
 * Created by Tom on 10/12/2016.
 */
public class TrackButton extends JToggleButton
{
    public String line;
    public int block;
    private boolean warningDisplayed = false; //Flag for displaying warning if block is broken

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

    /**
     * Checks if block may be broken and warning needs displayed
     * @param block The track block represented by the button
     * @return Should warning message be displayed
     */
    public boolean checkWarning(TrackBlock block)
    {
        if (block.status != BlockStatus.UNOCCUPIED && block.status != BlockStatus.CLOSED && block.trainID < 1)
        {
            if (!warningDisplayed) {
                warningDisplayed = true;
                return true;
            }
            return false;
        } else {
          warningDisplayed = false;
          return false;
        }
    }
}
