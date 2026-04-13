package org.apache.xmlbeans.impl.validator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlValidationError;
import org.apache.xmlbeans.impl.common.IdentityConstraint;
import org.apache.xmlbeans.impl.common.InvalidLexicalValueException;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;
import org.apache.xmlbeans.impl.util.XsTypeConverter;
import org.apache.xmlbeans.impl.values.JavaBase64HolderEx;
import org.apache.xmlbeans.impl.values.JavaBooleanHolderEx;
import org.apache.xmlbeans.impl.values.JavaDecimalHolderEx;
import org.apache.xmlbeans.impl.values.JavaDoubleHolderEx;
import org.apache.xmlbeans.impl.values.JavaFloatHolderEx;
import org.apache.xmlbeans.impl.values.JavaHexBinaryHolderEx;
import org.apache.xmlbeans.impl.values.JavaNotationHolderEx;
import org.apache.xmlbeans.impl.values.JavaQNameHolderEx;
import org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx;
import org.apache.xmlbeans.impl.values.JavaUriHolderEx;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.apache.xmlbeans.impl.values.XmlDateImpl;
import org.apache.xmlbeans.impl.values.XmlDurationImpl;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

/* loaded from: classes11.dex */
public final class Validator implements ValidatorListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean _booleanValue;
    private byte[] _byteArrayValue;
    private final IdentityConstraint _constraintEngine;
    private BigDecimal _decimalValue;
    private double _doubleValue;
    private int _eatContent;
    private Collection<XmlError> _errorListener;
    private int _errorState;
    private float _floatValue;
    private GDate _gdateValue;
    private GDuration _gdurationValue;
    private final SchemaTypeLoader _globalTypes;
    private boolean _invalid;
    private List<SchemaType> _listTypes;
    private List<Object> _listValue;
    private SchemaLocalAttribute _localAttribute;
    private SchemaLocalElement _localElement;
    private QName _qnameValue;
    private final SchemaField _rootField;
    private final SchemaType _rootType;
    private State _stateStack;
    private final boolean _strict;
    private String _stringValue;
    private int _suspendErrors;
    private final boolean _treatLaxAsSkip;
    private SchemaType _unionType;
    private final ValidatorVC _vc;
    private final LinkedList<TypeStoreVisitor> _visitorPool = new LinkedList<>();
    private SchemaAttributeModel _wildcardAttribute;
    private SchemaParticle _wildcardElement;

    public Validator(SchemaType type, SchemaField field, SchemaTypeLoader globalLoader, XmlOptions options, Collection<XmlError> defaultErrorListener) {
        XmlOptions options2 = XmlOptions.maskNull(options);
        this._errorListener = options2.getErrorListener();
        this._treatLaxAsSkip = options2.isValidateTreatLaxAsSkip();
        this._strict = options2.isValidateStrict();
        if (this._errorListener == null) {
            this._errorListener = defaultErrorListener;
        }
        this._constraintEngine = new IdentityConstraint(this._errorListener, type.isDocumentType());
        this._globalTypes = globalLoader;
        this._rootType = type;
        this._rootField = field;
        this._vc = new ValidatorVC();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class ValidatorVC implements ValidationContext {
        ValidatorListener.Event _event;

        private ValidatorVC() {
        }

        @Override // org.apache.xmlbeans.impl.common.ValidationContext
        public void invalid(String message) {
            Validator.this.emitError(this._event, message, null, null, 1001);
        }

        @Override // org.apache.xmlbeans.impl.common.ValidationContext
        public void invalid(String code, Object[] args) {
            Validator.this.emitError(this._event, code, args, null, null, 1001, null);
        }
    }

    public boolean isValid() {
        return !this._invalid && this._constraintEngine.isValid();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitError(ValidatorListener.Event event, String message, QName offendingQName, SchemaType expectedSchemaType, int errorType) {
        emitError(event, message, null, null, 0, null, offendingQName, expectedSchemaType, null, errorType, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitError(ValidatorListener.Event event, String code, Object[] args, QName offendingQName, SchemaType expectedSchemaType, int errorType, SchemaType badSchemaType) {
        emitError(event, null, code, args, 0, null, offendingQName, expectedSchemaType, null, errorType, badSchemaType);
    }

    private void emitError(ValidatorListener.Event event, String message, String code, Object[] args, int severity, QName fieldName, QName offendingQName, SchemaType expectedSchemaType, List<QName> expectedQNames, int errorType, SchemaType badSchemaType) {
        XmlError error;
        this._errorState++;
        if (this._suspendErrors == 0) {
            if (severity == 0) {
                this._invalid = true;
            }
            if (this._errorListener != null) {
                if (event == null) {
                    throw new AssertionError();
                }
                XmlCursor curs = event.getLocationAsCursor();
                if (curs != null) {
                    error = XmlValidationError.forCursorWithDetails(message, code, args, severity, curs, fieldName, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
                } else {
                    error = XmlValidationError.forLocationWithDetails(message, code, args, severity, event.getLocation(), fieldName, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
                }
                this._errorListener.add(error);
            }
        }
    }

    private void emitFieldError(ValidatorListener.Event event, String code, Object[] args, QName offendingQName, SchemaType expectedSchemaType, List<QName> expectedQNames, int errorType, SchemaType badSchemaType) {
        QName fieldName;
        if (this._stateStack != null && this._stateStack._field != null) {
            QName fieldName2 = this._stateStack._field.getName();
            fieldName = fieldName2;
        } else {
            fieldName = null;
        }
        emitError(event, null, code, args, 0, fieldName, offendingQName, expectedSchemaType, expectedQNames, errorType, badSchemaType);
    }

    @Override // org.apache.xmlbeans.impl.common.ValidatorListener
    public void nextEvent(int kind, ValidatorListener.Event event) {
        resetValues();
        if (this._eatContent > 0) {
            switch (kind) {
                case 1:
                    this._eatContent++;
                    return;
                case 2:
                    this._eatContent--;
                    return;
                default:
                    return;
            }
        }
        if (kind != 1 && kind != 4 && kind != 2 && kind != 3 && kind != 5) {
            throw new AssertionError();
        }
        switch (kind) {
            case 1:
                beginEvent(event);
                return;
            case 2:
                endEvent(event);
                return;
            case 3:
                textEvent(event);
                return;
            case 4:
                attrEvent(event);
                return;
            case 5:
                endAttrsEvent(event);
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void beginEvent(org.apache.xmlbeans.impl.common.ValidatorListener.Event r20) {
        /*
            Method dump skipped, instructions count: 982
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.validator.Validator.beginEvent(org.apache.xmlbeans.impl.common.ValidatorListener$Event):void");
    }

    private void attrEvent(ValidatorListener.Event event) {
        QName attrName = event.getName();
        State state = topState();
        if (state._attrs == null) {
            state._attrs = new HashSet<>();
        }
        if (state._attrs.contains(attrName)) {
            emitFieldError(event, XmlErrorCodes.XML_DUPLICATE_ATTRIBUTE, new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        state._attrs.add(attrName);
        if (!state._canHaveAttrs) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$NO_WILDCARD, new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        SchemaLocalAttribute attrSchema = state._attrModel == null ? null : state._attrModel.getAttribute(attrName);
        if (attrSchema != null) {
            this._localAttribute = attrSchema;
            if (attrSchema.getUse() == 1) {
                emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$PROHIBITED_ATTRIBUTE, new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
                return;
            } else {
                String value = validateSimpleType(attrSchema.getType(), attrSchema, event, false, false);
                this._constraintEngine.attr(event, attrName, attrSchema.getType(), value);
                return;
            }
        }
        int wildcardProcess = state._attrModel.getWildcardProcess();
        this._wildcardAttribute = state._attrModel;
        if (wildcardProcess == 0) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$NO_WILDCARD, new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        QNameSet attrWildcardSet = state._attrModel.getWildcardSet();
        if (!attrWildcardSet.contains(attrName)) {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$NOT_WILDCARD_VALID, new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
            return;
        }
        if (wildcardProcess != 3) {
            if (wildcardProcess != 2 || !this._treatLaxAsSkip) {
                SchemaLocalAttribute attrSchema2 = this._globalTypes.findAttribute(attrName);
                this._localAttribute = attrSchema2;
                if (attrSchema2 == null) {
                    if (wildcardProcess == 2) {
                        return;
                    }
                    if (wildcardProcess == 1) {
                        emitFieldError(event, XmlErrorCodes.ASSESS_ATTR_SCHEMA_VALID$NOT_RESOLVED, new Object[]{QNameHelper.pretty(attrName)}, attrName, null, null, 1000, state._type);
                        return;
                    }
                    throw new AssertionError();
                }
                String value2 = validateSimpleType(attrSchema2.getType(), attrSchema2, event, false, false);
                this._constraintEngine.attr(event, attrName, attrSchema2.getType(), value2);
            }
        }
    }

    private void endAttrsEvent(ValidatorListener.Event event) {
        State state = topState();
        if (state._attrModel != null) {
            SchemaLocalAttribute[] attrs = state._attrModel.getAttributes();
            for (SchemaLocalAttribute sla : attrs) {
                if (state._attrs == null || !state._attrs.contains(sla.getName())) {
                    if (sla.getUse() == 3) {
                        emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$MISSING_REQUIRED_ATTRIBUTE, new Object[]{QNameHelper.pretty(sla.getName())}, sla.getName(), null, null, 1000, state._type);
                    } else if (sla.isDefault() || sla.isFixed()) {
                        this._constraintEngine.attr(event, sla.getName(), sla.getType(), sla.getDefaultText());
                    }
                }
            }
        }
    }

    private void endEvent(ValidatorListener.Event event) {
        this._localElement = null;
        this._wildcardElement = null;
        State state = topState();
        if (!state._isNil) {
            if (!state.end()) {
                findDetailedErrorEnd(event, state);
            }
            if (state._isEmpty) {
                handleText(event, true, state._field);
            }
        }
        popState(event);
        this._constraintEngine.endElement(event);
    }

    private void textEvent(ValidatorListener.Event event) {
        State state = topState();
        if (state._isNil) {
            emitFieldError(event, XmlErrorCodes.ELEM_LOCALLY_VALID$NIL_WITH_CONTENT, null, state._field.getName(), state._type, null, 4, state._type);
        } else {
            handleText(event, false, state._field);
        }
        state._isEmpty = false;
    }

    private void handleText(ValidatorListener.Event event, boolean emptyContent, SchemaField field) {
        Validator validator;
        ValidatorListener.Event event2;
        State state = topState();
        if (state._sawText) {
            validator = this;
            event2 = event;
        } else if (state._hasSimpleContent) {
            validator = this;
            String value = validator.validateSimpleType(state._type, field, event, emptyContent, true);
            event2 = event;
            validator._constraintEngine.text(event2, state._type, value, false);
            field = field;
            emptyContent = emptyContent;
        } else {
            validator = this;
            event2 = event;
            if (state._canHaveMixedContent) {
                String value2 = validator.validateSimpleType(XmlString.type, field, event2, emptyContent, true);
                field = field;
                event2 = event2;
                emptyContent = emptyContent;
                validator._constraintEngine.text(event2, XmlString.type, value2, false);
            } else {
                field = field;
                emptyContent = emptyContent;
                if (emptyContent) {
                    validator._constraintEngine.text(event2, state._type, null, true);
                } else {
                    validator._constraintEngine.text(event2, state._type, "", false);
                }
            }
        }
        if (!emptyContent && !state._canHaveMixedContent && !event2.textIsWhitespace() && !state._hasSimpleContent) {
            if (field instanceof SchemaLocalElement) {
                SchemaLocalElement e = (SchemaLocalElement) field;
                if (state._type.getContentType() != 1 && state._type.getContentType() != 3) {
                    throw new AssertionError();
                }
                String errorCode = state._type.getContentType() == 1 ? XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$EMPTY_WITH_CONTENT : XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$ELEMENT_ONLY_WITH_TEXT;
                validator.emitError(event2, errorCode, new Object[]{QNameHelper.pretty(e.getName())}, e.getName(), field.getType(), 3, null);
            } else {
                emitError(event2, "Can't have mixed content", event2.getName(), state._type, 3);
            }
        }
        if (!emptyContent) {
            state._sawText = true;
        }
    }

    private void findDetailedErrorBegin(ValidatorListener.Event event, State state, QName qName) {
        ArrayList<QName> expectedNames = new ArrayList<>();
        ArrayList<QName> optionalNames = new ArrayList<>();
        SchemaProperty[] eltProperties = state._type.getElementProperties();
        for (SchemaProperty sProp : eltProperties) {
            if (state.test(sProp.getName())) {
                if (BigInteger.ZERO.compareTo(sProp.getMinOccurs()) == 0) {
                    optionalNames.add(sProp.getName());
                } else {
                    expectedNames.add(sProp.getName());
                }
            }
        }
        List<QName> names = expectedNames.isEmpty() ? optionalNames : expectedNames;
        if (!names.isEmpty()) {
            String buf = (String) names.stream().map(new Validator$$ExternalSyntheticLambda0()).collect(Collectors.joining(StringUtils.SPACE));
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$EXPECTED_DIFFERENT_ELEMENT, new Object[]{Integer.valueOf(names.size()), buf, QNameHelper.pretty(qName)}, qName, null, names, 1, state._type);
        } else {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$ELEMENT_NOT_ALLOWED, new Object[]{QNameHelper.pretty(qName)}, qName, null, null, 1, state._type);
        }
    }

    private void findDetailedErrorEnd(ValidatorListener.Event event, State state) {
        SchemaProperty[] eltProperties = state._type.getElementProperties();
        ArrayList<QName> expectedNames = new ArrayList<>();
        ArrayList<QName> optionalNames = new ArrayList<>();
        for (SchemaProperty sProp : eltProperties) {
            if (state.test(sProp.getName())) {
                if (BigInteger.ZERO.compareTo(sProp.getMinOccurs()) == 0) {
                    optionalNames.add(sProp.getName());
                } else {
                    expectedNames.add(sProp.getName());
                }
            }
        }
        List<QName> names = expectedNames.isEmpty() ? optionalNames : expectedNames;
        if (!names.isEmpty()) {
            String buf = (String) names.stream().map(new Validator$$ExternalSyntheticLambda0()).collect(Collectors.joining(StringUtils.SPACE));
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$MISSING_ELEMENT, new Object[]{Integer.valueOf(names.size()), buf}, null, null, names, 1, state._type);
        } else {
            emitFieldError(event, XmlErrorCodes.ELEM_COMPLEX_TYPE_LOCALLY_VALID$EXPECTED_ELEMENT, null, null, null, null, 2, state._type);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class State {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        SchemaAttributeModel _attrModel;
        HashSet<QName> _attrs;
        boolean _canHaveAttrs;
        boolean _canHaveElements;
        boolean _canHaveMixedContent;
        SchemaField _field;
        boolean _hasSimpleContent;
        boolean _isEmpty;
        boolean _isNil;
        State _next;
        boolean _sawText;
        SchemaType _type;
        SchemaTypeVisitorImpl _visitor;

        private State() {
        }

        boolean visit(QName name) {
            return this._canHaveElements && this._visitor.visit(name);
        }

        boolean test(QName name) {
            return this._canHaveElements && this._visitor.testValid(name);
        }

        boolean end() {
            return !this._canHaveElements || this._visitor.visit(null);
        }

        SchemaParticle currentParticle() {
            if (this._visitor == null) {
                throw new AssertionError();
            }
            return this._visitor.currentParticle();
        }
    }

    private boolean derivedFromInteger(SchemaType type) {
        int btc = type.getBuiltinTypeCode();
        while (btc == 0) {
            type = type.getBaseType();
            btc = type.getBuiltinTypeCode();
        }
        return btc >= 22 && btc <= 34;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0024. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void newState(org.apache.xmlbeans.SchemaType r4, org.apache.xmlbeans.SchemaField r5, boolean r6) {
        /*
            r3 = this;
            org.apache.xmlbeans.impl.validator.Validator$State r0 = new org.apache.xmlbeans.impl.validator.Validator$State
            r1 = 0
            r0.<init>()
            r0._type = r4
            r0._field = r5
            r1 = 1
            r0._isEmpty = r1
            r0._isNil = r6
            boolean r2 = r4.isSimpleType()
            if (r2 == 0) goto L18
            r0._hasSimpleContent = r1
            goto L4a
        L18:
            r0._canHaveAttrs = r1
            org.apache.xmlbeans.SchemaAttributeModel r2 = r4.getAttributeModel()
            r0._attrModel = r2
            int r2 = r4.getContentType()
            switch(r2) {
                case 1: goto L49;
                case 2: goto L46;
                case 3: goto L31;
                case 4: goto L2f;
                default: goto L27;
            }
        L27:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Unexpected content type"
            r1.<init>(r2)
            throw r1
        L2f:
            r0._canHaveMixedContent = r1
        L31:
            org.apache.xmlbeans.SchemaParticle r2 = r4.getContentModel()
            if (r2 == 0) goto L38
            goto L39
        L38:
            r1 = 0
        L39:
            r0._canHaveElements = r1
            boolean r1 = r0._canHaveElements
            if (r1 == 0) goto L4a
            org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl r1 = r3.initVisitor(r2)
            r0._visitor = r1
            goto L4a
        L46:
            r0._hasSimpleContent = r1
            goto L4a
        L49:
        L4a:
            r3.pushState(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.validator.Validator.newState(org.apache.xmlbeans.SchemaType, org.apache.xmlbeans.SchemaField, boolean):void");
    }

    private void popState(ValidatorListener.Event e) {
        if (this._stateStack._visitor != null) {
            poolVisitor(this._stateStack._visitor);
            this._stateStack._visitor = null;
        }
        this._stateStack = this._stateStack._next;
    }

    private void pushState(State state) {
        state._next = this._stateStack;
        this._stateStack = state;
    }

    private void poolVisitor(SchemaTypeVisitorImpl visitor) {
        this._visitorPool.add(visitor);
    }

    private SchemaTypeVisitorImpl initVisitor(SchemaParticle particle) {
        if (this._visitorPool.isEmpty()) {
            return new SchemaTypeVisitorImpl(particle);
        }
        SchemaTypeVisitorImpl result = (SchemaTypeVisitorImpl) this._visitorPool.removeLast();
        result.init(particle);
        return result;
    }

    private State topState() {
        return this._stateStack;
    }

    private String validateSimpleType(SchemaType type, SchemaField field, ValidatorListener.Event event, boolean emptyContent, boolean canApplyDefault) {
        String value;
        String errorCode;
        if (!type.isSimpleType() && type.getContentType() != 2) {
            throw new AssertionError();
        }
        if (type.isNoType()) {
            emitError(event, field.isAttribute() ? XmlErrorCodes.ATTR_LOCALLY_VALID$NO_TYPE : XmlErrorCodes.ELEM_LOCALLY_VALID$NO_TYPE, null, field.getName(), type, 3, null);
            return null;
        }
        if (emptyContent) {
            value = "";
        } else {
            int wsr = type.getWhiteSpaceRule();
            String value2 = wsr == 1 ? event.getText() : event.getText(wsr);
            value = value2;
        }
        if (value.isEmpty() && canApplyDefault && field != null && (field.isDefault() || field.isFixed())) {
            if (XmlQName.type.isAssignableFrom(type)) {
                emitError(event, "Default QName values are unsupported for " + QNameHelper.readable(type) + " - ignoring.", null, null, 2, field.getName(), null, type, null, 3, null);
                return null;
            }
            String defaultValue = XmlWhitespace.collapse(field.getDefaultText(), type.getWhiteSpaceRule());
            if (validateSimpleType(type, defaultValue, event)) {
                return defaultValue;
            }
            return null;
        }
        if (!validateSimpleType(type, value, event)) {
            return null;
        }
        if (field != null && field.isFixed()) {
            String fixedValue = XmlWhitespace.collapse(field.getDefaultText(), type.getWhiteSpaceRule());
            if (!validateSimpleType(type, fixedValue, event)) {
                return null;
            }
            XmlObject val = type.newValue(value);
            XmlObject def = type.newValue(fixedValue);
            if (!val.valueEquals(def)) {
                if (field.isAttribute()) {
                    emitError(event, XmlErrorCodes.ATTR_LOCALLY_VALID$FIXED, new Object[]{value, fixedValue, QNameHelper.pretty(event.getName())}, null, field.getType(), 3, null);
                } else {
                    if (field.getType().getContentType() == 4) {
                        errorCode = XmlErrorCodes.ELEM_LOCALLY_VALID$FIXED_VALID_MIXED_CONTENT;
                    } else if (type.isSimpleType()) {
                        errorCode = XmlErrorCodes.ELEM_LOCALLY_VALID$FIXED_VALID_SIMPLE_TYPE;
                    } else {
                        throw new AssertionError("Element with fixed may not be EMPTY or ELEMENT_ONLY");
                    }
                    emitError(event, errorCode, new Object[]{value, fixedValue}, field.getName(), field.getType(), 3, null);
                }
                return null;
            }
        }
        return value;
    }

    private boolean validateSimpleType(SchemaType type, String value, ValidatorListener.Event event) {
        if (!type.isSimpleType() && type.getContentType() != 2) {
            throw new AssertionError();
        }
        int retState = this._errorState;
        switch (type.getSimpleVariety()) {
            case 1:
                validateAtomicType(type, value, event);
                break;
            case 2:
                validateUnionType(type, value, event);
                break;
            case 3:
                validateListType(type, value, event);
                break;
            default:
                throw new RuntimeException("Unexpected simple variety");
        }
        return retState == this._errorState;
    }

    private void validateAtomicType(SchemaType type, String value, ValidatorListener.Event event) {
        if (type.getSimpleVariety() != 1) {
            throw new AssertionError();
        }
        int errorState = this._errorState;
        this._vc._event = event;
        switch (type.getPrimitiveType().getBuiltinTypeCode()) {
            case 2:
                this._stringValue = value;
                return;
            case 3:
                this._booleanValue = JavaBooleanHolderEx.validateLexical(value, type, this._vc);
                return;
            case 4:
                byte[] v = JavaBase64HolderEx.validateLexical(value, type, this._vc);
                if (v != null) {
                    JavaBase64HolderEx.validateValue(v, type, this._vc);
                }
                this._byteArrayValue = v;
                return;
            case 5:
                byte[] v2 = JavaHexBinaryHolderEx.validateLexical(value, type, this._vc);
                if (v2 != null) {
                    JavaHexBinaryHolderEx.validateValue(v2, type, this._vc);
                }
                this._byteArrayValue = v2;
                return;
            case 6:
                JavaUriHolderEx.validateLexical(value, type, this._vc);
                if (this._strict) {
                    try {
                        XsTypeConverter.lexAnyURI(value);
                    } catch (InvalidLexicalValueException e) {
                        this._vc.invalid(XmlErrorCodes.ANYURI, new Object[]{value});
                    }
                }
                this._stringValue = value;
                return;
            case 7:
                QName n = JavaQNameHolderEx.validateLexical(value, type, this._vc, event);
                if (errorState == this._errorState) {
                    JavaQNameHolderEx.validateValue(n, type, this._vc);
                }
                this._qnameValue = n;
                return;
            case 8:
                QName n2 = JavaNotationHolderEx.validateLexical(value, type, this._vc, event);
                if (errorState == this._errorState) {
                    JavaNotationHolderEx.validateValue(n2, type, this._vc);
                }
                this._qnameValue = n2;
                return;
            case 9:
                float f = JavaFloatHolderEx.validateLexical(value, type, this._vc);
                if (errorState == this._errorState) {
                    JavaFloatHolderEx.validateValue(f, type, this._vc);
                }
                this._floatValue = f;
                return;
            case 10:
                double d = JavaDoubleHolderEx.validateLexical(value, type, this._vc);
                if (errorState == this._errorState) {
                    JavaDoubleHolderEx.validateValue(d, type, this._vc);
                }
                this._doubleValue = d;
                return;
            case 11:
                JavaDecimalHolderEx.validateLexical(value, type, this._vc);
                if (derivedFromInteger(type) && value.lastIndexOf(46) >= 0) {
                    this._vc.invalid("integer", new Object[]{value});
                }
                if (errorState == this._errorState) {
                    this._decimalValue = new BigDecimal(value);
                    JavaDecimalHolderEx.validateValue(this._decimalValue, type, this._vc);
                    return;
                }
                return;
            case 12:
                JavaStringEnumerationHolderEx.validateLexical(value, type, this._vc);
                this._stringValue = value;
                return;
            case 13:
                GDuration d2 = XmlDurationImpl.validateLexical(value, type, this._vc);
                if (d2 != null) {
                    XmlDurationImpl.validateValue(d2, type, this._vc);
                }
                this._gdurationValue = d2;
                return;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                break;
            case 21:
                if (this._strict && value.length() == 6 && value.charAt(4) == '-' && value.charAt(5) == '-') {
                    this._vc.invalid(XmlErrorCodes.DATE, new Object[]{value});
                    break;
                }
                break;
            default:
                throw new RuntimeException("Unexpected primitive type code");
        }
        GDate d3 = XmlDateImpl.validateLexical(value, type, this._vc);
        if (d3 != null) {
            XmlDateImpl.validateValue(d3, type, this._vc);
        }
        this._gdateValue = d3;
    }

    private void validateListType(SchemaType type, String value, ValidatorListener.Event event) {
        SchemaType schemaType;
        Validator validator;
        ValidatorListener.Event event2;
        int i;
        int i2;
        int errorState = this._errorState;
        if (!type.matchPatternFacet(value)) {
            schemaType = type;
            emitError(event, XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.LIST, value, QNameHelper.readable(type)}, null, schemaType, 2000, null);
        } else {
            schemaType = type;
        }
        String[] items = XmlListImpl.split_list(value);
        XmlObject o = schemaType.getFacet(0);
        if (o != null && (i2 = ((SimpleValue) o).getIntValue()) != items.length) {
            emitError(event, XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, new Object[]{value, Integer.valueOf(items.length), Integer.valueOf(i2), QNameHelper.readable(schemaType)}, null, schemaType, 2000, null);
        }
        XmlObject o2 = schemaType.getFacet(1);
        if (o2 != null && (i = ((SimpleValue) o2).getIntValue()) > items.length) {
            emitError(event, XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, new Object[]{value, Integer.valueOf(items.length), Integer.valueOf(i), QNameHelper.readable(schemaType)}, null, schemaType, 2000, null);
        }
        XmlObject o3 = schemaType.getFacet(2);
        if (o3 == null) {
            validator = this;
            event2 = event;
        } else {
            int i3 = ((SimpleValue) o3).getIntValue();
            if (i3 >= items.length) {
                validator = this;
                event2 = event;
            } else {
                validator = this;
                event2 = event;
                validator.emitError(event2, XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, new Object[]{value, Integer.valueOf(items.length), Integer.valueOf(i3), QNameHelper.readable(schemaType)}, null, schemaType, 2000, null);
            }
        }
        SchemaType itemType = type.getListItemType();
        validator._listValue = new ArrayList();
        validator._listTypes = new ArrayList();
        for (String str : items) {
            validator.validateSimpleType(itemType, str, event2);
            validator.addToList(itemType);
        }
        if (errorState == validator._errorState && type.getEnumerationValues() != null) {
            NamespaceContext.push(new NamespaceContext(event2));
            try {
                try {
                    ((SchemaTypeImpl) type).newValidatingValue(value);
                } catch (XmlValueOutOfRangeException e) {
                    validator.emitError(event2, XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.LIST, value, QNameHelper.readable(type)}, null, type, 2000, null);
                }
            } finally {
                NamespaceContext.pop();
            }
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:31:0x00a8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void validateUnionType(org.apache.xmlbeans.SchemaType r18, java.lang.String r19, org.apache.xmlbeans.impl.common.ValidatorListener.Event r20) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.validator.Validator.validateUnionType(org.apache.xmlbeans.SchemaType, java.lang.String, org.apache.xmlbeans.impl.common.ValidatorListener$Event):void");
    }

    private void addToList(SchemaType type) {
        if (type.getSimpleVariety() != 1 && type.getSimpleVariety() != 2) {
            return;
        }
        if (type.getUnionMemberTypes().length > 0 && getUnionType() != null) {
            type = getUnionType();
            this._unionType = null;
        }
        this._listTypes.add(type);
        if (type.getPrimitiveType() == null) {
            this._listValue.add(null);
            return;
        }
        switch (type.getPrimitiveType().getBuiltinTypeCode()) {
            case 2:
            case 6:
                this._listValue.add(this._stringValue);
                return;
            case 3:
                this._listValue.add(this._booleanValue ? Boolean.TRUE : Boolean.FALSE);
                this._booleanValue = false;
                return;
            case 4:
            case 5:
                this._listValue.add(this._byteArrayValue);
                this._byteArrayValue = null;
                return;
            case 7:
            case 8:
                this._listValue.add(this._qnameValue);
                this._qnameValue = null;
                return;
            case 9:
                this._listValue.add(Float.valueOf(this._floatValue));
                this._floatValue = 0.0f;
                return;
            case 10:
                this._listValue.add(Double.valueOf(this._doubleValue));
                this._doubleValue = 0.0d;
                return;
            case 11:
                this._listValue.add(this._decimalValue);
                this._decimalValue = null;
                return;
            case 12:
                this._listValue.add(this._stringValue);
                this._stringValue = null;
                return;
            case 13:
                this._listValue.add(this._gdurationValue);
                this._gdurationValue = null;
                return;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                this._listValue.add(this._gdateValue);
                this._gdateValue = null;
                return;
            default:
                throw new RuntimeException("Unexpected primitive type code");
        }
    }

    private void resetValues() {
        this._localAttribute = null;
        this._wildcardAttribute = null;
        this._stringValue = null;
        this._decimalValue = null;
        this._booleanValue = false;
        this._floatValue = 0.0f;
        this._doubleValue = 0.0d;
        this._qnameValue = null;
        this._gdateValue = null;
        this._gdurationValue = null;
        this._byteArrayValue = null;
        this._listValue = null;
        this._listTypes = null;
        this._unionType = null;
    }

    public SchemaType getCurrentElementSchemaType() {
        State state = topState();
        if (state != null) {
            return state._type;
        }
        return null;
    }

    public SchemaLocalElement getCurrentElement() {
        if (this._localElement != null) {
            return this._localElement;
        }
        if (this._eatContent <= 0 && this._stateStack != null && (this._stateStack._field instanceof SchemaLocalElement)) {
            return (SchemaLocalElement) this._stateStack._field;
        }
        return null;
    }

    public SchemaParticle getCurrentWildcardElement() {
        return this._wildcardElement;
    }

    public SchemaLocalAttribute getCurrentAttribute() {
        return this._localAttribute;
    }

    public SchemaAttributeModel getCurrentWildcardAttribute() {
        return this._wildcardAttribute;
    }

    public String getStringValue() {
        return this._stringValue;
    }

    public BigDecimal getDecimalValue() {
        return this._decimalValue;
    }

    public boolean getBooleanValue() {
        return this._booleanValue;
    }

    public float getFloatValue() {
        return this._floatValue;
    }

    public double getDoubleValue() {
        return this._doubleValue;
    }

    public QName getQNameValue() {
        return this._qnameValue;
    }

    public GDate getGDateValue() {
        return this._gdateValue;
    }

    public GDuration getGDurationValue() {
        return this._gdurationValue;
    }

    public byte[] getByteArrayValue() {
        return this._byteArrayValue;
    }

    public List<Object> getListValue() {
        return this._listValue;
    }

    public List<SchemaType> getListTypes() {
        return this._listTypes;
    }

    public SchemaType getUnionType() {
        return this._unionType;
    }
}
