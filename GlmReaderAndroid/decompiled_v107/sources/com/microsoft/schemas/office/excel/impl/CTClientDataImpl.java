package com.microsoft.schemas.office.excel.impl;

import com.microsoft.schemas.office.excel.CTClientData;
import com.microsoft.schemas.office.excel.STCF;
import com.microsoft.schemas.office.excel.STObjectType;
import java.math.BigInteger;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalseBlank;

/* loaded from: classes.dex */
public class CTClientDataImpl extends XmlComplexContentImpl implements CTClientData {
    private static final QName[] PROPERTY_QNAME = {new QName("urn:schemas-microsoft-com:office:excel", "MoveWithCells"), new QName("urn:schemas-microsoft-com:office:excel", "SizeWithCells"), new QName("urn:schemas-microsoft-com:office:excel", "Anchor"), new QName("urn:schemas-microsoft-com:office:excel", "Locked"), new QName("urn:schemas-microsoft-com:office:excel", "DefaultSize"), new QName("urn:schemas-microsoft-com:office:excel", "PrintObject"), new QName("urn:schemas-microsoft-com:office:excel", "Disabled"), new QName("urn:schemas-microsoft-com:office:excel", "AutoFill"), new QName("urn:schemas-microsoft-com:office:excel", "AutoLine"), new QName("urn:schemas-microsoft-com:office:excel", "AutoPict"), new QName("urn:schemas-microsoft-com:office:excel", "FmlaMacro"), new QName("urn:schemas-microsoft-com:office:excel", "TextHAlign"), new QName("urn:schemas-microsoft-com:office:excel", "TextVAlign"), new QName("urn:schemas-microsoft-com:office:excel", "LockText"), new QName("urn:schemas-microsoft-com:office:excel", "JustLastX"), new QName("urn:schemas-microsoft-com:office:excel", "SecretEdit"), new QName("urn:schemas-microsoft-com:office:excel", "Default"), new QName("urn:schemas-microsoft-com:office:excel", "Help"), new QName("urn:schemas-microsoft-com:office:excel", "Cancel"), new QName("urn:schemas-microsoft-com:office:excel", "Dismiss"), new QName("urn:schemas-microsoft-com:office:excel", "Accel"), new QName("urn:schemas-microsoft-com:office:excel", "Accel2"), new QName("urn:schemas-microsoft-com:office:excel", "Row"), new QName("urn:schemas-microsoft-com:office:excel", "Column"), new QName("urn:schemas-microsoft-com:office:excel", "Visible"), new QName("urn:schemas-microsoft-com:office:excel", "RowHidden"), new QName("urn:schemas-microsoft-com:office:excel", "ColHidden"), new QName("urn:schemas-microsoft-com:office:excel", "VTEdit"), new QName("urn:schemas-microsoft-com:office:excel", "MultiLine"), new QName("urn:schemas-microsoft-com:office:excel", "VScroll"), new QName("urn:schemas-microsoft-com:office:excel", "ValidIds"), new QName("urn:schemas-microsoft-com:office:excel", "FmlaRange"), new QName("urn:schemas-microsoft-com:office:excel", "WidthMin"), new QName("urn:schemas-microsoft-com:office:excel", "Sel"), new QName("urn:schemas-microsoft-com:office:excel", "NoThreeD2"), new QName("urn:schemas-microsoft-com:office:excel", "SelType"), new QName("urn:schemas-microsoft-com:office:excel", "MultiSel"), new QName("urn:schemas-microsoft-com:office:excel", "LCT"), new QName("urn:schemas-microsoft-com:office:excel", "ListItem"), new QName("urn:schemas-microsoft-com:office:excel", "DropStyle"), new QName("urn:schemas-microsoft-com:office:excel", "Colored"), new QName("urn:schemas-microsoft-com:office:excel", "DropLines"), new QName("urn:schemas-microsoft-com:office:excel", "Checked"), new QName("urn:schemas-microsoft-com:office:excel", "FmlaLink"), new QName("urn:schemas-microsoft-com:office:excel", "FmlaPict"), new QName("urn:schemas-microsoft-com:office:excel", "NoThreeD"), new QName("urn:schemas-microsoft-com:office:excel", "FirstButton"), new QName("urn:schemas-microsoft-com:office:excel", "FmlaGroup"), new QName("urn:schemas-microsoft-com:office:excel", "Val"), new QName("urn:schemas-microsoft-com:office:excel", "Min"), new QName("urn:schemas-microsoft-com:office:excel", "Max"), new QName("urn:schemas-microsoft-com:office:excel", "Inc"), new QName("urn:schemas-microsoft-com:office:excel", "Page"), new QName("urn:schemas-microsoft-com:office:excel", "Horiz"), new QName("urn:schemas-microsoft-com:office:excel", "Dx"), new QName("urn:schemas-microsoft-com:office:excel", "MapOCX"), new QName("urn:schemas-microsoft-com:office:excel", "CF"), new QName("urn:schemas-microsoft-com:office:excel", "Camera"), new QName("urn:schemas-microsoft-com:office:excel", "RecalcAlways"), new QName("urn:schemas-microsoft-com:office:excel", "AutoScale"), new QName("urn:schemas-microsoft-com:office:excel", "DDE"), new QName("urn:schemas-microsoft-com:office:excel", "UIObj"), new QName("urn:schemas-microsoft-com:office:excel", "ScriptText"), new QName("urn:schemas-microsoft-com:office:excel", "ScriptExtended"), new QName("urn:schemas-microsoft-com:office:excel", "ScriptLanguage"), new QName("urn:schemas-microsoft-com:office:excel", "ScriptLocation"), new QName("urn:schemas-microsoft-com:office:excel", "FmlaTxbx"), new QName("", "ObjectType")};
    private static final long serialVersionUID = 1;

