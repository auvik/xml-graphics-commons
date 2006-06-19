/*
 * Copyright 2006 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

package org.apache.xmlgraphics.xmp;

import java.util.List;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Represents an XMP array as defined by the XMP specification.
 * @todo Property qualifiers are currently not supported, yet. 
 */
public class XMPArray extends XMPComplexValue {

    private XMPArrayType type;
    private List values = new java.util.ArrayList();
    private List xmllang = new java.util.ArrayList();
    
    /**
     * Main constructor
     * @param type the intended type of array
     */
    public XMPArray(XMPArrayType type) {
        this.type = type;
    }
    
    /** @return the type of array */
    public XMPArrayType getType() {
        return this.type;
    }
    
    /**
     * Returns the value at a given position
     * @param idx the index of the requested value
     * @return the value at the given position
     */
    public Object getValue(int idx) {
        return this.values.get(idx);
    }

    /** @see org.apache.xmlgraphics.xmp.XMPComplexValue#getSimpleValue() */
    public Object getSimpleValue() {
        if (values.size() == 1) {
            return getValue(0);
        } else {
            return null;
        }
    }

    /**
     * Returns a language-dependant values (available for alternative arrays).
     * @param lang the language ("x-default" for the default value)
     * @return the requested value
     */
    public String getLangValue(String lang) {
        String v = null;
        for (int i = 0, c = values.size(); i < c; i++) {
            String l = (String)xmllang.get(i);
            if ((l == null && lang == null) || (l != null && l.equals(lang))) {
                v = values.get(i).toString();
                break;
            }
        }
        if (lang == null && v == null) {
            v = getLangValue(XMPConstants.DEFAULT_LANGUAGE);
            if (v == null && values.size() > 0) {
                v = getValue(0).toString(); //get first
            }
        }
        return v;
    }
    
    /**
     * Removes a language-dependant value
     * @param lang the language ("x-default" for the default value)
     */
    public void removeLangValue(String lang) {
        if (lang == null && "".equals(lang)) {
            return;
        }
        for (int i = 0, c = values.size(); i < c; i++) {
            String l = (String)xmllang.get(i);
            if (lang.equals(l)) {
                values.remove(i);
                xmllang.remove(i);
                return;
            }
        }
    }
    
    /**
     * Adds a new value to the array
     * @param value the value
     */
    public void add(Object value) {
        values.add(value);
        xmllang.add(null);
    }

    /**
     * Adds a language-dependant value to the array. Make sure not to add the same language twice.
     * @param value the value
     * @param lang the language ("x-default" for the default value)
     */
    public void add(String value, String lang) {
        values.add(value);
        xmllang.add(lang);
    }

    /** @return the current number of value in the array */
    public int getSize() {
        return this.values.size();
    }
    
    /**
     * Converts the array to an object array.
     * @return an object array of all values in the array
     */
    public Object[] toObjectArray() {
        Object[] res = new Object[getSize()];
        for (int i = 0, c = res.length; i < c; i++) {
            res[i] = getValue(i);
        }
        return res;
    }

    /** @see org.apache.xmlgraphics.util.XMLizable#toSAX(org.xml.sax.ContentHandler) */
    public void toSAX(ContentHandler handler) throws SAXException {
        AttributesImpl atts = new AttributesImpl();
        handler.startElement(XMPConstants.RDF_NAMESPACE, 
                type.getName(), "rdf:" + type.getName(), atts);
        for (int i = 0, c = values.size(); i < c; i++) {
            String value = (String)values.get(i);
            String lang = (String)xmllang.get(i);
            atts.clear();
            if (lang != null) {
                atts.addAttribute(XMPConstants.XML_NS, "lang", "xml:lang", "CDATA", lang);
            }
            handler.startElement(XMPConstants.RDF_NAMESPACE, 
                    "li", "rdf:li", atts);
            char[] chars = value.toCharArray();
            handler.characters(chars, 0, chars.length);
            handler.endElement(XMPConstants.RDF_NAMESPACE, 
                    "li", "rdf:li");
        }
        handler.endElement(XMPConstants.RDF_NAMESPACE, 
                type.getName(), "rdf:" + type.getName());
    }

    /** @see java.lang.Object#toString() */
    public String toString() {
        return "XMP array: " + type + ", " + getSize();
    }

    
}
