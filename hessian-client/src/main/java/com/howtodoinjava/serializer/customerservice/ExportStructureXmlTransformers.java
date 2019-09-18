package com.howtodoinjava.serializer.customerservice;

import com.legstar.coxb.transform.AbstractXmlTransformers;
import com.legstar.coxb.transform.HostTransformException;

/**
 * XML Transformers provider for ExportStructure.
 *
 */
public class ExportStructureXmlTransformers extends AbstractXmlTransformers {

    /**
     * Create a set of directional transformers.
     * @throws HostTransformException if transformer cannot be created
     */
    public ExportStructureXmlTransformers() throws HostTransformException {
        super(new ExportStructureXmlToHostTransformer(),
                new ExportStructureHostToXmlTransformer());
    }

}
