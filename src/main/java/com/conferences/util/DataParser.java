package com.conferences.util;

import com.conferences.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataParser.class);


    private DataParser(){

    }
    public static Date toDate(String value,String pathIfExceptionHappened){


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            LOGGER.warn(String.format("date [%s]  has wrong format,Should be yyyy-MM-dd", value));
            throw new ValidationException(String.format("date [%s]  has wrong format,Should be yyyy-MM-dd", value));

        }
    }

}
