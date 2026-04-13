package org.apache.poi.hssf.usermodel;

import java.util.Objects;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NoteStructureSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.ss.usermodel.ChildAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;

/* loaded from: classes10.dex */
public class HSSFComment extends HSSFTextbox implements Comment {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FILL_TYPE_PICTURE = 3;
    private static final int FILL_TYPE_SOLID = 0;
    private static final int GROUP_SHAPE_HIDDEN_MASK = 16777218;
    private static final int GROUP_SHAPE_NOT_HIDDEN_MASK = -16777219;
    private static final int GROUP_SHAPE_PROPERTY_DEFAULT_VALUE = 655362;
    private final NoteRecord _note;

    @Override // org.apache.poi.ss.usermodel.Comment
    public /* bridge */ /* synthetic */ RichTextString getString() {
        return super.getString();
    }

    public HSSFComment(EscherContainerRecord spContainer, ObjRecord objRecord, TextObjectRecord textObjectRecord, NoteRecord note) {
        super(spContainer, objRecord, textObjectRecord);
        this._note = note;
    }

    public HSSFComment(HSSFShape parent, HSSFAnchor anchor) {
        this(parent, anchor, createNoteRecord());
    }

    private HSSFComment(HSSFShape parent, HSSFAnchor anchor, NoteRecord note) {
        super(parent, anchor);
        this._note = note;
        setFillColor(134217808);
        setVisible(false);
        setAuthor("");
        CommonObjectDataSubRecord cod = (CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0);
        cod.setObjectType((short) 25);
    }

