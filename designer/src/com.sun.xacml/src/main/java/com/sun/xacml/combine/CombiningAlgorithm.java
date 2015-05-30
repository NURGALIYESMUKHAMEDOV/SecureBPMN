
/*
 * @(#)CombiningAlgorithm.java
 *
 * Copyright 2003-2005 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   1. Redistribution of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 * 
 *   2. Redistribution in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 * 
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING
 * ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * OR NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN MICROSYSTEMS, INC. ("SUN")
 * AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE
 * AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES. IN NO EVENT WILL SUN OR ITS LICENSORS BE LIABLE FOR ANY LOST
 * REVENUE, PROFIT OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL,
 * INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY
 * OF LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE THIS SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that this software is not designed or intended for use in
 * the design, construction, operation or maintenance of any nuclear facility.
 */

package com.sun.xacml.combine;


import com.sun.xacml.EvaluationCtx;

import com.sun.xacml.ctx.Result;
import com.sun.xacml.debug.IndirectLocatable;
import com.sun.xacml.debug.RuntimeInfo;

import java.net.URI;

import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;


/**
 * The base type for all combining algorithms. It provides one method that
 * must be implemented.
 *
 * @since 1.0
 * @author Seth Proctor
 */
public abstract class CombiningAlgorithm implements IndirectLocatable
{

    // the identifier for the algorithm
    private URI identifier;
    
	//private RuntimeInfo src;
    private Stack<RuntimeInfo> src;

	private static Logger logger = Logger.getLogger(CombiningAlgorithm.class);
	


    /**
     * Constructor that takes the algorithm's identifier.
     *
     * @param identifier the algorithm's identifier
     */
    public CombiningAlgorithm(URI identifier) {
        this.identifier = identifier;
    }

    /**
     * Combines the results of the inputs based on the context to produce
     * some unified result. This is the one function of a combining algorithm.
     *
     * @param context the representation of the request
     * @param parameters a (possibly empty) non-null <code>List</code> of
     *                   <code>CombinerParameter<code>s provided for general
     *                   use (for all pre-2.0 policies this must be empty)
     * @param inputs a <code>List</code> of <code>CombinerElements</code>s to
     *               evaluate and combine
     *
     * @return a single unified result based on the combining logic
     */
    public abstract Result combine(EvaluationCtx context, List<CombinerParameter> parameters,
    								List<CombinerElement> inputs);

    /**
     * Returns the identifier for this algorithm.
     *
     * @return the algorithm's identifier
     */
    public URI getIdentifier() {
        return this.identifier;
    }
    
    public RuntimeInfo getRuntimeInfo() {
    	if ( this.src != null && this.src.size() > 0 ) {
    		return this.src.get(this.src.size()-1);
    	} else {
    		return null;
    	}
    	
    	//return this.src;
    }
    
    /**
     * Implements the IndirectLocatable interface
     */
    public void setRuntimeInfo(RuntimeInfo src) {
    	if ( this.src == null ) {
    		this.src = new Stack<RuntimeInfo>();
    	}
    	this.src.push(src);
//    	if ( this.src != null ) {
//    		logger.warn("Overwriting SourceLocator! This indicates that " +
//    				"serveral threads are used to query the enginge which " +
//    				"should NOT be the case when the source locator feature " +
//    				"is enabled (overwrite" + 
//    				( this.src == null ? "null" : this.src.getLocationMsgForError()) + " with " + 
//    				( src == null ? "null" : src.getLocationMsgForError()));
//    		try {
//    			throw new Exception();
//    		} catch(Exception e) {
//    			e.printStackTrace();
//    		}
//    	}
//    	this.src = src;
    }
    
    /**
     * Implements the IndirectLocatable interface
     */
    public void unsetRuntimeInfo(RuntimeInfo src) {
    	if ( this.src != null ) {
    		RuntimeInfo src_pop = this.src.pop();
    		if ( src_pop != src ) {
    			logger.warn("Unset Source Locator Object which did not match the current "
    					+ "element on the stack");
    			this.src.removeAllElements();
    		}
    	}
//    	if ( src != this.src ) {
//    		logger.warn("Unset Source Locator Object which is currently unvalid! (from " + 
//    				( this.src == null ? "null" : this.src.getLocationMsgForError()) + " with " + 
//    				( src == null ? "null" : src.getLocationMsgForError()));
//    	}
//    	this.src = null;
    }
}
