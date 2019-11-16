package br.com.joaopaulosj.vanhackathon2019.utils.helpers;

import java.util.Locale;

import br.com.joaopaulosj.vanhackathon2019.utils.base.BaseDateFormatHelper;

public class DateFormatHelper extends BaseDateFormatHelper {
    public DateFormatHelper(String dateFormat) {
        super(dateFormat);
    }

    @Override
    public Locale getLocale() {
        return Locale.getDefault();
    }
}
