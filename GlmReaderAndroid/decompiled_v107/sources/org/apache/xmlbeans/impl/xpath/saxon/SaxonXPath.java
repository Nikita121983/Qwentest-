package org.apache.xmlbeans.impl.xpath.saxon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import net.sf.saxon.Configuration;
import net.sf.saxon.dom.DOMNodeWrapper;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.NamespaceUri;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.SequenceTool;
import net.sf.saxon.sxpath.IndependentContext;
import net.sf.saxon.sxpath.XPathDynamicContext;
import net.sf.saxon.sxpath.XPathEvaluator;
import net.sf.saxon.sxpath.XPathExpression;
import net.sf.saxon.sxpath.XPathVariable;
import net.sf.saxon.tree.wrapper.VirtualNode;
import net.sf.saxon.value.DateTimeValue;
import net.sf.saxon.value.GDateValue;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.xpath.Path;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
public class SaxonXPath implements Path {
    private String contextVar;
    private String defaultNS;
    private final Map<String, String> namespaceMap = new HashMap();
    private String path;

    public SaxonXPath(String path, String contextVar, Map<String, String> namespaceMap) {
        this.path = path;
        this.contextVar = contextVar;
        this.defaultNS = namespaceMap.get(XPath._DEFAULT_ELT_NS);
        this.namespaceMap.putAll(namespaceMap);
        this.namespaceMap.remove(XPath._DEFAULT_ELT_NS);
    }

    @Override // org.apache.xmlbeans.impl.xpath.Path
    public XPathEngine execute(Cur c, XmlOptions options) {
        return new SaxonXPathEngine(this, c);
    }

    public List selectNodes(Object node) {
        try {
            Node contextNode = (Node) node;
            Configuration config = new Configuration();
            IndependentContext sc = new IndependentContext(config);
            if (this.defaultNS != null) {
                sc.setDefaultElementNamespace(NamespaceUri.of(this.defaultNS));
            }
            for (Map.Entry<String, String> entry : this.namespaceMap.entrySet()) {
                sc.declareNamespace(entry.getKey(), NamespaceUri.of(entry.getValue()));
            }
            NodeInfo contextItem = config.unravel(new DOMSource(contextNode));
            XPathEvaluator xpe = new XPathEvaluator(config);
            xpe.setStaticContext(sc);
            XPathVariable thisVar = sc.declareVariable(NamespaceUri.of(""), this.contextVar);
            XPathExpression xpath = xpe.createExpression(this.path);
            XPathDynamicContext dc = xpath.createDynamicContext((Item) null);
            dc.setContextItem(contextItem);
            dc.setVariable(thisVar, contextItem);
            List<Item> saxonNodes = xpath.evaluate(dc);
            List<Object> retNodes = new ArrayList<>(saxonNodes.size());
            for (Item item : saxonNodes) {
                if (item instanceof DOMNodeWrapper) {
                    Node n = getUnderlyingNode((DOMNodeWrapper) item);
                    retNodes.add(n);
                } else if (item instanceof NodeInfo) {
                    retNodes.add(item.getStringValue());
                } else if (item instanceof GDateValue) {
                    retNodes.add(item);
                } else if (item instanceof DateTimeValue) {
                    retNodes.add(item);
                } else {
                    retNodes.add(SequenceTool.convertToJava(item));
                }
            }
            return retNodes;
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public List selectPath(Object node) {
        return selectNodes(node);
    }

    private static Node getUnderlyingNode(VirtualNode v) {
        Object o = v;
        while (o instanceof VirtualNode) {
            o = ((VirtualNode) o).getUnderlyingNode();
        }
        return (Node) o;
    }
}
