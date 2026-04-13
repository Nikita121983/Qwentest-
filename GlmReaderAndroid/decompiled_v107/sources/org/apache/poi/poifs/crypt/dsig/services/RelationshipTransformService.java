package org.apache.poi.poifs.crypt.dsig.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.Provider;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import javax.xml.crypto.Data;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.OctetStreamData;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.XMLStructure;
import javax.xml.crypto.dom.DOMStructure;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.crypto.dsig.TransformService;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import org.apache.jcp.xml.dsig.internal.dom.ApacheNodeSetData;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.CTRelationshipReference;
import org.openxmlformats.schemas.xpackage.x2006.digitalSignature.RelationshipReferenceDocument;
import org.w3.x2000.x09.xmldsig.TransformDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public class RelationshipTransformService extends TransformService {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) RelationshipTransformService.class);
    public static final String TRANSFORM_URI = "http://schemas.openxmlformats.org/package/2006/RelationshipTransform";
    private final List<String> sourceIds;

    /* loaded from: classes10.dex */
    public static class RelationshipTransformParameterSpec implements TransformParameterSpec {
        List<String> sourceIds = new ArrayList();

        public void addRelationshipReference(String relationshipId) {
            this.sourceIds.add(relationshipId);
        }

        public boolean hasSourceIds() {
            return !this.sourceIds.isEmpty();
        }
    }

    /* loaded from: classes10.dex */
    private static final class POIXmlDsigProvider extends Provider {
        private static final String NAME = "POIXmlDsigProvider";
        static final long serialVersionUID = 1;

        private POIXmlDsigProvider() {
            super(NAME, 1.0d, NAME);
            put("TransformService.http://schemas.openxmlformats.org/package/2006/RelationshipTransform", RelationshipTransformService.class.getName());
            put("TransformService.http://schemas.openxmlformats.org/package/2006/RelationshipTransform MechanismType", "DOM");
        }
    }

    public RelationshipTransformService() {
        LOG.atDebug().log("constructor");
        this.sourceIds = new ArrayList();
    }

    public static synchronized void registerDsigProvider() {
        synchronized (RelationshipTransformService.class) {
            if (Security.getProvider("POIXmlDsigProvider") == null) {
                Security.addProvider(new POIXmlDsigProvider());
            }
        }
    }

    public void init(TransformParameterSpec params) throws InvalidAlgorithmParameterException {
        LOG.atDebug().log("init(params)");
        if (!(params instanceof RelationshipTransformParameterSpec)) {
            throw new InvalidAlgorithmParameterException();
        }
        RelationshipTransformParameterSpec relParams = (RelationshipTransformParameterSpec) params;
        this.sourceIds.addAll(relParams.sourceIds);
    }

    public void init(XMLStructure parent, XMLCryptoContext context) throws InvalidAlgorithmParameterException {
        LOG.atDebug().log("init(parent,context)");
        LOG.atDebug().log("parent java type: {}", parent.getClass().getName());
        DOMStructure domParent = (DOMStructure) parent;
        Node parentNode = domParent.getNode();
        try {
            TransformDocument transDoc = TransformDocument.Factory.parse(parentNode, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            XmlObject[] xoList = transDoc.getTransform().selectChildren(RelationshipReferenceDocument.type.getDocumentElementName());
            if (xoList.length == 0) {
                LOG.atWarn().log("no RelationshipReference/@SourceId parameters present");
            }
            for (XmlObject xo : xoList) {
                String sourceId = ((CTRelationshipReference) xo).getSourceId();
                LOG.atDebug().log("sourceId: {}", sourceId);
                this.sourceIds.add(sourceId);
            }
        } catch (XmlException e) {
            throw new InvalidAlgorithmParameterException(e);
        }
    }

    public void marshalParams(XMLStructure parent, XMLCryptoContext context) throws MarshalException {
        LOG.atDebug().log("marshallParams(parent,context)");
        DOMStructure domParent = (DOMStructure) parent;
        Element parentNode = (Element) domParent.getNode();
        Document doc = parentNode.getOwnerDocument();
        for (String sourceId : this.sourceIds) {
            Element el = doc.createElementNS("http://schemas.openxmlformats.org/package/2006/digital-signature", "mdssi:RelationshipReference");
            el.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:mdssi", "http://schemas.openxmlformats.org/package/2006/digital-signature");
            el.setAttribute("SourceId", sourceId);
            parentNode.appendChild(el);
        }
    }

    public AlgorithmParameterSpec getParameterSpec() {
        LOG.atDebug().log("getParameterSpec");
        return null;
    }

    public Data transform(Data data, XMLCryptoContext context) throws TransformException {
        LOG.atDebug().log("transform(data,context)");
        LOG.atDebug().log("data java type: {}", data.getClass().getName());
        OctetStreamData octetStreamData = (OctetStreamData) data;
        LOG.atDebug().log("URI: {}", octetStreamData.getURI());
        InputStream octetStream = octetStreamData.getOctetStream();
        try {
            Document doc = DocumentHelper.readDocument(octetStream);
            Element root = doc.getDocumentElement();
            NodeList nl = root.getChildNodes();
            TreeMap<String, Element> rsList = new TreeMap<>();
            for (int i = nl.getLength() - 1; i >= 0; i--) {
                Node n = nl.item(i);
                if (PackageRelationship.RELATIONSHIP_TAG_NAME.equals(n.getLocalName())) {
                    Element el = (Element) n;
                    String id = el.getAttribute(PackageRelationship.ID_ATTRIBUTE_NAME);
                    if (this.sourceIds.contains(id)) {
                        String targetMode = el.getAttribute(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME);
                        if (targetMode == null || targetMode.isEmpty()) {
                            el.setAttribute(PackageRelationship.TARGET_MODE_ATTRIBUTE_NAME, "Internal");
                        }
                        rsList.put(id, el);
                    }
                }
                root.removeChild(n);
            }
            Iterator<Element> it = rsList.values().iterator();
            while (it.hasNext()) {
                root.appendChild(it.next());
            }
            LOG.atDebug().log("# Relationship elements: {}", Unbox.box(rsList.size()));
            return new ApacheNodeSetData(new XMLSignatureInput(root));
        } catch (Exception e) {
            throw new TransformException(e.getMessage(), e);
        }
    }

    public Data transform(Data data, XMLCryptoContext context, OutputStream os) throws TransformException {
        LOG.atDebug().log("transform(data,context,os)");
        return null;
    }

    public boolean isFeatureSupported(String feature) {
        LOG.atDebug().log("isFeatureSupported(feature)");
        return false;
    }
}
