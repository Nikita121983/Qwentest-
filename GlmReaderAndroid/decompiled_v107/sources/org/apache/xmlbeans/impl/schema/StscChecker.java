package org.apache.xmlbeans.impl.schema;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlNOTATION;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XBeanDebug;

/* loaded from: classes11.dex */
public class StscChecker {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static void checkAll() {
        StscState state = StscState.get();
        List<SchemaType> allSeenTypes = new ArrayList<>();
        allSeenTypes.addAll(Arrays.asList(state.documentTypes()));
        allSeenTypes.addAll(Arrays.asList(state.attributeTypes()));
        allSeenTypes.addAll(Arrays.asList(state.redefinedGlobalTypes()));
        allSeenTypes.addAll(Arrays.asList(state.globalTypes()));
        for (int i = 0; i < allSeenTypes.size(); i++) {
            SchemaType gType = allSeenTypes.get(i);
            if (!state.noPvr() && !gType.isDocumentType()) {
                checkRestriction((SchemaTypeImpl) gType);
            }
            checkFields((SchemaTypeImpl) gType);
            allSeenTypes.addAll(Arrays.asList(gType.getAnonymousTypes()));
        }
        checkSubstitutionGroups(state.globalElements());
    }

    public static void checkFields(SchemaTypeImpl sType) {
        SchemaType[] members;
        if (sType.isSimpleType()) {
            return;
        }
        XmlObject location = sType.getParseObject();
        SchemaAttributeModel sAttrModel = sType.getAttributeModel();
        if (sAttrModel != null) {
            SchemaLocalAttribute[] sAttrs = sAttrModel.getAttributes();
            QName idAttr = null;
            for (SchemaLocalAttribute sAttr : sAttrs) {
                XmlObject attrLocation = ((SchemaLocalAttributeImpl) sAttr)._parseObject;
                if (XmlID.type.isAssignableFrom(sAttr.getType())) {
                    if (idAttr == null) {
                        idAttr = sAttr.getName();
                    } else {
                        StscState.get().error(XmlErrorCodes.ATTR_GROUP_PROPERTIES$TWO_IDS, new Object[]{QNameHelper.pretty(idAttr), sAttr.getName()}, attrLocation != null ? attrLocation : location);
                    }
                    if (sAttr.getDefaultText() != null) {
                        StscState.get().error(XmlErrorCodes.ATTR_PROPERTIES$ID_FIXED_OR_DEFAULT, (Object[]) null, attrLocation != null ? attrLocation : location);
                    }
                } else if (XmlNOTATION.type.isAssignableFrom(sAttr.getType())) {
                    int i = 8;
                    if (sAttr.getType().getBuiltinTypeCode() == 8) {
                        StscState.get().recover(XmlErrorCodes.ATTR_NOTATION_TYPE_FORBIDDEN, new Object[]{QNameHelper.pretty(sAttr.getName())}, attrLocation != null ? attrLocation : location);
                    } else {
                        if (sAttr.getType().getSimpleVariety() == 2) {
                            SchemaType[] members2 = sAttr.getType().getUnionConstituentTypes();
                            int length = members2.length;
                            int i2 = 0;
                            while (i2 < length) {
                                SchemaType member = members2[i2];
                                if (member.getBuiltinTypeCode() != i) {
                                    members = members2;
                                } else {
                                    members = members2;
                                    StscState.get().recover(XmlErrorCodes.ATTR_NOTATION_TYPE_FORBIDDEN, new Object[]{QNameHelper.pretty(sAttr.getName())}, attrLocation != null ? attrLocation : location);
                                }
                                i2++;
                                members2 = members;
                                i = 8;
                            }
                        }
                        boolean hasNS = true;
                        if (sType.isAttributeType()) {
                            if (sAttr.getName().getNamespaceURI().length() <= 0) {
                                hasNS = false;
                            }
                        } else {
                            SchemaType t = sType;
                            while (t.getOuterType() != null) {
                                t = t.getOuterType();
                            }
                            if (t.isDocumentType()) {
                                if (t.getDocumentElementName().getNamespaceURI().length() <= 0) {
                                    hasNS = false;
                                }
                            } else if (t.getName().getNamespaceURI().length() <= 0) {
                                hasNS = false;
                            }
                        }
                        if (hasNS) {
                            StscState.get().warning(XmlErrorCodes.ATTR_COMPATIBILITY_TARGETNS, new Object[]{QNameHelper.pretty(sAttr.getName())}, attrLocation != null ? attrLocation : location);
                        }
                    }
                } else {
                    String valueConstraint = sAttr.getDefaultText();
                    if (valueConstraint != null) {
                        try {
                            XmlAnySimpleType val = sAttr.getDefaultValue();
                            if (val.validate()) {
                                SchemaPropertyImpl sProp = (SchemaPropertyImpl) sType.getAttributeProperty(sAttr.getName());
                                if (sProp != null && sProp.getDefaultText() != null) {
                                    sProp.setDefaultValue(new XmlValueRef(val));
                                }
                            } else {
                                throw new Exception();
                                break;
                            }
                        } catch (Exception e) {
                            String constraintName = sAttr.isFixed() ? "fixed" : "default";
                            XmlObject constraintLocation = location;
                            if (attrLocation != null && (constraintLocation = attrLocation.selectAttribute("", constraintName)) == null) {
                                constraintLocation = attrLocation;
                            }
                            StscState.get().error(XmlErrorCodes.ATTR_PROPERTIES$CONSTRAINT_VALID, new Object[]{QNameHelper.pretty(sAttr.getName()), constraintName, valueConstraint, QNameHelper.pretty(sAttr.getType().getName())}, constraintLocation);
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        checkElementDefaults(sType.getContentModel(), location, sType);
    }

    private static void checkElementDefaults(SchemaParticle model, XmlObject location, SchemaType parentType) {
        XmlObject selectAttribute;
        boolean hasNS;
        XmlObject selectAttribute2;
        XmlObject selectAttribute3;
        String constraintName;
        if (model == null) {
            return;
        }
        switch (model.getParticleType()) {
            case 1:
            case 2:
            case 3:
                SchemaParticle[] children = model.getParticleChildren();
                for (SchemaParticle child : children) {
                    checkElementDefaults(child, location, parentType);
                }
                return;
            case 4:
                String valueConstraint = model.getDefaultText();
                if (valueConstraint != null) {
                    if (model.getType().isSimpleType() || model.getType().getContentType() == 2) {
                        try {
                            XmlAnySimpleType val = model.getDefaultValue();
                            XmlOptions opt = new XmlOptions();
                            opt.setValidateTextOnly();
                            if (!val.validate(opt)) {
                                throw new Exception();
                            }
                            SchemaPropertyImpl sProp = (SchemaPropertyImpl) parentType.getElementProperty(model.getName());
                            if (sProp != null && sProp.getDefaultText() != null) {
                                sProp.setDefaultValue(new XmlValueRef(val));
                            }
                        } catch (Exception e) {
                            constraintName = model.isFixed() ? "fixed" : "default";
                            XmlObject constraintLocation = location.selectAttribute("", constraintName);
                            StscState.get().error(XmlErrorCodes.ELEM_PROPERTIES$CONSTRAINT_VALID, new Object[]{QNameHelper.pretty(model.getName()), constraintName, valueConstraint, QNameHelper.pretty(model.getType().getName())}, constraintLocation == null ? location : constraintLocation);
                        }
                    } else if (model.getType().getContentType() == 4) {
                        if (!model.getType().getContentModel().isSkippable()) {
                            constraintName = model.isFixed() ? "fixed" : "default";
                            XmlObject constraintLocation2 = location.selectAttribute("", constraintName);
                            StscState.get().error(XmlErrorCodes.ELEM_DEFAULT_VALID$MIXED_AND_EMPTIABLE, new Object[]{QNameHelper.pretty(model.getName()), constraintName, valueConstraint}, constraintLocation2 == null ? location : constraintLocation2);
                        } else {
                            SchemaPropertyImpl sProp2 = (SchemaPropertyImpl) parentType.getElementProperty(model.getName());
                            if (sProp2 != null && sProp2.getDefaultText() != null) {
                                sProp2.setDefaultValue(new XmlValueRef(XmlString.type.newValue(valueConstraint)));
                            }
                        }
                    } else if (model.getType().getContentType() == 3) {
                        XmlObject constraintLocation3 = location.selectAttribute("", "default");
                        StscState.get().error(XmlErrorCodes.ELEM_DEFAULT_VALID$SIMPLE_TYPE_OR_MIXED, new Object[]{QNameHelper.pretty(model.getName()), valueConstraint, "element"}, constraintLocation3 == null ? location : constraintLocation3);
                    } else if (model.getType().getContentType() == 1) {
                        XmlObject constraintLocation4 = location.selectAttribute("", "default");
                        StscState.get().error(XmlErrorCodes.ELEM_DEFAULT_VALID$SIMPLE_TYPE_OR_MIXED, new Object[]{QNameHelper.pretty(model.getName()), valueConstraint, "empty"}, constraintLocation4 == null ? location : constraintLocation4);
                    }
                }
                String warningType = null;
                if (BuiltinSchemaTypeSystem.ST_ID.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_ID.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_IDREF.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_IDREF.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_IDREFS.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_IDREFS.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_ENTITY.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_ENTITY.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_ENTITIES.isAssignableFrom(model.getType())) {
                    warningType = BuiltinSchemaTypeSystem.ST_ENTITIES.getName().getLocalPart();
                } else if (BuiltinSchemaTypeSystem.ST_NOTATION.isAssignableFrom(model.getType())) {
                    if (model.getType().getBuiltinTypeCode() != 8) {
                        if (model.getType().getSimpleVariety() == 2) {
                            SchemaType[] members = model.getType().getUnionConstituentTypes();
                            for (SchemaType member : members) {
                                if (member.getBuiltinTypeCode() == 8) {
                                    StscState stscState = StscState.get();
                                    Object[] objArr = {QNameHelper.pretty(model.getName())};
                                    if (((SchemaLocalElementImpl) model)._parseObject == null) {
                                        selectAttribute = location;
                                    } else {
                                        selectAttribute = ((SchemaLocalElementImpl) model)._parseObject.selectAttribute("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
                                    }
                                    stscState.recover(XmlErrorCodes.ELEM_NOTATION_TYPE_FORBIDDEN, objArr, selectAttribute);
                                }
                            }
                        }
                        warningType = BuiltinSchemaTypeSystem.ST_NOTATION.getName().getLocalPart();
                    } else {
                        StscState stscState2 = StscState.get();
                        Object[] objArr2 = {QNameHelper.pretty(model.getName())};
                        if (((SchemaLocalElementImpl) model)._parseObject == null) {
                            selectAttribute2 = location;
                        } else {
                            selectAttribute2 = ((SchemaLocalElementImpl) model)._parseObject.selectAttribute("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
                        }
                        stscState2.recover(XmlErrorCodes.ELEM_NOTATION_TYPE_FORBIDDEN, objArr2, selectAttribute2);
                    }
                    SchemaType t = parentType;
                    while (t.getOuterType() != null) {
                        t = t.getOuterType();
                    }
                    if (t.isDocumentType()) {
                        hasNS = t.getDocumentElementName().getNamespaceURI().length() > 0;
                    } else {
                        hasNS = t.getName().getNamespaceURI().length() > 0;
                    }
                    if (hasNS) {
                        StscState.get().warning(XmlErrorCodes.ELEM_COMPATIBILITY_TARGETNS, new Object[]{QNameHelper.pretty(model.getName())}, ((SchemaLocalElementImpl) model)._parseObject == null ? location : ((SchemaLocalElementImpl) model)._parseObject);
                    }
                }
                if (warningType != null) {
                    StscState stscState3 = StscState.get();
                    Object[] objArr3 = {QNameHelper.pretty(model.getName()), warningType};
                    if (((SchemaLocalElementImpl) model)._parseObject == null) {
                        selectAttribute3 = location;
                    } else {
                        selectAttribute3 = ((SchemaLocalElementImpl) model)._parseObject.selectAttribute("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
                    }
                    stscState3.warning(XmlErrorCodes.ELEM_COMPATIBILITY_TYPE, objArr3, selectAttribute3);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x003a. Please report as an issue. */
    public static boolean checkRestriction(SchemaTypeImpl sType) {
        if (sType.getDerivationType() == 1 && !sType.isSimpleType()) {
            StscState state = StscState.get();
            XmlObject location = sType.getParseObject();
            SchemaType baseType = sType.getBaseType();
            if (baseType == null) {
                throw new AssertionError();
            }
            if (baseType.isSimpleType()) {
                state.error(XmlErrorCodes.SCHEMA_COMPLEX_TYPE$COMPLEX_CONTENT, new Object[]{QNameHelper.pretty(baseType.getName())}, location);
                return false;
            }
            switch (sType.getContentType()) {
                case 1:
                    switch (baseType.getContentType()) {
                        case 1:
                            break;
                        case 2:
                        default:
                            state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$EMPTY_AND_NOT_SIMPLE, (Object[]) null, location);
                            return false;
                        case 3:
                        case 4:
                            if (baseType.getContentModel() != null && !baseType.getContentModel().isSkippable()) {
                                state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$EMPTY_AND_ELEMENT_OR_MIXED_EMPTIABLE, (Object[]) null, location);
                                return false;
                            }
                            break;
                    }
                case 2:
                    switch (baseType.getContentType()) {
                        case 2:
                            SchemaType cType = sType.getContentBasedOnType();
                            if (cType != baseType) {
                                SchemaType bType = baseType;
                                while (bType != null && !bType.isSimpleType()) {
                                    bType = bType.getContentBasedOnType();
                                }
                                if (bType != null && !bType.isAssignableFrom(cType)) {
                                    state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$SC_NOT_DERIVED, (Object[]) null, location);
                                    return false;
                                }
                            }
                            break;
                        case 3:
                        default:
                            state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$SC_AND_SIMPLE_TYPE_OR_MIXED, (Object[]) null, location);
                            return false;
                        case 4:
                            if (baseType.getContentModel() != null && !baseType.getContentModel().isSkippable()) {
                                state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$SC_AND_MIXED_EMPTIABLE, (Object[]) null, location);
                                return false;
                            }
                            break;
                    }
                case 4:
                    if (baseType.getContentType() != 4) {
                        state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_MIXED, (Object[]) null, location);
                        return false;
                    }
                case 3:
                    if (baseType.getContentType() == 1) {
                        state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_EMPTY, (Object[]) null, location);
                        return false;
                    }
                    if (baseType.getContentType() == 2) {
                        state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_SIMPLE, (Object[]) null, location);
                        return false;
                    }
                    SchemaParticle baseModel = baseType.getContentModel();
                    SchemaParticle derivedModel = sType.getContentModel();
                    if (derivedModel != null || sType.getDerivationType() != 1) {
                        if (baseModel == null || derivedModel == null) {
                            XBeanDebug.LOG.atTrace().withThrowable(new Exception("Stacktrace")).log("Null models that weren't caught by EMPTY_CONTENT: {} ({}), {} ({})", baseType, baseModel, sType, derivedModel);
                            state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_VALID, (Object[]) null, location);
                            return false;
                        }
                        List<XmlError> errors = new ArrayList<>();
                        boolean isValid = isParticleValidRestriction(baseModel, derivedModel, errors, location);
                        if (!isValid) {
                            if (errors.isEmpty()) {
                                state.error(XmlErrorCodes.COMPLEX_TYPE_RESTRICTION$ELEMENT_OR_MIXED_AND_VALID, (Object[]) null, location);
                            } else {
                                state.getErrorListener().add(errors.get(errors.size() - 1));
                            }
                            return false;
                        }
                        break;
                    } else {
                        return true;
                    }
            }
        }
        return true;
    }

    public static boolean isParticleValidRestriction(SchemaParticle baseModel, SchemaParticle derivedModel, Collection<XmlError> errors, XmlObject context) {
        if (baseModel.equals(derivedModel)) {
            return true;
        }
        switch (baseModel.getParticleType()) {
            case 1:
                switch (derivedModel.getParticleType()) {
                    case 1:
                        boolean restrictionValid = recurse(baseModel, derivedModel, errors, context);
                        return restrictionValid;
                    case 2:
                    case 5:
                        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
                        return false;
                    case 3:
                        boolean restrictionValid2 = recurseUnordered(baseModel, derivedModel, errors, context);
                        return restrictionValid2;
                    case 4:
                        boolean restrictionValid3 = recurseAsIfGroup(baseModel, derivedModel, errors, context);
                        return restrictionValid3;
                    default:
                        XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                        throw new AssertionError();
                }
            case 2:
                switch (derivedModel.getParticleType()) {
                    case 1:
                    case 5:
                        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
                        return false;
                    case 2:
                        boolean restrictionValid4 = recurseLax(baseModel, derivedModel, errors, context);
                        return restrictionValid4;
                    case 3:
                        boolean restrictionValid5 = mapAndSum(baseModel, derivedModel, errors, context);
                        return restrictionValid5;
                    case 4:
                        boolean restrictionValid6 = recurseAsIfGroup(baseModel, derivedModel, errors, context);
                        return restrictionValid6;
                    default:
                        XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                        throw new AssertionError();
                }
            case 3:
                switch (derivedModel.getParticleType()) {
                    case 1:
                    case 2:
                    case 5:
                        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
                        return false;
                    case 3:
                        boolean restrictionValid7 = recurse(baseModel, derivedModel, errors, context);
                        return restrictionValid7;
                    case 4:
                        boolean restrictionValid8 = recurseAsIfGroup(baseModel, derivedModel, errors, context);
                        return restrictionValid8;
                    default:
                        XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                        throw new AssertionError();
                }
            case 4:
                switch (derivedModel.getParticleType()) {
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION$INVALID_RESTRICTION, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
                        return false;
                    case 4:
                        boolean restrictionValid9 = nameAndTypeOK((SchemaLocalElement) baseModel, (SchemaLocalElement) derivedModel, errors, context);
                        return restrictionValid9;
                    default:
                        XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                        throw new AssertionError();
                }
            case 5:
                switch (derivedModel.getParticleType()) {
                    case 1:
                    case 2:
                    case 3:
                        boolean restrictionValid10 = nsRecurseCheckCardinality(baseModel, derivedModel, errors, context);
                        return restrictionValid10;
                    case 4:
                        boolean restrictionValid11 = nsCompat(baseModel, (SchemaLocalElement) derivedModel, errors, context);
                        return restrictionValid11;
                    case 5:
                        boolean restrictionValid12 = nsSubset(baseModel, derivedModel, errors, context);
                        return restrictionValid12;
                    default:
                        XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Derived Type");
                        throw new AssertionError();
                }
            default:
                XBeanDebug.LOG.atDebug().withThrowable(new Exception("stacktrace")).log("Unknown schema type for Base Type");
                throw new AssertionError();
        }
    }

    private static boolean mapAndSum(SchemaParticle baseModel, SchemaParticle derivedModel, Collection<XmlError> errors, XmlObject context) {
        BigInteger derivedRangeMax;
        if (baseModel.getParticleType() != 2) {
            throw new AssertionError();
        }
        if (derivedModel.getParticleType() != 3) {
            throw new AssertionError();
        }
        SchemaParticle[] derivedParticleArray = derivedModel.getParticleChildren();
        SchemaParticle[] baseParticleArray = baseModel.getParticleChildren();
        for (SchemaParticle derivedParticle : derivedParticleArray) {
            boolean foundMatch = false;
            int length = baseParticleArray.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                SchemaParticle baseParticle = baseParticleArray[i];
                if (!isParticleValidRestriction(baseParticle, derivedParticle, errors, context)) {
                    i++;
                } else {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_MAP_AND_SUM$MAP, new Object[]{printParticle(derivedParticle)}, context));
                return false;
            }
        }
        BigInteger derivedRangeMin = derivedModel.getMinOccurs().multiply(BigInteger.valueOf(derivedModel.getParticleChildren().length));
        if (derivedModel.getMaxOccurs() == null) {
            derivedRangeMax = null;
        } else {
            BigInteger derivedRangeMax2 = derivedModel.getMaxOccurs();
            derivedRangeMax = derivedRangeMax2.multiply(BigInteger.valueOf(derivedModel.getParticleChildren().length));
        }
        if (derivedRangeMin.compareTo(baseModel.getMinOccurs()) < 0) {
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_MAP_AND_SUM$SUM_MIN_OCCURS_GTE_MIN_OCCURS, new Object[]{derivedRangeMin.toString(), baseModel.getMinOccurs().toString()}, context));
            return false;
        }
        if (baseModel.getMaxOccurs() == null) {
            return true;
        }
        if (derivedRangeMax != null && derivedRangeMax.compareTo(baseModel.getMaxOccurs()) <= 0) {
            return true;
        }
        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_MAP_AND_SUM$SUM_MAX_OCCURS_LTE_MAX_OCCURS, new Object[]{derivedRangeMax == null ? "unbounded" : derivedRangeMax.toString(), baseModel.getMaxOccurs().toString()}, context));
        return false;
    }

    private static boolean recurseAsIfGroup(SchemaParticle baseModel, SchemaParticle derivedModel, Collection<XmlError> errors, XmlObject context) {
        if ((baseModel.getParticleType() != 1 || derivedModel.getParticleType() != 4) && ((baseModel.getParticleType() != 2 || derivedModel.getParticleType() != 4) && (baseModel.getParticleType() != 3 || derivedModel.getParticleType() != 4))) {
            throw new AssertionError();
        }
        SchemaParticleImpl asIfPart = new SchemaParticleImpl();
        asIfPart.setParticleType(baseModel.getParticleType());
        asIfPart.setMinOccurs(BigInteger.ONE);
        asIfPart.setMaxOccurs(BigInteger.ONE);
        asIfPart.setParticleChildren(new SchemaParticle[]{derivedModel});
        return isParticleValidRestriction(baseModel, asIfPart, errors, context);
    }

    private static boolean recurseLax(SchemaParticle baseModel, SchemaParticle derivedModel, Collection<XmlError> errors, XmlObject context) {
        if (baseModel.getParticleType() != 2 || derivedModel.getParticleType() != 2) {
            throw new AssertionError();
        }
        if (!occurrenceRangeOK(baseModel, derivedModel, errors, context)) {
            return false;
        }
        SchemaParticle[] derivedParticleArray = derivedModel.getParticleChildren();
        SchemaParticle[] baseParticleArray = baseModel.getParticleChildren();
        int i = 0;
        int j = 0;
        while (i < derivedParticleArray.length && j < baseParticleArray.length) {
            SchemaParticle derivedParticle = derivedParticleArray[i];
            SchemaParticle baseParticle = baseParticleArray[j];
            if (isParticleValidRestriction(baseParticle, derivedParticle, errors, context)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        if (i >= derivedParticleArray.length) {
            return true;
        }
        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE_LAX$MAP, new Object[]{printParticles(baseParticleArray, i)}, context));
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
    
        r0 = false;
        r14.add(org.apache.xmlbeans.XmlError.forObject(org.apache.xmlbeans.XmlErrorCodes.PARTICLE_DERIVATION_RECURSE_UNORDERED$MAP_MAX_OCCURS_1, new java.lang.Object[]{printParticle(r7), printMaxOccurs(r7.getMinOccurs())}, r15));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean recurseUnordered(org.apache.xmlbeans.SchemaParticle r12, org.apache.xmlbeans.SchemaParticle r13, java.util.Collection<org.apache.xmlbeans.XmlError> r14, org.apache.xmlbeans.XmlObject r15) {
        /*
            int r0 = r12.getParticleType()
            r1 = 1
            if (r0 != r1) goto Lfb
            int r0 = r13.getParticleType()
            r1 = 3
            if (r0 != r1) goto Lfb
            r0 = 1
            boolean r1 = occurrenceRangeOK(r12, r13, r14, r15)
            r2 = 0
            if (r1 != 0) goto L17
            return r2
        L17:
            org.apache.xmlbeans.SchemaParticle[] r1 = r12.getParticleChildren()
            java.util.HashMap r3 = new java.util.HashMap
            r4 = 10
            r3.<init>(r4)
            java.lang.Object r4 = new java.lang.Object
            r4.<init>()
            int r5 = r1.length
            r6 = r2
        L29:
            if (r6 >= r5) goto L37
            r7 = r1[r6]
            javax.xml.namespace.QName r8 = r7.getName()
            r3.put(r8, r7)
            int r6 = r6 + 1
            goto L29
        L37:
            org.apache.xmlbeans.SchemaParticle[] r5 = r13.getParticleChildren()
            int r6 = r5.length
        L3c:
            if (r2 >= r6) goto Lb8
            r7 = r5[r2]
            javax.xml.namespace.QName r8 = r7.getName()
            java.lang.Object r8 = r3.get(r8)
            if (r8 != 0) goto L5e
            r0 = 0
            java.lang.String r2 = printParticle(r7)
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r6 = "rcase-RecurseUnordered.2"
            org.apache.xmlbeans.XmlError r2 = org.apache.xmlbeans.XmlError.forObject(r6, r2, r15)
            r14.add(r2)
            goto Lb8
        L5e:
            if (r8 != r4) goto L74
            r0 = 0
            java.lang.String r2 = printParticle(r7)
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            java.lang.String r6 = "rcase-RecurseUnordered.2.1"
            org.apache.xmlbeans.XmlError r2 = org.apache.xmlbeans.XmlError.forObject(r6, r2, r15)
            r14.add(r2)
            goto Lb8
        L74:
            r9 = r8
            org.apache.xmlbeans.SchemaParticle r9 = (org.apache.xmlbeans.SchemaParticle) r9
            java.math.BigInteger r10 = r7.getMaxOccurs()
            if (r10 == 0) goto L9c
            java.math.BigInteger r10 = r7.getMaxOccurs()
            java.math.BigInteger r11 = java.math.BigInteger.ONE
            int r10 = r10.compareTo(r11)
            if (r10 <= 0) goto L8a
            goto L9c
        L8a:
            boolean r10 = isParticleValidRestriction(r9, r7, r14, r15)
            if (r10 != 0) goto L92
            r0 = 0
            goto Lb8
        L92:
            javax.xml.namespace.QName r10 = r7.getName()
            r3.put(r10, r4)
            int r2 = r2 + 1
            goto L3c
        L9c:
            r0 = 0
            java.lang.String r2 = printParticle(r7)
            java.math.BigInteger r6 = r7.getMinOccurs()
            java.lang.String r6 = printMaxOccurs(r6)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r6}
            java.lang.String r6 = "rcase-RecurseUnordered.2.2a"
            org.apache.xmlbeans.XmlError r2 = org.apache.xmlbeans.XmlError.forObject(r6, r2, r15)
            r14.add(r2)
        Lb8:
            if (r0 == 0) goto Lfa
            java.util.Set r2 = r3.keySet()
            java.util.Iterator r6 = r2.iterator()
        Lc2:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto Lfa
            java.lang.Object r7 = r6.next()
            javax.xml.namespace.QName r7 = (javax.xml.namespace.QName) r7
            java.lang.Object r8 = r3.get(r7)
            if (r8 == r4) goto Lf9
            java.lang.Object r8 = r3.get(r7)
            org.apache.xmlbeans.SchemaParticle r8 = (org.apache.xmlbeans.SchemaParticle) r8
            boolean r8 = r8.isSkippable()
            if (r8 != 0) goto Lf9
            r0 = 0
            java.lang.Object r8 = r3.get(r7)
            org.apache.xmlbeans.SchemaParticle r8 = (org.apache.xmlbeans.SchemaParticle) r8
            java.lang.String r8 = printParticle(r8)
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r9 = "rcase-RecurseUnordered.2.3"
            org.apache.xmlbeans.XmlError r8 = org.apache.xmlbeans.XmlError.forObject(r9, r8, r15)
            r14.add(r8)
        Lf9:
            goto Lc2
        Lfa:
            return r0
        Lfb:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.recurseUnordered(org.apache.xmlbeans.SchemaParticle, org.apache.xmlbeans.SchemaParticle, java.util.Collection, org.apache.xmlbeans.XmlObject):boolean");
    }

    private static boolean recurse(SchemaParticle baseModel, SchemaParticle derivedModel, Collection<XmlError> errors, XmlObject context) {
        boolean recurseValid = true;
        if (!occurrenceRangeOK(baseModel, derivedModel, errors, context)) {
            return false;
        }
        SchemaParticle[] derivedParticleArray = derivedModel.getParticleChildren();
        SchemaParticle[] baseParticleArray = baseModel.getParticleChildren();
        int i = 0;
        int j = 0;
        while (true) {
            if (i >= derivedParticleArray.length || j >= baseParticleArray.length) {
                break;
            }
            SchemaParticle derivedParticle = derivedParticleArray[i];
            SchemaParticle baseParticle = baseParticleArray[j];
            if (isParticleValidRestriction(baseParticle, derivedParticle, errors, context)) {
                i++;
                j++;
            } else if (baseParticle.isSkippable()) {
                j++;
            } else {
                recurseValid = false;
                errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE$MAP_VALID, new Object[]{printParticle(derivedParticle), printParticle(derivedModel), printParticle(baseParticle), printParticle(baseModel)}, context));
                break;
            }
        }
        if (i < derivedParticleArray.length) {
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE$MAP, new Object[]{printParticle(derivedModel), printParticle(baseModel), printParticles(derivedParticleArray, i)}, context));
            return false;
        }
        if (j < baseParticleArray.length) {
            ArrayList<SchemaParticle> particles = new ArrayList<>(baseParticleArray.length);
            for (int k = j; k < baseParticleArray.length; k++) {
                if (!baseParticleArray[k].isSkippable()) {
                    particles.add(baseParticleArray[k]);
                }
            }
            if (!particles.isEmpty()) {
                errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_RECURSE$UNMAPPED_ARE_EMPTIABLE, new Object[]{printParticle(baseModel), printParticle(derivedModel), printParticles(particles)}, context));
                return false;
            }
            return recurseValid;
        }
        return recurseValid;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x007c A[LOOP:0: B:13:0x005b->B:20:0x007c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean nsRecurseCheckCardinality(org.apache.xmlbeans.SchemaParticle r7, org.apache.xmlbeans.SchemaParticle r8, java.util.Collection<org.apache.xmlbeans.XmlError> r9, org.apache.xmlbeans.XmlObject r10) {
        /*
            int r0 = r7.getParticleType()
            r1 = 5
            if (r0 != r1) goto L86
            int r0 = r8.getParticleType()
            r1 = 1
            if (r0 == r1) goto L23
            int r0 = r8.getParticleType()
            r2 = 2
            if (r0 == r2) goto L23
            int r0 = r8.getParticleType()
            r2 = 3
            if (r0 != r2) goto L1d
            goto L23
        L1d:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L23:
            r0 = 1
            org.apache.xmlbeans.impl.schema.SchemaParticleImpl r2 = new org.apache.xmlbeans.impl.schema.SchemaParticleImpl
            r2.<init>()
            int r3 = r7.getParticleType()
            r2.setParticleType(r3)
            int r3 = r7.getWildcardProcess()
            r2.setWildcardProcess(r3)
            org.apache.xmlbeans.QNameSet r3 = r7.getWildcardSet()
            r2.setWildcardSet(r3)
            java.math.BigInteger r3 = java.math.BigInteger.ZERO
            r2.setMinOccurs(r3)
            r3 = 0
            r2.setMaxOccurs(r3)
            org.apache.xmlbeans.QNameSet r3 = r7.getWildcardSet()
            r2.setTransitionRules(r3, r1)
            org.apache.xmlbeans.QNameSet r3 = r7.getWildcardSet()
            r2.setTransitionNotes(r3, r1)
            org.apache.xmlbeans.SchemaParticle[] r1 = r8.getParticleChildren()
            int r3 = r1.length
            r4 = 0
        L5b:
            if (r4 >= r3) goto L7f
            r5 = r1[r4]
            int r6 = r5.getParticleType()
            switch(r6) {
                case 1: goto L74;
                case 2: goto L74;
                case 3: goto L74;
                case 4: goto L6c;
                case 5: goto L67;
                default: goto L66;
            }
        L66:
            goto L79
        L67:
            boolean r0 = nsSubset(r2, r5, r9, r10)
            goto L79
        L6c:
            r6 = r5
            org.apache.xmlbeans.SchemaLocalElement r6 = (org.apache.xmlbeans.SchemaLocalElement) r6
            boolean r0 = nsCompat(r2, r6, r9, r10)
            goto L79
        L74:
            boolean r0 = nsRecurseCheckCardinality(r2, r5, r9, r10)
        L79:
            if (r0 != 0) goto L7c
            goto L7f
        L7c:
            int r4 = r4 + 1
            goto L5b
        L7f:
            if (r0 == 0) goto L85
            boolean r0 = checkGroupOccurrenceOK(r7, r8, r9, r10)
        L85:
            return r0
        L86:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.nsRecurseCheckCardinality(org.apache.xmlbeans.SchemaParticle, org.apache.xmlbeans.SchemaParticle, java.util.Collection, org.apache.xmlbeans.XmlObject):boolean");
    }

    private static boolean checkGroupOccurrenceOK(SchemaParticle baseModel, SchemaParticle derivedModel, Collection<XmlError> errors, XmlObject context) {
        boolean groupOccurrenceOK = true;
        BigInteger minRange = BigInteger.ZERO;
        BigInteger maxRange = BigInteger.ZERO;
        switch (derivedModel.getParticleType()) {
            case 1:
            case 3:
                minRange = getEffectiveMinRangeAllSeq(derivedModel);
                maxRange = getEffectiveMaxRangeAllSeq(derivedModel);
                break;
            case 2:
                minRange = getEffectiveMinRangeChoice(derivedModel);
                maxRange = getEffectiveMaxRangeChoice(derivedModel);
                break;
        }
        if (minRange.compareTo(baseModel.getMinOccurs()) < 0) {
            groupOccurrenceOK = false;
            errors.add(XmlError.forObject(XmlErrorCodes.OCCURRENCE_RANGE$MIN_GTE_MIN, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
        }
        if (baseModel.getMaxOccurs() == null) {
            return groupOccurrenceOK;
        }
        if (maxRange == null) {
            errors.add(XmlError.forObject(XmlErrorCodes.OCCURRENCE_RANGE$MAX_LTE_MAX, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
            return false;
        }
        if (maxRange.compareTo(baseModel.getMaxOccurs()) > 0) {
            errors.add(XmlError.forObject(XmlErrorCodes.OCCURRENCE_RANGE$MAX_LTE_MAX, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
            return false;
        }
        return groupOccurrenceOK;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0055 A[LOOP:0: B:2:0x000d->B:24:0x0055, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.math.BigInteger getEffectiveMaxRangeChoice(org.apache.xmlbeans.SchemaParticle r9) {
        /*
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            r1 = 0
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            java.math.BigInteger r3 = java.math.BigInteger.ZERO
            org.apache.xmlbeans.SchemaParticle[] r4 = r9.getParticleChildren()
            int r5 = r4.length
            r6 = 0
        Ld:
            if (r6 >= r5) goto L58
            r7 = r4[r6]
            int r8 = r7.getParticleType()
            switch(r8) {
                case 1: goto L45;
                case 2: goto L37;
                case 3: goto L45;
                case 4: goto L19;
                case 5: goto L19;
                default: goto L18;
            }
        L18:
            goto L52
        L19:
            java.math.BigInteger r8 = r7.getMaxOccurs()
            if (r8 != 0) goto L21
            r0 = 0
            goto L52
        L21:
            int r8 = r7.getIntMaxOccurs()
            if (r8 <= 0) goto L52
            r1 = 1
            java.math.BigInteger r8 = r7.getMaxOccurs()
            int r8 = r8.compareTo(r2)
            if (r8 <= 0) goto L52
            java.math.BigInteger r2 = r7.getMaxOccurs()
            goto L52
        L37:
            java.math.BigInteger r0 = getEffectiveMaxRangeChoice(r7)
            if (r0 == 0) goto L52
            int r8 = r0.compareTo(r3)
            if (r8 <= 0) goto L52
            r3 = r0
            goto L52
        L45:
            java.math.BigInteger r0 = getEffectiveMaxRangeAllSeq(r7)
            if (r0 == 0) goto L52
            int r8 = r0.compareTo(r3)
            if (r8 <= 0) goto L52
            r3 = r0
        L52:
            if (r0 != 0) goto L55
            goto L58
        L55:
            int r6 = r6 + 1
            goto Ld
        L58:
            if (r0 == 0) goto L70
            if (r1 == 0) goto L64
            java.math.BigInteger r5 = r9.getMaxOccurs()
            if (r5 != 0) goto L64
            r0 = 0
            goto L70
        L64:
            java.math.BigInteger r5 = r9.getMaxOccurs()
            java.math.BigInteger r6 = r2.add(r3)
            java.math.BigInteger r0 = r5.multiply(r6)
        L70:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.getEffectiveMaxRangeChoice(org.apache.xmlbeans.SchemaParticle):java.math.BigInteger");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004f A[LOOP:0: B:2:0x000d->B:22:0x004f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0052 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.math.BigInteger getEffectiveMaxRangeAllSeq(org.apache.xmlbeans.SchemaParticle r9) {
        /*
            java.math.BigInteger r0 = java.math.BigInteger.ZERO
            r1 = 0
            java.math.BigInteger r2 = java.math.BigInteger.ZERO
            java.math.BigInteger r3 = java.math.BigInteger.ZERO
            org.apache.xmlbeans.SchemaParticle[] r4 = r9.getParticleChildren()
            int r5 = r4.length
            r6 = 0
        Ld:
            if (r6 >= r5) goto L52
            r7 = r4[r6]
            int r8 = r7.getParticleType()
            switch(r8) {
                case 1: goto L3f;
                case 2: goto L31;
                case 3: goto L3f;
                case 4: goto L19;
                case 5: goto L19;
                default: goto L18;
            }
        L18:
            goto L4c
        L19:
            java.math.BigInteger r8 = r7.getMaxOccurs()
            if (r8 != 0) goto L21
            r0 = 0
            goto L4c
        L21:
            int r8 = r7.getIntMaxOccurs()
            if (r8 <= 0) goto L4c
            r1 = 1
            java.math.BigInteger r8 = r7.getMaxOccurs()
            java.math.BigInteger r2 = r2.add(r8)
            goto L4c
        L31:
            java.math.BigInteger r0 = getEffectiveMaxRangeChoice(r7)
            if (r0 == 0) goto L4c
            int r8 = r0.compareTo(r3)
            if (r8 <= 0) goto L4c
            r3 = r0
            goto L4c
        L3f:
            java.math.BigInteger r0 = getEffectiveMaxRangeAllSeq(r7)
            if (r0 == 0) goto L4c
            int r8 = r0.compareTo(r3)
            if (r8 <= 0) goto L4c
            r3 = r0
        L4c:
            if (r0 != 0) goto L4f
            goto L52
        L4f:
            int r6 = r6 + 1
            goto Ld
        L52:
            if (r0 == 0) goto L6a
            if (r1 == 0) goto L5e
            java.math.BigInteger r5 = r9.getMaxOccurs()
            if (r5 != 0) goto L5e
            r0 = 0
            goto L6a
        L5e:
            java.math.BigInteger r5 = r9.getMaxOccurs()
            java.math.BigInteger r6 = r2.add(r3)
            java.math.BigInteger r0 = r5.multiply(r6)
        L6a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.schema.StscChecker.getEffectiveMaxRangeAllSeq(org.apache.xmlbeans.SchemaParticle):java.math.BigInteger");
    }

    private static BigInteger getEffectiveMinRangeChoice(SchemaParticle derivedModel) {
        SchemaParticle[] particleChildren = derivedModel.getParticleChildren();
        if (particleChildren.length == 0) {
            return BigInteger.ZERO;
        }
        BigInteger minRange = null;
        for (SchemaParticle particle : particleChildren) {
            switch (particle.getParticleType()) {
                case 1:
                case 3:
                    BigInteger mrs = getEffectiveMinRangeAllSeq(particle);
                    if (minRange == null || minRange.compareTo(mrs) > 0) {
                        minRange = mrs;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    BigInteger mrc = getEffectiveMinRangeChoice(particle);
                    if (minRange == null || minRange.compareTo(mrc) > 0) {
                        minRange = mrc;
                        break;
                    } else {
                        break;
                    }
                case 4:
                case 5:
                    if (minRange == null || minRange.compareTo(particle.getMinOccurs()) > 0) {
                        minRange = particle.getMinOccurs();
                        break;
                    } else {
                        break;
                    }
            }
        }
        if (minRange == null) {
            minRange = BigInteger.ZERO;
        }
        return derivedModel.getMinOccurs().multiply(minRange);
    }

    private static BigInteger getEffectiveMinRangeAllSeq(SchemaParticle derivedModel) {
        SchemaParticle[] particleChildren = derivedModel.getParticleChildren();
        BigInteger particleTotalMinOccurs = BigInteger.ZERO;
        for (SchemaParticle particle : particleChildren) {
            switch (particle.getParticleType()) {
                case 1:
                case 3:
                    particleTotalMinOccurs = particleTotalMinOccurs.add(getEffectiveMinRangeAllSeq(particle));
                    break;
                case 2:
                    particleTotalMinOccurs = particleTotalMinOccurs.add(getEffectiveMinRangeChoice(particle));
                    break;
                case 4:
                case 5:
                    particleTotalMinOccurs = particleTotalMinOccurs.add(particle.getMinOccurs());
                    break;
            }
        }
        BigInteger minRange = derivedModel.getMinOccurs().multiply(particleTotalMinOccurs);
        return minRange;
    }

    private static boolean nsSubset(SchemaParticle baseModel, SchemaParticle derivedModel, Collection<XmlError> errors, XmlObject context) {
        if (baseModel.getParticleType() != 5) {
            throw new AssertionError();
        }
        if (derivedModel.getParticleType() != 5) {
            throw new AssertionError();
        }
        if (occurrenceRangeOK(baseModel, derivedModel, errors, context)) {
            if (baseModel.getWildcardSet().inverse().isDisjoint(derivedModel.getWildcardSet())) {
                return true;
            }
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_NS_SUBST$WILDCARD_SUBSET, new Object[]{printParticle(derivedModel), printParticle(baseModel)}, context));
            return false;
        }
        return false;
    }

    private static boolean nsCompat(SchemaParticle baseModel, SchemaLocalElement derivedElement, Collection<XmlError> errors, XmlObject context) {
        if (baseModel.getParticleType() != 5) {
            throw new AssertionError();
        }
        if (baseModel.getWildcardSet().contains(derivedElement.getName())) {
            boolean nsCompat = occurrenceRangeOK(baseModel, (SchemaParticle) derivedElement, errors, context);
            return nsCompat;
        }
        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_DERIVATION_NS_COMPAT$WILDCARD_VALID, new Object[]{printParticle((SchemaParticle) derivedElement), printParticle(baseModel)}, context));
        return false;
    }

    private static boolean nameAndTypeOK(SchemaLocalElement baseElement, SchemaLocalElement derivedElement, Collection<XmlError> errors, XmlObject context) {
        if (!((SchemaParticle) baseElement).canStartWithElement(derivedElement.getName())) {
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$NAME, new Object[]{printParticle((SchemaParticle) derivedElement), printParticle((SchemaParticle) baseElement)}, context));
            return false;
        }
        if (!baseElement.isNillable() && derivedElement.isNillable()) {
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$NILLABLE, new Object[]{printParticle((SchemaParticle) derivedElement), printParticle((SchemaParticle) baseElement)}, context));
            return false;
        }
        if (occurrenceRangeOK((SchemaParticle) baseElement, (SchemaParticle) derivedElement, errors, context) && checkFixed(baseElement, derivedElement, errors, context) && checkIdentityConstraints(baseElement, derivedElement, errors, context) && typeDerivationOK(baseElement.getType(), derivedElement.getType(), errors, context)) {
            return blockSetOK(baseElement, derivedElement, errors, context);
        }
        return false;
    }

    private static boolean blockSetOK(SchemaLocalElement baseElement, SchemaLocalElement derivedElement, Collection<XmlError> errors, XmlObject context) {
        if (baseElement.blockRestriction() && !derivedElement.blockRestriction()) {
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$DISALLOWED_SUBSTITUTIONS, new Object[]{printParticle((SchemaParticle) derivedElement), "restriction", printParticle((SchemaParticle) baseElement)}, context));
            return false;
        }
        if (baseElement.blockExtension() && !derivedElement.blockExtension()) {
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$DISALLOWED_SUBSTITUTIONS, new Object[]{printParticle((SchemaParticle) derivedElement), "extension", printParticle((SchemaParticle) baseElement)}, context));
            return false;
        }
        if (baseElement.blockSubstitution() && !derivedElement.blockSubstitution()) {
            errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$DISALLOWED_SUBSTITUTIONS, new Object[]{printParticle((SchemaParticle) derivedElement), "substitution", printParticle((SchemaParticle) baseElement)}, context));
            return false;
        }
        return true;
    }

    private static boolean typeDerivationOK(SchemaType baseType, SchemaType derivedType, Collection<XmlError> errors, XmlObject context) {
        if (baseType.isAssignableFrom(derivedType)) {
            boolean typeDerivationOK = checkAllDerivationsForRestriction(baseType, derivedType, errors, context);
            return typeDerivationOK;
        }
        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$TYPE_VALID, new Object[]{printType(derivedType), printType(baseType)}, context));
        return false;
    }

    private static boolean checkAllDerivationsForRestriction(SchemaType baseType, SchemaType derivedType, Collection<XmlError> errors, XmlObject context) {
        Set<SchemaType> possibleTypes = null;
        if (baseType.getSimpleVariety() == 2) {
            possibleTypes = new HashSet<>(Arrays.asList(baseType.getUnionConstituentTypes()));
        }
        for (SchemaType currentType = derivedType; !baseType.equals(currentType) && possibleTypes != null && !possibleTypes.contains(currentType); currentType = currentType.getBaseType()) {
            if (currentType.getDerivationType() != 1) {
                errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$TYPE_RESTRICTED, new Object[]{printType(derivedType), printType(baseType), printType(currentType)}, context));
                return false;
            }
        }
        return true;
    }

    private static boolean checkIdentityConstraints(SchemaLocalElement baseElement, SchemaLocalElement derivedElement, Collection<XmlError> errors, XmlObject context) {
        SchemaIdentityConstraint[] baseConstraints = baseElement.getIdentityConstraints();
        SchemaIdentityConstraint[] derivedConstraints = derivedElement.getIdentityConstraints();
        for (SchemaIdentityConstraint derivedConstraint : derivedConstraints) {
            if (checkForIdentityConstraintExistence(baseConstraints, derivedConstraint)) {
                errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$IDENTITY_CONSTRAINTS, new Object[]{printParticle((SchemaParticle) derivedElement), printParticle((SchemaParticle) baseElement)}, context));
                return false;
            }
        }
        return true;
    }

    private static boolean checkForIdentityConstraintExistence(SchemaIdentityConstraint[] baseConstraints, SchemaIdentityConstraint derivedConstraint) {
        for (SchemaIdentityConstraint baseConstraint : baseConstraints) {
            if (baseConstraint.getName().equals(derivedConstraint.getName())) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkFixed(SchemaLocalElement baseModel, SchemaLocalElement derivedModel, Collection<XmlError> errors, XmlObject context) {
        if (!baseModel.isFixed() || baseModel.getDefaultText().equals(derivedModel.getDefaultText())) {
            return true;
        }
        errors.add(XmlError.forObject(XmlErrorCodes.PARTICLE_RESTRICTION_NAME_AND_TYPE$FIXED, new Object[]{printParticle((SchemaParticle) derivedModel), derivedModel.getDefaultText(), printParticle((SchemaParticle) baseModel), baseModel.getDefaultText()}, context));
        return false;
    }

    private static boolean occurrenceRangeOK(SchemaParticle baseParticle, SchemaParticle derivedParticle, Collection<XmlError> errors, XmlObject context) {
        if (derivedParticle.getMinOccurs().compareTo(baseParticle.getMinOccurs()) >= 0) {
            if (baseParticle.getMaxOccurs() == null) {
                return true;
            }
            if (derivedParticle.getMaxOccurs() != null && baseParticle.getMaxOccurs() != null && derivedParticle.getMaxOccurs().compareTo(baseParticle.getMaxOccurs()) <= 0) {
                return true;
            }
            errors.add(XmlError.forObject(XmlErrorCodes.OCCURRENCE_RANGE$MAX_LTE_MAX, new Object[]{printParticle(derivedParticle), printMaxOccurs(derivedParticle.getMaxOccurs()), printParticle(baseParticle), printMaxOccurs(baseParticle.getMaxOccurs())}, context));
            return false;
        }
        errors.add(XmlError.forObject(XmlErrorCodes.OCCURRENCE_RANGE$MIN_GTE_MIN, new Object[]{printParticle(derivedParticle), derivedParticle.getMinOccurs().toString(), printParticle(baseParticle), baseParticle.getMinOccurs().toString()}, context));
        return false;
    }

    private static String printParticles(List<SchemaParticle> parts) {
        return printParticles((SchemaParticle[]) parts.toArray(new SchemaParticle[0]));
    }

    private static String printParticles(SchemaParticle[] parts) {
        return printParticles(parts, 0, parts.length);
    }

    private static String printParticles(SchemaParticle[] parts, int start) {
        return printParticles(parts, start, parts.length);
    }

    private static String printParticles(SchemaParticle[] parts, int start, int end) {
        StringBuilder buf = new StringBuilder(parts.length * 30);
        int i = start;
        while (i < end) {
            buf.append(printParticle(parts[i]));
            i++;
            if (i != end) {
                buf.append(", ");
            }
        }
        return buf.toString();
    }

    private static String printParticle(SchemaParticle part) {
        switch (part.getParticleType()) {
            case 1:
                return "<all>";
            case 2:
                return "<choice>";
            case 3:
                return "<sequence>";
            case 4:
                return "<element name=\"" + QNameHelper.pretty(part.getName()) + "\">";
            case 5:
                return "<any>";
            default:
                return "??";
        }
    }

    private static String printMaxOccurs(BigInteger bi) {
        if (bi == null) {
            return "unbounded";
        }
        return bi.toString();
    }

    private static String printType(SchemaType type) {
        if (type.getName() != null) {
            return QNameHelper.pretty(type.getName());
        }
        return type.toString();
    }

    private static void checkSubstitutionGroups(SchemaGlobalElement[] elts) {
        StscState state = StscState.get();
        for (SchemaGlobalElement elt : elts) {
            SchemaGlobalElement head = elt.substitutionGroup();
            if (head != null) {
                SchemaType headType = head.getType();
                SchemaType tailType = elt.getType();
                XmlObject parseTree = ((SchemaGlobalElementImpl) elt)._parseObject;
                if (!headType.isAssignableFrom(tailType)) {
                    state.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_VALID, new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName())}, parseTree);
                } else if (head.finalExtension() && head.finalRestriction()) {
                    state.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_FINAL, new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName()), "#all"}, parseTree);
                } else if (!headType.equals(tailType)) {
                    if (head.finalExtension() && tailType.getDerivationType() == 2) {
                        state.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_FINAL, new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName()), "extension"}, parseTree);
                    } else if (head.finalRestriction() && tailType.getDerivationType() == 1) {
                        state.error(XmlErrorCodes.ELEM_PROPERTIES$SUBSTITUTION_FINAL, new Object[]{QNameHelper.pretty(elt.getName()), QNameHelper.pretty(head.getName()), "restriction"}, parseTree);
                    }
                }
            }
        }
    }
}
