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

import net.sf.rcer.conn.locales.Locale;

/**
 * The actual data of a connection at runtime. This interface comprises the connection data represented by 
 * {@link IConnectionData} and adds the actual client, user name and locale.
 * @author vwegert
 *
 */
public interface IConnection extends IConnectionData {

	/**
	 * @return the ID of the actual connection. This ID should contain the {@link #getConnectionID() connection ID}, 
	 * the {@link #getClient() client} and the {@link #getUserName() user name}.
	 */
	public String getConnectionID();
	
	/**
	 * @return the actual client
	 */
	public String getClient();

	/**
	 * @return whether the user may change the client during logon
	 */
	public boolean isClientEditable();

	/**
	 * @return the actual user name
	 */
	public String getUserName();

	/**
	 * @return whether the user may change the user name during logon
	 */
	public boolean isUserEditable();

	/**
	 * @return the actual locale
	 */
	public Locale getLocale();

	/**
	 * @return whether the user may change the locale during logon
	 */
	public boolean isLocaleEditable();

	
	
}
