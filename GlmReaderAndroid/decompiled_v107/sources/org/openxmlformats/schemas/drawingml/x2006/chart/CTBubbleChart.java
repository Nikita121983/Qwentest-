package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTBubbleChart extends XmlObject {
    public static final DocumentFactory<CTBubbleChart> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbubblechart3ff4type");
    public static final SchemaType type = Factory.getType();

    CTUnsignedInt addNewAxId();

    CTBoolean addNewBubble3D();

    CTBubbleScale addNewBubbleScale();

    CTDLbls addNewDLbls();

    CTExtensionList addNewExtLst();

    CTBubbleSer addNewSer();

    CTBoolean addNewShowNegBubbles();

    CTSizeRepresents addNewSizeRepresents();

    CTBoolean addNewVaryColors();

    CTUnsignedInt getAxIdArray(int i);

    CTUnsignedInt[] getAxIdArray();

    List<CTUnsignedInt> getAxIdList();

    CTBoolean getBubble3D();

    CTBubbleScale getBubbleScale();

    CTDLbls getDLbls();

    CTExtensionList getExtLst();

    CTBubbleSer getSerArray(int i);

    CTBubbleSer[] getSerArray();

    List<CTBubbleSer> getSerList();

    CTBoolean getShowNegBubbles();

    CTSizeRepresents getSizeRepresents();

    CTBoolean getVaryColors();

    CTUnsignedInt insertNewAxId(int i);

    CTBubbleSer insertNewSer(int i);

    boolean isSetBubble3D();

    boolean isSetBubbleScale();

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetShowNegBubbles();

    boolean isSetSizeRepresents();

    boolean isSetVaryColors();

    void removeAxId(int i);

    void removeSer(int i);

    void setAxIdArray(int i, CTUnsignedInt cTUnsignedInt);

    void setAxIdArray(CTUnsignedInt[] cTUnsignedIntArr);

    void setBubble3D(CTBoolean cTBoolean);

    void setBubbleScale(CTBubbleScale cTBubbleScale);

    void setDLbls(CTDLbls cTDLbls);

    void setExtLst(CTExtensionList cTExtensionList);

    void setSerArray(int i, CTBubbleSer cTBubbleSer);

    void setSerArray(CTBubbleSer[] cTBubbleSerArr);

    void setShowNegBubbles(CTBoolean cTBoolean);

    void setSizeRepresents(CTSizeRepresents cTSizeRepresents);

    void setVaryColors(CTBoolean cTBoolean);

    int sizeOfAxIdArray();

    int sizeOfSerArray();

    void unsetBubble3D();

    void unsetBubbleScale();

    void unsetDLbls();

    void unsetExtLst();

    void unsetShowNegBubbles();

    void unsetSizeRepresents();

    void unsetVaryColors();
}
