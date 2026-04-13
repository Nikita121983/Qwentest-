package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTTableStyleCellStyle extends XmlObject {
    public static final DocumentFactory<CTTableStyleCellStyle> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablestylecellstyle1fddtype");
    public static final SchemaType type = Factory.getType();

    CTCell3D addNewCell3D();

    CTFillProperties addNewFill();

    CTStyleMatrixReference addNewFillRef();

    CTTableCellBorderStyle addNewTcBdr();

    CTCell3D getCell3D();

    CTFillProperties getFill();

    CTStyleMatrixReference getFillRef();

    CTTableCellBorderStyle getTcBdr();

    boolean isSetCell3D();

    boolean isSetFill();

    boolean isSetFillRef();

    boolean isSetTcBdr();

    void setCell3D(CTCell3D cTCell3D);

    void setFill(CTFillProperties cTFillProperties);

    void setFillRef(CTStyleMatrixReference cTStyleMatrixReference);

    void setTcBdr(CTTableCellBorderStyle cTTableCellBorderStyle);

    void unsetCell3D();

    void unsetFill();

    void unsetFillRef();

    void unsetTcBdr();
}
