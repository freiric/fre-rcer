/**
 * Copyright (c) 2008, 2010 The RCER Development Team.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 *
 * $Id$
 */
package net.sf.rcer.conn.connections;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.rcer.conn.Activator;
import net.sf.rcer.conn.Messages;
import net.sf.rcer.conn.locales.Locale;
import net.sf.rcer.conn.locales.LocaleNotFoundException;
import net.sf.rcer.conn.locales.LocaleRegistry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * The central registry for definitions of connections to the SAP R/3 systems.
 * @author vwegert
 *
 */
public class ConnectionRegistry implements IRegistryEventListener {

	/**
	 * The name of the extension point used to define the connections and connection providers.
	 */
	public static final String CONNECTIONS_EXTENSION_POINT = "net.sf.rcer.conn.connections"; //$NON-NLS-1$

	/**
	 * The singleton instance.
	 */
	private static volatile ConnectionRegistry instance;

	/**
	 * A map of all the connections that were defined using the extension point only.
	 */
	private Map<String, IConnectionData> staticConnectionData = new HashMap<String, IConnectionData>();

	/**
	 * A map of all the connection providers can supply dynamic connection data.
	 */
	private Map<String, IConnectionProvider> connectionProviders = new HashMap<String, IConnectionProvider>();

	/**
	 * Private constructor to prevent secondary instantiation.
	 */
	private ConnectionRegistry() {

		// load all connections and connection providers that are currently defined
		addConnections(Platform.getExtensionRegistry().getExtensionPoint(CONNECTIONS_EXTENSION_POINT).getExtensions());

		// register a listener so that the registry is notified of changes
		Platform.getExtensionRegistry().addListener(this, CONNECTIONS_EXTENSION_POINT);
	}

