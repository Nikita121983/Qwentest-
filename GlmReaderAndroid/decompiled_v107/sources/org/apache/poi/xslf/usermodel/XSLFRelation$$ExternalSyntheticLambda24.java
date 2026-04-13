package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes10.dex */
public final /* synthetic */ class XSLFRelation$$ExternalSyntheticLambda24 implements POIXMLRelation.PackagePartConstructor {
    @Override // org.apache.poi.ooxml.POIXMLRelation.PackagePartConstructor
    public final POIXMLDocumentPart init(PackagePart packagePart) {
        return new XSSFWorkbook(packagePart);
    }
}
