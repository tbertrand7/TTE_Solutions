package ctcOffice;

import javax.swing.*;

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
}
