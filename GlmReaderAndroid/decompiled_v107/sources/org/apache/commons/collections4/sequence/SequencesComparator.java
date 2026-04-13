package org.apache.commons.collections4.sequence;

import java.util.List;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.functors.DefaultEquator;

/* loaded from: classes9.dex */
public class SequencesComparator<T> {
    private final Equator<? super T> equator;
    private final List<T> sequence1;
    private final List<T> sequence2;
    private final int[] vDown;
    private final int[] vUp;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Snake {
        private final int diag;
        private final int end;
        private final int start;

        Snake(int start, int end, int diag) {
            this.start = start;
            this.end = end;
            this.diag = diag;
        }

        public int getDiag() {
            return this.diag;
        }

        public int getEnd() {
            return this.end;
        }

        public int getStart() {
            return this.start;
        }
    }

    public SequencesComparator(List<T> sequence1, List<T> sequence2) {
        this(sequence1, sequence2, DefaultEquator.defaultEquator());
    }

    public SequencesComparator(List<T> sequence1, List<T> sequence2, Equator<? super T> equator) {
        this.sequence1 = sequence1;
        this.sequence2 = sequence2;
        this.equator = equator;
        int size = sequence1.size() + sequence2.size() + 2;
        this.vDown = new int[size];
        this.vUp = new int[size];
    }

    private void buildScript(int i, int i2, int i3, int i4, EditScript<T> editScript) {
        Snake middleSnake = getMiddleSnake(i, i2, i3, i4);
        if (middleSnake == null || ((middleSnake.getStart() == i2 && middleSnake.getDiag() == i2 - i4) || (middleSnake.getEnd() == i && middleSnake.getDiag() == i - i3))) {
            int i5 = i;
            int i6 = i3;
            while (true) {
                if (i5 < i2 || i6 < i4) {
                    if (i5 < i2 && i6 < i4 && this.equator.equate(this.sequence1.get(i5), this.sequence2.get(i6))) {
                        editScript.append(new KeepCommand<>(this.sequence1.get(i5)));
                        i5++;
                        i6++;
                    } else if (i2 - i > i4 - i3) {
                        editScript.append(new DeleteCommand<>(this.sequence1.get(i5)));
                        i5++;
                    } else {
                        editScript.append(new InsertCommand<>(this.sequence2.get(i6)));
                        i6++;
                    }
                } else {
                    return;
                }
            }
        } else {
            buildScript(i, middleSnake.getStart(), i3, middleSnake.getStart() - middleSnake.getDiag(), editScript);
            for (int start = middleSnake.getStart(); start < middleSnake.getEnd(); start++) {
                editScript.append(new KeepCommand<>(this.sequence1.get(start)));
            }
            buildScript(middleSnake.getEnd(), i2, middleSnake.getEnd() - middleSnake.getDiag(), i4, editScript);
        }
    }

    private Snake buildSnake(int i, int i2, int i3, int i4) {
        int i5 = i;
        while (i5 - i2 < i4 && i5 < i3 && this.equator.equate(this.sequence1.get(i5), this.sequence2.get(i5 - i2))) {
            i5++;
        }
        return new Snake(i, i5, i2);
    }

    private Snake getMiddleSnake(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i2 - i;
        int i8 = i4 - i3;
        if (i7 != 0 && i8 != 0) {
            int i9 = i7 - i8;
            int i10 = i8 + i7;
            int i11 = (i10 % 2 == 0 ? i10 : i10 + 1) / 2;
            this.vDown[i11 + 1] = i;
            this.vUp[i11 + 1] = i2 + 1;
            int i12 = 0;
            while (i12 <= i11) {
                int i13 = -i12;
                while (i13 <= i12) {
                    int i14 = i13 + i11;
                    if (i13 == (-i12) || (i13 != i12 && this.vDown[i14 - 1] < this.vDown[i14 + 1])) {
                        this.vDown[i14] = this.vDown[i14 + 1];
                    } else {
                        this.vDown[i14] = this.vDown[i14 - 1] + 1;
                    }
                    int i15 = this.vDown[i14];
                    int i16 = ((i15 - i) + i3) - i13;
                    while (i15 < i2 && i16 < i4) {
                        i5 = i7;
                        i6 = i8;
                        if (!this.equator.equate(this.sequence1.get(i15), this.sequence2.get(i16))) {
                            break;
                        }
                        i15++;
                        this.vDown[i14] = i15;
                        i16++;
                        i7 = i5;
                        i8 = i6;
                    }
                    i5 = i7;
                    i6 = i8;
                    if (i9 % 2 != 0 && i9 - i12 <= i13 && i13 <= i9 + i12 && this.vUp[i14 - i9] <= this.vDown[i14]) {
                        return buildSnake(this.vUp[i14 - i9], (i13 + i) - i3, i2, i4);
                    }
                    i13 += 2;
                    i7 = i5;
                    i8 = i6;
                }
                int i17 = i7;
                int i18 = i8;
                for (int i19 = i9 - i12; i19 <= i9 + i12; i19 += 2) {
                    int i20 = (i19 + i11) - i9;
                    if (i19 == i9 - i12 || (i19 != i9 + i12 && this.vUp[i20 + 1] <= this.vUp[i20 - 1])) {
                        this.vUp[i20] = this.vUp[i20 + 1] - 1;
                    } else {
                        this.vUp[i20] = this.vUp[i20 - 1];
                    }
                    int i21 = this.vUp[i20] - 1;
                    int i22 = ((i21 - i) + i3) - i19;
                    while (i21 >= i && i22 >= i3 && this.equator.equate(this.sequence1.get(i21), this.sequence2.get(i22))) {
                        this.vUp[i20] = i21;
                        i22--;
                        i21--;
                    }
                    if (i9 % 2 == 0 && (-i12) <= i19 && i19 <= i12 && this.vUp[i20] <= this.vDown[i20 + i9]) {
                        return buildSnake(this.vUp[i20], (i19 + i) - i3, i2, i4);
                    }
                }
                i12++;
                i7 = i17;
                i8 = i18;
            }
            throw new IllegalStateException("Internal Error");
        }
        return null;
    }

    public EditScript<T> getScript() {
        EditScript<T> script = new EditScript<>();
        buildScript(0, this.sequence1.size(), 0, this.sequence2.size(), script);
        return script;
    }
}
