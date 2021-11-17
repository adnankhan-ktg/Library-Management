package com.intelliatech.LibraryManagement.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.intelliatech.LibraryManagement.constants.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class DateUtil {
    private final Logger logger = LogManager.getLogger(DateUtil.class);
    SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT_YYYY_MM_DD);
    Calendar c = Calendar.getInstance();

    public Date addDaysToDate(Date startDate, String termValue) {
        logger.info("----------------------Start::addDaysToDate----------------------");
        Date endDate = null;
        try {

            c.setTime(sdf.parse(String.valueOf(startDate)));
            c.add(Calendar.DAY_OF_MONTH, Integer.valueOf(termValue));
            String newDate = sdf.format(c.getTime());
            endDate = sdf.parse(newDate);
        } catch (ParseException ex) {
            logger.error("Error: " + ex);
        }
        logger.info("----------------------End::generateAlphanumericOtp----------------------");
        return endDate;
    }



    public long getDifferenceForCurrentDate(long requestedTime) {
        Date currentDate = new Date();
        long currentTimeInMilliseconds = currentDate.getTime();
        long difference = currentTimeInMilliseconds - requestedTime;
        return difference;
    }


    public boolean isValidDate(String dateStr, DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public int dateDifferenceInYears(String date1, String date2) {
        LocalDate d1 = LocalDate.parse(date1);
        LocalDate d2 = LocalDate.parse(date2);
        Period p = Period.between(d2, d1);
        return p.getYears();
    }

}

