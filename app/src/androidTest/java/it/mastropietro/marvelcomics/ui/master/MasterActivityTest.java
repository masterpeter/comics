package it.mastropietro.marvelcomics.ui.master;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import it.mastropietro.marvelcomics.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by Angelo Mastropietro on 13/03/17.
 */
@RunWith(AndroidJUnit4.class)
public class MasterActivityTest {

    @Rule
    public ActivityTestRule<MasterActivity> activityRule = new ActivityTestRule<>(MasterActivity.class);

    @Test
    public void whenActivityIsCreated_showSomeDataHappyCase() throws Exception {
        activityRule.getActivity();

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())));
    }
}