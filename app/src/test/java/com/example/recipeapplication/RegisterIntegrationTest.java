package com.example.recipeapplication;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

import androidx.test.core.app.ActivityScenario;

import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {33})
public class RegisterIntegrationTest {

    private Register registerActivity;
    private FirebaseAuth firebaseAuth;

    @Before
    public void setUp() {
        // Tworzymy mock FirebaseAuth
        firebaseAuth = mock(FirebaseAuth.class);
        FirebaseUser mockUser = mock(FirebaseUser.class);
        when(firebaseAuth.getCurrentUser()).thenReturn(mockUser);

        // Uruchamiamy aktywność rejestracji
        registerActivity = Robolectric.buildActivity(Register.class).create().get();
    }

    @Test
    public void testActivityLaunchesSuccessfully() {
        // Sprawdzamy, czy aktywność Register została poprawnie załadowana
        assertNotNull(registerActivity);
    }

    @Test
    public void testRegisterValidInput() {
        // Wprowadzamy poprawne dane do formularza rejestracji
        TextView textViewEmail = registerActivity.findViewById(R.id.email);
        TextView textViewPassword = registerActivity.findViewById(R.id.password);
        Button registerButton = registerActivity.findViewById(R.id.btn_register);
        ProgressBar progressBar = registerActivity.findViewById(R.id.progressBar);

        // Wypełnianie formularza
        textViewEmail.setText("test@example.com");
        textViewPassword.setText("password123");

        // Klikamy przycisk rejestracji
        registerButton.performClick();

        // Sprawdzamy, czy pasek postępu jest widoczny (symulacja oczekiwania na zakończenie rejestracji)
        assertTrue(progressBar.getVisibility() == ProgressBar.VISIBLE);

        // Sprawdzamy, czy użytkownik został zarejestrowany
        FirebaseUser user = firebaseAuth.getCurrentUser();
        assertNotNull(user);
        assertTrue(user.getEmail().equals("test@example.com"));
    }

    @Test
    public void testInvalidEmail() {
        // Sprawdzamy, czy aplikacja poprawnie reaguje na niepoprawny e-mail
        TextView textViewEmail = registerActivity.findViewById(R.id.email);
        TextView textViewPassword = registerActivity.findViewById(R.id.password);
        Button registerButton = registerActivity.findViewById(R.id.btn_register);

        // Wprowadzamy niepoprawny e-mail
        textViewEmail.setText("invalid-email");
        textViewPassword.setText("password123");

        // Klikamy przycisk rejestracji
        registerButton.performClick();

        // Sprawdzamy, czy Toast z błędem jest wyświetlany
        String toastMessage = ShadowToast.getTextOfLatestToast();
        assertNotNull(toastMessage);
        assertTrue(toastMessage.contains("Enter email"));
    }

    @Test
    public void testRedirectToLoginOnClick() {
        // Sprawdzamy, czy kliknięcie w link do logowania przekierowuje do aktywności logowania
        TextView textView = registerActivity.findViewById(R.id.loginNow);
        textView.performClick();

        // Sprawdzamy, czy po kliknięciu aktywność została przekierowana na stronę logowania
        ActivityScenario<Login> scenario = ActivityScenario.launch(Login.class);
        scenario.onActivity(activity -> {
            // Możemy sprawdzić, czy LoginActivity jest aktywna
            assertNotNull(activity);
        });
    }
}
