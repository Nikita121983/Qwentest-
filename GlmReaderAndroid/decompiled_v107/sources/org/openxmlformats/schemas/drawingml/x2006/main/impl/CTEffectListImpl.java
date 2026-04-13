package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;

/* loaded from: classes11.dex */
public class CTEffectListImpl extends XmlComplexContentImpl implements CTEffectList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "blur"), new QName(XSSFRelation.NS_DRAWINGML, "fillOverlay"), new QName(XSSFRelation.NS_DRAWINGML, "glow"), new QName(XSSFRelation.NS_DRAWINGML, "innerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "outerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "prstShdw"), new QName(XSSFRelation.NS_DRAWINGML, "reflection"), new QName(XSSFRelation.NS_DRAWINGML, "softEdge")};
    private static final long serialVersionUID = 1;

    public CTEffectListImpl(SchemaType sType) {
        super(sType);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTBlurEffect getBlur() {
        CTBlurEffect cTBlurEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTBlurEffect target = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            cTBlurEffect = target == null ? null : target;
        }
        return cTBlurEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetBlur() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[0]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setBlur(CTBlurEffect blur) {
        generatedSetterHelperImpl(blur, PROPERTY_QNAME[0], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTBlurEffect addNewBlur() {
        CTBlurEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetBlur() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTFillOverlayEffect getFillOverlay() {
        CTFillOverlayEffect cTFillOverlayEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTFillOverlayEffect target = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            cTFillOverlayEffect = target == null ? null : target;
        }
        return cTFillOverlayEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetFillOverlay() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setFillOverlay(CTFillOverlayEffect fillOverlay) {
        generatedSetterHelperImpl(fillOverlay, PROPERTY_QNAME[1], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTFillOverlayEffect addNewFillOverlay() {
        CTFillOverlayEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetFillOverlay() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTGlowEffect getGlow() {
        CTGlowEffect cTGlowEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTGlowEffect target = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            cTGlowEffect = target == null ? null : target;
        }
        return cTGlowEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetGlow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setGlow(CTGlowEffect glow) {
        generatedSetterHelperImpl(glow, PROPERTY_QNAME[2], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTGlowEffect addNewGlow() {
        CTGlowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetGlow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTInnerShadowEffect getInnerShdw() {
        CTInnerShadowEffect cTInnerShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTInnerShadowEffect target = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            cTInnerShadowEffect = target == null ? null : target;
        }
        return cTInnerShadowEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetInnerShdw() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setInnerShdw(CTInnerShadowEffect innerShdw) {
        generatedSetterHelperImpl(innerShdw, PROPERTY_QNAME[3], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTInnerShadowEffect addNewInnerShdw() {
        CTInnerShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetInnerShdw() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTOuterShadowEffect getOuterShdw() {
        CTOuterShadowEffect cTOuterShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTOuterShadowEffect target = (CTOuterShadowEffect) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            cTOuterShadowEffect = target == null ? null : target;
        }
        return cTOuterShadowEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetOuterShdw() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setOuterShdw(CTOuterShadowEffect outerShdw) {
        generatedSetterHelperImpl(outerShdw, PROPERTY_QNAME[4], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTOuterShadowEffect addNewOuterShdw() {
        CTOuterShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = (CTOuterShadowEffect) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetOuterShdw() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTPresetShadowEffect getPrstShdw() {
        CTPresetShadowEffect cTPresetShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTPresetShadowEffect target = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            cTPresetShadowEffect = target == null ? null : target;
        }
        return cTPresetShadowEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetPrstShdw() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setPrstShdw(CTPresetShadowEffect prstShdw) {
        generatedSetterHelperImpl(prstShdw, PROPERTY_QNAME[5], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTPresetShadowEffect addNewPrstShdw() {
        CTPresetShadowEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetPrstShdw() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTReflectionEffect getReflection() {
        CTReflectionEffect cTReflectionEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTReflectionEffect target = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            cTReflectionEffect = target == null ? null : target;
        }
        return cTReflectionEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetReflection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setReflection(CTReflectionEffect reflection) {
        generatedSetterHelperImpl(reflection, PROPERTY_QNAME[6], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTReflectionEffect addNewReflection() {
        CTReflectionEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetReflection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTSoftEdgesEffect getSoftEdge() {
        CTSoftEdgesEffect cTSoftEdgesEffect;
        synchronized (monitor()) {
            check_orphaned();
            CTSoftEdgesEffect target = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            cTSoftEdgesEffect = target == null ? null : target;
        }
        return cTSoftEdgesEffect;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public boolean isSetSoftEdge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void setSoftEdge(CTSoftEdgesEffect softEdge) {
        generatedSetterHelperImpl(softEdge, PROPERTY_QNAME[7], 0, (short) 1);
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public CTSoftEdgesEffect addNewSoftEdge() {
        CTSoftEdgesEffect target;
        synchronized (monitor()) {
            check_orphaned();
            target = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return target;
    }

    @Override // org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList
    public void unsetSoftEdge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }
}
