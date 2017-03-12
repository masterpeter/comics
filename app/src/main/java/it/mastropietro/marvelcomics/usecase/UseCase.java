package it.mastropietro.marvelcomics.usecase;

import rx.Scheduler;
import rx.Single;
import rx.Subscription;

/**
 * Created by Angelo Mastropietro on 11/03/17.
 */

abstract class UseCase {

    private Scheduler backgroundThread;
    private Scheduler mainThread;
    private Subscription subscription;

    UseCase(Scheduler backgroundThread,
            Scheduler mainThread) {
        this.backgroundThread = backgroundThread;
        this.mainThread = mainThread;
    }

    protected abstract Single buildObservable();

    public void execute() {
        subscription = buildObservable()
                .subscribeOn(backgroundThread)
                .observeOn(mainThread)
                .subscribe();
    }

    public void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
