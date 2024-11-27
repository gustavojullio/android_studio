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
        onView(withId(R.id.operacao)).check(matches(withText("8 + 3")));
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

    @Test
    public void testeSaidaSubtracao(){
        // Simulação quando pressiona no botão de subtração
        onView(withId(R.id.oito)).perform(click()); // Pressiona "8"
        onView(withId(R.id.subtracao)).perform(click()); // Pressiona "-"
        onView(withId(R.id.tres)).perform(click()); // Pressiona "3"
        onView(withId(R.id.igual)).perform(click()); // Pressiona "=" para calcular

        // Verifica se a saída do TextView foi igual a "5"
        onView(withId(R.id.resultado)).check(matches(withText("5.0")));
    }

    @Test
    public void testeSaidaMultiplicacao(){
        // Simulação quando pressiona no botão de multiplicação
        onView(withId(R.id.oito)).perform(click()); // Pressiona "8"
        onView(withId(R.id.multiplicacao)).perform(click()); // Pressiona "x"
        onView(withId(R.id.tres)).perform(click()); // Pressiona "3"
        onView(withId(R.id.igual)).perform(click()); // Pressiona "=" para calcular

        // Verifica se a saída do TextView foi igual a "24"
        onView(withId(R.id.resultado)).check(matches(withText("24.0")));
    }

    @Test
    public void testeSaidaDivisao(){
        // Simulação quando pressiona no botão de divisão
        onView(withId(R.id.nove)).perform(click()); // Pressiona "9"
        onView(withId(R.id.divisao)).perform(click()); // Pressiona "/"
        onView(withId(R.id.tres)).perform(click()); // Pressiona "3"
        onView(withId(R.id.igual)).perform(click()); // Pressiona "=" para calcular

        // Verifica se a saída do TextView foi igual a "3"
        onView(withId(R.id.resultado)).check(matches(withText("3.0")));
    }

    @Test
    public void testeSaidaDivisaoPorZero(){
        // Simulação quando pressiona no botão de divisao por zero
        onView(withId(R.id.nove)).perform(click()); // Pressiona "9"
        onView(withId(R.id.divisao)).perform(click()); // Pressiona "/"
        onView(withId(R.id.zero)).perform(click()); // Pressiona "0"
        onView(withId(R.id.igual)).perform(click()); // Pressiona "=" para calcular

        // Verifica se a saída do TextView foi igual a "Não é possível dividir por zero!"
        onView(withId(R.id.resultado)).check(matches(withText("Não é possível dividir por zero!")));
    }

    @Test
    public void testePorcentagem(){
        // Simulação quando pressiona no botão de porcentagem
        onView(withId(R.id.nove)).perform(click()); // Pressiona "9"
        onView(withId(R.id.porcentagem)).perform(click()); // Pressiona "%"

        // Verifica se a saída do TextView foi igual a "0.09"
        onView(withId(R.id.resultado)).check(matches(withText("0.09")));
    }

    @Test
    public void testeInverterSinal(){
        // Simulação quando pressiona no botão de +/-
        onView(withId(R.id.nove)).perform(click()); // Pressiona "9"
        onView(withId(R.id.maisMenos)).perform(click()); // Pressiona "+/-"

        // Verifica se a saída do TextView foi igual a "-9"
        onView(withId(R.id.operacao)).check(matches(withText("-9.0")));
    }

    @Test
    public void testeAc(){
        // Simulação quando pressiona no botão de AC
        onView(withId(R.id.nove)).perform(click()); // Pressiona "9"
        onView(withId(R.id.multiplicacao)).perform(click()); // Pressiona "x"
        onView(withId(R.id.tres)).perform(click()); // Pressiona "3"
        onView(withId(R.id.igual)).perform(click()); // Pressiona "=" para calcular
        onView(withId(R.id.ac)).perform(click()); // Pressiona "AC" para limpar

        // Verifica se a saída do TextView foi igual a ""
        onView(withId(R.id.operacao)).check(matches(withText("")));
    }
















}
