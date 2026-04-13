package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.MasterType;
import com.microsoft.schemas.office.visio.x2012.main.MastersDocument;
import com.microsoft.schemas.office.visio.x2012.main.MastersType;
import com.microsoft.schemas.office.visio.x2012.main.RelType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.util.Internal;
import org.apache.poi.xdgf.exceptions.XDGFException;
import org.apache.poi.xdgf.xml.XDGFXMLDocumentPart;
import org.apache.xmlbeans.XmlException;

/* loaded from: classes10.dex */
public class XDGFMasters extends XDGFXMLDocumentPart {
    protected Map<Long, XDGFMaster> _masters;
    MastersType _mastersObject;

    public XDGFMasters(PackagePart part) {
        super(part);
        this._masters = new HashMap();
    }

    @Internal
    protected MastersType getXmlObject() {
        return this._mastersObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.poi.ooxml.POIXMLDocumentPart
    public void onDocumentRead() {
        try {
            try {
                InputStream stream = getPackagePart().getInputStream();
                try {
                    this._mastersObject = MastersDocument.Factory.parse(stream).getMasters();
                    if (stream != null) {
                        stream.close();
                    }
                    Map<String, MasterType> masterSettings = new HashMap<>();
                    for (MasterType master : this._mastersObject.getMasterArray()) {
                        RelType rel = master.getRel();
                        if (rel != null) {
                            masterSettings.put(rel.getId(), master);
                        }
                    }
                    for (POIXMLDocumentPart.RelationPart rp : getRelationParts()) {
                        POIXMLDocumentPart part = rp.getDocumentPart();
                        String relId = rp.getRelationship().getId();
                        MasterType settings = masterSettings.get(relId);
                        if (settings == null) {
                            throw new POIXMLException("Master relationship for " + relId + " not found");
                        }
                        if (!(part instanceof XDGFMasterContents)) {
                            throw new POIXMLException("Unexpected masters relationship for " + relId + ": " + part);
                        }
                        XDGFMasterContents contents = (XDGFMasterContents) part;
                        contents.onDocumentRead();
                        XDGFMaster master2 = new XDGFMaster(settings, contents, this._document);
                        this._masters.put(Long.valueOf(master2.getID()), master2);
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (stream != null) {
                            try {
                                stream.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (POIXMLException e) {
                throw XDGFException.wrap(this, e);
            }
        } catch (IOException | XmlException e2) {
            throw new POIXMLException(e2);
        }
    }

    public Collection<XDGFMaster> getMastersList() {
        return Collections.unmodifiableCollection(this._masters.values());
    }

    public XDGFMaster getMasterById(long masterId) {
        return this._masters.get(Long.valueOf(masterId));
    }
}
