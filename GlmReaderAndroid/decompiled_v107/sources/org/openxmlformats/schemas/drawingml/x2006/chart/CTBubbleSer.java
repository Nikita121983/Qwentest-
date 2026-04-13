package org.openxmlformats.schemas.drawingml.x2006.chart;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

/* loaded from: classes11.dex */
public interface CTBubbleSer extends XmlObject {
    public static final DocumentFactory<CTBubbleSer> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbubblesere172type");
    public static final SchemaType type = Factory.getType();

    CTBoolean addNewBubble3D();

    CTNumDataSource addNewBubbleSize();

    CTDLbls addNewDLbls();

    CTDPt addNewDPt();

    CTErrBars addNewErrBars();

    CTExtensionList addNewExtLst();

    CTUnsignedInt addNewIdx();

    CTBoolean addNewInvertIfNegative();

    CTUnsignedInt addNewOrder();

    CTShapeProperties addNewSpPr();

    CTTrendline addNewTrendline();

    CTSerTx addNewTx();

    CTAxDataSource addNewXVal();

    CTNumDataSource addNewYVal();

    CTBoolean getBubble3D();

    CTNumDataSource getBubbleSize();

    CTDLbls getDLbls();

    CTDPt getDPtArray(int i);

    CTDPt[] getDPtArray();

    List<CTDPt> getDPtList();

    CTErrBars getErrBarsArray(int i);

    CTErrBars[] getErrBarsArray();

    List<CTErrBars> getErrBarsList();

    CTExtensionList getExtLst();

    CTUnsignedInt getIdx();

    CTBoolean getInvertIfNegative();

    CTUnsignedInt getOrder();

    CTShapeProperties getSpPr();

    CTTrendline getTrendlineArray(int i);

    CTTrendline[] getTrendlineArray();

    List<CTTrendline> getTrendlineList();

    CTSerTx getTx();

    CTAxDataSource getXVal();

    CTNumDataSource getYVal();

    CTDPt insertNewDPt(int i);

    CTErrBars insertNewErrBars(int i);

    CTTrendline insertNewTrendline(int i);

    boolean isSetBubble3D();

    boolean isSetBubbleSize();

    boolean isSetDLbls();

    boolean isSetExtLst();

    boolean isSetInvertIfNegative();

    boolean isSetSpPr();

    boolean isSetTx();

    boolean isSetXVal();

    boolean isSetYVal();

    void removeDPt(int i);

    void removeErrBars(int i);

    void removeTrendline(int i);

    void setBubble3D(CTBoolean cTBoolean);

    void setBubbleSize(CTNumDataSource cTNumDataSource);

    void setDLbls(CTDLbls cTDLbls);

    void setDPtArray(int i, CTDPt cTDPt);

    void setDPtArray(CTDPt[] cTDPtArr);

    void setErrBarsArray(int i, CTErrBars cTErrBars);

    void setErrBarsArray(CTErrBars[] cTErrBarsArr);

    void setExtLst(CTExtensionList cTExtensionList);

    void setIdx(CTUnsignedInt cTUnsignedInt);

    void setInvertIfNegative(CTBoolean cTBoolean);

    void setOrder(CTUnsignedInt cTUnsignedInt);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setTrendlineArray(int i, CTTrendline cTTrendline);

    void setTrendlineArray(CTTrendline[] cTTrendlineArr);

    void setTx(CTSerTx cTSerTx);

    void setXVal(CTAxDataSource cTAxDataSource);

    void setYVal(CTNumDataSource cTNumDataSource);

    int sizeOfDPtArray();

    int sizeOfErrBarsArray();

    int sizeOfTrendlineArray();

    void unsetBubble3D();

    void unsetBubbleSize();

    void unsetDLbls();

    void unsetExtLst();

    void unsetInvertIfNegative();

    void unsetSpPr();

    void unsetTx();

    void unsetXVal();

    void unsetYVal();
}
