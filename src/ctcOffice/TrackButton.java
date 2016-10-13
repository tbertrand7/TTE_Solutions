package ctcOffice;

import javax.swing.*;

/**
 * Created by Tom on 10/12/2016.
 */
public class TrackButton extends JToggleButton
{
    public int line;
    public char section;
    public int block;
    public int length;
    public int grade;
    public int speed;
    public int elevation;
    public int status;

    public TrackButton(String s)
    {
        super(s);
        line = -1;
        section = ' ';
        block = -1;
        length = -1;
        grade = 0;
        speed = 0;
        elevation = 0;
        status = 0;
    }

    public String toString()
    {
        return "Line " + line + " Block " + block;
    }
}
