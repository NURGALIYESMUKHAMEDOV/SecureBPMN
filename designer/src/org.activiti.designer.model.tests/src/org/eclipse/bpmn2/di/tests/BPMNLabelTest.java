/**
 * <copyright>
 * 
 * Copyright (c) 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Reiner Hille-Doering (SAP AG) - initial API and implementation and/or initial documentation
 * 
 * </copyright>
 */
package org.eclipse.bpmn2.di.tests;

import junit.textui.TestRunner;

import org.eclipse.bpmn2.di.BPMNLabel;
import org.eclipse.bpmn2.di.BpmnDiFactory;

import org.eclipse.dd.di.tests.LabelTest;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>BPMN Label</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class BPMNLabelTest extends LabelTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(BPMNLabelTest.class);
	}

	/**
	 * Constructs a new BPMN Label test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BPMNLabelTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this BPMN Label test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected BPMNLabel getFixture() {
		return (BPMNLabel) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(BpmnDiFactory.eINSTANCE.createBPMNLabel());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //BPMNLabelTest
