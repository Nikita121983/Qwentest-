package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes12.dex */
public interface CTCustomSheetViews extends XmlObject {
    public static final DocumentFactory<CTCustomSheetViews> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcustomsheetviewsc069type");
    public static final SchemaType type = Factory.getType();

    CTCustomSheetView addNewCustomSheetView();

    CTCustomSheetView getCustomSheetViewArray(int i);

    CTCustomSheetView[] getCustomSheetViewArray();

    List<CTCustomSheetView> getCustomSheetViewList();

    CTCustomSheetView insertNewCustomSheetView(int i);

    void removeCustomSheetView(int i);

    void setCustomSheetViewArray(int i, CTCustomSheetView cTCustomSheetView);

    void setCustomSheetViewArray(CTCustomSheetView[] cTCustomSheetViewArr);

    int sizeOfCustomSheetViewArray();
}
