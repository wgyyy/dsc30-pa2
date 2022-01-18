/*
    Name: Gaoying Wang
    PID:  A16131629
 */

import java.util.Optional;

/**
 * This class monitors the temperature and returns the number of days
 that day's temperature is higher than.
 * @author Gaoying Wang
 * @since  ${2022-01-14}
 */

public class WeatherMonitor {
    IntStack monitor;

    public WeatherMonitor() {
        monitor=new IntStack(5);
    }
    
    public int numDays(int temp) {
        int count=0;
        if (monitor.isEmpty()){
            monitor.push(temp);
            return 0;
        }else{
            for (int x=0;x<= monitor.p_nElems;x++) {
                if (temp > monitor.track[x]) {
                    count++;
                } else {
                    monitor.push(temp);
                    return 0;
                }
            }
            monitor.push(temp);
            return count-1;
        }
    }

}