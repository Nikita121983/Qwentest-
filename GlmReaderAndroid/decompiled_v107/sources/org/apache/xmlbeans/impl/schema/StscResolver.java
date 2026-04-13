package org.apache.xmlbeans.impl.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.Element;
import org.apache.xmlbeans.impl.xb.xsdschema.KeyrefDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;

/* loaded from: classes11.dex */
public class StscResolver {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static void resolveAll() {
        StscState state = StscState.get();
        SchemaType[] documentTypes = state.documentTypes();
        for (SchemaType schemaType : documentTypes) {
            resolveSubstitutionGroup((SchemaTypeImpl) schemaType);
        }
        List<SchemaType> allSeenTypes = new ArrayList<>();
        allSeenTypes.addAll(Arrays.asList(state.documentTypes()));
        allSeenTypes.addAll(Arrays.asList(state.attributeTypes()));
        allSeenTypes.addAll(Arrays.asList(state.redefinedGlobalTypes()));
        allSeenTypes.addAll(Arrays.asList(state.globalTypes()));
        for (int i = 0; i < allSeenTypes.size(); i++) {
            SchemaType gType = allSeenTypes.get(i);
            resolveType((SchemaTypeImpl) gType);
            allSeenTypes.addAll(Arrays.asList(gType.getAnonymousTypes()));
        }
        resolveIdentityConstraints();
    }

    public static boolean resolveType(SchemaTypeImpl sImpl) {
        if (sImpl.isResolved()) {
            return true;
        }
        if (sImpl.isResolving()) {
            StscState.get().error("Cyclic dependency error", 13, sImpl.getParseObject());
            return false;
        }
        sImpl.startResolving();
        if (sImpl.isDocumentType()) {
            resolveDocumentType(sImpl);
        } else if (sImpl.isAttributeType()) {
            resolveAttributeType(sImpl);
        } else if (sImpl.isSimpleType()) {
            StscSimpleTypeResolver.resolveSimpleType(sImpl);
        } else {
            StscComplexTypeResolver.resolveComplexType(sImpl);
        }
        sImpl.finishResolving();
        return true;
    }

    public static boolean resolveSubstitutionGroup(SchemaTypeImpl sImpl) {
        if (!sImpl.isDocumentType()) {
            throw new AssertionError();
        }
        if (sImpl.isSGResolved()) {
            return true;
        }
        if (sImpl.isSGResolving()) {
            StscState.get().error("Cyclic dependency error", 13, sImpl.getParseObject());
            return false;
        }
        sImpl.startResolvingSGs();
        TopLevelElement elt = (TopLevelElement) sImpl.getParseObject();
        SchemaTypeImpl substitutionGroup = null;
        QName eltName = new QName(sImpl.getTargetNamespace(), elt.getName());
        if (elt.isSetSubstitutionGroup()) {
            substitutionGroup = StscState.get().findDocumentType(elt.getSubstitutionGroup(), sImpl.getChameleonNamespace(), sImpl.getTargetNamespace());
            if (substitutionGroup == null) {
                StscState.get().notFoundError(elt.getSubstitutionGroup(), 1, elt.xgetSubstitutionGroup(), true);
            } else if (!resolveSubstitutionGroup(substitutionGroup)) {
                substitutionGroup = null;
            } else {
                sImpl.setSubstitutionGroup(elt.getSubstitutionGroup());
            }
        }
        while (substitutionGroup != null) {
            substitutionGroup.addSubstitutionGroupMember(eltName);
            if (substitutionGroup.getSubstitutionGroup() == null) {
                break;
            }
            substitutionGroup = StscState.get().findDocumentType(substitutionGroup.getSubstitutionGroup(), substitutionGroup.getChameleonNamespace(), null);
            if (substitutionGroup == null) {
                throw new AssertionError("Could not find document type for substitutionGroup");
            }
            if (!resolveSubstitutionGroup(substitutionGroup)) {
                substitutionGroup = null;
            }
        }
        sImpl.finishResolvingSGs();
        return true;
    }

