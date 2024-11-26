package com.example.projeto_calculadora;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    // Rule to launch MainActivity before tests
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTextViewContent() {
        // Verify initial state of input TextView (should be empty at the start)
        onView(withId(R.id.operacao)).check(matches(withText("")));

        // Verify initial state of output TextView (should be empty at the start)
        onView(withId(R.id.resultado)).check(matches(withText("")));
    }

    @Test
    public void testInputAfterButtonPress() {
        // Simulate button presses and check the input TextView
        onView(withId(R.id.oito)).perform(click()); // Press "8"
        onView(withId(R.id.soma)).perform(click()); // Press "+"
        onView(withId(R.id.tres)).perform(click()); // Press "3"

        // Verify input TextView shows "8+3"
        onView(withId(R.id.operacao)).check(matches(withText("8+3")));
    }

    @Test
    public void testOutputAfterCalculation() {
        // Simulate button presses to perform the addition
        onView(withId(R.id.oito)).perform(click()); // Press "8"
        onView(withId(R.id.soma)).perform(click()); // Press "+"
        onView(withId(R.id.tres)).perform(click()); // Press "3"
        onView(withId(R.id.igual)).perform(click()); // Press "=" to calculate

        // Verify output TextView shows the result "11"
        onView(withId(R.id.resultado)).check(matches(withText("11.0")));
    }

}
