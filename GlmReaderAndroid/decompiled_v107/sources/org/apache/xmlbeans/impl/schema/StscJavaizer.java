package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.UserType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.apache.xmlbeans.impl.logging.XmlBeansLogManager;

/* loaded from: classes11.dex */
public class StscJavaizer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_ENUM_COUNT = 3668;
    private static final Logger LOG = XmlBeansLogManager.getLogger(StscJavaizer.class);
    private static final String[] PREFIXES = {"get", "xget", "isNil", "isSet", "sizeOf", "set", "xset", "addNew", "setNil", "unset", "insert", "add", "insertNew", "addNew", "remove"};
    static String[] PROTECTED_PROPERTIES = {"StringValue", "BooleanValue", "ByteValue", "ShortValue", "IntValue", "LongValue", "BigIntegerValue", "BigDecimalValue", "FloatValue", "DoubleValue", "ByteArrayValue", "EnumValue", "CalendarValue", "DateValue", "GDateValue", "GDurationValue", "QNameValue", "ListValue", "ObjectValue", "Class"};
    static Set<String> PROTECTED_PROPERTIES_SET = new HashSet(Arrays.asList(PROTECTED_PROPERTIES));

    public static void javaizeAllTypes(boolean javaize) {
        StscState state = StscState.get();
        List<SchemaType> allSeenTypes = new ArrayList<>();
        allSeenTypes.addAll(Arrays.asList(state.documentTypes()));
        allSeenTypes.addAll(Arrays.asList(state.attributeTypes()));
        allSeenTypes.addAll(Arrays.asList(state.globalTypes()));
        if (javaize) {
            assignGlobalJavaNames(allSeenTypes);
        }
        for (int i = 0; i < allSeenTypes.size(); i++) {
            SchemaType gType = allSeenTypes.get(i);
            if (javaize) {
                javaizeType((SchemaTypeImpl) gType);
                String className = gType.getFullJavaName();
                if (className != null) {
                    state.addClassname(className.replace('$', '.'), gType);
                }
            } else {
                skipJavaizingType((SchemaTypeImpl) gType);
            }
            allSeenTypes.addAll(Arrays.asList(gType.getAnonymousTypes()));
            addAnonymousTypesFromRedefinition(gType, allSeenTypes);
        }
    }

    static void assignGlobalJavaNames(Collection<SchemaType> schemaTypes) {
        HashSet<String> usedNames = new HashSet<>();
        StscState state = StscState.get();
        for (SchemaType schemaType : schemaTypes) {
            SchemaTypeImpl sImpl = (SchemaTypeImpl) schemaType;
            QName topName = findTopName(sImpl);
            String pickedName = state.getJavaname(topName, sImpl.isDocumentType() ? 2 : 1);
            if (sImpl.isUnjavaized()) {
                sImpl.setFullJavaName(pickFullJavaClassName(usedNames, findTopName(sImpl), pickedName, sImpl.isDocumentType(), sImpl.isAttributeType()));
                sImpl.setFullJavaImplName(pickFullJavaImplName(usedNames, sImpl.getFullJavaName()));
                setExtensions(sImpl, state);
            }
        }
        setUserTypes(state);
        verifyInterfaceNameCollisions(usedNames, state);
    }

    private static void verifyInterfaceNameCollisions(Set<String> usedNames, StscState state) {
        BindingConfig config = state.getBindingConfig();
        if (config == null) {
            return;
        }
        InterfaceExtension[] exts = config.getInterfaceExtensions();
        for (InterfaceExtension ext : exts) {
            if (usedNames.contains(ext.getInterface().toLowerCase(Locale.ROOT))) {
                state.error("InterfaceExtension interface '" + ext.getInterface() + "' creates a name collision with one of the generated interfaces or classes.", 0, (XmlObject) null);
            }
            String handler = ext.getStaticHandler();
            if (handler != null && usedNames.contains(handler.toLowerCase(Locale.ROOT))) {
                state.error("InterfaceExtension handler class '" + handler + "' creates a name collision with one of the generated interfaces or classes.", 0, (XmlObject) null);
            }
        }
        PrePostExtension[] prepost = config.getPrePostExtensions();
        for (PrePostExtension prePostExtension : prepost) {
            String handler2 = prePostExtension.getStaticHandler();
            if (handler2 != null && usedNames.contains(handler2.toLowerCase(Locale.ROOT))) {
                state.error("PrePostExtension handler class '" + handler2 + "' creates a name collision with one of the generated interfaces or classes.", 0, (XmlObject) null);
            }
        }
    }

    private static void setUserTypes(StscState state) {
        BindingConfig config = state.getBindingConfig();
        if (config != null) {
            for (UserType utype : config.getUserTypes()) {
                SchemaTypeImpl sImpl = state.findGlobalType(utype.getName(), null, null);
                if (sImpl != null) {
                    sImpl.setUserTypeName(utype.getJavaName());
                    sImpl.setUserTypeHandlerName(utype.getStaticHandler());
                } else {
                    LOG.atWarn().log("Cannot match user type for {}", utype.getName());
                }
            }
        }
    }

    private static void setExtensions(SchemaTypeImpl sImpl, StscState state) {
        String javaName = sImpl.getFullJavaName();
        BindingConfig config = state.getBindingConfig();
        if (javaName != null && config != null) {
            sImpl.setInterfaceExtensions(config.getInterfaceExtensions(javaName));
            sImpl.setPrePostExtension(config.getPrePostExtension(javaName));
        }
    }

    private static boolean isStringType(SchemaType type) {
        return type != null && type.getSimpleVariety() == 1 && type.getPrimitiveType().getBuiltinTypeCode() == 12;
    }

    static String pickConstantName(Set<String> usedNames, String words) {
        String base = NameUtil.upperCaseUnderbar(words);
        if (base.isEmpty()) {
            base = "X";
        }
        if (base.startsWith("INT_")) {
            base = "X_" + base;
        }
        int index = 1;
        String uniqName = base;
        while (usedNames.contains(uniqName)) {
            index++;
            uniqName = base + "_" + index;
        }
        usedNames.add(uniqName);
        return uniqName;
    }

    static void skipJavaizingType(SchemaTypeImpl sImpl) {
        if (sImpl.isJavaized()) {
            return;
        }
        SchemaTypeImpl baseType = (SchemaTypeImpl) sImpl.getBaseType();
        if (baseType != null) {
            skipJavaizingType(baseType);
        }
        sImpl.startJavaizing();
        secondPassProcessType(sImpl);
        sImpl.finishJavaizing();
    }

    static void secondPassProcessType(SchemaTypeImpl sImpl) {
        XmlAnySimpleType[] enumVals;
        if (!isStringType(sImpl) || (enumVals = sImpl.getEnumerationValues()) == null) {
            return;
        }
        if (enumVals.length > MAX_ENUM_COUNT) {
            StscState.get().warning("SchemaType Enumeration found with too many enumeration values to create a Java enumeration. The base SchemaType \"" + sImpl.getBaseEnumType() + "\" will be used instead", 1, (XmlObject) null);
            return;
        }
        SchemaType basedOn = sImpl.getBaseEnumType();
        if (basedOn == null) {
            return;
        }
        SchemaStringEnumEntry[] entryArray = new SchemaStringEnumEntry[enumVals.length];
        if (basedOn == sImpl) {
            Set<String> usedNames = new HashSet<>();
            for (int i = 0; i < enumVals.length; i++) {
                String val = enumVals[i].getStringValue();
                entryArray[i] = new SchemaStringEnumEntryImpl(val, i + 1, pickConstantName(usedNames, val));
            }
        } else {
            for (int i2 = 0; i2 < enumVals.length; i2++) {
                entryArray[i2] = basedOn.enumEntryForString(enumVals[i2].getStringValue());
            }
        }
        sImpl.setStringEnumEntries(entryArray);
    }

    static void javaizeType(SchemaTypeImpl sImpl) {
        if (sImpl.isJavaized()) {
            return;
        }
        SchemaTypeImpl baseType = (SchemaTypeImpl) sImpl.getBaseType();
        if (baseType != null) {
            javaizeType(baseType);
        }
        if (sImpl.getContentBasedOnType() != null && sImpl.getContentBasedOnType() != baseType) {
            javaizeType((SchemaTypeImpl) sImpl.getContentBasedOnType());
        }
        sImpl.startJavaizing();
        sImpl.setCompiled(true);
        secondPassProcessType(sImpl);
        if (!sImpl.isSimpleType()) {
            SchemaProperty[] eltProps = sImpl.getElementProperties();
            SchemaProperty[] attrProps = sImpl.getAttributeProperties();
            Set<String> usedPropNames = new HashSet<>();
            SchemaProperty[] baseProps = baseType.getProperties();
            for (SchemaProperty baseProp : baseProps) {
                String name = baseProp.getJavaPropertyName();
                if (usedPropNames.contains(name)) {
                    throw new AssertionError();
                }
                usedPropNames.add(name);
            }
            avoidExtensionMethods(usedPropNames, sImpl);
            boolean doInherited = true;
            while (true) {
                if (eltProps.length > 0) {
                    assignJavaPropertyNames(usedPropNames, eltProps, baseType, doInherited);
                }
                assignJavaPropertyNames(usedPropNames, attrProps, baseType, doInherited);
                if (!doInherited) {
                    break;
                } else {
                    doInherited = false;
                }
            }
            SchemaProperty[] allprops = sImpl.getProperties();
            boolean insensitive = isPropertyModelOrderInsensitive(allprops);
            assignJavaTypeCodes(allprops);
            sImpl.setOrderSensitive(!insensitive);
        }
        if (sImpl.getFullJavaName() != null || sImpl.getOuterType() != null) {
            assignJavaAnonymousTypeNames(sImpl);
        }
        sImpl.finishJavaizing();
    }

    private static void avoidExtensionMethods(Set<String> usedPropNames, SchemaTypeImpl sImpl) {
        InterfaceExtension[] exts = sImpl.getInterfaceExtensions();
        if (exts != null) {
            for (InterfaceExtension ext : exts) {
                InterfaceExtension.MethodSignature[] methods = ext.getMethods();
                for (InterfaceExtension.MethodSignature method : methods) {
                    String methodName = method.getName();
                    for (String prefix : PREFIXES) {
                        if (methodName.startsWith(prefix)) {
                            usedPropNames.add(methodName.substring(prefix.length()));
                        }
                    }
                }
            }
        }
    }

    static void assignJavaAnonymousTypeNames(SchemaTypeImpl outerType) {
        String javaname;
        Set<String> usedTypeNames = new HashSet<>();
        SchemaType[] anonymousTypes = outerType.getAnonymousTypes();
        StscState state = StscState.get();
        int nrOfAnonTypes = anonymousTypes.length;
        if (outerType.isRedefinition()) {
            ArrayList<SchemaType> list = new ArrayList<>();
            addAnonymousTypesFromRedefinition(outerType, list);
            if (!list.isEmpty()) {
                SchemaType[] temp = new SchemaType[list.size() + nrOfAnonTypes];
                list.toArray(temp);
                System.arraycopy(anonymousTypes, 0, temp, list.size(), nrOfAnonTypes);
                anonymousTypes = temp;
            }
        }
        for (SchemaType scanOuterType = outerType; scanOuterType != null; scanOuterType = scanOuterType.getOuterType()) {
            usedTypeNames.add(scanOuterType.getShortJavaName());
        }
        for (SchemaType scanOuterType2 = outerType; scanOuterType2 != null; scanOuterType2 = scanOuterType2.getOuterType()) {
            usedTypeNames.add(scanOuterType2.getShortJavaImplName());
        }
        usedTypeNames.add(getOutermostPackage(outerType.getFullJavaName()));
        for (int i = 0; i < anonymousTypes.length; i++) {
            SchemaTypeImpl sImpl = (SchemaTypeImpl) anonymousTypes[i];
            if (sImpl != null && !sImpl.isSkippedAnonymousType()) {
                String localname = null;
                SchemaField containerField = sImpl.getContainerField();
                if (containerField != null) {
                    QName qname = sImpl.getContainerField().getName();
                    localname = qname.getLocalPart();
                    javaname = state.getJavaname(sImpl.getContainerField().getName(), 1);
                } else {
                    switch (sImpl.getOuterType().getSimpleVariety()) {
                        case 2:
                            javaname = "Member";
                            break;
                        case 3:
                            javaname = "Item";
                            break;
                        default:
                            throw new AssertionError("Weird type " + sImpl.toString());
                    }
                }
                if (i < nrOfAnonTypes) {
                    sImpl.setShortJavaName(pickInnerJavaClassName(usedTypeNames, localname, javaname));
                    sImpl.setShortJavaImplName(pickInnerJavaImplName(usedTypeNames, localname, javaname != null ? javaname + "Impl" : null));
                } else {
                    sImpl.setFullJavaName(outerType.getFullJavaName() + "$" + pickInnerJavaClassName(usedTypeNames, localname, javaname));
                    sImpl.setFullJavaImplName(outerType.getFullJavaImplName() + "$" + pickInnerJavaImplName(usedTypeNames, localname, javaname != null ? javaname + "Impl" : null));
                }
                setExtensions(sImpl, state);
            }
        }
    }

    static void assignJavaPropertyNames(Set<String> usedNames, SchemaProperty[] props, SchemaType baseType, boolean doInherited) {
        SchemaProperty baseProp;
        String theName;
        StscState state = StscState.get();
        for (SchemaProperty prop : props) {
            SchemaPropertyImpl sImpl = (SchemaPropertyImpl) prop;
            if (sImpl.isAttribute()) {
                baseProp = baseType.getAttributeProperty(sImpl.getName());
            } else {
                baseProp = baseType.getElementProperty(sImpl.getName());
            }
            if ((baseProp == null) != doInherited) {
                QName propQName = sImpl.getName();
                if (baseProp == null) {
                    theName = pickJavaPropertyName(usedNames, propQName.getLocalPart(), state.getJavaname(propQName, sImpl.isAttribute() ? 4 : 3));
                } else {
                    theName = baseProp.getJavaPropertyName();
                }
                sImpl.setJavaPropertyName(theName);
                boolean isArray = sImpl.getMaxOccurs() == null || sImpl.getMaxOccurs().compareTo(BigInteger.ONE) > 0;
                boolean isSingleton = !isArray && sImpl.getMaxOccurs().signum() > 0;
                boolean isOption = isSingleton && sImpl.getMinOccurs().signum() == 0;
                SchemaType javaBasedOnType = sImpl.getType();
                if (baseProp != null) {
                    if (baseProp.extendsJavaArray()) {
                        isSingleton = false;
                        isOption = false;
                        isArray = true;
                    }
                    if (baseProp.extendsJavaSingleton()) {
                        isSingleton = true;
                    }
                    if (baseProp.extendsJavaOption()) {
                        isOption = true;
                    }
                    javaBasedOnType = baseProp.javaBasedOnType();
                }
                sImpl.setExtendsJava(javaBasedOnType.getRef(), isSingleton, isOption, isArray);
            }
        }
    }

    static void assignJavaTypeCodes(SchemaProperty[] properties) {
        for (SchemaProperty property : properties) {
            SchemaPropertyImpl sImpl = (SchemaPropertyImpl) property;
            SchemaType sType = sImpl.javaBasedOnType();
            sImpl.setJavaTypeCode(javaTypeCodeForType(sType));
        }
    }

    static int javaTypeCodeInCommon(SchemaType[] types) {
        if (types == null || types.length == 0) {
            return 0;
        }
        int code = javaTypeCodeForType(types[0]);
        if (code == 19) {
            return code;
        }
        for (int i = 1; i < types.length; i++) {
            if (code != javaTypeCodeForType(types[i])) {
                return 19;
            }
        }
        return code;
    }

    static int javaTypeCodeForType(SchemaType sType) {
        if (!sType.isSimpleType()) {
            return 0;
        }
        if (((SchemaTypeImpl) sType).getUserTypeHandlerName() != null) {
            return 20;
        }
        if (sType.getSimpleVariety() == 2) {
            SchemaType baseType = sType.getUnionCommonBaseType();
            if (baseType != null && !baseType.isURType()) {
                sType = baseType;
            } else {
                return javaTypeCodeInCommon(sType.getUnionConstituentTypes());
            }
        }
        if (sType.getSimpleVariety() == 3) {
            return 16;
        }
        if (sType.isURType()) {
            return 0;
        }
        switch (sType.getPrimitiveType().getBuiltinTypeCode()) {
            case 2:
                return 10;
            case 3:
                return 1;
            case 4:
                return 11;
            case 5:
                return 11;
            case 6:
                return 10;
            case 7:
                return 15;
            case 8:
                return 0;
            case 9:
                return 2;
            case 10:
                return 3;
            case 11:
                switch (sType.getDecimalSize()) {
                    case 8:
                        return 4;
                    case 16:
                        return 5;
                    case 32:
                        return 6;
                    case 64:
                        return 7;
                    case 1000000:
                        return 9;
                    default:
                        return 8;
                }
            case 12:
                if (isStringType(sType.getBaseEnumType())) {
                    return (sType.getEnumerationValues() == null || sType.getEnumerationValues().length <= MAX_ENUM_COUNT) ? 18 : 10;
                }
                return 10;
            case 13:
                return 13;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 17;
            default:
                throw new AssertionError("unrecognized code " + sType.getPrimitiveType().getBuiltinTypeCode());
        }
    }

    static boolean isPropertyModelOrderInsensitive(SchemaProperty[] properties) {
        for (SchemaProperty prop : properties) {
            if (prop.hasNillable() == 1 || prop.hasDefault() == 1 || prop.hasFixed() == 1) {
                return false;
            }
            if (prop.hasDefault() != 0 && prop.getDefaultText() == null) {
                return false;
            }
        }
        return true;
    }

    static boolean protectReservedGlobalClassNames(String name) {
        int i = name.lastIndexOf(46);
        String lastSegment = name.substring(i + 1);
        return lastSegment.endsWith("Document") && !lastSegment.equals("Document");
    }

    static boolean protectReservedInnerClassNames(String name) {
        return name.equals("Enum") || name.equals("Factory");
    }

    static boolean protectReservedPropertyNames(String name) {
        return PROTECTED_PROPERTIES_SET.contains(name) || (name.endsWith(SoapEncSchemaTypeSystem.SOAP_ARRAY) && !name.equals(SoapEncSchemaTypeSystem.SOAP_ARRAY));
    }

    static String pickFullJavaClassName(Set<String> usedNames, QName qName, String configname, boolean isDocument, boolean isAttrType) {
        String base;
        boolean protect;
        String uniqName;
        if (configname != null && configname.indexOf(46) >= 0) {
            base = configname;
            protect = protectReservedGlobalClassNames(base);
        } else {
            StscState state = StscState.get();
            String uri = qName.getNamespaceURI();
            String base2 = NameUtil.getClassNameFromQName(qName);
            String pkgPrefix = state.getPackageOverride(uri);
            if (pkgPrefix != null) {
                base2 = pkgPrefix + "." + base2.substring(base2.lastIndexOf(46) + 1);
            }
            String javaPrefix = state.getJavaPrefix(uri);
            if (javaPrefix != null) {
                base2 = base2.substring(0, base2.lastIndexOf(46) + 1) + javaPrefix + base2.substring(base2.lastIndexOf(46) + 1);
            }
            if (configname != null) {
                base = base2.substring(0, base2.lastIndexOf(46) + 1) + configname;
            } else {
                base = base2;
            }
            boolean protect2 = protectReservedGlobalClassNames(base);
            if (configname != null) {
                protect = protect2;
            } else {
                if (isDocument) {
                    base = base + "Document";
                } else if (isAttrType) {
                    base = base + "Attribute";
                }
                String javaSuffix = state.getJavaSuffix(uri);
                if (javaSuffix == null) {
                    protect = protect2;
                } else {
                    base = base + javaSuffix;
                    protect = protect2;
                }
            }
        }
        String outermostPkg = getOutermostPackage(base);
        int index = 1;
        if (protect) {
            uniqName = base + 1;
        } else {
            uniqName = base;
        }
        while (true) {
            if (usedNames.contains(uniqName.toLowerCase(Locale.ROOT)) || uniqName.equals(outermostPkg)) {
                index++;
                uniqName = base + index;
            } else {
                usedNames.add(uniqName.toLowerCase(Locale.ROOT));
                return uniqName;
            }
        }
    }

    static String getOutermostPackage(String fqcn) {
        int lastdot;
        if (fqcn == null || (lastdot = fqcn.indexOf(46)) < 0) {
            return "";
        }
        return fqcn.substring(0, lastdot);
    }

    static String pickFullJavaImplName(Set<String> usedNames, String intfName) {
        String className = intfName;
        String pkgName = null;
        int index = intfName.lastIndexOf(46);
        if (index >= 0) {
            className = intfName.substring(index + 1);
            pkgName = intfName.substring(0, index);
        }
        String base = pkgName + ".impl." + className + "Impl";
        int index2 = 1;
        String uniqName = base;
        while (usedNames.contains(uniqName.toLowerCase(Locale.ROOT))) {
            index2++;
            uniqName = base + index2;
        }
        usedNames.add(uniqName.toLowerCase(Locale.ROOT));
        return uniqName;
    }

    static String pickJavaPropertyName(Set<String> usedNames, String localName, String javaName) {
        String uniqName;
        if (javaName == null) {
            javaName = NameUtil.upperCamelCase(localName);
        }
        boolean protect = protectReservedPropertyNames(javaName);
        int index = 1;
        if (protect) {
            uniqName = javaName + 1;
        } else {
            uniqName = javaName;
        }
        while (usedNames.contains(uniqName)) {
            index++;
            uniqName = javaName + index;
        }
        usedNames.add(uniqName);
        return uniqName;
    }

    static String pickInnerJavaClassName(Set<String> usedNames, String localName, String javaName) {
        String uniqName;
        if (javaName == null) {
            javaName = NameUtil.upperCamelCase(localName);
        }
        boolean protect = protectReservedInnerClassNames(javaName);
        int index = 1;
        if (protect) {
            uniqName = javaName + 1;
        } else {
            uniqName = javaName;
        }
        while (usedNames.contains(uniqName)) {
            index++;
            uniqName = javaName + index;
        }
        usedNames.add(uniqName);
        return uniqName;
    }

    static String pickInnerJavaImplName(Set<String> usedNames, String localName, String javaName) {
        if (javaName == null) {
            javaName = NameUtil.upperCamelCase(localName) + "Impl";
        }
        String uniqName = javaName;
        int index = 1;
        while (usedNames.contains(uniqName)) {
            index++;
            uniqName = javaName + index;
        }
        usedNames.add(uniqName);
        return uniqName;
    }

    static QName findTopName(SchemaType sType) {
        if (sType.getName() != null) {
            return sType.getName();
        }
        if (sType.isDocumentType()) {
            if (sType.getContentModel() == null || sType.getContentModel().getParticleType() != 4) {
                throw new IllegalStateException();
            }
            return sType.getDocumentElementName();
        }
        if (sType.isAttributeType()) {
            if (sType.getAttributeModel() == null || sType.getAttributeModel().getAttributes().length != 1) {
                throw new IllegalStateException();
            }
            return sType.getAttributeTypeAttributeName();
        }
        SchemaField sElt = sType.getContainerField();
        if (sElt == null) {
            throw new AssertionError();
        }
        if (sType.getOuterType() != null) {
            throw new AssertionError();
        }
        return sElt.getName();
    }

    static void addAnonymousTypesFromRedefinition(SchemaType sType, List<SchemaType> result) {
        while (((SchemaTypeImpl) sType).isRedefinition()) {
            if (sType.getDerivationType() == 2 || sType.isSimpleType()) {
                sType = sType.getBaseType();
                SchemaType[] newAnonTypes = sType.getAnonymousTypes();
                if (newAnonTypes.length > 0) {
                    result.addAll(Arrays.asList(newAnonTypes));
                }
            } else {
                return;
            }
        }
    }
}
