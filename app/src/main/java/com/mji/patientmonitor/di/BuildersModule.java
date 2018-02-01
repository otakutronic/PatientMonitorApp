package com.mji.patientmonitor.di;

import com.mji.patientmonitor.patient.PatientActivity;
import com.mji.patientmonitor.patient.PatientModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Binds all sub-components within the app.
 */
@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector(modules = PatientModule.class)
    abstract PatientActivity bindLobbyActivity();

    // Add bindings for other sub-components here
}
