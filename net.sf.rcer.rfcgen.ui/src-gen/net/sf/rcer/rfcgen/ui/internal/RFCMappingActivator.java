
/*
 * generated by Xtext
 */
package net.sf.rcer.rfcgen.ui.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

/**
 * Generated
 */
public class RFCMappingActivator extends AbstractUIPlugin {

	private Map<String,Injector> injectors = new HashMap<String,Injector>();
	private static RFCMappingActivator INSTANCE;

	public Injector getInjector(String languageName) {
		return injectors.get(languageName);
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
		try {
			
			injectors.put("net.sf.rcer.rfcgen.RFCMapping", Guice.createInjector(
				Modules.override(Modules.override(getRuntimeModule("net.sf.rcer.rfcgen.RFCMapping")).with(getUiModule("net.sf.rcer.rfcgen.RFCMapping"))).with(getSharedStateModule())
			));
			
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage(), e);
			throw e;
		}
	}
	
	public static RFCMappingActivator getInstance() {
		return INSTANCE;
	}
	
	protected Module getRuntimeModule(String grammar) {
		
		if ("net.sf.rcer.rfcgen.RFCMapping".equals(grammar)) {
		  return new net.sf.rcer.rfcgen.RFCMappingRuntimeModule();
		}
		
		throw new IllegalArgumentException(grammar);
	}
	protected Module getUiModule(String grammar) {
		
		if ("net.sf.rcer.rfcgen.RFCMapping".equals(grammar)) {
		  return new net.sf.rcer.rfcgen.ui.RFCMappingUiModule(this);
		}
		
		throw new IllegalArgumentException(grammar);
	}
	
	protected Module getSharedStateModule() {
		return new org.eclipse.xtext.ui.shared.SharedStateModule();
	}
	
}
