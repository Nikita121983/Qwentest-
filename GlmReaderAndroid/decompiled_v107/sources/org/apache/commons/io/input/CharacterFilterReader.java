package org.apache.commons.io.input;

import java.io.Reader;
import java.util.function.IntPredicate;

/* loaded from: classes9.dex */
public class CharacterFilterReader extends AbstractCharacterFilterReader {
    public CharacterFilterReader(Reader reader, final int skip) {
        super(reader, new IntPredicate() { // from class: org.apache.commons.io.input.CharacterFilterReader$$ExternalSyntheticLambda0
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return CharacterFilterReader.lambda$new$0(skip, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$new$0(int skip, int c) {
        return c == skip;
    }

    public CharacterFilterReader(Reader reader, IntPredicate skip) {
        super(reader, skip);
    }
}
