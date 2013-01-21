package org.openl.meta;

import static junit.framework.Assert.*;

import org.junit.Test;

public class TestFloatValue {
    @Test
    public void testRound() {
        FloatValue value1 = new FloatValue(1.23456789f);        
        
        assertEquals("1.2346", FloatValue.round(value1, 4).toString());
        
        assertEquals("1.0", FloatValue.round(value1, 0).toString());
        
        value1 = new FloatValue(12.513456f);
        
        assertEquals("13.0", FloatValue.round(value1, 0).toString());
        
        /**/
        FloatValue v1 = new FloatValue(0.7f);
        FloatValue v2 = new FloatValue(0.75f);
        
        FloatValue res = FloatValue.multiply(v1, v2);
        
        assertEquals("0.53", FloatValue.round(res, 2).toString());
        
        value1 = new FloatValue(12.6666667f);
        assertEquals("12.67", FloatValue.round(value1, 2).toString());
        
        value1 = new FloatValue(12.9999999f);        
        assertEquals("13.0", FloatValue.round(value1, 2).toString());
        
        value1 = new FloatValue(12.6466667f);
        assertEquals("12.65", FloatValue.round(value1, 2).toString());
 
    }
}
    