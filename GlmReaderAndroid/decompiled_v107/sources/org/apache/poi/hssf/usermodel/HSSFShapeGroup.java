package org.apache.poi.hssf.usermodel;

import androidx.core.view.InputDeviceCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.GroupMarkerSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.ss.util.IEEEDouble;

/* loaded from: classes10.dex */
public class HSSFShapeGroup extends HSSFShape implements HSSFShapeContainer {
    private final EscherSpgrRecord _spgrRecord;
    private final List<HSSFShape> shapes;

    public HSSFShapeGroup(EscherContainerRecord spgrContainer, ObjRecord objRecord) {
        super(spgrContainer, objRecord);
        this.shapes = new ArrayList();
        EscherContainerRecord spContainer = spgrContainer.getChildContainers().get(0);
        EscherRecord child = spContainer.getChild(0);
        if (!(child instanceof EscherSpgrRecord)) {
            throw new IllegalArgumentException("Had unexpected type of child at index 0: " + child.getClass());
        }
        this._spgrRecord = (EscherSpgrRecord) child;
        Iterator<EscherRecord> it = spContainer.iterator();
        while (it.hasNext()) {
            EscherRecord ch = it.next();
            switch (EscherRecordTypes.forTypeID(ch.getRecordId())) {
                case CLIENT_ANCHOR:
                    this.anchor = new HSSFClientAnchor((EscherClientAnchorRecord) ch);
                    break;
                case CHILD_ANCHOR:
                    this.anchor = new HSSFChildAnchor((EscherChildAnchorRecord) ch);
                    break;
            }
        }
    }

