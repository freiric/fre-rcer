/**
 * Copyright (c) 2008, 2010 The RCER Development Team.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
 * this entire header must remain intact.
 * 
 * $Id$
 *
 * $Id$
 */
package net.sf.rcer.rom.ddic;

import net.sf.rcer.rom.RepositoryObject;
import net.sf.rcer.rom.RepositoryObjectCollection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.sf.rcer.rom.ddic.View#getCollection <em>Collection</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.sf.rcer.rom.ddic.DDICPackage#getView()
 * @model
 * @generated
 */
public interface View extends RepositoryObject {
	/**
	 * Returns the value of the '<em><b>Collection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link net.sf.rcer.rom.RepositoryObjectCollection#getViews <em>Views</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection</em>' container reference.
	 * @see net.sf.rcer.rom.ddic.DDICPackage#getView_Collection()
	 * @see net.sf.rcer.rom.RepositoryObjectCollection#getViews
	 * @model opposite="views" transient="false" changeable="false" derived="true" ordered="false"
	 * @generated
	 */
	RepositoryObjectCollection getCollection();

} // View
