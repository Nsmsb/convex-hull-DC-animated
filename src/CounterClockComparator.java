import Dessin.*;
import java.util.Comparator;


public class CounterClockComparator implements Comparator<Point> {

	private Point center;
	
	public CounterClockComparator(Point center)
	{
		this.center = center;
	}

	@Override
	public int compare(Point a, Point b)
	{
		final int BEFORE = -1;
	    final int AFTER = 1;
		
	    if(a.x >= 0 && b.x < 0) return AFTER;
	    else if(a.x == 0 && b.x == 0)
	    {
	    	if (a.y > b.y)
    		{
	    		return AFTER;
    		}

	    	return BEFORE;
	    }
	    
	    double det = (a.x - center.x) * (b.y - center.y) - (b.x - center.x) * (a.y - center.y);
		if (det < 0) return AFTER;
		else if (det > 0) return BEFORE;
	    
	    double d1 = (a.x - center.x) * (a.x - center.x) + (a.y - center.y) * (a.y - center.y);
	    double d2 = (b.x - center.x) * (b.x - center.x) + (b.y - center.y) * (b.y - center.y);
	    
	    if(d1 > d2)
	    {
	    	return AFTER;
	    }
	    
	    return BEFORE;
	}
}
