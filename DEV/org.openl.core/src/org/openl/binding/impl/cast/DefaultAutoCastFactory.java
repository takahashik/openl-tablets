package org.openl.binding.impl.cast;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import org.openl.binding.IBindingContext;
import org.openl.types.IMethodCaller;
import org.openl.types.IOpenClass;
import org.openl.types.impl.CastingMethodCaller;
import org.openl.types.impl.ComponentTypeArrayOpenClass;
import org.openl.types.impl.DomainOpenClass;
import org.openl.types.java.AutoCastResultOpenMethod;
import org.openl.types.java.JavaOpenClass;
import org.openl.types.java.JavaOpenMethod;

public class DefaultAutoCastFactory implements AutoCastFactory {

	@Override
	public IMethodCaller build(IBindingContext bindingContext, IMethodCaller methodCaller,
			IOpenClass[] parameterTypes) {
		JavaOpenMethod method = null;
		if (methodCaller instanceof CastingMethodCaller) {
			CastingMethodCaller castingMethodCaller = (CastingMethodCaller) methodCaller;
			if (castingMethodCaller.getMethod() instanceof JavaOpenMethod) {
				method = (JavaOpenMethod) castingMethodCaller.getMethod();
			}
		}

		if (methodCaller instanceof JavaOpenMethod) {
			method = (JavaOpenMethod) methodCaller;
		}

		if (method instanceof JavaOpenMethod) {
			JavaOpenMethod javaOpenMethod = (JavaOpenMethod) method;
			Method javaMethod = javaOpenMethod.getJavaMethod();
			AutoCastReturnType autoCastReturnType = javaMethod.getAnnotation(AutoCastReturnType.class);
			if (autoCastReturnType != null) {
				int i = 0;
				for (Annotation[] parameterAnnotations : javaMethod.getParameterAnnotations()) {
					for (Annotation annotation : parameterAnnotations) {
						if (annotation instanceof ReturnType) {
							ReturnType returnType = (ReturnType) annotation;
							return buildAutoCastResultOpenMethod(bindingContext, methodCaller,
									method, parameterTypes[i],
									returnType.arrayDimension() >= 0 ? returnType.arrayDimension() : null);
						}
					}
					i++;
				}
			}
		}
		return methodCaller;
	}
	
	private IMethodCaller buildAutoCastResultOpenMethod(IBindingContext bindingContext, IMethodCaller methodCaller,
			JavaOpenMethod method, IOpenClass type, Integer arrayDim) {
		IOpenClass simpleType = type;
		int d = 0;
		while (simpleType.isArray()) {
			if (simpleType.getAggregateInfo() != null) {
				simpleType = simpleType.getAggregateInfo().getComponentType(simpleType);
			} else {
				simpleType = simpleType.getComponentClass();
			}
			d++;
		}
		
		if (arrayDim != null && d > arrayDim) {
			simpleType = JavaOpenClass.OBJECT;
		}
		
		if (!method.getType().isArray()) {
			IOpenCast cast = bindingContext.getCast(method.getType(), simpleType);
			if (cast != null) {
				return new AutoCastResultOpenMethod(methodCaller, simpleType, cast);
			}
		} else {
			IOpenClass v = method.getType();
			int dimensions = 0;
			while (v.isArray()) {
				v = v.getComponentClass();
				dimensions++;
			}
			
			ComponentTypeArrayOpenClass componentTypeArrayOpenClass = ComponentTypeArrayOpenClass.createComponentTypeArrayOpenClass(simpleType, dimensions);
			
			if (simpleType.getDomain() != null) {
				StringBuilder domainOpenClassName = new StringBuilder(simpleType.getName());
				for (int j = 0; j < dimensions; j++) {
					domainOpenClassName.append("[]");
				}
				DomainOpenClass domainArrayType = new DomainOpenClass(domainOpenClassName.toString(), componentTypeArrayOpenClass,
						simpleType.getDomain(), null);
				IOpenCast cast = bindingContext.getCast(method.getType(), domainArrayType);
				if (cast != null) {
					return new AutoCastResultOpenMethod(methodCaller, domainArrayType, cast);
				}
			} else {
				IOpenCast cast = bindingContext.getCast(method.getType(), componentTypeArrayOpenClass);
				if (cast != null) {
					return new AutoCastResultOpenMethod(methodCaller, componentTypeArrayOpenClass, cast);
				}
			}
		}
		return methodCaller;
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.PARAMETER)
	public @interface ReturnType {
		int arrayDimension() default -1;
	}
}