    public HSSFShapeGroup(HSSFShape parent, HSSFAnchor anchor) {
        super(parent, anchor);
        this.shapes = new ArrayList();
        this._spgrRecord = (EscherSpgrRecord) ((EscherContainerRecord) getEscherContainer().getChild(0)).getChildById(EscherSpgrRecord.RECORD_ID);
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    protected EscherContainerRecord createSpContainer() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        EscherContainerRecord spContainer = new EscherContainerRecord();
        EscherSpgrRecord spgr = new EscherSpgrRecord();
        EscherSpRecord sp = new EscherSpRecord();
        EscherOptRecord opt = new EscherOptRecord();
        EscherClientDataRecord clientData = new EscherClientDataRecord();
        escherContainerRecord.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        escherContainerRecord.setOptions((short) 15);
        spContainer.setRecordId(EscherContainerRecord.SP_CONTAINER);
        spContainer.setOptions((short) 15);
        spgr.setRecordId(EscherSpgrRecord.RECORD_ID);
        spgr.setOptions((short) 1);
        spgr.setRectX1(0);
        spgr.setRectY1(0);
        spgr.setRectX2(IEEEDouble.EXPONENT_BIAS);
        spgr.setRectY2(255);
        sp.setRecordId(EscherSpRecord.RECORD_ID);
        sp.setOptions((short) 2);
        if (getAnchor() instanceof HSSFClientAnchor) {
            sp.setFlags(InputDeviceCompat.SOURCE_DPAD);
        } else {
            sp.setFlags(515);
        }
        opt.setRecordId(EscherOptRecord.RECORD_ID);
        opt.setOptions((short) 35);
        opt.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.PROTECTION__LOCKAGAINSTGROUPING, 262148));
        opt.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        EscherRecord anchor = getAnchor().getEscherAnchor();
        clientData.setRecordId(EscherClientDataRecord.RECORD_ID);
        clientData.setOptions((short) 0);
        escherContainerRecord.addChildRecord(spContainer);
        spContainer.addChildRecord(spgr);
        spContainer.addChildRecord(sp);
        spContainer.addChildRecord(opt);
        spContainer.addChildRecord(anchor);
        spContainer.addChildRecord(clientData);
        return escherContainerRecord;
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    protected ObjRecord createObjRecord() {
        ObjRecord obj = new ObjRecord();
        CommonObjectDataSubRecord cmo = new CommonObjectDataSubRecord();
        cmo.setObjectType((short) 0);
        cmo.setLocked(true);
        cmo.setPrintable(true);
        cmo.setAutofill(true);
        cmo.setAutoline(true);
        GroupMarkerSubRecord gmo = new GroupMarkerSubRecord();
        EndSubRecord end = new EndSubRecord();
        obj.addSubRecord(cmo);
        obj.addSubRecord(gmo);
        obj.addSubRecord(end);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    public void afterRemove(HSSFPatriarch patriarch) {
        patriarch.getBoundAggregate().removeShapeToObjRecord(getEscherContainer().getChildContainers().get(0).getChildById(EscherClientDataRecord.RECORD_ID));
        EscherContainerRecord cont = getEscherContainer();
        HSSFPatriarch pat = getPatriarch();
        for (HSSFShape shape : this.shapes) {
            if (cont.removeChildRecord(shape.getEscherContainer())) {
                shape.afterRemove(pat);
            }
        }
        this.shapes.clear();
    }

    private void onCreate(HSSFShape shape) {
        EscherSpRecord sp;
        if (getPatriarch() != null) {
            EscherContainerRecord spContainer = shape.getEscherContainer();
            int shapeId = getPatriarch().newShapeId();
            shape.setShapeId(shapeId);
            getEscherContainer().addChildRecord(spContainer);
            shape.afterInsert(getPatriarch());
            if (shape instanceof HSSFShapeGroup) {
                sp = (EscherSpRecord) shape.getEscherContainer().getChildContainers().get(0).getChildById(EscherSpRecord.RECORD_ID);
            } else {
                sp = (EscherSpRecord) shape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
            }
            sp.setFlags(sp.getFlags() | 2);
        }
    }

    public HSSFShapeGroup createGroup(HSSFChildAnchor anchor) {
        HSSFShapeGroup group = new HSSFShapeGroup(this, anchor);
        group.setParent(this);
        group.setAnchor(anchor);
        this.shapes.add(group);
        onCreate(group);
        return group;
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public void addShape(HSSFShape shape) {
        shape.setPatriarch(getPatriarch());
        shape.setParent(this);
        this.shapes.add(shape);
    }

    public HSSFSimpleShape createShape(HSSFChildAnchor anchor) {
        HSSFSimpleShape shape = new HSSFSimpleShape(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        this.shapes.add(shape);
        onCreate(shape);
        EscherSpRecord sp = (EscherSpRecord) shape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (shape.getAnchor().isHorizontallyFlipped()) {
            sp.setFlags(sp.getFlags() | 64);
        }
        if (shape.getAnchor().isVerticallyFlipped()) {
            sp.setFlags(sp.getFlags() | 128);
        }
        return shape;
    }

    public HSSFTextbox createTextbox(HSSFChildAnchor anchor) {
        HSSFTextbox shape = new HSSFTextbox(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        this.shapes.add(shape);
        onCreate(shape);
        return shape;
    }

    public HSSFPolygon createPolygon(HSSFChildAnchor anchor) {
        HSSFPolygon shape = new HSSFPolygon(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        this.shapes.add(shape);
        onCreate(shape);
        return shape;
    }

    public HSSFPicture createPicture(HSSFChildAnchor anchor, int pictureIndex) {
        HSSFPicture shape = new HSSFPicture(this, anchor);
        shape.setParent(this);
        shape.setAnchor(anchor);
        shape.setPictureIndex(pictureIndex);
        this.shapes.add(shape);
        onCreate(shape);
        EscherSpRecord sp = (EscherSpRecord) shape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (shape.getAnchor().isHorizontallyFlipped()) {
            sp.setFlags(sp.getFlags() | 64);
        }
        if (shape.getAnchor().isVerticallyFlipped()) {
            sp.setFlags(sp.getFlags() | 128);
        }
        return shape;
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public List<HSSFShape> getChildren() {
        return Collections.unmodifiableList(this.shapes);
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public void setCoordinates(int x1, int y1, int x2, int y2) {
        this._spgrRecord.setRectX1(x1);
        this._spgrRecord.setRectX2(x2);
        this._spgrRecord.setRectY1(y1);
        this._spgrRecord.setRectY2(y2);
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public void clear() {
        ArrayList<HSSFShape> copy = new ArrayList<>(this.shapes);
        Iterator<HSSFShape> it = copy.iterator();
        while (it.hasNext()) {
            HSSFShape shape = it.next();
            removeShape(shape);
        }
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public int getX1() {
        return this._spgrRecord.getRectX1();
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public int getY1() {
        return this._spgrRecord.getRectY1();
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public int getX2() {
        return this._spgrRecord.getRectX2();
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public int getY2() {
        return this._spgrRecord.getRectY2();
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    public int countOfAllChildren() {
        int count = this.shapes.size();
        for (HSSFShape shape : this.shapes) {
            count += shape.countOfAllChildren();
        }
        return count;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    public void afterInsert(HSSFPatriarch patriarch) {
        EscherAggregate agg = patriarch.getBoundAggregate();
        EscherContainerRecord containerRecord = (EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        agg.associateShapeToObjRecord(containerRecord.getChildById(EscherClientDataRecord.RECORD_ID), getObjRecord());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    public void setShapeId(int shapeId) {
        EscherContainerRecord containerRecord = (EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        EscherSpRecord spRecord = (EscherSpRecord) containerRecord.getChildById(EscherSpRecord.RECORD_ID);
        spRecord.setShapeId(shapeId);
        CommonObjectDataSubRecord cod = (CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0);
        cod.setObjectId((short) (shapeId % 1024));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    public int getShapeId() {
        EscherContainerRecord containerRecord = (EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        return ((EscherSpRecord) containerRecord.getChildById(EscherSpRecord.RECORD_ID)).getShapeId();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.hssf.usermodel.HSSFShape
    public HSSFShape cloneShape() {
        throw new IllegalStateException("Use method cloneShape(HSSFPatriarch patriarch)");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HSSFShape cloneShape(HSSFPatriarch patriarch) {
        HSSFShape newShape;
        EscherContainerRecord spgrContainer = new EscherContainerRecord();
        spgrContainer.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        spgrContainer.setOptions((short) 15);
        EscherContainerRecord spContainer = new EscherContainerRecord();
        EscherContainerRecord cont = (EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER);
        byte[] inSp = cont.serialize();
        spContainer.fillFields(inSp, 0, new DefaultEscherRecordFactory());
        spgrContainer.addChildRecord(spContainer);
        ObjRecord obj = null;
        if (getObjRecord() != null) {
            obj = (ObjRecord) getObjRecord().cloneViaReserialise();
        }
        HSSFShapeGroup group = new HSSFShapeGroup(spgrContainer, obj);
        group.setPatriarch(patriarch);
        for (HSSFShape shape : getChildren()) {
            if (shape instanceof HSSFShapeGroup) {
                newShape = ((HSSFShapeGroup) shape).cloneShape(patriarch);
            } else {
                newShape = shape.cloneShape();
            }
            group.addShape(newShape);
            group.onCreate(newShape);
        }
        return group;
    }

    @Override // org.apache.poi.hssf.usermodel.HSSFShapeContainer
    public boolean removeShape(HSSFShape shape) {
        boolean isRemoved = getEscherContainer().removeChildRecord(shape.getEscherContainer());
        if (isRemoved) {
            shape.afterRemove(getPatriarch());
            this.shapes.remove(shape);
        }
        return isRemoved;
    }

    @Override // java.lang.Iterable
    public Iterator<HSSFShape> iterator() {
        return this.shapes.iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<HSSFShape> spliterator() {
        return this.shapes.spliterator();
    }
}
