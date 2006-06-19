/*
 * Copyright 2006 The Apache Software Foundation.
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
import org.apache.xmlgraphics.xmp.XMPConstants;
import org.apache.xmlgraphics.xmp.XMPSchema;
import org.apache.xmlgraphics.xmp.merge.MergeRuleSet;

/**
 * XMP Schema for PDF/A (ISO 19005-1). This schema uses a namespace URI used in a draft version
 * of the ISO standard which is still necessary because of a bug in Adobe Acrobat.
 */
public class PDFAOldXMPSchema extends XMPSchema {

    /** Namespace URI for Dublin Core */ 
    public static final String NAMESPACE = XMPConstants.PDF_A_IDENTIFICATION_OLD;
    
    private static MergeRuleSet mergeRuleSet = new MergeRuleSet();
    
    /** Creates a new schema instance for Dublin Core. */
    public PDFAOldXMPSchema() {
        super(NAMESPACE, "pdfaid_1");
    }
    
    /**
     * Creates and returns an adapter for this schema around the given metadata object.
     * @param meta the metadata object
     * @return the newly instantiated adapter
     */
    public static PDFAAdapter getAdapter(Metadata meta) {
        return new PDFAAdapter(meta, NAMESPACE);
    }

    /** @see org.apache.xmlgraphics.xmp.XMPSchema#getDefaultMergeRuleSet() */
    public MergeRuleSet getDefaultMergeRuleSet() {
        return mergeRuleSet;
    }
    
    
}
