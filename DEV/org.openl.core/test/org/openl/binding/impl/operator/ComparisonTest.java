package org.openl.binding.impl.operator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ComparisonTest {

    @Test
    public void testFloatEq() {
        final Float nan = Float.NaN;
        final Float inf_pos = Float.POSITIVE_INFINITY;
        final Float inf_neg = Float.NEGATIVE_INFINITY;
        final Float nil = null;
        final Float pos = 1.1f;
        final Float neg = -pos;

        assertTrue(Comparison.eq(pos, pos));
        assertFalse(Comparison.eq(pos, neg));
        assertFalse(Comparison.eq(neg, pos));
        assertTrue(Comparison.eq(neg, neg));
        assertFalse(Comparison.eq(nan, nan));
        assertFalse(Comparison.eq(pos, nan));
        assertFalse(Comparison.eq(inf_pos, nan));
        assertFalse(Comparison.eq(inf_neg, nan));
        assertFalse(Comparison.eq(nan, pos));
        assertFalse(Comparison.eq(nan, inf_pos));
        assertFalse(Comparison.eq(nan, inf_neg));
        assertTrue(Comparison.eq(inf_pos, inf_pos));
        assertTrue(Comparison.eq(inf_neg, inf_neg));
        assertFalse(Comparison.eq(inf_pos, inf_neg));
        assertFalse(Comparison.eq(inf_neg, inf_pos));
        assertFalse(Comparison.eq(pos, inf_pos));
        assertFalse(Comparison.eq(inf_pos, pos));
        assertFalse(Comparison.eq(pos, inf_neg));
        assertFalse(Comparison.eq(inf_neg, pos));
        assertTrue(Comparison.eq(nil, nil));
        assertFalse(Comparison.eq(pos, nil));
        assertFalse(Comparison.eq(nil, pos));
    }

    @Test
    public void testDoubleEq() {
        final Double nan = Double.NaN;
        final Double inf_pos = Double.POSITIVE_INFINITY;
        final Double inf_neg = Double.NEGATIVE_INFINITY;
        final Double nil = null;
        final Double pos = 1.1;
        final Double neg = -pos;

        assertTrue(Comparison.eq(pos, pos));
        assertFalse(Comparison.eq(pos, neg));
        assertFalse(Comparison.eq(neg, pos));
        assertTrue(Comparison.eq(neg, neg));
        assertFalse(Comparison.eq(nan, nan));
        assertFalse(Comparison.eq(pos, nan));
        assertFalse(Comparison.eq(inf_pos, nan));
        assertFalse(Comparison.eq(inf_neg, nan));
        assertFalse(Comparison.eq(nan, pos));
        assertFalse(Comparison.eq(nan, inf_pos));
        assertFalse(Comparison.eq(nan, inf_neg));
        assertTrue(Comparison.eq(inf_pos, inf_pos));
        assertTrue(Comparison.eq(inf_neg, inf_neg));
        assertFalse(Comparison.eq(inf_pos, inf_neg));
        assertFalse(Comparison.eq(inf_neg, inf_pos));
        assertFalse(Comparison.eq(pos, inf_pos));
        assertFalse(Comparison.eq(inf_pos, pos));
        assertFalse(Comparison.eq(pos, inf_neg));
        assertFalse(Comparison.eq(inf_neg, pos));
        assertTrue(Comparison.eq(nil, nil));
        assertFalse(Comparison.eq(pos, nil));
        assertFalse(Comparison.eq(nil, pos));
    }

    @Test
    public void testFloatNe() {
        final Float nan = Float.NaN;
        final Float inf_pos = Float.POSITIVE_INFINITY;
        final Float inf_neg = Float.NEGATIVE_INFINITY;
        final Float nil = null;
        final Float pos = 1.1f;
        final Float neg = -pos;

        assertFalse(Comparison.ne(pos, pos));
        assertTrue(Comparison.ne(pos, neg));
        assertTrue(Comparison.ne(neg, pos));
        assertFalse(Comparison.ne(neg, neg));
        assertTrue(Comparison.ne(nan, nan));
        assertTrue(Comparison.ne(pos, nan));
        assertTrue(Comparison.ne(inf_pos, nan));
        assertTrue(Comparison.ne(inf_neg, nan));
        assertTrue(Comparison.ne(nan, pos));
        assertTrue(Comparison.ne(nan, inf_pos));
        assertTrue(Comparison.ne(nan, inf_neg));
        assertFalse(Comparison.ne(inf_pos, inf_pos));
        assertFalse(Comparison.ne(inf_neg, inf_neg));
        assertTrue(Comparison.ne(inf_pos, inf_neg));
        assertTrue(Comparison.ne(inf_neg, inf_pos));
        assertTrue(Comparison.ne(pos, inf_pos));
        assertTrue(Comparison.ne(inf_pos, pos));
        assertTrue(Comparison.ne(pos, inf_neg));
        assertTrue(Comparison.ne(inf_neg, pos));
        assertFalse(Comparison.ne(nil, nil));
        assertTrue(Comparison.ne(pos, nil));
        assertTrue(Comparison.ne(nil, pos));
    }

    @Test
    public void testDoubleNe() {
        final Double nan = Double.NaN;
        final Double inf_pos = Double.POSITIVE_INFINITY;
        final Double inf_neg = Double.NEGATIVE_INFINITY;
        final Double nil = null;
        final Double pos = 1.1;
        final Double neg = -pos;

        assertFalse(Comparison.ne(pos, pos));
        assertTrue(Comparison.ne(pos, neg));
        assertTrue(Comparison.ne(neg, pos));
        assertFalse(Comparison.ne(neg, neg));
        assertTrue(Comparison.ne(nan, nan));
        assertTrue(Comparison.ne(pos, nan));
        assertTrue(Comparison.ne(inf_pos, nan));
        assertTrue(Comparison.ne(inf_neg, nan));
        assertTrue(Comparison.ne(nan, pos));
        assertTrue(Comparison.ne(nan, inf_pos));
        assertTrue(Comparison.ne(nan, inf_neg));
        assertFalse(Comparison.ne(inf_pos, inf_pos));
        assertFalse(Comparison.ne(inf_neg, inf_neg));
        assertTrue(Comparison.ne(inf_pos, inf_neg));
        assertTrue(Comparison.ne(inf_neg, inf_pos));
        assertTrue(Comparison.ne(pos, inf_pos));
        assertTrue(Comparison.ne(inf_pos, pos));
        assertTrue(Comparison.ne(pos, inf_neg));
        assertTrue(Comparison.ne(inf_neg, pos));
        assertFalse(Comparison.ne(nil, nil));
        assertTrue(Comparison.ne(pos, nil));
        assertTrue(Comparison.ne(nil, pos));
    }

    @Test
    public void testFloatGt() {
        final Float nan = Float.NaN;
        final Float inf_pos = Float.POSITIVE_INFINITY;
        final Float inf_neg = Float.NEGATIVE_INFINITY;
        final Float nil = null;
        final Float pos = 1.1f;
        final Float neg = -pos;

        assertFalse(Comparison.gt(pos, pos));
        assertTrue(Comparison.gt(pos, neg));
        assertFalse(Comparison.gt(neg, pos));
        assertFalse(Comparison.gt(neg, neg));
        assertFalse(Comparison.gt(nan, nan));
        assertFalse(Comparison.gt(pos, nan));
        assertFalse(Comparison.gt(inf_pos, nan));
        assertFalse(Comparison.gt(inf_neg, nan));
        assertFalse(Comparison.gt(nan, pos));
        assertFalse(Comparison.gt(nan, inf_pos));
        assertFalse(Comparison.gt(nan, inf_neg));
        assertFalse(Comparison.gt(inf_pos, inf_pos));
        assertFalse(Comparison.gt(inf_neg, inf_neg));
        assertTrue(Comparison.gt(inf_pos, inf_neg));
        assertFalse(Comparison.gt(inf_neg, inf_pos));
        assertFalse(Comparison.gt(pos, inf_pos));
        assertTrue(Comparison.gt(inf_pos, pos));
        assertTrue(Comparison.gt(pos, inf_neg));
        assertFalse(Comparison.gt(inf_neg, pos));
        assertNull(Comparison.gt(nil, nil));
        assertNull(Comparison.gt(pos, nil));
        assertNull(Comparison.gt(nil, pos));
    }

    @Test
    public void testDoubleGt() {
        final Double nan = Double.NaN;
        final Double inf_pos = Double.POSITIVE_INFINITY;
        final Double inf_neg = Double.NEGATIVE_INFINITY;
        final Double nil = null;
        final Double pos = 1.1;
        final Double neg = -pos;

        assertFalse(Comparison.gt(pos, pos));
        assertTrue(Comparison.gt(pos, neg));
        assertFalse(Comparison.gt(neg, pos));
        assertFalse(Comparison.gt(neg, neg));
        assertFalse(Comparison.gt(nan, nan));
        assertFalse(Comparison.gt(pos, nan));
        assertFalse(Comparison.gt(inf_pos, nan));
        assertFalse(Comparison.gt(inf_neg, nan));
        assertFalse(Comparison.gt(nan, pos));
        assertFalse(Comparison.gt(nan, inf_pos));
        assertFalse(Comparison.gt(nan, inf_neg));
        assertFalse(Comparison.gt(inf_pos, inf_pos));
        assertFalse(Comparison.gt(inf_neg, inf_neg));
        assertTrue(Comparison.gt(inf_pos, inf_neg));
        assertFalse(Comparison.gt(inf_neg, inf_pos));
        assertFalse(Comparison.gt(pos, inf_pos));
        assertTrue(Comparison.gt(inf_pos, pos));
        assertTrue(Comparison.gt(pos, inf_neg));
        assertFalse(Comparison.gt(inf_neg, pos));
        assertNull(Comparison.gt(nil, nil));
        assertNull(Comparison.gt(pos, nil));
        assertNull(Comparison.gt(nil, pos));
    }

    @Test
    public void testFloatLt() {
        final Float nan = Float.NaN;
        final Float inf_pos = Float.POSITIVE_INFINITY;
        final Float inf_neg = Float.NEGATIVE_INFINITY;
        final Float nil = null;
        final Float pos = 1.1f;
        final Float neg = -pos;

        assertFalse(Comparison.lt(pos, pos));
        assertFalse(Comparison.lt(pos, neg));
        assertTrue(Comparison.lt(neg, pos));
        assertFalse(Comparison.lt(neg, neg));
        assertFalse(Comparison.lt(nan, nan));
        assertFalse(Comparison.lt(pos, nan));
        assertFalse(Comparison.lt(inf_pos, nan));
        assertFalse(Comparison.lt(inf_neg, nan));
        assertFalse(Comparison.lt(nan, pos));
        assertFalse(Comparison.lt(nan, inf_pos));
        assertFalse(Comparison.lt(nan, inf_neg));
        assertFalse(Comparison.lt(inf_pos, inf_pos));
        assertFalse(Comparison.lt(inf_neg, inf_neg));
        assertFalse(Comparison.lt(inf_pos, inf_neg));
        assertTrue(Comparison.lt(inf_neg, inf_pos));
        assertTrue(Comparison.lt(pos, inf_pos));
        assertFalse(Comparison.lt(inf_pos, pos));
        assertFalse(Comparison.lt(pos, inf_neg));
        assertTrue(Comparison.lt(inf_neg, pos));
        assertNull(Comparison.lt(nil, nil));
        assertNull(Comparison.lt(pos, nil));
        assertNull(Comparison.lt(nil, pos));
    }

    @Test
    public void testDoubleLt() {
        final Double nan = Double.NaN;
        final Double inf_pos = Double.POSITIVE_INFINITY;
        final Double inf_neg = Double.NEGATIVE_INFINITY;
        final Double nil = null;
        final Double pos = 1.1;
        final Double neg = -pos;

        assertFalse(Comparison.lt(pos, pos));
        assertFalse(Comparison.lt(pos, neg));
        assertTrue(Comparison.lt(neg, pos));
        assertFalse(Comparison.lt(neg, neg));
        assertFalse(Comparison.lt(nan, nan));
        assertFalse(Comparison.lt(pos, nan));
        assertFalse(Comparison.lt(inf_pos, nan));
        assertFalse(Comparison.lt(inf_neg, nan));
        assertFalse(Comparison.lt(nan, pos));
        assertFalse(Comparison.lt(nan, inf_pos));
        assertFalse(Comparison.lt(nan, inf_neg));
        assertFalse(Comparison.lt(inf_pos, inf_pos));
        assertFalse(Comparison.lt(inf_neg, inf_neg));
        assertFalse(Comparison.lt(inf_pos, inf_neg));
        assertTrue(Comparison.lt(inf_neg, inf_pos));
        assertTrue(Comparison.lt(pos, inf_pos));
        assertFalse(Comparison.lt(inf_pos, pos));
        assertFalse(Comparison.lt(pos, inf_neg));
        assertTrue(Comparison.lt(inf_neg, pos));
        assertNull(Comparison.lt(nil, nil));
        assertNull(Comparison.lt(pos, nil));
        assertNull(Comparison.lt(nil, pos));
    }

    @Test
    public void testFloatGe() {
        final Float nan = Float.NaN;
        final Float inf_pos = Float.POSITIVE_INFINITY;
        final Float inf_neg = Float.NEGATIVE_INFINITY;
        final Float nil = null;
        final Float pos = 1.1f;
        final Float neg = -pos;

        assertTrue(Comparison.ge(pos, pos));
        assertTrue(Comparison.ge(pos, neg));
        assertFalse(Comparison.ge(neg, pos));
        assertTrue(Comparison.ge(neg, neg));
        assertFalse(Comparison.ge(nan, nan));
        assertFalse(Comparison.ge(pos, nan));
        assertFalse(Comparison.ge(inf_pos, nan));
        assertFalse(Comparison.ge(inf_neg, nan));
        assertFalse(Comparison.ge(nan, pos));
        assertFalse(Comparison.ge(nan, inf_pos));
        assertFalse(Comparison.ge(nan, inf_neg));
        assertTrue(Comparison.ge(inf_pos, inf_pos));
        assertTrue(Comparison.ge(inf_neg, inf_neg));
        assertTrue(Comparison.ge(inf_pos, inf_neg));
        assertFalse(Comparison.ge(inf_neg, inf_pos));
        assertFalse(Comparison.ge(pos, inf_pos));
        assertTrue(Comparison.ge(inf_pos, pos));
        assertTrue(Comparison.ge(pos, inf_neg));
        assertFalse(Comparison.ge(inf_neg, pos));
        assertTrue(Comparison.ge(nil, nil));
        assertNull(Comparison.ge(pos, nil));
        assertNull(Comparison.ge(nil, pos));
    }

    @Test
    public void testDoubleGe() {
        final Double nan = Double.NaN;
        final Double inf_pos = Double.POSITIVE_INFINITY;
        final Double inf_neg = Double.NEGATIVE_INFINITY;
        final Double nil = null;
        final Double pos = 1.1;
        final Double neg = -pos;

        assertTrue(Comparison.ge(pos, pos));
        assertTrue(Comparison.ge(pos, neg));
        assertFalse(Comparison.ge(neg, pos));
        assertTrue(Comparison.ge(neg, neg));
        assertFalse(Comparison.ge(nan, nan));
        assertFalse(Comparison.ge(pos, nan));
        assertFalse(Comparison.ge(inf_pos, nan));
        assertFalse(Comparison.ge(inf_neg, nan));
        assertFalse(Comparison.ge(nan, pos));
        assertFalse(Comparison.ge(nan, inf_pos));
        assertFalse(Comparison.ge(nan, inf_neg));
        assertTrue(Comparison.ge(inf_pos, inf_pos));
        assertTrue(Comparison.ge(inf_neg, inf_neg));
        assertTrue(Comparison.ge(inf_pos, inf_neg));
        assertFalse(Comparison.ge(inf_neg, inf_pos));
        assertFalse(Comparison.ge(pos, inf_pos));
        assertTrue(Comparison.ge(inf_pos, pos));
        assertTrue(Comparison.ge(pos, inf_neg));
        assertFalse(Comparison.ge(inf_neg, pos));
        assertTrue(Comparison.ge(nil, nil));
        assertNull(Comparison.ge(pos, nil));
        assertNull(Comparison.ge(nil, pos));
    }

    @Test
    public void testFloatLe() {
        final Float nan = Float.NaN;
        final Float inf_pos = Float.POSITIVE_INFINITY;
        final Float inf_neg = Float.NEGATIVE_INFINITY;
        final Float nil = null;
        final Float pos = 1.1f;
        final Float neg = -pos;

        assertTrue(Comparison.le(pos, pos));
        assertFalse(Comparison.le(pos, neg));
        assertTrue(Comparison.le(neg, pos));
        assertTrue(Comparison.le(neg, neg));
        assertFalse(Comparison.le(nan, nan));
        assertFalse(Comparison.le(pos, nan));
        assertFalse(Comparison.le(inf_pos, nan));
        assertFalse(Comparison.le(inf_neg, nan));
        assertFalse(Comparison.le(nan, pos));
        assertFalse(Comparison.le(nan, inf_pos));
        assertFalse(Comparison.le(nan, inf_neg));
        assertTrue(Comparison.le(inf_pos, inf_pos));
        assertTrue(Comparison.le(inf_neg, inf_neg));
        assertFalse(Comparison.le(inf_pos, inf_neg));
        assertTrue(Comparison.le(inf_neg, inf_pos));
        assertTrue(Comparison.le(pos, inf_pos));
        assertFalse(Comparison.le(inf_pos, pos));
        assertFalse(Comparison.le(pos, inf_neg));
        assertTrue(Comparison.le(inf_neg, pos));
        assertTrue(Comparison.le(nil, nil));
        assertNull(Comparison.le(pos, nil));
        assertNull(Comparison.le(nil, pos));
    }

    @Test
    public void testDoubleLe() {
        final Double nan = Double.NaN;
        final Double inf_pos = Double.POSITIVE_INFINITY;
        final Double inf_neg = Double.NEGATIVE_INFINITY;
        final Double nil = null;
        final Double pos = 1.1;
        final Double neg = -pos;

        assertTrue(Comparison.le(pos, pos));
        assertFalse(Comparison.le(pos, neg));
        assertTrue(Comparison.le(neg, pos));
        assertTrue(Comparison.le(neg, neg));
        assertFalse(Comparison.le(nan, nan));
        assertFalse(Comparison.le(pos, nan));
        assertFalse(Comparison.le(inf_pos, nan));
        assertFalse(Comparison.le(inf_neg, nan));
        assertFalse(Comparison.le(nan, pos));
        assertFalse(Comparison.le(nan, inf_pos));
        assertFalse(Comparison.le(nan, inf_neg));
        assertTrue(Comparison.le(inf_pos, inf_pos));
        assertTrue(Comparison.le(inf_neg, inf_neg));
        assertFalse(Comparison.le(inf_pos, inf_neg));
        assertTrue(Comparison.le(inf_neg, inf_pos));
        assertTrue(Comparison.le(pos, inf_pos));
        assertFalse(Comparison.le(inf_pos, pos));
        assertFalse(Comparison.le(pos, inf_neg));
        assertTrue(Comparison.le(inf_neg, pos));
        assertTrue(Comparison.le(nil, nil));
        assertNull(Comparison.le(pos, nil));
        assertNull(Comparison.le(nil, pos));
    }
}
