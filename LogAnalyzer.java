/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
import java.util.Scanner;

public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    private int[] yearCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    
    Scanner keyboard = new Scanner(System.in);

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        yearCounts = new int[5];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
        this.analyzeData();
    }
    
    public LogAnalyzer(String log)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[29];
        monthCounts = new int[13];
        yearCounts = new int[5];
        // Create the reader to obtain the data.
        reader = new LogfileReader(log);
        this.analyzeData();
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeData()
    {
        int i = 0;
        while(reader.hasNext()) {
            i++;
            System.out.println("line " + i);
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            int month = entry.getMonth();
            System.out.println("month : " + month);
            int year = entry.getYear();
            hourCounts[hour]++;
            dayCounts[day]++;
            monthCounts[month]++;
            yearCounts[year]++;
        }
    }
    
    public void numberOfAccesses()
    {
        int accesses = 0;
        while(reader.hasNext()) {
            accesses += 1;
            LogEntry entry = reader.next();
        }
        System.out.println("This file has been accessed " + accesses + " times.");
    }
    
    public void busiestHour()
    {
        int max = hourCounts[0];
        int maxhour = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) {
            //System.out.println("Hour : " + hour + ", Value : " + hourCounts[hour]);
            if(hourCounts[hour] > max)
            {
                max = hourCounts[hour];                
                maxhour = hour;
                //System.out.println("Max changed");
            }
        }
        System.out.println("The busiest hour is " + maxhour + " with " + max + " accesses.");
    }
    
    public void busiestTwoHour()
    {
        int max = hourCounts[0] + hourCounts[1];
        String maxhours = "0 and 1";
        for(int hour = 2; hour < hourCounts.length; hour += 2) {
            //System.out.println("Hour : " + hour + ", Value : " + hourCounts[hour]);
            if((hourCounts[hour] + hourCounts[hour + 1]) > max)
            {
                max = (hourCounts[hour] + hourCounts[hour + 1]);                
                maxhours = (hour) + " and " + (hour + 1);
                //System.out.println("Max changed");
            }
        }
        System.out.println("The busiest hours are " + maxhours + " with " + max + " accesses.");
    }
    
    public void quietestHour()
    {
        int min = hourCounts[0];
        int minhour = 0;
        for(int hour = 1; hour < hourCounts.length; hour++) {
            //System.out.println("Hour : " + hour + ", Value : " + hourCounts[hour]);
            if(hourCounts[hour] < min)
            {
                min = hourCounts[hour];                
                minhour = hour;
                //System.out.println("Min changed");
            }
        }
        System.out.println("The quietest hour is " + minhour + " with " + min + " accesses.");
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