    public CTClientDataImpl(SchemaType sType) {
        super(sType);
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getMoveWithCellsList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda210
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getMoveWithCellsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda221
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setMoveWithCellsArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda233
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertMoveWithCells(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda244(this), new CTClientDataImpl$$ExternalSyntheticLambda255(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getMoveWithCellsArray$0(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getMoveWithCellsArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[0], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda125
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getMoveWithCellsArray$0(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getMoveWithCellsArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetMoveWithCellsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda581
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetMoveWithCellsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda582
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetMoveWithCellsArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda583
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewMoveWithCells(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda244(this), new CTClientDataImpl$$ExternalSyntheticLambda255(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetMoveWithCellsArray$1(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetMoveWithCellsArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[0], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda375
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetMoveWithCellsArray$1(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetMoveWithCellsArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfMoveWithCellsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMoveWithCellsArray(STTrueFalseBlank.Enum[] moveWithCellsArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(moveWithCellsArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMoveWithCellsArray(int i, STTrueFalseBlank.Enum moveWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(moveWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMoveWithCellsArray(STTrueFalseBlank[] moveWithCellsArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(moveWithCellsArray, PROPERTY_QNAME[0]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMoveWithCellsArray(int i, STTrueFalseBlank moveWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(moveWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertMoveWithCells(int i, STTrueFalseBlank.Enum moveWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            target.setEnumValue(moveWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addMoveWithCells(STTrueFalseBlank.Enum moveWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[0]);
            target.setEnumValue(moveWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewMoveWithCells(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewMoveWithCells() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeMoveWithCells(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getSizeWithCellsList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda252
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getSizeWithCellsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda253
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setSizeWithCellsArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda254
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertSizeWithCells(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda256(this), new CTClientDataImpl$$ExternalSyntheticLambda257(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getSizeWithCellsArray$2(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getSizeWithCellsArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[1], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda633
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getSizeWithCellsArray$2(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getSizeWithCellsArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetSizeWithCellsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda467
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetSizeWithCellsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda468
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetSizeWithCellsArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda469
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewSizeWithCells(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda256(this), new CTClientDataImpl$$ExternalSyntheticLambda257(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetSizeWithCellsArray$3(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetSizeWithCellsArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[1], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda445
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetSizeWithCellsArray$3(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetSizeWithCellsArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfSizeWithCellsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSizeWithCellsArray(STTrueFalseBlank.Enum[] sizeWithCellsArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(sizeWithCellsArray, PROPERTY_QNAME[1]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSizeWithCellsArray(int i, STTrueFalseBlank.Enum sizeWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(sizeWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSizeWithCellsArray(STTrueFalseBlank[] sizeWithCellsArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(sizeWithCellsArray, PROPERTY_QNAME[1]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSizeWithCellsArray(int i, STTrueFalseBlank sizeWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(sizeWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertSizeWithCells(int i, STTrueFalseBlank.Enum sizeWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            target.setEnumValue(sizeWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addSizeWithCells(STTrueFalseBlank.Enum sizeWithCells) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[1]);
            target.setEnumValue(sizeWithCells);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewSizeWithCells(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewSizeWithCells() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeSizeWithCells(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getAnchorList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda127
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getAnchorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda128
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setAnchorArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda129
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertAnchor(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda105(this), new CTClientDataImpl$$ExternalSyntheticLambda106(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getAnchorArray$4(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getAnchorArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[2], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda588
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getAnchorArray$4(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getAnchorArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda102
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetAnchorArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda103
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetAnchorArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda104
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewAnchor(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda105(this), new CTClientDataImpl$$ExternalSyntheticLambda106(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetAnchorArray$5(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetAnchorArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[2], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda505
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetAnchorArray$5(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetAnchorArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAnchorArray(String[] anchorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(anchorArray, PROPERTY_QNAME[2]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAnchorArray(int i, String anchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(anchor);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAnchorArray(XmlString[] anchorArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(anchorArray, PROPERTY_QNAME[2]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAnchorArray(int i, XmlString anchor) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(anchor);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertAnchor(int i, String anchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            target.setStringValue(anchor);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addAnchor(String anchor) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[2]);
            target.setStringValue(anchor);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewAnchor(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewAnchor() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getLockedList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda151
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getLockedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda152
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setLockedArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda153
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertLocked(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda639(this), new CTClientDataImpl$$ExternalSyntheticLambda640(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getLockedArray$6(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getLockedArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[3], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda670
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getLockedArray$6(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getLockedArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetLockedList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda635
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetLockedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda636
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetLockedArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda637
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewLocked(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda639(this), new CTClientDataImpl$$ExternalSyntheticLambda640(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetLockedArray$7(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetLockedArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[3], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda145
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetLockedArray$7(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetLockedArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfLockedArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setLockedArray(STTrueFalseBlank.Enum[] lockedArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lockedArray, PROPERTY_QNAME[3]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setLockedArray(int i, STTrueFalseBlank.Enum locked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(locked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetLockedArray(STTrueFalseBlank[] lockedArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lockedArray, PROPERTY_QNAME[3]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetLockedArray(int i, STTrueFalseBlank locked) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(locked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertLocked(int i, STTrueFalseBlank.Enum locked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            target.setEnumValue(locked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addLocked(STTrueFalseBlank.Enum locked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[3]);
            target.setEnumValue(locked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewLocked(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewLocked() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeLocked(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getDefaultSizeList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda242
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDefaultSizeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda243
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDefaultSizeArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda245
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDefaultSize(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda246(this), new CTClientDataImpl$$ExternalSyntheticLambda247(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getDefaultSizeArray$8(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getDefaultSizeArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[4], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda628
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDefaultSizeArray$8(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getDefaultSizeArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetDefaultSizeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda339
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDefaultSizeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda340
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDefaultSizeArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda341
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDefaultSize(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda246(this), new CTClientDataImpl$$ExternalSyntheticLambda247(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetDefaultSizeArray$9(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetDefaultSizeArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[4], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda385
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDefaultSizeArray$9(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetDefaultSizeArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDefaultSizeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDefaultSizeArray(STTrueFalseBlank.Enum[] defaultSizeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(defaultSizeArray, PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDefaultSizeArray(int i, STTrueFalseBlank.Enum defaultSize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(defaultSize);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDefaultSizeArray(STTrueFalseBlank[] defaultSizeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(defaultSizeArray, PROPERTY_QNAME[4]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDefaultSizeArray(int i, STTrueFalseBlank defaultSize) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(defaultSize);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDefaultSize(int i, STTrueFalseBlank.Enum defaultSize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            target.setEnumValue(defaultSize);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDefaultSize(STTrueFalseBlank.Enum defaultSize) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[4]);
            target.setEnumValue(defaultSize);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewDefaultSize(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewDefaultSize() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDefaultSize(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getPrintObjectList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda141
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getPrintObjectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda142
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setPrintObjectArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda143
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertPrintObject(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda18(this), new CTClientDataImpl$$ExternalSyntheticLambda19(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getPrintObjectArray$10(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getPrintObjectArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[5], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda542
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getPrintObjectArray$10(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getPrintObjectArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetPrintObjectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda15
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetPrintObjectArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda16
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetPrintObjectArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda17
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewPrintObject(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda18(this), new CTClientDataImpl$$ExternalSyntheticLambda19(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetPrintObjectArray$11(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetPrintObjectArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[5], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda632
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetPrintObjectArray$11(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetPrintObjectArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfPrintObjectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setPrintObjectArray(STTrueFalseBlank.Enum[] printObjectArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(printObjectArray, PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setPrintObjectArray(int i, STTrueFalseBlank.Enum printObject) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(printObject);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetPrintObjectArray(STTrueFalseBlank[] printObjectArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(printObjectArray, PROPERTY_QNAME[5]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetPrintObjectArray(int i, STTrueFalseBlank printObject) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(printObject);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertPrintObject(int i, STTrueFalseBlank.Enum printObject) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            target.setEnumValue(printObject);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addPrintObject(STTrueFalseBlank.Enum printObject) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[5]);
            target.setEnumValue(printObject);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewPrintObject(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewPrintObject() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removePrintObject(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getDisabledList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda507
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDisabledArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda508
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDisabledArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda509
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDisabled(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda44(this), new CTClientDataImpl$$ExternalSyntheticLambda55(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getDisabledArray$12(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getDisabledArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[6], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda89
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDisabledArray$12(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getDisabledArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetDisabledList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda11
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDisabledArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda22
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDisabledArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda33
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDisabled(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda44(this), new CTClientDataImpl$$ExternalSyntheticLambda55(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetDisabledArray$13(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetDisabledArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[6], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda321
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDisabledArray$13(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetDisabledArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDisabledArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDisabledArray(STTrueFalseBlank.Enum[] disabledArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(disabledArray, PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDisabledArray(int i, STTrueFalseBlank.Enum disabled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(disabled);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDisabledArray(STTrueFalseBlank[] disabledArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(disabledArray, PROPERTY_QNAME[6]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDisabledArray(int i, STTrueFalseBlank disabled) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(disabled);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDisabled(int i, STTrueFalseBlank.Enum disabled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[6], i);
            target.setEnumValue(disabled);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDisabled(STTrueFalseBlank.Enum disabled) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[6]);
            target.setEnumValue(disabled);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewDisabled(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewDisabled() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDisabled(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getAutoFillList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda122
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getAutoFillArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda133
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setAutoFillArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda144
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertAutoFill(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda155(this), new CTClientDataImpl$$ExternalSyntheticLambda166(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getAutoFillArray$14(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getAutoFillArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[7], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda600
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getAutoFillArray$14(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getAutoFillArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetAutoFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda522
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetAutoFillArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda523
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetAutoFillArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda524
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewAutoFill(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda155(this), new CTClientDataImpl$$ExternalSyntheticLambda166(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetAutoFillArray$15(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetAutoFillArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[7], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda163
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetAutoFillArray$15(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetAutoFillArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfAutoFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoFillArray(STTrueFalseBlank.Enum[] autoFillArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoFillArray, PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoFillArray(int i, STTrueFalseBlank.Enum autoFill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(autoFill);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoFillArray(STTrueFalseBlank[] autoFillArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoFillArray, PROPERTY_QNAME[7]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoFillArray(int i, STTrueFalseBlank autoFill) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(autoFill);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertAutoFill(int i, STTrueFalseBlank.Enum autoFill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[7], i);
            target.setEnumValue(autoFill);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addAutoFill(STTrueFalseBlank.Enum autoFill) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[7]);
            target.setEnumValue(autoFill);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewAutoFill(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewAutoFill() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeAutoFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getAutoLineList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda136
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getAutoLineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda137
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setAutoLineArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda138
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertAutoLine(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda39(this), new CTClientDataImpl$$ExternalSyntheticLambda40(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getAutoLineArray$16(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getAutoLineArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[8], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda228
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getAutoLineArray$16(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getAutoLineArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetAutoLineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda36
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetAutoLineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda37
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetAutoLineArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda38
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewAutoLine(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda39(this), new CTClientDataImpl$$ExternalSyntheticLambda40(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetAutoLineArray$17(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetAutoLineArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[8], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda346
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetAutoLineArray$17(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetAutoLineArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfAutoLineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoLineArray(STTrueFalseBlank.Enum[] autoLineArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoLineArray, PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoLineArray(int i, STTrueFalseBlank.Enum autoLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(autoLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoLineArray(STTrueFalseBlank[] autoLineArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoLineArray, PROPERTY_QNAME[8]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoLineArray(int i, STTrueFalseBlank autoLine) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(autoLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertAutoLine(int i, STTrueFalseBlank.Enum autoLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[8], i);
            target.setEnumValue(autoLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addAutoLine(STTrueFalseBlank.Enum autoLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[8]);
            target.setEnumValue(autoLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewAutoLine(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewAutoLine() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeAutoLine(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getAutoPictList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda625
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getAutoPictArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda626
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setAutoPictArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda627
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertAutoPict(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda69(this), new CTClientDataImpl$$ExternalSyntheticLambda70(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getAutoPictArray$18(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getAutoPictArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[9], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda161
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getAutoPictArray$18(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getAutoPictArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetAutoPictList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda65
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetAutoPictArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda67
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetAutoPictArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda68
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewAutoPict(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda69(this), new CTClientDataImpl$$ExternalSyntheticLambda70(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetAutoPictArray$19(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetAutoPictArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[9], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda56
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetAutoPictArray$19(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetAutoPictArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfAutoPictArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoPictArray(STTrueFalseBlank.Enum[] autoPictArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoPictArray, PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoPictArray(int i, STTrueFalseBlank.Enum autoPict) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(autoPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoPictArray(STTrueFalseBlank[] autoPictArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoPictArray, PROPERTY_QNAME[9]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoPictArray(int i, STTrueFalseBlank autoPict) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(autoPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertAutoPict(int i, STTrueFalseBlank.Enum autoPict) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[9], i);
            target.setEnumValue(autoPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addAutoPict(STTrueFalseBlank.Enum autoPict) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[9]);
            target.setEnumValue(autoPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewAutoPict(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewAutoPict() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeAutoPict(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getFmlaMacroList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda342
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getFmlaMacroArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda343
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setFmlaMacroArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda345
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertFmlaMacro(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda80(this), new CTClientDataImpl$$ExternalSyntheticLambda81(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getFmlaMacroArray$20(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getFmlaMacroArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[10], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda263
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getFmlaMacroArray$20(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getFmlaMacroArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetFmlaMacroList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda76
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetFmlaMacroArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda78
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetFmlaMacroArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda79
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewFmlaMacro(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda80(this), new CTClientDataImpl$$ExternalSyntheticLambda81(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetFmlaMacroArray$21(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetFmlaMacroArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[10], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda568
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetFmlaMacroArray$21(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetFmlaMacroArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfFmlaMacroArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaMacroArray(String[] fmlaMacroArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaMacroArray, PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaMacroArray(int i, String fmlaMacro) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(fmlaMacro);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaMacroArray(XmlString[] fmlaMacroArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaMacroArray, PROPERTY_QNAME[10]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaMacroArray(int i, XmlString fmlaMacro) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(fmlaMacro);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertFmlaMacro(int i, String fmlaMacro) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[10], i);
            target.setStringValue(fmlaMacro);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addFmlaMacro(String fmlaMacro) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[10]);
            target.setStringValue(fmlaMacro);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewFmlaMacro(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewFmlaMacro() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeFmlaMacro(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getTextHAlignList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda349
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getTextHAlignArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda350
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setTextHAlignArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda351
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertTextHAlign(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda352(this), new CTClientDataImpl$$ExternalSyntheticLambda353(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getTextHAlignArray$22(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getTextHAlignArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[11], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda601
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getTextHAlignArray$22(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getTextHAlignArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetTextHAlignList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda585
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetTextHAlignArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda586
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetTextHAlignArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda587
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewTextHAlign(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda352(this), new CTClientDataImpl$$ExternalSyntheticLambda353(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetTextHAlignArray$23(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetTextHAlignArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[11], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda99
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetTextHAlignArray$23(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetTextHAlignArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfTextHAlignArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setTextHAlignArray(String[] textHAlignArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(textHAlignArray, PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setTextHAlignArray(int i, String textHAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(textHAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetTextHAlignArray(XmlString[] textHAlignArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(textHAlignArray, PROPERTY_QNAME[11]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetTextHAlignArray(int i, XmlString textHAlign) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(textHAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertTextHAlign(int i, String textHAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[11], i);
            target.setStringValue(textHAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addTextHAlign(String textHAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[11]);
            target.setStringValue(textHAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewTextHAlign(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewTextHAlign() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeTextHAlign(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getTextVAlignList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda236
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getTextVAlignArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda237
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setTextVAlignArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda238
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertTextVAlign(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda4(this), new CTClientDataImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getTextVAlignArray$24(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getTextVAlignArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[12], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda473
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getTextVAlignArray$24(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getTextVAlignArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetTextVAlignList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetTextVAlignArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetTextVAlignArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewTextVAlign(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda4(this), new CTClientDataImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetTextVAlignArray$25(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetTextVAlignArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[12], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda584
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetTextVAlignArray$25(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetTextVAlignArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfTextVAlignArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setTextVAlignArray(String[] textVAlignArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(textVAlignArray, PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setTextVAlignArray(int i, String textVAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(textVAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetTextVAlignArray(XmlString[] textVAlignArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(textVAlignArray, PROPERTY_QNAME[12]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetTextVAlignArray(int i, XmlString textVAlign) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(textVAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertTextVAlign(int i, String textVAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[12], i);
            target.setStringValue(textVAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addTextVAlign(String textVAlign) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[12]);
            target.setStringValue(textVAlign);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewTextVAlign(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewTextVAlign() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeTextVAlign(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getLockTextList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda71
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getLockTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda72
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setLockTextArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda73
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertLockText(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda74(this), new CTClientDataImpl$$ExternalSyntheticLambda75(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getLockTextArray$26(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getLockTextArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[13], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda50
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getLockTextArray$26(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getLockTextArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetLockTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda529
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetLockTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda530
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetLockTextArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda531
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewLockText(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda74(this), new CTClientDataImpl$$ExternalSyntheticLambda75(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetLockTextArray$27(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetLockTextArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[13], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda634
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetLockTextArray$27(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetLockTextArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfLockTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setLockTextArray(STTrueFalseBlank.Enum[] lockTextArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lockTextArray, PROPERTY_QNAME[13]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setLockTextArray(int i, STTrueFalseBlank.Enum lockText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(lockText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetLockTextArray(STTrueFalseBlank[] lockTextArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lockTextArray, PROPERTY_QNAME[13]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetLockTextArray(int i, STTrueFalseBlank lockText) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(lockText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertLockText(int i, STTrueFalseBlank.Enum lockText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[13], i);
            target.setEnumValue(lockText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addLockText(STTrueFalseBlank.Enum lockText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[13]);
            target.setEnumValue(lockText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewLockText(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewLockText() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeLockText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getJustLastXList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda354
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getJustLastXArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda356
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setJustLastXArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda357
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertJustLastX(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda300(this), new CTClientDataImpl$$ExternalSyntheticLambda301(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getJustLastXArray$28(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getJustLastXArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[14], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda397
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getJustLastXArray$28(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getJustLastXArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetJustLastXList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda296
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetJustLastXArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda297
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetJustLastXArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda298
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewJustLastX(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda300(this), new CTClientDataImpl$$ExternalSyntheticLambda301(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetJustLastXArray$29(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetJustLastXArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[14], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda130
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetJustLastXArray$29(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetJustLastXArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfJustLastXArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setJustLastXArray(STTrueFalseBlank.Enum[] justLastXArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(justLastXArray, PROPERTY_QNAME[14]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setJustLastXArray(int i, STTrueFalseBlank.Enum justLastX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(justLastX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetJustLastXArray(STTrueFalseBlank[] justLastXArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(justLastXArray, PROPERTY_QNAME[14]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetJustLastXArray(int i, STTrueFalseBlank justLastX) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(justLastX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertJustLastX(int i, STTrueFalseBlank.Enum justLastX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[14], i);
            target.setEnumValue(justLastX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addJustLastX(STTrueFalseBlank.Enum justLastX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[14]);
            target.setEnumValue(justLastX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewJustLastX(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewJustLastX() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeJustLastX(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getSecretEditList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda326
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getSecretEditArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda327
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setSecretEditArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda328
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertSecretEdit(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda329(this), new CTClientDataImpl$$ExternalSyntheticLambda330(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getSecretEditArray$30(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getSecretEditArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[15], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda455
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getSecretEditArray$30(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getSecretEditArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetSecretEditList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda414
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetSecretEditArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda415
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetSecretEditArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda416
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewSecretEdit(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda329(this), new CTClientDataImpl$$ExternalSyntheticLambda330(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetSecretEditArray$31(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetSecretEditArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[15], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda442
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetSecretEditArray$31(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetSecretEditArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfSecretEditArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSecretEditArray(STTrueFalseBlank.Enum[] secretEditArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(secretEditArray, PROPERTY_QNAME[15]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSecretEditArray(int i, STTrueFalseBlank.Enum secretEdit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(secretEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSecretEditArray(STTrueFalseBlank[] secretEditArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(secretEditArray, PROPERTY_QNAME[15]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSecretEditArray(int i, STTrueFalseBlank secretEdit) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(secretEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertSecretEdit(int i, STTrueFalseBlank.Enum secretEdit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[15], i);
            target.setEnumValue(secretEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addSecretEdit(STTrueFalseBlank.Enum secretEdit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[15]);
            target.setEnumValue(secretEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewSecretEdit(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewSecretEdit() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeSecretEdit(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getDefaultList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda488
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDefaultArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda499
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDefaultArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda510
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDefault(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda660(this), new CTClientDataImpl$$ExternalSyntheticLambda671(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getDefaultArray$32(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getDefaultArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[16], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda110
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDefaultArray$32(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getDefaultArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetDefaultList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda555
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDefaultArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda638
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDefaultArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda649
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDefault(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda660(this), new CTClientDataImpl$$ExternalSyntheticLambda671(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetDefaultArray$33(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetDefaultArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[16], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda624
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDefaultArray$33(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetDefaultArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDefaultArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDefaultArray(STTrueFalseBlank.Enum[] xdefaultArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(xdefaultArray, PROPERTY_QNAME[16]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDefaultArray(int i, STTrueFalseBlank.Enum xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(xdefault);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDefaultArray(STTrueFalseBlank[] xdefaultArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(xdefaultArray, PROPERTY_QNAME[16]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDefaultArray(int i, STTrueFalseBlank xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(xdefault);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDefault(int i, STTrueFalseBlank.Enum xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[16], i);
            target.setEnumValue(xdefault);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDefault(STTrueFalseBlank.Enum xdefault) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[16]);
            target.setEnumValue(xdefault);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewDefault(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewDefault() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDefault(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getHelpList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda479
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getHelpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda480
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setHelpArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda481
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertHelp(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda287(this), new CTClientDataImpl$$ExternalSyntheticLambda289(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getHelpArray$34(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getHelpArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[17], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda408
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getHelpArray$34(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getHelpArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetHelpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda284
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetHelpArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda285
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetHelpArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda286
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewHelp(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda287(this), new CTClientDataImpl$$ExternalSyntheticLambda289(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetHelpArray$35(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetHelpArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[17], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda197
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetHelpArray$35(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetHelpArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfHelpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setHelpArray(STTrueFalseBlank.Enum[] helpArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(helpArray, PROPERTY_QNAME[17]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setHelpArray(int i, STTrueFalseBlank.Enum help) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(help);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetHelpArray(STTrueFalseBlank[] helpArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(helpArray, PROPERTY_QNAME[17]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetHelpArray(int i, STTrueFalseBlank help) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(help);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertHelp(int i, STTrueFalseBlank.Enum help) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[17], i);
            target.setEnumValue(help);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addHelp(STTrueFalseBlank.Enum help) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[17]);
            target.setEnumValue(help);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewHelp(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewHelp() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeHelp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getCancelList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda659
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getCancelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda661
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setCancelArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda662
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertCancel(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda663(this), new CTClientDataImpl$$ExternalSyntheticLambda664(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getCancelArray$36(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getCancelArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[18], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda466
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getCancelArray$36(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getCancelArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetCancelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda606
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetCancelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda607
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetCancelArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda608
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewCancel(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda663(this), new CTClientDataImpl$$ExternalSyntheticLambda664(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetCancelArray$37(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetCancelArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[18], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda132
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetCancelArray$37(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetCancelArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfCancelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCancelArray(STTrueFalseBlank.Enum[] cancelArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(cancelArray, PROPERTY_QNAME[18]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCancelArray(int i, STTrueFalseBlank.Enum cancel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(cancel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCancelArray(STTrueFalseBlank[] cancelArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(cancelArray, PROPERTY_QNAME[18]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCancelArray(int i, STTrueFalseBlank cancel) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(cancel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertCancel(int i, STTrueFalseBlank.Enum cancel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[18], i);
            target.setEnumValue(cancel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addCancel(STTrueFalseBlank.Enum cancel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[18]);
            target.setEnumValue(cancel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewCancel(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewCancel() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeCancel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getDismissList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda589
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDismissArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda590
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDismissArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda591
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDismiss(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda494(this), new CTClientDataImpl$$ExternalSyntheticLambda495(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getDismissArray$38(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getDismissArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[19], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda621
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDismissArray$38(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getDismissArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetDismissList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda491
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDismissArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda492
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDismissArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda493
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDismiss(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda494(this), new CTClientDataImpl$$ExternalSyntheticLambda495(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetDismissArray$39(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetDismissArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[19], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda20
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDismissArray$39(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetDismissArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDismissArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDismissArray(STTrueFalseBlank.Enum[] dismissArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dismissArray, PROPERTY_QNAME[19]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDismissArray(int i, STTrueFalseBlank.Enum dismiss) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(dismiss);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDismissArray(STTrueFalseBlank[] dismissArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dismissArray, PROPERTY_QNAME[19]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDismissArray(int i, STTrueFalseBlank dismiss) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(dismiss);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDismiss(int i, STTrueFalseBlank.Enum dismiss) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[19], i);
            target.setEnumValue(dismiss);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDismiss(STTrueFalseBlank.Enum dismiss) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[19]);
            target.setEnumValue(dismiss);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewDismiss(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewDismiss() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDismiss(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getAccelList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda95
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getAccelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda96
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setAccelArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda97
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertAccel(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda98(this), new CTClientDataImpl$$ExternalSyntheticLambda100(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getAccelArray$40(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getAccelArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[20], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda489
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getAccelArray$40(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getAccelArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetAccelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda430
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetAccelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda431
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetAccelArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda433
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewAccel(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda98(this), new CTClientDataImpl$$ExternalSyntheticLambda100(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetAccelArray$41(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetAccelArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[20], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda173
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetAccelArray$41(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetAccelArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfAccelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAccelArray(BigInteger[] accelArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(accelArray, PROPERTY_QNAME[20]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAccelArray(int i, BigInteger accel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(accel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAccelArray(XmlInteger[] accelArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(accelArray, PROPERTY_QNAME[20]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAccelArray(int i, XmlInteger accel) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(accel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertAccel(int i, BigInteger accel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[20], i);
            target.setBigIntegerValue(accel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addAccel(BigInteger accel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[20]);
            target.setBigIntegerValue(accel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewAccel(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewAccel() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeAccel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getAccel2List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda437
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getAccel2Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda438
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setAccel2Array(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda439
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertAccel2(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda440(this), new CTClientDataImpl$$ExternalSyntheticLambda441(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getAccel2Array$42(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getAccel2Array() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[21], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda369
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getAccel2Array$42(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getAccel2Array(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetAccel2List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda456
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetAccel2Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda457
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetAccel2Array(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda458
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewAccel2(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda440(this), new CTClientDataImpl$$ExternalSyntheticLambda441(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetAccel2Array$43(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetAccel2Array() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[21], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda429
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetAccel2Array$43(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetAccel2Array(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfAccel2Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAccel2Array(BigInteger[] accel2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(accel2Array, PROPERTY_QNAME[21]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAccel2Array(int i, BigInteger accel2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(accel2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAccel2Array(XmlInteger[] accel2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(accel2Array, PROPERTY_QNAME[21]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAccel2Array(int i, XmlInteger accel2) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(accel2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertAccel2(int i, BigInteger accel2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[21], i);
            target.setBigIntegerValue(accel2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addAccel2(BigInteger accel2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[21]);
            target.setBigIntegerValue(accel2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewAccel2(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewAccel2() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeAccel2(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getRowList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda613
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getRowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda614
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setRowArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda615
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertRow(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda373(this), new CTClientDataImpl$$ExternalSyntheticLambda374(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getRowArray$44(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getRowArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[22], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda126
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getRowArray$44(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getRowArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetRowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda370
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetRowArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda371
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetRowArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda372
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewRow(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda373(this), new CTClientDataImpl$$ExternalSyntheticLambda374(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetRowArray$45(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetRowArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[22], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda490
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetRowArray$45(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetRowArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfRowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setRowArray(BigInteger[] rowArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(rowArray, PROPERTY_QNAME[22]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setRowArray(int i, BigInteger row) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(row);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetRowArray(XmlInteger[] rowArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(rowArray, PROPERTY_QNAME[22]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetRowArray(int i, XmlInteger row) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(row);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertRow(int i, BigInteger row) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[22], i);
            target.setBigIntegerValue(row);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addRow(BigInteger row) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[22]);
            target.setBigIntegerValue(row);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewRow(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewRow() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeRow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getColumnList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda376
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getColumnArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda378
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setColumnArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda379
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertColumn(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda380(this), new CTClientDataImpl$$ExternalSyntheticLambda381(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getColumnArray$46(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getColumnArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[23], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda275
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getColumnArray$46(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getColumnArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetColumnList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda434
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetColumnArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda435
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetColumnArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda436
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewColumn(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda380(this), new CTClientDataImpl$$ExternalSyntheticLambda381(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetColumnArray$47(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetColumnArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[23], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda135
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetColumnArray$47(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetColumnArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfColumnArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setColumnArray(BigInteger[] columnArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(columnArray, PROPERTY_QNAME[23]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setColumnArray(int i, BigInteger column) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(column);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetColumnArray(XmlInteger[] columnArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(columnArray, PROPERTY_QNAME[23]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetColumnArray(int i, XmlInteger column) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(column);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertColumn(int i, BigInteger column) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[23], i);
            target.setBigIntegerValue(column);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addColumn(BigInteger column) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[23]);
            target.setBigIntegerValue(column);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewColumn(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewColumn() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeColumn(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getVisibleList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda168
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getVisibleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda169
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setVisibleArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda170
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertVisible(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda171(this), new CTClientDataImpl$$ExternalSyntheticLambda172(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getVisibleArray$48(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getVisibleArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[24], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda7
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getVisibleArray$48(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getVisibleArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetVisibleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda616
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetVisibleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda617
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetVisibleArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda618
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewVisible(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda171(this), new CTClientDataImpl$$ExternalSyntheticLambda172(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetVisibleArray$49(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetVisibleArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[24], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda229
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetVisibleArray$49(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetVisibleArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfVisibleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setVisibleArray(STTrueFalseBlank.Enum[] visibleArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(visibleArray, PROPERTY_QNAME[24]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setVisibleArray(int i, STTrueFalseBlank.Enum visible) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(visible);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetVisibleArray(STTrueFalseBlank[] visibleArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(visibleArray, PROPERTY_QNAME[24]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetVisibleArray(int i, STTrueFalseBlank visible) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(visible);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertVisible(int i, STTrueFalseBlank.Enum visible) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[24], i);
            target.setEnumValue(visible);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addVisible(STTrueFalseBlank.Enum visible) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[24]);
            target.setEnumValue(visible);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewVisible(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewVisible() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeVisible(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getRowHiddenList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda572
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getRowHiddenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda573
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setRowHiddenArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda574
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertRowHidden(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda280(this), new CTClientDataImpl$$ExternalSyntheticLambda281(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getRowHiddenArray$50(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getRowHiddenArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[25], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda560
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getRowHiddenArray$50(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getRowHiddenArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetRowHiddenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda276
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetRowHiddenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda278
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetRowHiddenArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda279
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewRowHidden(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda280(this), new CTClientDataImpl$$ExternalSyntheticLambda281(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetRowHiddenArray$51(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetRowHiddenArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[25], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda465
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetRowHiddenArray$51(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetRowHiddenArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfRowHiddenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setRowHiddenArray(STTrueFalseBlank.Enum[] rowHiddenArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(rowHiddenArray, PROPERTY_QNAME[25]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setRowHiddenArray(int i, STTrueFalseBlank.Enum rowHidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(rowHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetRowHiddenArray(STTrueFalseBlank[] rowHiddenArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(rowHiddenArray, PROPERTY_QNAME[25]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetRowHiddenArray(int i, STTrueFalseBlank rowHidden) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(rowHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertRowHidden(int i, STTrueFalseBlank.Enum rowHidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[25], i);
            target.setEnumValue(rowHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addRowHidden(STTrueFalseBlank.Enum rowHidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[25]);
            target.setEnumValue(rowHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewRowHidden(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewRowHidden() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeRowHidden(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getColHiddenList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda266
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getColHiddenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda277
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setColHiddenArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda288
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertColHidden(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda299(this), new CTClientDataImpl$$ExternalSyntheticLambda310(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getColHiddenArray$52(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getColHiddenArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[26], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda367
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getColHiddenArray$52(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getColHiddenArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetColHiddenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda382
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetColHiddenArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda383
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetColHiddenArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda384
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewColHidden(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda299(this), new CTClientDataImpl$$ExternalSyntheticLambda310(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetColHiddenArray$53(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetColHiddenArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[26], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda575
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetColHiddenArray$53(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetColHiddenArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfColHiddenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setColHiddenArray(STTrueFalseBlank.Enum[] colHiddenArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(colHiddenArray, PROPERTY_QNAME[26]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setColHiddenArray(int i, STTrueFalseBlank.Enum colHidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(colHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetColHiddenArray(STTrueFalseBlank[] colHiddenArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(colHiddenArray, PROPERTY_QNAME[26]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetColHiddenArray(int i, STTrueFalseBlank colHidden) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(colHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertColHidden(int i, STTrueFalseBlank.Enum colHidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[26], i);
            target.setEnumValue(colHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addColHidden(STTrueFalseBlank.Enum colHidden) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[26]);
            target.setEnumValue(colHidden);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewColHidden(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewColHidden() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeColHidden(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getVTEditList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda390
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getVTEditArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda391
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setVTEditArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda392
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertVTEdit(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda307(this), new CTClientDataImpl$$ExternalSyntheticLambda308(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getVTEditArray$54(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getVTEditArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[27], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda545
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getVTEditArray$54(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getVTEditArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetVTEditList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda304
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetVTEditArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda305
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetVTEditArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda306
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewVTEdit(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda307(this), new CTClientDataImpl$$ExternalSyntheticLambda308(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetVTEditArray$55(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetVTEditArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[27], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda580
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetVTEditArray$55(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetVTEditArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfVTEditArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setVTEditArray(BigInteger[] vtEditArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(vtEditArray, PROPERTY_QNAME[27]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setVTEditArray(int i, BigInteger vtEdit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(vtEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetVTEditArray(XmlInteger[] vtEditArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(vtEditArray, PROPERTY_QNAME[27]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetVTEditArray(int i, XmlInteger vtEdit) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(vtEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertVTEdit(int i, BigInteger vtEdit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[27], i);
            target.setBigIntegerValue(vtEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addVTEdit(BigInteger vtEdit) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[27]);
            target.setBigIntegerValue(vtEdit);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewVTEdit(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewVTEdit() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeVTEdit(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getMultiLineList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda290
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getMultiLineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda291
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setMultiLineArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda292
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertMultiLine(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda123(this), new CTClientDataImpl$$ExternalSyntheticLambda124(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getMultiLineArray$56(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getMultiLineArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[28], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda605
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getMultiLineArray$56(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getMultiLineArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetMultiLineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda119
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetMultiLineArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda120
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetMultiLineArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda121
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewMultiLine(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda123(this), new CTClientDataImpl$$ExternalSyntheticLambda124(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetMultiLineArray$57(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetMultiLineArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[28], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda315
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetMultiLineArray$57(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetMultiLineArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfMultiLineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMultiLineArray(STTrueFalseBlank.Enum[] multiLineArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(multiLineArray, PROPERTY_QNAME[28]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMultiLineArray(int i, STTrueFalseBlank.Enum multiLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(multiLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMultiLineArray(STTrueFalseBlank[] multiLineArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(multiLineArray, PROPERTY_QNAME[28]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMultiLineArray(int i, STTrueFalseBlank multiLine) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(multiLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertMultiLine(int i, STTrueFalseBlank.Enum multiLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[28], i);
            target.setEnumValue(multiLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addMultiLine(STTrueFalseBlank.Enum multiLine) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[28]);
            target.setEnumValue(multiLine);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewMultiLine(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewMultiLine() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeMultiLine(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getVScrollList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda564
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getVScrollArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda565
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setVScrollArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda567
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertVScroll(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda644(this), new CTClientDataImpl$$ExternalSyntheticLambda645(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getVScrollArray$58(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getVScrollArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[29], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda552
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getVScrollArray$58(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getVScrollArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetVScrollList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda641
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetVScrollArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda642
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetVScrollArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda643
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewVScroll(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda644(this), new CTClientDataImpl$$ExternalSyntheticLambda645(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetVScrollArray$59(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetVScrollArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[29], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda474
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetVScrollArray$59(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetVScrollArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfVScrollArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setVScrollArray(STTrueFalseBlank.Enum[] vScrollArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(vScrollArray, PROPERTY_QNAME[29]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setVScrollArray(int i, STTrueFalseBlank.Enum vScroll) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(vScroll);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetVScrollArray(STTrueFalseBlank[] vScrollArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(vScrollArray, PROPERTY_QNAME[29]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetVScrollArray(int i, STTrueFalseBlank vScroll) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(vScroll);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertVScroll(int i, STTrueFalseBlank.Enum vScroll) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[29], i);
            target.setEnumValue(vScroll);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addVScroll(STTrueFalseBlank.Enum vScroll) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[29]);
            target.setEnumValue(vScroll);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewVScroll(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewVScroll() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeVScroll(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getValidIdsList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda317
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getValidIdsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda318
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setValidIdsArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda319
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertValidIds(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda320(this), new CTClientDataImpl$$ExternalSyntheticLambda322(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getValidIdsArray$60(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getValidIdsArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[30], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda595
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getValidIdsArray$60(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getValidIdsArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetValidIdsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda602
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetValidIdsArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda603
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetValidIdsArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda604
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewValidIds(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda320(this), new CTClientDataImpl$$ExternalSyntheticLambda322(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetValidIdsArray$61(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetValidIdsArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[30], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda49
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetValidIdsArray$61(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetValidIdsArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfValidIdsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setValidIdsArray(STTrueFalseBlank.Enum[] validIdsArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(validIdsArray, PROPERTY_QNAME[30]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setValidIdsArray(int i, STTrueFalseBlank.Enum validIds) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(validIds);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetValidIdsArray(STTrueFalseBlank[] validIdsArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(validIdsArray, PROPERTY_QNAME[30]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetValidIdsArray(int i, STTrueFalseBlank validIds) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(validIds);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertValidIds(int i, STTrueFalseBlank.Enum validIds) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[30], i);
            target.setEnumValue(validIds);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addValidIds(STTrueFalseBlank.Enum validIds) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[30]);
            target.setEnumValue(validIds);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewValidIds(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewValidIds() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeValidIds(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getFmlaRangeList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda553
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getFmlaRangeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda556
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setFmlaRangeArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda557
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertFmlaRange(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda558(this), new CTClientDataImpl$$ExternalSyntheticLambda559(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getFmlaRangeArray$62(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getFmlaRangeArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[31], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda154
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getFmlaRangeArray$62(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getFmlaRangeArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetFmlaRangeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda596
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetFmlaRangeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda597
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetFmlaRangeArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda598
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewFmlaRange(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda558(this), new CTClientDataImpl$$ExternalSyntheticLambda559(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetFmlaRangeArray$63(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetFmlaRangeArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[31], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda162
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetFmlaRangeArray$63(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetFmlaRangeArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfFmlaRangeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaRangeArray(String[] fmlaRangeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaRangeArray, PROPERTY_QNAME[31]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaRangeArray(int i, String fmlaRange) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(fmlaRange);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaRangeArray(XmlString[] fmlaRangeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaRangeArray, PROPERTY_QNAME[31]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaRangeArray(int i, XmlString fmlaRange) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(fmlaRange);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertFmlaRange(int i, String fmlaRange) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[31], i);
            target.setStringValue(fmlaRange);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addFmlaRange(String fmlaRange) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[31]);
            target.setStringValue(fmlaRange);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewFmlaRange(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewFmlaRange() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeFmlaRange(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getWidthMinList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda148
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getWidthMinArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda149
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setWidthMinArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda150
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertWidthMin(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda366(this), new CTClientDataImpl$$ExternalSyntheticLambda377(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getWidthMinArray$64(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getWidthMinArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[32], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda396
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getWidthMinArray$64(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getWidthMinArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetWidthMinList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda332
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetWidthMinArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda344
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetWidthMinArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda355
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewWidthMin(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda366(this), new CTClientDataImpl$$ExternalSyntheticLambda377(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetWidthMinArray$65(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetWidthMinArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[32], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda409
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetWidthMinArray$65(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetWidthMinArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfWidthMinArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setWidthMinArray(BigInteger[] widthMinArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(widthMinArray, PROPERTY_QNAME[32]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setWidthMinArray(int i, BigInteger widthMin) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(widthMin);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetWidthMinArray(XmlInteger[] widthMinArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(widthMinArray, PROPERTY_QNAME[32]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetWidthMinArray(int i, XmlInteger widthMin) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(widthMin);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertWidthMin(int i, BigInteger widthMin) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[32], i);
            target.setBigIntegerValue(widthMin);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addWidthMin(BigInteger widthMin) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[32]);
            target.setBigIntegerValue(widthMin);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewWidthMin(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewWidthMin() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeWidthMin(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getSelList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getSelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda111
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setSelArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda222
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertSel(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda333(this), new CTClientDataImpl$$ExternalSyntheticLambda444(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getSelArray$66(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getSelArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[33], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda214
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getSelArray$66(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getSelArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetSelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda420
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetSelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda422
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetSelArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda423
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewSel(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda333(this), new CTClientDataImpl$$ExternalSyntheticLambda444(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetSelArray$67(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetSelArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[33], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda544
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetSelArray$67(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetSelArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfSelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSelArray(BigInteger[] selArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(selArray, PROPERTY_QNAME[33]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSelArray(int i, BigInteger sel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(sel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSelArray(XmlInteger[] selArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(selArray, PROPERTY_QNAME[33]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSelArray(int i, XmlInteger sel) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(sel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertSel(int i, BigInteger sel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[33], i);
            target.setBigIntegerValue(sel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addSel(BigInteger sel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[33]);
            target.setBigIntegerValue(sel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewSel(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewSel() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeSel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getNoThreeD2List() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda164
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getNoThreeD2Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda165
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setNoThreeD2Array(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda167
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertNoThreeD2(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda112(this), new CTClientDataImpl$$ExternalSyntheticLambda113(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getNoThreeD2Array$68(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getNoThreeD2Array() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[34], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda388
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getNoThreeD2Array$68(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getNoThreeD2Array(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetNoThreeD2List() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda107
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetNoThreeD2Array(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda108
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetNoThreeD2Array(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda109
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewNoThreeD2(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda112(this), new CTClientDataImpl$$ExternalSyntheticLambda113(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetNoThreeD2Array$69(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetNoThreeD2Array() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[34], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda64
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetNoThreeD2Array$69(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetNoThreeD2Array(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfNoThreeD2Array() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[34]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setNoThreeD2Array(STTrueFalseBlank.Enum[] noThreeD2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(noThreeD2Array, PROPERTY_QNAME[34]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setNoThreeD2Array(int i, STTrueFalseBlank.Enum noThreeD2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(noThreeD2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetNoThreeD2Array(STTrueFalseBlank[] noThreeD2Array) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(noThreeD2Array, PROPERTY_QNAME[34]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetNoThreeD2Array(int i, STTrueFalseBlank noThreeD2) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(noThreeD2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertNoThreeD2(int i, STTrueFalseBlank.Enum noThreeD2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[34], i);
            target.setEnumValue(noThreeD2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addNoThreeD2(STTrueFalseBlank.Enum noThreeD2) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[34]);
            target.setEnumValue(noThreeD2);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewNoThreeD2(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[34], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewNoThreeD2() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeNoThreeD2(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getSelTypeList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda620
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getSelTypeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda622
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setSelTypeArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda623
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertSelType(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda427(this), new CTClientDataImpl$$ExternalSyntheticLambda428(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getSelTypeArray$70(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getSelTypeArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[35], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda477
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getSelTypeArray$70(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getSelTypeArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetSelTypeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda424
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetSelTypeArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda425
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetSelTypeArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda426
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewSelType(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda427(this), new CTClientDataImpl$$ExternalSyntheticLambda428(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetSelTypeArray$71(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetSelTypeArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[35], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda316
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetSelTypeArray$71(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetSelTypeArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfSelTypeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[35]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSelTypeArray(String[] selTypeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(selTypeArray, PROPERTY_QNAME[35]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setSelTypeArray(int i, String selType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(selType);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSelTypeArray(XmlString[] selTypeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(selTypeArray, PROPERTY_QNAME[35]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetSelTypeArray(int i, XmlString selType) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(selType);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertSelType(int i, String selType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[35], i);
            target.setStringValue(selType);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addSelType(String selType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[35]);
            target.setStringValue(selType);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewSelType(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[35], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewSelType() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeSelType(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getMultiSelList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda211
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getMultiSelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda212
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setMultiSelArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda213
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertMultiSel(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda86(this), new CTClientDataImpl$$ExternalSyntheticLambda87(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getMultiSelArray$72(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getMultiSelArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[36], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda274
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getMultiSelArray$72(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getMultiSelArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetMultiSelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda83
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetMultiSelArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda84
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetMultiSelArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda85
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewMultiSel(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda86(this), new CTClientDataImpl$$ExternalSyntheticLambda87(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetMultiSelArray$73(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetMultiSelArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[36], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda521
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetMultiSelArray$73(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetMultiSelArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfMultiSelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[36]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMultiSelArray(String[] multiSelArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(multiSelArray, PROPERTY_QNAME[36]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMultiSelArray(int i, String multiSel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(multiSel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMultiSelArray(XmlString[] multiSelArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(multiSelArray, PROPERTY_QNAME[36]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMultiSelArray(int i, XmlString multiSel) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(multiSel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertMultiSel(int i, String multiSel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[36], i);
            target.setStringValue(multiSel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addMultiSel(String multiSel) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[36]);
            target.setStringValue(multiSel);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewMultiSel(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[36], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewMultiSel() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeMultiSel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getLCTList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda215
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getLCTArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda216
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setLCTArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda217
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertLCT(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda218(this), new CTClientDataImpl$$ExternalSyntheticLambda219(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getLCTArray$74(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getLCTArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[37], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda282
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getLCTArray$74(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getLCTArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetLCTList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda386
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetLCTArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda387
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetLCTArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda389
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewLCT(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda218(this), new CTClientDataImpl$$ExternalSyntheticLambda219(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetLCTArray$75(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetLCTArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[37], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda48
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetLCTArray$75(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetLCTArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfLCTArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[37]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setLCTArray(String[] lctArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lctArray, PROPERTY_QNAME[37]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setLCTArray(int i, String lct) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(lct);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetLCTArray(XmlString[] lctArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(lctArray, PROPERTY_QNAME[37]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetLCTArray(int i, XmlString lct) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(lct);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertLCT(int i, String lct) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[37], i);
            target.setStringValue(lct);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addLCT(String lct) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[37]);
            target.setStringValue(lct);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewLCT(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[37], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewLCT() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeLCT(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getListItemList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda21
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getListItemArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda23
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setListItemArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda24
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertListItem(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda25(this), new CTClientDataImpl$$ExternalSyntheticLambda26(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getListItemArray$76(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getListItemArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[38], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda348
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getListItemArray$76(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getListItemArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetListItemList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda51
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetListItemArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda52
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetListItemArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda53
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewListItem(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda25(this), new CTClientDataImpl$$ExternalSyntheticLambda26(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetListItemArray$77(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetListItemArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[38], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda140
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetListItemArray$77(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetListItemArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfListItemArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[38]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setListItemArray(String[] listItemArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(listItemArray, PROPERTY_QNAME[38]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setListItemArray(int i, String listItem) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(listItem);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetListItemArray(XmlString[] listItemArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(listItemArray, PROPERTY_QNAME[38]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetListItemArray(int i, XmlString listItem) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(listItem);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertListItem(int i, String listItem) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[38], i);
            target.setStringValue(listItem);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addListItem(String listItem) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[38]);
            target.setStringValue(listItem);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewListItem(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[38], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewListItem() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeListItem(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getDropStyleList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda248
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDropStyleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda249
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDropStyleArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda250
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDropStyle(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda566(this), new CTClientDataImpl$$ExternalSyntheticLambda577(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getDropStyleArray$78(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getDropStyleArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[39], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda77
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDropStyleArray$78(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getDropStyleArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[39], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetDropStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda532
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDropStyleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda543
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDropStyleArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda554
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDropStyle(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda566(this), new CTClientDataImpl$$ExternalSyntheticLambda577(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetDropStyleArray$79(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetDropStyleArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[39], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda472
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDropStyleArray$79(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetDropStyleArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[39], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDropStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[39]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDropStyleArray(String[] dropStyleArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dropStyleArray, PROPERTY_QNAME[39]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDropStyleArray(int i, String dropStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[39], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(dropStyle);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDropStyleArray(XmlString[] dropStyleArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dropStyleArray, PROPERTY_QNAME[39]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDropStyleArray(int i, XmlString dropStyle) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[39], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(dropStyle);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDropStyle(int i, String dropStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[39], i);
            target.setStringValue(dropStyle);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDropStyle(String dropStyle) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[39]);
            target.setStringValue(dropStyle);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewDropStyle(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[39], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewDropStyle() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[39]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDropStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[39], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getColoredList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda192
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getColoredArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda193
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setColoredArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda194
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertColored(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda195(this), new CTClientDataImpl$$ExternalSyntheticLambda196(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getColoredArray$80(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getColoredArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[40], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda6
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getColoredArray$80(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getColoredArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[40], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetColoredList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda402
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetColoredArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda403
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetColoredArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda404
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewColored(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda195(this), new CTClientDataImpl$$ExternalSyntheticLambda196(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetColoredArray$81(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetColoredArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[40], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda652
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetColoredArray$81(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetColoredArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[40], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfColoredArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[40]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setColoredArray(STTrueFalseBlank.Enum[] coloredArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(coloredArray, PROPERTY_QNAME[40]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setColoredArray(int i, STTrueFalseBlank.Enum colored) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[40], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(colored);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetColoredArray(STTrueFalseBlank[] coloredArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(coloredArray, PROPERTY_QNAME[40]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetColoredArray(int i, STTrueFalseBlank colored) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[40], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(colored);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertColored(int i, STTrueFalseBlank.Enum colored) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[40], i);
            target.setEnumValue(colored);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addColored(STTrueFalseBlank.Enum colored) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[40]);
            target.setEnumValue(colored);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewColored(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[40], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewColored() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[40]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeColored(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[40], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getDropLinesList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda561
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDropLinesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda562
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDropLinesArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda563
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDropLines(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda503(this), new CTClientDataImpl$$ExternalSyntheticLambda504(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getDropLinesArray$82(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getDropLinesArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[41], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda447
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDropLinesArray$82(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getDropLinesArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[41], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetDropLinesList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda500
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDropLinesArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda501
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDropLinesArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda502
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDropLines(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda503(this), new CTClientDataImpl$$ExternalSyntheticLambda504(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetDropLinesArray$83(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetDropLinesArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[41], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda347
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDropLinesArray$83(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetDropLinesArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[41], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDropLinesArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[41]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDropLinesArray(BigInteger[] dropLinesArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dropLinesArray, PROPERTY_QNAME[41]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDropLinesArray(int i, BigInteger dropLines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[41], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(dropLines);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDropLinesArray(XmlInteger[] dropLinesArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dropLinesArray, PROPERTY_QNAME[41]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDropLinesArray(int i, XmlInteger dropLines) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[41], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(dropLines);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDropLines(int i, BigInteger dropLines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[41], i);
            target.setBigIntegerValue(dropLines);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDropLines(BigInteger dropLines) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[41]);
            target.setBigIntegerValue(dropLines);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewDropLines(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[41], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewDropLines() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[41]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDropLines(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[41], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getCheckedList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda258
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getCheckedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda259
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setCheckedArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda260
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertChecked(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda261(this), new CTClientDataImpl$$ExternalSyntheticLambda262(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getCheckedArray$84(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getCheckedArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[42], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda471
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getCheckedArray$84(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getCheckedArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[42], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetCheckedList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda539
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetCheckedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda540
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetCheckedArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda541
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewChecked(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda261(this), new CTClientDataImpl$$ExternalSyntheticLambda262(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetCheckedArray$85(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetCheckedArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[42], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda331
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetCheckedArray$85(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetCheckedArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[42], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfCheckedArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[42]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCheckedArray(BigInteger[] checkedArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(checkedArray, PROPERTY_QNAME[42]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCheckedArray(int i, BigInteger checked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[42], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(checked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCheckedArray(XmlInteger[] checkedArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(checkedArray, PROPERTY_QNAME[42]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCheckedArray(int i, XmlInteger checked) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[42], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(checked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertChecked(int i, BigInteger checked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[42], i);
            target.setBigIntegerValue(checked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addChecked(BigInteger checked) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[42]);
            target.setBigIntegerValue(checked);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewChecked(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[42], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewChecked() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[42]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeChecked(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[42], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getFmlaLinkList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda393
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getFmlaLinkArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda394
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setFmlaLinkArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda395
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertFmlaLink(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda12(this), new CTClientDataImpl$$ExternalSyntheticLambda13(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getFmlaLinkArray$86(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getFmlaLinkArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[43], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda506
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getFmlaLinkArray$86(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getFmlaLinkArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[43], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetFmlaLinkList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetFmlaLinkArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda9
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetFmlaLinkArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda10
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewFmlaLink(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda12(this), new CTClientDataImpl$$ExternalSyntheticLambda13(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetFmlaLinkArray$87(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetFmlaLinkArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[43], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda470
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetFmlaLinkArray$87(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetFmlaLinkArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[43], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfFmlaLinkArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[43]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaLinkArray(String[] fmlaLinkArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaLinkArray, PROPERTY_QNAME[43]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaLinkArray(int i, String fmlaLink) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[43], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(fmlaLink);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaLinkArray(XmlString[] fmlaLinkArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaLinkArray, PROPERTY_QNAME[43]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaLinkArray(int i, XmlString fmlaLink) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[43], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(fmlaLink);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertFmlaLink(int i, String fmlaLink) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[43], i);
            target.setStringValue(fmlaLink);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addFmlaLink(String fmlaLink) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[43]);
            target.setStringValue(fmlaLink);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewFmlaLink(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[43], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewFmlaLink() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[43]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeFmlaLink(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[43], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getFmlaPictList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda460
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getFmlaPictArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda461
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setFmlaPictArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda462
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertFmlaPict(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda463(this), new CTClientDataImpl$$ExternalSyntheticLambda464(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getFmlaPictArray$88(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getFmlaPictArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[44], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda511
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getFmlaPictArray$88(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getFmlaPictArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[44], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetFmlaPictList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda592
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetFmlaPictArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda593
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetFmlaPictArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda594
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewFmlaPict(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda463(this), new CTClientDataImpl$$ExternalSyntheticLambda464(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetFmlaPictArray$89(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetFmlaPictArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[44], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda487
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetFmlaPictArray$89(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetFmlaPictArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[44], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfFmlaPictArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[44]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaPictArray(String[] fmlaPictArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaPictArray, PROPERTY_QNAME[44]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaPictArray(int i, String fmlaPict) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[44], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(fmlaPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaPictArray(XmlString[] fmlaPictArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaPictArray, PROPERTY_QNAME[44]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaPictArray(int i, XmlString fmlaPict) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[44], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(fmlaPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertFmlaPict(int i, String fmlaPict) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[44], i);
            target.setStringValue(fmlaPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addFmlaPict(String fmlaPict) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[44]);
            target.setStringValue(fmlaPict);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewFmlaPict(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[44], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewFmlaPict() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[44]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeFmlaPict(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[44], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getNoThreeDList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda270
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getNoThreeDArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda271
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setNoThreeDArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda272
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertNoThreeD(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda45(this), new CTClientDataImpl$$ExternalSyntheticLambda46(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getNoThreeDArray$90(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getNoThreeDArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[45], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda139
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getNoThreeDArray$90(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getNoThreeDArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[45], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetNoThreeDList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda41
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetNoThreeDArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda42
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetNoThreeDArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda43
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewNoThreeD(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda45(this), new CTClientDataImpl$$ExternalSyntheticLambda46(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetNoThreeDArray$91(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetNoThreeDArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[45], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda251
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetNoThreeDArray$91(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetNoThreeDArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[45], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfNoThreeDArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[45]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setNoThreeDArray(STTrueFalseBlank.Enum[] noThreeDArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(noThreeDArray, PROPERTY_QNAME[45]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setNoThreeDArray(int i, STTrueFalseBlank.Enum noThreeD) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[45], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(noThreeD);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetNoThreeDArray(STTrueFalseBlank[] noThreeDArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(noThreeDArray, PROPERTY_QNAME[45]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetNoThreeDArray(int i, STTrueFalseBlank noThreeD) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[45], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(noThreeD);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertNoThreeD(int i, STTrueFalseBlank.Enum noThreeD) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[45], i);
            target.setEnumValue(noThreeD);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addNoThreeD(STTrueFalseBlank.Enum noThreeD) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[45]);
            target.setEnumValue(noThreeD);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewNoThreeD(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[45], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewNoThreeD() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[45]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeNoThreeD(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[45], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getFirstButtonList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda411
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getFirstButtonArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda412
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setFirstButtonArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda413
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertFirstButton(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda337(this), new CTClientDataImpl$$ExternalSyntheticLambda338(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getFirstButtonArray$92(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getFirstButtonArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[46], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda27
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getFirstButtonArray$92(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getFirstButtonArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[46], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetFirstButtonList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda334
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetFirstButtonArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda335
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetFirstButtonArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda336
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewFirstButton(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda337(this), new CTClientDataImpl$$ExternalSyntheticLambda338(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetFirstButtonArray$93(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetFirstButtonArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[46], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda134
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetFirstButtonArray$93(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetFirstButtonArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[46], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfFirstButtonArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[46]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFirstButtonArray(STTrueFalseBlank.Enum[] firstButtonArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(firstButtonArray, PROPERTY_QNAME[46]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFirstButtonArray(int i, STTrueFalseBlank.Enum firstButton) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[46], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(firstButton);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFirstButtonArray(STTrueFalseBlank[] firstButtonArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(firstButtonArray, PROPERTY_QNAME[46]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFirstButtonArray(int i, STTrueFalseBlank firstButton) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[46], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(firstButton);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertFirstButton(int i, STTrueFalseBlank.Enum firstButton) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[46], i);
            target.setEnumValue(firstButton);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addFirstButton(STTrueFalseBlank.Enum firstButton) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[46]);
            target.setEnumValue(firstButton);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewFirstButton(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[46], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewFirstButton() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[46]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeFirstButton(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[46], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getFmlaGroupList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda239
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getFmlaGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda240
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setFmlaGroupArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda241
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertFmlaGroup(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda203(this), new CTClientDataImpl$$ExternalSyntheticLambda204(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getFmlaGroupArray$94(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getFmlaGroupArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[47], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda273
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getFmlaGroupArray$94(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getFmlaGroupArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[47], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetFmlaGroupList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda200
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetFmlaGroupArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda201
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetFmlaGroupArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda202
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewFmlaGroup(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda203(this), new CTClientDataImpl$$ExternalSyntheticLambda204(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetFmlaGroupArray$95(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetFmlaGroupArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[47], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda449
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetFmlaGroupArray$95(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetFmlaGroupArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[47], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfFmlaGroupArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[47]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaGroupArray(String[] fmlaGroupArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaGroupArray, PROPERTY_QNAME[47]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaGroupArray(int i, String fmlaGroup) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[47], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(fmlaGroup);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaGroupArray(XmlString[] fmlaGroupArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaGroupArray, PROPERTY_QNAME[47]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaGroupArray(int i, XmlString fmlaGroup) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[47], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(fmlaGroup);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertFmlaGroup(int i, String fmlaGroup) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[47], i);
            target.setStringValue(fmlaGroup);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addFmlaGroup(String fmlaGroup) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[47]);
            target.setStringValue(fmlaGroup);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewFmlaGroup(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[47], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewFmlaGroup() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[47]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeFmlaGroup(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[47], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getValList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda450
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getValArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda451
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setValArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda452
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertVal(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda453(this), new CTClientDataImpl$$ExternalSyntheticLambda454(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getValArray$96(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getValArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[48], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda658
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getValArray$96(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getValArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[48], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetValList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda496
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetValArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda497
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetValArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda498
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewVal(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda453(this), new CTClientDataImpl$$ExternalSyntheticLambda454(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetValArray$97(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetValArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[48], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda47
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetValArray$97(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetValArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[48], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfValArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[48]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setValArray(BigInteger[] valArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(valArray, PROPERTY_QNAME[48]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setValArray(int i, BigInteger val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[48], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(val);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetValArray(XmlInteger[] valArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(valArray, PROPERTY_QNAME[48]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetValArray(int i, XmlInteger val) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[48], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(val);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertVal(int i, BigInteger val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[48], i);
            target.setBigIntegerValue(val);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addVal(BigInteger val) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[48]);
            target.setBigIntegerValue(val);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewVal(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[48], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewVal() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[48]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeVal(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[48], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getMinList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda178
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getMinArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda179
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setMinArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda180
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertMin(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda60(this), new CTClientDataImpl$$ExternalSyntheticLambda61(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getMinArray$98(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getMinArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[49], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda303
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getMinArray$98(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getMinArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[49], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetMinList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda57
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetMinArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda58
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetMinArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda59
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewMin(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda60(this), new CTClientDataImpl$$ExternalSyntheticLambda61(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetMinArray$99(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetMinArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[49], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda311
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetMinArray$99(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetMinArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[49], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfMinArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[49]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMinArray(BigInteger[] minArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(minArray, PROPERTY_QNAME[49]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMinArray(int i, BigInteger min) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[49], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(min);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMinArray(XmlInteger[] minArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(minArray, PROPERTY_QNAME[49]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMinArray(int i, XmlInteger min) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[49], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(min);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertMin(int i, BigInteger min) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[49], i);
            target.setBigIntegerValue(min);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addMin(BigInteger min) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[49]);
            target.setBigIntegerValue(min);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewMin(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[49], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewMin() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[49]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeMin(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[49], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getMaxList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda186
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getMaxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda187
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setMaxArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda189
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertMax(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda190(this), new CTClientDataImpl$$ExternalSyntheticLambda191(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getMaxArray$100(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getMaxArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[50], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda63
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getMaxArray$100(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getMaxArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[50], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetMaxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda576
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetMaxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda578
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetMaxArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda579
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewMax(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda190(this), new CTClientDataImpl$$ExternalSyntheticLambda191(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetMaxArray$101(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetMaxArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[50], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda88
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetMaxArray$101(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetMaxArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[50], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfMaxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[50]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMaxArray(BigInteger[] maxArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(maxArray, PROPERTY_QNAME[50]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMaxArray(int i, BigInteger max) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[50], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(max);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMaxArray(XmlInteger[] maxArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(maxArray, PROPERTY_QNAME[50]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMaxArray(int i, XmlInteger max) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[50], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(max);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertMax(int i, BigInteger max) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[50], i);
            target.setBigIntegerValue(max);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addMax(BigInteger max) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[50]);
            target.setBigIntegerValue(max);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewMax(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[50], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewMax() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[50]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeMax(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[50], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getIncList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda205
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getIncArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda206
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setIncArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda207
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertInc(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda208(this), new CTClientDataImpl$$ExternalSyntheticLambda209(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getIncArray$102(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getIncArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[51], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda302
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getIncArray$102(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getIncArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[51], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetIncList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda293
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetIncArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda294
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetIncArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda295
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewInc(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda208(this), new CTClientDataImpl$$ExternalSyntheticLambda209(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetIncArray$103(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetIncArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[51], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda62
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetIncArray$103(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetIncArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[51], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfIncArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[51]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setIncArray(BigInteger[] incArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(incArray, PROPERTY_QNAME[51]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setIncArray(int i, BigInteger inc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[51], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(inc);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetIncArray(XmlInteger[] incArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(incArray, PROPERTY_QNAME[51]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetIncArray(int i, XmlInteger inc) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[51], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(inc);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertInc(int i, BigInteger inc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[51], i);
            target.setBigIntegerValue(inc);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addInc(BigInteger inc) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[51]);
            target.setBigIntegerValue(inc);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewInc(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[51], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewInc() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[51]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeInc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[51], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getPageList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda525
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getPageArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda526
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setPageArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda527
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertPage(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda361(this), new CTClientDataImpl$$ExternalSyntheticLambda362(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getPageArray$104(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getPageArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[52], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda54
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getPageArray$104(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getPageArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[52], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetPageList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda358
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetPageArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda359
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetPageArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda360
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewPage(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda361(this), new CTClientDataImpl$$ExternalSyntheticLambda362(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetPageArray$105(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetPageArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[52], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda533
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetPageArray$105(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetPageArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[52], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfPageArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[52]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setPageArray(BigInteger[] pageArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(pageArray, PROPERTY_QNAME[52]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setPageArray(int i, BigInteger page) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[52], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(page);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetPageArray(XmlInteger[] pageArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(pageArray, PROPERTY_QNAME[52]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetPageArray(int i, XmlInteger page) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[52], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(page);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertPage(int i, BigInteger page) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[52], i);
            target.setBigIntegerValue(page);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addPage(BigInteger page) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[52]);
            target.setBigIntegerValue(page);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewPage(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[52], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewPage() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[52]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removePage(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[52], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getHorizList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda417
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getHorizArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda418
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setHorizArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda419
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertHoriz(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda268(this), new CTClientDataImpl$$ExternalSyntheticLambda269(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getHorizArray$106(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getHorizArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[53], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda599
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getHorizArray$106(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getHorizArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[53], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetHorizList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda264
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetHorizArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda265
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetHorizArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda267
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewHoriz(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda268(this), new CTClientDataImpl$$ExternalSyntheticLambda269(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetHorizArray$107(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetHorizArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[53], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda227
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetHorizArray$107(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetHorizArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[53], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfHorizArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[53]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setHorizArray(STTrueFalseBlank.Enum[] horizArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(horizArray, PROPERTY_QNAME[53]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setHorizArray(int i, STTrueFalseBlank.Enum horiz) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[53], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(horiz);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetHorizArray(STTrueFalseBlank[] horizArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(horizArray, PROPERTY_QNAME[53]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetHorizArray(int i, STTrueFalseBlank horiz) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[53], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(horiz);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertHoriz(int i, STTrueFalseBlank.Enum horiz) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[53], i);
            target.setEnumValue(horiz);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addHoriz(STTrueFalseBlank.Enum horiz) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[53]);
            target.setEnumValue(horiz);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewHoriz(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[53], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewHoriz() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[53]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeHoriz(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[53], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getDxList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda517
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda518
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDxArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda519
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDx(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda515(this), new CTClientDataImpl$$ExternalSyntheticLambda516(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getDxArray$108(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getDxArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[54], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda199
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDxArray$108(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getDxArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[54], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlInteger> xgetDxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda512
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda513
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDxArray(((Integer) obj).intValue(), (XmlInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda514
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDx(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda515(this), new CTClientDataImpl$$ExternalSyntheticLambda516(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlInteger[] lambda$xgetDxArray$109(int x$0) {
        return new XmlInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger[] xgetDxArray() {
        return (XmlInteger[]) xgetArray(PROPERTY_QNAME[54], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda177
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDxArray$109(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger xgetDxArray(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[54], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[54]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDxArray(BigInteger[] dxArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dxArray, PROPERTY_QNAME[54]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDxArray(int i, BigInteger dx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[54], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(dx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDxArray(XmlInteger[] dxArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(dxArray, PROPERTY_QNAME[54]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDxArray(int i, XmlInteger dx) {
        synchronized (monitor()) {
            check_orphaned();
            XmlInteger target = (XmlInteger) get_store().find_element_user(PROPERTY_QNAME[54], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(dx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDx(int i, BigInteger dx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[54], i);
            target.setBigIntegerValue(dx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDx(BigInteger dx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[54]);
            target.setBigIntegerValue(dx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger insertNewDx(int i) {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().insert_element_user(PROPERTY_QNAME[54], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlInteger addNewDx() {
        XmlInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlInteger) get_store().add_element_user(PROPERTY_QNAME[54]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[54], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getMapOCXList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda90
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getMapOCXArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda91
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setMapOCXArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda92
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertMapOCX(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda93(this), new CTClientDataImpl$$ExternalSyntheticLambda94(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getMapOCXArray$110(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getMapOCXArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[55], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda448
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getMapOCXArray$110(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getMapOCXArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[55], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetMapOCXList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda323
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetMapOCXArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda324
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetMapOCXArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda325
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewMapOCX(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda93(this), new CTClientDataImpl$$ExternalSyntheticLambda94(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetMapOCXArray$111(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetMapOCXArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[55], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda528
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetMapOCXArray$111(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetMapOCXArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[55], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfMapOCXArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[55]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMapOCXArray(STTrueFalseBlank.Enum[] mapOCXArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(mapOCXArray, PROPERTY_QNAME[55]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setMapOCXArray(int i, STTrueFalseBlank.Enum mapOCX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[55], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(mapOCX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMapOCXArray(STTrueFalseBlank[] mapOCXArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(mapOCXArray, PROPERTY_QNAME[55]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetMapOCXArray(int i, STTrueFalseBlank mapOCX) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[55], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(mapOCX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertMapOCX(int i, STTrueFalseBlank.Enum mapOCX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[55], i);
            target.setEnumValue(mapOCX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addMapOCX(STTrueFalseBlank.Enum mapOCX) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[55]);
            target.setEnumValue(mapOCX);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewMapOCX(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[55], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewMapOCX() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[55]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeMapOCX(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[55], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getCFList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda181
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getCFArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda182
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setCFArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda183
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertCF(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda184(this), new CTClientDataImpl$$ExternalSyntheticLambda185(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getCFArray$112(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getCFArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[56], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda459
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getCFArray$112(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getCFArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[56], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STCF> xgetCFList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda569
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetCFArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda570
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetCFArray(((Integer) obj).intValue(), (STCF) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda571
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewCF(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda184(this), new CTClientDataImpl$$ExternalSyntheticLambda185(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STCF[] lambda$xgetCFArray$113(int x$0) {
        return new STCF[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STCF[] xgetCFArray() {
        return (STCF[]) xgetArray(PROPERTY_QNAME[56], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda619
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetCFArray$113(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STCF xgetCFArray(int i) {
        STCF target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCF) get_store().find_element_user(PROPERTY_QNAME[56], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfCFArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[56]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCFArray(String[] cfArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(cfArray, PROPERTY_QNAME[56]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCFArray(int i, String cf) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[56], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(cf);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCFArray(STCF[] cfArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(cfArray, PROPERTY_QNAME[56]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCFArray(int i, STCF cf) {
        synchronized (monitor()) {
            check_orphaned();
            STCF target = (STCF) get_store().find_element_user(PROPERTY_QNAME[56], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(cf);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertCF(int i, String cf) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[56], i);
            target.setStringValue(cf);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addCF(String cf) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[56]);
            target.setStringValue(cf);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STCF insertNewCF(int i) {
        STCF target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCF) get_store().insert_element_user(PROPERTY_QNAME[56], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STCF addNewCF() {
        STCF target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STCF) get_store().add_element_user(PROPERTY_QNAME[56]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeCF(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[56], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getCameraList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda363
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getCameraArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda364
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setCameraArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda365
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertCamera(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda432(this), new CTClientDataImpl$$ExternalSyntheticLambda443(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getCameraArray$114(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getCameraArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[57], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda146
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getCameraArray$114(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getCameraArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[57], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetCameraList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda399
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetCameraArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda410
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetCameraArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda421
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewCamera(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda432(this), new CTClientDataImpl$$ExternalSyntheticLambda443(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetCameraArray$115(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetCameraArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[57], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda309
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetCameraArray$115(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetCameraArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[57], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfCameraArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[57]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCameraArray(STTrueFalseBlank.Enum[] cameraArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(cameraArray, PROPERTY_QNAME[57]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setCameraArray(int i, STTrueFalseBlank.Enum camera) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[57], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(camera);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCameraArray(STTrueFalseBlank[] cameraArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(cameraArray, PROPERTY_QNAME[57]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetCameraArray(int i, STTrueFalseBlank camera) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[57], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(camera);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertCamera(int i, STTrueFalseBlank.Enum camera) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[57], i);
            target.setEnumValue(camera);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addCamera(STTrueFalseBlank.Enum camera) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[57]);
            target.setEnumValue(camera);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewCamera(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[57], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewCamera() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[57]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeCamera(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[57], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getRecalcAlwaysList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda646
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getRecalcAlwaysArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda647
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setRecalcAlwaysArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda648
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertRecalcAlways(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda650(this), new CTClientDataImpl$$ExternalSyntheticLambda651(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getRecalcAlwaysArray$116(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getRecalcAlwaysArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[58], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda283
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getRecalcAlwaysArray$116(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getRecalcAlwaysArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[58], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetRecalcAlwaysList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda312
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetRecalcAlwaysArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda313
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetRecalcAlwaysArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda314
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewRecalcAlways(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda650(this), new CTClientDataImpl$$ExternalSyntheticLambda651(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetRecalcAlwaysArray$117(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetRecalcAlwaysArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[58], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda82
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetRecalcAlwaysArray$117(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetRecalcAlwaysArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[58], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfRecalcAlwaysArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[58]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setRecalcAlwaysArray(STTrueFalseBlank.Enum[] recalcAlwaysArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(recalcAlwaysArray, PROPERTY_QNAME[58]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setRecalcAlwaysArray(int i, STTrueFalseBlank.Enum recalcAlways) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[58], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(recalcAlways);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetRecalcAlwaysArray(STTrueFalseBlank[] recalcAlwaysArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(recalcAlwaysArray, PROPERTY_QNAME[58]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetRecalcAlwaysArray(int i, STTrueFalseBlank recalcAlways) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[58], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(recalcAlways);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertRecalcAlways(int i, STTrueFalseBlank.Enum recalcAlways) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[58], i);
            target.setEnumValue(recalcAlways);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addRecalcAlways(STTrueFalseBlank.Enum recalcAlways) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[58]);
            target.setEnumValue(recalcAlways);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewRecalcAlways(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[58], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewRecalcAlways() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[58]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeRecalcAlways(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[58], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getAutoScaleList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda405
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getAutoScaleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda406
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setAutoScaleArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda407
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertAutoScale(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda117(this), new CTClientDataImpl$$ExternalSyntheticLambda118(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getAutoScaleArray$118(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getAutoScaleArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[59], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda446
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getAutoScaleArray$118(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getAutoScaleArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[59], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetAutoScaleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda114
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetAutoScaleArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda115
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetAutoScaleArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda116
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewAutoScale(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda117(this), new CTClientDataImpl$$ExternalSyntheticLambda118(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetAutoScaleArray$119(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetAutoScaleArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[59], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda131
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetAutoScaleArray$119(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetAutoScaleArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[59], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfAutoScaleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[59]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoScaleArray(STTrueFalseBlank.Enum[] autoScaleArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoScaleArray, PROPERTY_QNAME[59]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setAutoScaleArray(int i, STTrueFalseBlank.Enum autoScale) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[59], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(autoScale);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoScaleArray(STTrueFalseBlank[] autoScaleArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(autoScaleArray, PROPERTY_QNAME[59]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetAutoScaleArray(int i, STTrueFalseBlank autoScale) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[59], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(autoScale);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertAutoScale(int i, STTrueFalseBlank.Enum autoScale) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[59], i);
            target.setEnumValue(autoScale);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addAutoScale(STTrueFalseBlank.Enum autoScale) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[59]);
            target.setEnumValue(autoScale);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewAutoScale(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[59], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewAutoScale() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[59]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeAutoScale(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[59], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getDDEList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda484
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getDDEArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda485
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setDDEArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda486
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertDDE(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda668(this), new CTClientDataImpl$$ExternalSyntheticLambda669(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getDDEArray$120(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getDDEArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[60], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda198
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getDDEArray$120(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getDDEArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[60], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetDDEList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda665
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetDDEArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda666
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetDDEArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda667
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewDDE(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda668(this), new CTClientDataImpl$$ExternalSyntheticLambda669(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetDDEArray$121(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetDDEArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[60], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda538
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetDDEArray$121(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetDDEArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[60], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfDDEArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[60]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDDEArray(STTrueFalseBlank.Enum[] ddeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ddeArray, PROPERTY_QNAME[60]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setDDEArray(int i, STTrueFalseBlank.Enum dde) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[60], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(dde);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDDEArray(STTrueFalseBlank[] ddeArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(ddeArray, PROPERTY_QNAME[60]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetDDEArray(int i, STTrueFalseBlank dde) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[60], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(dde);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertDDE(int i, STTrueFalseBlank.Enum dde) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[60], i);
            target.setEnumValue(dde);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addDDE(STTrueFalseBlank.Enum dde) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[60]);
            target.setEnumValue(dde);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewDDE(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[60], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewDDE() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[60]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeDDE(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[60], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank.Enum> getUIObjList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda230
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getUIObjArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda231
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setUIObjArray(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda232
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertUIObj(((Integer) obj).intValue(), (STTrueFalseBlank.Enum) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda234(this), new CTClientDataImpl$$ExternalSyntheticLambda235(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank.Enum[] lambda$getUIObjArray$122(int x$0) {
        return new STTrueFalseBlank.Enum[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum[] getUIObjArray() {
        return (STTrueFalseBlank.Enum[]) getEnumArray(PROPERTY_QNAME[61], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda147
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getUIObjArray$122(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank.Enum getUIObjArray(int i) {
        STTrueFalseBlank.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[61], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            r1 = (STTrueFalseBlank.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<STTrueFalseBlank> xgetUIObjList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda398
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetUIObjArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda400
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetUIObjArray(((Integer) obj).intValue(), (STTrueFalseBlank) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda401
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewUIObj(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda234(this), new CTClientDataImpl$$ExternalSyntheticLambda235(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ STTrueFalseBlank[] lambda$xgetUIObjArray$123(int x$0) {
        return new STTrueFalseBlank[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank[] xgetUIObjArray() {
        return (STTrueFalseBlank[]) xgetArray(PROPERTY_QNAME[61], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda368
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetUIObjArray$123(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank xgetUIObjArray(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[61], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfUIObjArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[61]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setUIObjArray(STTrueFalseBlank.Enum[] uiObjArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(uiObjArray, PROPERTY_QNAME[61]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setUIObjArray(int i, STTrueFalseBlank.Enum uiObj) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[61], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setEnumValue(uiObj);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetUIObjArray(STTrueFalseBlank[] uiObjArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(uiObjArray, PROPERTY_QNAME[61]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetUIObjArray(int i, STTrueFalseBlank uiObj) {
        synchronized (monitor()) {
            check_orphaned();
            STTrueFalseBlank target = (STTrueFalseBlank) get_store().find_element_user(PROPERTY_QNAME[61], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(uiObj);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertUIObj(int i, STTrueFalseBlank.Enum uiObj) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[61], i);
            target.setEnumValue(uiObj);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addUIObj(STTrueFalseBlank.Enum uiObj) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[61]);
            target.setEnumValue(uiObj);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank insertNewUIObj(int i) {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().insert_element_user(PROPERTY_QNAME[61], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STTrueFalseBlank addNewUIObj() {
        STTrueFalseBlank target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STTrueFalseBlank) get_store().add_element_user(PROPERTY_QNAME[61]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeUIObj(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[61], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getScriptTextList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda534
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getScriptTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda535
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setScriptTextArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda536
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertScriptText(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda32(this), new CTClientDataImpl$$ExternalSyntheticLambda34(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getScriptTextArray$124(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getScriptTextArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[62], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda483
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getScriptTextArray$124(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getScriptTextArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[62], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetScriptTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda29
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetScriptTextArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda30
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetScriptTextArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda31
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewScriptText(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda32(this), new CTClientDataImpl$$ExternalSyntheticLambda34(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetScriptTextArray$125(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetScriptTextArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[62], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda537
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetScriptTextArray$125(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetScriptTextArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[62], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfScriptTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[62]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptTextArray(String[] scriptTextArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptTextArray, PROPERTY_QNAME[62]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptTextArray(int i, String scriptText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[62], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(scriptText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptTextArray(XmlString[] scriptTextArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptTextArray, PROPERTY_QNAME[62]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptTextArray(int i, XmlString scriptText) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[62], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(scriptText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertScriptText(int i, String scriptText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[62], i);
            target.setStringValue(scriptText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addScriptText(String scriptText) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[62]);
            target.setStringValue(scriptText);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewScriptText(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[62], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewScriptText() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[62]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeScriptText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[62], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getScriptExtendedList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda174
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getScriptExtendedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda175
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setScriptExtendedArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda176
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertScriptExtended(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda159(this), new CTClientDataImpl$$ExternalSyntheticLambda160(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getScriptExtendedArray$126(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getScriptExtendedArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[63], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda520
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getScriptExtendedArray$126(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getScriptExtendedArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[63], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetScriptExtendedList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda156
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetScriptExtendedArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda157
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetScriptExtendedArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda158
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewScriptExtended(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda159(this), new CTClientDataImpl$$ExternalSyntheticLambda160(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetScriptExtendedArray$127(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetScriptExtendedArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[63], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda14
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetScriptExtendedArray$127(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetScriptExtendedArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[63], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfScriptExtendedArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[63]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptExtendedArray(String[] scriptExtendedArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptExtendedArray, PROPERTY_QNAME[63]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptExtendedArray(int i, String scriptExtended) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[63], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(scriptExtended);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptExtendedArray(XmlString[] scriptExtendedArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptExtendedArray, PROPERTY_QNAME[63]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptExtendedArray(int i, XmlString scriptExtended) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[63], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(scriptExtended);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertScriptExtended(int i, String scriptExtended) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[63], i);
            target.setStringValue(scriptExtended);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addScriptExtended(String scriptExtended) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[63]);
            target.setStringValue(scriptExtended);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewScriptExtended(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[63], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewScriptExtended() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[63]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeScriptExtended(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[63], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getScriptLanguageList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda220
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getScriptLanguageArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda223
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setScriptLanguageArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda224
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertScriptLanguage(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda225(this), new CTClientDataImpl$$ExternalSyntheticLambda226(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getScriptLanguageArray$128(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getScriptLanguageArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[64], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda610
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getScriptLanguageArray$128(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getScriptLanguageArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[64], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlNonNegativeInteger> xgetScriptLanguageList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda475
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetScriptLanguageArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda476
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetScriptLanguageArray(((Integer) obj).intValue(), (XmlNonNegativeInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda478
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewScriptLanguage(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda225(this), new CTClientDataImpl$$ExternalSyntheticLambda226(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlNonNegativeInteger[] lambda$xgetScriptLanguageArray$129(int x$0) {
        return new XmlNonNegativeInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger[] xgetScriptLanguageArray() {
        return (XmlNonNegativeInteger[]) xgetArray(PROPERTY_QNAME[64], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda35
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetScriptLanguageArray$129(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger xgetScriptLanguageArray(int i) {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().find_element_user(PROPERTY_QNAME[64], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfScriptLanguageArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[64]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptLanguageArray(BigInteger[] scriptLanguageArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptLanguageArray, PROPERTY_QNAME[64]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptLanguageArray(int i, BigInteger scriptLanguage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[64], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(scriptLanguage);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptLanguageArray(XmlNonNegativeInteger[] scriptLanguageArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptLanguageArray, PROPERTY_QNAME[64]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptLanguageArray(int i, XmlNonNegativeInteger scriptLanguage) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNonNegativeInteger target = (XmlNonNegativeInteger) get_store().find_element_user(PROPERTY_QNAME[64], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(scriptLanguage);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertScriptLanguage(int i, BigInteger scriptLanguage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[64], i);
            target.setBigIntegerValue(scriptLanguage);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addScriptLanguage(BigInteger scriptLanguage) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[64]);
            target.setBigIntegerValue(scriptLanguage);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger insertNewScriptLanguage(int i) {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().insert_element_user(PROPERTY_QNAME[64], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger addNewScriptLanguage() {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().add_element_user(PROPERTY_QNAME[64]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeScriptLanguage(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[64], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<BigInteger> getScriptLocationList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda653
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getScriptLocationArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda654
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setScriptLocationArray(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda655
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertScriptLocation(((Integer) obj).intValue(), (BigInteger) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda656(this), new CTClientDataImpl$$ExternalSyntheticLambda657(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BigInteger[] lambda$getScriptLocationArray$130(int x$0) {
        return new BigInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger[] getScriptLocationArray() {
        return (BigInteger[]) getObjectArray(PROPERTY_QNAME[65], new CTClientDataImpl$$ExternalSyntheticLambda188(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda28
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getScriptLocationArray$130(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public BigInteger getScriptLocationArray(int i) {
        BigInteger bigIntegerValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[65], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            bigIntegerValue = target.getBigIntegerValue();
        }
        return bigIntegerValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlNonNegativeInteger> xgetScriptLocationList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda609
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetScriptLocationArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda611
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetScriptLocationArray(((Integer) obj).intValue(), (XmlNonNegativeInteger) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda612
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewScriptLocation(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda656(this), new CTClientDataImpl$$ExternalSyntheticLambda657(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlNonNegativeInteger[] lambda$xgetScriptLocationArray$131(int x$0) {
        return new XmlNonNegativeInteger[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger[] xgetScriptLocationArray() {
        return (XmlNonNegativeInteger[]) xgetArray(PROPERTY_QNAME[65], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda101
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetScriptLocationArray$131(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger xgetScriptLocationArray(int i) {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().find_element_user(PROPERTY_QNAME[65], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfScriptLocationArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[65]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptLocationArray(BigInteger[] scriptLocationArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptLocationArray, PROPERTY_QNAME[65]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setScriptLocationArray(int i, BigInteger scriptLocation) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[65], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setBigIntegerValue(scriptLocation);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptLocationArray(XmlNonNegativeInteger[] scriptLocationArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(scriptLocationArray, PROPERTY_QNAME[65]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetScriptLocationArray(int i, XmlNonNegativeInteger scriptLocation) {
        synchronized (monitor()) {
            check_orphaned();
            XmlNonNegativeInteger target = (XmlNonNegativeInteger) get_store().find_element_user(PROPERTY_QNAME[65], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(scriptLocation);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertScriptLocation(int i, BigInteger scriptLocation) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[65], i);
            target.setBigIntegerValue(scriptLocation);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addScriptLocation(BigInteger scriptLocation) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[65]);
            target.setBigIntegerValue(scriptLocation);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger insertNewScriptLocation(int i) {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().insert_element_user(PROPERTY_QNAME[65], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlNonNegativeInteger addNewScriptLocation() {
        XmlNonNegativeInteger target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlNonNegativeInteger) get_store().add_element_user(PROPERTY_QNAME[65]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeScriptLocation(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[65], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<String> getFmlaTxbxList() {
        JavaListObject javaListObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListObject = new JavaListObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda546
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.getFmlaTxbxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda547
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.setFmlaTxbxArray(((Integer) obj).intValue(), (String) obj2);
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda548
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.insertFmlaTxbx(((Integer) obj).intValue(), (String) obj2);
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda549(this), new CTClientDataImpl$$ExternalSyntheticLambda550(this));
        }
        return javaListObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String[] lambda$getFmlaTxbxArray$132(int x$0) {
        return new String[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String[] getFmlaTxbxArray() {
        return (String[]) getObjectArray(PROPERTY_QNAME[66], new CTClientDataImpl$$ExternalSyntheticLambda66(), new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda551
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$getFmlaTxbxArray$132(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public String getFmlaTxbxArray(int i) {
        String stringValue;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[66], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            stringValue = target.getStringValue();
        }
        return stringValue;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public List<XmlString> xgetFmlaTxbxList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda629
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.xgetFmlaTxbxArray(((Integer) obj).intValue());
                }
            }, new BiConsumer() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda630
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    CTClientDataImpl.this.xsetFmlaTxbxArray(((Integer) obj).intValue(), (XmlString) obj2);
                }
            }, new Function() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda631
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return CTClientDataImpl.this.insertNewFmlaTxbx(((Integer) obj).intValue());
                }
            }, new CTClientDataImpl$$ExternalSyntheticLambda549(this), new CTClientDataImpl$$ExternalSyntheticLambda550(this));
        }
        return javaListXmlObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlString[] lambda$xgetFmlaTxbxArray$133(int x$0) {
        return new XmlString[x$0];
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString[] xgetFmlaTxbxArray() {
        return (XmlString[]) xgetArray(PROPERTY_QNAME[66], new IntFunction() { // from class: com.microsoft.schemas.office.excel.impl.CTClientDataImpl$$ExternalSyntheticLambda482
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return CTClientDataImpl.lambda$xgetFmlaTxbxArray$133(i);
            }
        });
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString xgetFmlaTxbxArray(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[66], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public int sizeOfFmlaTxbxArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[66]);
        }
        return count_elements;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaTxbxArray(String[] fmlaTxbxArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaTxbxArray, PROPERTY_QNAME[66]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setFmlaTxbxArray(int i, String fmlaTxbx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[66], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(fmlaTxbx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaTxbxArray(XmlString[] fmlaTxbxArray) {
        synchronized (monitor()) {
            check_orphaned();
            arraySetterHelper(fmlaTxbxArray, PROPERTY_QNAME[66]);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetFmlaTxbxArray(int i, XmlString fmlaTxbx) {
        synchronized (monitor()) {
            check_orphaned();
            XmlString target = (XmlString) get_store().find_element_user(PROPERTY_QNAME[66], i);
            if (target == null) {
                throw new IndexOutOfBoundsException();
            }
            target.set(fmlaTxbx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void insertFmlaTxbx(int i, String fmlaTxbx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().insert_element_user(PROPERTY_QNAME[66], i);
            target.setStringValue(fmlaTxbx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void addFmlaTxbx(String fmlaTxbx) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().add_element_user(PROPERTY_QNAME[66]);
            target.setStringValue(fmlaTxbx);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString insertNewFmlaTxbx(int i) {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().insert_element_user(PROPERTY_QNAME[66], i);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public XmlString addNewFmlaTxbx() {
        XmlString target;
        synchronized (monitor()) {
            check_orphaned();
            target = (XmlString) get_store().add_element_user(PROPERTY_QNAME[66]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void removeFmlaTxbx(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[66], i);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STObjectType.Enum getObjectType() {
        STObjectType.Enum r1;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            r1 = target == null ? null : (STObjectType.Enum) target.getEnumValue();
        }
        return r1;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public STObjectType xgetObjectType() {
        STObjectType target;
        synchronized (monitor()) {
            check_orphaned();
            target = (STObjectType) get_store().find_attribute_user(PROPERTY_QNAME[67]);
        }
        return target;
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void setObjectType(STObjectType.Enum objectType) {
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue target = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (SimpleValue) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.setEnumValue(objectType);
        }
    }

    @Override // com.microsoft.schemas.office.excel.CTClientData
    public void xsetObjectType(STObjectType objectType) {
        synchronized (monitor()) {
            check_orphaned();
            STObjectType target = (STObjectType) get_store().find_attribute_user(PROPERTY_QNAME[67]);
            if (target == null) {
                target = (STObjectType) get_store().add_attribute_user(PROPERTY_QNAME[67]);
            }
            target.set(objectType);
        }
    }
}