    public static void resolveDocumentType(SchemaTypeImpl sImpl) {
        SchemaTypeImpl findDocumentType;
        if (!sImpl.isResolving()) {
            throw new AssertionError();
        }
        if (!sImpl.isDocumentType()) {
            throw new AssertionError();
        }
        List<SchemaType> anonTypes = new ArrayList<>();
        SchemaGlobalElementImpl element = (SchemaGlobalElementImpl) StscTranslator.translateElement((Element) sImpl.getParseObject(), sImpl.getTargetNamespace(), sImpl.isChameleon(), null, null, anonTypes, sImpl);
        SchemaLocalElementImpl contentModel = null;
        if (element != null) {
            StscState.get().addGlobalElement(element);
            contentModel = new SchemaLocalElementImpl();
            contentModel.setParticleType(4);
            StscTranslator.copyGlobalElementToLocalElement(element, contentModel);
            contentModel.setMinOccurs(BigInteger.ONE);
            contentModel.setMaxOccurs(BigInteger.ONE);
            contentModel.setTransitionNotes(QNameSet.EMPTY, true);
        }
        Map elementPropertyModel = StscComplexTypeResolver.buildContentPropertyModelByQName(contentModel, sImpl);
        if (sImpl.getSubstitutionGroup() == null) {
            findDocumentType = BuiltinSchemaTypeSystem.ST_ANY_TYPE;
        } else {
            findDocumentType = StscState.get().findDocumentType(sImpl.getSubstitutionGroup(), sImpl.isChameleon() ? sImpl.getTargetNamespace() : null, null);
        }
        SchemaTypeImpl baseType = findDocumentType;
        sImpl.setBaseTypeRef(baseType.getRef());
        sImpl.setBaseDepth(baseType.getBaseDepth() + 1);
        sImpl.setDerivationType(1);
        sImpl.setComplexTypeVariety(3);
        sImpl.setContentModel(contentModel, new SchemaAttributeModelImpl(), elementPropertyModel, Collections.EMPTY_MAP, false);
        sImpl.setWildcardSummary(QNameSet.EMPTY, false, QNameSet.EMPTY, false);
        sImpl.setAnonymousTypeRefs(makeRefArray(anonTypes));
    }

    public static void resolveAttributeType(SchemaTypeImpl schemaTypeImpl) {
        if (!schemaTypeImpl.isResolving()) {
            throw new AssertionError();
        }
        if (!schemaTypeImpl.isAttributeType()) {
            throw new AssertionError();
        }
        List<SchemaType> anonTypes = new ArrayList<>();
        SchemaGlobalAttributeImpl attribute = (SchemaGlobalAttributeImpl) StscTranslator.translateAttribute((Attribute) schemaTypeImpl.getParseObject(), schemaTypeImpl.getTargetNamespace(), null, schemaTypeImpl.isChameleon(), anonTypes, schemaTypeImpl, null, false);
        SchemaAttributeModelImpl attributeModel = new SchemaAttributeModelImpl();
        if (attribute != null) {
            StscState.get().addGlobalAttribute(attribute);
            SchemaLocalAttributeImpl attributeCopy = new SchemaLocalAttributeImpl();
            StscTranslator.copyGlobalAttributeToLocalAttribute(attribute, attributeCopy);
            attributeModel.addAttribute(attributeCopy);
        }
        schemaTypeImpl.setBaseTypeRef(BuiltinSchemaTypeSystem.ST_ANY_TYPE.getRef());
        schemaTypeImpl.setBaseDepth(schemaTypeImpl.getBaseDepth() + 1);
        schemaTypeImpl.setDerivationType(1);
        schemaTypeImpl.setComplexTypeVariety(1);
        Map<QName, SchemaProperty> attributePropertyModel = StscComplexTypeResolver.buildAttributePropertyModelByQName(attributeModel, schemaTypeImpl);
        schemaTypeImpl.setContentModel(null, attributeModel, Collections.EMPTY_MAP, attributePropertyModel, false);
        schemaTypeImpl.setWildcardSummary(QNameSet.EMPTY, false, QNameSet.EMPTY, false);
        schemaTypeImpl.setAnonymousTypeRefs(makeRefArray(anonTypes));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SchemaType.Ref[] lambda$makeRefArray$0(int x$0) {
        return new SchemaType.Ref[x$0];
    }

    private static SchemaType.Ref[] makeRefArray(Collection<SchemaType> typeList) {
        return (SchemaType.Ref[]) typeList.stream().map(new StscComplexTypeResolver$$ExternalSyntheticLambda21()).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.schema.StscResolver$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return StscResolver.lambda$makeRefArray$0(i);
            }
        });
    }

    public static void resolveIdentityConstraints() {
        StscState state = StscState.get();
        SchemaIdentityConstraintImpl[] idcs = state.idConstraints();
        for (SchemaIdentityConstraintImpl idc : idcs) {
            if (!idc.isResolved()) {
                KeyrefDocument.Keyref xsdkr = (KeyrefDocument.Keyref) idc.getParseObject();
                QName keyName = xsdkr.getRefer();
                SchemaIdentityConstraintImpl key = state.findIdConstraint(keyName, idc.getChameleonNamespace(), idc.getTargetNamespace());
                if (key == null) {
                    state.notFoundError(keyName, 5, xsdkr, true);
                } else {
                    if (key.getConstraintCategory() == 2) {
                        state.error(XmlErrorCodes.IDENTITY_CONSTRAINT_PROPERTIES$KEYREF_REFERS_TO_KEYREF, (Object[]) null, idc.getParseObject());
                    }
                    if (key.getFields().length != idc.getFields().length) {
                        state.error(XmlErrorCodes.IDENTITY_CONSTRAINT_PROPERTIES$KEY_KEYREF_FIELD_COUNT_EQ, (Object[]) null, idc.getParseObject());
                    }
                    idc.setReferencedKey(key.getRef());
                }
            }
        }
    }
}
