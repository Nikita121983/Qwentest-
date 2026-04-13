package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.sl.usermodel.ShapeType;

/* loaded from: classes10.dex */
public class HSSFTextbox extends HSSFSimpleShape {
    public static final short HORIZONTAL_ALIGNMENT_CENTERED = 2;
    public static final short HORIZONTAL_ALIGNMENT_DISTRIBUTED = 7;
    public static final short HORIZONTAL_ALIGNMENT_JUSTIFIED = 4;
    public static final short HORIZONTAL_ALIGNMENT_LEFT = 1;
    public static final short HORIZONTAL_ALIGNMENT_RIGHT = 3;
    public static final short OBJECT_TYPE_TEXT = 6;
    public static final short VERTICAL_ALIGNMENT_BOTTOM = 3;
    public static final short VERTICAL_ALIGNMENT_CENTER = 2;
    public static final short VERTICAL_ALIGNMENT_DISTRIBUTED = 7;
    public static final short VERTICAL_ALIGNMENT_JUSTIFY = 4;
    public static final short VERTICAL_ALIGNMENT_TOP = 1;

    public HSSFTextbox(EscherContainerRecord spContainer, ObjRecord objRecord, TextObjectRecord textObjectRecord) {
        super(spContainer, objRecord, textObjectRecord);
    }

    public HSSFTextbox(HSSFShape parent, HSSFAnchor anchor) {
        super(parent, anchor);
        setHorizontalAlignment((short) 1);
        setVerticalAlignment((short) 1);
        setString(new HSSFRichTextString(""));
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    protected ObjRecord createObjRecord() {
        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord c = new CommonObjectDataSubRecord();
        c.setObjectType((short) 6);
        c.setLocked(true);
        c.setPrintable(true);
        c.setAutofill(true);
        c.setAutoline(true);
        EndSubRecord e = new EndSubRecord();
        obj.addSubRecord(c);
        obj.addSubRecord(e);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public EscherContainerRecord createSpContainer() {
        EscherContainerRecord spContainer = new EscherContainerRecord();
        EscherSpRecord sp = new EscherSpRecord();
        EscherOptRecord opt = new EscherOptRecord();
        EscherClientDataRecord clientData = new EscherClientDataRecord();
        EscherTextboxRecord escherTextbox = new EscherTextboxRecord();
        spContainer.setRecordId(EscherContainerRecord.SP_CONTAINER);
        spContainer.setOptions((short) 15);
        sp.setRecordId(EscherSpRecord.RECORD_ID);
        sp.setOptions((short) ((ShapeType.TEXT_BOX.nativeId << 4) | 2));
        sp.setFlags(2560);
        opt.setRecordId(EscherOptRecord.RECORD_ID);
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTID, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__WRAPTEXT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__ANCHORTEXT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTLEFT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTRIGHT, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTTOP, 0));
        opt.addEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTBOTTOM, 0));
        opt.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEDASHING, 0));
        opt.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
        opt.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEWIDTH, 9525));
        opt.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, HSSFShape.FILL__FILLCOLOR_DEFAULT));
        opt.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, HSSFShape.LINESTYLE__COLOR_DEFAULT));
        opt.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.FILL__NOFILLHITTEST, 65536));
        opt.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        EscherRecord anchor = getAnchor().getEscherAnchor();
        clientData.setRecordId(EscherClientDataRecord.RECORD_ID);
        clientData.setOptions((short) 0);
        escherTextbox.setRecordId(EscherTextboxRecord.RECORD_ID);
        escherTextbox.setOptions((short) 0);
        spContainer.addChildRecord(sp);
        spContainer.addChildRecord(opt);
        spContainer.addChildRecord(anchor);
        spContainer.addChildRecord(clientData);
        spContainer.addChildRecord(escherTextbox);
        return spContainer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public void afterInsert(HSSFPatriarch patriarch) {
        EscherAggregate agg = patriarch.getBoundAggregate();
        agg.associateShapeToObjRecord(getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID), getObjRecord());
        if (getTextObjectRecord() != null) {
            agg.associateShapeToObjRecord(getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID), getTextObjectRecord());
        }
    }

    public int getMarginLeft() {
        EscherSimpleProperty property = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTLEFT);
        if (property == null) {
            return 0;
        }
        return property.getPropertyValue();
    }

    public void setMarginLeft(int marginLeft) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTLEFT, marginLeft));
    }

    public int getMarginRight() {
        EscherSimpleProperty property = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTRIGHT);
        if (property == null) {
            return 0;
        }
        return property.getPropertyValue();
    }

    public void setMarginRight(int marginRight) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTRIGHT, marginRight));
    }

    public int getMarginTop() {
        EscherSimpleProperty property = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTTOP);
        if (property == null) {
            return 0;
        }
        return property.getPropertyValue();
    }

    public void setMarginTop(int marginTop) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTTOP, marginTop));
    }

    public int getMarginBottom() {
        EscherSimpleProperty property = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.TEXT__TEXTBOTTOM);
        if (property == null) {
            return 0;
        }
        return property.getPropertyValue();
    }

    public void setMarginBottom(int marginBottom) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTBOTTOM, marginBottom));
    }

    public short getHorizontalAlignment() {
        return (short) getTextObjectRecord().getHorizontalTextAlignment();
    }

    public void setHorizontalAlignment(short align) {
        getTextObjectRecord().setHorizontalTextAlignment(align);
    }

    public short getVerticalAlignment() {
        return (short) getTextObjectRecord().getVerticalTextAlignment();
    }

    public void setVerticalAlignment(short align) {
        getTextObjectRecord().setVerticalTextAlignment(align);
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFSimpleShape
    public void setShapeType(int shapeType) {
        throw new IllegalStateException("Shape type can not be changed in " + getClass().getSimpleName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public HSSFShape cloneShape() {
        TextObjectRecord txo = getTextObjectRecord() == null ? null : (TextObjectRecord) getTextObjectRecord().cloneViaReserialise();
        EscherContainerRecord spContainer = new EscherContainerRecord();
        byte[] inSp = getEscherContainer().serialize();
        spContainer.fillFields(inSp, 0, new DefaultEscherRecordFactory());
        ObjRecord obj = (ObjRecord) getObjRecord().cloneViaReserialise();
        return new HSSFTextbox(spContainer, obj, txo);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public void afterRemove(HSSFPatriarch patriarch) {
        patriarch.getBoundAggregate().removeShapeToObjRecord(getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID));
        patriarch.getBoundAggregate().removeShapeToObjRecord(getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID));
    }
}
