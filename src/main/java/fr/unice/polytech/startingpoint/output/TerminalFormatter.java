package fr.unice.polytech.startingpoint.output;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class TerminalFormatter extends Formatter {
    // https://koor.fr/Java/Tutorial/java_logging_util.wp
    // this method is called for every log records
    @Override
    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);

        buf.append(formatMessage(rec));

        return buf.toString();
    }
}
