package org.apache.poi.openxml4j.opc;

/* loaded from: classes10.dex */
public class OPCComplianceFlags {
    protected boolean ENFORCE_M4_2_FORBID_MARKUP_COMPATIBILITY_NAMESPACE;
    protected boolean ENFORCE_M4_3_FORBID_REFINING_DUBLIN_CORE_ELEMENTS;
    protected boolean ENFORCE_M4_4_FORBID_XML_LANG_ATTRIBUTE;
    protected boolean ENFORCE_M4_5_RESTRICT_XSI_TYPE_ATTRIBUTE;

    private OPCComplianceFlags(boolean forbidMarkupCompatibilityNamespace, boolean forbidRefiningDublinCoreElements, boolean forbidXmlLangAttribute, boolean restrictXsiTypeAttribute) {
        this.ENFORCE_M4_2_FORBID_MARKUP_COMPATIBILITY_NAMESPACE = forbidMarkupCompatibilityNamespace;
        this.ENFORCE_M4_3_FORBID_REFINING_DUBLIN_CORE_ELEMENTS = forbidRefiningDublinCoreElements;
        this.ENFORCE_M4_4_FORBID_XML_LANG_ATTRIBUTE = forbidXmlLangAttribute;
        this.ENFORCE_M4_5_RESTRICT_XSI_TYPE_ATTRIBUTE = restrictXsiTypeAttribute;
    }

    public static OPCComplianceFlags enforceAll() {
        return new OPCComplianceFlags(true, true, true, true);
    }

    public static OPCComplianceFlags disableAll() {
        return new OPCComplianceFlags(false, false, false, false);
    }

    public OPCComplianceFlags setForbidMarkupCompatibilityNamespace(boolean flag) {
        this.ENFORCE_M4_2_FORBID_MARKUP_COMPATIBILITY_NAMESPACE = flag;
        return this;
    }

    public OPCComplianceFlags setForbidRefiningDublinCoreElements(boolean flag) {
        this.ENFORCE_M4_3_FORBID_REFINING_DUBLIN_CORE_ELEMENTS = flag;
        return this;
    }

    public OPCComplianceFlags setForbidXmlLangAttribute(boolean flag) {
        this.ENFORCE_M4_4_FORBID_XML_LANG_ATTRIBUTE = flag;
        return this;
    }

    public OPCComplianceFlags setRestrictXsiTypeAttribute(boolean flag) {
        this.ENFORCE_M4_5_RESTRICT_XSI_TYPE_ATTRIBUTE = flag;
        return this;
    }

    public boolean getForbidMarkupCompatibilityNamespace() {
        return this.ENFORCE_M4_2_FORBID_MARKUP_COMPATIBILITY_NAMESPACE;
    }

    public boolean getForbidRefiningDublinCoreElements() {
        return this.ENFORCE_M4_3_FORBID_REFINING_DUBLIN_CORE_ELEMENTS;
    }

    public boolean getForbidXmlLangAttributes() {
        return this.ENFORCE_M4_4_FORBID_XML_LANG_ATTRIBUTE;
    }

    public boolean getRestrictXsiTypeAttribute() {
        return this.ENFORCE_M4_5_RESTRICT_XSI_TYPE_ATTRIBUTE;
    }
}
