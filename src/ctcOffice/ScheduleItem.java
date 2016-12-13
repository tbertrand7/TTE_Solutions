package ctcOffice;

import trackModel.TrackBlock;

/**
 * Created by Tom on 11/30/2016.
 * Class for Schedule entries
 */
public class ScheduleItem
{
    public String line;
    public TrackBlock destination;
    public double time;

    public ScheduleItem(String line, TrackBlock destination, double time)
    {
        this.line = line;
        this.destination = destination;
        this.time = time;
    }
}
