package org.apache.poi.xslf.model;

import java.util.function.Consumer;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlideMaster;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

@Internal
/* loaded from: classes10.dex */
public final class CharacterPropertyFetcher<T> extends PropertyFetcher<T> {
    int _level;
    private final CharPropFetcher<T> fetcher;
    private final XSLFTextRun run;

    /* loaded from: classes10.dex */
    public interface CharPropFetcher<S> {
        void fetch(CTTextCharacterProperties cTTextCharacterProperties, Consumer<S> consumer);
    }

    public CharacterPropertyFetcher(XSLFTextRun run, CharPropFetcher<T> fetcher) {
        this._level = run.getParagraph().getIndentLevel();
        this.fetcher = fetcher;
        this.run = run;
    }

    @Override // org.apache.poi.xslf.model.PropertyFetcher
    public boolean fetch(XSLFShape shape) {
        try {
            fetchProp(ParagraphPropertyFetcher.select(shape, this._level));
        } catch (XmlException e) {
        }
        return isSet();
    }

    public T fetchProperty(XSLFShape shape) {
        Sheet<XSLFShape, XSLFTextParagraph> sheet = shape.getSheet();
        fetchRunProp();
        if (!(sheet instanceof XSLFSlideMaster)) {
            fetchParagraphDefaultRunProp();
            fetchShapeProp(shape);
            fetchThemeProp(shape);
        }
        fetchMasterProp();
        if (isSet()) {
            return getValue();
        }
        return null;
    }

    private void fetchRunProp() {
        fetchProp(this.run.getRPr(false));
    }

    private void fetchParagraphDefaultRunProp() {
        CTTextParagraphProperties pr;
        if (!isSet() && (pr = this.run.getParagraph().getXmlObject().getPPr()) != null) {
            fetchProp(pr.getDefRPr());
        }
    }

    private void fetchShapeProp(XSLFShape shape) {
        if (!isSet()) {
            shape.fetchShapeProperty(this);
        }
    }

    private void fetchThemeProp(XSLFShape shape) {
        if (!isSet()) {
            fetchProp(ParagraphPropertyFetcher.getThemeProps(shape, this._level));
        }
    }

    private void fetchMasterProp() {
        if (!isSet()) {
            fetchProp(this.run.getParagraph().getDefaultMasterStyle());
        }
    }

    private void fetchProp(CTTextParagraphProperties props) {
        if (props != null) {
            fetchProp(props.getDefRPr());
        }
    }

    private void fetchProp(CTTextCharacterProperties props) {
        if (props != null) {
            this.fetcher.fetch(props, new Consumer() { // from class: org.apache.poi.xslf.model.CharacterPropertyFetcher$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    CharacterPropertyFetcher.this.setValue(obj);
                }
            });
        }
    }
}
