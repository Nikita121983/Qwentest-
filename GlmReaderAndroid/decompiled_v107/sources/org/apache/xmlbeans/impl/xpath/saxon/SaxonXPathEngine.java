package org.apache.xmlbeans.impl.xpath.saxon;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sf.saxon.value.DateTimeValue;
import net.sf.saxon.value.GDateValue;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.xpath.XPathEngine;
import org.apache.xmlbeans.impl.xpath.XPathExecutionContext;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
public class SaxonXPathEngine extends XPathExecutionContext implements XPathEngine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Cur _cur;
    private SaxonXPath _engine;
    private final long _version;
    private final DateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ROOT);
    private boolean _firstCall = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SaxonXPathEngine(SaxonXPath xpathImpl, Cur c) {
        this._engine = xpathImpl;
        this._version = c.getLocale().version();
        this._cur = c.weakCur(this);
    }

    @Override // org.apache.xmlbeans.impl.xpath.XPathEngine
    public boolean next(Cur c) {
        Cur pos;
        String value;
        if (!this._firstCall) {
            return false;
        }
        this._firstCall = false;
        if (this._cur != null && this._version != this._cur.getLocale().version()) {
            throw new ConcurrentModificationException("Document changed during select");
        }
        List resultsList = this._engine.selectPath(this._cur.getDom());
        for (int i = 0; i < resultsList.size(); i++) {
            Object node = resultsList.get(i);
            if (!(node instanceof Node)) {
                Object obj = resultsList.get(i);
                if (obj instanceof Date) {
                    value = this.xmlDateFormat.format((Date) obj);
                } else if (obj instanceof GDateValue) {
                    value = ((GDateValue) obj).getStringValue();
                } else if (obj instanceof DateTimeValue) {
                    value = ((DateTimeValue) obj).getStringValue();
                } else if (obj instanceof BigDecimal) {
                    value = ((BigDecimal) obj).toPlainString();
                } else {
                    value = obj.toString();
                }
                org.apache.xmlbeans.impl.store.Locale l = c.getLocale();
                try {
                    pos = l.load("<xml-fragment/>").tempCur();
                    pos.setValue(value);
                    SchemaType type = getType(node);
                    org.apache.xmlbeans.impl.store.Locale.autoTypeDocument(pos, type, null);
                    pos.next();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (!(node instanceof DomImpl.Dom)) {
                    throw new AssertionError("New object created in XPATH!");
                }
                pos = ((DomImpl.Dom) node).tempCur();
            }
            c.addToSelection(pos);
            pos.release();
        }
        release();
        this._engine = null;
        return true;
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
        if (node instanceof GDateValue) {
            SchemaType type8 = XmlDate.type;
            return type8;
        }
        if (node instanceof DateTimeValue) {
            SchemaType type9 = XmlDateTime.type;
            return type9;
        }
        SchemaType type10 = XmlAnySimpleType.type;
        return type10;
    }

    @Override // org.apache.xmlbeans.impl.xpath.XPathEngine
    public void release() {
        if (this._cur != null) {
            this._cur.release();
            this._cur = null;
        }
    }
}
