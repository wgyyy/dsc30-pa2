import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherMonitorTest {

    @Test
    public void numDays() {
        WeatherMonitor test = new WeatherMonitor();
        assertEquals(0, test.numDays(50));
        assertEquals(0, test.numDays(0));
        assertEquals(2, test.numDays(51));
        assertEquals(3, test.numDays(55));
        assertEquals(3, test.numDays(52));
        assertEquals(5, test.numDays(60));
        assertEquals(6, test.numDays(62));
        assertEquals(0, test.numDays(-5));
    }
}