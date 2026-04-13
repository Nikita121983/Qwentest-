package org.apache.poi.ss.formula;

/* loaded from: classes10.dex */
public interface IStabilityClassifier {
    public static final IStabilityClassifier TOTALLY_IMMUTABLE = new IStabilityClassifier() { // from class: org.apache.poi.ss.formula.IStabilityClassifier$$ExternalSyntheticLambda0
        @Override // org.apache.poi.ss.formula.IStabilityClassifier
        public final boolean isCellFinal(int i, int i2, int i3) {
            return IStabilityClassifier.lambda$static$0(i, i2, i3);
        }
    };

    boolean isCellFinal(int i, int i2, int i3);

    static /* synthetic */ boolean lambda$static$0(int sheetIndex, int rowIndex, int columnIndex) {
        return true;
    }
}
