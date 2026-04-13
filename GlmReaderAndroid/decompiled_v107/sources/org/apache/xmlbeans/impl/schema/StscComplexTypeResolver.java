package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.namespace.QName;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.QNameSetBuilder;
import org.apache.xmlbeans.QNameSetSpecification;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.schema.StscComplexTypeResolver;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupRef;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexRestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.Group;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NamespaceList;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleRestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.Wildcard;

/* loaded from: classes11.dex */
public class StscComplexTypeResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ANY_ATTRIBUTE_CODE = 102;
    private static final int ATTRIBUTE_CODE = 100;
    private static final int ATTRIBUTE_GROUP_CODE = 101;
    private static final int MODEL_GROUP_CODE = 100;
    private static final CodeForNameEntry[] particleCodes = {new CodeForNameEntry(QNameHelper.forLNS("all", "http://www.w3.org/2001/XMLSchema"), 1), new CodeForNameEntry(QNameHelper.forLNS("sequence", "http://www.w3.org/2001/XMLSchema"), 3), new CodeForNameEntry(QNameHelper.forLNS("choice", "http://www.w3.org/2001/XMLSchema"), 2), new CodeForNameEntry(QNameHelper.forLNS("element", "http://www.w3.org/2001/XMLSchema"), 4), new CodeForNameEntry(QNameHelper.forLNS(Languages.ANY, "http://www.w3.org/2001/XMLSchema"), 5), new CodeForNameEntry(QNameHelper.forLNS("group", "http://www.w3.org/2001/XMLSchema"), 100)};
    private static final Map<QName, Integer> particleCodeMap = Collections.unmodifiableMap((Map) Stream.of((Object[]) particleCodes).collect(Collectors.toMap(new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda10
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            QName qName;
            qName = ((StscComplexTypeResolver.CodeForNameEntry) obj).name;
            return qName;
        }
    }, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda12
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            Integer valueOf;
            valueOf = Integer.valueOf(((StscComplexTypeResolver.CodeForNameEntry) obj).code);
            return valueOf;
        }
    })));
    private static final CodeForNameEntry[] attributeCodes = {new CodeForNameEntry(QNameHelper.forLNS("attribute", "http://www.w3.org/2001/XMLSchema"), 100), new CodeForNameEntry(QNameHelper.forLNS("attributeGroup", "http://www.w3.org/2001/XMLSchema"), 101), new CodeForNameEntry(QNameHelper.forLNS("anyAttribute", "http://www.w3.org/2001/XMLSchema"), 102)};
    private static final Map<QName, Integer> attributeCodeMap = Collections.unmodifiableMap((Map) Stream.of((Object[]) attributeCodes).collect(Collectors.toMap(new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda13
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            QName qName;
            qName = ((StscComplexTypeResolver.CodeForNameEntry) obj).name;
            return qName;
        }
    }, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda14
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            Integer valueOf;
            valueOf = Integer.valueOf(((StscComplexTypeResolver.CodeForNameEntry) obj).code);
            return valueOf;
        }
    })));

    public static /* synthetic */ LinkedHashMap $r8$lambda$9jZrLfmN7qos7GZMvkzgNgs9WjU() {
        return new LinkedHashMap();
    }

    public static Group getContentModel(final ComplexType parseCt) {
        parseCt.getClass();
        parseCt.getClass();
        parseCt.getClass();
        parseCt.getClass();
        return getContentModel((Supplier<? extends Group>[]) new Supplier[]{new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda0
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexType.this.getAll();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda11
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexType.this.getSequence();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda19
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexType.this.getChoice();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda20
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexType.this.getGroup();
            }
        }});
    }

    public static Group getContentModel(final ComplexRestrictionType parseRest) {
        parseRest.getClass();
        parseRest.getClass();
        parseRest.getClass();
        parseRest.getClass();
        return getContentModel((Supplier<? extends Group>[]) new Supplier[]{new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda23
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexRestrictionType.this.getAll();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda24
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexRestrictionType.this.getSequence();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda25
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexRestrictionType.this.getChoice();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda26
            @Override // java.util.function.Supplier
            public final Object get() {
                return ComplexRestrictionType.this.getGroup();
            }
        }});
    }

    public static Group getContentModel(final ExtensionType parseExt) {
        parseExt.getClass();
        parseExt.getClass();
        parseExt.getClass();
        parseExt.getClass();
        return getContentModel((Supplier<? extends Group>[]) new Supplier[]{new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda1
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtensionType.this.getAll();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtensionType.this.getSequence();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda3
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtensionType.this.getChoice();
            }
        }, new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda4
            @Override // java.util.function.Supplier
            public final Object get() {
                return ExtensionType.this.getGroup();
            }
        }});
    }

    @SafeVarargs
    private static Group getContentModel(Supplier<? extends Group>... grps) {
        return (Group) Stream.of((Object[]) grps).map(new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda5
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (Group) ((Supplier) obj).get();
            }
        }).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((Group) obj);
                return nonNull;
            }
        }).findFirst().orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SchemaDocument.Schema getSchema(XmlObject o) {
        XmlCursor c = o.newCursor();
        while (c.toParent()) {
            try {
                XmlObject o2 = c.getObject();
                if (o2.schemaType().equals(SchemaDocument.Schema.type)) {
                    SchemaDocument.Schema schema = (SchemaDocument.Schema) o2;
                    if (c != null) {
                        c.close();
                    }
                    return schema;
                }
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
        if (c != null) {
            c.close();
            return null;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void resolveComplexType(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r22) {
        /*
            Method dump skipped, instructions count: 428
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver.resolveComplexType(org.apache.xmlbeans.impl.schema.SchemaTypeImpl):void");
    }

    static void resolveErrorType(SchemaTypeImpl sImpl) {
        throw new RuntimeException("This type of error recovery not yet implemented.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaType.Ref[] lambda$makeRefArray$0(int x$0) {
        return new SchemaType.Ref[x$0];
    }

    private static SchemaType.Ref[] makeRefArray(Collection<SchemaType> typeList) {
        return (SchemaType.Ref[]) typeList.stream().map(new StscComplexTypeResolver$$ExternalSyntheticLambda21()).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda22
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return StscComplexTypeResolver.lambda$makeRefArray$0(i);
            }
        });
    }

    static void resolveBasicComplexType(SchemaTypeImpl sImpl) {
        List<SchemaType> anonymousTypes = new ArrayList<>();
        ComplexType parseTree = (ComplexType) sImpl.getParseObject();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean chameleon = sImpl.getChameleonNamespace() != null;
        Group parseGroup = getContentModel(parseTree);
        if (sImpl.isRedefinition()) {
            StscState.get().error(XmlErrorCodes.SCHEMA_REDEFINE$EXTEND_OR_RESTRICT, new Object[]{"<complexType>"}, parseTree);
        }
        int particleCode = translateParticleCode(parseGroup);
        SchemaParticle contentModel = translateContentModel(sImpl, parseGroup, targetNamespace, chameleon, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), particleCode, anonymousTypes, new LinkedHashMap(), false, null);
        boolean isAll = contentModel != null && contentModel.getParticleType() == 1;
        SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl();
        translateAttributeModel(parseTree, targetNamespace, chameleon, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel, null, true, null);
        WildcardResult wcElt = summarizeEltWildcards(contentModel);
        WildcardResult wcAttr = summarizeAttrWildcards(attrModel);
        if (contentModel != null) {
            buildStateMachine(contentModel);
            if (!StscState.get().noUpa() && !((SchemaParticleImpl) contentModel).isDeterministic()) {
                StscState.get().error(XmlErrorCodes.UNIQUE_PARTICLE_ATTRIBUTION, (Object[]) null, parseGroup);
            }
        }
        Map<QName, SchemaProperty> elementPropertyModel = buildContentPropertyModelByQName(contentModel, sImpl);
        Map<QName, SchemaProperty> attributePropertyModel = buildAttributePropertyModelByQName(attrModel, sImpl);
        int complexVariety = parseTree.getMixed() ? 4 : contentModel == null ? 1 : 3;
        sImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getRef());
        sImpl.setBaseDepth(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getBaseDepth() + 1);
        sImpl.setDerivationType(2);
        sImpl.setComplexTypeVariety(complexVariety);
        sImpl.setContentModel(contentModel, attrModel, elementPropertyModel, attributePropertyModel, isAll);
        sImpl.setAnonymousTypeRefs(makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(wcElt.typedWildcards, wcElt.hasWildcards, wcAttr.typedWildcards, wcAttr.hasWildcards);
    }

    static void resolveCcRestriction(SchemaTypeImpl sImpl, ComplexRestrictionType parseTree, boolean mixed) {
        SchemaTypeImpl baseType;
        SchemaTypeImpl baseType2;
        SchemaAttributeModelImpl attrModel;
        int i;
        StscState state = StscState.get();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean chameleon = sImpl.getChameleonNamespace() != null;
        if (parseTree.getBase() == null) {
            state.error("A complexContent must define a base type", 28, parseTree);
            baseType = null;
        } else {
            if (sImpl.isRedefinition()) {
                baseType = state.findRedefinedGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseType != null && !baseType.getName().equals(sImpl.getName())) {
                    state.error(XmlErrorCodes.SCHEMA_REDEFINE$SAME_TYPE, new Object[]{"<complexType>", QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, parseTree);
                }
            } else {
                baseType = state.findGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), targetNamespace);
            }
            if (baseType == null) {
                state.notFoundError(parseTree.getBase(), 0, parseTree.xgetBase(), true);
            }
        }
        if (baseType == null) {
            baseType = BuiltinSchemaTypeSystem.ST_ANY_TYPE;
        }
        if (baseType != null && baseType.finalRestriction()) {
            state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$FINAL, new Object[]{QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, parseTree.xgetBase());
        }
        if (baseType != null && !StscResolver.resolveType(baseType)) {
            baseType2 = null;
        } else {
            baseType2 = baseType;
        }
        List<SchemaType> anonymousTypes = new ArrayList<>();
        boolean chameleon2 = chameleon;
        Group parseEg = getContentModel(parseTree);
        int particleCode = translateParticleCode(parseEg);
        SchemaParticle contentModel = translateContentModel(sImpl, parseEg, targetNamespace, chameleon2, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), particleCode, anonymousTypes, new LinkedHashMap(), false, null);
        boolean isAll = contentModel != null && contentModel.getParticleType() == 1;
        if (baseType2 == null) {
            attrModel = new SchemaAttributeModelImpl();
        } else {
            attrModel = new SchemaAttributeModelImpl(baseType2.getAttributeModel());
        }
        SchemaTypeImpl baseType3 = baseType2;
        SchemaAttributeModelImpl attrModel2 = attrModel;
        translateAttributeModel(parseTree, targetNamespace, chameleon2, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel2, baseType3, false, null);
        WildcardResult wcElt = summarizeEltWildcards(contentModel);
        WildcardResult wcAttr = summarizeAttrWildcards(attrModel2);
        if (contentModel != null) {
            buildStateMachine(contentModel);
            if (!StscState.get().noUpa() && !((SchemaParticleImpl) contentModel).isDeterministic()) {
                StscState.get().error(XmlErrorCodes.UNIQUE_PARTICLE_ATTRIBUTION, (Object[]) null, parseEg);
            }
        }
        Map<QName, SchemaProperty> elementPropertyModel = buildContentPropertyModelByQName(contentModel, sImpl);
        Map<QName, SchemaProperty> attributePropertyModel = buildAttributePropertyModelByQName(attrModel2, sImpl);
        int complexVariety = mixed ? 4 : contentModel == null ? 1 : 3;
        if (baseType3 == null) {
            i = 1;
        } else {
            i = 1;
            sImpl.setBaseTypeRef(baseType3.getRef());
            sImpl.setBaseDepth(baseType3.getBaseDepth() + 1);
        }
        sImpl.setDerivationType(i);
        sImpl.setComplexTypeVariety(complexVariety);
        sImpl.setContentModel(contentModel, attrModel2, elementPropertyModel, attributePropertyModel, isAll);
        sImpl.setAnonymousTypeRefs(makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(wcElt.typedWildcards, wcElt.hasWildcards, wcAttr.typedWildcards, wcAttr.hasWildcards);
    }

    static Map<QName, SchemaType> extractElementModel(SchemaType sType) {
        if (sType == null) {
            return new HashMap();
        }
        return (Map) Stream.of((Object[]) sType.getProperties()).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda16
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return StscComplexTypeResolver.lambda$extractElementModel$1((SchemaProperty) obj);
            }
        }).collect(Collectors.toMap(new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda17
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaProperty) obj).getName();
            }
        }, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda18
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaProperty) obj).getType();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$extractElementModel$1(SchemaProperty p) {
        return !p.isAttribute();
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0220  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void resolveCcExtension(org.apache.xmlbeans.impl.schema.SchemaTypeImpl r23, org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType r24, boolean r25) {
        /*
            Method dump skipped, instructions count: 605
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver.resolveCcExtension(org.apache.xmlbeans.impl.schema.SchemaTypeImpl, org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType, boolean):void");
    }

    static void resolveScRestriction(SchemaTypeImpl sImpl, SimpleRestrictionType parseTree) {
        SchemaTypeImpl schemaTypeImpl;
        List<SchemaType> anonymousTypes;
        SchemaTypeImpl baseType;
        SchemaTypeImpl contentType;
        SchemaTypeImpl baseType2;
        SchemaAttributeModelImpl attrModel;
        SchemaTypeImpl contentType2 = null;
        StscState state = StscState.get();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean chameleon = sImpl.getChameleonNamespace() != null;
        List<SchemaType> anonymousTypes2 = new ArrayList<>();
        if (parseTree.getSimpleType() == null) {
            schemaTypeImpl = sImpl;
            anonymousTypes = anonymousTypes2;
        } else {
            LocalSimpleType typedef = parseTree.getSimpleType();
            anonymousTypes = anonymousTypes2;
            contentType2 = StscTranslator.translateAnonymousSimpleType(typedef, targetNamespace, chameleon, sImpl.getElemFormDefault(), sImpl.getAttFormDefault(), anonymousTypes, sImpl);
            schemaTypeImpl = sImpl;
        }
        if (parseTree.getBase() == null) {
            state.error("A simpleContent restriction must define a base type", 28, parseTree);
            baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        } else if (schemaTypeImpl.isRedefinition()) {
            baseType = state.findRedefinedGlobalType(parseTree.getBase(), schemaTypeImpl.getChameleonNamespace(), schemaTypeImpl);
            if (baseType != null && !baseType.getName().equals(schemaTypeImpl.getName())) {
                state.error(XmlErrorCodes.SCHEMA_REDEFINE$SAME_TYPE, new Object[]{"<simpleType>", QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(schemaTypeImpl.getName())}, parseTree);
            }
        } else {
            baseType = state.findGlobalType(parseTree.getBase(), schemaTypeImpl.getChameleonNamespace(), targetNamespace);
        }
        if (baseType == null) {
            state.notFoundError(parseTree.getBase(), 0, parseTree.xgetBase(), true);
            baseType = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        StscResolver.resolveType(baseType);
        if (contentType2 != null) {
            StscResolver.resolveType(contentType2);
            contentType = contentType2;
        } else {
            SchemaTypeImpl contentType3 = baseType;
            contentType = contentType3;
        }
        if (baseType.isSimpleType()) {
            state.recover(XmlErrorCodes.COMPLEX_TYPE_PROPERTIES$SIMPLE_TYPE_EXTENSION, new Object[]{QNameHelper.pretty(baseType.getName())}, parseTree);
            SchemaTypeImpl baseType3 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            baseType2 = baseType3;
        } else {
            baseType2 = baseType;
        }
        if (baseType2 != null && baseType2.finalRestriction()) {
            state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$FINAL, new Object[]{QNameHelper.pretty(baseType2.getName()), QNameHelper.pretty(schemaTypeImpl.getName())}, parseTree.xgetBase());
        }
        if (baseType2 == null) {
            attrModel = new SchemaAttributeModelImpl();
        } else {
            attrModel = new SchemaAttributeModelImpl(baseType2.getAttributeModel());
        }
        SchemaAttributeModelImpl attrModel2 = attrModel;
        List<SchemaType> anonymousTypes3 = anonymousTypes;
        SchemaTypeImpl schemaTypeImpl2 = schemaTypeImpl;
        translateAttributeModel(parseTree, targetNamespace, chameleon, schemaTypeImpl.getAttFormDefault(), anonymousTypes3, schemaTypeImpl2, null, attrModel2, baseType2, false, null);
        SchemaTypeImpl baseType4 = baseType2;
        WildcardResult wcAttr = summarizeAttrWildcards(attrModel2);
        Map<QName, SchemaProperty> attributePropertyModel = buildAttributePropertyModelByQName(attrModel2, schemaTypeImpl2);
        if (baseType4 != null) {
            schemaTypeImpl2.setBaseTypeRef(baseType4.getRef());
            schemaTypeImpl2.setBaseDepth(baseType4.getBaseDepth() + 1);
        }
        schemaTypeImpl2.setContentBasedOnTypeRef(contentType.getRef());
        schemaTypeImpl2.setDerivationType(1);
        schemaTypeImpl2.setAnonymousTypeRefs(makeRefArray(anonymousTypes3));
        schemaTypeImpl2.setWildcardSummary(QNameSet.EMPTY, false, wcAttr.typedWildcards, wcAttr.hasWildcards);
        schemaTypeImpl2.setComplexTypeVariety(2);
        schemaTypeImpl2.setContentModel(null, attrModel2, null, attributePropertyModel, false);
        schemaTypeImpl2.setSimpleTypeVariety(contentType.getSimpleVariety());
        schemaTypeImpl2.setPrimitiveTypeRef(contentType.getPrimitiveType() == null ? null : contentType.getPrimitiveType().getRef());
        switch (schemaTypeImpl2.getSimpleVariety()) {
            case 2:
                schemaTypeImpl2.setUnionMemberTypeRefs(makeRefArray(Arrays.asList(contentType.getUnionMemberTypes())));
                break;
            case 3:
                SchemaType lit = contentType.getListItemType();
                if (lit != null) {
                    schemaTypeImpl2.setListItemTypeRef(lit.getRef());
                    break;
                }
                break;
        }
        StscSimpleTypeResolver.resolveFacets(schemaTypeImpl2, parseTree, contentType);
        StscSimpleTypeResolver.resolveFundamentalFacets(schemaTypeImpl2);
    }

    static void resolveScExtension(SchemaTypeImpl sImpl, SimpleExtensionType parseTree) {
        SchemaTypeImpl baseType;
        SchemaTypeImpl baseType2;
        StscState state = StscState.get();
        String targetNamespace = sImpl.getTargetNamespace();
        boolean chameleon = sImpl.getChameleonNamespace() != null;
        if (parseTree.getBase() == null) {
            state.error("A simpleContent extension must define a base type", 28, parseTree);
            baseType2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        } else {
            if (sImpl.isRedefinition()) {
                baseType = state.findRedefinedGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), sImpl);
                if (baseType != null && !baseType.getName().equals(sImpl.getName())) {
                    state.error(XmlErrorCodes.SCHEMA_REDEFINE$SAME_TYPE, new Object[]{"<simpleType>", QNameHelper.pretty(baseType.getName()), QNameHelper.pretty(sImpl.getName())}, parseTree);
                }
            } else {
                baseType = state.findGlobalType(parseTree.getBase(), sImpl.getChameleonNamespace(), targetNamespace);
            }
            if (baseType != null) {
                baseType2 = baseType;
            } else {
                state.notFoundError(parseTree.getBase(), 0, parseTree.xgetBase(), true);
                baseType2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            }
        }
        StscResolver.resolveType(baseType2);
        if (!baseType2.isSimpleType() && baseType2.getContentType() != 2) {
            state.error(XmlErrorCodes.SCHEMA_COMPLEX_TYPE$SIMPLE_CONTENT, new Object[]{QNameHelper.pretty(baseType2.getName())}, parseTree);
            baseType2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (baseType2 != null && baseType2.finalExtension()) {
            state.error(XmlErrorCodes.COMPLEX_TYPE_EXTENSION$FINAL, new Object[]{QNameHelper.pretty(baseType2.getName()), QNameHelper.pretty(sImpl.getName())}, parseTree.xgetBase());
        }
        resolveScExtensionPart2(sImpl, baseType2, parseTree, targetNamespace, chameleon);
    }

    static void resolveScExtensionPart2(SchemaTypeImpl sImpl, SchemaType baseType, ExtensionType parseTree, String targetNamespace, boolean chameleon) {
        List<SchemaType> anonymousTypes = new ArrayList<>();
        SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl(baseType.getAttributeModel());
        translateAttributeModel(parseTree, targetNamespace, chameleon, sImpl.getAttFormDefault(), anonymousTypes, sImpl, null, attrModel, baseType, true, null);
        WildcardResult wcAttr = summarizeAttrWildcards(attrModel);
        Map<QName, SchemaProperty> attributePropertyModel = buildAttributePropertyModelByQName(attrModel, sImpl);
        sImpl.setBaseTypeRef(baseType.getRef());
        sImpl.setBaseDepth(((SchemaTypeImpl) baseType).getBaseDepth() + 1);
        sImpl.setContentBasedOnTypeRef(baseType.getRef());
        sImpl.setDerivationType(2);
        sImpl.setAnonymousTypeRefs(makeRefArray(anonymousTypes));
        sImpl.setWildcardSummary(QNameSet.EMPTY, false, wcAttr.typedWildcards, wcAttr.hasWildcards);
        sImpl.setComplexTypeVariety(2);
        sImpl.setContentModel(null, attrModel, null, attributePropertyModel, false);
        sImpl.setSimpleTypeVariety(baseType.getSimpleVariety());
        sImpl.setPrimitiveTypeRef(baseType.getPrimitiveType() == null ? null : baseType.getPrimitiveType().getRef());
        switch (sImpl.getSimpleVariety()) {
            case 2:
                sImpl.setUnionMemberTypeRefs(makeRefArray(Arrays.asList(baseType.getUnionMemberTypes())));
                break;
            case 3:
                sImpl.setListItemTypeRef(baseType.getListItemType().getRef());
                break;
        }
        StscSimpleTypeResolver.resolveFacets(sImpl, null, (SchemaTypeImpl) baseType);
        StscSimpleTypeResolver.resolveFundamentalFacets(sImpl);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class WildcardResult {
        boolean hasWildcards;
        QNameSet typedWildcards;

        WildcardResult(QNameSet typedWildcards, boolean hasWildcards) {
            this.typedWildcards = typedWildcards;
            this.hasWildcards = hasWildcards;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WildcardResult summarizeAttrWildcards(SchemaAttributeModel attrModel) {
        if (attrModel.getWildcardProcess() == 0) {
            return new WildcardResult(QNameSet.EMPTY, false);
        }
        if (attrModel.getWildcardProcess() == 3) {
            return new WildcardResult(QNameSet.EMPTY, true);
        }
        return new WildcardResult(attrModel.getWildcardSet(), true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WildcardResult summarizeEltWildcards(SchemaParticle contentModel) {
        if (contentModel == null) {
            return new WildcardResult(QNameSet.EMPTY, false);
        }
        switch (contentModel.getParticleType()) {
            case 1:
            case 2:
            case 3:
                QNameSetBuilder set = new QNameSetBuilder();
                boolean hasWildcards = false;
                for (int i = 0; i < contentModel.countOfParticleChild(); i++) {
                    WildcardResult inner = summarizeEltWildcards(contentModel.getParticleChild(i));
                    set.addAll(inner.typedWildcards);
                    hasWildcards |= inner.hasWildcards;
                }
                return new WildcardResult(set.toQNameSet(), hasWildcards);
            case 4:
            default:
                return new WildcardResult(QNameSet.EMPTY, false);
            case 5:
                return new WildcardResult(contentModel.getWildcardProcess() == 3 ? QNameSet.EMPTY : contentModel.getWildcardSet(), true);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:16:0x003d. Please report as an issue. */
    static void translateAttributeModel(XmlObject parseTree, String targetNamespace, boolean chameleon, String formDefault, List<SchemaType> anonymousTypes, SchemaType outerType, Set<QName> seenAttributes, SchemaAttributeModelImpl result, SchemaType baseType, boolean extension, SchemaAttributeGroupImpl redefinitionFor) {
        SchemaAttributeModel baseModel;
        Throwable th;
        SchemaAttributeModel baseModel2;
        SchemaAttributeGroupImpl group;
        boolean seenRedefinition;
        SchemaAttributeGroupImpl nestedRedefinitionFor;
        SchemaAttributeGroupImpl nestedRedefinitionFor2;
        boolean chameleon2;
        String str = targetNamespace;
        StscState state = StscState.get();
        Set<QName> seenAttributes2 = seenAttributes == null ? new HashSet() : seenAttributes;
        if (baseType != null) {
            SchemaAttributeModel baseModel3 = baseType.getAttributeModel();
            baseModel = baseModel3;
        } else {
            baseModel = null;
        }
        XmlCursor cur = parseTree.newCursor();
        try {
            boolean more = cur.toFirstChild();
            boolean seenWildcard = false;
            boolean seenRedefinition2 = false;
            boolean seenRedefinition3 = chameleon;
            while (true) {
                if (!more) {
                    if (cur != null) {
                        cur.close();
                    }
                    if (extension || seenWildcard) {
                        return;
                    }
                    result.setWildcardSet(null);
                    result.setWildcardProcess(0);
                    return;
                }
                try {
                    switch (translateAttributeCode(cur.getName())) {
                        case 100:
                            Attribute xsdattr = (Attribute) cur.getObject();
                            SchemaAttributeModel baseModel4 = baseModel;
                            try {
                                SchemaLocalAttribute sAttr = StscTranslator.translateAttribute(xsdattr, str, formDefault, seenRedefinition3, anonymousTypes, outerType, baseModel4, true);
                                baseModel2 = baseModel4;
                                if (sAttr != null) {
                                    try {
                                        if (seenAttributes2.contains(sAttr.getName())) {
                                            state.error(XmlErrorCodes.COMPLEX_TYPE_PROPERTIES$DUPLICATE_ATTRIBUTE, new Object[]{QNameHelper.pretty(sAttr.getName()), QNameHelper.pretty(outerType.getName())}, xsdattr.xgetName());
                                        } else {
                                            seenAttributes2.add(sAttr.getName());
                                            if (baseModel2 != null) {
                                                SchemaLocalAttribute baseAttr = baseModel2.getAttribute(sAttr.getName());
                                                if (baseAttr == null) {
                                                    if (!extension && !baseModel2.getWildcardSet().contains(sAttr.getName())) {
                                                        state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ATTR_IN_BASE_WILDCARD_SET, new Object[]{QNameHelper.pretty(sAttr.getName()), QNameHelper.pretty(outerType.getName())}, xsdattr);
                                                    }
                                                } else if (extension) {
                                                    if (sAttr.getUse() == 1) {
                                                        state.error("An extension cannot prohibit an attribute from the base type; use restriction instead.", 37, xsdattr.xgetUse());
                                                    }
                                                } else if (sAttr.getUse() != 3) {
                                                    if (baseAttr.getUse() == 3) {
                                                        state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ATTR_REQUIRED, new Object[]{QNameHelper.pretty(sAttr.getName()), QNameHelper.pretty(outerType.getName())}, xsdattr);
                                                    }
                                                    if (sAttr.getUse() == 1) {
                                                        result.removeProhibitedAttribute(sAttr.getName());
                                                    }
                                                }
                                            }
                                            if (sAttr.getUse() != 1) {
                                                result.addAttribute(sAttr);
                                            } else {
                                                SchemaType attrType = sAttr.getType();
                                                if (anonymousTypes != null) {
                                                    anonymousTypes.remove(attrType);
                                                }
                                            }
                                            if (sAttr.getDefaultText() != null && !sAttr.isFixed() && sAttr.getUse() != 2) {
                                                state.error(XmlErrorCodes.SCHEMA_ATTR$DEFAULT_AND_USE_OPTIONAL, new Object[]{QNameHelper.pretty(sAttr.getName())}, xsdattr);
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        try {
                                            throw th;
                                        } finally {
                                        }
                                    }
                                }
                                more = cur.toNextSibling();
                                baseModel = baseModel2;
                                str = targetNamespace;
                            } catch (Throwable th3) {
                                th = th3;
                                throw th;
                            }
                            break;
                        case 101:
                            AttributeGroupRef xsdag = (AttributeGroupRef) cur.getObject();
                            QName ref = xsdag.getRef();
                            if (ref == null) {
                                state.error("Attribute group reference must have a ref attribute", 39, xsdag);
                                baseModel2 = baseModel;
                            } else {
                                if (redefinitionFor != null) {
                                    group = state.findRedefinedAttributeGroup(ref, seenRedefinition3 ? str : null, redefinitionFor);
                                    if (group == null || !redefinitionFor.getName().equals(group.getName())) {
                                        seenRedefinition = seenRedefinition2;
                                    } else {
                                        if (seenRedefinition2) {
                                            state.error(XmlErrorCodes.SCHEMA_REDEFINE$ATTR_GROUP_SELF_REF, new Object[]{QNameHelper.pretty(redefinitionFor.getName())}, xsdag);
                                        }
                                        seenRedefinition = true;
                                    }
                                } else {
                                    group = state.findAttributeGroup(ref, seenRedefinition3 ? str : null, str);
                                    seenRedefinition = seenRedefinition2;
                                }
                                if (group == null) {
                                    try {
                                        state.notFoundError(ref, 4, xsdag.xgetRef(), true);
                                    } catch (Throwable th4) {
                                        th = th4;
                                        throw th;
                                    }
                                } else if (state.isProcessing(group)) {
                                    state.error(XmlErrorCodes.SCHEMA_ATTR_GROUP$SELF_REF, new Object[]{QNameHelper.pretty(group.getName())}, group.getParseObject());
                                } else {
                                    String subTargetNamespace = targetNamespace;
                                    if (group.getTargetNamespace() != null) {
                                        subTargetNamespace = group.getTargetNamespace();
                                        seenRedefinition3 = group.getChameleonNamespace() != null;
                                    }
                                    try {
                                        state.startProcessing(group);
                                        if (group.isRedefinition()) {
                                            SchemaAttributeGroupImpl nestedRedefinitionFor3 = group;
                                            nestedRedefinitionFor = nestedRedefinitionFor3;
                                        } else {
                                            nestedRedefinitionFor = null;
                                        }
                                        nestedRedefinitionFor2 = group;
                                        chameleon2 = seenRedefinition3;
                                    } catch (Throwable th5) {
                                        th = th5;
                                    }
                                    try {
                                        translateAttributeModel(nestedRedefinitionFor2.getParseObject(), subTargetNamespace, chameleon2, nestedRedefinitionFor2.getFormDefault(), anonymousTypes, outerType, seenAttributes2, result, baseType, extension, nestedRedefinitionFor);
                                        state.finishProcessing(nestedRedefinitionFor2);
                                        seenRedefinition3 = chameleon2;
                                        baseModel2 = baseModel;
                                        seenRedefinition2 = seenRedefinition;
                                    } catch (Throwable th6) {
                                        th = th6;
                                        throw th;
                                    }
                                }
                                baseModel2 = baseModel;
                                seenRedefinition2 = seenRedefinition;
                            }
                            more = cur.toNextSibling();
                            baseModel = baseModel2;
                            str = targetNamespace;
                            break;
                        case 102:
                            try {
                                Wildcard xsdwc = (Wildcard) cur.getObject();
                                if (seenWildcard) {
                                    state.error("Only one attribute wildcard allowed", 38, xsdwc);
                                    baseModel2 = baseModel;
                                } else {
                                    try {
                                        NamespaceList nsList = xsdwc.xgetNamespace();
                                        String nsText = nsList == null ? "##any" : nsList.getStringValue();
                                        QNameSet wcset = QNameSet.forWildcardNamespaceString(nsText, str);
                                        if (baseModel != null && !extension) {
                                            if (baseModel.getWildcardSet() == null) {
                                                state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$BASE_HAS_ATTR_WILDCARD, (Object[]) null, xsdwc);
                                            } else if (!baseModel.getWildcardSet().containsAll(wcset)) {
                                                state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ATTR_WILDCARD_SUBSET, new Object[]{nsText}, xsdwc);
                                            }
                                            seenWildcard = true;
                                            baseModel2 = baseModel;
                                        }
                                        int wcprocess = translateWildcardProcess(xsdwc.xgetProcessContents());
                                        if (result.getWildcardProcess() == 0) {
                                            result.setWildcardSet(wcset);
                                            result.setWildcardProcess(wcprocess);
                                        } else if (extension) {
                                            result.setWildcardSet(wcset.union(result.getWildcardSet()));
                                            result.setWildcardProcess(wcprocess);
                                        } else {
                                            result.setWildcardSet(wcset.intersect(result.getWildcardSet()));
                                        }
                                        seenWildcard = true;
                                        baseModel2 = baseModel;
                                    } catch (Throwable th7) {
                                        th = th7;
                                        throw th;
                                    }
                                }
                                more = cur.toNextSibling();
                                baseModel = baseModel2;
                                str = targetNamespace;
                            } catch (Throwable th8) {
                                th = th8;
                            }
                        default:
                            baseModel2 = baseModel;
                            more = cur.toNextSibling();
                            baseModel = baseModel2;
                            str = targetNamespace;
                    }
                } catch (Throwable th9) {
                    th = th9;
                }
            }
        } catch (Throwable th10) {
            th = th10;
        }
    }

    static SchemaParticle extendContentModel(SchemaParticle baseContentModel, SchemaParticle extendedContentModel, XmlObject parseTree) {
        if (extendedContentModel == null) {
            return baseContentModel;
        }
        if (baseContentModel == null) {
            return extendedContentModel;
        }
        SchemaParticleImpl sPart = new SchemaParticleImpl();
        sPart.setParticleType(3);
        List<SchemaParticle> accumulate = new ArrayList<>();
        addMinusPointlessParticles(accumulate, baseContentModel, 3);
        addMinusPointlessParticles(accumulate, extendedContentModel, 3);
        sPart.setMinOccurs(BigInteger.ONE);
        sPart.setMaxOccurs(BigInteger.ONE);
        sPart.setParticleChildren((SchemaParticle[]) accumulate.toArray(new SchemaParticle[0]));
        return filterPointlessParticlesAndVerifyAllParticles(sPart, parseTree);
    }

    static BigInteger extractMinOccurs(XmlNonNegativeInteger nni) {
        if (nni == null) {
            return BigInteger.ONE;
        }
        BigInteger result = nni.getBigIntegerValue();
        if (result == null) {
            return BigInteger.ONE;
        }
        return result;
    }

    static BigInteger extractMaxOccurs(AllNNI allNNI) {
        if (allNNI == null) {
            return BigInteger.ONE;
        }
        if (allNNI.instanceType().getPrimitiveType().getBuiltinTypeCode() == 11) {
            return ((XmlInteger) allNNI).getBigIntegerValue();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class RedefinitionForGroup {
        private final SchemaModelGroupImpl group;
        private boolean seenRedefinition = false;

        public RedefinitionForGroup(SchemaModelGroupImpl group) {
            this.group = group;
        }

        public SchemaModelGroupImpl getGroup() {
            return this.group;
        }

        public boolean isSeenRedefinition() {
            return this.seenRedefinition;
        }

        public void setSeenRedefinition(boolean seenRedefinition) {
            this.seenRedefinition = seenRedefinition;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:141:0x01cc, code lost:
    
        r1 = r14.getObject();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4, types: [org.apache.xmlbeans.SchemaParticle] */
    /* JADX WARN: Type inference failed for: r17v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static org.apache.xmlbeans.SchemaParticle translateContentModel(org.apache.xmlbeans.SchemaType r22, org.apache.xmlbeans.XmlObject r23, java.lang.String r24, boolean r25, java.lang.String r26, java.lang.String r27, int r28, java.util.List<org.apache.xmlbeans.SchemaType> r29, java.util.Map<javax.xml.namespace.QName, org.apache.xmlbeans.SchemaType> r30, boolean r31, org.apache.xmlbeans.impl.schema.StscComplexTypeResolver.RedefinitionForGroup r32) {
        /*
            Method dump skipped, instructions count: 944
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver.translateContentModel(org.apache.xmlbeans.SchemaType, org.apache.xmlbeans.XmlObject, java.lang.String, boolean, java.lang.String, java.lang.String, int, java.util.List, java.util.Map, boolean, org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$RedefinitionForGroup):org.apache.xmlbeans.SchemaParticle");
    }

    static int translateWildcardProcess(Wildcard.ProcessContents process) {
        if (process == null) {
            return 1;
        }
        String processValue = process.getStringValue();
        if ("lax".equals(processValue)) {
            return 2;
        }
        if (!"skip".equals(processValue)) {
            return 1;
        }
        return 3;
    }

    static SchemaParticle filterPointlessParticlesAndVerifyAllParticles(SchemaParticle part, XmlObject parseTree) {
        if (part.getMaxOccurs() != null && part.getMaxOccurs().signum() == 0) {
            return null;
        }
        switch (part.getParticleType()) {
            case 1:
            case 3:
                if (part.getParticleChildren().length == 0) {
                    return null;
                }
                if (part.isSingleton() && part.countOfParticleChild() == 1) {
                    return part.getParticleChild(0);
                }
                break;
            case 2:
                if (part.getParticleChildren().length == 0 && part.getMinOccurs().compareTo(BigInteger.ZERO) == 0) {
                    return null;
                }
                if (part.isSingleton() && part.countOfParticleChild() == 1) {
                    return part.getParticleChild(0);
                }
                break;
            case 4:
            case 5:
                return part;
            default:
                throw new AssertionError();
        }
        boolean isAll = part.getParticleType() == 1;
        if (isAll && (part.getMaxOccurs() == null || part.getMaxOccurs().compareTo(BigInteger.ONE) > 0)) {
            StscState.get().error(XmlErrorCodes.ALL_GROUP_LIMITED$IN_MIN_MAX_1_PARTICLE, (Object[]) null, parseTree);
        }
        for (int i = 0; i < part.countOfParticleChild(); i++) {
            SchemaParticle child = part.getParticleChild(i);
            if (child.getParticleType() == 1) {
                StscState.get().error(XmlErrorCodes.ALL_GROUP_LIMITED$IN_COMPLEX_TYPE_DEF_PARTICLE, (Object[]) null, parseTree);
            } else if (isAll && (child.getParticleType() != 4 || child.getMaxOccurs() == null || child.getMaxOccurs().compareTo(BigInteger.ONE) > 0)) {
                StscState.get().error(XmlErrorCodes.ALL_GROUP_LIMITED$CHILD_PARTICLES_MAX_LTE_1, (Object[]) null, parseTree);
            }
        }
        return part;
    }

    static void addMinusPointlessParticles(List<SchemaParticle> list, SchemaParticle part, int parentParticleType) {
        if (part == null) {
            return;
        }
        switch (part.getParticleType()) {
            case 2:
                if (parentParticleType == 2 && part.isSingleton()) {
                    list.addAll(Arrays.asList(part.getParticleChildren()));
                    return;
                }
                break;
            case 3:
                if (parentParticleType == 3 && part.isSingleton()) {
                    list.addAll(Arrays.asList(part.getParticleChildren()));
                    return;
                }
                break;
        }
        list.add(part);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$throwingMerger$2(Object u, Object v) {
        throw new IllegalStateException("Duplicate key " + u.toString());
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return new BinaryOperator() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda15
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return StscComplexTypeResolver.lambda$throwingMerger$2(obj, obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<QName, SchemaProperty> buildAttributePropertyModelByQName(SchemaAttributeModel attrModel, final SchemaType owner) {
        return (Map) Stream.of((Object[]) attrModel.getAttributes()).collect(Collectors.toMap(new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda7
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SchemaLocalAttribute) obj).getName();
            }
        }, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                SchemaProperty buildUseProperty;
                buildUseProperty = StscComplexTypeResolver.buildUseProperty((SchemaLocalAttribute) obj, SchemaType.this);
                return buildUseProperty;
            }
        }, throwingMerger(), new Supplier() { // from class: org.apache.xmlbeans.impl.schema.StscComplexTypeResolver$$ExternalSyntheticLambda9
            @Override // java.util.function.Supplier
            public final Object get() {
                return StscComplexTypeResolver.$r8$lambda$9jZrLfmN7qos7GZMvkzgNgs9WjU();
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<QName, SchemaProperty> buildContentPropertyModelByQName(SchemaParticle part, SchemaType owner) {
        if (part == null) {
            return Collections.emptyMap();
        }
        boolean asSequence = false;
        Map<QName, SchemaProperty> model = null;
        switch (part.getParticleType()) {
            case 1:
            case 3:
                asSequence = true;
                break;
            case 2:
                asSequence = false;
                break;
            case 4:
                model = buildElementPropertyModel((SchemaLocalElement) part, owner);
                break;
            case 5:
                model = Collections.emptyMap();
                break;
            default:
                throw new AssertionError();
        }
        if (model == null) {
            model = new LinkedHashMap();
            SchemaParticle[] children = part.getParticleChildren();
            for (SchemaParticle child : children) {
                Map<QName, SchemaProperty> childModel = buildContentPropertyModelByQName(child, owner);
                for (SchemaProperty iProp : childModel.values()) {
                    SchemaPropertyImpl oProp = (SchemaPropertyImpl) model.get(iProp.getName());
                    if (oProp == null) {
                        if (!asSequence) {
                            ((SchemaPropertyImpl) iProp).setMinOccurs(BigInteger.ZERO);
                        }
                        model.put(iProp.getName(), iProp);
                    } else {
                        if (!oProp.getType().equals(iProp.getType())) {
                            throw new AssertionError();
                        }
                        mergeProperties(oProp, iProp, asSequence);
                    }
                }
            }
            BigInteger min = part.getMinOccurs();
            BigInteger max = part.getMaxOccurs();
            for (SchemaProperty oProp2 : model.values()) {
                BigInteger minOccurs = oProp2.getMinOccurs();
                BigInteger maxOccurs = oProp2.getMaxOccurs();
                BigInteger minOccurs2 = minOccurs.multiply(min);
                if (max != null && max.equals(BigInteger.ZERO)) {
                    maxOccurs = BigInteger.ZERO;
                } else if (maxOccurs != null && !maxOccurs.equals(BigInteger.ZERO)) {
                    maxOccurs = max == null ? null : maxOccurs.multiply(max);
                }
                ((SchemaPropertyImpl) oProp2).setMinOccurs(minOccurs2);
                ((SchemaPropertyImpl) oProp2).setMaxOccurs(maxOccurs);
            }
        }
        return model;
    }

    static Map<QName, SchemaProperty> buildElementPropertyModel(SchemaLocalElement epart, SchemaType owner) {
        SchemaProperty sProp = buildUseProperty(epart, owner);
        return Collections.singletonMap(sProp.getName(), sProp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SchemaProperty buildUseProperty(SchemaField use, SchemaType owner) {
        SchemaPropertyImpl sPropImpl = new SchemaPropertyImpl();
        sPropImpl.setName(use.getName());
        sPropImpl.setContainerTypeRef(owner.getRef());
        sPropImpl.setTypeRef(use.getType().getRef());
        sPropImpl.setAttribute(use.isAttribute());
        sPropImpl.setDefault(use.isDefault() ? 2 : 0);
        sPropImpl.setFixed(use.isFixed() ? 2 : 0);
        sPropImpl.setNillable(use.isNillable() ? 2 : 0);
        sPropImpl.setDefaultText(use.getDefaultText());
        sPropImpl.setMinOccurs(use.getMinOccurs());
        sPropImpl.setMaxOccurs(use.getMaxOccurs());
        if (use instanceof SchemaParticle) {
            sPropImpl.setDocumentation(((SchemaParticle) use).getDocumentation());
        }
        if (use instanceof SchemaLocalElementImpl) {
            SchemaLocalElementImpl elt = (SchemaLocalElementImpl) use;
            sPropImpl.setAcceptedNames(elt.acceptedStartNames());
        }
        return sPropImpl;
    }

    static void mergeProperties(SchemaPropertyImpl into, SchemaProperty from, boolean asSequence) {
        BigInteger minOccurs;
        BigInteger max;
        BigInteger add;
        BigInteger minOccurs2 = into.getMinOccurs();
        BigInteger maxOccurs = into.getMaxOccurs();
        if (asSequence) {
            minOccurs = minOccurs2.add(from.getMinOccurs());
            if (maxOccurs != null) {
                if (from.getMaxOccurs() == null) {
                    add = null;
                } else {
                    add = maxOccurs.add(from.getMaxOccurs());
                }
                maxOccurs = add;
            }
        } else {
            minOccurs = minOccurs2.min(from.getMinOccurs());
            if (maxOccurs != null) {
                if (from.getMaxOccurs() == null) {
                    max = null;
                } else {
                    max = maxOccurs.max(from.getMaxOccurs());
                }
                maxOccurs = max;
            }
        }
        into.setMinOccurs(minOccurs);
        into.setMaxOccurs(maxOccurs);
        if (from.hasNillable() != into.hasNillable()) {
            into.setNillable(1);
        }
        if (from.hasDefault() != into.hasDefault()) {
            into.setDefault(1);
        }
        if (from.hasFixed() != into.hasFixed()) {
            into.setFixed(1);
        }
        if (into.getDefaultText() != null) {
            if (from.getDefaultText() == null || !into.getDefaultText().equals(from.getDefaultText())) {
                into.setDefaultText(null);
            }
        }
    }

    static SchemaParticle[] ensureStateMachine(SchemaParticle[] children) {
        for (SchemaParticle child : children) {
            buildStateMachine(child);
        }
        return children;
    }

    static void buildStateMachine(SchemaParticle contentModel) {
        if (contentModel == null) {
            return;
        }
        SchemaParticleImpl partImpl = (SchemaParticleImpl) contentModel;
        if (partImpl.hasTransitionNotes()) {
            return;
        }
        QNameSetBuilder start = new QNameSetBuilder();
        QNameSetBuilder excludenext = new QNameSetBuilder();
        boolean deterministic = true;
        boolean canskip = partImpl.getMinOccurs().signum() == 0;
        switch (partImpl.getParticleType()) {
            case 1:
                SchemaParticle[] children = ensureStateMachine(partImpl.getParticleChildren());
                canskip = true;
                int length = children.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (children[i].isSkippable()) {
                            i++;
                        } else {
                            canskip = false;
                        }
                    }
                }
                int length2 = children.length;
                int i2 = 0;
                while (true) {
                    if (i2 < length2) {
                        if (((SchemaParticleImpl) children[i2]).isDeterministic()) {
                            i2++;
                        } else {
                            deterministic = false;
                        }
                    }
                }
                for (SchemaParticle child : children) {
                    if (deterministic && !start.isDisjoint(child.acceptedStartNames())) {
                        deterministic = false;
                    }
                    start.addAll(child.acceptedStartNames());
                    excludenext.addAll(((SchemaParticleImpl) child).getExcludeNextSet());
                }
                if (canskip) {
                    excludenext.addAll(start);
                    break;
                }
                break;
            case 2:
                SchemaParticle[] children2 = ensureStateMachine(partImpl.getParticleChildren());
                canskip = false;
                int length3 = children2.length;
                int i3 = 0;
                while (true) {
                    if (i3 < length3) {
                        SchemaParticle schemaParticle = children2[i3];
                        if (!schemaParticle.isSkippable()) {
                            i3++;
                        } else {
                            canskip = true;
                        }
                    }
                }
                int length4 = children2.length;
                int i4 = 0;
                while (true) {
                    if (i4 < length4) {
                        if (((SchemaParticleImpl) children2[i4]).isDeterministic()) {
                            i4++;
                        } else {
                            deterministic = false;
                        }
                    }
                }
                for (SchemaParticle child2 : children2) {
                    if (deterministic && !start.isDisjoint(child2.acceptedStartNames())) {
                        deterministic = false;
                    }
                    start.addAll(child2.acceptedStartNames());
                    excludenext.addAll(((SchemaParticleImpl) child2).getExcludeNextSet());
                }
                break;
            case 3:
                SchemaParticle[] children3 = ensureStateMachine(partImpl.getParticleChildren());
                canskip = true;
                for (int i5 = 0; canskip && i5 < children3.length; i5++) {
                    if (!children3[i5].isSkippable()) {
                        canskip = false;
                    }
                }
                int i6 = children3.length;
                int i7 = 0;
                while (true) {
                    if (i7 < i6) {
                        if (((SchemaParticleImpl) children3[i7]).isDeterministic()) {
                            i7++;
                        } else {
                            deterministic = false;
                        }
                    }
                }
                for (int i8 = 1; i8 < children3.length; i8++) {
                    excludenext.addAll(((SchemaParticleImpl) children3[i8 - 1]).getExcludeNextSet());
                    if (deterministic && !excludenext.isDisjoint(children3[i8].acceptedStartNames())) {
                        deterministic = false;
                    }
                    if (children3[i8].isSkippable()) {
                        excludenext.addAll(children3[i8].acceptedStartNames());
                    } else {
                        excludenext.clear();
                    }
                }
                for (SchemaParticle child3 : children3) {
                    start.addAll(child3.acceptedStartNames());
                    if (!child3.isSkippable()) {
                        break;
                    }
                }
                break;
            case 4:
                if (partImpl.hasTransitionRules()) {
                    start.addAll(partImpl.acceptedStartNames());
                    break;
                } else {
                    start.add(partImpl.getName());
                    break;
                }
            case 5:
                start.addAll(partImpl.getWildcardSet());
                break;
            default:
                throw new IllegalStateException("Unrecognized schema particle");
        }
        BigInteger minOccurs = partImpl.getMinOccurs();
        BigInteger maxOccurs = partImpl.getMaxOccurs();
        boolean canloop = maxOccurs == null || maxOccurs.compareTo(BigInteger.ONE) > 0;
        boolean varloop = maxOccurs == null || minOccurs.compareTo(maxOccurs) < 0;
        if (canloop && deterministic && !excludenext.isDisjoint(start)) {
            QNameSet suspectSet = excludenext.intersect(start);
            Map<SchemaParticle, QNameSet> startMap = new HashMap<>();
            particlesMatchingStart(partImpl, suspectSet, startMap, new QNameSetBuilder());
            Map<SchemaParticle, QNameSet> afterMap = new HashMap<>();
            particlesMatchingAfter(partImpl, suspectSet, afterMap, new QNameSetBuilder(), true);
            deterministic = afterMapSubsumedByStartMap(startMap, afterMap);
        }
        if (varloop) {
            excludenext.addAll(start);
        }
        partImpl.setTransitionRules(start.toQNameSet(), canskip || minOccurs.signum() == 0);
        partImpl.setTransitionNotes(excludenext.toQNameSet(), deterministic);
    }

    private static boolean afterMapSubsumedByStartMap(Map<SchemaParticle, QNameSet> startMap, Map<SchemaParticle, QNameSet> afterMap) {
        if (afterMap.size() > startMap.size()) {
            return false;
        }
        if (afterMap.isEmpty()) {
            return true;
        }
        for (SchemaParticle part : startMap.keySet()) {
            if (part.getParticleType() == 5 && afterMap.containsKey(part)) {
                QNameSet startSet = startMap.get(part);
                QNameSet afterSet = afterMap.get(part);
                if (!startSet.containsAll(afterSet)) {
                    return false;
                }
            }
            afterMap.remove(part);
            if (afterMap.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static void particlesMatchingStart(SchemaParticle part, QNameSetSpecification suspectSet, Map<SchemaParticle, QNameSet> result, QNameSetBuilder eliminate) {
        int i = 0;
        switch (part.getParticleType()) {
            case 1:
            case 2:
                SchemaParticle[] children = part.getParticleChildren();
                int length = children.length;
                while (i < length) {
                    particlesMatchingStart(children[i], suspectSet, result, eliminate);
                    i++;
                }
                return;
            case 3:
                SchemaParticle[] children2 = part.getParticleChildren();
                if (children2.length == 0) {
                    return;
                }
                if (!children2[0].isSkippable()) {
                    particlesMatchingStart(children2[0], suspectSet, result, eliminate);
                    return;
                }
                QNameSetBuilder remainingSuspects = new QNameSetBuilder(suspectSet);
                QNameSetBuilder suspectsToEliminate = new QNameSetBuilder();
                int length2 = children2.length;
                while (i < length2) {
                    SchemaParticle child = children2[i];
                    particlesMatchingStart(child, remainingSuspects, result, suspectsToEliminate);
                    eliminate.addAll(suspectsToEliminate);
                    if (!child.isSkippable()) {
                        return;
                    }
                    remainingSuspects.removeAll(suspectsToEliminate);
                    if (remainingSuspects.isEmpty()) {
                        return;
                    }
                    suspectsToEliminate.clear();
                    i++;
                }
                return;
            case 4:
                if (!suspectSet.contains(part.getName())) {
                    return;
                }
                result.put(part, null);
                eliminate.add(part.getName());
                return;
            case 5:
                if (suspectSet.isDisjoint(part.getWildcardSet())) {
                    return;
                }
                result.put(part, part.getWildcardSet().intersect(suspectSet));
                eliminate.addAll(part.getWildcardSet());
                return;
            default:
                return;
        }
    }

    private static void particlesMatchingAfter(SchemaParticle part, QNameSetSpecification suspectSet, Map<SchemaParticle, QNameSet> result, QNameSetBuilder eliminate, boolean top) {
        boolean varloop = true;
        switch (part.getParticleType()) {
            case 1:
            case 2:
                for (SchemaParticle child : part.getParticleChildren()) {
                    particlesMatchingAfter(child, suspectSet, result, eliminate, false);
                }
                break;
            case 3:
                SchemaParticle[] children = part.getParticleChildren();
                if (children.length != 0) {
                    if (!children[children.length - 1].isSkippable()) {
                        particlesMatchingAfter(children[0], suspectSet, result, eliminate, false);
                        break;
                    } else {
                        QNameSetBuilder remainingSuspects = new QNameSetBuilder(suspectSet);
                        QNameSetBuilder suspectsToEliminate = new QNameSetBuilder();
                        for (int i = children.length - 1; i >= 0; i--) {
                            particlesMatchingAfter(children[i], remainingSuspects, result, suspectsToEliminate, false);
                            eliminate.addAll(suspectsToEliminate);
                            if (!children[i].isSkippable()) {
                                break;
                            } else {
                                remainingSuspects.removeAll(suspectsToEliminate);
                                if (remainingSuspects.isEmpty()) {
                                    break;
                                } else {
                                    suspectsToEliminate.clear();
                                }
                            }
                        }
                        break;
                    }
                }
                break;
        }
        if (!top) {
            BigInteger minOccurs = part.getMinOccurs();
            BigInteger maxOccurs = part.getMaxOccurs();
            if (maxOccurs != null && minOccurs.compareTo(maxOccurs) >= 0) {
                varloop = false;
            }
            if (varloop) {
                particlesMatchingStart(part, suspectSet, result, eliminate);
            }
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

    private static int translateParticleCode(Group parseEg) {
        if (parseEg == null) {
            return 0;
        }
        XmlCursor c = parseEg.newCursor();
        try {
            int translateParticleCode = translateParticleCode(c.getName());
            if (c != null) {
                c.close();
            }
            return translateParticleCode;
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

    private static int translateParticleCode(QName name) {
        return particleCodeMap.getOrDefault(name, 0).intValue();
    }

    static int translateAttributeCode(QName currentName) {
        return attributeCodeMap.getOrDefault(currentName, 0).intValue();
    }
}
