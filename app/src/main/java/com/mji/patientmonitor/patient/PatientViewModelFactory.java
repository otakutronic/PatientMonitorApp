package com.mji.patientmonitor.patient;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.mji.patientmonitor.common.domain.interactors.LoadCommonGreetingUseCase;
import com.mji.patientmonitor.rx.SchedulersFacade;

class PatientViewModelFactory implements ViewModelProvider.Factory {

    private final LoadCommonGreetingUseCase loadCommonGreetingUseCase;

    private final LoadPatientUseCase loadPatientUseCase;

    private final SchedulersFacade schedulersFacade;

    PatientViewModelFactory(LoadCommonGreetingUseCase loadCommonGreetingUseCase,
                            LoadPatientUseCase loadPatientUseCase,
                            SchedulersFacade schedulersFacade) {
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.loadPatientUseCase = loadPatientUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(PatientViewModel.class)) {
            return (T) new PatientViewModel(loadCommonGreetingUseCase, loadPatientUseCase, schedulersFacade);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
