package com.brazoft.foundation.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public enum DateTimeFormat {
  DATE {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
    }
  },
  DATETIME {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
    }
  },
  EXTENSIVE_DATE {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateInstance(DateFormat.FULL, locale);
    }
  },
  EXTENSIVE_DATETIME {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, locale);
    }
  },
  LONG_DATE {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateInstance(DateFormat.LONG, locale);
    }
  },
  LONG_DATETIME {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    }
  },
  LONG_TIME {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getTimeInstance(DateFormat.LONG, locale);
    }
  },
  SHORT_DATE {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateInstance(DateFormat.SHORT, locale);
    }
  },
  SHORT_DATETIME {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale);
    }
  },
  SHORT_TIME {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getTimeInstance(DateFormat.SHORT, locale);
    }
  },
  TIME {

    @Override
    protected DateFormat getFormat(Locale locale) {
      return DateFormat.getTimeInstance(DateFormat.MEDIUM, locale);
    }
  };

  public String toString(Calendar value) {
    return this.toString(value.getTime());
  }

  public String toString(Date value) {
    return this.toString(value, Locale.getDefault());
  }

  public String toString(Calendar value, Locale locale) {
    return this.toString(value.getTime(), locale);
  }

  public String toString(Date value, Locale locale) {
    return this.getFormat(locale).format(value);
  }

  public Calendar toCalendar(String value) {
    return Converter.toCalendar(this.toDate(value));
  }

  public Date toDate(String value) {
    return this.toDate(value, Locale.getDefault());
  }

  public Calendar toCalendar(String value, Locale locale) {
    return Converter.toCalendar(this.toDate(value, locale));
  }

  public Date toDate(String value, Locale locale) {
    try {
      return this.getFormat(locale).parse(value);
    } catch (ParseException e) {
      ExceptionHandler.handleRuntime(e);
      return null;
    }
  }

  public String getPattern() {
    return this.getPattern(Locale.getDefault());
  }

  public String getPattern(Locale locale) {
    return ((SimpleDateFormat) this.getFormat(locale)).toPattern();
  }

  protected abstract DateFormat getFormat(Locale locale);
}