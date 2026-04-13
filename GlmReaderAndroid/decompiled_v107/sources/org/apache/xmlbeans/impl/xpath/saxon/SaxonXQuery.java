package org.apache.xmlbeans.impl.xpath.saxon;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathException;
import net.sf.saxon.Configuration;
import net.sf.saxon.dom.DOMNodeWrapper;
import net.sf.saxon.dom.DocumentWrapper;
import net.sf.saxon.dom.NodeOverNodeInfo;
import net.sf.saxon.ma.map.HashTrieMap;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.NamespaceUri;
import net.sf.saxon.om.NodeInfo;
import net.sf.saxon.om.StructuredQName;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.str.StringView;
import net.sf.saxon.type.BuiltInAtomicType;
import net.sf.saxon.value.AnyURIValue;
import net.sf.saxon.value.BigDecimalValue;
import net.sf.saxon.value.BigIntegerValue;
import net.sf.saxon.value.BooleanValue;
import net.sf.saxon.value.DateTimeValue;
import net.sf.saxon.value.DateValue;
import net.sf.saxon.value.DoubleValue;
import net.sf.saxon.value.DurationValue;
import net.sf.saxon.value.FloatValue;
import net.sf.saxon.value.GDayValue;
import net.sf.saxon.value.GMonthDayValue;
import net.sf.saxon.value.GMonthValue;
import net.sf.saxon.value.GYearMonthValue;
import net.sf.saxon.value.GYearValue;
import net.sf.saxon.value.HexBinaryValue;
import net.sf.saxon.value.Int64Value;
import net.sf.saxon.value.ObjectValue;
import net.sf.saxon.value.QNameValue;
import net.sf.saxon.value.SaxonDuration;
import net.sf.saxon.value.SaxonXMLGregorianCalendar;
import net.sf.saxon.value.StringValue;
import net.sf.saxon.value.TimeValue;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlTokenSource;
import org.apache.xmlbeans.impl.logging.XmlBeansLogManager;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Cursor;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.xpath.XQuery;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
public class SaxonXQuery implements XQuery {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = XmlBeansLogManager.getLogger(SaxonXQuery.class);
    private Cur _cur;
    private XmlOptions _options;
    private long _version;
    private final Configuration config;
    private final String contextVar;
    private final XQueryExpression xquery;

    public SaxonXQuery(String query, String contextVar, Integer boundary, XmlOptions xmlOptions) {
        if (contextVar.startsWith(".") || contextVar.startsWith("..")) {
            throw new AssertionError();
        }
        this._options = xmlOptions;
        this.config = new Configuration();
        StaticQueryContext sc = this.config.newStaticQueryContext();
        Map<String, String> nsMap = xmlOptions.getLoadAdditionalNamespaces();
        if (nsMap != null) {
            for (Map.Entry<String, String> entry : nsMap.entrySet()) {
                sc.declareNamespace(entry.getKey(), NamespaceUri.of(entry.getValue()));
            }
        }
        this.contextVar = contextVar;
        try {
            this.xquery = sc.compileQuery(query.substring(0, boundary.intValue()) + " declare variable $" + contextVar + " external;" + query.substring(boundary.intValue()));
        } catch (TransformerException e) {
            throw new XmlRuntimeException(e);
        }
    }

    @Override // org.apache.xmlbeans.impl.xpath.XQuery
    public XmlObject[] objectExecute(Cur c, XmlOptions options) {
        Cur res;
        this._version = c.getLocale().version();
        this._cur = c.weakCur(this);
        this._options = options;
        Map<String, Object> bindings = XmlOptions.maskNull(this._options).getXqueryVariables();
        List<Object> resultsList = execQuery(this._cur.getDom(), bindings);
        XmlObject[] result = new XmlObject[resultsList.size()];
        for (int i = 0; i < resultsList.size(); i++) {
            Locale l = Locale.getLocale(this._cur.getLocale().getSchemaTypeLoader(), this._options);
            l.enter();
            Object node = resultsList.get(i);
            try {
                try {
                    if (!(node instanceof Node)) {
                        res = l.load("<xml-fragment/>").tempCur();
                        res.setValue(node.toString());
                        SchemaType type = getType(node);
                        Locale.autoTypeDocument(res, type, null);
                        result[i] = res.getObject();
                    } else {
                        res = loadNode(l, (Node) node);
                    }
                    result[i] = res.getObject();
                    l.exit();
                    res.release();
                } catch (XmlException e) {
                    throw new RuntimeException(e);
                }
            } catch (Throwable th) {
                l.exit();
                throw th;
            }
        }
        release();
        return result;
    }

