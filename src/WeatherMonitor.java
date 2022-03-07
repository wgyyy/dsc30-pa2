/*
    Name: Gaoying Wang
    PID:  A16131629
 */

import java.util.Optional;

/**
 * This class monitors the temperature and returns the number of days
 that day's temperature is higher than.
 * @author Gaoying Wang
 * @since  ${2022-03-04}
 */

public class WeatherMonitor {
    IntStack monitor;
    int[] record;

    public WeatherMonitor() {
        monitor = new IntStack(5);
        record = new int[monitor.capacity()];
    }
    
    public int numDays(int temp) {
        int count=0;
        monitor.push(temp);
        if (record.length < monitor.capacity()) {
            int[] new_record = new int[monitor.capacity()];
            for (int i = 0; i < record.length; i++) {
                new_record[i] = record[i];
            }
            this.record = new_record;
        }
        record[monitor.size()-1] = temp;
        for (int i = 0; i < monitor.size(); i++){
            if (record[monitor.size()-1] > record[i]) {
                count++;
            }
        }
        return count;
    }

}