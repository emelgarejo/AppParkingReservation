package pe.edu.upc.appparkingreservation.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import pe.edu.upc.appparkingreservation.R;
import pe.edu.upc.appparkingreservation.model.Person;
import pe.edu.upc.appparkingreservation.service.AccountService;


public class RegisterActivity extends AppCompatActivity {

    private RegisterUserTask mRegisteruserTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mNombre;
    private EditText mApellido;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private SimpleDateFormat dateFormatter;

    private Button regUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.txtEmail);
        mPasswordView = (EditText) findViewById(R.id.txtPassword);
        mNombre = (EditText) findViewById(R.id.txtNombre);
        mApellido = (EditText) findViewById(R.id.txtApellido);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    registerUser();
                    return true;
                }
                return false;
            }
        });

        mLoginFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.login_progress);

        regUserButton = (Button) findViewById(R.id.registerUserButton);
        regUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                registerUser();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registrar, menu);
        return true;
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void registerUser() {
        if (mRegisteruserTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String nombre = mNombre.getText().toString();
        String apellido = mApellido.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(nombre)) {
            mNombre.setError(getString(R.string.error_field_required));
            focusView = mNombre;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(apellido)) {
            mApellido.setError(getString(R.string.error_field_required));
            focusView = mApellido;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mRegisteruserTask = new RegisterUserTask(nombre, apellido, email, password);
            mRegisteruserTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class RegisterUserTask extends AsyncTask<Void, Void, Boolean> {

        private final String mNombre;
        private final String mApellido;
        private final String mEmail;
        private final String mPassword;

        RegisterUserTask(String nombre, String apellido, String email, String password) {
            mEmail = email;
            mPassword = password;
            mApellido = apellido;
            mNombre = nombre;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            AccountService service = new AccountService(RegisterActivity.this);
            Person person = new Person();
            person.setName(mNombre);
            person.setLastName(mApellido);
            person.setPassword(mPassword);
            person.setUserName(mEmail);

            return service.registerPerson(person);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mRegisteruserTask = null;
            showProgress(false);

            if (success) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mRegisteruserTask = null;
            showProgress(false);
        }
    }

}

