package tat.mukhutdinov.challenger.auth.di

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton


object AuthModule {

    val module = Kodein.Module(AuthModule::class.java.name) {

        bind<GoogleSignInOptions>() with singleton {
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("504859177887-e8uvuajf9u2dpl2o0ne887jp2gar0o4j.apps.googleusercontent.com")
                .requestEmail()
                .build()
        }
    }
}