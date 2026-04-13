package org.apache.poi.poifs.crypt.dsig;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

/* loaded from: classes10.dex */
public class SignatureMarshalDefaultListener implements SignatureMarshalListener {
    private static final String OBJECT_TAG = "Object";
    private static final Set<String> IGNORE_NS = new HashSet(Arrays.asList(null, "http://www.w3.org/2000/xmlns/", SignatureFacet.XML_DIGSIG_NS));
    private static final List<String> DIRECT_NS = Collections.unmodifiableList(Arrays.asList("http://schemas.openxmlformats.org/package/2006/digital-signature", SignatureFacet.MS_DIGSIG_NS));

    @Override // org.apache.poi.poifs.crypt.dsig.SignatureMarshalListener
    public void handleElement(SignatureInfo signatureInfo, Document doc, EventTarget target, EventListener parentListener) {
        final DocumentTraversal traversal = (DocumentTraversal) doc;
        final Map<String, String> prefixCfg = signatureInfo.getSignatureConfig().getNamespacePrefixes();
        final Map<String, String> prefixUsed = new HashMap<>();
        forEachElement(doc.getElementsByTagName(OBJECT_TAG), new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureMarshalDefaultListener$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureMarshalDefaultListener.this.m2501xd8f241fe(traversal, prefixCfg, prefixUsed, (Element) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$handleElement$2$org-apache-poi-poifs-crypt-dsig-SignatureMarshalDefaultListener, reason: not valid java name */
    public /* synthetic */ void m2501xd8f241fe(final DocumentTraversal traversal, final Map prefixCfg, final Map prefixUsed, Element o) {
        forEachElement(o.getChildNodes(), new Consumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureMarshalDefaultListener$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SignatureMarshalDefaultListener.this.m2502xf8805d6a(traversal, prefixCfg, prefixUsed, (Element) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$null$1$org-apache-poi-poifs-crypt-dsig-SignatureMarshalDefaultListener, reason: not valid java name */
    public /* synthetic */ void m2502xf8805d6a(DocumentTraversal traversal, Map prefixCfg, Map prefixUsed, final Element c) {
        getAllNamespaces(traversal, c, prefixCfg, prefixUsed);
        prefixUsed.forEach(new BiConsumer() { // from class: org.apache.poi.poifs.crypt.dsig.SignatureMarshalDefaultListener$$ExternalSyntheticLambda2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                SignatureMarshalDefaultListener.setXmlns(c, (String) obj2, (String) obj);
            }
        });
    }

    private static void forEachElement(NodeList nl, Consumer<Element> consumer) {
        int len = nl.getLength();
        for (int i = 0; i < len; i++) {
            Node n = nl.item(i);
            if (n instanceof Element) {
                consumer.accept((Element) n);
            }
        }
    }

    private void getAllNamespaces(DocumentTraversal traversal, Element objNode, Map<String, String> prefixCfg, Map<String, String> prefixUsed) {
        prefixUsed.clear();
        NodeIterator iter = traversal.createNodeIterator(objNode, 1, (NodeFilter) null, false);
        while (true) {
            try {
                Element node = (Element) iter.nextNode();
                if (node != null) {
                    setPrefix(node, prefixCfg, prefixUsed);
                    NamedNodeMap nnm = node.getAttributes();
                    int nnmLen = nnm.getLength();
                    for (int j = 0; j < nnmLen; j++) {
                        setPrefix(nnm.item(j), prefixCfg, prefixUsed);
                    }
                } else {
                    return;
                }
            } finally {
                iter.detach();
            }
        }
    }

    private void setPrefix(Node node, Map<String, String> prefixCfg, Map<String, String> prefixUsed) {
        String ns = node.getNamespaceURI();
        String prefix = prefixCfg.get(ns);
        if (IGNORE_NS.contains(ns)) {
            return;
        }
        if (prefix != null) {
            node.setPrefix(prefix);
        }
        if (DIRECT_NS.contains(ns)) {
            setXmlns(node, prefix, ns);
        } else {
            prefixUsed.put(ns, prefix);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setXmlns(Node node, String prefix, String ns) {
        if ((node instanceof Element) && !ns.equals(node.getParentNode().getNamespaceURI())) {
            ((Element) node).setAttributeNS("http://www.w3.org/2000/xmlns/", Sax2Dom.XMLNS_PREFIX + (prefix == null ? "" : ":" + prefix), ns);
        }
    }
}
