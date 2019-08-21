//subclass of MusicItem
public class Date {
    private int day;
    private int month;
    private int year;

    //constructor
    public Date(int day, int month, int year) {
        if(day >= 1 && day <= 31){
            this.day = day;
        } else {
            throw new RuntimeException("Invalid date provided");

        }

        if (month < 13 && month > 0){
            this.month = month;
        }else {
            throw new IllegalArgumentException("Check again..!! You entered an invalid month");
        }

        this.year = year;

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
