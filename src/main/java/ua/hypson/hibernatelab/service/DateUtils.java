package ua.hypson.hibernatelab.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * converter Created by admin on 28.05.2016.
 */
public class DateUtils {

    public int convert(Date birthday) {
        LocalDate localDate = Instant.ofEpochMilli(birthday.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        Period period = Period.between(localDate, now);
        return period.getYears();
    }


    /**
     * @param dateString String in format yyyy-MM-dd
     * @return java.util.Date object parsed out of given string
     */
    public Date parseDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
