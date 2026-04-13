package org.apache.xmlbeans.impl.schema;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.regex.ParseException;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.regex.SchemaRegularExpression;
import org.apache.xmlbeans.impl.values.XmlIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlStringImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

/* loaded from: classes11.dex */
public class BuiltinSchemaTypeSystem extends SchemaTypeLoaderBase implements SchemaTypeSystem {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final SchemaType[] EMPTY_SCHEMATYPE_ARRAY = new SchemaType[0];
    private static final SchemaType.Ref[] EMPTY_SCHEMATYPEREF_ARRAY = new SchemaType.Ref[0];
    private static final SchemaGlobalElement[] EMPTY_SCHEMAELEMENT_ARRAY = new SchemaGlobalElement[0];
    private static final SchemaGlobalAttribute[] EMPTY_SCHEMAATTRIBUTE_ARRAY = new SchemaGlobalAttribute[0];
    private static final SchemaModelGroup[] EMPTY_SCHEMAMODELGROUP_ARRAY = new SchemaModelGroup[0];
    private static final SchemaAttributeGroup[] EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY = new SchemaAttributeGroup[0];
    private static final SchemaAnnotation[] EMPTY_SCHEMAANNOTATION_ARRAY = new SchemaAnnotation[0];
    private static BuiltinSchemaTypeSystem _global = new BuiltinSchemaTypeSystem();
    public static final SchemaTypeImpl ST_ANY_TYPE = _global.getBuiltinType(1);
    public static final SchemaTypeImpl ST_ANY_SIMPLE = _global.getBuiltinType(2);
    public static final SchemaTypeImpl ST_BOOLEAN = _global.getBuiltinType(3);
    public static final SchemaTypeImpl ST_BASE_64_BINARY = _global.getBuiltinType(4);
    public static final SchemaTypeImpl ST_HEX_BINARY = _global.getBuiltinType(5);
    public static final SchemaTypeImpl ST_ANY_URI = _global.getBuiltinType(6);
    public static final SchemaTypeImpl ST_QNAME = _global.getBuiltinType(7);
    public static final SchemaTypeImpl ST_NOTATION = _global.getBuiltinType(8);
    public static final SchemaTypeImpl ST_FLOAT = _global.getBuiltinType(9);
    public static final SchemaTypeImpl ST_DOUBLE = _global.getBuiltinType(10);
    public static final SchemaTypeImpl ST_DECIMAL = _global.getBuiltinType(11);
    public static final SchemaTypeImpl ST_STRING = _global.getBuiltinType(12);
    public static final SchemaTypeImpl ST_DURATION = _global.getBuiltinType(13);
    public static final SchemaTypeImpl ST_DATE_TIME = _global.getBuiltinType(14);
    public static final SchemaTypeImpl ST_TIME = _global.getBuiltinType(15);
    public static final SchemaTypeImpl ST_DATE = _global.getBuiltinType(16);
    public static final SchemaTypeImpl ST_G_YEAR_MONTH = _global.getBuiltinType(17);
    public static final SchemaTypeImpl ST_G_YEAR = _global.getBuiltinType(18);
    public static final SchemaTypeImpl ST_G_MONTH_DAY = _global.getBuiltinType(19);
    public static final SchemaTypeImpl ST_G_DAY = _global.getBuiltinType(20);
    public static final SchemaTypeImpl ST_G_MONTH = _global.getBuiltinType(21);
    public static final SchemaTypeImpl ST_INTEGER = _global.getBuiltinType(22);
    public static final SchemaTypeImpl ST_LONG = _global.getBuiltinType(23);
    public static final SchemaTypeImpl ST_INT = _global.getBuiltinType(24);
    public static final SchemaTypeImpl ST_SHORT = _global.getBuiltinType(25);
    public static final SchemaTypeImpl ST_BYTE = _global.getBuiltinType(26);
    public static final SchemaTypeImpl ST_NON_POSITIVE_INTEGER = _global.getBuiltinType(27);
    public static final SchemaTypeImpl ST_NEGATIVE_INTEGER = _global.getBuiltinType(28);
    public static final SchemaTypeImpl ST_NON_NEGATIVE_INTEGER = _global.getBuiltinType(29);
    public static final SchemaTypeImpl ST_POSITIVE_INTEGER = _global.getBuiltinType(30);
    public static final SchemaTypeImpl ST_UNSIGNED_LONG = _global.getBuiltinType(31);
    public static final SchemaTypeImpl ST_UNSIGNED_INT = _global.getBuiltinType(32);
    public static final SchemaTypeImpl ST_UNSIGNED_SHORT = _global.getBuiltinType(33);
    public static final SchemaTypeImpl ST_UNSIGNED_BYTE = _global.getBuiltinType(34);
    public static final SchemaTypeImpl ST_NORMALIZED_STRING = _global.getBuiltinType(35);
    public static final SchemaTypeImpl ST_TOKEN = _global.getBuiltinType(36);
    public static final SchemaTypeImpl ST_NAME = _global.getBuiltinType(37);
    public static final SchemaTypeImpl ST_NCNAME = _global.getBuiltinType(38);
    public static final SchemaTypeImpl ST_LANGUAGE = _global.getBuiltinType(39);
    public static final SchemaTypeImpl ST_ID = _global.getBuiltinType(40);
    public static final SchemaTypeImpl ST_IDREF = _global.getBuiltinType(41);
    public static final SchemaTypeImpl ST_IDREFS = _global.getBuiltinType(42);
    public static final SchemaTypeImpl ST_ENTITY = _global.getBuiltinType(43);
    public static final SchemaTypeImpl ST_ENTITIES = _global.getBuiltinType(44);
    public static final SchemaTypeImpl ST_NMTOKEN = _global.getBuiltinType(45);
    public static final SchemaTypeImpl ST_NMTOKENS = _global.getBuiltinType(46);
    public static final SchemaTypeImpl ST_NO_TYPE = _global.getBuiltinType(0);
    private static final XmlValueRef XMLSTR_PRESERVE = buildString("preserve");
    private static final XmlValueRef XMLSTR_REPLACE = buildString("preserve");
    private static final XmlValueRef XMLSTR_COLLAPSE = buildString("preserve");
    private static final XmlValueRef[] FACETS_NONE = {null, null, null, null, null, null, null, null, null, null, null, null};
    private static final boolean[] FIXED_FACETS_NONE = {false, false, false, false, false, false, false, false, false, false, false, false};
    private static final XmlValueRef[] FACETS_WS_COLLAPSE = {null, null, null, null, null, null, null, null, null, build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_WS_REPLACE = {null, null, null, null, null, null, null, null, null, build_wsstring(2), null, null};
    private static final XmlValueRef[] FACETS_WS_PRESERVE = {null, null, null, null, null, null, null, null, null, build_wsstring(1), null, null};
    private static final XmlValueRef[] FACETS_INTEGER = {null, null, null, null, null, null, null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_LONG = {null, null, null, null, buildInteger(BigInteger.valueOf(Long.MIN_VALUE)), buildInteger(BigInteger.valueOf(Long.MAX_VALUE)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_INT = {null, null, null, null, buildInteger(BigInteger.valueOf(-2147483648L)), buildInteger(BigInteger.valueOf(2147483647L)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_SHORT = {null, null, null, null, buildInteger(BigInteger.valueOf(-32768)), buildInteger(BigInteger.valueOf(32767)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_BYTE = {null, null, null, null, buildInteger(BigInteger.valueOf(-128)), buildInteger(BigInteger.valueOf(127)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_NONNEGATIVE = {null, null, null, null, buildInteger(BigInteger.ZERO), null, null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_POSITIVE = {null, null, null, null, buildInteger(BigInteger.ONE), null, null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_NONPOSITIVE = {null, null, null, null, null, buildInteger(BigInteger.ZERO), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_NEGATIVE = {null, null, null, null, null, buildInteger(BigInteger.ONE.negate()), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_UNSIGNED_LONG = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(new BigInteger("18446744073709551615")), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_UNSIGNED_INT = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(BigInteger.valueOf(4294967295L)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_UNSIGNED_SHORT = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(BigInteger.valueOf(65535)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_UNSIGNED_BYTE = {null, null, null, null, buildInteger(BigInteger.ZERO), buildInteger(BigInteger.valueOf(255)), null, null, buildNnInteger(BigInteger.ZERO), build_wsstring(3), null, null};
    private static final XmlValueRef[] FACETS_BUILTIN_LIST = {null, buildNnInteger(BigInteger.ONE), null, null, null, null, null, null, null, build_wsstring(3), null, null};
    private static final boolean[] FIXED_FACETS_WS = {false, false, false, false, false, false, false, false, false, true, false, false};
    private static final boolean[] FIXED_FACETS_INTEGER = {false, false, false, false, false, false, false, false, true, true, false, false};
    static final XmlValueRef[] FACETS_UNION = FACETS_NONE;
    static final boolean[] FIXED_FACETS_UNION = FIXED_FACETS_NONE;
    static final XmlValueRef[] FACETS_LIST = FACETS_WS_COLLAPSE;
    static final boolean[] FIXED_FACETS_LIST = FIXED_FACETS_WS;
    private Map<QName, SchemaType> _typeMap = new HashMap();
    private SchemaTypeImpl[] _typeArray = new SchemaTypeImpl[47];
    private Map<String, SchemaType> _handlesToObjects = new HashMap();
    private Map<SchemaType, String> _objectsToHandles = new HashMap();
    private Map<String, SchemaType> _typesByClassname = new HashMap();
    private SchemaContainer _container = new SchemaContainer("http://www.w3.org/2001/XMLSchema");

    static {
        for (int i = 0; i <= 46; i++) {
            _global.fillInType(i);
        }
    }

    public static SchemaTypeSystem get() {
        return _global;
    }

    private SchemaTypeImpl getBuiltinType(int btc) {
        return this._typeArray[btc];
    }

    private BuiltinSchemaTypeSystem() {
        this._container.setTypeSystem(this);
        setupBuiltin(1, "anyType", "org.apache.xmlbeans.XmlObject");
        setupBuiltin(2, "anySimpleType", "org.apache.xmlbeans.XmlAnySimpleType");
        setupBuiltin(3, "boolean", "org.apache.xmlbeans.XmlBoolean");
        setupBuiltin(4, XmlErrorCodes.BASE64BINARY, "org.apache.xmlbeans.XmlBase64Binary");
        setupBuiltin(5, XmlErrorCodes.HEXBINARY, "org.apache.xmlbeans.XmlHexBinary");
        setupBuiltin(6, XmlErrorCodes.ANYURI, "org.apache.xmlbeans.XmlAnyURI");
        setupBuiltin(7, XmlErrorCodes.QNAME, "org.apache.xmlbeans.XmlQName");
        setupBuiltin(8, "NOTATION", "org.apache.xmlbeans.XmlNOTATION");
        setupBuiltin(9, "float", "org.apache.xmlbeans.XmlFloat");
        setupBuiltin(10, XmlErrorCodes.DOUBLE, "org.apache.xmlbeans.XmlDouble");
        setupBuiltin(11, XmlErrorCodes.DECIMAL, "org.apache.xmlbeans.XmlDecimal");
        setupBuiltin(12, TypedValues.Custom.S_STRING, "org.apache.xmlbeans.XmlString");
        setupBuiltin(13, "duration", "org.apache.xmlbeans.XmlDuration");
        setupBuiltin(14, "dateTime", "org.apache.xmlbeans.XmlDateTime");
        setupBuiltin(15, "time", "org.apache.xmlbeans.XmlTime");
        setupBuiltin(16, XmlErrorCodes.DATE, "org.apache.xmlbeans.XmlDate");
        setupBuiltin(17, "gYearMonth", "org.apache.xmlbeans.XmlGYearMonth");
        setupBuiltin(18, "gYear", "org.apache.xmlbeans.XmlGYear");
        setupBuiltin(19, "gMonthDay", "org.apache.xmlbeans.XmlGMonthDay");
        setupBuiltin(20, "gDay", "org.apache.xmlbeans.XmlGDay");
        setupBuiltin(21, "gMonth", "org.apache.xmlbeans.XmlGMonth");
        setupBuiltin(22, "integer", "org.apache.xmlbeans.XmlInteger");
        setupBuiltin(23, XmlErrorCodes.LONG, "org.apache.xmlbeans.XmlLong");
        setupBuiltin(24, XmlErrorCodes.INT, "org.apache.xmlbeans.XmlInt");
        setupBuiltin(25, "short", "org.apache.xmlbeans.XmlShort");
        setupBuiltin(26, "byte", "org.apache.xmlbeans.XmlByte");
        setupBuiltin(27, "nonPositiveInteger", "org.apache.xmlbeans.XmlNonPositiveInteger");
        setupBuiltin(28, "negativeInteger", "org.apache.xmlbeans.XmlNegativeInteger");
        setupBuiltin(29, "nonNegativeInteger", "org.apache.xmlbeans.XmlNonNegativeInteger");
        setupBuiltin(30, "positiveInteger", "org.apache.xmlbeans.XmlPositiveInteger");
        setupBuiltin(31, "unsignedLong", "org.apache.xmlbeans.XmlUnsignedLong");
        setupBuiltin(32, "unsignedInt", "org.apache.xmlbeans.XmlUnsignedInt");
        setupBuiltin(33, "unsignedShort", "org.apache.xmlbeans.XmlUnsignedShort");
        setupBuiltin(34, "unsignedByte", "org.apache.xmlbeans.XmlUnsignedByte");
        setupBuiltin(35, "normalizedString", "org.apache.xmlbeans.XmlNormalizedString");
        setupBuiltin(36, "token", "org.apache.xmlbeans.XmlToken");
        setupBuiltin(37, "Name", "org.apache.xmlbeans.XmlName");
        setupBuiltin(38, XmlErrorCodes.NCNAME, "org.apache.xmlbeans.XmlNCName");
        setupBuiltin(39, "language", "org.apache.xmlbeans.XmlLanguage");
        setupBuiltin(40, "ID", "org.apache.xmlbeans.XmlID");
        setupBuiltin(41, "IDREF", "org.apache.xmlbeans.XmlIDREF");
        setupBuiltin(42, "IDREFS", "org.apache.xmlbeans.XmlIDREFS");
        setupBuiltin(43, "ENTITY", "org.apache.xmlbeans.XmlENTITY");
        setupBuiltin(44, "ENTITIES", "org.apache.xmlbeans.XmlENTITIES");
        setupBuiltin(45, XmlErrorCodes.NMTOKEN, "org.apache.xmlbeans.XmlNMTOKEN");
        setupBuiltin(46, "NMTOKENS", "org.apache.xmlbeans.XmlNMTOKENS");
        setupBuiltin(0, null, null);
        this._container.setImmutable();
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public String getName() {
        return "schema.typesystem.builtin";
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public boolean isNamespaceDefined(String namespace) {
        return namespace.equals("http://www.w3.org/2001/XMLSchema");
    }

    @Override // org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase, org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType findType(QName name) {
        return this._typeMap.get(name);
    }

    @Override // org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase, org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType findDocumentType(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase, org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType findAttributeType(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase, org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalElement findElement(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase, org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalAttribute findAttribute(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findTypeRef(QName name) {
        SchemaType type = findType(name);
        if (type == null) {
            return null;
        }
        return type.getRef();
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findDocumentTypeRef(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType.Ref findAttributeTypeRef(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalElement.Ref findElementRef(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalAttribute.Ref findAttributeRef(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaModelGroup.Ref findModelGroupRef(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName name) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType typeForClassname(String classname) {
        return this._typesByClassname.get(classname);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public InputStream getSourceAsStream(String sourceName) {
        return null;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType[] globalTypes() {
        SchemaType[] result = new SchemaType[this._typeArray.length - 1];
        System.arraycopy(this._typeArray, 1, result, 0, result.length);
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType[] documentTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType[] attributeTypes() {
        return EMPTY_SCHEMATYPE_ARRAY;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaGlobalElement[] globalElements() {
        return EMPTY_SCHEMAELEMENT_ARRAY;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaGlobalAttribute[] globalAttributes() {
        return EMPTY_SCHEMAATTRIBUTE_ARRAY;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaModelGroup[] modelGroups() {
        return EMPTY_SCHEMAMODELGROUP_ARRAY;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaAttributeGroup[] attributeGroups() {
        return EMPTY_SCHEMAATTRIBUTEGROUP_ARRAY;
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaAnnotation[] annotations() {
        return EMPTY_SCHEMAANNOTATION_ARRAY;
    }

    public String handleForType(SchemaType type) {
        return this._objectsToHandles.get(type);
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public ClassLoader getClassLoader() {
        return BuiltinSchemaTypeSystem.class.getClassLoader();
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public void saveToDirectory(File classDir) {
        throw new UnsupportedOperationException("The builtin schema type system cannot be saved.");
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public void save(Filer filer) {
        throw new UnsupportedOperationException("The builtin schema type system cannot be saved.");
    }

    private static XmlValueRef build_wsstring(int wsr) {
        switch (wsr) {
            case 1:
                return XMLSTR_PRESERVE;
            case 2:
                return XMLSTR_REPLACE;
            case 3:
                return XMLSTR_COLLAPSE;
            default:
                return null;
        }
    }

    private static XmlValueRef buildNnInteger(BigInteger bigInt) {
        if (bigInt == null || bigInt.signum() < 0) {
            return null;
        }
        try {
            XmlIntegerImpl i = new XmlIntegerImpl();
            i.setBigIntegerValue(bigInt);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    private static XmlValueRef buildInteger(BigInteger bigInt) {
        if (bigInt == null) {
            return null;
        }
        try {
            XmlIntegerImpl i = new XmlIntegerImpl();
            i.setBigIntegerValue(bigInt);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    private static XmlValueRef buildString(String str) {
        if (str == null) {
            return null;
        }
        try {
            XmlStringImpl i = new XmlStringImpl();
            i.setStringValue(str);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    private void setupBuiltin(int btc, String localname, String classname) {
        SchemaTypeImpl result = new SchemaTypeImpl(this._container, true);
        this._container.addGlobalType(result.getRef());
        QName name = localname == null ? null : QNameHelper.forLNS(localname, "http://www.w3.org/2001/XMLSchema");
        String handle = "_BI_" + (localname == null ? "NO_TYPE" : localname);
        result.setName(name);
        result.setBuiltinTypeCode(btc);
        if (classname != null) {
            result.setFullJavaName(classname);
        }
        this._typeArray[btc] = result;
        this._typeMap.put(name, result);
        this._handlesToObjects.put(handle, result);
        this._objectsToHandles.put(result, handle);
        if (classname != null) {
            this._typesByClassname.put(classname, result);
        }
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public void resolve() {
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaType typeForHandle(String handle) {
        return this._handlesToObjects.get(handle);
    }

    @Override // org.apache.xmlbeans.SchemaTypeSystem
    public SchemaComponent resolveHandle(String handle) {
        return this._handlesToObjects.get(handle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r18v3 */
    /* JADX WARN: Type inference failed for: r18v4 */
    /* JADX WARN: Type inference failed for: r18v5 */
    public void fillInType(int btc) {
        SchemaType item;
        int variety;
        int derivationType;
        SchemaType base;
        int wsr;
        boolean[] fixedf;
        int decimalSize;
        XmlValueRef[] facets;
        boolean isFinite;
        boolean isBounded;
        ?? r18;
        String pattern;
        boolean hasPattern;
        boolean z;
        SchemaTypeImpl result = getBuiltinType(btc);
        switch (btc) {
            case 0:
                SchemaType base2 = ST_ANY_TYPE;
                item = null;
                variety = 0;
                derivationType = 1;
                base = base2;
                break;
            case 1:
                item = null;
                variety = 0;
                derivationType = 1;
                base = null;
                break;
            case 2:
                SchemaType base3 = ST_ANY_TYPE;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base3;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                SchemaType base4 = ST_ANY_SIMPLE;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base4;
                break;
            case 22:
                SchemaType base5 = ST_DECIMAL;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base5;
                break;
            case 23:
                SchemaType base6 = ST_INTEGER;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base6;
                break;
            case 24:
                SchemaType base7 = ST_LONG;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base7;
                break;
            case 25:
                SchemaType base8 = ST_INT;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base8;
                break;
            case 26:
                SchemaType base9 = ST_SHORT;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base9;
                break;
            case 27:
                SchemaType base10 = ST_INTEGER;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base10;
                break;
            case 28:
                SchemaType base11 = ST_NON_POSITIVE_INTEGER;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base11;
                break;
            case 29:
                SchemaType base12 = ST_INTEGER;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base12;
                break;
            case 30:
                SchemaType base13 = ST_NON_NEGATIVE_INTEGER;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base13;
                break;
            case 31:
                SchemaType base14 = ST_NON_NEGATIVE_INTEGER;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base14;
                break;
            case 32:
                SchemaType base15 = ST_UNSIGNED_LONG;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base15;
                break;
            case 33:
                SchemaType base16 = ST_UNSIGNED_INT;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base16;
                break;
            case 34:
                SchemaType base17 = ST_UNSIGNED_SHORT;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base17;
                break;
            case 35:
                SchemaType base18 = ST_STRING;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base18;
                break;
            case 36:
                SchemaType base19 = ST_NORMALIZED_STRING;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base19;
                break;
            case 37:
                SchemaType base20 = ST_TOKEN;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base20;
                break;
            case 38:
                SchemaType base21 = ST_NAME;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base21;
                break;
            case 39:
            case 45:
                SchemaType base22 = ST_TOKEN;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base22;
                break;
            case 40:
            case 41:
            case 43:
                SchemaType base23 = ST_NCNAME;
                item = null;
                variety = 1;
                derivationType = 1;
                base = base23;
                break;
            case 42:
            case 44:
            case 46:
                SchemaType base24 = ST_ANY_SIMPLE;
                if (btc != 42) {
                    if (btc == 44) {
                        SchemaType item2 = ST_ENTITY;
                        item = item2;
                        variety = 3;
                        derivationType = 1;
                        base = base24;
                        break;
                    } else {
                        SchemaType item3 = ST_NMTOKEN;
                        item = item3;
                        variety = 3;
                        derivationType = 1;
                        base = base24;
                        break;
                    }
                } else {
                    SchemaType item4 = ST_IDREF;
                    item = item4;
                    variety = 3;
                    derivationType = 1;
                    base = base24;
                    break;
                }
            default:
                throw new AssertionError();
        }
        result.setDerivationType(derivationType);
        result.setSimpleTypeVariety(variety);
        if (variety != 0) {
            result.setSimpleType(true);
        } else if (btc != 1 && btc != 0) {
            throw new AssertionError();
        }
        result.setBaseTypeRef(base == null ? null : base.getRef());
        result.setBaseDepth(base == null ? 0 : ((SchemaTypeImpl) base).getBaseDepth() + 1);
        result.setListItemTypeRef(item == null ? null : item.getRef());
        if (btc >= 2 && btc <= 21) {
            result.setPrimitiveTypeRef(result.getRef());
        } else if (variety == 1) {
            if (base == null) {
                throw new IllegalStateException("Base was null for " + btc);
            }
            if (base.getPrimitiveType() == null) {
                throw new IllegalStateException("Base.gpt was null for " + btc);
            }
            result.setPrimitiveTypeRef(base.getPrimitiveType().getRef());
        }
        switch (btc) {
            case 0:
            case 1:
            case 2:
                XmlValueRef[] facets2 = FACETS_NONE;
                boolean[] fixedf2 = FIXED_FACETS_NONE;
                wsr = 0;
                fixedf = fixedf2;
                decimalSize = 0;
                facets = facets2;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                XmlValueRef[] facets3 = FACETS_WS_COLLAPSE;
                boolean[] fixedf3 = FIXED_FACETS_WS;
                wsr = 3;
                fixedf = fixedf3;
                decimalSize = 0;
                facets = facets3;
                break;
            case 11:
                XmlValueRef[] facets4 = FACETS_WS_COLLAPSE;
                boolean[] fixedf4 = FIXED_FACETS_WS;
                wsr = 3;
                fixedf = fixedf4;
                decimalSize = 1000001;
                facets = facets4;
                break;
            case 12:
                XmlValueRef[] facets5 = FACETS_WS_PRESERVE;
                boolean[] fixedf5 = FIXED_FACETS_NONE;
                wsr = 1;
                fixedf = fixedf5;
                decimalSize = 0;
                facets = facets5;
                break;
            case 22:
                XmlValueRef[] facets6 = FACETS_INTEGER;
                boolean[] fixedf6 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf6;
                decimalSize = 1000000;
                facets = facets6;
                break;
            case 23:
                XmlValueRef[] facets7 = FACETS_LONG;
                boolean[] fixedf7 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf7;
                decimalSize = 64;
                facets = facets7;
                break;
            case 24:
                XmlValueRef[] facets8 = FACETS_INT;
                boolean[] fixedf8 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf8;
                decimalSize = 32;
                facets = facets8;
                break;
            case 25:
                XmlValueRef[] facets9 = FACETS_SHORT;
                boolean[] fixedf9 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf9;
                decimalSize = 16;
                facets = facets9;
                break;
            case 26:
                XmlValueRef[] facets10 = FACETS_BYTE;
                boolean[] fixedf10 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf10;
                decimalSize = 8;
                facets = facets10;
                break;
            case 27:
                XmlValueRef[] facets11 = FACETS_NONPOSITIVE;
                boolean[] fixedf11 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf11;
                decimalSize = 1000000;
                facets = facets11;
                break;
            case 28:
                XmlValueRef[] facets12 = FACETS_NEGATIVE;
                boolean[] fixedf12 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf12;
                decimalSize = 1000000;
                facets = facets12;
                break;
            case 29:
                XmlValueRef[] facets13 = FACETS_NONNEGATIVE;
                boolean[] fixedf13 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf13;
                decimalSize = 1000000;
                facets = facets13;
                break;
            case 30:
                XmlValueRef[] facets14 = FACETS_POSITIVE;
                boolean[] fixedf14 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf14;
                decimalSize = 1000000;
                facets = facets14;
                break;
            case 31:
                XmlValueRef[] facets15 = FACETS_UNSIGNED_LONG;
                boolean[] fixedf15 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf15;
                decimalSize = 1000000;
                facets = facets15;
                break;
            case 32:
                XmlValueRef[] facets16 = FACETS_UNSIGNED_INT;
                boolean[] fixedf16 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf16;
                decimalSize = 64;
                facets = facets16;
                break;
            case 33:
                XmlValueRef[] facets17 = FACETS_UNSIGNED_SHORT;
                boolean[] fixedf17 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf17;
                decimalSize = 32;
                facets = facets17;
                break;
            case 34:
                XmlValueRef[] facets18 = FACETS_UNSIGNED_BYTE;
                boolean[] fixedf18 = FIXED_FACETS_INTEGER;
                wsr = 3;
                fixedf = fixedf18;
                decimalSize = 16;
                facets = facets18;
                break;
            case 35:
                XmlValueRef[] facets19 = FACETS_WS_REPLACE;
                boolean[] fixedf19 = FIXED_FACETS_NONE;
                wsr = 2;
                fixedf = fixedf19;
                decimalSize = 0;
                facets = facets19;
                break;
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 45:
                XmlValueRef[] facets20 = FACETS_WS_COLLAPSE;
                boolean[] fixedf20 = FIXED_FACETS_NONE;
                wsr = 3;
                fixedf = fixedf20;
                decimalSize = 0;
                facets = facets20;
                break;
            case 44:
            case 46:
                XmlValueRef[] facets21 = FACETS_BUILTIN_LIST;
                boolean[] fixedf21 = FIXED_FACETS_NONE;
                wsr = 0;
                fixedf = fixedf21;
                decimalSize = 0;
                facets = facets21;
                break;
            default:
                throw new AssertionError();
        }
        int ordered = 0;
        boolean isNumeric = false;
        switch (btc) {
            case 0:
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 12:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
                isFinite = false;
                isBounded = false;
                r18 = 0;
                break;
            case 3:
                isFinite = true;
                isBounded = false;
                r18 = 0;
                break;
            case 9:
            case 10:
            case 11:
            case 22:
                isNumeric = true;
                ordered = 2;
                isFinite = false;
                isBounded = false;
                r18 = 0;
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                ordered = 1;
                isFinite = false;
                isBounded = false;
                r18 = 0;
                break;
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
                isNumeric = true;
                ordered = 2;
                isFinite = true;
                isBounded = true;
                r18 = 0;
                break;
            default:
                throw new AssertionError();
        }
        result.setBasicFacets(facets, fixedf);
        result.setWhiteSpaceRule(wsr);
        result.setOrdered(ordered);
        result.setBounded(isBounded);
        result.setNumeric(isNumeric);
        result.setFinite(isFinite);
        result.setDecimalSize(decimalSize);
        result.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
        switch (btc) {
            case 37:
                pattern = "\\i\\c*";
                hasPattern = true;
                break;
            case 38:
                pattern = "[\\i-[:]][\\c-[:]]*";
                hasPattern = true;
                break;
            case 39:
                pattern = "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*";
                hasPattern = true;
                break;
            case 40:
            case 41:
            case 43:
                pattern = null;
                hasPattern = true;
                break;
            case 42:
            case 44:
            default:
                pattern = null;
                hasPattern = false;
                break;
            case 45:
                pattern = "\\c+";
                hasPattern = true;
                break;
        }
        if (pattern != null) {
            try {
                RegularExpression p = SchemaRegularExpression.forPattern(pattern);
                RegularExpression[] regularExpressionArr = new RegularExpression[1];
                regularExpressionArr[r18] = p;
                result.setPatterns(regularExpressionArr);
            } catch (ParseException e) {
                throw new AssertionError();
            }
        }
        result.setPatternFacet(hasPattern);
        if (btc == 1) {
            SchemaParticleImpl contentModel = new SchemaParticleImpl();
            contentModel.setParticleType(5);
            contentModel.setWildcardSet(QNameSet.ALL);
            contentModel.setWildcardProcess(2);
            contentModel.setMinOccurs(BigInteger.ZERO);
            contentModel.setMaxOccurs(null);
            contentModel.setTransitionRules(QNameSet.ALL, true);
            contentModel.setTransitionNotes(QNameSet.ALL, true);
            SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl();
            attrModel.setWildcardProcess(2);
            attrModel.setWildcardSet(QNameSet.ALL);
            result.setComplexTypeVariety(4);
            result.setContentModel(contentModel, attrModel, Collections.EMPTY_MAP, Collections.EMPTY_MAP, false);
            result.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
            result.setWildcardSummary(QNameSet.ALL, true, QNameSet.ALL, true);
            z = r18;
        } else if (btc != 0) {
            z = r18;
        } else {
            SchemaAttributeModelImpl attrModel2 = new SchemaAttributeModelImpl();
            result.setComplexTypeVariety(1);
            result.setContentModel(null, attrModel2, Collections.EMPTY_MAP, Collections.EMPTY_MAP, false);
            result.setAnonymousTypeRefs(EMPTY_SCHEMATYPEREF_ARRAY);
            z = r18;
            result.setWildcardSummary(QNameSet.EMPTY, z, QNameSet.EMPTY, z);
        }
        result.setOrderSensitive(z);
    }

    public static SchemaType getNoType() {
        return ST_NO_TYPE;
    }
}
