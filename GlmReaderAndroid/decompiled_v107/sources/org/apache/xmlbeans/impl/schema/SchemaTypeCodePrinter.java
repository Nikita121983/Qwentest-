package org.apache.xmlbeans.impl.schema;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.logging.log4j.message.StructuredDataId;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.SchemaCodePrinter;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.apache.xmlbeans.impl.repackage.Repackager;

/* loaded from: classes11.dex */
public final class SchemaTypeCodePrinter implements SchemaCodePrinter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ADD_NEW_VALUE = 3;
    private static final int INDENT_INCREMENT = 4;
    static final String INDEX_CLASSNAME = "TypeSystemHolder";
    private static final String MAX_SPACES = "                                        ";
    private static final int NOTHING = 1;
    private static final int THROW_EXCEPTION = 4;
    private int _indent = 0;
    private Writer _writer;
    private XmlOptions opt;

    void indent() {
        this._indent += 4;
    }

    void outdent() {
        this._indent -= 4;
    }

    void emit(String s, XmlOptions.BeanMethod method) throws IOException {
        Set<XmlOptions.BeanMethod> partMet = this.opt == null ? null : this.opt.getCompilePartialMethod();
        if (partMet == null || partMet.contains(method)) {
            emit(s);
        }
    }

    void emit(String s) throws IOException {
        if (!s.trim().isEmpty()) {
            int indent = this._indent;
            if (indent > MAX_SPACES.length() / 2) {
                indent = (MAX_SPACES.length() / 4) + (indent / 2);
            }
            if (indent > MAX_SPACES.length()) {
                indent = MAX_SPACES.length();
            }
            this._writer.write(MAX_SPACES.substring(0, indent));
        }
        try {
            this._writer.write(s);
        } catch (CharacterCodingException e) {
            this._writer.write(makeSafe(s));
        }
        this._writer.write(System.lineSeparator());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x003e. Please report as an issue. */
    private static String makeSafe(String s) {
        Charset charset = Charset.defaultCharset();
        CharsetEncoder cEncoder = charset.newEncoder();
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < s.length() && cEncoder.canEncode(s.charAt(i))) {
            i++;
        }
        while (i < s.length()) {
            char c = s.charAt(i);
            if (cEncoder.canEncode(c)) {
                result.append(c);
            } else {
                String hexValue = Integer.toHexString(c);
                switch (hexValue.length()) {
                    case 1:
                        result.append("\\u000").append(hexValue);
                        break;
                    case 2:
                        result.append("\\u00").append(hexValue);
                        break;
                    case 3:
                        result.append("\\u0").append(hexValue);
                        break;
                    case 4:
                        result.append("\\u").append(hexValue);
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
            i++;
        }
        return result.toString();
    }

    @Override // org.apache.xmlbeans.SchemaCodePrinter
    public void printType(Writer writer, SchemaType sType, XmlOptions opt) throws IOException {
        this.opt = opt;
        this._writer = writer;
        printTopComment(sType);
        printPackage(sType, true);
        emit("");
        emit("import " + ElementFactory.class.getName() + ";");
        emit("import " + AbstractDocumentFactory.class.getName() + ";");
        emit("import " + DocumentFactory.class.getName() + ";");
        emit("import " + SimpleTypeFactory.class.getName() + ";");
        emit("");
        printInnerType(sType, sType.getTypeSystem());
        this._writer.flush();
    }

    @Override // org.apache.xmlbeans.SchemaCodePrinter
    public void printTypeImpl(Writer writer, SchemaType sType, XmlOptions opt) throws IOException {
        this.opt = opt;
        this._writer = writer;
        printTopComment(sType);
        printPackage(sType, false);
        emit("");
        emit("import javax.xml.namespace.QName;");
        emit("import org.apache.xmlbeans.QNameSet;");
        emit("import org.apache.xmlbeans.XmlObject;");
        emit("");
        printInnerTypeImpl(sType, sType.getTypeSystem(), false);
    }

    String findJavaType(SchemaType sType) {
        while (sType.getFullJavaName() == null) {
            sType = sType.getBaseType();
        }
        return sType.getFullJavaName();
    }

    static String prettyQName(QName qname) {
        if (qname == null) {
            return "";
        }
        String result = qname.getLocalPart();
        if (qname.getNamespaceURI() != null) {
            return result + "(@" + qname.getNamespaceURI() + ")";
        }
        return result;
    }

    void printInnerTypeJavaDoc(SchemaType sType) throws IOException {
        QName name = sType.getName();
        if (name == null) {
            if (sType.isDocumentType()) {
                name = sType.getDocumentElementName();
            } else if (sType.isAttributeType()) {
                name = sType.getAttributeTypeAttributeName();
            } else if (sType.getContainerField() != null) {
                name = sType.getContainerField().getName();
            }
        }
        emit("/**");
        if (this.opt.isCompileAnnotationAsJavadoc() && sType.getDocumentation() != null && sType.getDocumentation().length() > 0) {
            emit(" *");
            printJavaDocBody(sType.getDocumentation());
            emit(" *");
        }
        if (sType.isDocumentType()) {
            emit(" * A document containing one " + prettyQName(name) + " element.");
        } else if (sType.isAttributeType()) {
            emit(" * A document containing one " + prettyQName(name) + " attribute.");
        } else if (name != null) {
            emit(" * An XML " + prettyQName(name) + ".");
        } else {
            emit(" * An anonymous inner XML type.");
        }
        emit(" *");
        switch (sType.getSimpleVariety()) {
            case 0:
                emit(" * This is a complex type.");
                break;
            case 1:
                emit(" * This is an atomic type that is a restriction of " + getFullJavaName(sType) + ".");
                break;
            case 2:
                emit(" * This is a union type. Instances are of one of the following types:");
                SchemaType[] members = sType.getUnionConstituentTypes();
                for (SchemaType member : members) {
                    emit(" *     " + member.getFullJavaName());
                }
                break;
            case 3:
                emit(" * This is a list type whose items are " + sType.getListItemType().getFullJavaName() + ".");
                break;
        }
        emit(" */");
    }

    private String getFullJavaName(SchemaType sType) {
        SchemaTypeImpl sTypeI = (SchemaTypeImpl) sType;
        String ret = sTypeI.getFullJavaName();
        while (sTypeI != null && sTypeI.isRedefinition()) {
            ret = sTypeI.getFullJavaName();
            sTypeI = (SchemaTypeImpl) sTypeI.getBaseType();
        }
        return ret;
    }

    private String getUserTypeStaticHandlerMethod(boolean encode, SchemaTypeImpl stype) {
        String unqualifiedName;
        String unqualifiedName2 = stype.getName().getLocalPart();
        if (unqualifiedName2.length() < 2) {
            unqualifiedName = unqualifiedName2.toUpperCase(Locale.ROOT);
        } else {
            unqualifiedName = unqualifiedName2.substring(0, 1).toUpperCase(Locale.ROOT) + unqualifiedName2.substring(1);
        }
        if (encode) {
            return stype.getUserTypeHandlerName() + ".encode" + unqualifiedName;
        }
        return stype.getUserTypeHandlerName() + ".decode" + unqualifiedName;
    }

    public static String indexClassForSystem(SchemaTypeSystem system) {
        String name = system.getName();
        return name + "." + INDEX_CLASSNAME;
    }

    void printStaticTypeDeclaration(SchemaType sType, SchemaTypeSystem system) throws IOException {
        Class<?> factoryClass;
        if (sType.isAnonymousType() && !sType.isDocumentType() && !sType.isAttributeType()) {
            factoryClass = ElementFactory.class;
        } else if (sType.isSimpleType()) {
            factoryClass = SimpleTypeFactory.class;
        } else if (sType.isAbstract()) {
            factoryClass = AbstractDocumentFactory.class;
        } else {
            factoryClass = DocumentFactory.class;
        }
        String factoryName = factoryClass.getSimpleName();
        String fullName = sType.getFullJavaName().replace('$', '.');
        String sysName = sType.getTypeSystem().getName();
        emit(factoryName + "<" + fullName + "> Factory = new " + factoryName + "<>(" + sysName + ".TypeSystemHolder.typeSystem, \"" + ((SchemaTypeSystemImpl) system).handleForType(sType) + "\");");
        emit("org.apache.xmlbeans.SchemaType type = Factory.getType();");
        emit("");
    }

    void printInnerType(SchemaType sType, SchemaTypeSystem system) throws IOException {
        emit("");
        printInnerTypeJavaDoc(sType);
        startInterface(sType);
        printStaticTypeDeclaration(sType, system);
        if (sType.isSimpleType()) {
            if (sType.hasStringEnumValues()) {
                printStringEnumeration(sType);
            }
        } else {
            if (sType.getContentType() == 2 && sType.hasStringEnumValues()) {
                printStringEnumeration(sType);
            }
            SchemaProperty[] props = getDerivedProperties(sType);
            for (SchemaProperty prop : props) {
                printPropertyGetters(prop);
                if (!prop.isReadOnly()) {
                    printPropertySetters(prop);
                }
            }
        }
        printNestedInnerTypes(sType, system);
        endBlock();
    }

    void printNestedInnerTypes(SchemaType sType, SchemaTypeSystem system) throws IOException {
        boolean redefinition = sType.getName() != null && sType.getName().equals(sType.getBaseType().getName());
        while (sType != null) {
            SchemaType[] anonTypes = sType.getAnonymousTypes();
            for (SchemaType anonType : anonTypes) {
                if (anonType.isSkippedAnonymousType()) {
                    printNestedInnerTypes(anonType, system);
                } else {
                    printInnerType(anonType, system);
                }
            }
            if (redefinition) {
                if (sType.getDerivationType() == 2 || sType.isSimpleType()) {
                    sType = sType.getBaseType();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    void printTopComment(SchemaType sType) throws IOException {
        QName thename;
        emit("/*");
        if (sType.getName() != null) {
            emit(" * XML Type:  " + sType.getName().getLocalPart());
            emit(" * Namespace: " + sType.getName().getNamespaceURI());
        } else {
            if (sType.isDocumentType()) {
                thename = sType.getDocumentElementName();
                emit(" * An XML document type.");
            } else if (sType.isAttributeType()) {
                thename = sType.getAttributeTypeAttributeName();
                emit(" * An XML attribute type.");
            } else {
                throw new AssertionError();
            }
            if (thename == null) {
                throw new AssertionError();
            }
            emit(" * Localname: " + thename.getLocalPart());
            emit(" * Namespace: " + thename.getNamespaceURI());
        }
        emit(" * Java type: " + sType.getFullJavaName());
        emit(" *");
        emit(" * Automatically generated - do not modify.");
        emit(" */");
    }

    void printPackage(SchemaType sType, boolean intf) throws IOException {
        String fqjn;
        if (intf) {
            fqjn = sType.getFullJavaName();
        } else {
            fqjn = sType.getFullJavaImplName();
        }
        int lastdot = fqjn.lastIndexOf(46);
        if (lastdot < 0) {
            return;
        }
        String pkg = fqjn.substring(0, lastdot);
        emit("package " + pkg + ";");
    }

    void startInterface(SchemaType sType) throws IOException {
        String shortName = sType.getShortJavaName();
        String baseInterface = findJavaType(sType.getBaseType());
        emit("public interface " + shortName + " extends " + baseInterface + getExtensionInterfaces(sType) + " {");
        indent();
        emitSpecializedAccessors(sType);
    }

    private static String getExtensionInterfaces(SchemaType sType) {
        SchemaTypeImpl sImpl = getImpl(sType);
        if (sImpl == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        InterfaceExtension[] exts = sImpl.getInterfaceExtensions();
        if (exts != null) {
            for (InterfaceExtension ext : exts) {
                sb.append(", ").append(ext.getInterface());
            }
        }
        return sb.toString();
    }

    private static SchemaTypeImpl getImpl(SchemaType sType) {
        if (sType instanceof SchemaTypeImpl) {
            return (SchemaTypeImpl) sType;
        }
        return null;
    }

    private void emitSpecializedAccessors(SchemaType sType) throws IOException {
        if (sType.getSimpleVariety() == 1 && sType.getPrimitiveType().getBuiltinTypeCode() == 11) {
            int bits = sType.getDecimalSize();
            int parentBits = sType.getBaseType().getDecimalSize();
            if (bits != parentBits || sType.getBaseType().getFullJavaName() == null) {
                switch (bits) {
                    case 8:
                        emit("byte getByteValue();", XmlOptions.BeanMethod.GET);
                        emit("void setByteValue(byte b);", XmlOptions.BeanMethod.SET);
                        break;
                    case 16:
                        emit("short getShortValue();", XmlOptions.BeanMethod.GET);
                        emit("void setShortValue(short s);", XmlOptions.BeanMethod.SET);
                        break;
                    case 32:
                        emit("int getIntValue();", XmlOptions.BeanMethod.GET);
                        emit("void setIntValue(int i);", XmlOptions.BeanMethod.SET);
                        break;
                    case 64:
                        emit("long getLongValue();", XmlOptions.BeanMethod.GET);
                        emit("void setLongValue(long l);", XmlOptions.BeanMethod.SET);
                        break;
                    case 1000000:
                        emit("java.math.BigInteger getBigIntegerValue();", XmlOptions.BeanMethod.GET);
                        emit("void setBigIntegerValue(java.math.BigInteger bi);", XmlOptions.BeanMethod.SET);
                        break;
                }
            }
        }
        if (sType.getSimpleVariety() == 2) {
            emit("java.lang.Object getObjectValue();", XmlOptions.BeanMethod.GET);
            emit("void setObjectValue(java.lang.Object val);", XmlOptions.BeanMethod.SET);
            emit("org.apache.xmlbeans.SchemaType instanceType();", XmlOptions.BeanMethod.INSTANCE_TYPE);
            SchemaType ctype = sType.getUnionCommonBaseType();
            if (ctype != null && ctype.getSimpleVariety() != 2) {
                emitSpecializedAccessors(ctype);
            }
        }
        if (sType.getSimpleVariety() == 3) {
            emit("java.util.List getListValue();", XmlOptions.BeanMethod.GET_LIST);
            emit("java.util.List xgetListValue();", XmlOptions.BeanMethod.XGET_LIST);
            emit("void setListValue(java.util.List<?> list);", XmlOptions.BeanMethod.SET_LIST);
        }
    }

    void startBlock() {
        indent();
    }

    void endBlock() throws IOException {
        outdent();
        emit(VectorFormat.DEFAULT_SUFFIX);
    }

    void printJavaDoc(String sentence, XmlOptions.BeanMethod method) throws IOException {
        Set<XmlOptions.BeanMethod> partMet = this.opt == null ? null : this.opt.getCompilePartialMethod();
        if (partMet == null || partMet.contains(method)) {
            printJavaDoc(sentence);
        }
    }

    void printJavaDoc(String sentence) throws IOException {
        emit("");
        emit("/**");
        emit(" * " + sentence);
        emit(" */");
    }

    void printJavaDocParagraph(String s) throws IOException {
        emit("");
        emit("/**");
        printJavaDocBody(s);
        emit(" */");
    }

    void printJavaDocBody(String doc) throws IOException {
        String docClean = doc.trim().replace("\t", "").replace("*/", "* /");
        for (String s : docClean.split("[\\n\\r]+")) {
            emit(" * " + s);
        }
    }

    public static String javaStringEscape(String str) {
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case '\n':
                case '\r':
                case '\"':
                case '\\':
                    StringBuilder sb = new StringBuilder();
                    for (int i2 = 0; i2 < str.length(); i2++) {
                        char ch = str.charAt(i2);
                        switch (ch) {
                            case '\n':
                                sb.append("\\n");
                                break;
                            case '\r':
                                sb.append("\\r");
                                break;
                            case '\"':
                                sb.append("\\\"");
                                break;
                            case '\\':
                                sb.append("\\\\");
                                break;
                            default:
                                sb.append(ch);
                                break;
                        }
                    }
                    return sb.toString();
                default:
            }
        }
        return str;
    }

    void printStringEnumeration(SchemaType sType) throws IOException {
        SchemaType baseEnumType;
        boolean hasBase;
        SchemaType baseEnumType2 = sType.getBaseEnumType();
        String baseEnumClass = baseEnumType2.getFullJavaName();
        boolean hasBase2 = hasBase(sType);
        if (!hasBase2) {
            emit("");
            emit("org.apache.xmlbeans.StringEnumAbstractBase getEnumValue();", XmlOptions.BeanMethod.GET);
            emit("void setEnumValue(org.apache.xmlbeans.StringEnumAbstractBase e);", XmlOptions.BeanMethod.SET);
        }
        emit("");
        SchemaStringEnumEntry[] entries = sType.getStringEnumEntries();
        HashSet<String> seenValues = new HashSet<>();
        HashSet<String> repeatValues = new HashSet<>();
        int length = entries.length;
        int i = 0;
        while (i < length) {
            SchemaStringEnumEntry entry = entries[i];
            String enumValue = entry.getString();
            if (seenValues.contains(enumValue)) {
                repeatValues.add(enumValue);
                baseEnumType = baseEnumType2;
                hasBase = hasBase2;
            } else {
                seenValues.add(enumValue);
                String constName = entry.getEnumName();
                if (hasBase2) {
                    baseEnumType = baseEnumType2;
                    hasBase = hasBase2;
                    emit(baseEnumClass + ".Enum " + constName + " = " + baseEnumClass + "." + constName + ";");
                } else {
                    baseEnumType = baseEnumType2;
                    hasBase = hasBase2;
                    emit("Enum " + constName + " = Enum.forString(\"" + javaStringEscape(enumValue) + "\");");
                }
            }
            i++;
            baseEnumType2 = baseEnumType;
            hasBase2 = hasBase;
        }
        boolean hasBase3 = hasBase2;
        emit("");
        for (SchemaStringEnumEntry entry2 : entries) {
            if (!repeatValues.contains(entry2.getString())) {
                String constName2 = "INT_" + entry2.getEnumName();
                if (hasBase3) {
                    emit("int " + constName2 + " = " + baseEnumClass + "." + constName2 + ";");
                } else {
                    emit("int " + constName2 + " = Enum." + constName2 + ";");
                }
            }
        }
        if (!hasBase3) {
            emit("");
            emit("/**");
            emit(" * Enumeration value class for " + baseEnumClass + ".");
            emit(" * These enum values can be used as follows:");
            emit(" * <pre>");
            emit(" * enum.toString(); // returns the string value of the enum");
            emit(" * enum.intValue(); // returns an int value, useful for switches");
            if (entries.length > 0) {
                emit(" * // e.g., case Enum.INT_" + entries[0].getEnumName());
            }
            emit(" * Enum.forString(s); // returns the enum value for a string");
            emit(" * Enum.forInt(i); // returns the enum value for an int");
            emit(" * </pre>");
            emit(" * Enumeration objects are immutable singleton objects that");
            emit(" * can be compared using == object equality. They have no");
            emit(" * public constructor. See the constants defined within this");
            emit(" * class for all the valid values.");
            emit(" */");
            emit("final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase {");
            indent();
            emit("/**");
            emit(" * Returns the enum value for a string, or null if none.");
            emit(" */");
            emit("public static Enum forString(java.lang.String s) {");
            emit("    return (Enum)table.forString(s);");
            emit(VectorFormat.DEFAULT_SUFFIX);
            emit("");
            emit("/**");
            emit(" * Returns the enum value corresponding to an int, or null if none.");
            emit(" */");
            emit("public static Enum forInt(int i) {");
            emit("    return (Enum)table.forInt(i);");
            emit(VectorFormat.DEFAULT_SUFFIX);
            emit("");
            emit("private Enum(java.lang.String s, int i) {");
            emit("    super(s, i);");
            emit(VectorFormat.DEFAULT_SUFFIX);
            emit("");
            int i2 = 0;
            for (int length2 = entries.length; i2 < length2; length2 = length2) {
                SchemaStringEnumEntry entry3 = entries[i2];
                String constName3 = "INT_" + entry3.getEnumName();
                int intValue = entry3.getIntValue();
                emit("static final int " + constName3 + " = " + intValue + ";");
                i2++;
            }
            emit("");
            emit("public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =");
            emit("    new org.apache.xmlbeans.StringEnumAbstractBase.Table(new Enum[] {");
            indent();
            for (SchemaStringEnumEntry entry4 : entries) {
                String enumValue2 = entry4.getString();
                String constName4 = "INT_" + entry4.getEnumName();
                emit("new Enum(\"" + javaStringEscape(enumValue2) + "\", " + constName4 + "),");
            }
            outdent();
            emit("});");
            emit("private static final long serialVersionUID = 1L;");
            emit("private java.lang.Object readResolve() {");
            emit("    return forInt(intValue());");
            emit(VectorFormat.DEFAULT_SUFFIX);
            outdent();
            emit(VectorFormat.DEFAULT_SUFFIX);
        }
    }

    private boolean hasBase(SchemaType sType) {
        SchemaType baseEnumType = sType.getBaseEnumType();
        return (baseEnumType.isAnonymousType() && baseEnumType.isSkippedAnonymousType()) ? sType.getContentBasedOnType() != null ? sType.getContentBasedOnType().getBaseType() != baseEnumType : sType.getBaseType() != baseEnumType : baseEnumType != sType;
    }

    String xmlTypeForProperty(SchemaProperty sProp) {
        SchemaType sType = sProp.javaBasedOnType();
        return findJavaType(sType).replace('$', '.');
    }

    static boolean xmlTypeForPropertyIsUnion(SchemaProperty sProp) {
        SchemaType sType = sProp.javaBasedOnType();
        return sType.isSimpleType() && sType.getSimpleVariety() == 2;
    }

    static boolean isJavaPrimitive(int javaType) {
        return javaType >= 1 && javaType <= 7;
    }

    static String javaWrappedType(int javaType) {
        switch (javaType) {
            case 1:
                return "java.lang.Boolean";
            case 2:
                return "java.lang.Float";
            case 3:
                return "java.lang.Double";
            case 4:
                return "java.lang.Byte";
            case 5:
                return "java.lang.Short";
            case 6:
                return "java.lang.Integer";
            case 7:
                return "java.lang.Long";
            default:
                throw new AssertionError();
        }
    }

    String javaTypeForProperty(SchemaProperty sProp) {
        if (sProp.getJavaTypeCode() == 0) {
            return findJavaType(sProp.javaBasedOnType()).replace('$', '.');
        }
        if (sProp.getJavaTypeCode() == 20) {
            return ((SchemaTypeImpl) sProp.getType()).getUserTypeName();
        }
        switch (sProp.getJavaTypeCode()) {
            case 1:
                return "boolean";
            case 2:
                return "float";
            case 3:
                return XmlErrorCodes.DOUBLE;
            case 4:
                return "byte";
            case 5:
                return "short";
            case 6:
                return XmlErrorCodes.INT;
            case 7:
                return XmlErrorCodes.LONG;
            case 8:
                return "java.math.BigDecimal";
            case 9:
                return "java.math.BigInteger";
            case 10:
                return "java.lang.String";
            case 11:
                return "byte[]";
            case 12:
                return "org.apache.xmlbeans.GDate";
            case 13:
                return "org.apache.xmlbeans.GDuration";
            case 14:
                return "java.util.Date";
            case 15:
                return "javax.xml.namespace.QName";
            case 16:
                return "java.util.List";
            case 17:
                return "java.util.Calendar";
            case 18:
                SchemaType sType = sProp.javaBasedOnType();
                if (sType.getSimpleVariety() == 2) {
                    sType = sType.getUnionCommonBaseType();
                }
                if (sType.getBaseEnumType() == null) {
                    throw new AssertionError();
                }
                if (hasBase(sType)) {
                    return findJavaType(sType.getBaseEnumType()).replace('$', '.') + ".Enum";
                }
                return findJavaType(sType).replace('$', '.') + ".Enum";
            case 19:
                return "java.lang.Object";
            default:
                throw new AssertionError();
        }
    }

    void printPropertyGetters(SchemaProperty prop) throws IOException {
        boolean nillable;
        boolean xmltype;
        String propertyName = prop.getJavaPropertyName();
        int javaType = prop.getJavaTypeCode();
        String type = javaTypeForProperty(prop);
        String xtype = xmlTypeForProperty(prop);
        boolean nillable2 = prop.hasNillable() != 0;
        boolean several = prop.extendsJavaArray();
        String propertyDocumentation = prop.getDocumentation();
        String propdesc = "\"" + prop.getName().getLocalPart() + "\"" + (prop.isAttribute() ? " attribute" : " element");
        boolean xmltype2 = javaType == 0;
        if (prop.extendsJavaSingleton()) {
            if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation == null || propertyDocumentation.length() <= 0) {
                printJavaDoc((several ? "Gets first " : "Gets the ") + propdesc, XmlOptions.BeanMethod.GET);
            } else {
                printJavaDocParagraph(propertyDocumentation);
            }
            emit(type + " get" + propertyName + "();", XmlOptions.BeanMethod.GET);
            if (!xmltype2) {
                printJavaDoc((several ? "Gets (as xml) first " : "Gets (as xml) the ") + propdesc, XmlOptions.BeanMethod.XGET);
                emit(xtype + " xget" + propertyName + "();", XmlOptions.BeanMethod.XGET);
            }
            if (nillable2) {
                printJavaDoc((several ? "Tests for nil first " : "Tests for nil ") + propdesc, XmlOptions.BeanMethod.IS_NIL);
                emit("boolean isNil" + propertyName + "();", XmlOptions.BeanMethod.IS_NIL);
            }
        }
        if (prop.extendsJavaOption()) {
            if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation == null || propertyDocumentation.length() <= 0) {
                printJavaDoc((several ? "True if has at least one " : "True if has ") + propdesc, XmlOptions.BeanMethod.IS_SET);
            } else {
                printJavaDocParagraph(propertyDocumentation);
            }
            emit("boolean isSet" + propertyName + "();", XmlOptions.BeanMethod.IS_SET);
        }
        if (several) {
            if (this.opt.isCompileAnnotationAsJavadoc() && propertyDocumentation != null && propertyDocumentation.length() > 0) {
                printJavaDocParagraph(propertyDocumentation);
            }
            String arrayName = propertyName + SoapEncSchemaTypeSystem.SOAP_ARRAY;
            String wrappedType = type;
            if (isJavaPrimitive(javaType)) {
                wrappedType = javaWrappedType(javaType);
            }
            if (this.opt.isCompileAnnotationAsJavadoc() && propertyDocumentation != null && propertyDocumentation.length() > 0) {
                printJavaDocParagraph(propertyDocumentation);
                nillable = nillable2;
                xmltype = xmltype2;
            } else {
                nillable = nillable2;
                xmltype = xmltype2;
                printJavaDoc("Gets a List of " + propdesc + "s", XmlOptions.BeanMethod.GET_LIST);
            }
            emit("java.util.List<" + wrappedType + "> get" + propertyName + "List();", XmlOptions.BeanMethod.GET_LIST);
            if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation == null || propertyDocumentation.length() <= 0) {
                printJavaDoc("Gets array of all " + propdesc + "s", XmlOptions.BeanMethod.GET_ARRAY);
            } else {
                printJavaDocParagraph(propertyDocumentation);
            }
            emit(type + "[] get" + arrayName + "();", XmlOptions.BeanMethod.GET_ARRAY);
            printJavaDoc("Gets ith " + propdesc, XmlOptions.BeanMethod.GET_IDX);
            emit(type + " get" + arrayName + "(int i);", XmlOptions.BeanMethod.GET_IDX);
            if (!xmltype) {
                printJavaDoc("Gets (as xml) a List of " + propdesc + "s", XmlOptions.BeanMethod.XGET_LIST);
                emit("java.util.List<" + xtype + "> xget" + propertyName + "List();", XmlOptions.BeanMethod.XGET_LIST);
                printJavaDoc("Gets (as xml) array of all " + propdesc + "s", XmlOptions.BeanMethod.XGET_ARRAY);
                emit(xtype + "[] xget" + arrayName + "();", XmlOptions.BeanMethod.XGET_ARRAY);
                printJavaDoc("Gets (as xml) ith " + propdesc, XmlOptions.BeanMethod.XGET_IDX);
                emit(xtype + " xget" + arrayName + "(int i);", XmlOptions.BeanMethod.XGET_IDX);
            }
            if (nillable) {
                printJavaDoc("Tests for nil ith " + propdesc, XmlOptions.BeanMethod.IS_NIL_IDX);
                emit("boolean isNil" + arrayName + "(int i);", XmlOptions.BeanMethod.IS_NIL_IDX);
            }
            printJavaDoc("Returns number of " + propdesc, XmlOptions.BeanMethod.SIZE_OF_ARRAY);
            emit("int sizeOf" + arrayName + "();", XmlOptions.BeanMethod.SIZE_OF_ARRAY);
        }
    }

    void printPropertySetters(SchemaProperty prop) throws IOException {
        String propertyDocumentation;
        String propertyDocumentation2;
        String propertyDocumentation3;
        String str;
        String propertyName;
        QName qName = prop.getName();
        boolean isAttr = prop.isAttribute();
        String propertyName2 = prop.getJavaPropertyName();
        int javaType = prop.getJavaTypeCode();
        String type = javaTypeForProperty(prop);
        String xtype = xmlTypeForProperty(prop);
        boolean nillable = prop.hasNillable() != 0;
        boolean optional = prop.extendsJavaOption();
        boolean several = prop.extendsJavaArray();
        boolean singleton = prop.extendsJavaSingleton();
        String propertyDocumentation4 = prop.getDocumentation();
        String safeVarName = NameUtil.nonJavaKeyword(NameUtil.lowerCamelCase(propertyName2));
        if (safeVarName.equals(Complex.DEFAULT_SUFFIX)) {
            safeVarName = "iValue";
        }
        boolean xmltype = javaType == 0;
        String propdesc = "\"" + qName.getLocalPart() + "\"" + (isAttr ? " attribute" : " element");
        boolean nillable2 = nillable;
        boolean xmltype2 = xmltype;
        if (singleton) {
            if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation4 == null || propertyDocumentation4.length() <= 0) {
                propertyDocumentation = propertyDocumentation4;
                printJavaDoc((several ? "Sets first " : "Sets the ") + propdesc, XmlOptions.BeanMethod.SET);
            } else {
                printJavaDocParagraph(propertyDocumentation4);
                propertyDocumentation = propertyDocumentation4;
            }
            emit("void set" + propertyName2 + "(" + type + StringUtils.SPACE + safeVarName + ");", XmlOptions.BeanMethod.SET);
            if (!xmltype2) {
                printJavaDoc((several ? "Sets (as xml) first " : "Sets (as xml) the ") + propdesc, XmlOptions.BeanMethod.XSET);
                emit("void xset" + propertyName2 + "(" + xtype + StringUtils.SPACE + safeVarName + ");", XmlOptions.BeanMethod.XSET);
            }
            if (xmltype2 && !several) {
                printJavaDoc("Appends and returns a new empty " + propdesc, XmlOptions.BeanMethod.ADD_NEW);
                emit(xtype + " addNew" + propertyName2 + "();", XmlOptions.BeanMethod.ADD_NEW);
            }
            if (nillable2) {
                printJavaDoc((several ? "Nils the first " : "Nils the ") + propdesc, XmlOptions.BeanMethod.SET_NIL);
                emit("void setNil" + propertyName2 + "();", XmlOptions.BeanMethod.SET_NIL);
            }
        } else {
            propertyDocumentation = propertyDocumentation4;
        }
        if (!optional) {
            propertyDocumentation2 = propertyDocumentation;
            propertyDocumentation3 = " addNew";
        } else {
            if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation == null || propertyDocumentation.length() <= 0) {
                propertyDocumentation2 = propertyDocumentation;
                propertyDocumentation3 = " addNew";
                printJavaDoc((several ? "Removes first " : "Unsets the ") + propdesc, XmlOptions.BeanMethod.UNSET);
            } else {
                propertyDocumentation2 = propertyDocumentation;
                printJavaDocParagraph(propertyDocumentation2);
                propertyDocumentation3 = " addNew";
            }
            emit("void unset" + propertyName2 + "();", XmlOptions.BeanMethod.UNSET);
        }
        if (several) {
            String arrayName = propertyName2 + SoapEncSchemaTypeSystem.SOAP_ARRAY;
            if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation2 == null || propertyDocumentation2.length() <= 0) {
                str = "();";
                printJavaDoc("Sets array of all " + propdesc, XmlOptions.BeanMethod.SET_ARRAY);
            } else {
                printJavaDocParagraph(propertyDocumentation2);
                str = "();";
            }
            emit("void set" + arrayName + "(" + type + "[] " + safeVarName + "Array);", XmlOptions.BeanMethod.SET_ARRAY);
            if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation2 == null || propertyDocumentation2.length() <= 0) {
                printJavaDoc("Sets ith " + propdesc, XmlOptions.BeanMethod.SET_IDX);
            } else {
                printJavaDocParagraph(propertyDocumentation2);
            }
            emit("void set" + arrayName + "(int i, " + type + StringUtils.SPACE + safeVarName + ");", XmlOptions.BeanMethod.SET_IDX);
            if (!xmltype2) {
                printJavaDoc("Sets (as xml) array of all " + propdesc, XmlOptions.BeanMethod.XSET_ARRAY);
                emit("void xset" + arrayName + "(" + xtype + "[] " + safeVarName + "Array);", XmlOptions.BeanMethod.XSET_ARRAY);
                printJavaDoc("Sets (as xml) ith " + propdesc, XmlOptions.BeanMethod.XSET_IDX);
                emit("void xset" + arrayName + "(int i, " + xtype + StringUtils.SPACE + safeVarName + ");", XmlOptions.BeanMethod.XSET_IDX);
            }
            if (nillable2) {
                printJavaDoc("Nils the ith " + propdesc, XmlOptions.BeanMethod.SET_NIL_IDX);
                emit("void setNil" + arrayName + "(int i);", XmlOptions.BeanMethod.SET_NIL_IDX);
            }
            if (!xmltype2) {
                printJavaDoc("Inserts the value as the ith " + propdesc, XmlOptions.BeanMethod.INSERT_IDX);
                propertyName = propertyName2;
                emit("void insert" + propertyName + "(int i, " + type + StringUtils.SPACE + safeVarName + ");", XmlOptions.BeanMethod.INSERT_IDX);
                printJavaDoc("Appends the value as the last " + propdesc, XmlOptions.BeanMethod.ADD);
                emit("void add" + propertyName + "(" + type + StringUtils.SPACE + safeVarName + ");", XmlOptions.BeanMethod.ADD);
            } else {
                propertyName = propertyName2;
            }
            printJavaDoc("Inserts and returns a new empty value (as xml) as the ith " + propdesc, XmlOptions.BeanMethod.INSERT_NEW_IDX);
            emit(xtype + " insertNew" + propertyName + "(int i);", XmlOptions.BeanMethod.INSERT_NEW_IDX);
            printJavaDoc("Appends and returns a new empty value (as xml) as the last " + propdesc, XmlOptions.BeanMethod.ADD_NEW);
            emit(xtype + propertyDocumentation3 + propertyName + str, XmlOptions.BeanMethod.ADD_NEW);
            printJavaDoc("Removes the ith " + propdesc, XmlOptions.BeanMethod.REMOVE_IDX);
            emit("void remove" + propertyName + "(int i);", XmlOptions.BeanMethod.REMOVE_IDX);
        }
    }

    String getAtomicRestrictionType(SchemaType sType) {
        SchemaType pType = sType.getPrimitiveType();
        switch (pType.getBuiltinTypeCode()) {
            case 2:
                return "org.apache.xmlbeans.impl.values.XmlAnySimpleTypeImpl";
            case 3:
                return "org.apache.xmlbeans.impl.values.JavaBooleanHolderEx";
            case 4:
                return "org.apache.xmlbeans.impl.values.JavaBase64HolderEx";
            case 5:
                return "org.apache.xmlbeans.impl.values.JavaHexBinaryHolderEx";
            case 6:
                return "org.apache.xmlbeans.impl.values.JavaUriHolderEx";
            case 7:
                return "org.apache.xmlbeans.impl.values.JavaQNameHolderEx";
            case 8:
                return "org.apache.xmlbeans.impl.values.JavaNotationHolderEx";
            case 9:
                return "org.apache.xmlbeans.impl.values.JavaFloatHolderEx";
            case 10:
                return "org.apache.xmlbeans.impl.values.JavaDoubleHolderEx";
            case 11:
                switch (sType.getDecimalSize()) {
                    case 8:
                    case 16:
                    case 32:
                        return "org.apache.xmlbeans.impl.values.JavaIntHolderEx";
                    case 64:
                        return "org.apache.xmlbeans.impl.values.JavaLongHolderEx";
                    case 1000000:
                        return "org.apache.xmlbeans.impl.values.JavaIntegerHolderEx";
                    case SchemaType.SIZE_BIG_DECIMAL /* 1000001 */:
                        return "org.apache.xmlbeans.impl.values.JavaDecimalHolderEx";
                    default:
                        throw new AssertionError();
                }
            case 12:
                if (sType.hasStringEnumValues()) {
                    return "org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx";
                }
                return "org.apache.xmlbeans.impl.values.JavaStringHolderEx";
            case 13:
                return "org.apache.xmlbeans.impl.values.JavaGDurationHolderEx";
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return "org.apache.xmlbeans.impl.values.JavaGDateHolderEx";
            default:
                throw new AssertionError("unrecognized primitive type");
        }
    }

    static SchemaType findBaseType(SchemaType sType) {
        while (sType.getFullJavaName() == null) {
            sType = sType.getBaseType();
        }
        return sType;
    }

    String getBaseClass(SchemaType sType) {
        SchemaType baseType = findBaseType(sType.getBaseType());
        switch (sType.getSimpleVariety()) {
            case 0:
                if (!XmlObject.type.equals(baseType)) {
                    return baseType.getFullJavaImplName();
                }
                return "org.apache.xmlbeans.impl.values.XmlComplexContentImpl";
            case 1:
                if (sType.isBuiltinType()) {
                    throw new AssertionError();
                }
                return getAtomicRestrictionType(sType);
            case 2:
                return "org.apache.xmlbeans.impl.values.XmlUnionImpl";
            case 3:
                return "org.apache.xmlbeans.impl.values.XmlListImpl";
            default:
                throw new IllegalStateException();
        }
    }

    void printConstructor(SchemaType sType, String shortName) throws IOException {
        String str;
        emit("");
        emit("public " + shortName + "(org.apache.xmlbeans.SchemaType sType) {");
        startBlock();
        StringBuilder append = new StringBuilder().append("super(sType");
        if (sType.getSimpleVariety() == 0) {
            str = "";
        } else {
            str = ", " + (!sType.isSimpleType());
        }
        emit(append.append(str).append(");").toString());
        endBlock();
        if (sType.getSimpleVariety() != 0) {
            emit("");
            emit("protected " + shortName + "(org.apache.xmlbeans.SchemaType sType, boolean b) {");
            startBlock();
            emit("super(sType, b);");
            endBlock();
        }
    }

    void startClass(SchemaType sType, boolean isInner) throws IOException {
        String shortName = sType.getShortJavaImplName();
        String baseClass = getBaseClass(sType);
        StringBuilder interfaces = new StringBuilder();
        interfaces.append(sType.getFullJavaName().replace('$', '.'));
        if (sType.getSimpleVariety() == 2) {
            SchemaType[] memberTypes = sType.getUnionMemberTypes();
            for (SchemaType memberType : memberTypes) {
                interfaces.append(", ").append(memberType.getFullJavaName().replace('$', '.'));
            }
        }
        emit("public " + (isInner ? "static " : "") + "class " + shortName + " extends " + baseClass + " implements " + ((Object) interfaces) + " {");
        startBlock();
        emit("private static final long serialVersionUID = 1L;");
    }

    void makeAttributeDefaultValue(String jtargetType, SchemaProperty prop, String identifier) throws IOException {
        String fullName = jtargetType;
        if (fullName == null) {
            fullName = prop.javaBasedOnType().getFullJavaName().replace('$', '.');
        }
        emit("target = (" + fullName + ")get_default_attribute_value(" + identifier + ");");
    }

    String makeMissingValue(int javaType) throws IOException {
        switch (javaType) {
            case 1:
                return "false";
            case 2:
                return "0.0f";
            case 3:
                return "0.0";
            case 4:
            case 5:
            case 6:
                return "0";
            case 7:
                return "0L";
            default:
                return "null";
        }
    }

    void printJGetArrayValue(int javaType, String type, SchemaTypeImpl stype, String setIdentifier) throws IOException {
        String em;
        switch (javaType) {
            case 0:
                em = "XmlObjectArray(#ID#, new " + type + "[0]);";
                break;
            case 1:
                em = "BooleanArray(#ID#);";
                break;
            case 2:
                em = "FloatArray(#ID#);";
                break;
            case 3:
                em = "DoubleArray(#ID#);";
                break;
            case 4:
                em = "ByteArray(#ID#);";
                break;
            case 5:
                em = "ShortArray(#ID#);";
                break;
            case 6:
                em = "IntArray(#ID#);";
                break;
            case 7:
                em = "LongArray(#ID#);";
                break;
            case 8:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getBigDecimalValue, java.math.BigDecimal[]::new);";
                break;
            case 9:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getBigIntegerValue, java.math.BigInteger[]::new);";
                break;
            case 10:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getStringValue, String[]::new);";
                break;
            case 11:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getByteArrayValue, byte[][]::new);";
                break;
            case 12:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getGDateValue, org.apache.xmlbeans.GDate[]::new);";
                break;
            case 13:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getGDurationValue, org.apache.xmlbeans.GDuration[]::new);";
                break;
            case 14:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getDateValue, java.util.Date[]::new);";
                break;
            case 15:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getQNameValue, javax.xml.namespace.QName[]::new);";
                break;
            case 16:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getListValue, java.util.List[]::new);";
                break;
            case 17:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getCalendarValue, java.util.Calendar[]::new);";
                break;
            case 18:
                em = "EnumArray(#ID#, " + type + "[]::new);";
                break;
            case 19:
                em = "ObjectArray(#ID#, org.apache.xmlbeans.SimpleValue::getObjectValue, java.lang.Object[]::new);";
                break;
            case 20:
                em = "ObjectArray(#ID#, e -> " + getUserTypeStaticHandlerMethod(false, stype) + "(e), " + stype.getUserTypeName() + "[]::new);";
                break;
            default:
                throw new IllegalStateException();
        }
        emit("return get" + em.replace("#ID#", setIdentifier), XmlOptions.BeanMethod.GET_ARRAY);
    }

    String printJGetValue(int javaType, String type, SchemaTypeImpl stype) throws IOException {
        switch (javaType) {
            case 0:
                return TypedValues.AttributesType.S_TARGET;
            case 1:
                return "target.getBooleanValue()";
            case 2:
                return "target.getFloatValue()";
            case 3:
                return "target.getDoubleValue()";
            case 4:
                return "target.getByteValue()";
            case 5:
                return "target.getShortValue()";
            case 6:
                return "target.getIntValue()";
            case 7:
                return "target.getLongValue()";
            case 8:
                return "target.getBigDecimalValue()";
            case 9:
                return "target.getBigIntegerValue()";
            case 10:
                return "target.getStringValue()";
            case 11:
                return "target.getByteArrayValue()";
            case 12:
                return "target.getGDateValue()";
            case 13:
                return "target.getGDurationValue()";
            case 14:
                return "target.getDateValue()";
            case 15:
                return "target.getQNameValue()";
            case 16:
                return "target.getListValue()";
            case 17:
                return "target.getCalendarValue()";
            case 18:
                return "(" + type + ")target.getEnumValue()";
            case 19:
                return "target.getObjectValue()";
            case 20:
                return getUserTypeStaticHandlerMethod(false, stype) + "(target)";
            default:
                throw new IllegalStateException();
        }
    }

    void printJSetValue(int javaType, String safeVarName, SchemaTypeImpl stype) throws IOException {
        String em;
        switch (javaType) {
            case 0:
                em = "target.set(#VARNAME#)";
                break;
            case 1:
                em = "target.setBooleanValue(#VARNAME#)";
                break;
            case 2:
                em = "target.setFloatValue(#VARNAME#)";
                break;
            case 3:
                em = "target.setDoubleValue(#VARNAME#)";
                break;
            case 4:
                em = "target.setByteValue(#VARNAME#)";
                break;
            case 5:
                em = "target.setShortValue(#VARNAME#)";
                break;
            case 6:
                em = "target.setIntValue(#VARNAME#)";
                break;
            case 7:
                em = "target.setLongValue(#VARNAME#)";
                break;
            case 8:
                em = "target.setBigDecimalValue(#VARNAME#)";
                break;
            case 9:
                em = "target.setBigIntegerValue(#VARNAME#)";
                break;
            case 10:
                em = "target.setStringValue(#VARNAME#)";
                break;
            case 11:
                em = "target.setByteArrayValue(#VARNAME#)";
                break;
            case 12:
                em = "target.setGDateValue(#VARNAME#)";
                break;
            case 13:
                em = "target.setGDurationValue(#VARNAME#)";
                break;
            case 14:
                em = "target.setDateValue(#VARNAME#)";
                break;
            case 15:
                em = "target.setQNameValue(#VARNAME#)";
                break;
            case 16:
                em = "target.setListValue(#VARNAME#)";
                break;
            case 17:
                em = "target.setCalendarValue(#VARNAME#)";
                break;
            case 18:
                em = "target.setEnumValue(#VARNAME#)";
                break;
            case 19:
                em = "target.setObjectValue(#VARNAME#)";
                break;
            case 20:
                em = getUserTypeStaticHandlerMethod(true, stype) + "(#VARNAME#, target)";
                break;
            default:
                throw new IllegalStateException();
        }
        emit(em.replace("#VARNAME#", safeVarName) + ";");
    }

    void printStaticFields(SchemaProperty[] properties, Map<SchemaProperty, Identifier> propMap) throws IOException {
        if (properties.length == 0) {
            return;
        }
        int countQSet = 0;
        emit("");
        emit("private static final QName[] PROPERTY_QNAME = {");
        indent();
        for (SchemaProperty prop : properties) {
            QName name = prop.getName();
            propMap.put(prop, new Identifier(propMap.size()));
            emit("new QName(\"" + name.getNamespaceURI() + "\", \"" + name.getLocalPart() + "\"),");
            countQSet = Math.max(countQSet, prop.acceptedNames() == null ? 0 : prop.acceptedNames().length);
        }
        outdent();
        emit("};");
        emit("");
        int i = 1;
        if (countQSet > 1) {
            emit("private static final QNameSet[] PROPERTY_QSET = {");
            int length = properties.length;
            int i2 = 0;
            while (i2 < length) {
                SchemaProperty prop2 = properties[i2];
                QName[] qnames = prop2.acceptedNames();
                if (qnames != null && qnames.length > i) {
                    int i3 = 0 + 1;
                    propMap.get(prop2).setSetIndex(0);
                    emit("QNameSet.forArray( new QName[] { ");
                    indent();
                    for (QName qname : qnames) {
                        emit("new QName(\"" + qname.getNamespaceURI() + "\", \"" + qname.getLocalPart() + "\"),");
                    }
                    outdent();
                    emit("}),");
                }
                i2++;
                i = 1;
            }
            emit("};");
        }
    }

    void emitImplementationPreamble() throws IOException {
        emit("synchronized (monitor()) {");
        indent();
        emit("check_orphaned();");
    }

    void emitImplementationPostamble() throws IOException {
        outdent();
        emit(VectorFormat.DEFAULT_SUFFIX);
    }

    void emitAddTarget(String identifier, boolean isAttr, String xtype) throws IOException {
        if (isAttr) {
            emit("target = (" + xtype + ")get_store().add_attribute_user(" + identifier + ");");
        } else {
            emit("target = (" + xtype + ")get_store().add_element_user(" + identifier + ");");
        }
    }

    void emitPre(SchemaType sType, int opType, String identifier, boolean isAttr) throws IOException {
        emitPre(sType, opType, identifier, isAttr, StructuredDataId.RESERVED);
    }

    void emitPre(SchemaType sType, int opType, String identifier, boolean isAttr, String index) throws IOException {
        PrePostExtension ext;
        SchemaTypeImpl sImpl = getImpl(sType);
        if (sImpl != null && (ext = sImpl.getPrePostExtension()) != null && ext.hasPreCall()) {
            emit("if ( " + ext.getStaticHandler() + ".preSet(" + prePostOpString(opType) + ", this, " + identifier + ", " + isAttr + ", " + index + ")) {");
            startBlock();
        }
    }

    void emitPost(SchemaType sType, int opType, String identifier, boolean isAttr) throws IOException {
        emitPost(sType, opType, identifier, isAttr, StructuredDataId.RESERVED);
    }

    void emitPost(SchemaType sType, int opType, String identifier, boolean isAttr, String index) throws IOException {
        PrePostExtension ext;
        SchemaTypeImpl sImpl = getImpl(sType);
        if (sImpl != null && (ext = sImpl.getPrePostExtension()) != null) {
            if (ext.hasPreCall()) {
                endBlock();
            }
            if (ext.hasPostCall()) {
                emit(ext.getStaticHandler() + ".postSet(" + prePostOpString(opType) + ", this, " + identifier + ", " + isAttr + ", " + index + ");");
            }
        }
    }

    String prePostOpString(int opType) {
        switch (opType) {
            case 1:
                return "org.apache.xmlbeans.PrePostExtension.OPERATION_SET";
            case 2:
                return "org.apache.xmlbeans.PrePostExtension.OPERATION_INSERT";
            case 3:
                return "org.apache.xmlbeans.PrePostExtension.OPERATION_REMOVE";
            default:
                throw new AssertionError();
        }
    }

    void emitGetTarget(String setIdentifier, String identifier, boolean isAttr, String index, int nullBehaviour, String xtype) throws IOException {
        if (setIdentifier == null || identifier == null) {
            throw new AssertionError();
        }
        emit(xtype + " target = null;");
        if (isAttr) {
            emit("target = (" + xtype + ")get_store().find_attribute_user(" + identifier + ");");
        } else {
            emit("target = (" + xtype + ")get_store().find_element_user(" + setIdentifier + ", " + index + ");");
        }
        if (nullBehaviour == 1) {
            return;
        }
        emit("if (target == null) {");
        startBlock();
        switch (nullBehaviour) {
            case 3:
                emitAddTarget(identifier, isAttr, xtype);
                break;
            case 4:
                emit("throw new IndexOutOfBoundsException();");
                break;
            default:
                throw new AssertionError("Bad behaviour type: " + nullBehaviour);
        }
        endBlock();
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0160 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0127  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void printListGetterImpl(java.lang.String r10, java.lang.String r11, java.lang.String r12, boolean r13, boolean r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter.printListGetterImpl(java.lang.String, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    void printGetterImpls(SchemaProperty prop, Map<SchemaProperty, Identifier> propMap) throws IOException {
        String jtargetType;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String xtype;
        boolean isAttr;
        Set<XmlOptions.BeanMethod> bmList;
        int javaType;
        String propdesc;
        String setIdentifier;
        String xtype2;
        int javaType2;
        String jtargetType2;
        String str6;
        String str7;
        String str8;
        String str9;
        String identifier;
        boolean isAttr2;
        String identifier2;
        String propdesc2;
        String propertyName;
        String propdesc3;
        String propdesc4;
        boolean isAttr3;
        String setIdentifier2;
        String xtype3;
        String identifier3;
        int javaType3;
        String setIdentifier3;
        String str10;
        String xtype4;
        QName qName = prop.getName();
        String xtype5 = propMap.get(prop).getIdentifier();
        String setIdentifier4 = propMap.get(prop).getSetIdentifier();
        boolean several = prop.extendsJavaArray();
        boolean nillable = prop.hasNillable() != 0;
        String type = javaTypeForProperty(prop);
        String xtype6 = xmlTypeForProperty(prop);
        int javaType4 = prop.getJavaTypeCode();
        boolean isAttr4 = prop.isAttribute();
        String propertyName2 = prop.getJavaPropertyName();
        String propertyDocumentation = prop.getDocumentation();
        String propdesc5 = "\"" + qName.getLocalPart() + "\"" + (isAttr4 ? " attribute" : " element");
        boolean xmltype = javaType4 == 0;
        String jtargetType3 = (xmlTypeForPropertyIsUnion(prop) || !xmltype) ? "org.apache.xmlbeans.SimpleValue" : xtype6;
        Set<XmlOptions.BeanMethod> bmList2 = this.opt == null ? null : this.opt.getCompilePartialMethod();
        if (!prop.extendsJavaSingleton()) {
            jtargetType = jtargetType3;
            str = " get";
            str2 = ";";
            str3 = "public boolean isNil";
            str4 = "return target;";
            str5 = " xget";
            xtype = xtype6;
            isAttr = isAttr4;
            bmList = bmList2;
            javaType = javaType4;
            propdesc = propdesc5;
            setIdentifier = setIdentifier4;
            xtype2 = xtype5;
        } else {
            if (bmList2 != null && !bmList2.contains(XmlOptions.BeanMethod.GET)) {
                javaType3 = javaType4;
                setIdentifier3 = setIdentifier4;
                propdesc = propdesc5;
                str = " get";
                bmList = bmList2;
                str10 = ";";
            } else {
                if (!this.opt.isCompileAnnotationAsJavadoc() || propertyDocumentation == null || propertyDocumentation.length() <= 0) {
                    identifier3 = xtype5;
                    printJavaDoc((several ? "Gets first " : "Gets the ") + propdesc5);
                } else {
                    printJavaDocParagraph(propertyDocumentation);
                    identifier3 = xtype5;
                }
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public " + type + " get" + propertyName2 + "() {");
                startBlock();
                emitImplementationPreamble();
                String propertyDocumentation2 = jtargetType3;
                str = " get";
                bmList = bmList2;
                propdesc = propdesc5;
                xtype5 = identifier3;
                emitGetTarget(setIdentifier4, xtype5, isAttr4, "0", 1, propertyDocumentation2);
                jtargetType3 = propertyDocumentation2;
                if (isAttr4 && (prop.hasDefault() == 2 || prop.hasFixed() == 2)) {
                    emit("if (target == null) {");
                    startBlock();
                    makeAttributeDefaultValue(jtargetType3, prop, xtype5);
                    endBlock();
                }
                javaType3 = javaType4;
                setIdentifier3 = setIdentifier4;
                str10 = ";";
                emit("return (target == null) ? " + makeMissingValue(javaType3) + " : " + printJGetValue(javaType3, type, (SchemaTypeImpl) prop.getType()) + str10);
                emitImplementationPostamble();
                endBlock();
            }
            if (xmltype) {
                javaType = javaType3;
                xtype4 = xtype6;
                jtargetType = jtargetType3;
                str2 = str10;
                str4 = "return target;";
                str5 = " xget";
                setIdentifier = setIdentifier3;
            } else if (bmList == null || bmList.contains(XmlOptions.BeanMethod.XGET)) {
                String identifier4 = xtype5;
                printJavaDoc((several ? "Gets (as xml) first " : "Gets (as xml) the ") + propdesc);
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public " + xtype6 + " xget" + propertyName2 + "() {");
                startBlock();
                emitImplementationPreamble();
                javaType = javaType3;
                str2 = str10;
                str5 = " xget";
                jtargetType = jtargetType3;
                xtype4 = xtype6;
                setIdentifier = setIdentifier3;
                xtype5 = identifier4;
                emitGetTarget(setIdentifier, xtype5, isAttr4, "0", 1, xtype4);
                if (isAttr4 && (prop.hasDefault() == 2 || prop.hasFixed() == 2)) {
                    emit("if (target == null) {");
                    startBlock();
                    makeAttributeDefaultValue(xtype4, prop, xtype5);
                    endBlock();
                }
                str4 = "return target;";
                emit(str4);
                emitImplementationPostamble();
                endBlock();
            } else {
                javaType = javaType3;
                xtype4 = xtype6;
                jtargetType = jtargetType3;
                str2 = str10;
                str4 = "return target;";
                str5 = " xget";
                setIdentifier = setIdentifier3;
            }
            if (!nillable) {
                isAttr = isAttr4;
                str3 = "public boolean isNil";
                xtype = xtype4;
                xtype2 = xtype5;
            } else if (bmList == null || bmList.contains(XmlOptions.BeanMethod.IS_NIL)) {
                printJavaDoc((several ? "Tests for nil first " : "Tests for nil ") + propdesc);
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public boolean isNil" + propertyName2 + "() {");
                startBlock();
                emitImplementationPreamble();
                str3 = "public boolean isNil";
                emitGetTarget(setIdentifier, xtype5, isAttr4, "0", 1, xtype4);
                isAttr = isAttr4;
                xtype = xtype4;
                xtype2 = xtype5;
                emit("return target != null && target.isNil();");
                emitImplementationPostamble();
                endBlock();
            } else {
                isAttr = isAttr4;
                str3 = "public boolean isNil";
                xtype = xtype4;
                xtype2 = xtype5;
            }
        }
        if (prop.extendsJavaOption() && (bmList == null || bmList.contains(XmlOptions.BeanMethod.IS_SET))) {
            printJavaDoc((several ? "True if has at least one " : "True if has ") + propdesc);
            if (!this.opt.isCompileNoAnnotations()) {
                emit("@Override");
            }
            emit("public boolean isSet" + propertyName2 + "() {");
            startBlock();
            emitImplementationPreamble();
            if (isAttr) {
                emit("return get_store().find_attribute_user(" + xtype2 + ") != null;");
            } else {
                emit("return get_store().count_elements(" + setIdentifier + ") != 0;");
            }
            emitImplementationPostamble();
            endBlock();
        }
        if (several) {
            String arrayName = propertyName2 + SoapEncSchemaTypeSystem.SOAP_ARRAY;
            String wrappedType = type;
            if (isJavaPrimitive(javaType)) {
                wrappedType = javaWrappedType(javaType);
            }
            String propdesc6 = propdesc;
            String propdesc7 = setIdentifier;
            printListGetterImpl(propdesc6, propertyName2, wrappedType, xmltype, false);
            if (bmList == null || bmList.contains(XmlOptions.BeanMethod.GET_ARRAY)) {
                printJavaDoc("Gets array of all " + propdesc6 + "s");
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public " + type + "[] get" + arrayName + "() {");
                startBlock();
                javaType2 = javaType;
                printJGetArrayValue(javaType2, type, (SchemaTypeImpl) prop.getType(), propdesc7);
                endBlock();
            } else {
                javaType2 = javaType;
            }
            if (bmList == null || bmList.contains(XmlOptions.BeanMethod.GET_IDX)) {
                printJavaDoc("Gets ith " + propdesc6);
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public " + type + str + arrayName + "(int i) {");
                startBlock();
                emitImplementationPreamble();
                String jtargetType4 = jtargetType;
                jtargetType2 = propdesc6;
                String identifier5 = xtype2;
                String str11 = str4;
                str6 = "(int i) {";
                boolean isAttr5 = isAttr;
                str7 = str3;
                str8 = "s";
                str9 = str11;
                emitGetTarget(propdesc7, identifier5, isAttr5, Complex.DEFAULT_SUFFIX, 4, jtargetType4);
                identifier = identifier5;
                isAttr2 = isAttr5;
                identifier2 = propdesc7;
                emit("return " + printJGetValue(javaType2, type, (SchemaTypeImpl) prop.getType()) + str2);
                emitImplementationPostamble();
                endBlock();
            } else {
                identifier = xtype2;
                str9 = str4;
                identifier2 = propdesc7;
                isAttr2 = isAttr;
                jtargetType2 = propdesc6;
                str6 = "(int i) {";
                str7 = str3;
                str8 = "s";
            }
            if (!xmltype) {
                propdesc2 = jtargetType2;
                String xtype7 = xtype;
                printListGetterImpl(propdesc2, propertyName2, xtype7, false, true);
                propertyName = xtype7;
            } else {
                propdesc2 = jtargetType2;
                propertyName = xtype;
            }
            if (!xmltype && (bmList == null || bmList.contains(XmlOptions.BeanMethod.XGET_ARRAY))) {
                printJavaDoc("Gets (as xml) array of all " + propdesc2 + str8);
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public " + propertyName + "[] xget" + arrayName + "() {");
                startBlock();
                emit("return xgetArray(" + identifier2 + ", " + propertyName + "[]::new);");
                endBlock();
            }
            if (xmltype) {
                propdesc3 = propdesc2;
                propdesc4 = identifier2;
                isAttr3 = isAttr2;
                setIdentifier2 = propertyName;
                xtype3 = identifier;
            } else if (bmList == null || bmList.contains(XmlOptions.BeanMethod.XGET_IDX)) {
                printJavaDoc("Gets (as xml) ith " + propdesc2);
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public " + propertyName + str5 + arrayName + str6);
                startBlock();
                emitImplementationPreamble();
                propdesc3 = propdesc2;
                propdesc4 = identifier2;
                isAttr3 = isAttr2;
                setIdentifier2 = propertyName;
                xtype3 = identifier;
                emitGetTarget(propdesc4, xtype3, isAttr3, Complex.DEFAULT_SUFFIX, 4, setIdentifier2);
                emit(str9);
                emitImplementationPostamble();
                endBlock();
            } else {
                propdesc3 = propdesc2;
                propdesc4 = identifier2;
                isAttr3 = isAttr2;
                setIdentifier2 = propertyName;
                xtype3 = identifier;
            }
            if (nillable && (bmList == null || bmList.contains(XmlOptions.BeanMethod.IS_NIL_IDX))) {
                printJavaDoc("Tests for nil ith " + propdesc3);
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit(str7 + arrayName + str6);
                startBlock();
                emitImplementationPreamble();
                emitGetTarget(propdesc4, xtype3, isAttr3, Complex.DEFAULT_SUFFIX, 4, setIdentifier2);
                emit("return target.isNil();");
                emitImplementationPostamble();
                endBlock();
            }
            if (bmList == null || bmList.contains(XmlOptions.BeanMethod.SIZE_OF_ARRAY)) {
                printJavaDoc("Returns number of " + propdesc3);
                if (!this.opt.isCompileNoAnnotations()) {
                    emit("@Override");
                }
                emit("public int sizeOf" + arrayName + "() {");
                startBlock();
                emitImplementationPreamble();
                emit("return get_store().count_elements(" + propdesc4 + ");");
                emitImplementationPostamble();
                endBlock();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0e7b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0d84  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0dd7  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0e02  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0d49  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0c9b  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0b5e  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0ad1  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0a14  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x08a2  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0880  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0979  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0a1c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0adf  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0b66  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0cb7  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0d4d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0e55  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0ed3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0ef9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void printSetterImpls(org.apache.xmlbeans.SchemaProperty r48, java.util.Map<org.apache.xmlbeans.SchemaProperty, org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter.Identifier> r49, org.apache.xmlbeans.SchemaType r50) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 3927
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter.printSetterImpls(org.apache.xmlbeans.SchemaProperty, java.util.Map, org.apache.xmlbeans.SchemaType):void");
    }

    SchemaProperty[] getSchemaProperties(SchemaType sType) {
        if (sType.getContentType() != 2) {
            return getDerivedProperties(sType);
        }
        SchemaType baseType = sType.getBaseType();
        List<SchemaProperty> extraProperties = null;
        while (true) {
            if (baseType.isSimpleType() || baseType.isBuiltinType()) {
                break;
            }
            for (SchemaProperty baseProperty : baseType.getDerivedProperties()) {
                if (!baseProperty.isAttribute() || sType.getAttributeProperty(baseProperty.getName()) == null) {
                    if (extraProperties == null) {
                        extraProperties = new ArrayList<>();
                    }
                    extraProperties.add(baseProperty);
                }
            }
            baseType = baseType.getBaseType();
        }
        SchemaProperty[] properties = sType.getProperties();
        if (extraProperties == null) {
            return properties;
        }
        Collections.addAll(extraProperties, properties);
        return (SchemaProperty[]) extraProperties.toArray(new SchemaProperty[0]);
    }

    void printInnerTypeImpl(SchemaType sType, SchemaTypeSystem system, boolean isInner) throws IOException {
        String shortName = sType.getShortJavaImplName();
        printInnerTypeJavaDoc(sType);
        startClass(sType, isInner);
        printConstructor(sType, shortName);
        printExtensionImplMethods(sType);
        if (!sType.isSimpleType()) {
            SchemaProperty[] properties = getSchemaProperties(sType);
            Map<SchemaProperty, Identifier> propMap = new HashMap<>();
            printStaticFields(properties, propMap);
            for (SchemaProperty prop : properties) {
                printGetterImpls(prop, propMap);
                if (!prop.isReadOnly()) {
                    printSetterImpls(prop, propMap, sType);
                }
            }
        }
        printNestedTypeImpls(sType, system);
        endBlock();
    }

    private SchemaProperty[] getDerivedProperties(SchemaType sType) {
        QName name = sType.getName();
        if (name != null && name.equals(sType.getBaseType().getName())) {
            SchemaProperty[] props = sType.getDerivedProperties();
            Map<QName, SchemaProperty> propsByName = new LinkedHashMap<>();
            for (SchemaProperty prop : props) {
                propsByName.put(prop.getName(), prop);
            }
            for (SchemaType sType2 = sType.getBaseType(); sType2 != null && name.equals(sType2.getName()); sType2 = sType2.getBaseType()) {
                SchemaProperty[] props2 = sType2.getDerivedProperties();
                for (SchemaProperty prop2 : props2) {
                    if (!propsByName.containsKey(prop2.getName())) {
                        propsByName.put(prop2.getName(), prop2);
                    }
                }
            }
            return (SchemaProperty[]) propsByName.values().toArray(new SchemaProperty[0]);
        }
        return sType.getDerivedProperties();
    }

    private void printExtensionImplMethods(SchemaType sType) throws IOException {
        InterfaceExtension[] exts;
        SchemaTypeImpl sImpl = getImpl(sType);
        if (sImpl != null && (exts = sImpl.getInterfaceExtensions()) != null) {
            for (InterfaceExtension ext : exts) {
                InterfaceExtension.MethodSignature[] methods = ext.getMethods();
                if (methods != null) {
                    for (InterfaceExtension.MethodSignature method : methods) {
                        printJavaDoc("Implementation method for interface " + ext.getStaticHandler());
                        printInterfaceMethodDecl(method);
                        startBlock();
                        printInterfaceMethodImpl(ext.getStaticHandler(), method);
                        endBlock();
                    }
                }
            }
        }
    }

    void printInterfaceMethodDecl(InterfaceExtension.MethodSignature method) throws IOException {
        StringBuilder decl = new StringBuilder(60);
        decl.append("public ").append(method.getReturnType());
        decl.append(StringUtils.SPACE).append(method.getName()).append("(");
        String[] paramTypes = method.getParameterTypes();
        String[] paramNames = method.getParameterNames();
        for (int i = 1; i < paramTypes.length; i++) {
            if (i > 1) {
                decl.append(", ");
            }
            decl.append(paramTypes[i]).append(StringUtils.SPACE).append(paramNames[i]);
        }
        decl.append(")");
        String[] exceptions = method.getExceptionTypes();
        int i2 = 0;
        while (i2 < exceptions.length) {
            decl.append(i2 == 0 ? " throws " : ", ").append(exceptions[i2]);
            i2++;
        }
        decl.append(" {");
        emit(decl.toString());
    }

    void printInterfaceMethodImpl(String handler, InterfaceExtension.MethodSignature method) throws IOException {
        StringBuilder impl = new StringBuilder(60);
        if (!method.getReturnType().equals("void")) {
            impl.append("return ");
        }
        impl.append(handler).append(".").append(method.getName()).append("(this");
        String[] params = method.getParameterTypes();
        String[] paramsNames = method.getParameterNames();
        for (int i = 1; i < params.length; i++) {
            impl.append(", ").append(paramsNames[i]);
        }
        impl.append(");");
        emit(impl.toString());
    }

    void printNestedTypeImpls(SchemaType sType, SchemaTypeSystem system) throws IOException {
        boolean redefinition = sType.getName() != null && sType.getName().equals(sType.getBaseType().getName());
        while (sType != null) {
            SchemaType[] anonTypes = sType.getAnonymousTypes();
            for (SchemaType anonType : anonTypes) {
                if (anonType.isSkippedAnonymousType()) {
                    printNestedTypeImpls(anonType, system);
                } else {
                    printInnerTypeImpl(anonType, system, true);
                }
            }
            if (redefinition) {
                if (sType.getDerivationType() == 2 || sType.isSimpleType()) {
                    sType = sType.getBaseType();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    @Override // org.apache.xmlbeans.SchemaCodePrinter
    public void printHolder(Writer writer, SchemaTypeSystem system, XmlOptions opt, Repackager repackager) throws IOException {
        this._writer = writer;
        String sysPack = system.getName();
        if (repackager != null) {
            sysPack = repackager.repackage(new StringBuffer(sysPack)).toString();
        }
        emit("package " + sysPack + ";");
        emit("");
        emit("import org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl;");
        emit("");
        emit("public final class TypeSystemHolder extends SchemaTypeSystemImpl {");
        indent();
        emit("public static final TypeSystemHolder typeSystem = new TypeSystemHolder();");
        emit("");
        emit("private TypeSystemHolder() {");
        indent();
        emit("super(TypeSystemHolder.class);");
        outdent();
        emit(VectorFormat.DEFAULT_SUFFIX);
        outdent();
        emit(VectorFormat.DEFAULT_SUFFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class Identifier {
        private final int getindex;
        private Integer setindex;

        private Identifier(int index) {
            this.setindex = null;
            this.getindex = index;
        }

        public String getIdentifier() {
            return "PROPERTY_QNAME[" + this.getindex + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }

        public String getSetIdentifier() {
            return this.setindex == null ? getIdentifier() : "PROPERTY_QSET[" + this.setindex + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }

        public void setSetIndex(int setindex) {
            this.setindex = Integer.valueOf(setindex);
        }
    }
}
