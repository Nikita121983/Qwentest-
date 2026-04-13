package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlByte;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlShort;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;

/* loaded from: classes11.dex */
public class StscSimpleTypeResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final RegularExpression[] EMPTY_REGEX_ARRAY = new RegularExpression[0];
    private static final CodeForNameEntry[] facetCodes = {new CodeForNameEntry(QNameHelper.forLNS("length", "http://www.w3.org/2001/XMLSchema"), 0), new CodeForNameEntry(QNameHelper.forLNS("minLength", "http://www.w3.org/2001/XMLSchema"), 1), new CodeForNameEntry(QNameHelper.forLNS("maxLength", "http://www.w3.org/2001/XMLSchema"), 2), new CodeForNameEntry(QNameHelper.forLNS("pattern", "http://www.w3.org/2001/XMLSchema"), 10), new CodeForNameEntry(QNameHelper.forLNS("enumeration", "http://www.w3.org/2001/XMLSchema"), 11), new CodeForNameEntry(QNameHelper.forLNS("whiteSpace", "http://www.w3.org/2001/XMLSchema"), 9), new CodeForNameEntry(QNameHelper.forLNS("maxInclusive", "http://www.w3.org/2001/XMLSchema"), 5), new CodeForNameEntry(QNameHelper.forLNS("maxExclusive", "http://www.w3.org/2001/XMLSchema"), 6), new CodeForNameEntry(QNameHelper.forLNS("minInclusive", "http://www.w3.org/2001/XMLSchema"), 4), new CodeForNameEntry(QNameHelper.forLNS("minExclusive", "http://www.w3.org/2001/XMLSchema"), 3), new CodeForNameEntry(QNameHelper.forLNS("totalDigits", "http://www.w3.org/2001/XMLSchema"), 7), new CodeForNameEntry(QNameHelper.forLNS("fractionDigits", "http://www.w3.org/2001/XMLSchema"), 8)};
    private static final Map<QName, Integer> facetCodeMap = buildFacetCodeMap();

    public static void resolveSimpleType(SchemaTypeImpl schemaTypeImpl) {
        SimpleType simpleType = (SimpleType) schemaTypeImpl.getParseObject();
        if (!schemaTypeImpl.isSimpleType()) {
            throw new AssertionError();
        }
        SchemaDocument.Schema schema = StscComplexTypeResolver.getSchema(simpleType);
        int i = (simpleType.isSetList() ? 1 : 0) + (simpleType.isSetUnion() ? 1 : 0) + (simpleType.isSetRestriction() ? 1 : 0);
        if (i > 1) {
            StscState.get().error("A simple type must define either a list, a union, or a restriction: more than one found.", 52, simpleType);
        } else if (i < 1) {
            StscState.get().error("A simple type must define either a list, a union, or a restriction: none was found.", 52, simpleType);
            resolveErrorSimpleType(schemaTypeImpl);
            return;
        }
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        Object obj = null;
        if (simpleType.isSetFinal()) {
            obj = simpleType.getFinal();
        } else if (schema != null && schema.isSetFinalDefault()) {
            obj = schema.getFinalDefault();
        }
        if (obj != null) {
            if (obj instanceof String) {
                if ("#all".equals(obj)) {
                    z3 = true;
                    z2 = true;
                    z = true;
                }
            } else if (obj instanceof List) {
                List list = (List) obj;
                if (list.contains("restriction")) {
                    z = true;
                }
                if (list.contains(XmlErrorCodes.LIST)) {
                    z2 = true;
                }
                if (list.contains(XmlErrorCodes.UNION)) {
                    z3 = true;
                }
            }
        }
        schemaTypeImpl.setSimpleFinal(z, z2, z3);
        ArrayList arrayList = new ArrayList();
        if (simpleType.getList() != null) {
            resolveListType(schemaTypeImpl, simpleType.getList(), arrayList);
        } else if (simpleType.getUnion() != null) {
            resolveUnionType(schemaTypeImpl, simpleType.getUnion(), arrayList);
        } else if (simpleType.getRestriction() != null) {
            resolveSimpleRestrictionType(schemaTypeImpl, simpleType.getRestriction(), arrayList);
        }
        schemaTypeImpl.setAnonymousTypeRefs(makeRefArray(arrayList));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaType.Ref[] lambda$makeRefArray$0(int x$0) {
        return new SchemaType.Ref[x$0];
    }

    private static SchemaType.Ref[] makeRefArray(List<? extends SchemaType> typeList) {
        return (SchemaType.Ref[]) typeList.stream().map(new StscComplexTypeResolver$$ExternalSyntheticLambda21()).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.StscSimpleTypeResolver$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return StscSimpleTypeResolver.lambda$makeRefArray$0(i);
            }
        });
    }

    static void resolveErrorSimpleType(SchemaTypeImpl sImpl) {
        sImpl.setSimpleTypeVariety(1);
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        sImpl.setPrimitiveTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
    }

    static void resolveListType(SchemaTypeImpl sImpl, ListDocument.List parseList, List<SchemaType> anonTypes) {
        LocalSimpleType parseInner;
        SchemaTypeImpl sImpl2;
        SchemaTypeImpl itemImpl;
        XmlObject errorLoc;
        SchemaTypeImpl sImpl3;
        StscState state = StscState.get();
        sImpl.setSimpleTypeVariety(3);
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        sImpl.setDerivationType(1);
        if (sImpl.isRedefinition()) {
            state.error(XmlErrorCodes.SCHEMA_REDEFINE$EXTEND_OR_RESTRICT, new Object[]{XmlErrorCodes.LIST}, parseList);
        }
        QName itemName = parseList.getItemType();
        LocalSimpleType parseInner2 = parseList.getSimpleType();
        if (itemName != null && parseInner2 != null) {
            state.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$LIST_HAS_BOTH_ITEM_OR_SIMPLE_TYPE, (Object[]) null, parseList);
            parseInner = null;
        } else {
            parseInner = parseInner2;
        }
        if (itemName != null) {
            itemImpl = state.findGlobalType(itemName, sImpl.getChameleonNamespace(), sImpl.getTargetNamespace());
            errorLoc = parseList.xgetItemType();
            if (itemImpl != null) {
                sImpl2 = sImpl;
            } else {
                state.notFoundError(itemName, 0, parseList.xgetItemType(), true);
                itemImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
                sImpl2 = sImpl;
            }
        } else if (parseInner == null) {
            state.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$LIST_HAS_NEITHER_ITEM_OR_SIMPLE_TYPE, (Object[]) null, parseList);
            resolveErrorSimpleType(sImpl);
            return;
        } else {
            sImpl2 = sImpl;
            itemImpl = StscTranslator.translateAnonymousSimpleType(parseInner, sImpl.getTargetNamespace(), sImpl.getChameleonNamespace() != null, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonTypes, sImpl2);
            errorLoc = parseInner;
        }
        if (itemImpl.finalList()) {
            state.error(XmlErrorCodes.SIMPLE_TYPE_PROPERTIES$LIST_FINAL, (Object[]) null, parseList);
        }
        StscResolver.resolveType(itemImpl);
        if (!itemImpl.isSimpleType()) {
            state.error(XmlErrorCodes.SIMPLE_TYPE_RESTRICTION$LIST_ITEM_NOT_SIMPLE, (Object[]) null, errorLoc);
            sImpl3 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        } else {
            sImpl3 = sImpl2;
        }
        switch (itemImpl.getSimpleVariety()) {
            case 1:
                break;
            case 2:
                if (itemImpl.isUnionOfLists()) {
                    state.error(XmlErrorCodes.SIMPLE_TYPE_RESTRICTION$LIST_ITEM_IS_UNION_OF_LIST, (Object[]) null, errorLoc);
                    resolveErrorSimpleType(sImpl3);
                    return;
                }
                break;
            case 3:
                state.error(XmlErrorCodes.SIMPLE_TYPE_RESTRICTION$LIST_ITEM_IS_LIST, (Object[]) null, errorLoc);
                resolveErrorSimpleType(sImpl3);
                return;
            default:
                throw new AssertionError();
        }
        sImpl3.setListItemTypeRef(itemImpl.getRef());
        if (sImpl3.getBuiltinTypeCode() == 8) {
            state.recover(XmlErrorCodes.DATATYPE_ENUM_NOTATION, null, errorLoc);
        }
        sImpl3.setBasicFacets(StscState.FACETS_LIST, StscState.FIXED_FACETS_LIST);
        sImpl3.setWhiteSpaceRule(3);
        resolveFundamentalFacets(sImpl3);
    }

    static void resolveUnionType(SchemaTypeImpl sImpl, UnionDocument.Union parseUnion, List<SchemaType> anonTypes) {
        XmlObject errorLoc;
        XmlObject errorLoc2;
        int i;
        boolean z;
        sImpl.setSimpleTypeVariety(2);
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getBaseDepth() + 1);
        sImpl.setDerivationType(1);
        StscState state = StscState.get();
        if (sImpl.isRedefinition()) {
            state.error(XmlErrorCodes.SCHEMA_REDEFINE$EXTEND_OR_RESTRICT, new Object[]{XmlErrorCodes.UNION}, parseUnion);
        }
        List<QName> memberTypes = parseUnion.getMemberTypes();
        SimpleType[] simpleTypes = parseUnion.getSimpleTypeArray();
        List<SchemaTypeImpl> memberImplList = new ArrayList<>();
        if (simpleTypes.length == 0 && (memberTypes == null || memberTypes.isEmpty())) {
            state.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$UNION_HAS_MEMBER_TYPES_OR_SIMPLE_TYPES, (Object[]) null, parseUnion);
        }
        if (memberTypes != null) {
            for (QName mName : memberTypes) {
                SchemaTypeImpl memberImpl = state.findGlobalType(mName, sImpl.getChameleonNamespace(), sImpl.getTargetNamespace());
                if (memberImpl == null) {
                    state.notFoundError(mName, 0, parseUnion.xgetMemberTypes(), true);
                } else {
                    memberImplList.add(memberImpl);
                }
            }
        }
        int i2 = 0;
        while (i2 < simpleTypes.length) {
            int i3 = i2;
            SimpleType simpleType = simpleTypes[i3];
            String targetNamespace = sImpl.getTargetNamespace();
            if (sImpl.getChameleonNamespace() != null) {
                i = i3;
                z = true;
            } else {
                i = i3;
                z = false;
            }
            int i4 = i;
            SchemaTypeImpl mImpl = StscTranslator.translateAnonymousSimpleType(simpleType, targetNamespace, z, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonTypes, sImpl);
            memberImplList.add(mImpl);
            mImpl.setAnonymousUnionMemberOrdinal(i4 + 1);
            i2 = i4 + 1;
        }
        Iterator<SchemaTypeImpl> mImpls = memberImplList.iterator();
        while (mImpls.hasNext()) {
            SchemaTypeImpl mImpl2 = mImpls.next();
            if (!StscResolver.resolveType(mImpl2)) {
                String memberName = "";
                if (Objects.equals(mImpl2.getOuterType(), sImpl)) {
                    errorLoc2 = mImpl2.getParseObject();
                } else {
                    memberName = QNameHelper.pretty(mImpl2.getName()) + StringUtils.SPACE;
                    errorLoc2 = parseUnion.xgetMemberTypes();
                }
                state.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$CYCLIC_UNION, new Object[]{memberName}, errorLoc2);
                mImpls.remove();
            }
        }
        boolean isUnionOfLists = false;
        Iterator<SchemaTypeImpl> mImpls2 = memberImplList.iterator();
        while (mImpls2.hasNext()) {
            SchemaTypeImpl mImpl3 = mImpls2.next();
            if (!mImpl3.isSimpleType()) {
                String memberName2 = "";
                if (mImpl3.getOuterType() != null && mImpl3.getOuterType().equals(sImpl)) {
                    errorLoc = mImpl3.getParseObject();
                } else {
                    memberName2 = QNameHelper.pretty(mImpl3.getName()) + StringUtils.SPACE;
                    errorLoc = parseUnion.xgetMemberTypes();
                }
                state.error(XmlErrorCodes.SIMPLE_TYPE_RESTRICTION$UNION_MEMBER_NOT_SIMPLE, new Object[]{memberName2}, errorLoc);
                mImpls2.remove();
            } else if (mImpl3.getSimpleVariety() == 3 || (mImpl3.getSimpleVariety() == 2 && mImpl3.isUnionOfLists())) {
                isUnionOfLists = true;
            }
        }
        for (SchemaTypeImpl schemaType : memberImplList) {
            if (schemaType.finalUnion()) {
                state.error(XmlErrorCodes.SIMPLE_TYPE_PROPERTIES$UNION_FINAL, (Object[]) null, parseUnion);
            }
        }
        sImpl.setUnionOfLists(isUnionOfLists);
        sImpl.setUnionMemberTypeRefs(makeRefArray(memberImplList));
        sImpl.setBasicFacets(StscState.FACETS_UNION, StscState.FIXED_FACETS_UNION);
        resolveFundamentalFacets(sImpl);
    }

    static void resolveSimpleRestrictionType(SchemaTypeImpl sImpl, RestrictionDocument.Restriction parseRestr, List<SchemaType> anonTypes) {
        SimpleType parseInner;
        SchemaTypeImpl sImpl2;
        SchemaTypeImpl baseImpl;
        QName baseName = parseRestr.getBase();
        SimpleType parseInner2 = parseRestr.getSimpleType();
        StscState state = StscState.get();
        if (baseName != null && parseInner2 != null) {
            state.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$RESTRICTION_HAS_BOTH_BASE_OR_SIMPLE_TYPE, (Object[]) null, parseRestr);
            parseInner = null;
        } else {
            parseInner = parseInner2;
        }
        if (baseName != null) {
            if (sImpl.isRedefinition()) {
                baseImpl = state.findRedefinedGlobalType(parseRestr.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseImpl != null && !baseImpl.getName().equals(sImpl.getName())) {
                    state.error(XmlErrorCodes.SCHEMA_REDEFINE$SAME_TYPE, new Object[]{"<simpleType>", QNameHelper.pretty(baseName), QNameHelper.pretty(sImpl.getName())}, parseRestr);
                }
            } else {
                baseImpl = state.findGlobalType(baseName, sImpl.getChameleonNamespace(), sImpl.getTargetNamespace());
            }
            if (baseImpl != null) {
                sImpl2 = sImpl;
            } else {
                state.notFoundError(baseName, 0, parseRestr.xgetBase(), true);
                baseImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
                sImpl2 = sImpl;
            }
        } else if (parseInner != null) {
            if (sImpl.isRedefinition()) {
                StscState.get().error(XmlErrorCodes.SCHEMA_REDEFINE$EXTEND_OR_RESTRICT, new Object[]{"<simpleType>"}, parseInner);
            }
            sImpl2 = sImpl;
            baseImpl = StscTranslator.translateAnonymousSimpleType(parseInner, sImpl.getTargetNamespace(), sImpl.getChameleonNamespace() != null, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonTypes, sImpl2);
        } else {
            sImpl2 = sImpl;
            state.error(XmlErrorCodes.SCHEMA_SIMPLE_TYPE$RESTRICTION_HAS_NEITHER_BASE_OR_SIMPLE_TYPE, (Object[]) null, parseRestr);
            baseImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (!StscResolver.resolveType(baseImpl)) {
            baseImpl = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (baseImpl.finalRestriction()) {
            state.error(XmlErrorCodes.SIMPLE_TYPE_PROPERTIES$RESTRICTION_FINAL, (Object[]) null, parseRestr);
        }
        sImpl2.setBaseTypeRef(baseImpl.getRef());
        sImpl2.setBaseDepth(baseImpl.getBaseDepth() + 1);
        sImpl2.setDerivationType(1);
        if (!baseImpl.isSimpleType()) {
            state.error(XmlErrorCodes.SIMPLE_TYPE_RESTRICTION$ATOMIC_NOT_SIMPLE, (Object[]) null, parseRestr.xgetBase());
            resolveErrorSimpleType(sImpl2);
            return;
        }
        sImpl2.setSimpleTypeVariety(baseImpl.getSimpleVariety());
        switch (baseImpl.getSimpleVariety()) {
            case 1:
                sImpl2.setPrimitiveTypeRef(baseImpl.getPrimitiveType().getRef());
                break;
            case 2:
                sImpl2.setUnionOfLists(baseImpl.isUnionOfLists());
                sImpl2.setUnionMemberTypeRefs(makeRefArray(Arrays.asList(baseImpl.getUnionMemberTypes())));
                break;
            case 3:
                sImpl2.setListItemTypeRef(baseImpl.getListItemType().getRef());
                break;
        }
        resolveFacets(sImpl2, parseRestr, baseImpl);
        resolveFundamentalFacets(sImpl2);
    }

    static int translateWhitespaceCode(XmlAnySimpleType value) {
        String textval = value.getStringValue();
        if (textval.equals("collapse")) {
            return 3;
        }
        if (textval.equals("preserve")) {
            return 1;
        }
        if (textval.equals("replace")) {
            return 2;
        }
        StscState.get().error("Unrecognized whitespace value \"" + textval + "\"", 20, value);
        return 0;
    }

    static boolean isMultipleFacet(int facetcode) {
        return facetcode == 11 || facetcode == 10;
    }

    static boolean facetAppliesToType(int facetCode, SchemaTypeImpl baseImpl) {
        switch (baseImpl.getSimpleVariety()) {
            case 2:
                switch (facetCode) {
                    case 10:
                    case 11:
                        return true;
                    default:
                        return false;
                }
            case 3:
                switch (facetCode) {
                    case 0:
                    case 1:
                    case 2:
                    case 9:
                    case 10:
                    case 11:
                        return true;
                    default:
                        return false;
                }
            default:
                switch (baseImpl.getPrimitiveType().getBuiltinTypeCode()) {
                    case 2:
                        return false;
                    case 3:
                        switch (facetCode) {
                            case 9:
                            case 10:
                                return true;
                            default:
                                return false;
                        }
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 12:
                        switch (facetCode) {
                            case 0:
                            case 1:
                            case 2:
                            case 9:
                            case 10:
                            case 11:
                                return true;
                            default:
                                return false;
                        }
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
                        switch (facetCode) {
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 9:
                            case 10:
                            case 11:
                                return true;
                            case 7:
                            case 8:
                            default:
                                return false;
                        }
                    case 11:
                        switch (facetCode) {
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                                return true;
                            default:
                                return false;
                        }
                    default:
                        throw new AssertionError();
                }
        }
    }

    private static int other_similar_limit(int facetcode) {
        switch (facetcode) {
            case 3:
                return 4;
            case 4:
                return 3;
            case 5:
                return 6;
            case 6:
                return 5;
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:129:0x0295, code lost:
    
        if (r0 == r5) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x0348, code lost:
    
        if (r0.compareTo(r26[0]) < 0) goto L189;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:49:0x00df. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:221:0x03f3 A[Catch: all -> 0x0405, TryCatch #2 {all -> 0x0405, blocks: (B:13:0x03fa, B:224:0x00e4, B:226:0x00f0, B:238:0x011d, B:240:0x012f, B:50:0x015d, B:61:0x018d, B:63:0x0197, B:64:0x019c, B:66:0x01a0, B:68:0x01a8, B:69:0x01b1, B:71:0x01b7, B:73:0x01bf, B:74:0x01c5, B:76:0x01c9, B:78:0x01d1, B:79:0x01d7, B:80:0x01db, B:82:0x01e5, B:83:0x01ec, B:85:0x01f0, B:87:0x01f8, B:88:0x0201, B:90:0x0205, B:92:0x020d, B:93:0x0213, B:94:0x0217, B:96:0x021f, B:107:0x023d, B:110:0x0244, B:111:0x0249, B:113:0x024d, B:115:0x0255, B:116:0x025e, B:118:0x0262, B:120:0x0268, B:122:0x026e, B:124:0x0277, B:125:0x0287, B:134:0x02a8, B:140:0x02b1, B:144:0x02c2, B:147:0x02c6, B:148:0x02d4, B:149:0x02e2, B:150:0x02f0, B:156:0x0300, B:158:0x030a, B:159:0x0311, B:161:0x0315, B:163:0x031d, B:164:0x0326, B:166:0x032a, B:168:0x0330, B:172:0x0339, B:175:0x0342, B:177:0x034a, B:178:0x0350, B:180:0x0356, B:182:0x035e, B:183:0x0366, B:185:0x036c, B:187:0x0374, B:188:0x037c, B:189:0x0380, B:191:0x038a, B:192:0x0390, B:194:0x0394, B:196:0x039c, B:197:0x03a4, B:199:0x03a9, B:201:0x03af, B:203:0x03b9, B:205:0x03bf, B:206:0x03c4, B:208:0x03c9, B:210:0x03cf, B:212:0x03d7, B:216:0x03ea, B:217:0x03e0, B:236:0x0106, B:250:0x0146, B:219:0x03ed, B:221:0x03f3), top: B:223:0x00e4, inners: #9, #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:222:0x03f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void resolveFacets(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r29, org.apache.xmlbeans.XmlObject r30, org.apache.xmlbeans.impl.schema.SchemaTypeImpl r31) {
        /*
            Method dump skipped, instructions count: 1286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscSimpleTypeResolver.resolveFacets(org.apache.xmlbeans.impl.schema.SchemaTypeImpl, org.apache.xmlbeans.XmlObject, org.apache.xmlbeans.impl.schema.SchemaTypeImpl):void");
    }

    private static XmlValueRef[] makeValueRefArray(XmlAnySimpleType[] source) {
        XmlValueRef[] result = new XmlValueRef[source.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = source[i] == null ? null : new XmlValueRef(source[i]);
        }
        return result;
    }

    private static boolean isDiscreteType(SchemaTypeImpl sImpl) {
        if (sImpl.getFacet(8) != null) {
            return true;
        }
        switch (sImpl.getPrimitiveType().getBuiltinTypeCode()) {
            case 3:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return true;
            default:
                return false;
        }
    }

    private static boolean isNumericPrimitive(SchemaType sImpl) {
        switch (sImpl.getBuiltinTypeCode()) {
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    private static int decimalSizeOfType(SchemaTypeImpl sImpl) {
        int size = mathematicalSizeOfType(sImpl);
        if (size == 8 && !XmlByte.type.isAssignableFrom(sImpl)) {
            size = 16;
        }
        if (size == 16 && !XmlShort.type.isAssignableFrom(sImpl) && !XmlUnsignedByte.type.isAssignableFrom(sImpl)) {
            return 32;
        }
        return size;
    }

    private static int mathematicalSizeOfType(SchemaTypeImpl sImpl) {
        if (sImpl.getPrimitiveType().getBuiltinTypeCode() != 11) {
            return 0;
        }
        if (sImpl.getFacet(8) == null || ((SimpleValue) sImpl.getFacet(8)).getBigIntegerValue().signum() != 0) {
            return SchemaType.SIZE_BIG_DECIMAL;
        }
        BigInteger min = null;
        BigInteger max = null;
        if (sImpl.getFacet(3) != null) {
            min = ((SimpleValue) sImpl.getFacet(3)).getBigIntegerValue();
        }
        if (sImpl.getFacet(4) != null) {
            min = ((SimpleValue) sImpl.getFacet(4)).getBigIntegerValue();
        }
        if (sImpl.getFacet(5) != null) {
            max = ((SimpleValue) sImpl.getFacet(5)).getBigIntegerValue();
        }
        if (sImpl.getFacet(6) != null) {
            max = ((SimpleValue) sImpl.getFacet(6)).getBigIntegerValue();
        }
        if (sImpl.getFacet(7) != null) {
            BigInteger peg = null;
            try {
                BigInteger totalDigits = ((SimpleValue) sImpl.getFacet(7)).getBigIntegerValue();
                switch (totalDigits.intValue()) {
                    case 0:
                    case 1:
                    case 2:
                        peg = BigInteger.valueOf(99L);
                        break;
                    case 3:
                    case 4:
                        peg = BigInteger.valueOf(9999L);
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        peg = BigInteger.valueOf(999999999L);
                        break;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                        peg = BigInteger.valueOf(999999999999999999L);
                        break;
                }
            } catch (XmlValueOutOfRangeException e) {
            }
            if (peg != null) {
                BigInteger negate = peg.negate();
                if (min != null) {
                    negate = min.max(negate);
                }
                min = negate;
                max = max == null ? peg : max.min(peg);
            }
        }
        if (min != null && max != null) {
            if (min.signum() < 0) {
                min = min.negate().subtract(BigInteger.ONE);
            }
            if (max.signum() < 0) {
                max = max.negate().subtract(BigInteger.ONE);
            }
            BigInteger max2 = max.max(min);
            if (max2.compareTo(BigInteger.valueOf(127L)) <= 0) {
                return 8;
            }
            if (max2.compareTo(BigInteger.valueOf(32767L)) <= 0) {
                return 16;
            }
            if (max2.compareTo(BigInteger.valueOf(2147483647L)) <= 0) {
                return 32;
            }
            if (max2.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0) {
                return 64;
            }
            return 1000000;
        }
        return 1000000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void resolveFundamentalFacets(SchemaTypeImpl sImpl) {
        switch (sImpl.getSimpleVariety()) {
            case 1:
                SchemaTypeImpl baseImpl = (SchemaTypeImpl) sImpl.getBaseType();
                sImpl.setOrdered(baseImpl.ordered());
                sImpl.setBounded(((sImpl.getFacet(3) == null && sImpl.getFacet(4) == null) || (sImpl.getFacet(5) == null && sImpl.getFacet(6) == null)) ? false : true);
                sImpl.setFinite(baseImpl.isFinite() || (sImpl.isBounded() && isDiscreteType(sImpl)));
                if (!baseImpl.isNumeric() && !isNumericPrimitive(sImpl.getPrimitiveType())) {
                    r1 = false;
                }
                sImpl.setNumeric(r1);
                sImpl.setDecimalSize(decimalSizeOfType(sImpl));
                return;
            case 2:
                SchemaType[] mTypes = sImpl.getUnionMemberTypes();
                int ordered = 0;
                boolean isBounded = true;
                boolean isFinite = true;
                boolean isNumeric = true;
                for (SchemaType mType : mTypes) {
                    if (mType.ordered() != 0) {
                        ordered = 1;
                    }
                    if (!mType.isBounded()) {
                        isBounded = false;
                    }
                    if (!mType.isFinite()) {
                        isFinite = false;
                    }
                    if (!mType.isNumeric()) {
                        isNumeric = false;
                    }
                }
                sImpl.setOrdered(ordered);
                sImpl.setBounded(isBounded);
                sImpl.setFinite(isFinite);
                sImpl.setNumeric(isNumeric);
                sImpl.setDecimalSize(0);
                return;
            case 3:
                sImpl.setOrdered(0);
                sImpl.setBounded((sImpl.getFacet(0) == null && sImpl.getFacet(2) == null) ? false : true);
                sImpl.setFinite(sImpl.getListItemType().isFinite() && sImpl.isBounded());
                sImpl.setNumeric(false);
                sImpl.setDecimalSize(0);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class CodeForNameEntry {
        public int code;
        public QName name;

        CodeForNameEntry(QName name, int code) {
            this.name = name;
            this.code = code;
        }
    }

    private static Map<QName, Integer> buildFacetCodeMap() {
        Map<QName, Integer> result = new HashMap<>();
        for (CodeForNameEntry facetCode : facetCodes) {
            result.put(facetCode.name, Integer.valueOf(facetCode.code));
        }
        return result;
    }

    private static int translateFacetCode(QName name) {
        return facetCodeMap.getOrDefault(name, -1).intValue();
    }
}
