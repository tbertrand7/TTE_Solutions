package ctcOffice;

import javax.swing.*;

/**
 * Created by Tom on 10/12/2016.
 */
public class TrackButton extends JToggleButton
{
    public String line;
    public char section;
    public int block;
    public int length;
    public int grade;
    public int speed;
    public int elevation;
    public int status;
    public int train;

    public TrackButton(String s)
    {
        super(s);
        line = "Green";
        section = ' ';
        block = 1;
        length = 150;
        grade = 0;
        speed = 0;
        elevation = 0;
        status = 0;
        train = 1;
    }

    public String toString()
    {
        return line + " Line: Block " + block;
    }
}
