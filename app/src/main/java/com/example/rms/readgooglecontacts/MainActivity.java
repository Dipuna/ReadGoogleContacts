package com.example.rms.readgooglecontacts;

/*import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.gdata.client.authn.oauth.GoogleOAuthParameters;
import com.google.gdata.client.authn.oauth.OAuthException;
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.contacts.*;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;
import java.io.IOException;
import java.net.URL;*/
/*import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.People;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;*/
import com.google.gson.Gson;

import org.json.JSONException;

/*import com.google.gdata.client.*;
import com.google.gdata.client.contacts.*;
import com.google.gdata.data.*;
import com.google.gdata.data.contacts.*;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.*;
import com.google.gdata.util.*;
import java.io.IOException;
import java.net.URL;*/
import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.People;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.Person;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener{

    private static final String TAG = "RestApiActivity";

    // Scope for reading user's contacts
    private static final String CONTACTS_SCOPE = "https://www.googleapis.com/auth/contacts.readonly";

    // Bundle key for account object
    private static final String KEY_ACCOUNT = "key_account";

    // Request codes
    private static final int RC_SIGN_IN = 9001;
    private static final int RC_RECOVERABLE = 9002;

    // Global instance of the HTTP transport
    private static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();

    // Global instance of the JSON factory
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private GoogleApiClient mGoogleApiClient;

    private Account mAccount;

//    private TextView mStatusTextView;
//    private TextView mDetailTextView;
    private ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        myService = new ContactsService("ContactRead");
        // Restore instance state
        if (savedInstanceState != null) {
            mAccount = savedInstanceState.getParcelable(KEY_ACCOUNT);
        }
        // Configure sign-in to request the user's ID, email address, basic profile,
        // and readonly access to contacts.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(CONTACTS_SCOPE))
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


       /* final ContactsService myService = new ContactsService("ContactRead");
//        myService.getRequestFactory().setHeader("User-Agent", "Google-contactsExampleApp-3");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//                        myService.setUserCredentials(username, pwd);
                        printAllContacts(myService);
                    } catch (AuthenticationException e) {
                        e.printStackTrace();
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();

*/ Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }


    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.
            showProgressDialog();
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    hideProgressDialog();
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_ACCOUNT, mAccount);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

        // Handling a user-recoverable auth exception
        if (requestCode == RC_RECOVERABLE) {
            if (resultCode == RESULT_OK) {
                getContacts();
            } else {
                Toast.makeText(this,"contacts failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Get the account from the sign in result
            GoogleSignInAccount account = result.getSignInAccount();

            // Signed in successfully, show authenticated UI.
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName()));
//            updateUI(true);

            // Store the account from the result
            mAccount = account.getAccount();

            // Asynchronously access the People API for the account
            getContacts();
        } else {
            // Clear the local account
            mAccount = null;

            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }

    private void getContacts() {
        if (mAccount == null) {
            Log.w(TAG, "getContacts: null account");
            return;
        }

        new GetContactsTask().execute(mAccount);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.w(TAG, "onConnectionFailed:" + connectionResult);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * AsyncTask that uses the credentials from Google Sign In to access the People API.
     */
    private class GetContactsTask extends AsyncTask<Account, Void, List<Person>> {

        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        @Override
        protected List<Person> doInBackground(Account... params) {
            try {
                GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                        MainActivity.this,
                        Collections.singleton(CONTACTS_SCOPE));
                credential.setSelectedAccount(params[0]);

                People service = new People.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                        .setApplicationName("Google Sign In Quickstart")
                        .build();

                ListConnectionsResponse connectionsResponse = service
                        .people()
                        .connections()
                        .list("people/me")
                        .setRequestMaskIncludeField("person.emailAddresses")
//                        .setRequestMaskIncludeField("person.names,person.emailAddresses,person.phoneNumbers")
                        .execute();

                return connectionsResponse.getConnections();
            } catch (UserRecoverableAuthIOException userRecoverableException) {
                Log.w(TAG, "getContacts:recoverable exception", userRecoverableException);
                startActivityForResult(userRecoverableException.getIntent(), RC_RECOVERABLE);
            } catch (IOException e) {
                Log.w(TAG, "getContacts:exception", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Person> connections) {
            hideProgressDialog();


            if (connections != null && connections.size() > 0) {
                for (Person person: connections){
                    Gson gson = new Gson();
                    String json = gson.toJson(person);
                    Log.e("person",json);
//                   Log.e("Email",person.getEmailAddresses().get(0).toString()) ;
//                    getPersonInfo(person);
                }
            } else {
                System.out.println("No connections found.");
            }

           /* if (connections != null) {
                Log.d(TAG, "getContacts:connections: size=" + connections.size());

                // Get names of all connections
                StringBuilder msg = new StringBuilder();
                for (int i = 0; i < connections.size(); i++) {
                    Person person = connections.get(i);
                    if (person.getNames() != null && person.getNames().size() > 0) {
                        msg.append(person.getNames().get(0).getDisplayName());

                        if (i < connections.size() - 1) {
                            msg.append(",");
                        }
                    }
                }

                // Display names
//                mDetailTextView.setText(getString(R.string.connections_fmt, msg.toString()));
            } else {
                Log.d(TAG, "getContacts:connections: null");
//                mDetailTextView.setText(getString(R.string.connections_fmt, "None"));
            }*/
        }
    }



/*

    */
/**
     * AsyncTask that uses the credentials from Google Sign In to access the People API.
     *//*

    private class GetContactsTask extends AsyncTask<Account, Void, List<Person>> {

        @Override
        protected void onPreExecute() {
            showProgressDialog();
        }

        @Override
        protected List<Person> doInBackground(Account... params) {
            try {
                GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                        MainActivity.this,
                        Collections.singleton(CONTACTS_SCOPE));
                credential.setSelectedAccount(params[0]);

                People service = new People.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                        .setApplicationName("Google Sign In Quickstart")
                        .build();

                ListConnectionsResponse connectionsResponse = service
                        .people()
                        .connections()
                        .list("people/me")
                        .execute();

                return connectionsResponse.getConnections();
            } catch (UserRecoverableAuthIOException userRecoverableException) {
                Log.w(TAG, "getContacts:recoverable exception", userRecoverableException);
                startActivityForResult(userRecoverableException.getIntent(), RC_RECOVERABLE);
            } catch (IOException e) {
                Log.w(TAG, "getContacts:exception", e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Person> connections) {
            hideProgressDialog();

            if (connections != null) {
                Log.d(TAG, "getContacts:connections: size=" + connections.size());

                // Get names of all connections
                StringBuilder msg = new StringBuilder();
                for (int i = 0; i < connections.size(); i++) {
                    Person person = connections.get(i);
                    Gson gson = new Gson();
                    String json = gson.toJson(person);
                    Log.e("person",json);
                    if (person.getNames() != null && person.getNames().size() > 0) {
                        msg.append(person.getNames().get(0).getDisplayName());
                        Log.e("name",person.getNames().get(0).getDisplayName());
//                        if(person.getEmailAddresses().size()!=0)
//                        Log.e("Emails",person.getEmailAddresses().get(0)+"");
                        if (i < connections.size() - 1) {
                            msg.append(",");
                        }
                    }
                }

                // Display names
//                mDetailTextView.setText(getString(R.string.connections_fmt, msg.toString()));
            } else {
                Log.d(TAG, "getContacts:connections: null");
//                mDetailTextView.setText(getString(R.string.connections_fmt, "None"));
            }
        }
    }
*/

   /* public static void printAllContacts(ContactsService myService)
            throws ServiceException, IOException {
        // Request the feed
        URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full");
        ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
        // Print the results
        System.out.println(resultFeed.getTitle().getPlainText());
        for (int i = 0; i < resultFeed.getEntries().size(); i++) {
            ContactEntry entry = resultFeed.getEntries().get(i);
            System.out.println("\t" + entry.getTitle().getPlainText());

            System.out.println("Email addresses:");
            for (Email email : entry.getEmailAddresses()) {
                System.out.print(" " + email.getAddress());
                if (email.getRel() != null) {
                    System.out.print(" rel:" + email.getRel());
                }
                if (email.getLabel() != null) {
                    System.out.print(" label:" + email.getLabel());
                }
                if (email.getPrimary()) {
                    System.out.print(" (primary) ");
                }
                System.out.print("\n");
            }

            System.out.println("IM addresses:");
            for (Im im : entry.getImAddresses()) {
                System.out.print(" " + im.getAddress());
                if (im.getLabel() != null) {
                    System.out.print(" label:" + im.getLabel());
                }
                if (im.getRel() != null) {
                    System.out.print(" rel:" + im.getRel());
                }
                if (im.getProtocol() != null) {
                    System.out.print(" protocol:" + im.getProtocol());
                }
                if (im.getPrimary()) {
                    System.out.print(" (primary) ");
                }
                System.out.print("\n");
            }

            System.out.println("Groups:");
            for (GroupMembershipInfo group : entry.getGroupMembershipInfos()) {
                String groupHref = group.getHref();
                System.out.println("  Id: " + groupHref);
            }

            System.out.println("Extended Properties:");
            for (ExtendedProperty property : entry.getExtendedProperties()) {
                if (property.getValue() != null) {
                    System.out.println("  " + property.getName() + "(value) = " +
                            property.getValue());
                } else if (property.getXmlBlob() != null) {
                    System.out.println("  " + property.getName() + "(xmlBlob)= " +
                            property.getXmlBlob().getBlob());
                }
            }

            String photoLink = entry.getContactPhotoLink().getHref();
            System.out.println("Photo Link: " + photoLink);

           *//* if (photoLink.getEtag() != null) {
                System.out.println("Contact Photo's ETag: " + photoLink.getEtag());
            }*//*

            System.out.println("Contact's ETag: " + entry.getEtag());
        }
    }

    private class TokenGet extends AsyncTask<Object, Object, Void> {
        private ProgressDialog pDialog;
        String Code;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Contacting Google ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
//            Code = pref.getString("Code", "");
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Object... args) {
            GetAccessToken jParser = new GetAccessToken();
            try {
                sAccessToken = GoogleAuthUtil.getToken(MainActivity.this, mAccount,
                        "oauth2:" + OAUTH_SCOPE);
                Log.e("sAccessToken",sAccessToken);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GoogleAuthException e) {
                e.printStackTrace();
            }
//            JSONObject json = jParser.gettoken(TOKEN_URL,CLIENT_ID,REDIRECT_URI,GRANT_TYPE);
//            Code,CLIENT_SECRET,
            return nu;
        }

        @Override
        protected void onPostExecute(Void json) {
            pDialog.dismiss();
            if (json != null){

                try {

                    final String tok = json.getString("access_token");
                    String expire = json.getString("expires_in");
                    String refresh = json.getString("refresh_token");

                    Log.d("Token Access", tok);
                    Log.d("Expire", expire);
                    Log.d("Refresh", refresh);
//                    auth.setText("Authenticated");

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
//                        myService.setUserCredentials(username, pwd);
                                GoogleOAuthParameters oauthParameters = new GoogleOAuthParameters();
                                oauthParameters.setOAuthConsumerKey(CLIENT_ID);
//                                oauthParameters.setOAuthConsumerSecret(CLIENT_SECRET);
                                oauthParameters.setOAuthToken(sAccessToken);
                                oauthParameters.setOAuthTokenSecret(sAccessToken);
                                myService.setOAuthCredentials(oauthParameters, new OAuthHmacSha1Signer());
                                printAllContacts(myService);
                            } catch (AuthenticationException e) {
                                e.printStackTrace();
                            } catch (ServiceException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (OAuthException e) {
                                e.printStackTrace();
                            }

                        }
                    }).start();

//                    Access.setText("Access Token:"+tok+"\nExpires:"+expire+"\nRefresh Token:"+refresh);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();
            }
        }
    }*/


}
