package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTrueFalse;

/* loaded from: classes.dex */
public interface CTSignatureLine extends XmlObject {
    public static final DocumentFactory<CTSignatureLine> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsignaturelineec85type");
    public static final SchemaType type = Factory.getType();

    String getAddlxml();

    STTrueFalse.Enum getAllowcomments();

    STExt.Enum getExt();

    String getId();

    STTrueFalse.Enum getIssignatureline();

    String getProvid();

    STTrueFalse.Enum getShowsigndate();

    String getSigninginstructions();

    STTrueFalse.Enum getSigninginstructionsset();

    String getSigprovurl();

    String getSuggestedsigner();

    String getSuggestedsigner2();

    String getSuggestedsigneremail();

    boolean isSetAddlxml();

    boolean isSetAllowcomments();

    boolean isSetExt();

    boolean isSetId();

    boolean isSetIssignatureline();

    boolean isSetProvid();

    boolean isSetShowsigndate();

    boolean isSetSigninginstructions();

    boolean isSetSigninginstructionsset();

    boolean isSetSigprovurl();

    boolean isSetSuggestedsigner();

    boolean isSetSuggestedsigner2();

    boolean isSetSuggestedsigneremail();

    void setAddlxml(String str);

    void setAllowcomments(STTrueFalse.Enum r1);

    void setExt(STExt.Enum r1);

    void setId(String str);

    void setIssignatureline(STTrueFalse.Enum r1);

    void setProvid(String str);

    void setShowsigndate(STTrueFalse.Enum r1);

    void setSigninginstructions(String str);

    void setSigninginstructionsset(STTrueFalse.Enum r1);

    void setSigprovurl(String str);

    void setSuggestedsigner(String str);

    void setSuggestedsigner2(String str);

    void setSuggestedsigneremail(String str);

    void unsetAddlxml();

    void unsetAllowcomments();

    void unsetExt();

    void unsetId();

    void unsetIssignatureline();

    void unsetProvid();

    void unsetShowsigndate();

    void unsetSigninginstructions();

    void unsetSigninginstructionsset();

    void unsetSigprovurl();

    void unsetSuggestedsigner();

    void unsetSuggestedsigner2();

    void unsetSuggestedsigneremail();

    XmlString xgetAddlxml();

    STTrueFalse xgetAllowcomments();

    STExt xgetExt();

    STGuid xgetId();

    STTrueFalse xgetIssignatureline();

    STGuid xgetProvid();

    STTrueFalse xgetShowsigndate();

    XmlString xgetSigninginstructions();

    STTrueFalse xgetSigninginstructionsset();

    XmlString xgetSigprovurl();

    XmlString xgetSuggestedsigner();

    XmlString xgetSuggestedsigner2();

    XmlString xgetSuggestedsigneremail();

    void xsetAddlxml(XmlString xmlString);

    void xsetAllowcomments(STTrueFalse sTTrueFalse);

    void xsetExt(STExt sTExt);

    void xsetId(STGuid sTGuid);

    void xsetIssignatureline(STTrueFalse sTTrueFalse);

    void xsetProvid(STGuid sTGuid);

    void xsetShowsigndate(STTrueFalse sTTrueFalse);

    void xsetSigninginstructions(XmlString xmlString);

    void xsetSigninginstructionsset(STTrueFalse sTTrueFalse);

    void xsetSigprovurl(XmlString xmlString);

    void xsetSuggestedsigner(XmlString xmlString);

    void xsetSuggestedsigner2(XmlString xmlString);

    void xsetSuggestedsigneremail(XmlString xmlString);
}
