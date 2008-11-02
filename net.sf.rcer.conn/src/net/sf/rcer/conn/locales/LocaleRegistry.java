/**
 * Copyright (c) 2008 The RCER Development Team.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 *
 * $$Id$$
 */
package net.sf.rcer.conn.locales;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;

/**
 * The central registry for SAP Locales. Use this singleton class to obtain the locales 
 * defined using the extension point <tt>net.sf.rcer.conn.saplocales</tt>.
 * @author vwegert
 *
 */
public class LocaleRegistry implements IRegistryEventListener {

	/**
	 * The name of the extension point used to define the locales.
	 */
	public static final String SAPLOCALES_EXTENSION_POINT = "net.sf.rcer.conn.saplocales";
	
	/**
	 * The singleton instance.
	 */
	private static LocaleRegistry instance;
	
	/**
	 * A sorted map that contains the locale objects, ordered by ISO codes.
	 */
	private SortedMap<String, Locale> localesByISO = new TreeMap<String, Locale>();
	
	/**
	 * Private constructor to prevent secondary instantiation.
	 */
	private LocaleRegistry() {
		
		// load all locales that are currently defined
		addLocales(Platform.getExtensionRegistry().getExtensionPoint(SAPLOCALES_EXTENSION_POINT).getExtensions());
		
		// register a listener so that the registry is notified of changes
		Platform.getExtensionRegistry().addListener(this, SAPLOCALES_EXTENSION_POINT);
	}
	
	/**
	 * @return the singleton instance.
	 */
	public static LocaleRegistry getInstance() {
		if (instance == null) {
			instance = new LocaleRegistry();
		}
		return instance;
	}
	
	/**
	 * @return the list of locales that are currently registered
	 */
	public Collection<Locale> getLocales() {
		return localesByISO.values();
	}
	
	/**
	 * @return the list of ISO codes that are currently registered
	 */
	public Collection<String> getISOCodes() {
		return localesByISO.keySet();
	}
	
	/**
	 * @param isoCode the ISO code of the locale to retrieve
	 * @return the {@link Locale} object
	 * @throws LocaleNotFoundException
	 */
	public Locale getLocaleByISO(String isoCode) throws LocaleNotFoundException {
		if (localesByISO.containsKey(isoCode)) {
			return localesByISO.get(isoCode);
		}
		throw new LocaleNotFoundException(isoCode);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtension[])
	 */
	public void added(IExtension[] extensions) {
		addLocales(extensions);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
	 */
	public void removed(IExtension[] extensions) {
		removeLocales(extensions);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtensionPoint[])
	 */
	public void added(IExtensionPoint[] extensionPoints) {
		// This class does not react to extension point changes.
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtensionPoint[])
	 */
	public void removed(IExtensionPoint[] extensionPoints) {
		// This class does not react to extension point changes.
	}
	
	/**
	 * Examine the extensions provided and either insert new locale objects or update the existing ones.
	 * @param extensions
	 */
	private void addLocales(IExtension[] extensions) {
		for(final IExtension extension: extensions) {
			assert extension.getExtensionPointUniqueIdentifier().equals(SAPLOCALES_EXTENSION_POINT);
			final IConfigurationElement[] elements = extension.getConfigurationElements();
			for(final IConfigurationElement element: elements) {
				if (element.getName().equals("locale")) {
					final String id = element.getAttribute("id");
					final String isoCode = element.getAttribute("iso");
					final String description = element.getAttribute("description");
					if (localesByISO.containsKey(isoCode)) {
						final Locale locale = localesByISO.get(id);
						locale.setID(id);
						locale.setDescription(description);
					} else {
						localesByISO.put(isoCode, new Locale(id, isoCode, description));
					}
				}
			}
		}
	}

	/**
	 * Examine the extensions provided and remove the locale objects.
	 * @param extensions
	 */
	private void removeLocales(IExtension[] extensions) {
		for(final IExtension extension: extensions) {
			assert extension.getExtensionPointUniqueIdentifier().equals(SAPLOCALES_EXTENSION_POINT);
			final IConfigurationElement[] elements = extension.getConfigurationElements();
			for(final IConfigurationElement element: elements) {
				if (element.getName().equals("locale")) {
					final String isoCode = element.getAttribute("iso");
					if (localesByISO.containsKey(isoCode)) {
						localesByISO.remove(isoCode);
					}
				}
			}
		}
	}

}
