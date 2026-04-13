package org.apache.poi.ss.format;

import java.util.Locale;
import java.util.regex.Matcher;
import org.apache.poi.ss.format.CellFormatPart;

/* loaded from: classes10.dex */
public class CellTextFormatter extends CellFormatter {
    static final CellFormatter SIMPLE_TEXT = new CellTextFormatter("@");
    private final String desc;
    private final int[] textPos;

    public CellTextFormatter(String format) {
        super(format);
        final int[] numPlaces = new int[1];
        this.desc = CellFormatPart.parseFormat(format, CellFormatType.TEXT, new CellFormatPart.PartHandler() { // from class: org.apache.poi.ss.format.CellTextFormatter$$ExternalSyntheticLambda0
            @Override // org.apache.poi.ss.format.CellFormatPart.PartHandler
            public final String handlePart(Matcher matcher, String str, CellFormatType cellFormatType, StringBuffer stringBuffer) {
                return CellTextFormatter.lambda$new$0(numPlaces, matcher, str, cellFormatType, stringBuffer);
            }
        }).toString();
        this.textPos = new int[numPlaces[0]];
        int pos = this.desc.length() - 1;
        for (int i = 0; i < this.textPos.length; i++) {
            this.textPos[i] = this.desc.lastIndexOf(0, pos);
            pos = this.textPos[i] - 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$new$0(int[] numPlaces, Matcher m, String part, CellFormatType type, StringBuffer desc) {
        if (part.equals("@")) {
            numPlaces[0] = numPlaces[0] + 1;
            return "\u0000";
        }
        return null;
    }

    @Override // org.apache.poi.ss.format.CellFormatter
    public void formatValue(StringBuffer toAppendTo, Object obj) {
        int start = toAppendTo.length();
        String text = obj.toString();
        if (obj instanceof Boolean) {
            text = text.toUpperCase(Locale.ROOT);
        }
        toAppendTo.append(this.desc);
        for (int textPo : this.textPos) {
            int pos = start + textPo;
            toAppendTo.replace(pos, pos + 1, text);
        }
    }

    @Override // org.apache.poi.ss.format.CellFormatter
    public void simpleValue(StringBuffer toAppendTo, Object value) {
        SIMPLE_TEXT.formatValue(toAppendTo, value);
    }
}
