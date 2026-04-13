package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface CTEffectList extends XmlObject {
    public static final DocumentFactory<CTEffectList> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cteffectlist6featype");
    public static final SchemaType type = Factory.getType();

    CTBlurEffect addNewBlur();

    CTFillOverlayEffect addNewFillOverlay();

    CTGlowEffect addNewGlow();

    CTInnerShadowEffect addNewInnerShdw();

    CTOuterShadowEffect addNewOuterShdw();

    CTPresetShadowEffect addNewPrstShdw();

    CTReflectionEffect addNewReflection();

    CTSoftEdgesEffect addNewSoftEdge();

    CTBlurEffect getBlur();

    CTFillOverlayEffect getFillOverlay();

    CTGlowEffect getGlow();

    CTInnerShadowEffect getInnerShdw();

    CTOuterShadowEffect getOuterShdw();

    CTPresetShadowEffect getPrstShdw();

    CTReflectionEffect getReflection();

    CTSoftEdgesEffect getSoftEdge();

    boolean isSetBlur();

    boolean isSetFillOverlay();

    boolean isSetGlow();

    boolean isSetInnerShdw();

    boolean isSetOuterShdw();

    boolean isSetPrstShdw();

    boolean isSetReflection();

    boolean isSetSoftEdge();

    void setBlur(CTBlurEffect cTBlurEffect);

    void setFillOverlay(CTFillOverlayEffect cTFillOverlayEffect);

    void setGlow(CTGlowEffect cTGlowEffect);

    void setInnerShdw(CTInnerShadowEffect cTInnerShadowEffect);

    void setOuterShdw(CTOuterShadowEffect cTOuterShadowEffect);

    void setPrstShdw(CTPresetShadowEffect cTPresetShadowEffect);

    void setReflection(CTReflectionEffect cTReflectionEffect);

    void setSoftEdge(CTSoftEdgesEffect cTSoftEdgesEffect);

    void unsetBlur();

    void unsetFillOverlay();

    void unsetGlow();

    void unsetInnerShdw();

    void unsetOuterShdw();

    void unsetPrstShdw();

    void unsetReflection();

    void unsetSoftEdge();
}
