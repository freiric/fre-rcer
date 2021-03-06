/**
 * Copyright (c) 2009, 2010 The RCER Development Team.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 *
 * $Id: RFCMappingFormatter.java 235 2009-12-31 17:51:07Z vwegert $
 */
package net.sf.rcer.rfcgen.ui.outline;

import org.eclipse.xtext.ui.editor.outline.actions.DefaultContentOutlineNodeAdapterFactory;

/**
 * TODO Write documentation for type RFCMappingOutlineNodeAdapterFactory.
 * @author vwegert
 *
 */
public class RFCMappingOutlineNodeAdapterFactory extends DefaultContentOutlineNodeAdapterFactory {

	private static final Class<?>[] types = { 
		// provide list of classes to adapt to, e.g.:
		// Entity.class
		// Service.class
	};

	/* (non-Javadoc)
	 * @see org.eclipse.xtext.ui.editor.outline.actions.DefaultContentOutlineNodeAdapterFactory#getAdapterList()
	 */
	@Override
	public Class<?>[] getAdapterList() {
		return types;
	}

}

