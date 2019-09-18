package com.howtodoinjava.serializer.customerservice;

import com.legstar.coxb.CobolContext;
import com.legstar.coxb.transform.AbstractHostToXmlTransformer;
import com.legstar.coxb.transform.HostTransformException;

/**
 * Transforms mainframe data to XML.
 * <p/>
 * This is a typical use of this class:
 * <pre>
 *  ExportStructureHostToXmlTransformer transformer = new ExportStructureHostToXmlTransformer();
 *  StringWriter writer = new StringWriter();
 *  transformer.transform(hostByteArray, writer);
 * </pre>
 *
 */
public class ExportStructureHostToXmlTransformer extends AbstractHostToXmlTransformer {

    /**
     * Create a Host to XML transformer using a Host to Java transformer.
     * @throws HostTransformException if transformer cannot be created
     */
    public ExportStructureHostToXmlTransformer() throws HostTransformException {
        super(new ExportStructureHostToJavaTransformer());
    }
    
    /**
     * Create a Host to XML transformer using a specific COBOL parameters set.
     * @param cobolContext the COBOL parameters set.
     * @throws HostTransformException if transformer cannot be created
     */
    public ExportStructureHostToXmlTransformer(
            final CobolContext cobolContext) throws HostTransformException {
        super(new ExportStructureHostToJavaTransformer(cobolContext));
    }

    /**
     * Create a Host to XML transformer using a specific host character set while
     * other COBOL parameters are set by default.
     * @param hostCharset the host character set
     * @throws HostTransformException if transformer cannot be created
     */
    public ExportStructureHostToXmlTransformer(
            final String hostCharset) throws HostTransformException {
        super(new ExportStructureHostToJavaTransformer(hostCharset));
    }
    
    /** {@inheritDoc} */
    public String getElementName() {
        return "ExportStructure";
    }

    /** {@inheritDoc} */
    public String getNamespace() {
        return "http://coxb.test.legstar.com/customerservice";
    }

    /** {@inheritDoc} */
    public boolean isXmlRootElement() {
        return false;
    }
    
}
