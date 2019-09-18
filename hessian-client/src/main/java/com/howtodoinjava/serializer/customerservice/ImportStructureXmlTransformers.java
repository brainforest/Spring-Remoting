package com.howtodoinjava.serializer.customerservice;

import com.legstar.coxb.transform.AbstractXmlTransformers;
import com.legstar.coxb.transform.HostTransformException;

/**
 * XML Transformers provider for ImportStructure.
 *
 */
public class ImportStructureXmlTransformers extends AbstractXmlTransformers {

    /**
     * Create a set of directional transformers.
     * @throws HostTransformException if transformer cannot be created
     */
    public ImportStructureXmlTransformers() throws HostTransformException {
        super(new ImportStructureXmlToHostTransformer(),
                new ImportStructureHostToXmlTransformer());
    }

}
