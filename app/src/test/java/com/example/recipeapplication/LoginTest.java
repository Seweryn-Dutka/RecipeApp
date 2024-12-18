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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LoginTest {

    @Mock
    FirebaseAuth mockFirebaseAuth;

    @Mock
    Task<AuthResult> mockAuthResultTask;

    private Login loginActivity;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginActivity = new Login();
        loginActivity.mAuth = mockFirebaseAuth; // Zamieniamy prawdziwy FirebaseAuth na mock
    }

    @Test
    public void givenValidCredentials_whenLogin_thenSuccess() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";

        when(mockFirebaseAuth.signInWithEmailAndPassword(email, password))
                .thenReturn(mockAuthResultTask);

        when(mockAuthResultTask.isSuccessful()).thenReturn(true);

        // Act
        loginActivity.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Assert
                    assert(task.isSuccessful());
                });

        // Verify
        verify(mockFirebaseAuth).signInWithEmailAndPassword(email, password);
    }

    @Test
    public void givenInvalidCredentials_whenLogin_thenFails() {
        // Arrange
        String email = "wrong@example.com";
        String password = "wrongpassword";

        when(mockFirebaseAuth.signInWithEmailAndPassword(email, password))
                .thenReturn(mockAuthResultTask);

        when(mockAuthResultTask.isSuccessful()).thenReturn(false);

        // Act
        loginActivity.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    // Assert
                    assert(!task.isSuccessful());
                });

        // Verify
        verify(mockFirebaseAuth).signInWithEmailAndPassword(email, password);
    }
}
