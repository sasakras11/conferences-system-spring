package com.conferences.service.impl;

import com.conferences.exception.NoSuchElementInDatabaseException;
import com.conferences.exception.OctalNumberParseException;
import com.conferences.exception.ValidationException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractService<T, R extends JpaRepository<T, Integer>> {

    public T findByIdIfPresentOrRedirect(String id, R repository, String pageIfExceptionHappened) {
        int validatedId = getParsedOctalNumberOrRedirect(id, pageIfExceptionHappened);

        return repository.findById(validatedId).orElseThrow(() ->
                new NoSuchElementInDatabaseException(pageIfExceptionHappened));
    }

    public String getValidatedNameOrRedirect(String value, String pageIfExceptionHappened) {
        if (value.length() < 4) {
            throw new ValidationException(pageIfExceptionHappened);
        } else {
            return value;
        }
    }

    public int getParsedOctalNumberOrRedirect(String value, String pageIfExceptionHappened) {
        try {
            int num = Integer.parseInt(value);

            if (num > 1_000_000) {
                throw new OctalNumberParseException(pageIfExceptionHappened);
            }
            return num;

        } catch (NumberFormatException e) {
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
            throw new ValidationException(pageIfExceptionHappened);
        }
    }

    public int getValidHourOrRedirect(String value, String pageIfExceptionHappened) {
        int hour = getParsedOctalNumberOrRedirect(value, pageIfExceptionHappened);
        if (hour >= 0 && hour < 24) {
            return hour;
        } else {
            throw new ValidationException(pageIfExceptionHappened);
        }
    }

    public int getValidEndHourOrRedirect(String startHour, String endHour, String pageIfExceptionHappened) {
        int startH = getValidHourOrRedirect(startHour, pageIfExceptionHappened);
        int endH = getValidHourOrRedirect(endHour, pageIfExceptionHappened);
        if (endH > startH) {
            return endH;
        } else {
            throw new ValidationException(pageIfExceptionHappened);
        }
    }
}
