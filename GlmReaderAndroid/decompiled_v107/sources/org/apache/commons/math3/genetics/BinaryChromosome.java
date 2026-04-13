package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/* loaded from: classes10.dex */
public abstract class BinaryChromosome extends AbstractListChromosome<Integer> {
    public BinaryChromosome(List<Integer> representation) throws InvalidRepresentationException {
        super(representation);
    }

    public BinaryChromosome(Integer[] representation) throws InvalidRepresentationException {
        super(representation);
    }

    @Override // org.apache.commons.math3.genetics.AbstractListChromosome
    protected void checkValidity(List<Integer> chromosomeRepresentation) throws InvalidRepresentationException {
        Iterator i$ = chromosomeRepresentation.iterator();
        while (i$.hasNext()) {
            int i = i$.next().intValue();
            if (i < 0 || i > 1) {
                throw new InvalidRepresentationException(LocalizedFormats.INVALID_BINARY_DIGIT, Integer.valueOf(i));
            }
        }
    }

    public static List<Integer> randomBinaryRepresentation(int length) {
        List<Integer> rList = new ArrayList<>(length);
        for (int j = 0; j < length; j++) {
            rList.add(Integer.valueOf(GeneticAlgorithm.getRandomGenerator().nextInt(2)));
        }
        return rList;
    }

    @Override // org.apache.commons.math3.genetics.Chromosome
    protected boolean isSame(Chromosome another) {
        if (!(another instanceof BinaryChromosome)) {
            return false;
        }
        BinaryChromosome anotherBc = (BinaryChromosome) another;
        if (getLength() != anotherBc.getLength()) {
            return false;
        }
        for (int i = 0; i < getRepresentation().size(); i++) {
            if (!getRepresentation().get(i).equals(anotherBc.getRepresentation().get(i))) {
                return false;
            }
        }
        return true;
    }
}
