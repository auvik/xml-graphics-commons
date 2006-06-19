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

package org.apache.xmlgraphics.xmp.schemas.pdf;

import org.apache.xmlgraphics.xmp.Metadata;
import org.apache.xmlgraphics.xmp.XMPSchemaAdapter;
import org.apache.xmlgraphics.xmp.XMPSchemaRegistry;

/**
 * Schema adapter implementation for the old (deprecated) PDF/A schema. This is still needed to
 * make Adobe Acrobat happy. 
 */
public class PDFAAdapter extends XMPSchemaAdapter {

    /**
     * Constructs a new adapter for Dublin Core around the given metadata object.
     * @param meta the underlying metadata
     */
    public PDFAAdapter(Metadata meta, String namespace) {
        super(meta, XMPSchemaRegistry.getInstance().getSchema(namespace));
    }

    /**
     * Sets the PDF/A version identifier ("part").
     * @param value the version identifier ("1" for PDF/A-1)
     */
    public void setPart(int value) {
        setValue("part", Integer.toString(value));
    }

    /** @return the PDF/A version identifier */
    public int getPart() {
        return Integer.parseInt(getValue("part"));
    }

    /**
     * Sets the PDF/A amendment identifier ("amd").
     * @param value the amendment identifiert
     */
    public void setAmendment(String value) {
        setValue("amd", value);
    }
    
    /** @return the PDF/A amendment identifier */
    public String getAmendment() {
        return getValue("amd");
    }
    
    /**
     * Sets the PDF/A conformance level.
     * @param value the conformance level ("A" or "B" for PDF/A-1)
     */
    public void setConformance(String value) {
        setValue("conformance", value);
    }
    
    /** @return the PDF/A conformance level */
    public String getConformance() {
        return getValue("conformance");
    }
    
}
