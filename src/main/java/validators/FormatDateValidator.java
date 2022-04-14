package validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FormatDateValidator implements DataValidator {
    private final String dateFormat;

    public FormatDateValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String date) {
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public String getDateFormat() {
        return dateFormat;
    }
}
