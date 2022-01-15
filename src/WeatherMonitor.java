/*
    Name: Gaoying Wang
    PID:  A16131629
 */

/**
 * This class monitors the temperature and returns the number of days
 that day's temperature is higher than.
 * @author Gaoying Wang
 * @since  ${2022-01-14}
 */

public class WeatherMonitor {
    int[] track=new int[1];
    public WeatherMonitor() {
        this.numDays(3);
    }
    
    public int numDays(int temp) {
        int count=0;
        int[] new_track=new int[track.length];
        for (int x=0;x<track.length;x++){
            new_track[x]=track[x];
        }
        int[] track=new int[new_track.length+1];
        for (int y=0;y<new_track.length;y++){
            track[y]=new_track[y];
        }
        track[new_track.length]=temp;
        for (int z=1;z<track.length;z++){
            if (track[z]>track[z-1]){
                count++;
            }else{
                return 0;
            }
        }
        return count;
    }
    public static void main(String[] args){
        WeatherMonitor sm= new WeatherMonitor();
        sm.numDays(59);
        sm.numDays(60);
        sm.numDays(70);
    }
}