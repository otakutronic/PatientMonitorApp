package com.mji.patientmonitor.di;

import android.content.Context;

import com.mji.patientmonitor.App;
import com.mji.patientmonitor.common.domain.model.CommonGreetingRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    CommonGreetingRepository provideCommonHelloService() {
        return new CommonGreetingRepository();
    }
}
