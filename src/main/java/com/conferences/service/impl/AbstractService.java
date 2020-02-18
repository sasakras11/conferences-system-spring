package com.conferences.service.impl;

import com.conferences.exception.NoSuchElementInDatabaseException;
import com.conferences.exception.OctalNumberParseException;
import com.conferences.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractService<T, R extends JpaRepository<T, Integer>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);

    public T findByIdIfPresentOrRedirect(String id, R repository, String pageIfExceptionHappened) {
        int validatedId = getParsedOctalNumberOrRedirect(id, pageIfExceptionHappened);

        return repository.findById(validatedId).orElseThrow(() ->
                new NoSuchElementInDatabaseException(pageIfExceptionHappened));


    }

    public String getValidatedNameOrRedirect(String value, String pageIfExceptionHappened) {
        if (value.length() < 4) {
            LOGGER.warn(String.format("name [%s] has too small length,should be more then 3", value));
            throw new ValidationException(pageIfExceptionHappened);

        } else {
            return value;
        }
    }


    public int getParsedOctalNumberOrRedirect(String value, String pageIfExceptionHappened) {
        try {
            int num = Integer.parseInt(value);

            if (num > 1_000_000) {
                LOGGER.warn(String.format("number [%s]  has too big value,should be less then 1_000_000", value));
                throw new OctalNumberParseException(pageIfExceptionHappened);
            }
            return num;

        } catch (NumberFormatException e) {
            LOGGER.warn(String.format("string [%s] is not octal number", value));
            throw new OctalNumberParseException(pageIfExceptionHappened);
        }

    }


    public Date getParsedDateOrRedirect(String value, String pageIfExceptionHappened) {
        if (value.length() > 10) {
            throw new ValidationException(pageIfExceptionHappened);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            return dateFormat.parse(value);
        } catch (ParseException e) {
            LOGGER.warn(String.format("date [%s]  has wrong format,Should be yyyy-MM-dd", value));
            throw new ValidationException(pageIfExceptionHappened);

        }
    }

    public int getValidHourOrRedirect(String value, String pageIfExceptionHappened) {
        int hour = getParsedOctalNumberOrRedirect(value, pageIfExceptionHappened);
        if (hour >= 0 && hour < 24) {
            return hour;
        } else {
            LOGGER.warn(String.format("hour [%s] is not valid", value));
            throw new ValidationException(pageIfExceptionHappened);
        }
    }

    public int getValidEndHourOrRedirect(String startHour, String endHour, String pageIfExceptionHappened) {
        int startH = getValidHourOrRedirect(startHour, pageIfExceptionHappened);
        int endH = getValidHourOrRedirect(endHour, pageIfExceptionHappened);
        if (endH > startH) {
            return endH;
        } else {
            LOGGER.warn(String.format("endHour [%s] is not valid, because is greater then startHour - [%s]", endHour, startHour));
            throw new ValidationException(pageIfExceptionHappened);
        }
    }


}
