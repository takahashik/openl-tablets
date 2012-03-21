package org.openl.rules.project.instantiation;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.openl.rules.context.IRulesRuntimeContext;

public class RulesServiceEnhancerHelperTest {
    @Test
    public void testServiceClassDecoration() throws Exception {
        Class<?> enhanced = RulesServiceEnhancerHelper.decorateMethods(SimpleInterface.class, Thread.currentThread()
            .getContextClassLoader());
        checkEnhancement(enhanced, SimpleInterface.class, false);
    }

    private void checkEnhancement(Class<?> enhanced, Class<?> simple, boolean checkAnnotations) {
        assertEquals(enhanced.getMethods().length, simple.getMethods().length);
        // check methods
        for (Method method : simple.getMethods()) {
            assertNotNull(getEnhancedMethod(method, simple, enhanced));
        }
        if (checkAnnotations) {
            // check annotations: all annotations should remain after
            // undecoration.
            // note: annotation passing to enhanced class currently is not
            // supported.
            assertArrayEquals(enhanced.getAnnotations(), simple.getAnnotations());
            for (Method method : simple.getMethods()) {
                assertArrayEquals(getEnhancedMethod(method, simple, enhanced).getAnnotations(), method.getAnnotations());
            }
        }
    }

    private Method getEnhancedMethod(Method methodFromSimple, Class<?> simple, Class<?> enhanced) {
        return MethodUtils.getMatchingAccessibleMethod(enhanced,
            methodFromSimple.getName(),
            (Class[]) ArrayUtils.add(methodFromSimple.getParameterTypes(), 0, IRulesRuntimeContext.class));
    }

    @Test
    public void testServiceClassUndecoration() throws Exception {
        Class<?> undecorated = RulesServiceEnhancerHelper.undecorateMethods(Enhanced.class, Thread.currentThread()
            .getContextClassLoader());
        checkEnhancement(Enhanced.class, undecorated, true);
        Class<?> undecorated2 = RulesServiceEnhancerHelper.undecorateMethods(Enhanced2.class, Thread.currentThread()
            .getContextClassLoader());
        checkEnhancement(Enhanced2.class, undecorated2, true);
    }

    @Test
    public void testServiceClassRecognition() {
        assertTrue(RulesServiceEnhancerHelper.isEnhancedClass(Enhanced.class));
        assertTrue(RulesServiceEnhancerHelper.isEnhancedClass(Enhanced2.class));
        assertFalse(RulesServiceEnhancerHelper.isEnhancedClass(SimpleInterface.class));
        assertFalse(RulesServiceEnhancerHelper.isEnhancedClass(Mixed.class));
        assertFalse(RulesServiceEnhancerHelper.isEnhancedClass(Mixed2.class));
    }

    private static interface Enhanced {
        void doSome(IRulesRuntimeContext context);

        void doSome2(IRulesRuntimeContext context, String arg);

        String getSome(IRulesRuntimeContext context, String arg, int arg2);
    }

    @XmlType
    private static interface Enhanced2 {
        void doSome(IRulesRuntimeContext context);

        @Deprecated
        void doSome2(IRulesRuntimeContext context, String arg);

        @Ignore
        String getSome(IRulesRuntimeContext context, String arg, int arg2);
    }

    private static interface SimpleInterface {
        void doSome();

        void doSome2(String arg);

        String getSome(String arg, int arg2);
    }

    private static interface Mixed {
        void doSome();

        void doSome2(String arg);

        String getSome(IRulesRuntimeContext context, String arg, int arg2);
    }

    private static interface Mixed2 {
        void doSome(IRulesRuntimeContext context);

        void doSome2(IRulesRuntimeContext context, String arg);

        String getSome(String arg, int arg2);
    }
}