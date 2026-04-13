package org.apache.commons.math3.ml.neuralnet.sofm;

import java.util.Iterator;
import org.apache.commons.math3.ml.neuralnet.Network;

/* loaded from: classes10.dex */
public class KohonenTrainingTask implements Runnable {
    private final Iterator<double[]> featuresIterator;
    private final Network net;
    private final KohonenUpdateAction updateAction;

    public KohonenTrainingTask(Network net, Iterator<double[]> featuresIterator, KohonenUpdateAction updateAction) {
        this.net = net;
        this.featuresIterator = featuresIterator;
        this.updateAction = updateAction;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.featuresIterator.hasNext()) {
            this.updateAction.update(this.net, this.featuresIterator.next());
        }
    }
}
