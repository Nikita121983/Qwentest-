package androidx.constraintlayout.core.parser;

import java.util.Iterator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/* loaded from: classes.dex */
public class CLArray extends CLContainer {
    public CLArray(char[] content) {
        super(content);
    }

    public static CLElement allocate(char[] content) {
        return new CLArray(content);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toJSON() {
        StringBuilder content = new StringBuilder(getDebugName() + CollectionUtils.DEFAULT_TOSTRING_PREFIX);
        boolean first = true;
        for (int i = 0; i < this.mElements.size(); i++) {
            if (!first) {
                content.append(", ");
            } else {
                first = false;
            }
            content.append(this.mElements.get(i).toJSON());
        }
        return ((Object) content) + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toFormattedJSON(int indent, int forceIndent) {
        StringBuilder json = new StringBuilder();
        String val = toJSON();
        if (forceIndent <= 0 && val.length() + indent < sMaxLine) {
            json.append(val);
        } else {
            json.append("[\n");
            boolean first = true;
            Iterator<CLElement> it = this.mElements.iterator();
            while (it.hasNext()) {
                CLElement element = it.next();
                if (!first) {
                    json.append(",\n");
                } else {
                    first = false;
                }
                addIndent(json, sBaseIndent + indent);
                json.append(element.toFormattedJSON(sBaseIndent + indent, forceIndent - 1));
            }
            json.append(StringUtils.LF);
            addIndent(json, indent);
            json.append(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        return json.toString();
    }
}
