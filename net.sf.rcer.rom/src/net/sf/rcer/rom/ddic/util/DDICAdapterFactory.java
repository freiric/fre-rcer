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
package net.sf.rcer.rom.ddic.util;

import net.sf.rcer.rom.RepositoryObject;

import net.sf.rcer.rom.ddic.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.sf.rcer.rom.ddic.DDICPackage
 * @generated
 */
public class DDICAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DDICPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DDICAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = DDICPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DDICSwitch<Adapter> modelSwitch =
		new DDICSwitch<Adapter>() {
			@Override
			public Adapter caseDomain(Domain object) {
				return createDomainAdapter();
			}
			@Override
			public Adapter caseDomainValue(DomainValue object) {
				return createDomainValueAdapter();
			}
			@Override
			public Adapter caseDomainValueSingle(DomainValueSingle object) {
				return createDomainValueSingleAdapter();
			}
			@Override
			public Adapter caseDomainValueRange(DomainValueRange object) {
				return createDomainValueRangeAdapter();
			}
			@Override
			public Adapter caseDataElement(DataElement object) {
				return createDataElementAdapter();
			}
			@Override
			public Adapter caseStructure(Structure object) {
				return createStructureAdapter();
			}
			@Override
			public Adapter caseTable(Table object) {
				return createTableAdapter();
			}
			@Override
			public Adapter caseStructureField(StructureField object) {
				return createStructureFieldAdapter();
			}
			@Override
			public Adapter caseTableField(TableField object) {
				return createTableFieldAdapter();
			}
			@Override
			public Adapter caseNamedField(NamedField object) {
				return createNamedFieldAdapter();
			}
			@Override
			public Adapter caseStructureInclusion(StructureInclusion object) {
				return createStructureInclusionAdapter();
			}
			@Override
			public Adapter caseSingleField(SingleField object) {
				return createSingleFieldAdapter();
			}
			@Override
			public Adapter caseDataElementField(DataElementField object) {
				return createDataElementFieldAdapter();
			}
			@Override
			public Adapter caseDirectField(DirectField object) {
				return createDirectFieldAdapter();
			}
			@Override
			public Adapter caseStructuredField(StructuredField object) {
				return createStructuredFieldAdapter();
			}
			@Override
			public Adapter caseTabularField(TabularField object) {
				return createTabularFieldAdapter();
			}
			@Override
			public Adapter caseTableType(TableType object) {
				return createTableTypeAdapter();
			}
			@Override
			public Adapter caseView(View object) {
				return createViewAdapter();
			}
			@Override
			public Adapter caseSearchHelp(SearchHelp object) {
				return createSearchHelpAdapter();
			}
			@Override
			public Adapter caseEnqueueObject(EnqueueObject object) {
				return createEnqueueObjectAdapter();
			}
			@Override
			public Adapter caseRepositoryObject(RepositoryObject object) {
				return createRepositoryObjectAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.Domain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.Domain
	 * @generated
	 */
	public Adapter createDomainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.DomainValue <em>Domain Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.DomainValue
	 * @generated
	 */
	public Adapter createDomainValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.DomainValueSingle <em>Domain Value Single</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.DomainValueSingle
	 * @generated
	 */
	public Adapter createDomainValueSingleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.DomainValueRange <em>Domain Value Range</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.DomainValueRange
	 * @generated
	 */
	public Adapter createDomainValueRangeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.DataElement <em>Data Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.DataElement
	 * @generated
	 */
	public Adapter createDataElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.Structure <em>Structure</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.Structure
	 * @generated
	 */
	public Adapter createStructureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.Table
	 * @generated
	 */
	public Adapter createTableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.StructureField <em>Structure Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.StructureField
	 * @generated
	 */
	public Adapter createStructureFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.TableField <em>Table Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.TableField
	 * @generated
	 */
	public Adapter createTableFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.NamedField <em>Named Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.NamedField
	 * @generated
	 */
	public Adapter createNamedFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.StructureInclusion <em>Structure Inclusion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.StructureInclusion
	 * @generated
	 */
	public Adapter createStructureInclusionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.SingleField <em>Single Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.SingleField
	 * @generated
	 */
	public Adapter createSingleFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.DataElementField <em>Data Element Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.DataElementField
	 * @generated
	 */
	public Adapter createDataElementFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.DirectField <em>Direct Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.DirectField
	 * @generated
	 */
	public Adapter createDirectFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.StructuredField <em>Structured Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.StructuredField
	 * @generated
	 */
	public Adapter createStructuredFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.TabularField <em>Tabular Field</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.TabularField
	 * @generated
	 */
	public Adapter createTabularFieldAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.TableType <em>Table Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.TableType
	 * @generated
	 */
	public Adapter createTableTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.View <em>View</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.View
	 * @generated
	 */
	public Adapter createViewAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.SearchHelp <em>Search Help</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.SearchHelp
	 * @generated
	 */
	public Adapter createSearchHelpAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.ddic.EnqueueObject <em>Enqueue Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.ddic.EnqueueObject
	 * @generated
	 */
	public Adapter createEnqueueObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.sf.rcer.rom.RepositoryObject <em>Repository Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.sf.rcer.rom.RepositoryObject
	 * @generated
	 */
	public Adapter createRepositoryObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //DDICAdapterFactory
