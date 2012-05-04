/*
 * Created on May 24, 2004
 *
 * Developed by OpenRules Inc 2003-2004
 */
package org.openl.rules.helpers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.ArrayUtils;
import org.openl.rules.testmethod.OpenLUserRuntimeException;
import org.openl.util.ArrayTool;
import org.openl.util.DateTool;
import org.openl.util.math.MathUtils;

/**
 * This class is connected to rules and all these methods can be used from rules.
 * The biggest part of methods is being generated. See org.openl.rules.gen module.
 * 
 * @author snshor
 */
public class RulesUtils {

    public static final String DEFAULT_DOUBLE_FORMAT = "#,##0.00";
    
    public static final double E = Math.E;
    public static final double PI = Math.PI;
    
    // <<< INSERT Functions >>>
	// MAX
	public static java.lang.Byte max(java.lang.Byte[] values) {
        return (java.lang.Byte) MathUtils.max(values);
    }
    
	public static java.lang.Short max(java.lang.Short[] values) {
        return (java.lang.Short) MathUtils.max(values);
    }
    
	public static java.lang.Integer max(java.lang.Integer[] values) {
        return (java.lang.Integer) MathUtils.max(values);
    }
    
	public static java.lang.Long max(java.lang.Long[] values) {
        return (java.lang.Long) MathUtils.max(values);
    }
    
	public static java.lang.Float max(java.lang.Float[] values) {
        return (java.lang.Float) MathUtils.max(values);
    }
    
	public static java.lang.Double max(java.lang.Double[] values) {
        return (java.lang.Double) MathUtils.max(values);
    }
    
	public static java.math.BigInteger max(java.math.BigInteger[] values) {
        return (java.math.BigInteger) MathUtils.max(values);
    }
    
	public static java.math.BigDecimal max(java.math.BigDecimal[] values) {
        return (java.math.BigDecimal) MathUtils.max(values);
    }
    
	public static byte max(byte[] values) {
        return (byte) MathUtils.max(values);
    }
    
	public static short max(short[] values) {
        return (short) MathUtils.max(values);
    }
    
	public static int max(int[] values) {
        return (int) MathUtils.max(values);
    }
    
	public static long max(long[] values) {
        return (long) MathUtils.max(values);
    }
    
	public static float max(float[] values) {
        return (float) MathUtils.max(values);
    }
    
	public static double max(double[] values) {
        return (double) MathUtils.max(values);
    }
    

	// MIN
	public static java.lang.Byte min(java.lang.Byte[] values) {
        return (java.lang.Byte) MathUtils.min(values);
    }
	public static java.lang.Short min(java.lang.Short[] values) {
        return (java.lang.Short) MathUtils.min(values);
    }
	public static java.lang.Integer min(java.lang.Integer[] values) {
        return (java.lang.Integer) MathUtils.min(values);
    }
	public static java.lang.Long min(java.lang.Long[] values) {
        return (java.lang.Long) MathUtils.min(values);
    }
	public static java.lang.Float min(java.lang.Float[] values) {
        return (java.lang.Float) MathUtils.min(values);
    }
	public static java.lang.Double min(java.lang.Double[] values) {
        return (java.lang.Double) MathUtils.min(values);
    }
	public static java.math.BigInteger min(java.math.BigInteger[] values) {
        return (java.math.BigInteger) MathUtils.min(values);
    }
	public static java.math.BigDecimal min(java.math.BigDecimal[] values) {
        return (java.math.BigDecimal) MathUtils.min(values);
    }
	public static byte min(byte[] values) {
        return (byte) MathUtils.min(values);
    }
	public static short min(short[] values) {
        return (short) MathUtils.min(values);
    }
	public static int min(int[] values) {
        return (int) MathUtils.min(values);
    }
	public static long min(long[] values) {
        return (long) MathUtils.min(values);
    }
	public static float min(float[] values) {
        return (float) MathUtils.min(values);
    }
	public static double min(double[] values) {
        return (double) MathUtils.min(values);
    }

	// SUM
	public static java.lang.Byte sum(java.lang.Byte[] values) {
        return (java.lang.Byte) MathUtils.sum(values);
    }
    
	public static java.lang.Short sum(java.lang.Short[] values) {
        return (java.lang.Short) MathUtils.sum(values);
    }
    
	public static java.lang.Integer sum(java.lang.Integer[] values) {
        return (java.lang.Integer) MathUtils.sum(values);
    }
    
