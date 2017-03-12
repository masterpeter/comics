package it.mastropietro.marvelcomics.usecase;

import org.junit.Before;
import org.junit.Test;

import rx.Scheduler;
import rx.Single;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Angelo Mastropietro on 12/03/17.
 */
public class UseCaseTest {

    private UseCase useCase;

    @Before
    public void setUp() throws Exception {
        TestScheduler backgroundThread = new TestScheduler();
        TestScheduler mainThread = new TestScheduler();
        useCase = new TestUseCase(backgroundThread, mainThread);
    }

    @Test
    public void whenUseCaseIsExecuted_thenSubscribeToObservableReturnedByUseCase() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        useCase.execute(testSubscriber);

        assertNotNull(useCase.subscription);
    }

    @Test
    public void whenUnsubscribeIsCalled_thenUnsubscribeFromObservable() throws Exception {
        TestSubscriber testSubscriber = new TestSubscriber();
        useCase.execute(testSubscriber);

        assertThat(testSubscriber.isUnsubscribed(), is(false));

        useCase.unsubscribe();

        assertThat(testSubscriber.isUnsubscribed(), is(true));
    }

    private static class TestUseCase extends UseCase {

        TestUseCase(Scheduler backgroundThread, Scheduler mainThread) {
            super(backgroundThread, mainThread);
        }

        @Override protected Single buildObservable() {
            return Single.just(1);
        }
    }

}