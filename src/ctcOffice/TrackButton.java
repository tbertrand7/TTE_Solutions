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
    public double grade;
    public int speed;
    public double elevation;
    public int status;
    public int train;

    public TrackButton(String s)
    {
        super(s);
        line = "Green";
        section = 'A';
        block = 1;
        length = 100;
        grade = 0.5;
        speed = 20;
        elevation = 0.5;
        status = 2;
        train = 1;
    }

    public String toString()
    {
        return line + " Line: Block " + block;
    }
}
