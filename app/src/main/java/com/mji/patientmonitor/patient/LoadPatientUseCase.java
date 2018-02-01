package com.mji.patientmonitor.patient;

import com.mji.patientmonitor.common.domain.interactors.LoadGreetingUseCase;
import javax.inject.Inject;
import io.reactivex.Single;

class LoadPatientUseCase implements LoadGreetingUseCase {
    private final PatientRepository patientRepository;

    @Inject
    LoadPatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Single<String> execute() {
        return patientRepository.getPatientMessage();
    }

    public Single<String> getPatientText() {
        return patientRepository.getPatientText();
    }

    public Single<String> streamCamera() {
        return patientRepository.setCamera();
    }
}
