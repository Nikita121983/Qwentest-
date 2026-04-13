package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.DataType;
import com.microsoft.schemas.office.visio.x2012.main.ForeignDataType;
import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
import com.microsoft.schemas.office.visio.x2012.main.TextType;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.XmlUnsignedInt;

/* loaded from: classes.dex */
public class ShapeSheetTypeImpl extends SheetTypeImpl implements ShapeSheetType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Text"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Data1"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Data2"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Data3"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "ForeignData"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Shapes"), new QName("", "ID"), new QName("", "OriginalID"), new QName("", "Del"), new QName("", "MasterShape"), new QName("", "UniqueID"), new QName("", "Name"), new QName("", "NameU"), new QName("", "IsCustomName"), new QName("", "IsCustomNameU"), new QName("", "Master"), new QName("", PackageRelationship.TYPE_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public ShapeSheetTypeImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public TextType getText() {
        TextType textType;
        synchronized (monitor()) {
            check_orphaned();
            TextType target = (TextType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            textType = target == null ? null : target;
        }
        return textType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setText(TextType text) {
        generatedSetterHelperImpl(text, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public TextType addNewText() {
        TextType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (TextType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public DataType getData1() {
        DataType dataType;
        synchronized (monitor()) {
            check_orphaned();
            DataType target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            dataType = target == null ? null : target;
        }
        return dataType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetData1() {
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

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setData1(DataType data1) {
        generatedSetterHelperImpl(data1, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public DataType addNewData1() {
        DataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetData1() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public DataType getData2() {
        DataType dataType;
        synchronized (monitor()) {
            check_orphaned();
            DataType target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            dataType = target == null ? null : target;
        }
        return dataType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetData2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setData2(DataType data2) {
        generatedSetterHelperImpl(data2, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public DataType addNewData2() {
        DataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetData2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public DataType getData3() {
        DataType dataType;
        synchronized (monitor()) {
            check_orphaned();
            DataType target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            dataType = target == null ? null : target;
        }
        return dataType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetData3() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setData3(DataType data3) {
        generatedSetterHelperImpl(data3, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public DataType addNewData3() {
        DataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetData3() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public ForeignDataType getForeignData() {
        ForeignDataType foreignDataType;
        synchronized (monitor()) {
            check_orphaned();
            ForeignDataType target = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            foreignDataType = target == null ? null : target;
        }
        return foreignDataType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetForeignData() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setForeignData(ForeignDataType foreignData) {
        generatedSetterHelperImpl(foreignData, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public ForeignDataType addNewForeignData() {
        ForeignDataType target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetForeignData() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public ShapesType getShapes() {
        ShapesType shapesType;
        synchronized (monitor()) {
            check_orphaned();
            ShapesType target = (ShapesType) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            shapesType = target == null ? null : target;
        }
        return shapesType;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetShapes() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setShapes(ShapesType shapes) {
        generatedSetterHelperImpl(shapes, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public ShapesType addNewShapes() {
        ShapesType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (ShapesType) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetShapes() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public long getID() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlUnsignedInt xgetID() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setID(long id) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.setLongValue(id);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetID(XmlUnsignedInt id) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[6]);
            }
            target.set(id);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public long getOriginalID() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlUnsignedInt xgetOriginalID() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetOriginalID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setOriginalID(long originalID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.setLongValue(originalID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetOriginalID(XmlUnsignedInt originalID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[7]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[7]);
            }
            target.set(originalID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetOriginalID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean getDel() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlBoolean xgetDel() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetDel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[8]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setDel(boolean del) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.setBooleanValue(del);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetDel(XmlBoolean del) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[8]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[8]);
            }
            target.set(del);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public long getMasterShape() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlUnsignedInt xgetMasterShape() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetMasterShape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[9]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setMasterShape(long masterShape) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.setLongValue(masterShape);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetMasterShape(XmlUnsignedInt masterShape) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[9]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[9]);
            }
            target.set(masterShape);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetMasterShape() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public String getUniqueID() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlString xgetUniqueID() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetUniqueID() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[10]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setUniqueID(String uniqueID) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.setStringValue(uniqueID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetUniqueID(XmlString uniqueID) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[10]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[10]);
            }
            target.set(uniqueID);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetUniqueID() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public String getName() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlString xgetName() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setName(String name) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.setStringValue(name);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetName(XmlString name) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[11]);
            }
            target.set(name);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public String getNameU() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlString xgetNameU() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetNameU() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setNameU(String nameU) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.setStringValue(nameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetNameU(XmlString nameU) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (target == null) {
                target = (XmlString) get_store().add_attribute_user(PROPERTY_QNAME[12]);
            }
            target.set(nameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetNameU() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean getIsCustomName() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlBoolean xgetIsCustomName() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetIsCustomName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setIsCustomName(boolean isCustomName) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.setBooleanValue(isCustomName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetIsCustomName(XmlBoolean isCustomName) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[13]);
            }
            target.set(isCustomName);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetIsCustomName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean getIsCustomNameU() {
        boolean booleanValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            booleanValue = target == null ? false : target.getBooleanValue();
        }
        return booleanValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlBoolean xgetIsCustomNameU() {
        XmlBoolean target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetIsCustomNameU() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setIsCustomNameU(boolean isCustomNameU) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.setBooleanValue(isCustomNameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetIsCustomNameU(XmlBoolean isCustomNameU) {
        synchronized (monitor()) {
            check_orphaned();
            XmlBoolean target = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (target == null) {
                target = (XmlBoolean) get_store().add_attribute_user(PROPERTY_QNAME[14]);
            }
            target.set(isCustomNameU);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetIsCustomNameU() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public long getMaster() {
        long longValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            longValue = target == null ? 0L : target.getLongValue();
        }
        return longValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlUnsignedInt xgetMaster() {
        XmlUnsignedInt target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetMaster() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setMaster(long master) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.setLongValue(master);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetMaster(XmlUnsignedInt master) {
        synchronized (monitor()) {
            check_orphaned();
            XmlUnsignedInt target = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (target == null) {
                target = (XmlUnsignedInt) get_store().add_attribute_user(PROPERTY_QNAME[15]);
            }
            target.set(master);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetMaster() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public String getType() {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            stringValue = target == null ? null : target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public XmlToken xgetType() {
        XmlToken target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
        }
        return z;
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void setType(String type) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.setStringValue(type);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void xsetType(XmlToken type) {
        synchronized (monitor()) {
            check_orphaned();
            XmlToken target = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (target == null) {
                target = (XmlToken) get_store().add_attribute_user(PROPERTY_QNAME[16]);
            }
            target.set(type);
        }
    }

    @Override // com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType
    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[16]);
        }
    }
}