	public static java.lang.Long sum(java.lang.Long[] values) {
        return (java.lang.Long) MathUtils.sum(values);
    }
    
	public static java.lang.Float sum(java.lang.Float[] values) {
        return (java.lang.Float) MathUtils.sum(values);
    }
    
	public static java.lang.Double sum(java.lang.Double[] values) {
        return (java.lang.Double) MathUtils.sum(values);
    }
    
	public static java.math.BigInteger sum(java.math.BigInteger[] values) {
        return (java.math.BigInteger) MathUtils.sum(values);
    }
    
	public static java.math.BigDecimal sum(java.math.BigDecimal[] values) {
        return (java.math.BigDecimal) MathUtils.sum(values);
    }
    
	public static byte sum(byte[] values) {
        return (byte) MathUtils.sum(values);
    }
    
	public static short sum(short[] values) {
        return (short) MathUtils.sum(values);
    }
    
	public static int sum(int[] values) {
        return (int) MathUtils.sum(values);
    }
    
	public static long sum(long[] values) {
        return (long) MathUtils.sum(values);
    }
    
	public static float sum(float[] values) {
        return (float) MathUtils.sum(values);
    }
    
	public static double sum(double[] values) {
        return (double) MathUtils.sum(values);
    }
    

	// AVERAGE
	public static java.lang.Byte avg(java.lang.Byte[] values) {
        return MathUtils.avg(values);
    }
    
	public static java.lang.Short avg(java.lang.Short[] values) {
        return MathUtils.avg(values);
    }
    
	public static java.lang.Integer avg(java.lang.Integer[] values) {
        return MathUtils.avg(values);
    }
    
	public static java.lang.Long avg(java.lang.Long[] values) {
        return MathUtils.avg(values);
    }
    
	public static java.lang.Float avg(java.lang.Float[] values) {
        return MathUtils.avg(values);
    }
    
	public static java.lang.Double avg(java.lang.Double[] values) {
        return MathUtils.avg(values);
    }
    
	public static java.math.BigInteger avg(java.math.BigInteger[] values) {
        return MathUtils.avg(values);
    }
    
	public static java.math.BigDecimal avg(java.math.BigDecimal[] values) {
        return MathUtils.avg(values);
    }
    
	public static byte avg(byte[] values) {
        return MathUtils.avg(values);
    }
    
	public static short avg(short[] values) {
        return MathUtils.avg(values);
    }
    
	public static int avg(int[] values) {
        return MathUtils.avg(values);
    }
    
	public static long avg(long[] values) {
        return MathUtils.avg(values);
    }
    
	public static float avg(float[] values) {
        return MathUtils.avg(values);
    }
    
	public static double avg(double[] values) {
        return MathUtils.avg(values);
    }
    