    protected HSSFComment(NoteRecord note, TextObjectRecord txo) {
        this(null, new HSSFClientAnchor(), note);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.hssf.usermodel.HSSFTextbox, org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public void afterInsert(HSSFPatriarch patriarch) {
        super.afterInsert(patriarch);
        patriarch.getBoundAggregate().addTailRecord(getNoteRecord());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.usermodel.HSSFTextbox, org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public EscherContainerRecord createSpContainer() {
        EscherContainerRecord spContainer = super.createSpContainer();
        EscherOptRecord opt = (EscherOptRecord) spContainer.getChildById(EscherOptRecord.RECORD_ID);
        if (opt == null) {
            throw new AssertionError();
        }
        opt.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTLEFT);
        opt.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTRIGHT);
        opt.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTTOP);
        opt.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTBOTTOM);
        opt.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, false, false, GROUP_SHAPE_PROPERTY_DEFAULT_VALUE));
        return spContainer;
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFTextbox, org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    protected ObjRecord createObjRecord() {
        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord c = new CommonObjectDataSubRecord();
        c.setObjectType((short) 202);
        c.setLocked(true);
        c.setPrintable(true);
        c.setAutofill(false);
        c.setAutoline(true);
        NoteStructureSubRecord u = new NoteStructureSubRecord();
        EndSubRecord e = new EndSubRecord();
        obj.addSubRecord(c);
        obj.addSubRecord(u);
        obj.addSubRecord(e);
        return obj;
    }

    private static NoteRecord createNoteRecord() {
        NoteRecord note = new NoteRecord();
        note.setFlags((short) 0);
        note.setAuthor("");
        return note;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    public void setShapeId(int shapeId) {
        if (shapeId > 65535) {
            throw new IllegalArgumentException("Cannot add more than 65535 shapes");
        }
        super.setShapeId(shapeId);
        CommonObjectDataSubRecord cod = (CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0);
        cod.setObjectId(shapeId);
        this._note.setShapeId(shapeId);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setVisible(boolean visible) {
        this._note.setFlags(visible ? (short) 2 : (short) 0);
        setHidden(!visible);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public boolean isVisible() {
        return this._note.getFlags() == 2;
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public CellAddress getAddress() {
        return new CellAddress(getRow(), getColumn());
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setAddress(CellAddress address) {
        setRow(address.getRow());
        setColumn(address.getColumn());
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setAddress(int row, int col) {
        setRow(row);
        setColumn(col);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public int getRow() {
        return this._note.getRow();
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setRow(int row) {
        this._note.setRow(row);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public int getColumn() {
        return this._note.getColumn();
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setColumn(int col) {
        this._note.setColumn(col);
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public String getAuthor() {
        return this._note.getAuthor();
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public void setAuthor(String author) {
        if (this._note != null) {
            this._note.setAuthor(author);
        }
    }

    protected NoteRecord getNoteRecord() {
        return this._note;
    }

    public boolean hasPosition() {
        return this._note != null && getColumn() >= 0 && getRow() >= 0;
    }

    @Override // org.apache.poi.ss.usermodel.Comment
    public ClientAnchor getClientAnchor() {
        ChildAnchor anchor = super.getAnchor();
        if (anchor instanceof ClientAnchor) {
            return (ClientAnchor) anchor;
        }
        throw new IllegalStateException("Anchor can not be changed in " + ClientAnchor.class.getSimpleName());
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFTextbox, org.apache.poi.hssf.usermodel.HSSFSimpleShape
    public void setShapeType(int shapeType) {
        throw new IllegalStateException("Shape type can not be changed in " + getClass().getSimpleName());
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFTextbox, org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public void afterRemove(HSSFPatriarch patriarch) {
        super.afterRemove(patriarch);
        patriarch.getBoundAggregate().removeTailRecord(getNoteRecord());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.usermodel.HSSFTextbox, org.apache.poi.hssf.usermodel.HSSFSimpleShape, org.apache.poi.hssf.usermodel.HSSFShape
    public HSSFShape cloneShape() {
        TextObjectRecord txo = (TextObjectRecord) getTextObjectRecord().cloneViaReserialise();
        EscherContainerRecord spContainer = new EscherContainerRecord();
        byte[] inSp = getEscherContainer().serialize();
        spContainer.fillFields(inSp, 0, new DefaultEscherRecordFactory());
        ObjRecord obj = (ObjRecord) getObjRecord().cloneViaReserialise();
        NoteRecord note = (NoteRecord) getNoteRecord().cloneViaReserialise();
        return new HSSFComment(spContainer, obj, txo, note);
    }

    public void setBackgroundImage(int pictureIndex) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.FILL__PATTERNTEXTURE, false, true, pictureIndex));
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.FILL__FILLTYPE, false, false, 3));
        EscherBSERecord bse = getPatriarch().getSheet().getWorkbook().getWorkbook().getBSERecord(pictureIndex);
        bse.setRef(bse.getRef() + 1);
    }

    public void resetBackgroundImage() {
        EscherSimpleProperty property = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.FILL__PATTERNTEXTURE);
        if (property != null) {
            EscherBSERecord bse = getPatriarch().getSheet().getWorkbook().getWorkbook().getBSERecord(property.getPropertyValue());
            bse.setRef(bse.getRef() - 1);
            getOptRecord().removeEscherProperty(EscherPropertyTypes.FILL__PATTERNTEXTURE);
        }
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.FILL__FILLTYPE, false, false, 0));
    }

    public int getBackgroundImageId() {
        EscherSimpleProperty property = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.FILL__PATTERNTEXTURE);
        if (property == null) {
            return 0;
        }
        return property.getPropertyValue();
    }

    private void setHidden(boolean value) {
        EscherSimpleProperty property = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.GROUPSHAPE__FLAGS);
        if (value) {
            setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, false, false, property.getPropertyValue() | GROUP_SHAPE_HIDDEN_MASK));
        } else {
            setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, false, false, property.getPropertyValue() & GROUP_SHAPE_NOT_HIDDEN_MASK));
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HSSFComment)) {
            return false;
        }
        HSSFComment other = (HSSFComment) obj;
        return getNoteRecord().equals(other.getNoteRecord());
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(getRow()), Integer.valueOf(getColumn()));
    }
}
