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
            System.out.println("Reading line " + i);
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay();
            int month = entry.getMonth();
            int year = entry.getYear();
            System.out.println("Y " + year + ", M " + month + ", D " + day + ", H " + hour);
            hourCounts[hour]++;
            dayCounts[day]++;
            monthCounts[month]++;
            yearCounts[year]++;
        }
        System.out.println("File read finished!");
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
    
    public void quietestDay()
    {
        int min = dayCounts[0];
        int minday = 0;
        for(int day = 1; day < dayCounts.length; day++) {
            //System.out.println("Day : " + day + ", Value : " + dayCounts[day]);
            if(dayCounts[day] < min)
            {
                min = dayCounts[day];                
                minday = day;
                //System.out.println("Min changed");
            }
        }
        System.out.println("The quietest day is " + minday + " with " + min + " accesses.");
    }
    
    public void busiestDay()
    {
        int max = dayCounts[0];
        int maxday = 0;
        for(int day = 1; day < dayCounts.length; day++) {
            //System.out.println("Day : " + day + ", Value : " + dayCounts[day]);
            if(dayCounts[day] > max)
            {
                max = dayCounts[day];                
                maxday = day;
                //System.out.println("Max changed");
            }
        }
        System.out.println("The buisest day is " + maxday + " with " + max + " accesses.");
    }
    
    public void totalAccessesPerMonth()
    {
        System.out.println("Month : Count");
        for(int month = 1; month < monthCounts.length; month++) {
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
    
    public void averageAccessesPerMonth()
    {
        //System.out.println("Month : Count");
        int total = 0;
        for(int month = 1; month < monthCounts.length; month++) {
            //System.out.println(month + ": " + monthCounts[month]);
            total += monthCounts[month];
        }
        total /= (monthCounts.length - 1);
        System.out.println("The average accesses per month is " + total);
        
    }
    
    public void busiestMonth()
    {
        int max = monthCounts[1];
        int maxmonth = 1;
        for(int month = 2; month < monthCounts.length; month++) {
            //System.out.println("Day : " + day + ", Value : " + dayCounts[day]);
            if(monthCounts[month] > max)
            {
                max = monthCounts[month];                
                maxmonth = month;
                //System.out.println("Max changed");
            }
        }
        System.out.println("The buisest month is " + maxmonth + " with " + max + " accesses.");
    }
    
    public void quietestMonth()
    {
        int min = monthCounts[1];
        System.out.println("Min : " + min);
        int minmonth = 1;
        for(int month = 2; month < monthCounts.length; month++) {
            System.out.println("Month : " + month + ", Value : " + monthCounts[month]);
            if(monthCounts[month] < min)
            {
                min = monthCounts[month];                
                minmonth = month;
                System.out.println("Max changed");
            }
        }
        System.out.println("The buisest month is " + minmonth + " with " + min + " accesses.");
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
