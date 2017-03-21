package it.mastropietro.marvelcomics.data.repository.clock;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by Angelo Mastropietro on 21/03/17.
 */
public class ClockImplTest {

    private ClockImpl clock;

    @Before
    public void setUp() throws Exception {
        clock = new ClockImpl();
    }

    @Test
    public void isCurrentTimeAfter() throws Exception {
        String toCompare = String.valueOf(System.currentTimeMillis() - 1000);

        assertTrue(clock.isCurrentTimeAfter(toCompare));

        toCompare = String.valueOf(System.currentTimeMillis() + 1000);

        assertFalse(clock.isCurrentTimeAfter(toCompare));
    }
}