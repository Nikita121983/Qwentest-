package org.apache.poi.hssf.usermodel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherProperty;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.util.RecordFormatException;

/* loaded from: classes10.dex */
public class HSSFShapeFactory {
    public static void createShapeTree(EscherContainerRecord container, final EscherAggregate agg, HSSFShapeContainer out, final DirectoryNode root) {
        HSSFShape shape;
        if (container.getRecordId() == EscherContainerRecord.SPGR_CONTAINER) {
            ObjRecord obj = null;
            EscherRecord child = container.getChild(0);
            if (!(child instanceof EscherContainerRecord)) {
                throw new IllegalArgumentException("Had unexpected type of child: " + child.getClass());
            }
            EscherClientDataRecord clientData = (EscherClientDataRecord) ((EscherContainerRecord) child).getChildById(EscherClientDataRecord.RECORD_ID);
            if (clientData != null) {
                Record record = agg.getShapeToObjMapping().get(clientData);
                if (!(record instanceof ObjRecord)) {
                    throw new IllegalArgumentException("Had unexpected type of clientData: " + (record == null ? "<null>" : record.getClass()));
                }
                obj = (ObjRecord) record;
            }
            final HSSFShapeGroup group = new HSSFShapeGroup(container, obj);
            List<EscherContainerRecord> children = container.getChildContainers();
            if (children.size() > 1) {
                children.subList(1, children.size()).forEach(new Consumer() { // from class: org.apache.poi.hssf.usermodel.HSSFShapeFactory$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj2) {
                        HSSFShapeFactory.createShapeTree((EscherContainerRecord) obj2, EscherAggregate.this, group, root);
                    }
                });
            }
            out.addShape(group);
            return;
        }
        if (container.getRecordId() == EscherContainerRecord.SP_CONTAINER) {
            Map<EscherRecord, Record> shapeToObj = agg.getShapeToObjMapping();
            ObjRecord objRecord = null;
            TextObjectRecord txtRecord = null;
            Iterator<EscherRecord> it = container.iterator();
            while (it.hasNext()) {
                EscherRecord record2 = it.next();
                switch (EscherRecordTypes.forTypeID(record2.getRecordId())) {
                    case CLIENT_DATA:
                        Record subRecord = shapeToObj.get(record2);
                        if (!(subRecord instanceof ObjRecord)) {
                            throw new RecordFormatException("Did not have a ObjRecord: " + subRecord);
                        }
                        objRecord = (ObjRecord) subRecord;
                        break;
                    case CLIENT_TEXTBOX:
                        Record subRecord2 = shapeToObj.get(record2);
                        if (!(subRecord2 instanceof TextObjectRecord)) {
                            throw new RecordFormatException("Did not have a TextObjRecord: " + subRecord2);
                        }
                        txtRecord = (TextObjectRecord) subRecord2;
                        break;
                }
            }
            if (objRecord == null) {
                throw new RecordFormatException("EscherClientDataRecord can't be found.");
            }
            if (isEmbeddedObject(objRecord)) {
                HSSFObjectData objectData = new HSSFObjectData(container, objRecord, root);
                out.addShape(objectData);
                return;
            }
            CommonObjectDataSubRecord cmo = (CommonObjectDataSubRecord) objRecord.getSubRecords().get(0);
            switch (cmo.getObjectType()) {
                case 1:
                    shape = new HSSFSimpleShape(container, objRecord);
                    break;
                case 2:
                    shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                    break;
                case 6:
                    shape = new HSSFTextbox(container, objRecord, txtRecord);
                    break;
                case 8:
                    shape = new HSSFPicture(container, objRecord);
                    break;
                case 20:
                    shape = new HSSFCombobox(container, objRecord);
                    break;
                case 25:
                    shape = new HSSFComment(container, objRecord, txtRecord, agg.getNoteRecordByObj(objRecord));
                    break;
                case 30:
                    EscherOptRecord optRecord = (EscherOptRecord) container.getChildById(EscherOptRecord.RECORD_ID);
                    if (optRecord == null) {
                        shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                        break;
                    } else {
                        EscherProperty property = optRecord.lookup(EscherPropertyTypes.GEOMETRY__VERTICES);
                        if (property != null) {
                            shape = new HSSFPolygon(container, objRecord, txtRecord);
                            break;
                        } else {
                            shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                            break;
                        }
                    }
                default:
                    shape = new HSSFSimpleShape(container, objRecord, txtRecord);
                    break;
            }
            out.addShape(shape);
        }
    }

    private static boolean isEmbeddedObject(ObjRecord obj) {
        for (SubRecord sub : obj.getSubRecords()) {
            if (sub instanceof EmbeddedObjectRefSubRecord) {
                return true;
            }
        }
        return false;
    }
}
