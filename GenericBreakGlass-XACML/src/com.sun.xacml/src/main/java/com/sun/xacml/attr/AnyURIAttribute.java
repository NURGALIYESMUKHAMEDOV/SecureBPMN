
/*
 * @(#)AnyURIAttribute.java
 *
 * Copyright 2003-2004 Sun Microsystems, Inc. All Rights Reserved.
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

package com.sun.xacml.attr;

import java.net.URI;
import java.net.URISyntaxException;

import org.w3c.dom.Node;

import com.sun.xacml.ParsingException;


/**
 * Representation of an xs:anyURI value. This class supports parsing
 * xs:anyURI values.
 *
 * @since 1.0
 * @author Seth Proctor
 */
public class AnyURIAttribute extends AttributeValue
{

    /**
     * Official name of this type
     */
    public static final String identifier = 
    		TypeIdentifierConstants.ANYURI;
        

    //URI version of name for this type
    private static URI identifierURI;

    // RuntimeException that wraps an Exception thrown during the
    // creation of identifierURI, null if none
    private static RuntimeException earlyException;

    /**
     * Static initializer that initializes the identifierURI
     * class field so that we can catch any exceptions thrown
     * by URI(String) and transform them into a RuntimeException.
     * Such exceptions should never happen but should be reported
     * properly if they ever do.
     */
    static {
        try {
            identifierURI = URI.create(identifier);
        } catch (IllegalArgumentException e) {
            earlyException = e;
        }
    }

    // the URI value that this class represents
    private URI value;

    /**
     * Creates a new <code>AnyURIAttribute</code> that represents
     * the URI value supplied.
     *
     * @param value the <code>URI</code> value to be represented
     */
    public AnyURIAttribute(URI value) {
        super(identifierURI);

        // Shouldn't happen, but just in case...
        if (earlyException != null) {
            throw earlyException;
        }
        this.value = value;
    }

    /**
     * Returns a new <code>AnyURIAttribute</code> that represents
     * the xs:anyURI at a particular DOM node.
     *
     * @param root the <code>Node</code> that contains the desired value
     *
     * @return a new <code>AnyURIAttribute</code> representing the
     *         appropriate value (null if there is a parsing error)
     *         
     * @throws URISyntaxException 
     * @throws ParsingException 
     */
    public static AnyURIAttribute getInstance(Node root)
            throws URISyntaxException, ParsingException {
        if (root.getFirstChild() != null) {
            return getInstance(root.getFirstChild().getNodeValue());
        }
        throw new ParsingException("Error while parsing" 
                + "a AnyURIAttribute");
    }

    /**
     * Returns a new <code>AnyURIAttribute</code> that represents
     * the xs:anyURI value indicated by the <code>String</code> provided.
     *
     * @param value a string representing the desired value
     *
     * @return a new <code>AnyURIAttribute</code> representing the
     *         appropriate value
     *         
     * @throws URISyntaxException 
     * @throws ParsingException 
     */
    public static AnyURIAttribute getInstance(String value)
            throws URISyntaxException, ParsingException {
        if (value == null) {
            throw new ParsingException("Can't create a " 
                    + "AnyURIAttribute from null input");
        }
        return new AnyURIAttribute(new URI(value));
    }

    /**
     * Returns the <code>URI</code> value represented by this object.
     *
     * @return the <code>URI</code> value
     */
    public URI getValue() {
        return this.value;
    }

    /**
     * Returns true if the input is an instance of this class and if its
     * value equals the value contained in this class.
     *
     * @param o the object to compare
     *
     * @return true if this object and the input represent the same value
     */
    public boolean equals(Object o) {
        if (! (o instanceof AnyURIAttribute)) {
            return false;
        }
        AnyURIAttribute other = (AnyURIAttribute)o;

        return this.value.equals(other.value);
    }

    /**
     * Returns the hashcode value used to index and compare this object with
     * others of the same type. Typically this is the hashcode of the backing
     * data object.
     *
     * @return the object's hashcode value
     */
    public int hashCode() {
        return this.value.hashCode();
    }

    /**
     * Converts to a String representation.
     *
     * @return the String representation
     */
    public String toString() {
        return "AnyURIAttribute: \"" + this.value.toString() + "\"";
    }

    /**
     * @return The AttributeValue encoded as a <code>String</code>.
     *
     */
    public String encode() {
        return this.value.toString();
    }

}
