package tat.mukhutdinov.challenger.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Status.RESULT_INTERNAL_ERROR
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance
import tat.mukhutdinov.challenger.R
import tat.mukhutdinov.challenger.auth.domain.boundary.AuthDomain
import tat.mukhutdinov.challenger.databinding.AuthBinding
import tat.mukhutdinov.challenger.infrastructure.structure.ui.BaseViewModel
import timber.log.Timber


class AuthViewModel : BaseViewModel<AuthBinding>(), AuthBindings {

    override val layoutId: Int = R.layout.auth

    private val domain: AuthDomain by instance()

    private val googleSingInOptions: GoogleSignInOptions by instance()

    private lateinit var googleSignIn: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        googleSignIn = GoogleSignIn.getClient(requireContext(), googleSingInOptions)
    }

    override fun onStart() {
        super.onStart()

        googleSignIn.silentSignIn().addOnCompleteListener(requireActivity()) {
            handleGoogleSignInResult(it)
        }
    }

    override fun onGoogleSignInClicked() {
        startActivityForResult(googleSignIn.signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            // The Task returned from this call is always completed, no need to attach a listener
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
                ?: throw ApiException(RESULT_INTERNAL_ERROR)

            auth(account.idToken.orEmpty())
        } catch (e: ApiException) {
            Timber.w(e)

            Toast.makeText(context, "Failed to sign in", LENGTH_SHORT).show()
        }
    }

    private fun auth(token: String) {
        fragmentScope.launch {
            domain.auth(token)

            val a = 5
        }
    }

    companion object {
        private const val GOOGLE_SIGN_IN_REQUEST_CODE = 101
    }
}