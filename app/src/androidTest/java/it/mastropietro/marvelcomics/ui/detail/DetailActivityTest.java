package it.mastropietro.marvelcomics.ui.detail;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import it.mastropietro.marvelcomics.R;
import it.mastropietro.marvelcomics.model.Comic;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Angelo Mastropietro on 13/03/17.
 */
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> activityRule = new ActivityTestRule<>(DetailActivity.class, true, false);

    @Test
    public void whenDetailIsOpened_showComicInfo() throws Exception {
        Intent callingIntent = DetailActivity.getCallingIntent(InstrumentationRegistry.getContext(), buildSampleComic());
        activityRule.launchActivity(callingIntent);

        onView(withId(R.id.description)).check(matches(withText("Lorem ipsum")));
    }

    private Comic buildSampleComic() {
        return new Comic.Builder()
                .title("Simple title")
                .description("Lorem ipsum")
                .images(new ArrayList<String>())
                .characterList(new ArrayList<String>())
                .build();
    }
}