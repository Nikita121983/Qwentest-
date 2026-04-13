package org.apache.xmlbeans.impl.values;

import androidx.core.os.EnvironmentCompat;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.DelegateXmlObject;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.impl.common.GlobalLock;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XmlErrorWatcher;
import org.apache.xmlbeans.impl.common.XmlLocale;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;
import org.apache.xmlbeans.impl.util.LongUTFDataInputStream;
import org.apache.xmlbeans.impl.validator.Validator;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

/* loaded from: classes11.dex */
public abstract class XmlObjectBase implements TypeStoreUser, Serializable, XmlObject, SimpleValue {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FLAGS_DATED = 672;
    private static final int FLAGS_ELEMENT = 7;
    private static final int FLAG_ATTRIBUTE = 8;
    private static final int FLAG_COMPLEXCONTENT = 16384;
    private static final int FLAG_COMPLEXTYPE = 8192;
    private static final int FLAG_ELEMENT_DATED = 512;
    private static final int FLAG_FIXED = 4;
    private static final int FLAG_HASDEFAULT = 2;
    private static final int FLAG_IMMUTABLE = 4096;
    private static final int FLAG_ISDEFAULT = 256;
    private static final int FLAG_NIL = 64;
    private static final int FLAG_NILLABLE = 1;
    private static final int FLAG_NIL_DATED = 128;
    private static final int FLAG_NOT_VARIABLE = 32768;
    private static final int FLAG_ORPHANED = 2048;
    private static final int FLAG_SETTINGDEFAULT = 1024;
    private static final int FLAG_STORE = 16;
    private static final int FLAG_VALIDATE_ON_SET = 65536;
    private static final int FLAG_VALUE_DATED = 32;
    public static final short KIND_SETTERHELPER_ARRAYITEM = 2;
    public static final short KIND_SETTERHELPER_SINGLETON = 1;
    public static final short MAJOR_VERSION_NUMBER = 1;
    public static final short MINOR_VERSION_NUMBER = 1;
    private int _flags = 65;
    private Object _textsource;
    public static final ValidationContext _voorVc = new ValueOutOfRangeValidationContext();
    private static final BigInteger _max = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger _min = BigInteger.valueOf(Long.MIN_VALUE);
    private static final XmlOptions _toStringOptions = buildInnerPrettyOptions();
    private static final XmlObject[] EMPTY_RESULT = new XmlObject[0];

    protected abstract String compute_text(NamespaceManager namespaceManager);

    protected abstract boolean equal_to(XmlObject xmlObject);

    public abstract SchemaType schemaType();

    protected abstract void set_nil();

    protected abstract void set_text(String str);

    protected abstract int value_hash_code();

    @Override // org.apache.xmlbeans.XmlTokenSource
    public final Object monitor() {
        if (has_store()) {
            return get_store().get_locale();
        }
        return this;
    }

