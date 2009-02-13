/**
 * Copyright (c) 2008 The RCER Development Team.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 *
 * $Id$
 */
package net.sf.rcer.conn.tools;

import java.text.MessageFormat;

/**
 * This exception occurs whenever someone tries to access a table with RFC_READ_TABLE and 
 * requests a field that does not exist. 
 * @author vwegert
 *
 */
public class FieldNotFoundException extends Exception {
	
	/**
	 * Required for serialization.
	 */
	private static final long serialVersionUID = 3633403413739407362L;

	/**
	 * Default constructor.
	 * @param tableName the name of the table
	 * @param fieldName the name of the field
	 */
	public FieldNotFoundException(String tableName, String fieldName) {
		super(MessageFormat.format("No field named {0} exists in table {1}", fieldName, tableName));
	}

}