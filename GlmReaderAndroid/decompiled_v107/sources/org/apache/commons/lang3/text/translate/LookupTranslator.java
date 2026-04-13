package org.apache.commons.lang3.text.translate;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

@Deprecated
/* loaded from: classes9.dex */
public class LookupTranslator extends CharSequenceTranslator {
    private final int longest;
    private final HashMap<String, String> lookupMap = new HashMap<>();
    private final HashSet<Character> prefixSet = new HashSet<>();
    private final int shortest;

    public LookupTranslator(CharSequence[]... lookup) {
        int tmpShortest = Integer.MAX_VALUE;
        int tmpLongest = 0;
        if (lookup != null) {
            for (CharSequence[] seq : lookup) {
                this.lookupMap.put(seq[0].toString(), seq[1].toString());
                this.prefixSet.add(Character.valueOf(seq[0].charAt(0)));
                int sz = seq[0].length();
                tmpShortest = sz < tmpShortest ? sz : tmpShortest;
                if (sz > tmpLongest) {
                    tmpLongest = sz;
                }
            }
        }
        this.shortest = tmpShortest;
        this.longest = tmpLongest;
    }

    @Override // org.apache.commons.lang3.text.translate.CharSequenceTranslator
    public int translate(CharSequence input, int index, Writer out) throws IOException {
        if (this.prefixSet.contains(Character.valueOf(input.charAt(index)))) {
            int max = this.longest;
            if (this.longest + index > input.length()) {
                max = input.length() - index;
            }
            for (int i = max; i >= this.shortest; i--) {
                CharSequence subSeq = input.subSequence(index, index + i);
                String result = this.lookupMap.get(subSeq.toString());
                if (result != null) {
                    out.write(result);
                    return i;
                }
            }
            return 0;
        }
        return 0;
    }
}
