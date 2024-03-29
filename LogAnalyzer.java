/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Collie Clarke
 * @version    2024.03.11
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Where to calculate the dayly access counts.
    private int[] dayCounts;
    // Where to calculate the monthly access counts.
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    private String logWord;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String logWord)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(logWord);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Analyze the Dailyy access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeMonthlylyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            this.monthCounts[month]++;
        }
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
    
    /**
    * Return the number of accesses recorded in the log file.
    */
    public int numberOfAccesses()
    {
        int total = 0;
        
        for(int i = 0; i < hourCounts.length; i++)
        {
            total += hourCounts[i];
        }
        
        return total;
    }   
    
    /**
    * Print all the values in the marks array that are
    * greater than mean.
    * @param marks An array of mark values.
    * @param mean The mean (average) mark.
    */
    public void printGreater(double[] marks, double mean)
    {
    for(int index = 0; index < marks.length; index++) {
        if(marks[index] > mean) {
            System.out.println(marks[index]);
            }
        }
    }
    
    /**
     * This will print out and tell you when is the busiest hour.
     */
    public void busiestHour()
    {
        int busy = 0;
        int hour = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(dayCounts[i] > busy)
            {
                busy = hourCounts[i];
                hour = i;
            }
        }
        System.out.println("Busiest hour is " + hour + ".");
    }
    
    /**
     * This will print out and tell you when is the busiest day.
     */
    public void busiestDay()
    {
        int busy = 0;
        int day = 0;
        for(int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] > busy)
            {
                busy = dayCounts[i];
                day = i;
            }
        }
        System.out.println("Busiest hour is " + day + ".");
    }
    
    /**
     * This will print out and tell you when is the busiest month.
     */
    public void busiestMonth()
    {
        int busy = 0;
        int month = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] > busy)
            {
                busy = monthCounts[i];
                month = i;
            }
        }
        System.out.println("Busiest hour is " + month + ".");
    }
    
    /**
     * This will print out and tell you when is the quietest hour.
     */
    public void quietestHour()
    {
        int quiet = hourCounts[0];
        int hour = 0;
        for(int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] > quiet)
            {
                quiet = hourCounts[i];
                hour = i;
            }
        }
        System.out.println("Quietest hour is " + hour + ".");
    }
    
    /**
     * This will print out and tell you when is the quietest hour.
     */
    public void quietestDay()
    {
        int quiet = dayCounts[0];
        int day = 0;
        for(int i = 0; i < dayCounts.length; i++)
        {
            if(dayCounts[i] > quiet)
            {
                quiet = dayCounts[i];
                day = i;
            }
        }
        System.out.println("Quietest day is " + day + ".");
    }
    
    /**
     * This will print out and tell you when is the quietest hour.
     */
    public void quietestMonth()
    {
        int quiet = monthCounts[0];
        int month = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            if(monthCounts[i] > quiet)
            {
                quiet = monthCounts[i];
                month = i;
            }
        }
        System.out.println("Quietest month is " + month + ".");
    }
    
    /**
     * This code will tell you when are the two busiest hours.
     */
    public void busiestTwoHour()
    {
        int busiest = 0;
        int hourOne = 0;
        int hourTwo = 0;
        for (int i = 0; i < hourCounts.length; i++)
        {
            if(hourCounts[i] >= busiest)
            {
                busiest = hourCounts[i];
                hourTwo = hourOne;
                hourOne = i;
            }
        }
        System.out.println("The two busietst hours are " + hourOne + " and " + hourTwo + ".");
    }
    
    /**
     * This code would tell you the total access per month
     */
    public void totalAccessPerMonth()
    {
        System.out.println("Month: Counts");
        int total = 0;
        for(int i = 0; i < monthCounts.length; i++)
        {
            System.out.println(i + ": " + monthCounts[i]);
            total += monthCounts[i];
        }
        System.out.println("Total: "+ total);
    }
    
    /**
     * This code would tell you the total access per month
     */
    public void averageAccessPerMonth()
    {
        System.out.println("Month: Average");
        int total = 0;
        analyzeMonthlylyData();
        for(int i = 0; i < this.monthCounts.length; i++)
        {
            System.out.println(i + ": " + this.monthCounts[i]);
            total += this.monthCounts[i];
        }
        int avg = total / this.monthCounts.length;
        System.out.println("Average: "+ avg);
    }
}
