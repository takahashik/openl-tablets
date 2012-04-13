package org.openl.util.ce.impl;

import org.openl.util.ce.IServiceMT;
import org.openl.util.ce.IServiceMTConfiguration;
import org.openl.util.ce.IServiceMTFactory;

public class ServiceFactory16 implements IServiceMTFactory{

	@Override
	public IServiceMT makeService(IServiceMTConfiguration config) {
	
		return new ServiceMT16(config);
	}

}