package org.apache.commons.math3.genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes10.dex */
public abstract class AbstractListChromosome<T> extends Chromosome {
    private final List<T> representation;

    protected abstract void checkValidity(List<T> list) throws InvalidRepresentationException;

    public abstract AbstractListChromosome<T> newFixedLengthChromosome(List<T> list);

    public AbstractListChromosome(List<T> representation) throws InvalidRepresentationException {
        this(representation, true);
    }

    public AbstractListChromosome(T[] representation) throws InvalidRepresentationException {
        this(Arrays.asList(representation));
    }

    public AbstractListChromosome(List<T> representation, boolean copyList) {
        checkValidity(representation);
        this.representation = Collections.unmodifiableList(copyList ? new ArrayList<>(representation) : representation);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<T> getRepresentation() {
        return this.representation;
    }

    public int getLength() {
        return getRepresentation().size();
    }

    public String toString() {
        return String.format("(f=%s %s)", Double.valueOf(getFitness()), getRepresentation());
    }
}
