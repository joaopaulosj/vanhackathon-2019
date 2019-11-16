package br.com.mobile2you.m2ybase.utils.helpers;

import java.util.Locale;

import br.com.mobile2you.m2ybase.utils.base.BaseDateFormatHelper;

public class DateFormatHelper extends BaseDateFormatHelper {
    public DateFormatHelper(String dateFormat) {
        super(dateFormat);
    }

    @Override
    public Locale getLocale() {
        return Locale.getDefault();
    }
}
