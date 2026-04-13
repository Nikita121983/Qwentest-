package org.apache.xmlbeans.impl.validator;

import java.util.Collection;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlWhitespace;

/* loaded from: classes11.dex */
public class ValidatorUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* loaded from: classes11.dex */
    private static class EventImpl implements ValidatorListener.Event {
        PrefixResolver _prefixResolver;
        String _text;

        EventImpl(PrefixResolver prefixResolver, String text) {
            this._prefixResolver = prefixResolver;
            this._text = text;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public XmlCursor getLocationAsCursor() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public Location getLocation() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public String getXsiType() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public String getXsiNil() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public String getXsiLoc() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public String getXsiNoLoc() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public QName getName() {
            return null;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public String getText() {
            return this._text;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public String getText(int wsr) {
            return XmlWhitespace.collapse(this._text, wsr);
        }

        @Override // org.apache.xmlbeans.impl.common.ValidatorListener.Event
        public boolean textIsWhitespace() {
            return false;
        }

        @Override // org.apache.xmlbeans.impl.common.PrefixResolver
        public String getNamespaceForPrefix(String prefix) {
            return this._prefixResolver.getNamespaceForPrefix(prefix);
        }
    }

    public static boolean validateSimpleType(SchemaType type, String value, Collection<XmlError> errors, PrefixResolver prefixResolver) {
        if (!type.isSimpleType() && type.getContentType() != 2) {
            throw new AssertionError();
        }
        Validator validator = new Validator(type, null, type.getTypeSystem(), null, errors);
        EventImpl ev = new EventImpl(prefixResolver, value);
        validator.nextEvent(1, ev);
        validator.nextEvent(3, ev);
        validator.nextEvent(2, ev);
        return validator.isValid();
    }
}
