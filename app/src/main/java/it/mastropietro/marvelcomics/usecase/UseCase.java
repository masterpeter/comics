package it.mastropietro.marvelcomics.usecase;

import rx.Scheduler;
import rx.Single;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Angelo Mastropietro on 11/03/17.
 */

public abstract class UseCase {

    private Scheduler backgroundThread;
    private Scheduler mainThread;
    Subscription subscription;

    protected UseCase(Scheduler backgroundThread,
                      Scheduler mainThread) {
        this.backgroundThread = backgroundThread;
        this.mainThread = mainThread;
    }

    protected abstract Single buildObservable();

    @SuppressWarnings("unchecked")
    public void execute(Subscriber subscriber) {
        subscription = buildObservable()
                .subscribeOn(backgroundThread)
                .observeOn(mainThread)
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