    @Override // org.apache.xmlbeans.impl.xpath.XQuery
    public XmlCursor cursorExecute(Cur c, XmlOptions options) {
        this._version = c.getLocale().version();
        this._cur = c.weakCur(this);
        this._options = options;
        Map<String, Object> bindings = XmlOptions.maskNull(this._options).getXqueryVariables();
        List<Object> resultsList = execQuery(this._cur.getDom(), bindings);
        Locale locale = Locale.getLocale(this._cur.getLocale().getSchemaTypeLoader(), this._options);
        locale.enter();
        Locale.LoadContext _context = new Cur.CurLoadContext(locale, this._options);
        Cursor resultCur = null;
        for (int i = 0; i < resultsList.size(); i++) {
            try {
                loadNodeHelper(locale, (Node) resultsList.get(i), _context);
            } catch (XmlException e) {
                LOG.atInfo().withThrowable(e).log("Can't autotype document");
            } finally {
                locale.exit();
            }
        }
        Cur c2 = _context.finish();
        Locale.associateSourceName(c, this._options);
        Locale.autoTypeDocument(c, null, this._options);
        resultCur = new Cursor(c2);
        release();
        return resultCur;
    }

    public List<Object> execQuery(Object node, Map<String, Object> variableBindings) {
        try {
            Node contextNode = (Node) node;
            Document dom = contextNode.getNodeType() == 9 ? (Document) contextNode : contextNode.getOwnerDocument();
            DocumentWrapper docWrapper = new DocumentWrapper(dom, (String) null, this.config);
            DOMNodeWrapper wrap = docWrapper.wrap(contextNode);
            NamespaceUri emptyUri = NamespaceUri.of("");
            DynamicQueryContext dc = new DynamicQueryContext(this.config);
            dc.setContextItem(wrap);
            dc.setParameter(new StructuredQName("", emptyUri, this.contextVar), wrap);
            if (variableBindings != null) {
                for (Map.Entry<String, Object> me : variableBindings.entrySet()) {
                    StructuredQName key = new StructuredQName("", emptyUri, me.getKey());
                    Object value = me.getValue();
                    if (value instanceof XmlTokenSource) {
                        Node paramObject = ((XmlTokenSource) value).getDomNode();
                        dc.setParameter(key, docWrapper.wrap(paramObject));
                    } else {
                        try {
                            dc.setParameter(key, objectToItem(value, this.config));
                        } catch (XPathException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            List<Object> saxonNodes = this.xquery.evaluate(dc);
            ListIterator<Object> it = saxonNodes.listIterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof NodeInfo) {
                    it.set(NodeOverNodeInfo.wrap((NodeInfo) o));
                }
            }
            return saxonNodes;
        } catch (TransformerException e2) {
            throw new RuntimeException("Error binding " + this.contextVar, e2);
        }
    }

    private static Item objectToItem(Object value, Configuration config) throws XPathException, net.sf.saxon.trans.XPathException {
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return BooleanValue.get(((Boolean) value).booleanValue());
        }
        if (value instanceof byte[]) {
            return new HexBinaryValue((byte[]) value);
        }
        if (value instanceof Byte) {
            return new Int64Value(((Byte) value).byteValue(), BuiltInAtomicType.BYTE, false);
        }
        if (value instanceof Float) {
            return new FloatValue(((Float) value).floatValue());
        }
        if (value instanceof Double) {
            return new DoubleValue(((Double) value).doubleValue());
        }
        if (value instanceof Integer) {
            return new Int64Value(((Integer) value).intValue(), BuiltInAtomicType.INT, false);
        }
        if (value instanceof Long) {
            return new Int64Value(((Long) value).longValue(), BuiltInAtomicType.LONG, false);
        }
        if (value instanceof Short) {
            return new Int64Value(((Short) value).shortValue(), BuiltInAtomicType.SHORT, false);
        }
        if (value instanceof String) {
            return new StringValue((String) value);
        }
        if (value instanceof BigDecimal) {
            return new BigDecimalValue((BigDecimal) value);
        }
        if (value instanceof BigInteger) {
            return new BigIntegerValue((BigInteger) value);
        }
        if (value instanceof SaxonDuration) {
            return ((SaxonDuration) value).getDurationValue();
        }
        if (value instanceof Duration) {
            Duration dv = (Duration) value;
            return new DurationValue(dv.getSign() >= 0, dv.getYears(), dv.getMonths(), dv.getDays(), dv.getHours(), dv.getMinutes(), dv.getSeconds(), 0);
        }
        if (value instanceof SaxonXMLGregorianCalendar) {
            return ((SaxonXMLGregorianCalendar) value).toCalendarValue();
        }
        if (value instanceof XMLGregorianCalendar) {
            XMLGregorianCalendar g = (XMLGregorianCalendar) value;
            QName gtype = g.getXMLSchemaType();
            if (gtype.equals(DatatypeConstants.DATETIME)) {
                return DateTimeValue.makeDateTimeValue(StringView.tidy(value.toString()), config.getConversionRules()).asAtomic();
            }
            if (gtype.equals(DatatypeConstants.DATE)) {
                return DateValue.makeDateValue(StringView.tidy(value.toString()), config.getConversionRules()).asAtomic();
            }
            if (gtype.equals(DatatypeConstants.TIME)) {
                return TimeValue.makeTimeValue(StringView.tidy(value.toString())).asAtomic();
            }
            if (gtype.equals(DatatypeConstants.GYEAR)) {
                return GYearValue.makeGYearValue(StringView.tidy(value.toString()), config.getConversionRules()).asAtomic();
            }
            if (gtype.equals(DatatypeConstants.GYEARMONTH)) {
                return GYearMonthValue.makeGYearMonthValue(StringView.tidy(value.toString()), config.getConversionRules()).asAtomic();
            }
            if (gtype.equals(DatatypeConstants.GMONTH)) {
                String val = value.toString();
                if (val.endsWith("--")) {
                    val = val.substring(0, val.length() - 2);
                }
                return GMonthValue.makeGMonthValue(StringView.tidy(val)).asAtomic();
            }
            if (gtype.equals(DatatypeConstants.GMONTHDAY)) {
                return GMonthDayValue.makeGMonthDayValue(StringView.tidy(value.toString())).asAtomic();
            }
            if (gtype.equals(DatatypeConstants.GDAY)) {
                return GDayValue.makeGDayValue(StringView.tidy(value.toString())).asAtomic();
            }
            throw new AssertionError("Unknown Gregorian date type");
        }
        if (value instanceof QName) {
            QName q = (QName) value;
            return new QNameValue(q.getPrefix(), NamespaceUri.of(q.getNamespaceURI()), q.getLocalPart());
        }
        if (value instanceof URI) {
            return new AnyURIValue(value.toString());
        }
        if (value instanceof Map) {
            HashTrieMap htm = new HashTrieMap();
            for (Map.Entry<?, ?> me : ((Map) value).entrySet()) {
                htm.initialPut(objectToItem(me.getKey(), config), objectToItem(me.getValue(), config));
            }
            return htm;
        }
        return new ObjectValue(value);
    }

    private SchemaType getType(Object node) {
        if (node instanceof Integer) {
            SchemaType type = XmlInteger.type;
            return type;
        }
        if (node instanceof Double) {
            SchemaType type2 = XmlDouble.type;
            return type2;
        }
        if (node instanceof Long) {
            SchemaType type3 = XmlLong.type;
            return type3;
        }
        if (node instanceof Float) {
            SchemaType type4 = XmlFloat.type;
            return type4;
        }
        if (node instanceof BigDecimal) {
            SchemaType type5 = XmlDecimal.type;
            return type5;
        }
        if (node instanceof Boolean) {
            SchemaType type6 = XmlBoolean.type;
            return type6;
        }
        if (node instanceof String) {
            SchemaType type7 = XmlString.type;
            return type7;
        }
        if (node instanceof Date) {
            SchemaType type8 = XmlDate.type;
            return type8;
        }
        SchemaType type9 = XmlAnySimpleType.type;
        return type9;
    }

    public void release() {
        if (this._cur != null) {
            this._cur.release();
            this._cur = null;
        }
    }

    private Cur loadNode(Locale locale, Node node) {
        Locale.LoadContext context = new Cur.CurLoadContext(locale, this._options);
        try {
            loadNodeHelper(locale, node, context);
            Cur c = context.finish();
            Locale.associateSourceName(c, this._options);
            Locale.autoTypeDocument(c, null, this._options);
            return c;
        } catch (Exception e) {
            throw new XmlRuntimeException(e.getMessage(), e);
        }
    }

    private void loadNodeHelper(Locale locale, Node node, Locale.LoadContext context) {
        if (node.getNodeType() == 2) {
            QName attName = new QName(node.getNamespaceURI(), node.getLocalName(), node.getPrefix());
            context.attr(attName, node.getNodeValue());
        } else {
            locale.loadNode(node, context);
        }
    }
}
