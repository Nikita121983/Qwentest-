package org.apache.poi.xdgf.usermodel;

import com.microsoft.schemas.office.visio.x2012.main.DocumentSettingsType;
import com.microsoft.schemas.office.visio.x2012.main.StyleSheetType;
import com.microsoft.schemas.office.visio.x2012.main.VisioDocumentType;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;

/* loaded from: classes10.dex */
public class XDGFDocument {
    long _defaultFillStyle;
    long _defaultGuideStyle;
    long _defaultLineStyle;
    long _defaultTextStyle;
    protected VisioDocumentType _document;
    Map<Long, XDGFStyleSheet> _styleSheets = new HashMap();

    public XDGFDocument(VisioDocumentType document) {
        this._document = document;
        if (!this._document.isSetDocumentSettings()) {
            throw new POIXMLException("Document settings not found");
        }
        DocumentSettingsType docSettings = this._document.getDocumentSettings();
        if (docSettings.isSetDefaultFillStyle()) {
            this._defaultFillStyle = docSettings.getDefaultFillStyle();
        }
        if (docSettings.isSetDefaultGuideStyle()) {
            this._defaultGuideStyle = docSettings.getDefaultGuideStyle();
        }
        if (docSettings.isSetDefaultLineStyle()) {
            this._defaultLineStyle = docSettings.getDefaultLineStyle();
        }
        if (docSettings.isSetDefaultTextStyle()) {
            this._defaultTextStyle = docSettings.getDefaultTextStyle();
        }
        if (this._document.isSetStyleSheets()) {
            for (StyleSheetType styleSheet : this._document.getStyleSheets().getStyleSheetArray()) {
                this._styleSheets.put(Long.valueOf(styleSheet.getID()), new XDGFStyleSheet(styleSheet, this));
            }
        }
    }

    @Internal
    public VisioDocumentType getXmlObject() {
        return this._document;
    }

    public XDGFStyleSheet getStyleById(long id) {
        return this._styleSheets.get(Long.valueOf(id));
    }

    public XDGFStyleSheet getDefaultFillStyle() {
        XDGFStyleSheet style = getStyleById(this._defaultFillStyle);
        if (style == null) {
            throw new POIXMLException("No default fill style found!");
        }
        return style;
    }

    public XDGFStyleSheet getDefaultGuideStyle() {
        XDGFStyleSheet style = getStyleById(this._defaultGuideStyle);
        if (style == null) {
            throw new POIXMLException("No default guide style found!");
        }
        return style;
    }

    public XDGFStyleSheet getDefaultLineStyle() {
        XDGFStyleSheet style = getStyleById(this._defaultLineStyle);
        if (style == null) {
            throw new POIXMLException("No default line style found!");
        }
        return style;
    }

    public XDGFStyleSheet getDefaultTextStyle() {
        XDGFStyleSheet style = getStyleById(this._defaultTextStyle);
        if (style == null) {
            throw new POIXMLException("No default text style found!");
        }
        return style;
    }
}