	// SMALL
	public static java.lang.Byte small(java.lang.Byte[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static java.lang.Short small(java.lang.Short[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static java.lang.Integer small(java.lang.Integer[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static java.lang.Long small(java.lang.Long[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static java.lang.Float small(java.lang.Float[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static java.lang.Double small(java.lang.Double[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static java.math.BigInteger small(java.math.BigInteger[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static java.math.BigDecimal small(java.math.BigDecimal[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static byte small(byte[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static short small(short[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static int small(int[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static long small(long[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static float small(float[] values, int position) {
        return MathUtils.small(values, position);
    }
    
	public static double small(double[] values, int position) {
        return MathUtils.small(values, position);
    }
    

	// BIG
	public static java.lang.Byte big(java.lang.Byte[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static java.lang.Short big(java.lang.Short[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static java.lang.Integer big(java.lang.Integer[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static java.lang.Long big(java.lang.Long[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static java.lang.Float big(java.lang.Float[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static java.lang.Double big(java.lang.Double[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static java.math.BigInteger big(java.math.BigInteger[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static java.math.BigDecimal big(java.math.BigDecimal[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static byte big(byte[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static short big(short[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static int big(int[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static long big(long[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static float big(float[] values, int position) {
        return MathUtils.big(values, position);
    }
    
	public static double big(double[] values, int position) {
        return MathUtils.big(values, position);
    }
    

	// MEDIAN
	public static java.lang.Byte median(java.lang.Byte[] values) {
        return MathUtils.median(values);
    } 
    
	public static java.lang.Short median(java.lang.Short[] values) {
        return MathUtils.median(values);
    } 
    
	public static java.lang.Integer median(java.lang.Integer[] values) {
        return MathUtils.median(values);
    } 
    
	public static java.lang.Long median(java.lang.Long[] values) {
        return MathUtils.median(values);
    } 
    
	public static java.lang.Float median(java.lang.Float[] values) {
        return MathUtils.median(values);
    } 
    
	public static java.lang.Double median(java.lang.Double[] values) {
        return MathUtils.median(values);
    } 
    
	public static java.math.BigInteger median(java.math.BigInteger[] values) {
        return MathUtils.median(values);
    } 
    
	public static java.math.BigDecimal median(java.math.BigDecimal[] values) {
        return MathUtils.median(values);
    } 
    
	public static byte median(byte[] values) {
        return MathUtils.median(values);
    } 
    
	public static short median(short[] values) {
        return MathUtils.median(values);
    } 
    
	public static int median(int[] values) {
        return MathUtils.median(values);
    } 
    
	public static long median(long[] values) {
        return MathUtils.median(values);
    } 
    
	public static float median(float[] values) {
        return MathUtils.median(values);
    } 
    
	public static double median(double[] values) {
        return MathUtils.median(values);
    } 
    

	// QUAOTIENT
	public static long quotient(java.lang.Byte number, java.lang.Byte divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(java.lang.Short number, java.lang.Short divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(java.lang.Integer number, java.lang.Integer divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(java.lang.Long number, java.lang.Long divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(java.lang.Float number, java.lang.Float divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(java.lang.Double number, java.lang.Double divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(java.math.BigInteger number, java.math.BigInteger divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(java.math.BigDecimal number, java.math.BigDecimal divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(byte number, byte divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(short number, short divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(int number, int divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(long number, long divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(float number, float divisor) {
        return MathUtils.quotient(number, divisor);
    }
    
	public static long quotient(double number, double divisor) {
        return MathUtils.quotient(number, divisor);
    }
    

	//MOD as in Excel
    public static java.lang.Byte mod(java.lang.Byte number, java.lang.Byte divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static java.lang.Short mod(java.lang.Short number, java.lang.Short divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static java.lang.Integer mod(java.lang.Integer number, java.lang.Integer divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static java.lang.Long mod(java.lang.Long number, java.lang.Long divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static java.lang.Float mod(java.lang.Float number, java.lang.Float divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static java.lang.Double mod(java.lang.Double number, java.lang.Double divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static java.math.BigInteger mod(java.math.BigInteger number, java.math.BigInteger divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static java.math.BigDecimal mod(java.math.BigDecimal number, java.math.BigDecimal divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static byte mod(byte number, byte divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static short mod(short number, short divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static int mod(int number, int divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static long mod(long number, long divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static float mod(float number, float divisor) {
        return MathUtils.mod(number, divisor);
    }	
    
    public static double mod(double number, double divisor) {
        return MathUtils.mod(number, divisor);
    }	
    

	// SLICE
    public static java.lang.Byte[] slice(java.lang.Byte[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.lang.Byte[] slice(java.lang.Byte[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static java.lang.Short[] slice(java.lang.Short[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.lang.Short[] slice(java.lang.Short[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static java.lang.Integer[] slice(java.lang.Integer[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.lang.Integer[] slice(java.lang.Integer[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static java.lang.Long[] slice(java.lang.Long[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.lang.Long[] slice(java.lang.Long[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static java.lang.Float[] slice(java.lang.Float[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.lang.Float[] slice(java.lang.Float[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static java.lang.Double[] slice(java.lang.Double[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.lang.Double[] slice(java.lang.Double[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static java.math.BigInteger[] slice(java.math.BigInteger[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.math.BigInteger[] slice(java.math.BigInteger[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static java.math.BigDecimal[] slice(java.math.BigDecimal[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static java.math.BigDecimal[] slice(java.math.BigDecimal[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static byte[] slice(byte[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static byte[] slice(byte[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static short[] slice(short[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static short[] slice(short[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static int[] slice(int[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static int[] slice(int[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static long[] slice(long[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static long[] slice(long[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static float[] slice(float[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static float[] slice(float[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    
    public static double[] slice(double[] values, int startIndexInclusive) {
        return MathUtils.slice(values, startIndexInclusive);
    }	
    
    public static double[] slice(double[] values, int startIndexInclusive, int endIndexExclusive) {
    	return MathUtils.slice(values, startIndexInclusive, endIndexExclusive);
    }	
    

	// SORT
	public static java.lang.Byte[] sort(java.lang.Byte[] values) {
        return MathUtils.sort(values);
    }
    
	public static java.lang.Short[] sort(java.lang.Short[] values) {
        return MathUtils.sort(values);
    }
    
	public static java.lang.Integer[] sort(java.lang.Integer[] values) {
        return MathUtils.sort(values);
    }
    
	public static java.lang.Long[] sort(java.lang.Long[] values) {
        return MathUtils.sort(values);
    }
    
	public static java.lang.Float[] sort(java.lang.Float[] values) {
        return MathUtils.sort(values);
    }
    
	public static java.lang.Double[] sort(java.lang.Double[] values) {
        return MathUtils.sort(values);
    }
    
	public static java.math.BigInteger[] sort(java.math.BigInteger[] values) {
        return MathUtils.sort(values);
    }
    
	public static java.math.BigDecimal[] sort(java.math.BigDecimal[] values) {
        return MathUtils.sort(values);
    }
    
	public static byte[] sort(byte[] values) {
        return MathUtils.sort(values);
    }
    
	public static short[] sort(short[] values) {
        return MathUtils.sort(values);
    }
    
	public static int[] sort(int[] values) {
        return MathUtils.sort(values);
    }
    
	public static long[] sort(long[] values) {
        return MathUtils.sort(values);
    }
    
	public static float[] sort(float[] values) {
        return MathUtils.sort(values);
    }
    
	public static double[] sort(double[] values) {
        return MathUtils.sort(values);
    }
    
    
	
    
    
					// <<< END INSERT Functions >>> 

    public static boolean contains(Object[] array, Object obj) {
        return ArrayUtils.contains(array, obj);
    }

    public static boolean contains(int[] array, int elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(long[] array, long elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(byte[] array, byte elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(short[] array, short elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(char[] array, char elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(float[] array, float elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(double[] array, double elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(boolean[] array, boolean elem) {
        return ArrayUtils.contains(array, elem);
    }

    public static boolean contains(Object[] ary1, Object[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(int[] ary1, int[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(byte[] ary1, byte[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(short[] ary1, short[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(long[] ary1, long[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(char[] ary1, char[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(float[] ary1, float[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(String[] ary1, String[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(double[] ary1, double[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

    public static boolean contains(boolean[] ary1, boolean[] ary2) {
        return ArrayTool.containsAll(ary1, ary2);
    }

//
    public static int indexOf(Object[] array, Object obj) {
        return ArrayUtils.indexOf(array, obj);
    }

    public static int indexOf(int[] array, int elem) {
        return ArrayUtils.indexOf(array, elem);
    }

    public static int indexOf(long[] array, long elem) {
        return ArrayUtils.indexOf(array, elem);
    }

    public static int indexOf(byte[] array, byte elem) {
        return ArrayUtils.indexOf(array, elem);
    }

    public static int indexOf(short[] array, short elem) {
        return ArrayUtils.indexOf(array, elem);
    }

    public static int indexOf(char[] array, char elem) {
        return ArrayUtils.indexOf(array, elem);
    }

    public static int indexOf(float[] array, float elem) {
        return ArrayUtils.indexOf(array, elem);
    }

    public static int indexOf(double[] array, double elem) {
        return ArrayUtils.indexOf(array, elem);
    }

    public static int indexOf(boolean[] array, boolean elem) {
        return ArrayUtils.indexOf(array, elem);
    }
    
    public static boolean noNulls(Object[] values) {
        return ArrayTool.noNulls(values);
    }

    public static void error(String msg) {
        throw new OpenLUserRuntimeException(msg);
    }

    public static void error(Throwable t) throws Throwable {
        throw new OpenLUserRuntimeException(t);
    }

    public static String format(Date date) {
        return format(date, null);
    }

    public static String format(Date date, String format) {
        DateFormat df = format == null ? DateFormat.getDateInstance(DateFormat.SHORT) : new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String format(double d) {
        return format(d, DEFAULT_DOUBLE_FORMAT);
    }

    public static String format(double d, String fmt) {
        DecimalFormat df = new DecimalFormat(fmt);
        return df.format(d);
    }

    public static String[] intersection(String[] ary1, String[] ary2) {
        return ArrayTool.intersection(ary1, ary2);
    }

    public static void out(String output) {
        System.out.println(output);
    }
    
    public static void out(Object output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(byte output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(short output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(int output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(long output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(float output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(double output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(char output) {
        System.out.println(String.valueOf(output));
    }
    
    public static void out(boolean output) {
        System.out.println(String.valueOf(output));
    }
    
    /**
     * Parse the represented string value to the double. 
     * Uses default Locale for it.
     * 
     * Shouldn`t be used.
     */
    @Deprecated
    public static double parseFormattedDouble(String s) throws ParseException {
        return parseFormattedDouble(s, DEFAULT_DOUBLE_FORMAT);
    }
    
    /**
     * Parse the represented string value to the double. 
     * Uses default Locale for it. See {@link DecimalFormat#DecimalFormat(String)}
     * 
     * Shouldn`t be used.
     */
    @Deprecated
    public static double parseFormattedDouble(String s, String fmt) throws ParseException {
        DecimalFormat df = new DecimalFormat(fmt);
        return df.parse(s).doubleValue();
    }
    
    public static int absMonth(Date d) {
        return DateTool.absMonth(d);
    }

    public static int absQuarter(Date d) {
        return DateTool.absQuarter(d);
    }

    public static int dayDiff(Date d1, Date d2) {
        return DateTool.dayDiff(d1, d2);
    }

    public static int dayOfMonth(Date d) {
        return DateTool.dayOfMonth(d);
    }

    public static Date firstDateOfQuarter(int absQuarter) {
        return DateTool.firstDateOfQuarter(absQuarter);
    }

    public static Date lastDateOfQuarter(int absQuarter) {
        return DateTool.lastDateOfQuarter(absQuarter);
    }

    public static int lastDayOfMonth(Date d) {
        return DateTool.lastDayOfMonth(d);
    }

    public static int month(Date d) {
        return DateTool.month(d);
    }

    public static int monthDiff(Date d1, Date d2) {
        return DateTool.monthDiff(d1, d2);
    }
    
    public static int yearDiff(Date d1, Date d2) {
        return DateTool.yearDiff(d1, d2);
    }
    
    public static int weekDiff(Date d1, Date d2) {
        return DateTool.weekDiff(d1, d2);
    }

    public static int quarter(Date d) {
        return DateTool.quarter(d);
    }

    public static int year(Date d) {
        return DateTool.year(d);
    }
    
    // Math functions
    
    //PRODUCT    
    public static double product(byte[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(short[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(int[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(long[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(float[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(double[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(Byte[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(Short[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(Integer[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(Long[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(Float[] values) {
        return MathUtils.product(values);
    }
    
    public static double product(Double[] values) {
        return MathUtils.product(values);
    }
    public static BigInteger product(BigInteger[] values) {
        return MathUtils.product(values);
    }
    
    public static BigDecimal product(BigDecimal[] values) {
        return MathUtils.product(values);
    }
    
    // logical AND
    public static boolean allTrue(boolean[] values) {
        return org.openl.util.BooleanUtils.and(values);
    }
    
    public static boolean allTrue(Boolean[] values) {
        return org.openl.util.BooleanUtils.and(values);
    }
    
    // Exclusive or
    public static boolean xor(boolean[] values) {        
        return org.openl.util.BooleanUtils.xor(values);
    }
    
    public static boolean xor(Boolean[] values) {        
        return org.openl.util.BooleanUtils.xor(values);
    }
    
    // or
    public static boolean anyTrue(boolean[] values) {        
        return org.openl.util.BooleanUtils.or(values);
    }
    
    public static boolean anyTrue(Boolean[] values) {        
        return org.openl.util.BooleanUtils.or(values);
    }
    
    public static long round(double value) {
        return Math.round(value);
    }
    
    public static int round(float value) {
        return Math.round(value);
    }
    
    public static double round(double value, int scale) {
        return org.apache.commons.math.util.MathUtils.round(value, scale);
    }
    
    public static float round(float value, int scale) {
        return org.apache.commons.math.util.MathUtils.round(value, scale);
    }
    
    public static double round(double value, int scale, int roundingMethod) {
        return org.apache.commons.math.util.MathUtils.round(value, scale, roundingMethod);
    }
    
    public static float round(float value, int scale, int roundingMethod) {
        return org.apache.commons.math.util.MathUtils.round(value, scale, roundingMethod);
    }
    
    // added for BA`s, who don`t know about the possibilities of
    // BigDecimal
    public static BigDecimal round(BigDecimal value) {        
        return round(value, 0);
    }
    
    // added for BA`s, who don`t know about the possibilities of
    // BigDecimal
    public static BigDecimal round(BigDecimal value, int scale) {
        return round(value, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    // added for BA`s, who don`t know about the possibilities of
    // BigDecimal
    public static BigDecimal round(BigDecimal value, int scale, int roundingMethod) {
        return value.setScale(scale, roundingMethod);
    }
    
    public static Object[] removeNulls(Object[] array) {
    	return ArrayTool.removeNulls(array);
    }
    
  //Delegation Methods from java.lang.Math class
    public static double abs(double a){
    	return Math.abs(a);
    }
    
    public static float abs(float a){
    	return Math.abs(a);
    }
    
    public static int abs(int a){
    	return Math.abs(a);
    }
    
    public static long abs(long a){
    	return Math.abs(a);
    }
    
    public static double acos(double a){
    	return Math.acos(a);
    }
    
    public static double asin(double a){
    	return Math.asin(a);
    }
    
    public static double atan(double a){
    	return Math.atan(a);
    }
    
    public static double atan2(double y, double x){
    	return Math.atan2(y, x);
    }
    
    public static double cbrt(double a){
    	return Math.cbrt(a);
    }
    
    public static double ceil(double a){
    	return Math.ceil(a);
    }
    
    public static double copySign(double magnitude, double sign){
    	return Math.copySign(magnitude, sign);
    }
    
    public static float copySign(float magnitude, float sign){
    	return Math.copySign(magnitude, sign);
    }
    
    public static double cos(double a){
    	return Math.cos(a);
    }
    
    public static double cosh(double x){
    	return Math.cosh(x);
    }
    
    public static double exp(double a){
    	return Math.exp(a);
    }
    
    public static double expm1(double x){
    	return Math.expm1(x);
    }
    
    public static double floor(double a){
    	return Math.floor(a);
    }
    
    public static int getExponent(double d){
    	return Math.getExponent(d);
    }
    
    public static int getExponent(float f){
    	return Math.getExponent(f);
    }
    
    public static double getExponent(double x, double y){
    	return Math.hypot(x, y);
    }
    
    public static double IEEEremainder(double f1, double f2){
    	return Math.IEEEremainder(f1, f2);
    }
    
    public static double log(double a){
    	return Math.log(a);
    }
    
    public static double log10(double a){
    	return Math.log10(a);
    }
    
    public static double log1p(double x){
    	return Math.log1p(x);
    }
    
    public static int max(int a, int b){
    	return Math.max(a, b);
    }
    
    public static double max(double a, double b){
    	return Math.max(a, b);
    }
    
    public static float max(float a, float b){
    	return Math.max(a, b);
    }
    
    public static long max(long a, long b){
    	return Math.max(a, b);
    }
    
    public static int min(int a, int b){
    	return Math.min(a, b);
    }
    
    public static double min(double a, double b){
    	return Math.min(a, b);
    }
    
    public static float min(float a, float b){
    	return Math.min(a, b);
    }
    
    public static long min(long a, long b){
    	return Math.min(a, b);
    }
    
    public static double nextAfter(double start, double direction){
    	return Math.nextAfter(start, direction);
    }
    
    public static float nextAfter(float start, float direction){
    	return Math.nextAfter(start, direction);
    }
    
    public static float nextAfter(float f){
    	return Math.nextUp(f);
    }
    
    public static double nextAfter(double d){
    	return Math.nextUp(d);
    }
    
    public static double pow(double a, double b){
    	return Math.pow(a, b);
    }
    
    public static double random(){
    	return Math.random();
    }
    
    public static double rint(double a){
    	return Math.rint(a);
    }
    
    public static double scalb(double d, int scaleFactor){
    	return Math.scalb(d, scaleFactor);
    }
    
    public static float scalb(float f, int scaleFactor){
    	return Math.scalb(f, scaleFactor);
    }
    
    public static double signum(double d){
    	return Math.signum(d);
    }
    
    public static double signum(float f){
    	return Math.signum(f);
    }
    
    public static double sin(double a){
    	return Math.sin(a);
    }
    
    public static double sinh(double x){
    	return Math.sinh(x);
    }
    
    public static double sqrt(double a){
    	return Math.sqrt(a);
    }
    
    public static double tan(double a){
    	return Math.tan(a);
    }
    
    public static double tanh(double x){
    	return Math.tanh(x);
    }
    
    public static double toDegrees(double angrad){
    	return Math.toDegrees(angrad);
    }
    
    public static double toRadians(double angdeg){
    	return Math.toRadians(angdeg);
    }
    
    public static double ulp(double d){
    	return Math.ulp(d);
    }
    
    public static float ulp(float f){
    	return Math.ulp(f);
    }
    
    //Delegation Methods from org.apache.commons.lang.ArrayUtils class
    public static boolean[] add(boolean[] array, boolean element){
        return ArrayUtils.add(array, element);
    }
    
    public static boolean[] add(boolean[] array, int index, boolean element){
        return ArrayUtils.add(array, index, element);
    }
        
    public static byte[] add(byte[] array, byte element){
        return ArrayUtils.add(array, element);
    }
        
    public static byte[] add(byte[] array, int index, byte element){
        return ArrayUtils.add(array, index, element);
    }
        
    public static char[] add(char[] array, char element){
        return ArrayUtils.add(array, element);
    }
        
    public static char[] add(char[] array, int index, char element){
        return ArrayUtils.add(array, index, element);
    }
        
    public static double[] add(double[] array, double element){
        return ArrayUtils.add(array, element);
    }
       
    public static double[] add(double[] array, int index, double element){
        return ArrayUtils.add(array, index, element);
    }
        
    public static float[] add(float[] array, float element){
        return ArrayUtils.add(array, element);
    }
        
    public static float[] add(float[] array, int index, float element){
        return ArrayUtils.add(array, index, element);
    }
        
    public static int[] add(int[] array, int element){
        return ArrayUtils.add(array, element);
    }
       
    public static int[] add(int[] array, int index, int element){
        return ArrayUtils.add(array, index, element);
    }
       
    public static long[] add(long[] array, int index, long element){
        return ArrayUtils.add(array, index, element);
    }
       
    public static long[] add(long[] array, long element){
        return ArrayUtils.add(array, element);
    }
       
    public static Object[] add(Object[] array, int index, Object element){
        return ArrayUtils.add(array, index, element);
    }
       
    public static Object[] add(Object[] array, Object element){
        return ArrayUtils.add(array, element);
    }
       
    public static short[] add(short[] array, int index, short element){
        return ArrayUtils.add(array, index, element);
    }
        
    public static short[] add(short[] array, short element){
        return ArrayUtils.add(array, element);
    }
    
    public static boolean[] remove(boolean[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static byte[] remove(byte[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static char[] remove(char[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static double[] remove(double[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static float[] remove(float[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static int[] remove(int[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static long[] remove(long[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static Object[] remove(Object[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static short[] remove(short[] array, int index){
        return ArrayUtils.remove(array, index);
    }
    
    public static boolean[] removeElement(boolean[] array, boolean element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static byte[] removeElement(byte[] array, byte element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static char[] removeElement(char[] array, char element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static double[] removeElement(double[] array, double element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static float[] removeElement(float[] array, float element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static int[] removeElement(int[] array, int element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static long[] removeElement(long[] array, long element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static Object[] removeElement(Object[] array, Object element){
        return ArrayUtils.removeElement(array, element);
    }
    
    public static short[] removeElement(short[] array, short element){
        return ArrayUtils.removeElement(array, element);
    }
    
    //Method which ignore null param value
    public static Object[] addArrayElementIgnoreNull(Object[] array, int index, Object element){
        
        if(element != null){
            return ArrayUtils.add(array, index, element);
        }else{
            return array;
        }
    }
       
    public static Object[] addArrayElementIgnoreNull(Object[] array, Object element){
        
        if(element != null){
            return ArrayUtils.add(array, element);
        }else{
            return array;
        }
    }
    
    static boolean[] addAll(boolean[] array1, boolean[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static byte[] addAll(byte[] array1, byte[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static char[] addAll(char[] array1, char[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static double[] addAll(double[] array1, double[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static float[] addAll(float[] array1, float[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static int[] addAll(int[] array1, int[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static long[] addAll(long[] array1, long[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static Object[] addAll(Object[] array1, Object[] array2){
        return ArrayUtils.addAll(array1, array2);
    }
    
    static short[] addAll(short[] array1, short[] array2){
        return ArrayUtils.addAll(array1, array2);
    }

}
