package org.openl.rules.convertor;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.openl.binding.IBindingContext;
import org.openl.util.RuntimeExceptionWrapper;

public class String2DoubleConvertor extends NumberConvertor implements IString2DataConvertor {
    
    private static final String DEFAULT_DOUBLE_FORMAT = "#0.00";

    public String format(Object data, String format) {
        if (format == null) {
            format = DEFAULT_DOUBLE_FORMAT;
        }
        // NOTE!!! Using new DecimalFormat(format), depends on the users locale.
        // E.g. if locale on the users machine is ru_RU, the ','(comma) delimiter will
        // be used. It is not appropriate for many cases, e.g. formatting the value for writing its
        // value to the Java class(Java expects '.' dot delimiter).
        //
        DecimalFormat df = new DecimalFormat(format);
        
        return df.format(((Number) data).doubleValue());
    }

    public Object parse(String xdata, String format, IBindingContext cxt) {

        if (format != null) {
            DecimalFormat df = new DecimalFormat(format);
            try {
                Number n = df.parse(xdata);

                return new Double(n.doubleValue());
            } catch (ParseException e) {
                throw RuntimeExceptionWrapper.wrap("", e);
            }
        }

        String data = numberStringWithoutModifier(xdata);

        double d = Double.parseDouble(data);

        return xdata == data ? new Double(d) : new Double(d * numberModifier(xdata));
    }

}