	/**
	 * @return the singleton instance.
	 */
	public static ConnectionRegistry getInstance() {
		if (instance == null) {
			synchronized (ConnectionRegistry.class) {
				if (instance == null) {
					instance = new ConnectionRegistry();
				}
			}
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtension[])
	 */
	public void added(IExtension[] extensions) {
		addConnections(extensions);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
	 */
	public void removed(IExtension[] extensions) {
		removeConnections(extensions);
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
	 * Examine the extensions provided and either insert new connections and connection providers 
	 * or update the existing connections.
	 * @param extensions
	 */
	private void addConnections(IExtension[] extensions) {
		for(final IExtension extension: extensions) {
			assert extension.getExtensionPointUniqueIdentifier().equals(CONNECTIONS_EXTENSION_POINT);
			final String pluginID = extension.getContributor().getName();

			final IConfigurationElement[] elements = extension.getConfigurationElements();
			for(final IConfigurationElement element: elements) {

				if (element.getName().equals("connection")) { //$NON-NLS-1$
					// add or modify a static connection
					final String localID = element.getAttribute("id"); //$NON-NLS-1$
					final String globalID = pluginID + "." + localID; //$NON-NLS-1$
					if (staticConnectionData.containsKey(globalID)) {
						updateStaticConnection(element, globalID);
					} else {
						addStaticConnection(element, globalID);
					}

				} else if (element.getName().equals("provider")) { //$NON-NLS-1$
					// add a connection provider (can't modify it if it already exists)
					final String localID = element.getAttribute("id"); //$NON-NLS-1$
					final String globalID = pluginID + "." + localID; //$NON-NLS-1$
					if (connectionProviders.containsKey(globalID)) {
						final String nominalClass = element.getAttribute("provider"); //$NON-NLS-1$
						final String actualClass  = connectionProviders.get(globalID).getClass().getName();
						if (!actualClass.equals(nominalClass)) {
							logError(MessageFormat.format(Messages.ConnectionRegistry_ReplaceConnectionProviderError,
									actualClass, globalID, nominalClass), null);
						}
					} else {
						try {
							IConnectionProvider provider = (IConnectionProvider) element.createExecutableExtension("provider"); //$NON-NLS-1$
							connectionProviders.put(globalID, provider);
						} catch (CoreException e) {
							logError(MessageFormat.format(Messages.ConnectionRegistry_LoadConnectionProviderError,
									globalID, element.getAttribute("provider")), e); //$NON-NLS-1$
						}
					}

				}
			}
		}
	}

	/**
	 * Examine the extensions provided and remove the connections and connection providers.
	 * @param extensions
	 */
	private void removeConnections(IExtension[] extensions) {
		for(final IExtension extension: extensions) {
			assert extension.getExtensionPointUniqueIdentifier().equals(CONNECTIONS_EXTENSION_POINT);
			final String pluginID = extension.getContributor().getName();

			final IConfigurationElement[] elements = extension.getConfigurationElements();
			for(final IConfigurationElement element: elements) {

				if (element.getName().equals("connection")) { //$NON-NLS-1$
					// remove a static connection
					final String localID = element.getAttribute("id"); //$NON-NLS-1$
					final String globalID = pluginID + "." + localID; //$NON-NLS-1$
					if (staticConnectionData.containsKey(globalID)) {
						staticConnectionData.remove(globalID);
					}

				} else if (element.getName().equals("provider")) { //$NON-NLS-1$
					// remove a connection provider
					final String localID = element.getAttribute("id"); //$NON-NLS-1$
					final String globalID = pluginID + "." + localID; //$NON-NLS-1$
					if (connectionProviders.containsKey(globalID)) {
						connectionProviders.remove(globalID);
					}

				}
			}
		}
	}

	/**
	 * Extracts the information from an extension element and creates a new static connection object.
	 * @param configurationElement
	 * @param connectionID
	 */
	private void addStaticConnection(final IConfigurationElement configurationElement, final String connectionID) {
		ConnectionData connection = null;
		if (configurationElement.getAttribute("connectionType").equals("direct")) { //$NON-NLS-1$ //$NON-NLS-2$
			try {
				int systemNumber = Integer.parseInt(configurationElement.getAttribute("systemNumber")); //$NON-NLS-1$
				connection = new ConnectionData(connectionID,
						configurationElement.getAttribute("description"), //$NON-NLS-1$
						configurationElement.getAttribute("systemID"), //$NON-NLS-1$
						configurationElement.getAttribute("router"), //$NON-NLS-1$
						configurationElement.getAttribute("applicationServer"), //$NON-NLS-1$
						systemNumber);
			} catch (NumberFormatException e) {
				logError(MessageFormat.format(
						Messages.ConnectionRegistry_InvalidSystemNumberError,
						connectionID, configurationElement.getAttribute("systemNumber")), e); //$NON-NLS-1$
				return;
			}
		} else {
			try {
				int messageServerPort = Integer.parseInt(configurationElement.getAttribute("messageServerPort")); //$NON-NLS-1$
				connection = new ConnectionData(connectionID,
						configurationElement.getAttribute("description"), //$NON-NLS-1$
						configurationElement.getAttribute("systemID"), //$NON-NLS-1$
						configurationElement.getAttribute("router"), //$NON-NLS-1$
						configurationElement.getAttribute("messageServer"), //$NON-NLS-1$
						messageServerPort,
						configurationElement.getAttribute("loadBalancingGroup")); //$NON-NLS-1$
			} catch (NumberFormatException e) {
				logError(MessageFormat.format(
						Messages.ConnectionRegistry_InvalidMessageServerPortError,
						connectionID, configurationElement.getAttribute("messageServerPort")), e); //$NON-NLS-1$
				return;
			}
		}

		connection.setDefaultUser(configurationElement.getAttribute("defaultUser"),  //$NON-NLS-1$
				configurationElement.getAttribute("defaultUserEditable").equalsIgnoreCase("true")); //$NON-NLS-1$ //$NON-NLS-2$
		connection.setDefaultClient(configurationElement.getAttribute("defaultClient"),  //$NON-NLS-1$
				configurationElement.getAttribute("defaultClientEditable").equalsIgnoreCase("true")); //$NON-NLS-1$ //$NON-NLS-2$

		final String localeISOCode = configurationElement.getAttribute("defaultLocale"); //$NON-NLS-1$
		if ((localeISOCode != null) && (!localeISOCode.equals(""))) { //$NON-NLS-1$
			try {
				final Locale locale = LocaleRegistry.getInstance().getLocaleByISO(localeISOCode);
				connection.setDefaultLocale(locale,  
						configurationElement.getAttribute("defaultLocaleEditable").equalsIgnoreCase("true")); //$NON-NLS-1$ //$NON-NLS-2$
			} catch (LocaleNotFoundException e) {
				logError(MessageFormat.format(Messages.ConnectionRegistry_InvalidLocaleError,
						connectionID), e);
			}
		}

		staticConnectionData.put(connectionID, connection);

	}

	/**
	 * Updates an existing static connection object.
	 * @param configurationElement
	 * @param connectionID
	 */
	private void updateStaticConnection(final IConfigurationElement configurationElement, final String connectionID) {
		assert false;
		// TODO implement this method
	}

	/**
	 * Auxiliary method to log an error message
	 * @param message the message to log
	 * @param exception the exception that occurred
	 */
	private void logError(String message, Exception exception) {
		Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, message, exception));
	}

	/**
	 * @return the set of plug-in provider IDs
	 */
	public Set<String> getProviderIDs() {
		return connectionProviders.keySet();
	}

	/**
	 * @param providerID the ID of the connection provider
	 * @return a list of connection IDs supplied by the provider
	 * @throws ProviderNotFoundException 
	 */
	public Set<String> getConnectionDataIDs(String providerID) throws ProviderNotFoundException {
		if ((providerID == null) || (providerID.equals(""))) { //$NON-NLS-1$
			return getConnectionDataIDs();
		} else if (!connectionProviders.containsKey(providerID)) {
			throw new ProviderNotFoundException(providerID);
		} else { 
			Set<String> connectionIDs = new HashSet<String>();
			for(final String localID: connectionProviders.get(providerID).getConnectionIDs()) {
				connectionIDs.add(providerID + "#" + localID); //$NON-NLS-1$
			}
			return connectionIDs;
		}
	}

	/**
	 * @return a list of all connections IDs, whether the connections are statically defined or dynamically provided
	 */
	public Set<String> getConnectionDataIDs() {
		Set<String> connectionIDs = new HashSet<String>();

		// add the static connections
		connectionIDs.addAll(staticConnectionData.keySet());

		// add the connections provided by all connection providers
		for(final String providerID: connectionProviders.keySet()) {
			final IConnectionProvider provider = connectionProviders.get(providerID);
			for(final String connectionID: provider.getConnectionIDs()) {
				connectionIDs.add(providerID + "#" + connectionID); //$NON-NLS-1$
			}
		}

		return connectionIDs;			
	}

	/**
	 * @param connectionID
	 * @return the connection data for a given connection ID
	 * @throws ConnectionNotFoundException
	 * @throws ProviderNotFoundException 
	 */
	public IConnectionData getConnectionData(String connectionID) throws ConnectionNotFoundException, ProviderNotFoundException {
		if ((connectionID == null) || (connectionID.equals(""))) { //$NON-NLS-1$
			throw new ConnectionNotFoundException(connectionID, true);
		} else if (connectionID.contains("#")) { //$NON-NLS-1$
			String[] parts = connectionID.split("#"); //$NON-NLS-1$
			if (parts.length != 2) {
				logError(MessageFormat.format(Messages.ConnectionRegistry_InvalidConnectionIDError,
						connectionID), null);
				throw new ConnectionNotFoundException(connectionID, true);				
			}
			if (!connectionProviders.containsKey(parts[0])) {
				throw new ProviderNotFoundException(parts[0]);
			}
			return new ProvidedConnectionData(parts[0], connectionProviders.get(parts[0]).getConnectionData(parts[1]));
		} else {
			if (!staticConnectionData.containsKey(connectionID)) {
				throw new ConnectionNotFoundException(connectionID, true);
			}
			return staticConnectionData.get(connectionID);
		}
	}

	/**
	 * @return a set of all connections, whether they are statically defined or dynamically provided
	 */
	public Set<IConnectionData> getConnectionData() {
		Set<IConnectionData> connectionData = new HashSet<IConnectionData>();
		for (final String id: getConnectionDataIDs()) {
			try {
				connectionData.add(getConnectionData(id));
			} catch (ConnectionNotFoundException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
						MessageFormat.format(Messages.ConnectionRegistry_ConnectionRetrievalError, id), e));
			} catch (ProviderNotFoundException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, 
						MessageFormat.format(Messages.ConnectionRegistry_ConnectionRetrievalError, id), e));
			}
		}
		return connectionData;
	}
}
