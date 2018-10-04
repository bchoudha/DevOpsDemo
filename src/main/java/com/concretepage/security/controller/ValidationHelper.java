package no.difa.eik.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import no.difa.eik.common.config.MessageConstants;
@Slf4j
public class ValidationHelper {
    private ValidationHelper() {
    }

    public static void validateNIN(String ninStr, String formatStr) throws ParseException {
        ninStr=ninStr.substring(0, 6);
        log.info("date string: {}", ninStr);
        int dayPart = Integer.parseInt(ninStr.substring(0, 2));
        log.info("day part: {}", dayPart);
        //if D-Number
        if (dayPart > 40) {
            dayPart = dayPart - 40;
        }
        ninStr=String.valueOf(dayPart).length()==1?"0"+dayPart+ ninStr.substring(2, 6):dayPart + ninStr.substring(2, 6);
        log.info("new date string: {}", ninStr);
        try {
            validateDate(ninStr, formatStr);
        } catch (ParseException e) {
            ParseException parseException= new ParseException(MessageConstants.NIN_VALIDATION_MSG,0);
            parseException.initCause(e);
            throw parseException;
        }

    }

    public static void validateDate(String dateStr, String formatStr) throws ParseException {
        try {
            if (("ISO_DATE_TIME").equals(formatStr)) {
                LocalDate date = getLocalDate(dateStr, DateTimeFormatter.ISO_DATE_TIME);
                log.debug("Valid date:{}", date);
            } else if (("ISO_DATE").equals(formatStr)) {
                LocalDate date = getLocalDate(dateStr, DateTimeFormatter.ISO_DATE);
                log.debug("valid date:{}", date);
            } else {
                SimpleDateFormat format = new SimpleDateFormat(formatStr);
                format.setLenient(false);
                Date date = format.parse(dateStr);
                log.debug("Valid Date:{}", date);
            }
        }
        catch (ParseException|DateTimeParseException p)
        {
            ParseException parseException= new ParseException(MessageConstants.DATE_VALIDATION_MSG,0);
            parseException.initCause(p);
            throw parseException;
        }
    }

    private static LocalDate getLocalDate(String dateStr, DateTimeFormatter isoDateTime) {
        LocalDate dob;
        dob = LocalDate.parse(dateStr, isoDateTime);
        return dob;
    }

    public static boolean isAdult(String dateStr, String formatStr,int adultAge) throws ParseException {
        log.info("dateStr:{}  formatStr:{}  adultAge:{}",dateStr,formatStr,adultAge);
        LocalDate dob=null;
        if(("ISO_DATE_TIME").equals(formatStr))
        {
            dob = getLocalDate(dateStr, DateTimeFormatter.ISO_DATE_TIME);
        }
        else if(("ISO_DATE").equals(formatStr))
        {
            dob = getLocalDate(dateStr, DateTimeFormatter.ISO_DATE);
        }
        else
        {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            format.setLenient(false);
            Date date=format.parse(dateStr);
            dob=date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        log.info("Age is:{}" ,getAge(dob));
        return (getAge(dob)>=adultAge);
    }

    private static int getAge(LocalDate dob) {
        LocalDate curDate = LocalDate.now();
        return Period.between(dob, curDate).getYears();
    }
}
