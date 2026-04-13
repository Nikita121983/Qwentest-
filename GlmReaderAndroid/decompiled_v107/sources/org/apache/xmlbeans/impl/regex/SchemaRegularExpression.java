package org.apache.xmlbeans.impl.regex;

import java.util.HashMap;
import java.util.Map;
import org.apache.xmlbeans.impl.common.XMLChar;

/* loaded from: classes11.dex */
public class SchemaRegularExpression extends RegularExpression {
    static final Map<String, SchemaRegularExpression> knownPatterns = buildKnownPatternMap();

    private SchemaRegularExpression(String pattern) {
        super(pattern, "X");
    }

    public static RegularExpression forPattern(String s) {
        SchemaRegularExpression tre = knownPatterns.get(s);
        if (tre != null) {
            return tre;
        }
        return new RegularExpression(s, "X");
    }

    private static Map<String, SchemaRegularExpression> buildKnownPatternMap() {
        Map<String, SchemaRegularExpression> result = new HashMap<>();
        result.put("\\c+", new SchemaRegularExpression("\\c+") { // from class: org.apache.xmlbeans.impl.regex.SchemaRegularExpression.1
            @Override // org.apache.xmlbeans.impl.regex.RegularExpression
            public boolean matches(String s) {
                return XMLChar.isValidNmtoken(s);
            }
        });
        result.put("\\i\\c*", new SchemaRegularExpression("\\i\\c*") { // from class: org.apache.xmlbeans.impl.regex.SchemaRegularExpression.2
            @Override // org.apache.xmlbeans.impl.regex.RegularExpression
            public boolean matches(String s) {
                return XMLChar.isValidName(s);
            }
        });
        result.put("[\\i-[:]][\\c-[:]]*", new SchemaRegularExpression("[\\i-[:]][\\c-[:]]*") { // from class: org.apache.xmlbeans.impl.regex.SchemaRegularExpression.3
            @Override // org.apache.xmlbeans.impl.regex.RegularExpression
            public boolean matches(String s) {
                return XMLChar.isValidNCName(s);
            }
        });
        return result;
    }
}
