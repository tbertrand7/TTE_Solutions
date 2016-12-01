package ctcOffice;

/**
 * Created by Tom on 11/30/2016.
 * Class for Schedule entries
 */
public class ScheduleItem
{
    public String line;
    public int train;
    public int destination;
    public double time;

    public ScheduleItem(String line, int train, int destination, double time)
    {
        this.line = line;
        this.train = train;
        this.destination = destination;
        this.time = time;
    }
}