    private static XmlObjectBase underlying(XmlObject obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof XmlObjectBase) {
            return (XmlObjectBase) obj;
        }
        while (obj instanceof DelegateXmlObject) {
            obj = ((DelegateXmlObject) obj).underlyingXmlObject();
        }
        if (obj instanceof XmlObjectBase) {
            return (XmlObjectBase) obj;
        }
        throw new IllegalStateException("Non-native implementations of XmlObject should extend FilterXmlObject or implement DelegateXmlObject");
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final XmlObject copy() {
        XmlObject _copy;
        if (preCheck()) {
            return _copy();
        }
        synchronized (monitor()) {
            _copy = _copy();
        }
        return _copy;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final XmlObject copy(XmlOptions options) {
        XmlObject _copy;
        if (preCheck()) {
            return _copy(options);
        }
        synchronized (monitor()) {
            _copy = _copy(options);
        }
        return _copy;
    }

    private boolean preCheck() {
        if (has_store()) {
            return get_store().get_locale().noSync();
        }
        return false;
    }

    public final XmlObject _copy() {
        return _copy(null);
    }

    public final XmlObject _copy(XmlOptions xmlOptions) {
        if (isImmutable()) {
            return this;
        }
        check_orphaned();
        SchemaTypeLoader stl = get_store().get_schematypeloader();
        return (XmlObject) get_store().copy(stl, schemaType(), xmlOptions);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XmlDocumentProperties documentProperties() {
        XmlCursor cur = newCursorForce();
        try {
            XmlDocumentProperties documentProperties = cur.documentProperties();
            if (cur != null) {
                cur.close();
            }
            return documentProperties;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XMLStreamReader newXMLStreamReader() {
        return newXMLStreamReader(null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XMLStreamReader newXMLStreamReader(XmlOptions options) {
        XmlCursor cur = newCursorForce();
        try {
            XMLStreamReader newXMLStreamReader = cur.newXMLStreamReader(makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
            return newXMLStreamReader;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public InputStream newInputStream() {
        return newInputStream(null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public InputStream newInputStream(XmlOptions options) {
        XmlCursor cur = newCursorForce();
        try {
            InputStream newInputStream = cur.newInputStream(makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
            return newInputStream;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Reader newReader() {
        return newReader(null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Reader newReader(XmlOptions options) {
        XmlCursor cur = newCursorForce();
        try {
            Reader newReader = cur.newReader(makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
            return newReader;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Node getDomNode() {
        XmlCursor cur = newCursorForce();
        try {
            Node domNode = cur.getDomNode();
            if (cur != null) {
                cur.close();
            }
            return domNode;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Node newDomNode() {
        return newDomNode(null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public Node newDomNode(XmlOptions options) {
        XmlCursor cur = newCursorForce();
        try {
            Node newDomNode = cur.newDomNode(makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
            return newDomNode;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(ContentHandler ch, LexicalHandler lh, XmlOptions options) throws SAXException {
        XmlCursor cur = newCursorForce();
        try {
            cur.save(ch, lh, makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(File file, XmlOptions options) throws IOException {
        XmlCursor cur = newCursorForce();
        try {
            cur.save(file, makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(OutputStream os, XmlOptions options) throws IOException {
        XmlCursor cur = newCursorForce();
        try {
            cur.save(os, makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(Writer w, XmlOptions options) throws IOException {
        XmlCursor cur = newCursorForce();
        try {
            cur.save(w, makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(ContentHandler ch, LexicalHandler lh) throws SAXException {
        save(ch, lh, null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(File file) throws IOException {
        save(file, (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(OutputStream os) throws IOException {
        save(os, (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void save(Writer w) throws IOException {
        save(w, (XmlOptions) null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public void dump() {
        XmlCursor cur = newCursorForce();
        try {
            cur.dump();
            if (cur != null) {
                cur.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public XmlCursor newCursorForce() {
        XmlCursor newCursor;
        synchronized (monitor()) {
            newCursor = ensureStore().newCursor();
        }
        return newCursor;
    }

    private XmlObject ensureStore() {
        String value;
        if ((this._flags & 16) != 0) {
            return this;
        }
        check_dated();
        if ((this._flags & 64) != 0) {
            value = "";
        } else {
            value = compute_text(has_store() ? get_store() : null);
        }
        XmlOptions options = new XmlOptions().setDocumentType(schemaType());
        XmlObject x = XmlObject.Factory.newInstance(options);
        XmlCursor c = x.newCursor();
        try {
            c.toNextToken();
            c.insertChars(value);
            if (c != null) {
                c.close();
            }
            return x;
        } finally {
        }
    }

    private static XmlOptions makeInnerOptions(XmlOptions options) {
        XmlOptions innerOptions = new XmlOptions(options);
        innerOptions.setSaveInner();
        return innerOptions;
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public XmlCursor newCursor() {
        XmlCursor new_cursor;
        if ((this._flags & 16) == 0) {
            throw new IllegalStateException("XML Value Objects cannot create cursors");
        }
        check_orphaned();
        XmlLocale l = getXmlLocale();
        if (l.noSync()) {
            l.enter();
            try {
                return get_store().new_cursor();
            } finally {
            }
        }
        synchronized (l) {
            l.enter();
            try {
                new_cursor = get_store().new_cursor();
            } finally {
            }
        }
        return new_cursor;
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public SchemaType instanceType() {
        SchemaType schemaType;
        synchronized (monitor()) {
            schemaType = isNil() ? null : schemaType();
        }
        return schemaType;
    }

    private SchemaField schemaField() {
        SchemaType st = schemaType();
        SchemaField field = st.getContainerField();
        if (field == null) {
            return get_store().get_schema_field();
        }
        return field;
    }

    /* loaded from: classes11.dex */
    private static final class ValueOutOfRangeValidationContext implements ValidationContext {
        private ValueOutOfRangeValidationContext() {
        }

        /* synthetic */ ValueOutOfRangeValidationContext(AnonymousClass1 x0) {
            this();
        }

        @Override // org.apache.xmlbeans.impl.common.ValidationContext
        public void invalid(String message) {
            throw new XmlValueOutOfRangeException(message);
        }

        @Override // org.apache.xmlbeans.impl.common.ValidationContext
        public void invalid(String code, Object[] args) {
            throw new XmlValueOutOfRangeException(code, args);
        }
    }

    /* loaded from: classes11.dex */
    public static final class ImmutableValueValidationContext implements ValidationContext {
        private final Collection<XmlError> _coll;
        private final XmlObject _loc;

        ImmutableValueValidationContext(Collection<XmlError> coll, XmlObject loc) {
            this._coll = coll;
            this._loc = loc;
        }

        @Override // org.apache.xmlbeans.impl.common.ValidationContext
        public void invalid(String message) {
            this._coll.add(XmlError.forObject(message, this._loc));
        }

        @Override // org.apache.xmlbeans.impl.common.ValidationContext
        public void invalid(String code, Object[] args) {
            this._coll.add(XmlError.forObject(code, args, this._loc));
        }
    }

    @Override // org.apache.xmlbeans.XmlObject
    public boolean validate() {
        return validate(null);
    }

    @Override // org.apache.xmlbeans.XmlObject
    public boolean validate(XmlOptions options) {
        Throwable th;
        if ((this._flags & 16) == 0) {
            if ((this._flags & 4096) != 0) {
                return validate_immutable(options);
            }
            throw new IllegalStateException("XML objects with no underlying store cannot be validated");
        }
        synchronized (monitor()) {
            try {
                try {
                    if ((this._flags & 2048) != 0) {
                        throw new XmlValueDisconnectedException();
                    }
                    SchemaField field = schemaField();
                    SchemaType type = schemaType();
                    TypeStore typeStore = get_store();
                    Validator validator = new Validator(type, field, typeStore.get_schematypeloader(), options, null);
                    typeStore.validate(validator);
                    return validator.isValid();
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    private boolean validate_immutable(XmlOptions options) {
        Collection<XmlError> errorListener = options == null ? null : options.getErrorListener();
        XmlErrorWatcher watcher = new XmlErrorWatcher(errorListener);
        if (!schemaType().isSimpleType() && (options == null || !options.isValidateTextOnly())) {
            SchemaProperty[] properties = schemaType().getProperties();
            for (SchemaProperty property : properties) {
                if (property.getMinOccurs().signum() > 0) {
                    if (property.isAttribute()) {
                        watcher.add(XmlError.forObject(XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$MISSING_REQUIRED_ATTRIBUTE, new Object[]{QNameHelper.pretty(property.getName())}, this));
                    } else {
                        watcher.add(XmlError.forObject(XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$MISSING_ELEMENT, new Object[]{property.getMinOccurs(), QNameHelper.pretty(property.getName())}, this));
                    }
                }
            }
            if (schemaType().getContentType() != 2) {
                return !watcher.hasError();
            }
        }
        String text = (String) this._textsource;
        if (text == null) {
            text = "";
        }
        validate_simpleval(text, new ImmutableValueValidationContext(watcher, this));
        return !watcher.hasError();
    }

    public void validate_simpleval(String lexical, ValidationContext ctx) {
    }

    private static XmlObject[] _typedArray(XmlObject[] input) {
        if (input.length == 0) {
            return input;
        }
        SchemaType commonType = input[0].schemaType();
        if (commonType.equals(XmlObject.type) || commonType.isNoType()) {
            return input;
        }
        for (int i = 1; i < input.length; i++) {
            if (input[i].schemaType().isNoType()) {
                return input;
            }
            commonType = commonType.getCommonBaseType(input[i].schemaType());
            if (commonType.equals(XmlObject.type)) {
                return input;
            }
        }
        Class<? extends XmlObject> desiredClass = commonType.getJavaClass();
        while (desiredClass == null) {
            commonType = commonType.getBaseType();
            if (XmlObject.type.equals(commonType)) {
                return input;
            }
            desiredClass = commonType.getJavaClass();
        }
        XmlObject[] result = (XmlObject[]) Array.newInstance(desiredClass, input.length);
        System.arraycopy(input, 0, result, 0, input.length);
        return result;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject[] selectPath(String path) {
        return selectPath(path, null);
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject[] selectPath(String path, XmlOptions options) {
        XmlObject[] selections;
        XmlCursor c = newCursor();
        try {
            if (c == null) {
                throw new XmlValueDisconnectedException();
            }
            c.selectPath(path, options);
            if (!c.hasNextSelection()) {
                selections = EMPTY_RESULT;
            } else {
                selections = new XmlObject[c.getSelectionCount()];
                int i = 0;
                while (c.toNextSelection()) {
                    XmlObject object = c.getObject();
                    selections[i] = object;
                    if (object == null) {
                        if (c.toParent()) {
                            XmlObject object2 = c.getObject();
                            selections[i] = object2;
                            if (object2 != null) {
                            }
                        }
                        throw new XmlRuntimeException("Path must select only elements and attributes");
                    }
                    i++;
                }
            }
            if (c != null) {
                c.close();
            }
            return _typedArray(selections);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (c != null) {
                    try {
                        c.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject[] execQuery(String path) {
        return execQuery(path, null);
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject[] execQuery(String queryExpr, XmlOptions options) {
        XmlObject[] _typedArray;
        synchronized (monitor()) {
            TypeStore typeStore = get_store();
            if (typeStore == null) {
                throw new XmlRuntimeException("Cannot do XQuery on XML Value Objects");
            }
            _typedArray = _typedArray(typeStore.exec_query(queryExpr, options));
        }
        return _typedArray;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject changeType(SchemaType type) {
        XmlObject xmlObject;
        if (type == null) {
            throw new IllegalArgumentException("Invalid type (null)");
        }
        if ((this._flags & 16) == 0) {
            throw new IllegalStateException("XML Value Objects cannot have thier type changed");
        }
        synchronized (monitor()) {
            check_orphaned();
            xmlObject = (XmlObject) get_store().change_type(type);
        }
        return xmlObject;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject substitute(QName name, SchemaType type) {
        XmlObject xmlObject;
        if (name == null) {
            throw new IllegalArgumentException("Invalid name (null)");
        }
        if (type == null) {
            throw new IllegalArgumentException("Invalid type (null)");
        }
        if ((this._flags & 16) == 0) {
            throw new IllegalStateException("XML Value Objects cannot be used with substitution");
        }
        synchronized (monitor()) {
            check_orphaned();
            xmlObject = (XmlObject) get_store().substitute(name, type);
        }
        return xmlObject;
    }

    public void init_flags(SchemaProperty prop) {
        if (prop == null) {
            return;
        }
        if (prop.hasDefault() == 1 || prop.hasFixed() == 1 || prop.hasNillable() == 1) {
            return;
        }
        this._flags &= -8;
        this._flags |= (prop.hasNillable() == 0 ? 0 : 1) | (prop.hasDefault() == 0 ? 0 : 2) | (prop.hasFixed() == 0 ? 0 : 4) | 32768;
    }

    public void initComplexType(boolean complexType, boolean complexContent) {
        this._flags |= (complexContent ? 16384 : 0) | (complexType ? 8192 : 0);
    }

    protected boolean _isComplexType() {
        return (this._flags & 8192) != 0;
    }

    protected boolean _isComplexContent() {
        return (this._flags & 16384) != 0;
    }

    public void setValidateOnSet() {
        this._flags |= 65536;
    }

    public boolean _validateOnSet() {
        return (this._flags & 65536) != 0;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final boolean isNil() {
        boolean z;
        synchronized (monitor()) {
            check_dated();
            z = (this._flags & 64) != 0;
        }
        return z;
    }

    public final boolean isFixed() {
        check_element_dated();
        return (this._flags & 4) != 0;
    }

    public final boolean isNillable() {
        check_element_dated();
        return (this._flags & 1) != 0;
    }

    public final boolean isDefaultable() {
        check_element_dated();
        return (this._flags & 2) != 0;
    }

    public final boolean isDefault() {
        check_dated();
        return (this._flags & 256) != 0;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final void setNil() {
        synchronized (monitor()) {
            set_prepare();
            if ((this._flags & 1) == 0 && (this._flags & 65536) != 0) {
                throw new XmlValueNotNillableException();
            }
            set_nil();
            this._flags |= 64;
            if ((this._flags & 16) != 0) {
                get_store().invalidate_text();
                this._flags &= -673;
                get_store().invalidate_nil();
            } else {
                this._textsource = null;
            }
        }
    }

    protected int elementFlags() {
        check_element_dated();
        return this._flags & 7;
    }

    public void setImmutable() {
        if ((this._flags & 4112) != 0) {
            throw new IllegalStateException();
        }
        this._flags |= 4096;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public boolean isImmutable() {
        return (this._flags & 4096) != 0;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final void attach_store(TypeStore store) {
        this._textsource = store;
        if ((this._flags & 4096) != 0) {
            throw new IllegalStateException();
        }
        this._flags |= 688;
        if (store.is_attribute()) {
            this._flags |= 8;
        }
        if (store.validate_on_set()) {
            this._flags |= 65536;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final void invalidate_value() {
        if ((this._flags & 16) == 0) {
            throw new AssertionError();
        }
        this._flags |= 32;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final boolean uses_invalidate_value() {
        SchemaType type = schemaType();
        return type.isSimpleType() || type.getContentType() == 2;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final void invalidate_nilvalue() {
        if ((this._flags & 16) == 0) {
            throw new AssertionError();
        }
        this._flags |= 160;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final void invalidate_element_order() {
        if ((this._flags & 16) == 0) {
            throw new AssertionError();
        }
        this._flags |= FLAGS_DATED;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final TypeStore get_store() {
        if ((this._flags & 16) == 0) {
            throw new AssertionError();
        }
        return (TypeStore) this._textsource;
    }

    public final XmlLocale getXmlLocale() {
        return get_store().get_locale();
    }

    public final boolean has_store() {
        return (this._flags & 16) != 0;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final String build_text(NamespaceManager nsm) {
        NamespaceManager namespaceManager;
        if ((this._flags & 16) == 0) {
            throw new AssertionError();
        }
        if ((this._flags & 32) != 0) {
            throw new AssertionError();
        }
        if ((this._flags & 320) != 0) {
            return "";
        }
        if (nsm == null) {
            namespaceManager = has_store() ? get_store() : null;
        } else {
            namespaceManager = nsm;
        }
        return compute_text(namespaceManager);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public boolean build_nil() {
        if ((this._flags & 16) == 0) {
            throw new AssertionError();
        }
        if ((this._flags & 32) == 0) {
            return (this._flags & 64) != 0;
        }
        throw new AssertionError();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public void validate_now() {
        check_dated();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public void disconnect_store() {
        if ((this._flags & 16) == 0) {
            throw new AssertionError();
        }
        this._flags |= 2720;
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public TypeStoreUser create_element_user(QName eltName, QName xsiType) {
        return (TypeStoreUser) ((SchemaTypeImpl) schemaType()).createElementType(eltName, xsiType, get_store().get_schematypeloader());
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public TypeStoreUser create_attribute_user(QName attrName) {
        return (TypeStoreUser) ((SchemaTypeImpl) schemaType()).createAttributeType(attrName, get_store().get_schematypeloader());
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public SchemaType get_schema_type() {
        return schemaType();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public SchemaType get_element_type(QName eltName, QName xsiType) {
        return schemaType().getElementType(eltName, xsiType, get_store().get_schematypeloader());
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public SchemaType get_attribute_type(QName attrName) {
        return schemaType().getAttributeType(attrName, get_store().get_schematypeloader());
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public String get_default_element_text(QName eltName) {
        if (!_isComplexContent()) {
            throw new AssertionError();
        }
        if (!_isComplexContent()) {
            throw new IllegalStateException();
        }
        SchemaProperty prop = schemaType().getElementProperty(eltName);
        if (prop == null) {
            return "";
        }
        return prop.getDefaultText();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public String get_default_attribute_text(QName attrName) {
        if (!_isComplexType()) {
            throw new AssertionError();
        }
        if (!_isComplexType()) {
            throw new IllegalStateException();
        }
        SchemaProperty prop = schemaType().getAttributeProperty(attrName);
        if (prop == null) {
            return "";
        }
        return prop.getDefaultText();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public int get_elementflags(QName eltName) {
        SchemaProperty prop;
        if (!_isComplexContent() || (prop = schemaType().getElementProperty(eltName)) == null) {
            return 0;
        }
        if (prop.hasDefault() == 1 || prop.hasFixed() == 1 || prop.hasNillable() == 1) {
            return -1;
        }
        return (prop.hasNillable() != 0 ? 1 : 0) | (prop.hasDefault() == 0 ? 0 : 2) | (prop.hasFixed() == 0 ? 0 : 4);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public int get_attributeflags(QName attrName) {
        SchemaProperty prop;
        if (!_isComplexType() || (prop = schemaType().getAttributeProperty(attrName)) == null) {
            return 0;
        }
        return (prop.hasFixed() != 0 ? 4 : 0) | (prop.hasDefault() == 0 ? 0 : 2);
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public boolean is_child_element_order_sensitive() {
        if (!_isComplexType()) {
            return false;
        }
        return schemaType().isOrderSensitive();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public final QNameSet get_element_ending_delimiters(QName eltname) {
        SchemaProperty prop = schemaType().getElementProperty(eltname);
        if (prop == null) {
            return null;
        }
        return prop.getJavaSetterDelimiter();
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public TypeStoreVisitor new_visitor() {
        if (!_isComplexContent()) {
            return null;
        }
        return new SchemaTypeVisitorImpl(schemaType().getContentModel());
    }

    @Override // org.apache.xmlbeans.impl.values.TypeStoreUser
    public SchemaField get_attribute_field(QName attrName) {
        SchemaAttributeModel model = schemaType().getAttributeModel();
        if (model == null) {
            return null;
        }
        return model.getAttribute(attrName);
    }

    public void set_String(String v) {
        if ((this._flags & 4096) != 0) {
            throw new IllegalStateException();
        }
        boolean wasNilled = (this._flags & 64) != 0;
        String wscanon = apply_wscanon(v);
        update_from_wscanon_text(wscanon);
        if ((this._flags & 16) != 0) {
            this._flags &= -33;
            if ((this._flags & 1024) == 0) {
                get_store().store_text(v);
            }
            if (wasNilled) {
                get_store().invalidate_nil();
                return;
            }
            return;
        }
        this._textsource = v;
    }

    protected void update_from_complex_content() {
        throw new XmlValueNotSupportedException("Complex content");
    }

    private void update_from_wscanon_text(String v) {
        if ((this._flags & 2) != 0 && (this._flags & 1024) == 0 && (this._flags & 8) == 0 && v.equals("")) {
            String def = get_store().compute_default_text();
            if (def == null) {
                throw new XmlValueOutOfRangeException();
            }
            this._flags |= 1024;
            try {
                setStringValue(def);
                this._flags &= -1025;
                this._flags &= -65;
                this._flags |= 256;
                return;
            } catch (Throwable th) {
                this._flags &= -1025;
                throw th;
            }
        }
        set_text(v);
        this._flags &= -321;
    }

    protected boolean is_defaultable_ws(String v) {
        return true;
    }

    protected int get_wscanon_rule() {
        return 3;
    }

    private String apply_wscanon(String v) {
        return XmlWhitespace.collapse(v, get_wscanon_rule());
    }

    private void check_element_dated() {
        if ((this._flags & 512) != 0 && (this._flags & 32768) == 0) {
            if ((this._flags & 2048) != 0) {
                throw new XmlValueDisconnectedException();
            }
            int eltflags = get_store().compute_flags();
            this._flags &= -520;
            this._flags |= eltflags;
        }
        if ((this._flags & 32768) != 0) {
            this._flags &= -513;
        }
    }

    protected final boolean is_orphaned() {
        return (this._flags & 2048) != 0;
    }

    public final void check_orphaned() {
        if (is_orphaned()) {
            throw new XmlValueDisconnectedException();
        }
    }

    public final void check_dated() {
        String text;
        if ((this._flags & FLAGS_DATED) != 0) {
            if ((this._flags & 2048) != 0) {
                throw new XmlValueDisconnectedException();
            }
            if ((this._flags & 16) == 0) {
                throw new AssertionError();
            }
            check_element_dated();
            if ((this._flags & 512) != 0) {
                int eltflags = get_store().compute_flags();
                this._flags &= -520;
                this._flags |= eltflags;
            }
            int eltflags2 = 0;
            if ((this._flags & 128) != 0) {
                if (get_store().find_nil()) {
                    if ((this._flags & 1) == 0 && (this._flags & 65536) != 0) {
                        throw new XmlValueOutOfRangeException();
                    }
                    set_nil();
                    this._flags |= 64;
                    eltflags2 = 1;
                } else {
                    this._flags &= -65;
                }
                this._flags &= -129;
            }
            if (eltflags2 == 0) {
                if ((this._flags & 16384) != 0 || (text = get_wscanon_text()) == null) {
                    update_from_complex_content();
                } else {
                    NamespaceContext.push(new NamespaceContext(get_store()));
                    try {
                        update_from_wscanon_text(text);
                    } finally {
                        NamespaceContext.pop();
                    }
                }
            }
            this._flags &= -33;
        }
    }

    private void set_prepare() {
        check_element_dated();
        if ((this._flags & 4096) != 0) {
            throw new IllegalStateException();
        }
    }

    private void set_commit() {
        boolean wasNilled = (this._flags & 64) != 0;
        this._flags &= -321;
        if ((this._flags & 16) != 0) {
            this._flags &= -673;
            get_store().invalidate_text();
            if (wasNilled) {
                get_store().invalidate_nil();
            }
            this._flags &= -673;
            return;
        }
        this._textsource = null;
    }

    public final String get_wscanon_text() {
        if ((this._flags & 16) == 0) {
            return apply_wscanon((String) this._textsource);
        }
        return get_store().fetch_text(get_wscanon_rule());
    }

    public float getFloatValue() {
        BigDecimal bd = getBigDecimalValue();
        if (bd == null) {
            return 0.0f;
        }
        return bd.floatValue();
    }

    public double getDoubleValue() {
        BigDecimal bd = getBigDecimalValue();
        if (bd == null) {
            return 0.0d;
        }
        return bd.doubleValue();
    }

    public BigDecimal getBigDecimalValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "numeric"});
    }

    public BigInteger getBigIntegerValue() {
        BigDecimal bd = getBigDecimalValue();
        if (bd == null) {
            return null;
        }
        return bd.toBigInteger();
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public byte getByteValue() {
        long l = getIntValue();
        if (l > 127) {
            throw new XmlValueOutOfRangeException();
        }
        if (l < -128) {
            throw new XmlValueOutOfRangeException();
        }
        return (byte) l;
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public short getShortValue() {
        long l = getIntValue();
        if (l > 32767) {
            throw new XmlValueOutOfRangeException();
        }
        if (l < -32768) {
            throw new XmlValueOutOfRangeException();
        }
        return (short) l;
    }

    public int getIntValue() {
        long l = getLongValue();
        if (l > 2147483647L) {
            throw new XmlValueOutOfRangeException();
        }
        if (l < -2147483648L) {
            throw new XmlValueOutOfRangeException();
        }
        return (int) l;
    }

    public long getLongValue() {
        BigInteger b = getBigIntegerValue();
        if (b == null) {
            return 0L;
        }
        if (b.compareTo(_max) >= 0) {
            throw new XmlValueOutOfRangeException();
        }
        if (b.compareTo(_min) <= 0) {
            throw new XmlValueOutOfRangeException();
        }
        return b.longValue();
    }

    static XmlOptions buildInnerPrettyOptions() {
        XmlOptions options = new XmlOptions();
        options.setSaveInner();
        options.setSavePrettyPrint();
        options.setSaveAggressiveNamespaces();
        options.setUseDefaultNamespace();
        return options;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final String toString() {
        String xmlText;
        synchronized (monitor()) {
            xmlText = ensureStore().xmlText(_toStringOptions);
        }
        return xmlText;
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public String xmlText() {
        return xmlText(null);
    }

    @Override // org.apache.xmlbeans.XmlTokenSource
    public String xmlText(XmlOptions options) {
        XmlCursor cur = newCursorForce();
        try {
            String xmlText = cur.xmlText(makeInnerOptions(options));
            if (cur != null) {
                cur.close();
            }
            return xmlText;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public StringEnumAbstractBase getEnumValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "enum"});
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public String getStringValue() {
        if (isImmutable()) {
            if ((this._flags & 64) != 0) {
                return null;
            }
            return compute_text(null);
        }
        synchronized (monitor()) {
            if (_isComplexContent()) {
                return get_store().fetch_text(1);
            }
            check_dated();
            if ((this._flags & 64) != 0) {
                return null;
            }
            return compute_text(has_store() ? get_store() : null);
        }
    }

    public byte[] getByteArrayValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "byte[]"});
    }

    public boolean getBooleanValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "boolean"});
    }

    public GDate getGDateValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "Date"});
    }

    public Date getDateValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "Date"});
    }

    public Calendar getCalendarValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "Calendar"});
    }

    public GDuration getGDurationValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "Duration"});
    }

    public QName getQNameValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), XmlErrorCodes.QNAME});
    }

    public List<?> getListValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "List"});
    }

    public List<? extends XmlAnySimpleType> xgetListValue() {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_S2J, new Object[]{getPrimitiveTypeName(), "List"});
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public Object getObjectValue() {
        return java_value(this);
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setBooleanValue(boolean v) {
        synchronized (monitor()) {
            set_prepare();
            set_boolean(v);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setByteValue(byte v) {
        synchronized (monitor()) {
            set_prepare();
            set_byte(v);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setShortValue(short v) {
        synchronized (monitor()) {
            set_prepare();
            set_short(v);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setIntValue(int v) {
        synchronized (monitor()) {
            set_prepare();
            set_int(v);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setLongValue(long v) {
        synchronized (monitor()) {
            set_prepare();
            set_long(v);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setFloatValue(float v) {
        synchronized (monitor()) {
            set_prepare();
            set_float(v);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setDoubleValue(double v) {
        synchronized (monitor()) {
            set_prepare();
            set_double(v);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setByteArrayValue(byte[] obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_ByteArray(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setEnumValue(StringEnumAbstractBase obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_enum(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setBigIntegerValue(BigInteger obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_BigInteger(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setBigDecimalValue(BigDecimal obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_BigDecimal(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setCalendarValue(Calendar obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_Calendar(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setDateValue(Date obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_Date(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setGDateValue(GDate obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_GDate(obj);
            set_commit();
        }
    }

    public final void setGDateValue(GDateSpecification obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_GDate(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setGDurationValue(GDuration obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_GDuration(obj);
            set_commit();
        }
    }

    public final void setGDurationValue(GDurationSpecification obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_GDuration(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setQNameValue(QName obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_QName(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setListValue(List<?> obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_list(obj);
            set_commit();
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public final void setStringValue(String obj) {
        if (obj == null) {
            setNil();
            return;
        }
        synchronized (monitor()) {
            set_prepare();
            set_String(obj);
        }
    }

    @Override // org.apache.xmlbeans.SimpleValue
    public void setObjectValue(Object o) {
        if (o == null) {
            setNil();
            return;
        }
        if (o instanceof XmlObject) {
            set((XmlObject) o);
            return;
        }
        if (o instanceof String) {
            setStringValue((String) o);
            return;
        }
        if (o instanceof StringEnumAbstractBase) {
            setEnumValue((StringEnumAbstractBase) o);
            return;
        }
        if (o instanceof BigInteger) {
            setBigIntegerValue((BigInteger) o);
            return;
        }
        if (o instanceof BigDecimal) {
            setBigDecimalValue((BigDecimal) o);
            return;
        }
        if (o instanceof Byte) {
            setByteValue(((Byte) o).byteValue());
            return;
        }
        if (o instanceof Short) {
            setShortValue(((Short) o).shortValue());
            return;
        }
        if (o instanceof Integer) {
            setIntValue(((Integer) o).intValue());
            return;
        }
        if (o instanceof Long) {
            setLongValue(((Long) o).longValue());
            return;
        }
        if (o instanceof Boolean) {
            setBooleanValue(((Boolean) o).booleanValue());
            return;
        }
        if (o instanceof Float) {
            setFloatValue(((Float) o).floatValue());
            return;
        }
        if (o instanceof Double) {
            setDoubleValue(((Double) o).doubleValue());
            return;
        }
        if (o instanceof Calendar) {
            setCalendarValue((Calendar) o);
            return;
        }
        if (o instanceof Date) {
            setDateValue((Date) o);
            return;
        }
        if (o instanceof GDateSpecification) {
            setGDateValue((GDateSpecification) o);
            return;
        }
        if (o instanceof GDurationSpecification) {
            setGDurationValue((GDurationSpecification) o);
            return;
        }
        if (o instanceof QName) {
            setQNameValue((QName) o);
        } else if (o instanceof List) {
            setListValue((List) o);
        } else {
            if (o instanceof byte[]) {
                setByteArrayValue((byte[]) o);
                return;
            }
            throw new XmlValueNotSupportedException("Can't set union object of class : " + o.getClass().getName());
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x004f. Please report as an issue. */
    public final void set_newValue(XmlObject obj) {
        if (obj == null || obj.isNil()) {
            setNil();
            return;
        }
        if (obj instanceof XmlAnySimpleType) {
            XmlAnySimpleType v = (XmlAnySimpleType) obj;
            SchemaType instanceType = ((SimpleValue) v).instanceType();
            if (instanceType == null) {
                throw new AssertionError("Nil case should have been handled already");
            }
            if (instanceType.getSimpleVariety() == 3) {
                synchronized (monitor()) {
                    set_prepare();
                    set_list(((SimpleValue) v).xgetListValue());
                    set_commit();
                }
                return;
            }
            synchronized (monitor()) {
                if (instanceType.getSimpleVariety() != 1) {
                    throw new AssertionError();
                }
                switch (instanceType.getPrimitiveType().getBuiltinTypeCode()) {
                    case 2:
                        boolean pushed = false;
                        if (!v.isImmutable()) {
                            pushed = true;
                            NamespaceContext.push(new NamespaceContext(v));
                        }
                        try {
                            set_prepare();
                            set_xmlanysimple(v);
                            set_commit();
                            break;
                        } finally {
                            if (pushed) {
                                NamespaceContext.pop();
                            }
                        }
                    case 3:
                        boolean bool = ((SimpleValue) v).getBooleanValue();
                        set_prepare();
                        set_boolean(bool);
                        set_commit();
                        break;
                    case 4:
                        byte[] byteArr = ((SimpleValue) v).getByteArrayValue();
                        set_prepare();
                        set_b64(byteArr);
                        set_commit();
                        break;
                    case 5:
                        byte[] byteArr2 = ((SimpleValue) v).getByteArrayValue();
                        set_prepare();
                        set_hex(byteArr2);
                        set_commit();
                        break;
                    case 6:
                        String uri = v.getStringValue();
                        set_prepare();
                        set_text(uri);
                        set_commit();
                        break;
                    case 7:
                        QName name = ((SimpleValue) v).getQNameValue();
                        set_prepare();
                        set_QName(name);
                        set_commit();
                        break;
                    case 8:
                        String s = v.getStringValue();
                        set_prepare();
                        set_notation(s);
                        set_commit();
                        break;
                    case 9:
                        float f = ((SimpleValue) v).getFloatValue();
                        set_prepare();
                        set_float(f);
                        set_commit();
                        break;
                    case 10:
                        double d = ((SimpleValue) v).getDoubleValue();
                        set_prepare();
                        set_double(d);
                        set_commit();
                        break;
                    case 11:
                        switch (instanceType.getDecimalSize()) {
                            case 8:
                                byte b = ((SimpleValue) v).getByteValue();
                                set_prepare();
                                set_byte(b);
                                break;
                            case 16:
                                short s2 = ((SimpleValue) v).getShortValue();
                                set_prepare();
                                set_short(s2);
                                break;
                            case 32:
                                int i = ((SimpleValue) v).getIntValue();
                                set_prepare();
                                set_int(i);
                                break;
                            case 64:
                                long l = ((SimpleValue) v).getLongValue();
                                set_prepare();
                                set_long(l);
                                break;
                            case 1000000:
                                BigInteger bi = ((SimpleValue) v).getBigIntegerValue();
                                set_prepare();
                                set_BigInteger(bi);
                                break;
                            case SchemaType.SIZE_BIG_DECIMAL /* 1000001 */:
                                BigDecimal bd = ((SimpleValue) v).getBigDecimalValue();
                                set_prepare();
                                set_BigDecimal(bd);
                                break;
                            default:
                                throw new AssertionError("invalid numeric bit count");
                        }
                        set_commit();
                        break;
                    case 12:
                        String s3 = v.getStringValue();
                        set_prepare();
                        set_String(s3);
                        set_commit();
                        break;
                    case 13:
                        GDuration gd = ((SimpleValue) v).getGDurationValue();
                        set_prepare();
                        set_GDuration(gd);
                        set_commit();
                        break;
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                        GDate gd2 = ((SimpleValue) v).getGDateValue();
                        set_prepare();
                        set_GDate(gd2);
                        set_commit();
                        break;
                    default:
                        throw new AssertionError("encountered nonprimitive type.");
                }
            }
            return;
        }
        throw new IllegalStateException("Complex type unexpected");
    }

    private TypeStoreUser setterHelper(XmlObjectBase src) {
        check_orphaned();
        src.check_orphaned();
        return get_store().copy_contents_from(src.get_store()).get_store().change_type(src.schemaType());
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final XmlObject set(XmlObject src) {
        if (isImmutable()) {
            throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
        }
        XmlObjectBase obj = underlying(src);
        TypeStoreUser newObj = this;
        if (obj == null) {
            setNil();
            return this;
        }
        if (obj.isImmutable()) {
            setStringValue(obj.getStringValue());
        } else {
            boolean noSyncThis = preCheck();
            boolean noSyncObj = obj.preCheck();
            if (monitor() == obj.monitor()) {
                if (noSyncThis) {
                    newObj = setterHelper(obj);
                } else {
                    synchronized (monitor()) {
                        newObj = setterHelper(obj);
                    }
                }
            } else if (noSyncThis) {
                if (noSyncObj) {
                    newObj = setterHelper(obj);
                } else {
                    synchronized (obj.monitor()) {
                        newObj = setterHelper(obj);
                    }
                }
            } else if (noSyncObj) {
                synchronized (monitor()) {
                    newObj = setterHelper(obj);
                }
            } else {
                boolean acquired = false;
                try {
                    try {
                        GlobalLock.acquire();
                        acquired = true;
                        synchronized (monitor()) {
                            synchronized (obj.monitor()) {
                                GlobalLock.release();
                                acquired = false;
                                newObj = setterHelper(obj);
                            }
                        }
                    } catch (InterruptedException e) {
                        throw new XmlRuntimeException(e);
                    }
                } finally {
                    if (acquired) {
                        GlobalLock.release();
                    }
                }
            }
        }
        return (XmlObject) newObj;
    }

    public final XmlObject generatedSetterHelperImpl(XmlObject src, QName propName, int index, short kindSetterHelper) {
        XmlObject xmlObject;
        XmlObject xmlObject2;
        XmlObject xmlObject3;
        XmlObject xmlObject4;
        XmlObjectBase target;
        XmlObjectBase target2;
        XmlObjectBase srcObj = underlying(src);
        if (srcObj == null) {
            synchronized (monitor()) {
                target2 = getTargetForSetter(propName, index, kindSetterHelper);
                target2.setNil();
            }
            return target2;
        }
        if (srcObj.isImmutable()) {
            synchronized (monitor()) {
                target = getTargetForSetter(propName, index, kindSetterHelper);
                target.setStringValue(srcObj.getStringValue());
            }
            return target;
        }
        boolean noSyncThis = preCheck();
        boolean noSyncObj = srcObj.preCheck();
        if (monitor() == srcObj.monitor()) {
            if (noSyncThis) {
                return (XmlObject) objSetterHelper(srcObj, propName, index, kindSetterHelper);
            }
            synchronized (monitor()) {
                xmlObject4 = (XmlObject) objSetterHelper(srcObj, propName, index, kindSetterHelper);
            }
            return xmlObject4;
        }
        if (noSyncThis) {
            if (noSyncObj) {
                return (XmlObject) objSetterHelper(srcObj, propName, index, kindSetterHelper);
            }
            synchronized (srcObj.monitor()) {
                xmlObject3 = (XmlObject) objSetterHelper(srcObj, propName, index, kindSetterHelper);
            }
            return xmlObject3;
        }
        if (noSyncObj) {
            synchronized (monitor()) {
                xmlObject2 = (XmlObject) objSetterHelper(srcObj, propName, index, kindSetterHelper);
            }
            return xmlObject2;
        }
        boolean acquired = false;
        try {
            try {
                GlobalLock.acquire();
                acquired = true;
                synchronized (monitor()) {
                    synchronized (srcObj.monitor()) {
                        GlobalLock.release();
                        acquired = false;
                        xmlObject = (XmlObject) objSetterHelper(srcObj, propName, index, kindSetterHelper);
                    }
                }
                return xmlObject;
            } catch (InterruptedException e) {
                throw new XmlRuntimeException(e);
            }
        } finally {
            if (acquired) {
                GlobalLock.release();
            }
        }
    }

    private TypeStoreUser objSetterHelper(XmlObjectBase srcObj, QName propName, int index, short kindSetterHelper) {
        XmlObjectBase target = getTargetForSetter(propName, index, kindSetterHelper);
        target.check_orphaned();
        srcObj.check_orphaned();
        return target.get_store().copy_contents_from(srcObj.get_store()).get_store().change_type(srcObj.schemaType());
    }

    private XmlObjectBase getTargetForSetter(QName propName, int index, short kindSetterHelper) {
        switch (kindSetterHelper) {
            case 1:
                check_orphaned();
                XmlObjectBase target = (XmlObjectBase) get_store().find_element_user(propName, index);
                if (target == null) {
                    target = (XmlObjectBase) get_store().add_element_user(propName);
                }
                if (target.isImmutable()) {
                    throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
                }
                return target;
            case 2:
                check_orphaned();
                XmlObjectBase target2 = (XmlObjectBase) get_store().find_element_user(propName, index);
                if (target2 == null) {
                    throw new IndexOutOfBoundsException();
                }
                if (target2.isImmutable()) {
                    throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
                }
                return target2;
            default:
                throw new IllegalArgumentException("Unknown kindSetterHelper: " + ((int) kindSetterHelper));
        }
    }

    public final XmlObject _set(XmlObject src) {
        if (isImmutable()) {
            throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
        }
        XmlObjectBase obj = underlying(src);
        TypeStoreUser newObj = this;
        if (obj == null) {
            setNil();
            return this;
        }
        if (obj.isImmutable()) {
            setStringValue(obj.getStringValue());
        } else {
            check_orphaned();
            obj.check_orphaned();
            newObj = get_store().copy_contents_from(obj.get_store()).get_store().change_type(obj.schemaType());
        }
        return (XmlObject) newObj;
    }

    protected void set_list(List<?> list) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"List", getPrimitiveTypeName()});
    }

    protected void set_boolean(boolean v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"boolean", getPrimitiveTypeName()});
    }

    protected void set_byte(byte v) {
        set_int(v);
    }

    protected void set_short(short v) {
        set_int(v);
    }

    protected void set_int(int v) {
        set_long(v);
    }

    protected void set_long(long v) {
        set_BigInteger(BigInteger.valueOf(v));
    }

    protected void set_char(char v) {
        set_String(Character.toString(v));
    }

    protected void set_float(float v) {
        set_BigDecimal(new BigDecimal(v));
    }

    protected void set_double(double v) {
        set_BigDecimal(new BigDecimal(v));
    }

    protected void set_enum(StringEnumAbstractBase e) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"enum", getPrimitiveTypeName()});
    }

    protected void set_ByteArray(byte[] b) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"byte[]", getPrimitiveTypeName()});
    }

    protected void set_b64(byte[] b) {
        set_ByteArray(b);
    }

    protected void set_hex(byte[] b) {
        set_ByteArray(b);
    }

    protected void set_BigInteger(BigInteger v) {
        set_BigDecimal(new BigDecimal(v));
    }

    protected void set_BigDecimal(BigDecimal v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"numeric", getPrimitiveTypeName()});
    }

    protected void set_Date(Date v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"Date", getPrimitiveTypeName()});
    }

    protected void set_Calendar(Calendar v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"Calendar", getPrimitiveTypeName()});
    }

    protected void set_GDate(GDateSpecification v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"Date", getPrimitiveTypeName()});
    }

    protected void set_GDuration(GDurationSpecification v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"Duration", getPrimitiveTypeName()});
    }

    protected void set_ComplexXml(XmlObject v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{"complex content", getPrimitiveTypeName()});
    }

    protected void set_QName(QName v) {
        throw new XmlValueNotSupportedException(XmlErrorCodes.EXCEPTION_VALUE_NOT_SUPPORTED_J2S, new Object[]{XmlErrorCodes.QNAME, getPrimitiveTypeName()});
    }

    protected void set_notation(String v) {
        throw new XmlValueNotSupportedException();
    }

    protected void set_xmlanysimple(XmlAnySimpleType v) {
        set_String(v.getStringValue());
    }

    private String getPrimitiveTypeName() {
        SchemaType type = schemaType();
        if (type.isNoType()) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        SchemaType t = type.getPrimitiveType();
        if (t == null) {
            return "complex";
        }
        return t.getName().getLocalPart();
    }

    private boolean comparable_value_spaces(SchemaType t1, SchemaType t2) {
        if (t1.getSimpleVariety() == 2 || t2.getSimpleVariety() == 2) {
            throw new AssertionError();
        }
        if (!t1.isSimpleType() && !t2.isSimpleType()) {
            return t1.getContentType() == t2.getContentType();
        }
        if (!t1.isSimpleType() || !t2.isSimpleType()) {
            return false;
        }
        if (t1.getSimpleVariety() == 3 && t2.getSimpleVariety() == 3) {
            return true;
        }
        if (t1.getSimpleVariety() == 3 || t2.getSimpleVariety() == 3) {
            return false;
        }
        return t1.getPrimitiveType().equals(t2.getPrimitiveType());
    }

    private boolean valueEqualsImpl(XmlObject xmlobj) {
        check_dated();
        SchemaType typethis = instanceType();
        SchemaType typeother = ((SimpleValue) xmlobj).instanceType();
        if (typethis == null && typeother == null) {
            return true;
        }
        if (typethis == null || typeother == null || !comparable_value_spaces(typethis, typeother)) {
            return false;
        }
        if (xmlobj.schemaType().getSimpleVariety() == 2) {
            return underlying(xmlobj).equal_to(this);
        }
        return equal_to(xmlobj);
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final boolean valueEquals(XmlObject xmlobj) {
        boolean valueEqualsImpl;
        boolean valueEqualsImpl2;
        boolean valueEqualsImpl3;
        try {
            try {
                if (isImmutable()) {
                    if (xmlobj.isImmutable()) {
                        return valueEqualsImpl(xmlobj);
                    }
                    synchronized (xmlobj.monitor()) {
                        valueEqualsImpl3 = valueEqualsImpl(xmlobj);
                    }
                    if (0 != 0) {
                        GlobalLock.release();
                    }
                    return valueEqualsImpl3;
                }
                if (!xmlobj.isImmutable() && monitor() != xmlobj.monitor()) {
                    GlobalLock.acquire();
                    synchronized (monitor()) {
                        synchronized (xmlobj.monitor()) {
                            GlobalLock.release();
                            valueEqualsImpl2 = valueEqualsImpl(xmlobj);
                        }
                    }
                    if (0 != 0) {
                        GlobalLock.release();
                    }
                    return valueEqualsImpl2;
                }
                synchronized (monitor()) {
                    valueEqualsImpl = valueEqualsImpl(xmlobj);
                }
                if (0 != 0) {
                    GlobalLock.release();
                }
                return valueEqualsImpl;
            } catch (InterruptedException e) {
                throw new XmlRuntimeException(e);
            }
        } finally {
            if (0 != 0) {
                GlobalLock.release();
            }
        }
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final int compareTo(Object obj) {
        int result = compareValue((XmlObject) obj);
        if (result == 2) {
            throw new ClassCastException();
        }
        return result;
    }

    private int compareValueImpl(XmlObject xmlobj) {
        try {
            SchemaType type1 = instanceType();
            SchemaType type2 = ((SimpleValue) xmlobj).instanceType();
            if (type1 == null && type2 == null) {
                return 0;
            }
            if (type1 == null || type2 == null || !type1.isSimpleType() || type1.isURType() || !type2.isSimpleType() || type2.isURType()) {
                return 2;
            }
            if (type1.getPrimitiveType().getBuiltinTypeCode() != type2.getPrimitiveType().getBuiltinTypeCode()) {
                return 2;
            }
            return compare_to(xmlobj);
        } catch (XmlValueOutOfRangeException e) {
            return 2;
        }
    }

    @Override // org.apache.xmlbeans.XmlObject
    public final int compareValue(XmlObject xmlobj) {
        int compareValueImpl;
        int compareValueImpl2;
        int compareValueImpl3;
        if (xmlobj == null) {
            return 2;
        }
        try {
            try {
                if (isImmutable()) {
                    if (xmlobj.isImmutable()) {
                        return compareValueImpl(xmlobj);
                    }
                    synchronized (xmlobj.monitor()) {
                        compareValueImpl3 = compareValueImpl(xmlobj);
                    }
                    if (0 != 0) {
                        GlobalLock.release();
                    }
                    return compareValueImpl3;
                }
                if (!xmlobj.isImmutable() && monitor() != xmlobj.monitor()) {
                    GlobalLock.acquire();
                    synchronized (monitor()) {
                        synchronized (xmlobj.monitor()) {
                            GlobalLock.release();
                            compareValueImpl2 = compareValueImpl(xmlobj);
                        }
                    }
                    if (0 != 0) {
                        GlobalLock.release();
                    }
                    return compareValueImpl2;
                }
                synchronized (monitor()) {
                    compareValueImpl = compareValueImpl(xmlobj);
                }
                if (0 != 0) {
                    GlobalLock.release();
                }
                return compareValueImpl;
            } catch (InterruptedException e) {
                throw new XmlRuntimeException(e);
            }
        } finally {
            if (0 != 0) {
                GlobalLock.release();
            }
        }
    }

    protected int compare_to(XmlObject xmlobj) {
        if (equal_to(xmlobj)) {
            return 0;
        }
        return 2;
    }

    @Override // org.apache.xmlbeans.XmlObject
    public int valueHashCode() {
        int value_hash_code;
        synchronized (monitor()) {
            value_hash_code = value_hash_code();
        }
        return value_hash_code;
    }

    public boolean isInstanceOf(SchemaType type) {
        if (type.getSimpleVariety() != 2) {
            for (SchemaType myType = instanceType(); myType != null; myType = myType.getBaseType()) {
                if (type == myType) {
                    return true;
                }
            }
            return false;
        }
        Set<SchemaType> ctypes = new HashSet<>(Arrays.asList(type.getUnionConstituentTypes()));
        for (SchemaType myType2 = instanceType(); myType2 != null; myType2 = myType2.getBaseType()) {
            if (ctypes.contains(myType2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!isImmutable()) {
            return super.equals(obj);
        }
        if (!(obj instanceof XmlObject)) {
            return false;
        }
        XmlObject xmlobj = (XmlObject) obj;
        if (xmlobj.isImmutable()) {
            return valueEquals(xmlobj);
        }
        return false;
    }

    public final int hashCode() {
        if (!isImmutable()) {
            return super.hashCode();
        }
        synchronized (monitor()) {
            if (isNil()) {
                return 0;
            }
            return value_hash_code();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
    
        if (r0.toChild(r5) != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        r1.add(r0.getObject());
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
    
        if (r0.toNextSibling(r5) != false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002e, code lost:
    
        if (r1.isEmpty() == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0030, code lost:
    
        r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0032, code lost:
    
        if (r0 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0034, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0037, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0038, code lost:
    
        r2 = (org.apache.xmlbeans.XmlObject[]) r1.toArray(org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0040, code lost:
    
        if (r0 == null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0042, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0045, code lost:
    
        return r2;
     */
    @Override // org.apache.xmlbeans.XmlObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.xmlbeans.XmlObject[] selectChildren(javax.xml.namespace.QName r5) {
        /*
            r4 = this;
            org.apache.xmlbeans.XmlCursor r0 = r4.newCursor()
            boolean r1 = r0.isContainer()     // Catch: java.lang.Throwable -> L46
            if (r1 != 0) goto L12
            org.apache.xmlbeans.XmlObject[] r1 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L46
            if (r0 == 0) goto L11
            r0.close()
        L11:
            return r1
        L12:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L46
            r1.<init>()     // Catch: java.lang.Throwable -> L46
            boolean r2 = r0.toChild(r5)     // Catch: java.lang.Throwable -> L46
            if (r2 == 0) goto L2a
        L1d:
            org.apache.xmlbeans.XmlObject r2 = r0.getObject()     // Catch: java.lang.Throwable -> L46
            r1.add(r2)     // Catch: java.lang.Throwable -> L46
            boolean r2 = r0.toNextSibling(r5)     // Catch: java.lang.Throwable -> L46
            if (r2 != 0) goto L1d
        L2a:
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L46
            if (r2 == 0) goto L38
            org.apache.xmlbeans.XmlObject[] r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L46
            if (r0 == 0) goto L37
            r0.close()
        L37:
            return r2
        L38:
            org.apache.xmlbeans.XmlObject[] r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L46
            java.lang.Object[] r2 = r1.toArray(r2)     // Catch: java.lang.Throwable -> L46
            org.apache.xmlbeans.XmlObject[] r2 = (org.apache.xmlbeans.XmlObject[]) r2     // Catch: java.lang.Throwable -> L46
            if (r0 == 0) goto L45
            r0.close()
        L45:
            return r2
        L46:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L48
        L48:
            r2 = move-exception
            if (r0 == 0) goto L53
            r0.close()     // Catch: java.lang.Throwable -> L4f
            goto L53
        L4f:
            r3 = move-exception
            r1.addSuppressed(r3)
        L53:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlObjectBase.selectChildren(javax.xml.namespace.QName):org.apache.xmlbeans.XmlObject[]");
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject[] selectChildren(String elementUri, String elementLocalName) {
        return selectChildren(new QName(elementUri, elementLocalName));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        if (r0.toFirstChild() != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0023, code lost:
    
        if (r0.isContainer() == false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
    
        if (r5.contains(r0.getName()) == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002f, code lost:
    
        r1.add(r0.getObject());
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
    
        if (r0.toNextSibling() != false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0042, code lost:
    
        throw new java.lang.AssertionError();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0047, code lost:
    
        if (r1.isEmpty() == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0049, code lost:
    
        r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004b, code lost:
    
        if (r0 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004d, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0050, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0051, code lost:
    
        r2 = (org.apache.xmlbeans.XmlObject[]) r1.toArray(org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0059, code lost:
    
        if (r0 == null) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005b, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005e, code lost:
    
        return r2;
     */
    @Override // org.apache.xmlbeans.XmlObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.xmlbeans.XmlObject[] selectChildren(org.apache.xmlbeans.QNameSet r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L6d
            org.apache.xmlbeans.XmlCursor r0 = r4.newCursor()
            boolean r1 = r0.isContainer()     // Catch: java.lang.Throwable -> L5f
            if (r1 != 0) goto L14
            org.apache.xmlbeans.XmlObject[] r1 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L5f
            if (r0 == 0) goto L13
            r0.close()
        L13:
            return r1
        L14:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L5f
            r1.<init>()     // Catch: java.lang.Throwable -> L5f
            boolean r2 = r0.toFirstChild()     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L43
        L1f:
            boolean r2 = r0.isContainer()     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L3d
            javax.xml.namespace.QName r2 = r0.getName()     // Catch: java.lang.Throwable -> L5f
            boolean r2 = r5.contains(r2)     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L36
            org.apache.xmlbeans.XmlObject r2 = r0.getObject()     // Catch: java.lang.Throwable -> L5f
            r1.add(r2)     // Catch: java.lang.Throwable -> L5f
        L36:
            boolean r2 = r0.toNextSibling()     // Catch: java.lang.Throwable -> L5f
            if (r2 != 0) goto L1f
            goto L43
        L3d:
            java.lang.AssertionError r2 = new java.lang.AssertionError     // Catch: java.lang.Throwable -> L5f
            r2.<init>()     // Catch: java.lang.Throwable -> L5f
            throw r2     // Catch: java.lang.Throwable -> L5f
        L43:
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L51
            org.apache.xmlbeans.XmlObject[] r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L5f
            if (r0 == 0) goto L50
            r0.close()
        L50:
            return r2
        L51:
            org.apache.xmlbeans.XmlObject[] r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L5f
            java.lang.Object[] r2 = r1.toArray(r2)     // Catch: java.lang.Throwable -> L5f
            org.apache.xmlbeans.XmlObject[] r2 = (org.apache.xmlbeans.XmlObject[]) r2     // Catch: java.lang.Throwable -> L5f
            if (r0 == 0) goto L5e
            r0.close()
        L5e:
            return r2
        L5f:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L61
        L61:
            r2 = move-exception
            if (r0 == 0) goto L6c
            r0.close()     // Catch: java.lang.Throwable -> L68
            goto L6c
        L68:
            r3 = move-exception
            r1.addSuppressed(r3)
        L6c:
            throw r2
        L6d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlObjectBase.selectChildren(org.apache.xmlbeans.QNameSet):org.apache.xmlbeans.XmlObject[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0016, code lost:
    
        if (r0.toFirstAttribute() != false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0020, code lost:
    
        if (r0.getName().equals(r5) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
    
        if (r0.toNextAttribute() != false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0022, code lost:
    
        r1 = r0.getObject();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0026, code lost:
    
        if (r0 == null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0028, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x002b, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0033, code lost:
    
        if (r0 == null) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0035, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0038, code lost:
    
        return null;
     */
    @Override // org.apache.xmlbeans.XmlObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.xmlbeans.XmlObject selectAttribute(javax.xml.namespace.QName r5) {
        /*
            r4 = this;
            org.apache.xmlbeans.XmlCursor r0 = r4.newCursor()
            boolean r1 = r0.isContainer()     // Catch: java.lang.Throwable -> L39
            r2 = 0
            if (r1 != 0) goto L12
        Lc:
            if (r0 == 0) goto L11
            r0.close()
        L11:
            return r2
        L12:
            boolean r1 = r0.toFirstAttribute()     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L32
        L18:
            javax.xml.namespace.QName r1 = r0.getName()     // Catch: java.lang.Throwable -> L39
            boolean r1 = r1.equals(r5)     // Catch: java.lang.Throwable -> L39
            if (r1 == 0) goto L2c
            org.apache.xmlbeans.XmlObject r1 = r0.getObject()     // Catch: java.lang.Throwable -> L39
            if (r0 == 0) goto L2b
            r0.close()
        L2b:
            return r1
        L2c:
            boolean r1 = r0.toNextAttribute()     // Catch: java.lang.Throwable -> L39
            if (r1 != 0) goto L18
        L32:
        L33:
            if (r0 == 0) goto L38
            r0.close()
        L38:
            return r2
        L39:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L3b
        L3b:
            r2 = move-exception
            if (r0 == 0) goto L46
            r0.close()     // Catch: java.lang.Throwable -> L42
            goto L46
        L42:
            r3 = move-exception
            r1.addSuppressed(r3)
        L46:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlObjectBase.selectAttribute(javax.xml.namespace.QName):org.apache.xmlbeans.XmlObject");
    }

    @Override // org.apache.xmlbeans.XmlObject
    public XmlObject selectAttribute(String attributeUri, String attributeLocalName) {
        return selectAttribute(new QName(attributeUri, attributeLocalName));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        if (r0.toFirstAttribute() != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0027, code lost:
    
        if (r5.contains(r0.getName()) == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0029, code lost:
    
        r1.add(r0.getObject());
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0034, code lost:
    
        if (r0.toNextAttribute() != false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003a, code lost:
    
        if (r1.isEmpty() == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003c, code lost:
    
        r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x003e, code lost:
    
        if (r0 == null) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0040, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0043, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0044, code lost:
    
        r2 = (org.apache.xmlbeans.XmlObject[]) r1.toArray(org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004c, code lost:
    
        if (r0 == null) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004e, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0051, code lost:
    
        return r2;
     */
    @Override // org.apache.xmlbeans.XmlObject
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.xmlbeans.XmlObject[] selectAttributes(org.apache.xmlbeans.QNameSet r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L60
            org.apache.xmlbeans.XmlCursor r0 = r4.newCursor()
            boolean r1 = r0.isContainer()     // Catch: java.lang.Throwable -> L52
            if (r1 != 0) goto L14
            org.apache.xmlbeans.XmlObject[] r1 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L13
            r0.close()
        L13:
            return r1
        L14:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L52
            r1.<init>()     // Catch: java.lang.Throwable -> L52
            boolean r2 = r0.toFirstAttribute()     // Catch: java.lang.Throwable -> L52
            if (r2 == 0) goto L36
        L1f:
            javax.xml.namespace.QName r2 = r0.getName()     // Catch: java.lang.Throwable -> L52
            boolean r2 = r5.contains(r2)     // Catch: java.lang.Throwable -> L52
            if (r2 == 0) goto L30
            org.apache.xmlbeans.XmlObject r2 = r0.getObject()     // Catch: java.lang.Throwable -> L52
            r1.add(r2)     // Catch: java.lang.Throwable -> L52
        L30:
            boolean r2 = r0.toNextAttribute()     // Catch: java.lang.Throwable -> L52
            if (r2 != 0) goto L1f
        L36:
            boolean r2 = r1.isEmpty()     // Catch: java.lang.Throwable -> L52
            if (r2 == 0) goto L44
            org.apache.xmlbeans.XmlObject[] r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L43
            r0.close()
        L43:
            return r2
        L44:
            org.apache.xmlbeans.XmlObject[] r2 = org.apache.xmlbeans.impl.values.XmlObjectBase.EMPTY_RESULT     // Catch: java.lang.Throwable -> L52
            java.lang.Object[] r2 = r1.toArray(r2)     // Catch: java.lang.Throwable -> L52
            org.apache.xmlbeans.XmlObject[] r2 = (org.apache.xmlbeans.XmlObject[]) r2     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L51
            r0.close()
        L51:
            return r2
        L52:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L54
        L54:
            r2 = move-exception
            if (r0 == 0) goto L5f
            r0.close()     // Catch: java.lang.Throwable -> L5b
            goto L5f
        L5b:
            r3 = move-exception
            r1.addSuppressed(r3)
        L5f:
            throw r2
        L60:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.values.XmlObjectBase.selectAttributes(org.apache.xmlbeans.QNameSet):org.apache.xmlbeans.XmlObject[]");
    }

    public Object writeReplace() {
        synchronized (monitor()) {
            if (isRootXmlObject()) {
                return new SerializedRootObject(this);
            }
            return new SerializedInteriorObject(this, getRootXmlObject());
        }
    }

    private boolean isRootXmlObject() {
        XmlCursor cur = newCursor();
        if (cur == null) {
            if (cur != null) {
                cur.close();
                return false;
            }
            return false;
        }
        try {
            boolean z = !cur.toParent();
            if (cur != null) {
                cur.close();
            }
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cur != null) {
                    try {
                        cur.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private XmlObject getRootXmlObject() {
        XmlCursor cur = newCursor();
        if (cur != null) {
            try {
                cur.toStartDoc();
                XmlObject object = cur.getObject();
                if (cur != null) {
                    cur.close();
                }
                return object;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (cur != null) {
                        try {
                            cur.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return this;
    }

    /* loaded from: classes11.dex */
    private static class SerializedRootObject implements Serializable {
        private static final long serialVersionUID = 1;
        transient XmlObject _impl;
        transient Class<? extends XmlObject> _xbeanClass;

        /* synthetic */ SerializedRootObject(XmlObject x0, AnonymousClass1 x1) {
            this(x0);
        }

        private SerializedRootObject(XmlObject impl) {
            this._xbeanClass = impl.schemaType().getJavaClass();
            this._impl = impl;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(this._xbeanClass);
            out.writeShort(0);
            out.writeShort(1);
            out.writeShort(1);
            String xmlText = this._impl.xmlText();
            out.writeObject(xmlText);
            out.writeBoolean(false);
        }

        private void readObject(ObjectInputStream in) throws IOException {
            String xmlText;
            try {
                this._xbeanClass = (Class) in.readObject();
                int utfBytes = in.readUnsignedShort();
                int majorVersionNum = 0;
                int minorVersionNum = 0;
                if (utfBytes == 0) {
                    majorVersionNum = in.readUnsignedShort();
                    minorVersionNum = in.readUnsignedShort();
                }
                switch (majorVersionNum) {
                    case 0:
                        xmlText = readObjectV0(in, utfBytes);
                        in.readBoolean();
                        break;
                    case 1:
                        if (minorVersionNum == 1) {
                            xmlText = (String) in.readObject();
                            in.readBoolean();
                            break;
                        } else {
                            throw new IOException("Deserialization error: version number " + majorVersionNum + "." + minorVersionNum + " not supported.");
                        }
                    default:
                        throw new IOException("Deserialization error: version number " + majorVersionNum + "." + minorVersionNum + " not supported.");
                }
                XmlOptions opts = new XmlOptions().setDocumentType(XmlBeans.typeForClass(this._xbeanClass));
                this._impl = XmlBeans.getContextTypeLoader().parse(xmlText, (SchemaType) null, opts);
            } catch (Exception e) {
                throw new IOException(e.getMessage(), e);
            }
        }

        private String readObjectV0(ObjectInputStream in, int utfBytes) throws IOException {
            byte[] bArray = new byte[utfBytes + 2];
            bArray[0] = (byte) ((utfBytes >> 8) & 255);
            bArray[1] = (byte) (utfBytes & 255);
            int totalBytesRead = 0;
            while (totalBytesRead < utfBytes) {
                int numRead = in.read(bArray, totalBytesRead + 2, utfBytes - totalBytesRead);
                if (numRead == -1) {
                    break;
                }
                totalBytesRead += numRead;
            }
            if (totalBytesRead != utfBytes) {
                throw new IOException("Error reading backwards compatible XmlObject: number of bytes read (" + totalBytesRead + ") != number expected (" + utfBytes + ")");
            }
            LongUTFDataInputStream dis = null;
            try {
                dis = new LongUTFDataInputStream(new ByteArrayInputStream(bArray));
                String str = dis.readLongUTF();
                dis.close();
                return str;
            } catch (Throwable th) {
                if (dis != null) {
                    dis.close();
                }
                throw th;
            }
        }

        private Object readResolve() throws ObjectStreamException {
            return this._impl;
        }
    }

    /* loaded from: classes11.dex */
    private static class SerializedInteriorObject implements Serializable {
        private static final long serialVersionUID = 1;
        transient XmlObject _impl;
        transient XmlObject _root;

        /* synthetic */ SerializedInteriorObject(XmlObject x0, XmlObject x1, AnonymousClass1 x2) {
            this(x0, x1);
        }

        private SerializedInteriorObject(XmlObject impl, XmlObject root) {
            this._impl = impl;
            this._root = root;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(this._root);
            out.writeBoolean(false);
            out.writeInt(distanceToRoot());
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            this._root = (XmlObject) in.readObject();
            in.readBoolean();
            this._impl = objectAtDistance(in.readInt());
        }

        private Object readResolve() throws ObjectStreamException {
            return this._impl;
        }

        private int distanceToRoot() {
            int count = 0;
            XmlCursor cur = this._impl.newCursor();
            while (!cur.toPrevToken().isNone()) {
                try {
                    if (!cur.currentTokenType().isNamespace()) {
                        count++;
                    }
                } finally {
                }
            }
            if (cur != null) {
                cur.close();
            }
            return count;
        }

        private XmlObject objectAtDistance(int count) {
            XmlCursor cur = this._root.newCursor();
            while (count > 0) {
                try {
                    cur.toNextToken();
                    if (!cur.currentTokenType().isNamespace()) {
                        count--;
                    }
                } finally {
                }
            }
            XmlObject result = cur.getObject();
            if (cur != null) {
                cur.close();
            }
            return result;
        }
    }

    public static Object java_value(XmlObject obj) {
        if (obj.isNil()) {
            return null;
        }
        if (!(obj instanceof XmlAnySimpleType)) {
            return obj;
        }
        SchemaType instanceType = ((SimpleValue) obj).instanceType();
        if (instanceType == null) {
            throw new AssertionError("Nil case should have been handled above");
        }
        if (instanceType.getSimpleVariety() == 3) {
            return ((SimpleValue) obj).getListValue();
        }
        SimpleValue base = (SimpleValue) obj;
        switch (instanceType.getPrimitiveType().getBuiltinTypeCode()) {
            case 2:
            case 8:
            case 12:
                return base.getStringValue();
            case 3:
                return base.getBooleanValue() ? Boolean.TRUE : Boolean.FALSE;
            case 4:
            case 5:
                return base.getByteArrayValue();
            case 6:
                return base.getStringValue();
            case 7:
                return base.getQNameValue();
            case 9:
                return Float.valueOf(base.getFloatValue());
            case 10:
                return Double.valueOf(base.getDoubleValue());
            case 11:
                switch (instanceType.getDecimalSize()) {
                    case 8:
                        return Byte.valueOf(base.getByteValue());
                    case 16:
                        return Short.valueOf(base.getShortValue());
                    case 32:
                        return Integer.valueOf(base.getIntValue());
                    case 64:
                        return Long.valueOf(base.getLongValue());
                    case 1000000:
                        return base.getBigIntegerValue();
                    case SchemaType.SIZE_BIG_DECIMAL /* 1000001 */:
                        return base.getBigDecimalValue();
                    default:
                        throw new AssertionError("invalid numeric bit count");
                }
            case 13:
                return base.getGDurationValue();
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return base.getCalendarValue();
            default:
                throw new AssertionError("encountered nonprimitive type.");
        }
    }

    public XmlAnySimpleType get_default_attribute_value(QName name) {
        SchemaLocalAttribute sAttr;
        SchemaType sType = schemaType();
        SchemaAttributeModel aModel = sType.getAttributeModel();
        if (aModel == null || (sAttr = aModel.getAttribute(name)) == null) {
            return null;
        }
        return sAttr.getDefaultValue();
    }

    private List<XmlObjectBase> getBaseArray(QName elementName) {
        check_orphaned();
        ArrayList arrayList = new ArrayList();
        get_store().find_all_element_users(elementName, arrayList);
        return arrayList;
    }

    private List<XmlObjectBase> getBaseArray(QNameSet elementSet) {
        check_orphaned();
        ArrayList arrayList = new ArrayList();
        get_store().find_all_element_users(elementSet, arrayList);
        return arrayList;
    }

    public <T> T[] getObjectArray(QName qName, Function<SimpleValue, T> function, IntFunction<T[]> intFunction) {
        T[] tArr;
        synchronized (monitor()) {
            tArr = (T[]) getBaseArray(qName).stream().map(function).toArray(intFunction);
        }
        return tArr;
    }

    public <T> T[] getEnumArray(QName qName, IntFunction<T[]> intFunction) {
        T[] tArr;
        synchronized (monitor()) {
            tArr = (T[]) getBaseArray(qName).stream().map(new XmlObjectBase$$ExternalSyntheticLambda4()).toArray(intFunction);
        }
        return tArr;
    }

    public boolean[] getBooleanArray(QName elementName) {
        boolean[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementName);
            result = new boolean[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getBooleanValue();
            }
        }
        return result;
    }

    public float[] getFloatArray(QName elementName) {
        float[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementName);
            result = new float[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getFloatValue();
            }
        }
        return result;
    }

    public double[] getDoubleArray(QName elementName) {
        double[] array;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementName);
            Stream<XmlObjectBase> stream = targetList.stream();
            SimpleValue.class.getClass();
            array = stream.map(new XmlObjectBase$$ExternalSyntheticLambda0(SimpleValue.class)).mapToDouble(new XmlObjectBase$$ExternalSyntheticLambda1()).toArray();
        }
        return array;
    }

    public byte[] getByteArray(QName elementName) {
        byte[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementName);
            result = new byte[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getByteValue();
            }
        }
        return result;
    }

    public short[] getShortArray(QName elementName) {
        short[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementName);
            result = new short[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getShortValue();
            }
        }
        return result;
    }

    public int[] getIntArray(QName elementName) {
        int[] array;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementName);
            Stream<XmlObjectBase> stream = targetList.stream();
            SimpleValue.class.getClass();
            array = stream.map(new XmlObjectBase$$ExternalSyntheticLambda0(SimpleValue.class)).mapToInt(new XmlObjectBase$$ExternalSyntheticLambda3()).toArray();
        }
        return array;
    }

    public long[] getLongArray(QName elementName) {
        long[] array;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementName);
            Stream<XmlObjectBase> stream = targetList.stream();
            SimpleValue.class.getClass();
            array = stream.map(new XmlObjectBase$$ExternalSyntheticLambda0(SimpleValue.class)).mapToLong(new XmlObjectBase$$ExternalSyntheticLambda2()).toArray();
        }
        return array;
    }

    public <T extends XmlObject> T[] getXmlObjectArray(QName qName, T[] tArr) {
        T[] tArr2;
        synchronized (monitor()) {
            tArr2 = (T[]) ((XmlObject[]) getBaseArray(qName).toArray(tArr));
        }
        return tArr2;
    }

    protected <T> T[] getObjectArray(QNameSet qNameSet, Function<SimpleValue, T> function, IntFunction<T[]> intFunction) {
        T[] tArr;
        synchronized (monitor()) {
            tArr = (T[]) getBaseArray(qNameSet).stream().map(function).toArray(intFunction);
        }
        return tArr;
    }

    protected <T> T[] getEnumArray(QNameSet qNameSet, IntFunction<T[]> intFunction) {
        T[] tArr;
        synchronized (monitor()) {
            tArr = (T[]) getBaseArray(qNameSet).stream().map(new XmlObjectBase$$ExternalSyntheticLambda4()).toArray(intFunction);
        }
        return tArr;
    }

    protected boolean[] getBooleanArray(QNameSet elementSet) {
        boolean[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementSet);
            result = new boolean[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getBooleanValue();
            }
        }
        return result;
    }

    protected float[] getFloatArray(QNameSet elementSet) {
        float[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementSet);
            result = new float[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getFloatValue();
            }
        }
        return result;
    }

    protected double[] getDoubleArray(QNameSet elementSet) {
        double[] array;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementSet);
            Stream<XmlObjectBase> stream = targetList.stream();
            SimpleValue.class.getClass();
            array = stream.map(new XmlObjectBase$$ExternalSyntheticLambda0(SimpleValue.class)).mapToDouble(new XmlObjectBase$$ExternalSyntheticLambda1()).toArray();
        }
        return array;
    }

    protected byte[] getByteArray(QNameSet elementSet) {
        byte[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementSet);
            result = new byte[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getByteValue();
            }
        }
        return result;
    }

    protected short[] getShortArray(QNameSet elementSet) {
        short[] result;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementSet);
            result = new short[targetList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = targetList.get(i).getShortValue();
            }
        }
        return result;
    }

    protected int[] getIntArray(QNameSet elementSet) {
        int[] array;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementSet);
            Stream<XmlObjectBase> stream = targetList.stream();
            SimpleValue.class.getClass();
            array = stream.map(new XmlObjectBase$$ExternalSyntheticLambda0(SimpleValue.class)).mapToInt(new XmlObjectBase$$ExternalSyntheticLambda3()).toArray();
        }
        return array;
    }

    protected long[] getLongArray(QNameSet elementSet) {
        long[] array;
        synchronized (monitor()) {
            List<XmlObjectBase> targetList = getBaseArray(elementSet);
            Stream<XmlObjectBase> stream = targetList.stream();
            SimpleValue.class.getClass();
            array = stream.map(new XmlObjectBase$$ExternalSyntheticLambda0(SimpleValue.class)).mapToLong(new XmlObjectBase$$ExternalSyntheticLambda2()).toArray();
        }
        return array;
    }

    protected <T extends XmlObject> T[] getXmlObjectArray(QNameSet qNameSet, T[] tArr) {
        T[] tArr2;
        synchronized (monitor()) {
            tArr2 = (T[]) ((XmlObject[]) getBaseArray(qNameSet).toArray(tArr));
        }
        return tArr2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends XmlObject> T[] xgetArray(QName qName, IntFunction<T[]> intFunction) {
        T[] tArr;
        synchronized (monitor()) {
            check_orphaned();
            ArrayList arrayList = new ArrayList();
            get_store().find_all_element_users(qName, arrayList);
            tArr = (T[]) ((XmlObject[]) arrayList.stream().toArray(intFunction));
        }
        return tArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected <T extends XmlObject> T[] xgetArray(QNameSet qNameSet, IntFunction<T[]> intFunction) {
        T[] tArr;
        synchronized (monitor()) {
            check_orphaned();
            ArrayList arrayList = new ArrayList();
            get_store().find_all_element_users(qNameSet, arrayList);
            tArr = (T[]) ((XmlObject[]) arrayList.stream().toArray(intFunction));
        }
        return tArr;
    }
}
