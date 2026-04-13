package org.apache.xmlbeans.impl.schema;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaBookmark;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlPositiveInteger;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.Sax2Dom;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.schema.StscImporter;
import org.apache.xmlbeans.impl.schema.StscTranslator;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.XmlNonNegativeIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlPositiveIntegerImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.Annotated;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.Keybase;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.soap.SOAPArrayType;

/* loaded from: classes11.dex */
public class StscTranslator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String FORM_QUALIFIED = "qualified";
    private static final QName WSDL_ARRAYTYPE_NAME = QNameHelper.forLNS(SoapEncSchemaTypeSystem.ARRAY_TYPE, "http://schemas.xmlsoap.org/wsdl/");
    public static final RegularExpression XPATH_REGEXP = new RegularExpression("(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*))))(\\|(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*)))))*", "X");

    public static void addAllDefinitions(StscImporter.SchemaToProcess[] schemasAndChameleons) {
        int i;
        int i2;
        int i3;
        int i4;
        StscImporter.SchemaToProcess[] schemaToProcessArr = schemasAndChameleons;
        List<RedefinitionHolder> redefinitions = new ArrayList<>();
        int i5 = 0;
        for (StscImporter.SchemaToProcess schemasAndChameleon : schemaToProcessArr) {
            List<StscImporter.SchemaToProcess> redefines = schemasAndChameleon.getRedefines();
            if (redefines != null) {
                List<RedefineDocument.Redefine> redefineObjects = schemasAndChameleon.getRedefineObjects();
                Iterator<StscImporter.SchemaToProcess> it = redefines.iterator();
                Iterator<RedefineDocument.Redefine> ito = redefineObjects.iterator();
                while (it.hasNext()) {
                    if (!ito.hasNext()) {
                        throw new AssertionError("The array of redefines and redefine objects have to have the same length");
                    }
                    redefinitions.add(new RedefinitionHolder(it.next(), ito.next()));
                }
            }
        }
        RedefinitionMaster globalRedefinitions = new RedefinitionMaster((RedefinitionHolder[]) redefinitions.toArray(new RedefinitionHolder[0]));
        StscState state = StscState.get();
        int length = schemaToProcessArr.length;
        int i6 = 0;
        while (i6 < length) {
            StscImporter.SchemaToProcess schemasAndChameleon2 = schemaToProcessArr[i6];
            SchemaDocument.Schema schema = schemasAndChameleon2.getSchema();
            String givenTargetNamespace = schemasAndChameleon2.getChameleonNamespace();
            if (schema.sizeOfNotationArray() > 0) {
                state.warning("Schema <notation> is not yet supported for this release.", 51, schema.getNotationArray(i5));
            }
            String targetNamespace = schema.getTargetNamespace();
            boolean chameleon = false;
            if (givenTargetNamespace != null && targetNamespace == null) {
                targetNamespace = givenTargetNamespace;
                chameleon = true;
            }
            if (targetNamespace == null) {
                targetNamespace = "";
            }
            if (targetNamespace.length() > 0 || !isEmptySchema(schema)) {
                state.registerContribution(targetNamespace, schema.documentProperties().getSourceName());
                state.addNewContainer(targetNamespace);
            }
            List<Annotated> redefChain = new ArrayList<>();
            TopLevelComplexType[] complexTypes = schema.getComplexTypeArray();
            int length2 = complexTypes.length;
            int i7 = i5;
            while (i7 < length2) {
                TopLevelComplexType complexType = complexTypes[i7];
                RedefinitionHolder[] rhArray = globalRedefinitions.getComplexTypeRedefinitions(complexType.getName(), schemasAndChameleon2);
                int length3 = rhArray.length;
                TopLevelComplexType type = complexType;
                List<RedefinitionHolder> redefinitions2 = redefinitions;
                int i8 = 0;
                while (i8 < length3) {
                    int i9 = length3;
                    RedefinitionHolder redefinitionHolder = rhArray[i8];
                    if (redefinitionHolder != null) {
                        i4 = i8;
                        TopLevelComplexType redef = redefinitionHolder.redefineComplexType(type.getName());
                        if (redef == null) {
                            throw new AssertionError();
                        }
                        redefChain.add(type);
                        type = redef;
                    } else {
                        i4 = i8;
                    }
                    i8 = i4 + 1;
                    length3 = i9;
                }
                SchemaTypeImpl t = translateGlobalComplexType(type, targetNamespace, chameleon, !redefChain.isEmpty());
                state.addGlobalType(t, null);
                int k = redefChain.size() - 1;
                while (k >= 0) {
                    int k2 = k;
                    RedefinitionHolder[] rhArray2 = rhArray;
                    SchemaTypeImpl r = translateGlobalComplexType((TopLevelComplexType) redefChain.remove(k), targetNamespace, chameleon, k2 > 0);
                    state.addGlobalType(r, t);
                    t = r;
                    rhArray = rhArray2;
                    k = k2 - 1;
                }
                i7++;
                redefinitions = redefinitions2;
            }
            List<RedefinitionHolder> redefinitions3 = redefinitions;
            TopLevelSimpleType[] simpleTypes = schema.getSimpleTypeArray();
            int length4 = simpleTypes.length;
            int i10 = 0;
            while (i10 < length4) {
                TopLevelSimpleType simpleType = simpleTypes[i10];
                TopLevelSimpleType type2 = simpleType;
                TopLevelSimpleType[] simpleTypes2 = simpleTypes;
                RedefinitionHolder[] rhArray3 = globalRedefinitions.getSimpleTypeRedefinitions(type2.getName(), schemasAndChameleon2);
                int i11 = length4;
                int length5 = rhArray3.length;
                int i12 = 0;
                while (i12 < length5) {
                    int i13 = i12;
                    RedefinitionHolder redefinitionHolder2 = rhArray3[i13];
                    if (redefinitionHolder2 == null) {
                        i3 = length5;
                    } else {
                        i3 = length5;
                        TopLevelSimpleType redef2 = redefinitionHolder2.redefineSimpleType(type2.getName());
                        if (redef2 == null) {
                            throw new AssertionError();
                        }
                        redefChain.add(type2);
                        type2 = redef2;
                    }
                    i12 = i13 + 1;
                    length5 = i3;
                }
                SchemaTypeImpl t2 = translateGlobalSimpleType(type2, targetNamespace, chameleon, !redefChain.isEmpty());
                state.addGlobalType(t2, null);
                int k3 = redefChain.size() - 1;
                while (k3 >= 0) {
                    int k4 = k3;
                    int i14 = i10;
                    SchemaTypeImpl r2 = translateGlobalSimpleType((TopLevelSimpleType) redefChain.remove(k3), targetNamespace, chameleon, k4 > 0);
                    state.addGlobalType(r2, t2);
                    t2 = r2;
                    i10 = i14;
                    k3 = k4 - 1;
                }
                i10++;
                simpleTypes = simpleTypes2;
                length4 = i11;
            }
            TopLevelElement[] elements = schema.getElementArray();
            int length6 = elements.length;
            int i15 = 0;
            while (i15 < length6) {
                TopLevelElement element = elements[i15];
                state.addDocumentType(translateDocumentType(element, targetNamespace, chameleon), QNameHelper.forLNS(element.getName(), targetNamespace));
                i15++;
                elements = elements;
            }
            TopLevelAttribute[] attributes = schema.getAttributeArray();
            int length7 = attributes.length;
            int i16 = 0;
            while (i16 < length7) {
                TopLevelAttribute attribute = attributes[i16];
                state.addAttributeType(translateAttributeType(attribute, targetNamespace, chameleon), QNameHelper.forLNS(attribute.getName(), targetNamespace));
                i16++;
                attributes = attributes;
            }
            NamedGroup[] modelgroups = schema.getGroupArray();
            int length8 = modelgroups.length;
            int i17 = 0;
            while (i17 < length8) {
                NamedGroup group = modelgroups[i17];
                RedefinitionHolder[] rhArray4 = globalRedefinitions.getModelGroupRedefinitions(group.getName(), schemasAndChameleon2);
                NamedGroup[] modelgroups2 = modelgroups;
                int length9 = rhArray4.length;
                int i18 = length8;
                int i19 = 0;
                while (i19 < length9) {
                    int i20 = length9;
                    RedefinitionHolder redefinitionHolder3 = rhArray4[i19];
                    if (redefinitionHolder3 == null) {
                        i2 = i19;
                    } else {
                        i2 = i19;
                        NamedGroup redef3 = redefinitionHolder3.redefineModelGroup(group.getName());
                        if (redef3 == null) {
                            throw new AssertionError();
                        }
                        redefChain.add(group);
                        group = redef3;
                    }
                    i19 = i2 + 1;
                    length9 = i20;
                }
                SchemaModelGroupImpl g = translateModelGroup(group, targetNamespace, chameleon, !redefChain.isEmpty());
                state.addModelGroup(g, null);
                int k5 = redefChain.size() - 1;
                while (k5 >= 0) {
                    int k6 = k5;
                    int i21 = i17;
                    SchemaModelGroupImpl r3 = translateModelGroup((NamedGroup) redefChain.remove(k5), targetNamespace, chameleon, k6 > 0);
                    state.addModelGroup(r3, g);
                    g = r3;
                    i17 = i21;
                    k5 = k6 - 1;
                }
                i17++;
                modelgroups = modelgroups2;
                length8 = i18;
            }
            NamedAttributeGroup[] attrgroups = schema.getAttributeGroupArray();
            int length10 = attrgroups.length;
            int i22 = 0;
            while (i22 < length10) {
                NamedAttributeGroup group2 = attrgroups[i22];
                RedefinitionHolder[] rhArray5 = globalRedefinitions.getAttributeGroupRedefinitions(group2.getName(), schemasAndChameleon2);
                NamedAttributeGroup[] attrgroups2 = attrgroups;
                int length11 = rhArray5.length;
                int i23 = length10;
                int i24 = 0;
                while (i24 < length11) {
                    int i25 = length11;
                    RedefinitionHolder redefinitionHolder4 = rhArray5[i24];
                    if (redefinitionHolder4 == null) {
                        i = i24;
                    } else {
                        i = i24;
                        NamedAttributeGroup redef4 = redefinitionHolder4.redefineAttributeGroup(group2.getName());
                        if (redef4 == null) {
                            throw new AssertionError();
                        }
                        redefChain.add(group2);
                        group2 = redef4;
                    }
                    i24 = i + 1;
                    length11 = i25;
                }
                SchemaAttributeGroupImpl g2 = translateAttributeGroup(group2, targetNamespace, chameleon, !redefChain.isEmpty());
                state.addAttributeGroup(g2, null);
                int k7 = redefChain.size() - 1;
                while (k7 >= 0) {
                    int k8 = k7;
                    RedefinitionMaster globalRedefinitions2 = globalRedefinitions;
                    SchemaAttributeGroupImpl r4 = translateAttributeGroup((NamedAttributeGroup) redefChain.remove(k7), targetNamespace, chameleon, k8 > 0);
                    state.addAttributeGroup(r4, g2);
                    g2 = r4;
                    globalRedefinitions = globalRedefinitions2;
                    k7 = k8 - 1;
                }
                i22++;
                attrgroups = attrgroups2;
                length10 = i23;
            }
            RedefinitionMaster globalRedefinitions3 = globalRedefinitions;
            AnnotationDocument.Annotation[] annotations = schema.getAnnotationArray();
            for (AnnotationDocument.Annotation annotation : annotations) {
                state.addAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), schema, annotation), targetNamespace);
            }
            i6++;
            schemaToProcessArr = schemasAndChameleons;
            redefinitions = redefinitions3;
            globalRedefinitions = globalRedefinitions3;
            i5 = 0;
        }
        for (Object redefinition : redefinitions) {
            ((RedefinitionHolder) redefinition).complainAboutMissingDefinitions();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class RedefinitionHolder {
        private Map<String, NamedAttributeGroup> agRedefinitions;
        private Map<String, TopLevelComplexType> ctRedefinitions;
        private Map<String, NamedGroup> mgRedefinitions;
        private String schemaLocation;
        private final StscImporter.SchemaToProcess schemaRedefined;
        private Map<String, TopLevelSimpleType> stRedefinitions;

        RedefinitionHolder(StscImporter.SchemaToProcess schemaToProcess, RedefineDocument.Redefine redefine) {
            this.stRedefinitions = Collections.emptyMap();
            this.ctRedefinitions = Collections.emptyMap();
            this.agRedefinitions = Collections.emptyMap();
            this.mgRedefinitions = Collections.emptyMap();
            this.schemaLocation = "";
            this.schemaRedefined = schemaToProcess;
            if (redefine != null) {
                StscState state = StscState.get();
                this.stRedefinitions = new HashMap();
                this.ctRedefinitions = new HashMap();
                this.agRedefinitions = new HashMap();
                this.mgRedefinitions = new HashMap();
                if (redefine.getSchemaLocation() != null) {
                    this.schemaLocation = redefine.getSchemaLocation();
                }
                TopLevelComplexType[] complexTypes = redefine.getComplexTypeArray();
                for (TopLevelComplexType complexType : complexTypes) {
                    if (complexType.getName() != null) {
                        if (this.ctRedefinitions.containsKey(complexType.getName())) {
                            state.error("Duplicate type redefinition: " + complexType.getName(), 49, (XmlObject) null);
                        } else {
                            this.ctRedefinitions.put(complexType.getName(), complexType);
                        }
                    }
                }
                TopLevelSimpleType[] simpleTypes = redefine.getSimpleTypeArray();
                for (TopLevelSimpleType simpleType : simpleTypes) {
                    if (simpleType.getName() != null) {
                        if (this.stRedefinitions.containsKey(simpleType.getName())) {
                            state.error("Duplicate type redefinition: " + simpleType.getName(), 49, (XmlObject) null);
                        } else {
                            this.stRedefinitions.put(simpleType.getName(), simpleType);
                        }
                    }
                }
                NamedGroup[] modelgroups = redefine.getGroupArray();
                for (NamedGroup modelgroup : modelgroups) {
                    if (modelgroup.getName() != null) {
                        if (this.mgRedefinitions.containsKey(modelgroup.getName())) {
                            state.error("Duplicate type redefinition: " + modelgroup.getName(), 49, (XmlObject) null);
                        } else {
                            this.mgRedefinitions.put(modelgroup.getName(), modelgroup);
                        }
                    }
                }
                NamedAttributeGroup[] attrgroups = redefine.getAttributeGroupArray();
                for (NamedAttributeGroup attrgroup : attrgroups) {
                    if (attrgroup.getName() != null) {
                        if (this.agRedefinitions.containsKey(attrgroup.getName())) {
                            state.error("Duplicate type redefinition: " + attrgroup.getName(), 49, (XmlObject) null);
                        } else {
                            this.agRedefinitions.put(attrgroup.getName(), attrgroup);
                        }
                    }
                }
            }
        }

        public TopLevelSimpleType redefineSimpleType(String name) {
            if (name == null || !this.stRedefinitions.containsKey(name)) {
                return null;
            }
            return this.stRedefinitions.remove(name);
        }

        public TopLevelComplexType redefineComplexType(String name) {
            if (name == null || !this.ctRedefinitions.containsKey(name)) {
                return null;
            }
            return this.ctRedefinitions.remove(name);
        }

        public NamedGroup redefineModelGroup(String name) {
            if (name == null || !this.mgRedefinitions.containsKey(name)) {
                return null;
            }
            return this.mgRedefinitions.remove(name);
        }

        public NamedAttributeGroup redefineAttributeGroup(String name) {
            if (name == null || !this.agRedefinitions.containsKey(name)) {
                return null;
            }
            return this.agRedefinitions.remove(name);
        }

        public void complainAboutMissingDefinitions() {
            if (this.stRedefinitions.isEmpty() && this.ctRedefinitions.isEmpty() && this.agRedefinitions.isEmpty() && this.mgRedefinitions.isEmpty()) {
                return;
            }
            StscState state = StscState.get();
            for (String name : this.stRedefinitions.keySet()) {
                state.error("Redefined simple type " + name + " not found in " + this.schemaLocation, 60, this.stRedefinitions.get(name));
            }
            for (String name2 : this.ctRedefinitions.keySet()) {
                state.error("Redefined complex type " + name2 + " not found in " + this.schemaLocation, 60, this.ctRedefinitions.get(name2));
            }
            for (String name3 : this.agRedefinitions.keySet()) {
                state.error("Redefined attribute group " + name3 + " not found in " + this.schemaLocation, 60, this.agRedefinitions.get(name3));
            }
            for (String name4 : this.mgRedefinitions.keySet()) {
                state.error("Redefined model group " + name4 + " not found in " + this.schemaLocation, 60, this.mgRedefinitions.get(name4));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class RedefinitionMaster {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final short ATTRIBUTE_GROUP = 4;
        private static final short COMPLEX_TYPE = 2;
        private static final RedefinitionHolder[] EMPTY_REDEFINTION_HOLDER_ARRAY = new RedefinitionHolder[0];
        private static final short MODEL_GROUP = 3;
        private static final short SIMPLE_TYPE = 1;
        private Map<String, List<RedefinitionHolder>> agRedefinitions;
        private Map<String, List<RedefinitionHolder>> ctRedefinitions;
        private Map<String, List<RedefinitionHolder>> mgRedefinitions;
        private Map<String, List<RedefinitionHolder>> stRedefinitions;

        RedefinitionMaster(RedefinitionHolder[] redefHolders) {
            this.stRedefinitions = Collections.emptyMap();
            this.ctRedefinitions = Collections.emptyMap();
            this.agRedefinitions = Collections.emptyMap();
            this.mgRedefinitions = Collections.emptyMap();
            if (redefHolders.length > 0) {
                this.stRedefinitions = new HashMap();
                this.ctRedefinitions = new HashMap();
                this.agRedefinitions = new HashMap();
                this.mgRedefinitions = new HashMap();
                for (RedefinitionHolder redefHolder : redefHolders) {
                    for (String key : redefHolder.stRedefinitions.keySet()) {
                        this.stRedefinitions.computeIfAbsent(key, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return StscTranslator.RedefinitionMaster.lambda$new$0((String) obj);
                            }
                        }).add(redefHolder);
                    }
                    for (String key2 : redefHolder.ctRedefinitions.keySet()) {
                        this.ctRedefinitions.computeIfAbsent(key2, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda1
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return StscTranslator.RedefinitionMaster.lambda$new$1((String) obj);
                            }
                        }).add(redefHolder);
                    }
                    for (String key3 : redefHolder.agRedefinitions.keySet()) {
                        this.agRedefinitions.computeIfAbsent(key3, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda2
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return StscTranslator.RedefinitionMaster.lambda$new$2((String) obj);
                            }
                        }).add(redefHolder);
                    }
                    for (String key4 : redefHolder.mgRedefinitions.keySet()) {
                        this.mgRedefinitions.computeIfAbsent(key4, new Function() { // from class: org.apache.xmlbeans.impl.schema.StscTranslator$RedefinitionMaster$$ExternalSyntheticLambda3
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return StscTranslator.RedefinitionMaster.lambda$new$3((String) obj);
                            }
                        }).add(redefHolder);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ List lambda$new$0(String k) {
            return new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ List lambda$new$1(String k) {
            return new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ List lambda$new$2(String k) {
            return new ArrayList();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ List lambda$new$3(String k) {
            return new ArrayList();
        }

        RedefinitionHolder[] getSimpleTypeRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List<RedefinitionHolder> redefines = this.stRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(redefines, schema, name, (short) 1);
        }

        RedefinitionHolder[] getComplexTypeRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List<RedefinitionHolder> redefines = this.ctRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(redefines, schema, name, (short) 2);
        }

        RedefinitionHolder[] getAttributeGroupRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List<RedefinitionHolder> redefines = this.agRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(redefines, schema, name, (short) 4);
        }

        RedefinitionHolder[] getModelGroupRedefinitions(String name, StscImporter.SchemaToProcess schema) {
            List<RedefinitionHolder> redefines = this.mgRedefinitions.get(name);
            if (redefines == null) {
                return EMPTY_REDEFINTION_HOLDER_ARRAY;
            }
            return doTopologicalSort(redefines, schema, name, (short) 3);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:116:0x01f5, code lost:
        
            r6[r1] = r17;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private org.apache.xmlbeans.impl.schema.StscTranslator.RedefinitionHolder[] doTopologicalSort(java.util.List<org.apache.xmlbeans.impl.schema.StscTranslator.RedefinitionHolder> r19, org.apache.xmlbeans.impl.schema.StscImporter.SchemaToProcess r20, java.lang.String r21, short r22) {
            /*
                Method dump skipped, instructions count: 526
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscTranslator.RedefinitionMaster.doTopologicalSort(java.util.List, org.apache.xmlbeans.impl.schema.StscImporter$SchemaToProcess, java.lang.String, short):org.apache.xmlbeans.impl.schema.StscTranslator$RedefinitionHolder[]");
        }

        private String componentNameFromCode(short code) {
            switch (code) {
                case 1:
                    return "simple type";
                case 2:
                    return "complex type";
                case 3:
                    return "model group";
                case 4:
                    return "attribute group";
                default:
                    return "";
            }
        }

        private XmlObject locationFromRedefinitionAndCode(RedefinitionHolder redefinition, String name, short code) {
            switch (code) {
                case 1:
                    XmlObject location = (XmlObject) redefinition.stRedefinitions.get(name);
                    return location;
                case 2:
                    XmlObject location2 = (XmlObject) redefinition.ctRedefinitions.get(name);
                    return location2;
                case 3:
                    XmlObject location3 = (XmlObject) redefinition.mgRedefinitions.get(name);
                    return location3;
                case 4:
                    XmlObject location4 = (XmlObject) redefinition.agRedefinitions.get(name);
                    return location4;
                default:
                    return null;
            }
        }
    }

    private static String findFilename(XmlObject xobj) {
        return StscState.get().sourceNameForUri(xobj.documentProperties().getSourceName());
    }

    private static SchemaTypeImpl translateDocumentType(TopLevelElement xsdType, String targetNamespace, boolean chameleon) {
        SchemaTypeImpl sType = new SchemaTypeImpl(StscState.get().getContainer(targetNamespace));
        sType.setDocumentType(true);
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, false);
        sType.setFilename(findFilename(xsdType));
        return sType;
    }

    private static SchemaTypeImpl translateAttributeType(TopLevelAttribute xsdType, String targetNamespace, boolean chameleon) {
        SchemaTypeImpl sType = new SchemaTypeImpl(StscState.get().getContainer(targetNamespace));
        sType.setAttributeType(true);
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, false);
        sType.setFilename(findFilename(xsdType));
        return sType;
    }

    private static SchemaTypeImpl translateGlobalComplexType(TopLevelComplexType xsdType, String targetNamespace, boolean chameleon, boolean redefinition) {
        StscState state = StscState.get();
        String localname = xsdType.getName();
        if (localname == null) {
            state.error(XmlErrorCodes.MISSING_NAME, new Object[]{"global type"}, xsdType);
            return null;
        }
        if (!XMLChar.isValidNCName(localname)) {
            state.error(XmlErrorCodes.INVALID_VALUE, new Object[]{localname, "name"}, xsdType.xgetName());
        }
        QName name = QNameHelper.forLNS(localname, targetNamespace);
        if (isReservedTypeName(name)) {
            state.warning(XmlErrorCodes.RESERVED_TYPE_NAME, new Object[]{QNameHelper.pretty(name)}, xsdType);
            return null;
        }
        SchemaTypeImpl sType = new SchemaTypeImpl(state.getContainer(targetNamespace));
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, redefinition);
        sType.setFilename(findFilename(xsdType));
        sType.setName(QNameHelper.forLNS(localname, targetNamespace));
        sType.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), xsdType));
        sType.setUserData(getUserData(xsdType));
        return sType;
    }

    private static SchemaTypeImpl translateGlobalSimpleType(TopLevelSimpleType xsdType, String targetNamespace, boolean chameleon, boolean redefinition) {
        StscState state = StscState.get();
        String localname = xsdType.getName();
        if (localname == null) {
            state.error(XmlErrorCodes.MISSING_NAME, new Object[]{"global type"}, xsdType);
            return null;
        }
        if (!XMLChar.isValidNCName(localname)) {
            state.error(XmlErrorCodes.INVALID_VALUE, new Object[]{localname, "name"}, xsdType.xgetName());
        }
        QName name = QNameHelper.forLNS(localname, targetNamespace);
        if (isReservedTypeName(name)) {
            state.warning(XmlErrorCodes.RESERVED_TYPE_NAME, new Object[]{QNameHelper.pretty(name)}, xsdType);
            return null;
        }
        SchemaTypeImpl sType = new SchemaTypeImpl(state.getContainer(targetNamespace));
        sType.setSimpleType(true);
        sType.setParseContext(xsdType, targetNamespace, chameleon, null, null, redefinition);
        sType.setFilename(findFilename(xsdType));
        sType.setName(name);
        sType.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), xsdType));
        sType.setUserData(getUserData(xsdType));
        return sType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SchemaTypeImpl translateAnonymousSimpleType(SimpleType typedef, String targetNamespace, boolean chameleon, String elemFormDefault, String attFormDefault, List<SchemaType> anonymousTypes, SchemaType outerType) {
        StscState state = StscState.get();
        SchemaTypeImpl sType = new SchemaTypeImpl(state.getContainer(targetNamespace));
        sType.setSimpleType(true);
        sType.setParseContext(typedef, targetNamespace, chameleon, elemFormDefault, attFormDefault, false);
        sType.setOuterSchemaTypeRef(outerType.getRef());
        sType.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), typedef));
        sType.setUserData(getUserData(typedef));
        anonymousTypes.add(sType);
        return sType;
    }

    static FormChoice findElementFormDefault(XmlObject obj) {
        XmlCursor cur = obj.newCursor();
        do {
            try {
                if (cur.getObject().schemaType() == SchemaDocument.Schema.type) {
                    FormChoice xgetElementFormDefault = ((SchemaDocument.Schema) cur.getObject()).xgetElementFormDefault();
                    if (cur != null) {
                        cur.close();
                    }
                    return xgetElementFormDefault;
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
        } while (cur.toParent());
        if (cur != null) {
            cur.close();
            return null;
        }
        return null;
    }

    public static boolean uriMatch(String s1, String s2) {
        if (s1 == null) {
            return s2 == null || s2.equals("");
        }
        if (s2 == null) {
            return s1.equals("");
        }
        return s1.equals(s2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void copyGlobalElementToLocalElement(SchemaGlobalElement schemaGlobalElement, SchemaLocalElementImpl target) {
        target.setNameAndTypeRef(schemaGlobalElement.getName(), schemaGlobalElement.getType().getRef());
        target.setNillable(schemaGlobalElement.isNillable());
        target.setDefault(schemaGlobalElement.getDefaultText(), schemaGlobalElement.isFixed(), ((SchemaGlobalElementImpl) schemaGlobalElement).getParseObject());
        target.setIdentityConstraints(((SchemaLocalElementImpl) schemaGlobalElement).getIdentityConstraintRefs());
        target.setBlock(schemaGlobalElement.blockExtension(), schemaGlobalElement.blockRestriction(), schemaGlobalElement.blockSubstitution());
        target.setAbstract(schemaGlobalElement.isAbstract());
        target.setTransitionRules(((SchemaParticle) schemaGlobalElement).acceptedStartNames(), ((SchemaParticle) schemaGlobalElement).isSkippable());
        target.setAnnotation(schemaGlobalElement.getAnnotation());
    }

    public static void copyGlobalAttributeToLocalAttribute(SchemaGlobalAttributeImpl referenced, SchemaLocalAttributeImpl target) {
        target.init(referenced.getName(), referenced.getTypeRef(), referenced.getUse(), referenced.getDefaultText(), referenced.getParseObject(), referenced._defaultValue, referenced.isFixed(), referenced.getWSDLArrayType(), referenced.getAnnotation(), null);
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:140:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x048c A[EDGE_INSN: B:147:0x048c->B:148:0x048c BREAK  A[LOOP:1: B:138:0x0465->B:144:0x0484], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x04ba A[EDGE_INSN: B:158:0x04ba->B:159:0x04ba BREAK  A[LOOP:2: B:149:0x0493->B:155:0x04b1], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x04e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl translateElement(org.apache.xmlbeans.impl.xb.xsdschema.Element r29, java.lang.String r30, boolean r31, java.lang.String r32, java.lang.String r33, java.util.List<org.apache.xmlbeans.SchemaType> r34, org.apache.xmlbeans.SchemaType r35) {
        /*
            Method dump skipped, instructions count: 1295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscTranslator.translateElement(org.apache.xmlbeans.impl.xb.xsdschema.Element, java.lang.String, boolean, java.lang.String, java.lang.String, java.util.List, org.apache.xmlbeans.SchemaType):org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl");
    }

    private static SchemaType checkRecursiveGroupReference(QName[] context, QName containingElement, SchemaTypeImpl outerType) {
        QName[] outerContext;
        if (context.length < 1) {
            return null;
        }
        for (SchemaTypeImpl type = outerType; type != null && type.getName() == null && !type.isDocumentType(); type = (SchemaTypeImpl) type.getOuterType()) {
            if (containingElement.equals(type.getContainerField().getName()) && (outerContext = type.getGroupReferenceContext()) != null && outerContext.length == context.length) {
                boolean equal = true;
                for (int i = 0; i < context.length; i++) {
                    if ((context[i] != null || outerContext[i] != null) && (context[i] == null || !context[i].equals(outerContext[i]))) {
                        equal = false;
                        break;
                    }
                }
                if (equal) {
                    return type;
                }
            }
        }
        return null;
    }

    private static String removeWhitespace(String xpath) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < xpath.length(); i++) {
            char ch = xpath.charAt(i);
            if (!XMLChar.isSpace(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private static boolean checkXPathSyntax(String xpath) {
        boolean matches;
        if (xpath == null) {
            return false;
        }
        String xpath2 = removeWhitespace(xpath);
        synchronized (XPATH_REGEXP) {
            matches = XPATH_REGEXP.matches(xpath2);
        }
        return matches;
    }

    private static SchemaIdentityConstraintImpl translateIdentityConstraint(Keybase parseIC, String targetNamespace, boolean chameleon) {
        StscState state = StscState.get();
        String selector = parseIC.getSelector() == null ? null : parseIC.getSelector().getXpath();
        if (!checkXPathSyntax(selector)) {
            state.error(XmlErrorCodes.SELECTOR_XPATH, new Object[]{selector}, parseIC.getSelector().xgetXpath());
            return null;
        }
        FieldDocument.Field[] fieldElts = parseIC.getFieldArray();
        for (FieldDocument.Field fieldElt : fieldElts) {
            if (!checkXPathSyntax(fieldElt.getXpath())) {
                state.error(XmlErrorCodes.FIELDS_XPATH, new Object[]{fieldElt.getXpath()}, fieldElt.xgetXpath());
                return null;
            }
        }
        SchemaIdentityConstraintImpl ic = new SchemaIdentityConstraintImpl(state.getContainer(targetNamespace));
        ic.setName(QNameHelper.forLNS(parseIC.getName(), targetNamespace));
        ic.setSelector(parseIC.getSelector().getXpath());
        ic.setParseContext(parseIC, targetNamespace, chameleon);
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(state.getContainer(targetNamespace), parseIC);
        ic.setAnnotation(ann);
        ic.setUserData(getUserData(parseIC));
        Map<String, String> nsMap = new HashMap<>();
        XmlCursor c = parseIC.newCursor();
        try {
            c.getAllNamespaces(nsMap);
            if (c != null) {
                c.close();
            }
            nsMap.remove("");
            ic.setNSMap(nsMap);
            String[] fields = new String[fieldElts.length];
            for (int j = 0; j < fields.length; j++) {
                fields[j] = fieldElts[j].getXpath();
            }
            ic.setFields(fields);
            try {
                ic.buildPaths();
                state.addIdConstraint(ic);
                ic.setFilename(findFilename(parseIC));
                return state.findIdConstraint(ic.getName(), targetNamespace, null);
            } catch (XPath.XPathCompileException e) {
                state.error(XmlErrorCodes.INVALID_XPATH, new Object[]{e.getMessage()}, parseIC);
                return null;
            }
        } finally {
        }
    }

    public static SchemaModelGroupImpl translateModelGroup(NamedGroup namedGroup, String targetNamespace, boolean chameleon, boolean redefinition) {
        String stringValue;
        String stringValue2;
        String name = namedGroup.getName();
        if (name == null) {
            StscState.get().error(XmlErrorCodes.MISSING_NAME, new Object[]{"model group"}, namedGroup);
            return null;
        }
        SchemaContainer c = StscState.get().getContainer(targetNamespace);
        SchemaModelGroupImpl result = new SchemaModelGroupImpl(c);
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(c, namedGroup);
        FormChoice elemFormDefault = findElementFormDefault(namedGroup);
        FormChoice attFormDefault = findAttributeFormDefault(namedGroup);
        QName forLNS = QNameHelper.forLNS(name, targetNamespace);
        if (elemFormDefault == null) {
            stringValue = null;
        } else {
            stringValue = elemFormDefault.getStringValue();
        }
        if (attFormDefault == null) {
            stringValue2 = null;
        } else {
            stringValue2 = attFormDefault.getStringValue();
        }
        result.init(forLNS, targetNamespace, chameleon, stringValue, stringValue2, redefinition, namedGroup, ann, getUserData(namedGroup));
        result.setFilename(findFilename(namedGroup));
        return result;
    }

    public static SchemaAttributeGroupImpl translateAttributeGroup(AttributeGroup attrGroup, String targetNamespace, boolean chameleon, boolean redefinition) {
        String stringValue;
        String name = attrGroup.getName();
        if (name == null) {
            StscState.get().error(XmlErrorCodes.MISSING_NAME, new Object[]{"attribute group"}, attrGroup);
            return null;
        }
        SchemaContainer c = StscState.get().getContainer(targetNamespace);
        SchemaAttributeGroupImpl result = new SchemaAttributeGroupImpl(c);
        SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(c, attrGroup);
        FormChoice formDefault = findAttributeFormDefault(attrGroup);
        QName forLNS = QNameHelper.forLNS(name, targetNamespace);
        if (formDefault == null) {
            stringValue = null;
        } else {
            stringValue = formDefault.getStringValue();
        }
        result.init(forLNS, targetNamespace, chameleon, stringValue, redefinition, attrGroup, ann, getUserData(attrGroup));
        result.setFilename(findFilename(attrGroup));
        return result;
    }

    static FormChoice findAttributeFormDefault(XmlObject obj) {
        XmlCursor cur = obj.newCursor();
        do {
            try {
                if (cur.getObject().schemaType() == SchemaDocument.Schema.type) {
                    FormChoice xgetAttributeFormDefault = ((SchemaDocument.Schema) cur.getObject()).xgetAttributeFormDefault();
                    if (cur != null) {
                        cur.close();
                    }
                    return xgetAttributeFormDefault;
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
        } while (cur.toParent());
        if (cur != null) {
            cur.close();
            return null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v3 */
    /* JADX WARN: Type inference failed for: r18v4, types: [org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl] */
    /* JADX WARN: Type inference failed for: r18v5 */
    public static SchemaLocalAttributeImpl translateAttribute(Attribute xsdAttr, String targetNamespace, String formDefault, boolean chameleon, List<SchemaType> anonymousTypes, SchemaType outerType, SchemaAttributeModel baseModel, boolean local) {
        String name;
        SchemaLocalAttributeImpl sAttr;
        QName qname;
        SchemaLocalAttributeImpl sAttr2;
        String str;
        QName qname2;
        String fmrfixedtext;
        boolean qualified;
        SchemaType sType;
        boolean isFixed;
        boolean isFixed2;
        ?? r18;
        StscState state = StscState.get();
        String name2 = xsdAttr.getName();
        QName ref = xsdAttr.getRef();
        if (ref != null && name2 != null) {
            if (!name2.equals(ref.getLocalPart()) || !uriMatch(targetNamespace, ref.getNamespaceURI())) {
                state.error(XmlErrorCodes.SCHEMA_ATTR$REF_OR_NAME_HAS_BOTH, new Object[]{name2}, xsdAttr.xgetRef());
            } else {
                state.warning(XmlErrorCodes.SCHEMA_ATTR$REF_OR_NAME_HAS_BOTH, new Object[]{name2}, xsdAttr.xgetRef());
            }
            name = null;
        } else {
            name = name2;
        }
        if (ref == null && name == null) {
            state.error(XmlErrorCodes.SCHEMA_ATTR$REF_OR_NAME_HAS_NEITHER, (Object[]) null, xsdAttr);
            return null;
        }
        if (name != null && !XMLChar.isValidNCName(name)) {
            state.error(XmlErrorCodes.INVALID_VALUE, new Object[]{name, "name"}, xsdAttr.xgetName());
        }
        boolean isFixed3 = false;
        String deftext = null;
        String fmrfixedtext2 = null;
        SchemaType sType2 = null;
        int use = 2;
        if (local) {
            sAttr = new SchemaLocalAttributeImpl();
        } else {
            sAttr = new SchemaGlobalAttributeImpl(StscState.get().getContainer(targetNamespace));
            ((SchemaGlobalAttributeImpl) sAttr).setParseContext(xsdAttr, targetNamespace, chameleon);
        }
        if (ref != null) {
            if (xsdAttr.getType() != null) {
                r18 = 0;
                state.error(XmlErrorCodes.SCHEMA_ATTR$REF_FEATURES, new Object[]{ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY}, xsdAttr.xgetType());
            } else {
                r18 = 0;
            }
            if (xsdAttr.getSimpleType() != null) {
                state.error(XmlErrorCodes.SCHEMA_ATTR$REF_FEATURES, new Object[]{"<simpleType>"}, xsdAttr.getSimpleType());
            }
            if (xsdAttr.getForm() != null) {
                state.error(XmlErrorCodes.SCHEMA_ATTR$REF_FEATURES, new Object[]{"form"}, xsdAttr.xgetForm());
            }
            SchemaGlobalAttribute referenced = state.findGlobalAttribute(ref, chameleon ? targetNamespace : r18, targetNamespace);
            if (referenced == null) {
                state.notFoundError(ref, 3, xsdAttr.xgetRef(), true);
                return r18;
            }
            qname2 = ref;
            use = referenced.getUse();
            sType2 = referenced.getType();
            deftext = referenced.getDefaultText();
            if (deftext != null && (isFixed3 = referenced.isFixed())) {
                fmrfixedtext2 = deftext;
            }
            sAttr2 = sAttr;
            str = targetNamespace;
            fmrfixedtext = fmrfixedtext2;
        } else {
            if (local) {
                FormChoice form = xsdAttr.xgetForm();
                if (form != null) {
                    qualified = form.getStringValue().equals(FORM_QUALIFIED);
                } else if (formDefault != null) {
                    qualified = formDefault.equals(FORM_QUALIFIED);
                } else {
                    FormChoice form2 = findAttributeFormDefault(xsdAttr);
                    qualified = form2 != null && form2.getStringValue().equals(FORM_QUALIFIED);
                }
                QName qname3 = qualified ? QNameHelper.forLNS(name, targetNamespace) : QNameHelper.forLN(name);
                qname = qname3;
            } else {
                qname = QNameHelper.forLNS(name, targetNamespace);
            }
            if (xsdAttr.getType() != null) {
                sType2 = state.findGlobalType(xsdAttr.getType(), chameleon ? targetNamespace : null, targetNamespace);
                if (sType2 == null) {
                    state.notFoundError(xsdAttr.getType(), 0, xsdAttr.xgetType(), true);
                }
            }
            if (qname.getNamespaceURI().equals("http://www.w3.org/2001/XMLSchema-instance")) {
                state.error(XmlErrorCodes.NO_XSI, new Object[]{"http://www.w3.org/2001/XMLSchema-instance"}, xsdAttr.xgetName());
            }
            if (qname.getNamespaceURI().isEmpty() && qname.getLocalPart().equals(Sax2Dom.XMLNS_PREFIX)) {
                state.error(XmlErrorCodes.NO_XMLNS, (Object[]) null, xsdAttr.xgetName());
            }
            LocalSimpleType typedef = xsdAttr.getSimpleType();
            if (sType2 != null && typedef != null) {
                state.error(XmlErrorCodes.SCHEMA_ATTR$TYPE_ATTR_OR_NESTED_TYPE, (Object[]) null, typedef);
                typedef = null;
            }
            if (typedef == null) {
                sAttr2 = sAttr;
                str = targetNamespace;
            } else {
                SchemaTypeImpl sTypeImpl = new SchemaTypeImpl(state.getContainer(targetNamespace));
                sTypeImpl.setContainerField(sAttr);
                sTypeImpl.setOuterSchemaTypeRef(outerType == null ? null : outerType.getRef());
                anonymousTypes.add(sTypeImpl);
                sTypeImpl.setSimpleType(true);
                SchemaLocalAttributeImpl schemaLocalAttributeImpl = sAttr;
                LocalSimpleType typedef2 = typedef;
                sAttr2 = schemaLocalAttributeImpl;
                sTypeImpl.setParseContext(typedef2, targetNamespace, chameleon, null, null, false);
                str = targetNamespace;
                sTypeImpl.setAnnotation(SchemaAnnotationImpl.getAnnotation(state.getContainer(str), typedef2));
                sTypeImpl.setUserData(getUserData(typedef2));
                sType2 = sTypeImpl;
            }
            if (sType2 == null && baseModel != null && baseModel.getAttribute(qname) != null) {
                sType2 = baseModel.getAttribute(qname).getType();
                qname2 = qname;
                fmrfixedtext = null;
            } else {
                qname2 = qname;
                fmrfixedtext = null;
            }
        }
        if (sType2 == null) {
            sType2 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
        }
        if (!sType2.isSimpleType()) {
            state.error("Attributes must have a simple type (not complex).", 46, xsdAttr);
            SchemaType sType3 = BuiltinSchemaTypeSystem.ST_ANY_SIMPLE;
            sType = sType3;
        } else {
            sType = sType2;
        }
        if (xsdAttr.isSetUse()) {
            int use2 = translateUseCode(xsdAttr.xgetUse());
            if (use2 != 2 && !isFixed3) {
                deftext = null;
                use = use2;
            } else {
                use = use2;
            }
        }
        if (xsdAttr.isSetDefault() || xsdAttr.isSetFixed()) {
            if (isFixed3 && !xsdAttr.isSetFixed()) {
                state.error("A use of a fixed attribute definition must also be fixed", 9, xsdAttr.xgetFixed());
            }
            boolean isFixed4 = xsdAttr.isSetFixed();
            if (xsdAttr.isSetDefault() && isFixed4) {
                state.error(XmlErrorCodes.SCHEMA_ATTR$DEFAULT_OR_FIXED, (Object[]) null, xsdAttr.xgetFixed());
                isFixed = false;
            } else {
                isFixed = isFixed4;
            }
            deftext = isFixed ? xsdAttr.getFixed() : xsdAttr.getDefault();
            if (fmrfixedtext != null && !fmrfixedtext.equals(deftext)) {
                state.error(XmlErrorCodes.SCHEMA_ATTR$FIXED_NOT_MATCH, (Object[]) null, xsdAttr.xgetFixed());
                deftext = fmrfixedtext;
                isFixed2 = isFixed;
            } else {
                isFixed2 = isFixed;
            }
        } else {
            isFixed2 = isFixed3;
        }
        if (!local) {
            ((SchemaGlobalAttributeImpl) sAttr2).setFilename(findFilename(xsdAttr));
        }
        SOAPArrayType wat = null;
        XmlCursor c = xsdAttr.newCursor();
        try {
            String arrayType = c.getAttributeText(WSDL_ARRAYTYPE_NAME);
            if (c != null) {
                c.close();
            }
            if (arrayType != null) {
                try {
                    wat = new SOAPArrayType(arrayType, new NamespaceContext(xsdAttr));
                } catch (XmlValueOutOfRangeException e) {
                    state.error(XmlErrorCodes.SOAPARRAY, new Object[]{arrayType}, xsdAttr);
                }
            }
            SchemaAnnotationImpl ann = SchemaAnnotationImpl.getAnnotation(state.getContainer(str), xsdAttr);
            SchemaLocalAttributeImpl sAttr3 = sAttr2;
            sAttr3.init(qname2, sType.getRef(), use, deftext, xsdAttr, null, isFixed2, wat, ann, getUserData(xsdAttr));
            return sAttr3;
        } finally {
        }
    }

    static int translateUseCode(Attribute.Use attruse) {
        if (attruse == null) {
            return 2;
        }
        String val = attruse.getStringValue();
        if (val.equals("optional")) {
            return 2;
        }
        if (val.equals("required")) {
            return 3;
        }
        if (!val.equals("prohibited")) {
            return 2;
        }
        return 1;
    }

    static BigInteger buildBigInt(XmlAnySimpleType value) {
        if (value == null) {
            return null;
        }
        String text = value.getStringValue();
        try {
            BigInteger bigInt = new BigInteger(text);
            if (bigInt.signum() < 0) {
                StscState.get().error(XmlErrorCodes.INVALID_VALUE, new Object[]{text, "nonNegativeInteger"}, value);
                return null;
            }
            return bigInt;
        } catch (NumberFormatException e) {
            StscState.get().error(XmlErrorCodes.INVALID_VALUE_DETAIL, new Object[]{text, "nonNegativeInteger", e.getMessage()}, value);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static XmlNonNegativeInteger buildNnInteger(XmlAnySimpleType value) {
        BigInteger bigInt = buildBigInt(value);
        try {
            XmlNonNegativeIntegerImpl i = new XmlNonNegativeIntegerImpl();
            i.setBigIntegerValue(bigInt);
            i.setImmutable();
            return i;
        } catch (XmlValueOutOfRangeException e) {
            StscState.get().error("Internal error processing number", 21, value);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static XmlPositiveInteger buildPosInteger(XmlAnySimpleType value) {
        BigInteger bigInt = buildBigInt(value);
        try {
            XmlPositiveIntegerImpl i = new XmlPositiveIntegerImpl();
            i.setBigIntegerValue(bigInt);
            i.setImmutable();
            return i;
        } catch (XmlValueOutOfRangeException e) {
            StscState.get().error("Internal error processing number", 21, value);
            return null;
        }
    }

    private static Object getUserData(XmlObject pos) {
        XmlCursor c = pos.newCursor();
        try {
            XmlCursor.XmlBookmark b = c.getBookmark(SchemaBookmark.class);
            if (c != null) {
                c.close();
            }
            if (b instanceof SchemaBookmark) {
                return ((SchemaBookmark) b).getValue();
            }
            return null;
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

    private static boolean isEmptySchema(SchemaDocument.Schema schema) {
        XmlCursor cursor = schema.newCursor();
        try {
            boolean z = !cursor.toFirstChild();
            if (cursor != null) {
                cursor.close();
            }
            return z;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (cursor != null) {
                    try {
                        cursor.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static boolean isReservedTypeName(QName name) {
        return BuiltinSchemaTypeSystem.get().findType(name) != null;
    }
}
