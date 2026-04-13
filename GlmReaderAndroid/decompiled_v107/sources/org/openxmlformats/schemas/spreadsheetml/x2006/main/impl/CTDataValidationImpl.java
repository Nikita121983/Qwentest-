package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.commons.math3.linear.ConjugateGradient;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationErrorStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationImeMode;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationImeMode$Enum;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFormula;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSqref;

/* loaded from: classes12.dex */
public class CTDataValidationImpl extends XmlComplexContentImpl implements CTDataValidation {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "formula1"), new QName(XSSFRelation.NS_SPREADSHEETML, "formula2"), new QName("", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY), new QName("", "errorStyle"), new QName("", "imeMode"), new QName("", ConjugateGradient.OPERATOR), new QName("", "allowBlank"), new QName("", "showDropDown"), new QName("", "showInputMessage"), new QName("", "showErrorMessage"), new QName("", "errorTitle"), new QName("", "error"), new QName("", "promptTitle"), new QName("", "prompt"), new QName("", "sqref")};
    private static final long serialVersionUID = 1;

    public CTDataValidationImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public String getFormula1() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STFormula xgetFormula1() {
        STFormula target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STFormula) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetFormula1() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setFormula1(String formula1) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.setStringValue(formula1);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetFormula1(STFormula formula1) {
        synchronized (monitor()) {
            check_orphaned();
            STFormula target = (STFormula) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (target == null) {
                target = (STFormula) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            target.set(formula1);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetFormula1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public String getFormula2() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STFormula xgetFormula2() {
        STFormula target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STFormula) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetFormula2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setFormula2(String formula2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.setStringValue(formula2);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetFormula2(STFormula formula2) {
        synchronized (monitor()) {
            check_orphaned();
            STFormula target = (STFormula) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (target == null) {
                target = (STFormula) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            target.set(formula2);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetFormula2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationType.Enum getType() {
        STDataValidationType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
            r1 = target == null ? null : (STDataValidationType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationType xgetType() {
        STDataValidationType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDataValidationType) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STDataValidationType) get_default_attribute_value(PROPERTY_QNAME[2]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setType(STDataValidationType.Enum type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.setEnumValue(type);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetType(STDataValidationType type) {
        synchronized (monitor()) {
            check_orphaned();
            STDataValidationType target = (STDataValidationType) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (target == null) {
                target = (STDataValidationType) get_store().add_attribute_user(PROPERTY_QNAME[2]);
            }
            target.set(type);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[2]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationErrorStyle.Enum getErrorStyle() {
        STDataValidationErrorStyle.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
            r1 = target == null ? null : (STDataValidationErrorStyle.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationErrorStyle xgetErrorStyle() {
        STDataValidationErrorStyle target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDataValidationErrorStyle) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STDataValidationErrorStyle) get_default_attribute_value(PROPERTY_QNAME[3]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetErrorStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setErrorStyle(STDataValidationErrorStyle.Enum errorStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.setEnumValue(errorStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetErrorStyle(STDataValidationErrorStyle errorStyle) {
        synchronized (monitor()) {
            check_orphaned();
            STDataValidationErrorStyle target = (STDataValidationErrorStyle) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (target == null) {
                target = (STDataValidationErrorStyle) get_store().add_attribute_user(PROPERTY_QNAME[3]);
            }
            target.set(errorStyle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetErrorStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationImeMode$Enum getImeMode() {
        STDataValidationImeMode$Enum sTDataValidationImeMode$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
            sTDataValidationImeMode$Enum = target == null ? null : (STDataValidationImeMode$Enum) target.getEnumValue();
        }
        return sTDataValidationImeMode$Enum;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationImeMode xgetImeMode() {
        STDataValidationImeMode target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STDataValidationImeMode) get_default_attribute_value(PROPERTY_QNAME[4]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetImeMode() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setImeMode(STDataValidationImeMode$Enum imeMode) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.setEnumValue(imeMode);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetImeMode(STDataValidationImeMode imeMode) {
        synchronized (monitor()) {
            check_orphaned();
            STDataValidationImeMode target = get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (target == null) {
                target = (STDataValidationImeMode) get_store().add_attribute_user(PROPERTY_QNAME[4]);
            }
            target.set(imeMode);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetImeMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationOperator.Enum getOperator() {
        STDataValidationOperator.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
            r1 = target == null ? null : (STDataValidationOperator.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STDataValidationOperator xgetOperator() {
        STDataValidationOperator target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STDataValidationOperator) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STDataValidationOperator) get_default_attribute_value(PROPERTY_QNAME[5]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetOperator() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setOperator(STDataValidationOperator.Enum operator) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.setEnumValue(operator);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetOperator(STDataValidationOperator operator) {
        synchronized (monitor()) {
            check_orphaned();
            STDataValidationOperator target = (STDataValidationOperator) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (target == null) {
                target = (STDataValidationOperator) get_store().add_attribute_user(PROPERTY_QNAME[5]);
            }
            target.set(operator);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetOperator() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean getAllowBlank() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public XmlBoolean xgetAllowBlank() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[6]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetAllowBlank() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setAllowBlank(boolean allowBlank) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setBooleanValue(allowBlank);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetAllowBlank(XmlBoolean allowBlank) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(allowBlank);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetAllowBlank() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean getShowDropDown() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public XmlBoolean xgetShowDropDown() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[7]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetShowDropDown() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setShowDropDown(boolean showDropDown) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setBooleanValue(showDropDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetShowDropDown(XmlBoolean showDropDown) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(showDropDown);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetShowDropDown() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean getShowInputMessage() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public XmlBoolean xgetShowInputMessage() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[8]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetShowInputMessage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setShowInputMessage(boolean showInputMessage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setBooleanValue(showInputMessage);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetShowInputMessage(XmlBoolean showInputMessage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(showInputMessage);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetShowInputMessage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean getShowErrorMessage() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public XmlBoolean xgetShowErrorMessage() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_default_attribute_value(PROPERTY_QNAME[9]);
            }
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetShowErrorMessage() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setShowErrorMessage(boolean showErrorMessage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setBooleanValue(showErrorMessage);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetShowErrorMessage(XmlBoolean showErrorMessage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(showErrorMessage);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetShowErrorMessage() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public String getErrorTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STXstring xgetErrorTitle() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetErrorTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setErrorTitle(String errorTitle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setStringValue(errorTitle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetErrorTitle(STXstring errorTitle) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(errorTitle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetErrorTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public String getError() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STXstring xgetError() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetError() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setError(String error) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setStringValue(error);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetError(STXstring error) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(error);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetError() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public String getPromptTitle() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STXstring xgetPromptTitle() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetPromptTitle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setPromptTitle(String promptTitle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(promptTitle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetPromptTitle(STXstring promptTitle) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(promptTitle);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetPromptTitle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public String getPrompt() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STXstring xgetPrompt() {
        STXstring target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public boolean isSetPrompt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setPrompt(String prompt) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setStringValue(prompt);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetPrompt(STXstring prompt) {
        synchronized (monitor()) {
            check_orphaned();
            STXstring target = (STXstring) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (STXstring) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(prompt);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void unsetPrompt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public List getSqref() {
        List<?> listValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            listValue = target == null ? null : target.getListValue();
        }
        return listValue;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public STSqref xgetSqref() {
        STSqref target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STSqref) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void setSqref(List sqref) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setListValue(sqref);
        }
    }

    @Override // org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation
    public void xsetSqref(STSqref sqref) {
        synchronized (monitor()) {
            check_orphaned();
            STSqref target = (STSqref) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (STSqref) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(sqref);
        }
    }
}
