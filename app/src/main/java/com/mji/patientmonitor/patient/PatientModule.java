package com.mji.patientmonitor.patient;

import com.mji.patientmonitor.common.domain.interactors.LoadCommonGreetingUseCase;
import com.mji.patientmonitor.rx.SchedulersFacade;

import dagger.Module;
import dagger.Provides;

/**
 * Define PatientActivity-specific dependencies here.
 */
@Module
public class PatientModule {

    @Provides
    PatientRepository provideLobbyGreetingRepository() {
        return new PatientRepository();
    }

    @Provides
    PatientViewModelFactory provideLobbyViewModelFactory(LoadCommonGreetingUseCase loadCommonGreetingUseCase,
                                                         LoadPatientUseCase loadPatientUseCase,
                                                         SchedulersFacade schedulersFacade) {
        return new PatientViewModelFactory(loadCommonGreetingUseCase, loadPatientUseCase, schedulersFacade);
    }
}
