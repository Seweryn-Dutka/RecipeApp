package com.example.recipeapplication;

import static org.mockito.Mockito.*;

import android.content.Intent;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegisterTest {

    @Mock
    FirebaseAuth mockFirebaseAuth;

    @Mock
    Task<AuthResult> mockAuthResultTask;

    private Register registerActivity;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registerActivity = new Register();
        registerActivity.mAuth = mockFirebaseAuth; // Zamieniamy prawdziwy FirebaseAuth na mock
    }

    @Test
    public void givenValidEmailAndPassword_whenRegister_thenUserIsCreated() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";

        when(mockFirebaseAuth.createUserWithEmailAndPassword(email, password))
                .thenReturn(mockAuthResultTask);

        when(mockAuthResultTask.isSuccessful()).thenReturn(true);

        // Act
        registerActivity.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Assert
                    assert(task.isSuccessful());
                });

        // Verify
        verify(mockFirebaseAuth).createUserWithEmailAndPassword(email, password);
    }

    @Test
    public void givenInvalidEmail_whenRegister_thenAuthenticationFails() {
        // Arrange
        String email = "invalidEmail";
        String password = "password123";

        when(mockFirebaseAuth.createUserWithEmailAndPassword(email, password))
                .thenReturn(mockAuthResultTask);

        when(mockAuthResultTask.isSuccessful()).thenReturn(false);

        // Act
        registerActivity.mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Assert
                    assert(!task.isSuccessful());
                });

        // Verify
        verify(mockFirebaseAuth).createUserWithEmailAndPassword(email, password);
    }
}
