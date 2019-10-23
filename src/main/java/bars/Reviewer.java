package bars;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reviewer {
    private String id;
    private Date date;
    private int score;

    public Reviewer(String id, Date date, int score) {
        this.id = id;
        this.date = date;
        this.score = score;
    }

    public Reviewer(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            date = null;
            e.printStackTrace();
        }

        this.date = date;

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
