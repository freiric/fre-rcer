/*******************************************************************************
 * Copyright (c) 2010 The RCER Development Team.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Volker Wegert <rcer@volker-wegert.de> - initial API and implementation
 *******************************************************************************/
 
package net.sf.rcer.rom.ddic.rfc;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.text.MessageFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRecord;
import com.sap.conn.jco.JCoTable;

/**
 * A class to model a RFC call to DD_DOMA_GET. Use the setters to prepare the importing parameters, 
 * then invoke {@link #execute(JCoDestination)} to execute the call. The attributes of this instance 
 * will be updated to reflect the data returned from the SAP R/3 system.
 * <blockquote><pre>
 * RFCDomainReader call = new RFCDomainReader();
 * call.setFoo(...);
 * call.execute(destination);
 * bar = call.getBar();
 * </pre></blockquote>
 */
public class RFCDomainReader {

	private PropertyChangeSupport _pcs;

	private String domainName;
	private String localeID;
	private RFCDomainData domainData;
	private List<RFCDomainValue> values = new ArrayList<RFCDomainValue>();
	
	/**
	 * Default constructor to create an instance with initial values.
	 */
	public RFCDomainReader() {
		_pcs = new PropertyChangeSupport(this);
	}

	/**
	 * @return the name (DOMAIN_NAME)
	 */
	public String getDomainName() {
		return this.domainName;
	}
	
	/**
	 * Changes the name (DOMAIN_NAME).
	 * @param newDomainName the new name to set
	 */
	public void setDomainName(String newDomainName) {
		_pcs.firePropertyChange("domainName", this.domainName, newDomainName); //$NON-NLS-1$
		this.domainName = newDomainName;
	}
	
	/**
	 * @return the localeID (LANGU)
	 */
	public String getLocaleID() {
		return this.localeID;
	}
	
	/**
	 * Changes the localeID (LANGU).
	 * @param newLocaleID the new localeID to set
	 */
	public void setLocaleID(String newLocaleID) {
		_pcs.firePropertyChange("localeID", this.localeID, newLocaleID); //$NON-NLS-1$
		this.localeID = newLocaleID;
	}
	
	/**
	 * @return the the data of the domain (DD01V_WA_A)
	 */
	public RFCDomainData getDomainData() {
		return this.domainData;
	}
	
	/**
	 * Changes the the data of the domain (DD01V_WA_A).
	 * @param newDomainData the new the data of the domain to set
	 */
	public void setDomainData(RFCDomainData newDomainData) {
		_pcs.firePropertyChange("domainData", this.domainData, newDomainData); //$NON-NLS-1$
		this.domainData = newDomainData;
	}
	
	/**
	 * @return the the fixed values of the domain (DD07V_TAB_A)
	 */
	public List<RFCDomainValue> getValues() {
		return this.values;
	}
	
	/**
	 * Changes the the fixed values of the domain (DD07V_TAB_A).
	 * @param newValues the new the fixed values of the domain to set
	 */
	public void setValues(List<RFCDomainValue> newValues) {
		_pcs.firePropertyChange("values", this.values, newValues); //$NON-NLS-1$
		this.values = newValues;
	}
	
	/**
	 * Executes the RFC call and updates the attributes accordingly.
	 * @param destination the RFC destination to use
	 * @throws JCoException
	 */
	public void execute(JCoDestination destination) throws JCoException {
		JCoFunction function = destination.getRepository().getFunction("DD_DOMA_GET"); //$NON-NLS-1$
		// FIXME: set inactive parameters
		function.getImportParameterList().setValue("DOMAIN_NAME", domainName); //$NON-NLS-1$
		function.getImportParameterList().setValue("LANGU", localeID); //$NON-NLS-1$
		domainData.toStructure(function.getImportParameterList().getStructure("DD01V_WA_A")); //$NON-NLS-1$
		RFCDomainValue.toTable(values, function.getImportParameterList().getTable("DD07V_TAB_A")); //$NON-NLS-1$
		function.execute(destination);
		domainName = function.getExportParameterList().getString("DOMAIN_NAME"); //$NON-NLS-1$
		localeID = function.getExportParameterList().getString("LANGU"); //$NON-NLS-1$
		domainData = new RFCDomainData(function.getExportParameterList().getStructure("DD01V_WA_A")); //$NON-NLS-1$
		values = RFCDomainValue.fromTable(function.getExportParameterList().getTable("DD07V_TAB_A")); //$NON-NLS-1$
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		_pcs.addPropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		_pcs.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @return the list of property change listeners
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners()
	 */
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return _pcs.getPropertyChangeListeners();
	}

	/**
	 * @param propertyName
	 * @return the list of property change listeners for a certain property
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners(java.lang.String)
	 */
	public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
		return _pcs.getPropertyChangeListeners(propertyName);
	}

	/**
	 * @param propertyName
	 * @return <code>true</code> if any property change listeners are registered
	 * @see java.beans.PropertyChangeSupport#hasListeners(java.lang.String)
	 */
	public boolean hasListeners(String propertyName) {
		return _pcs.hasListeners(propertyName);
	}

	/**
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		_pcs.removePropertyChangeListener(listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		_pcs.removePropertyChangeListener(propertyName, listener);
	}

}
