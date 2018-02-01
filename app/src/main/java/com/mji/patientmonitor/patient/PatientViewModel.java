package com.mji.patientmonitor.patient;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mji.patientmonitor.common.domain.interactors.LoadCommonGreetingUseCase;
import com.mji.patientmonitor.common.viewmodel.Response;
import com.mji.patientmonitor.rx.SchedulersFacade;

import io.reactivex.disposables.CompositeDisposable;

class PatientViewModel extends ViewModel {

    private final LoadCommonGreetingUseCase loadCommonGreetingUseCase;

    private final LoadPatientUseCase loadPatientUseCase;

    private final SchedulersFacade schedulersFacade;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<Response> response = new MutableLiveData<>();

    PatientViewModel(LoadCommonGreetingUseCase loadCommonGreetingUseCase,
                     LoadPatientUseCase loadPatientUseCase,
                     SchedulersFacade schedulersFacade) {
        this.loadCommonGreetingUseCase = loadCommonGreetingUseCase;
        this.loadPatientUseCase = loadPatientUseCase;
        this.schedulersFacade = schedulersFacade;
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

    void loadPatientFeedbackText() {
        loadText(loadPatientUseCase);
    }

    void setCamera() {

        Log.d("tag", "setting patients cam");

        setCameraStream(loadPatientUseCase);
    }

    void getCameraFeed() {
        getCameraStream(loadPatientUseCase);
    }

    MutableLiveData<Response> response() {
        return response;
    }

    private void loadText(LoadPatientUseCase loadPatientUsecase) {

        Log.d("tag", "load patient text");

        disposables.add(loadPatientUsecase.getPatientText()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(Response.loading()))
                .subscribe(
                        greeting -> response.setValue(Response.success(greeting)),
                        throwable -> response.setValue(Response.error(throwable))
                )
        );
    }

    private void setCameraStream(LoadPatientUseCase loadPatientUsecase) {

        Log.d("tag", "stream patients cam");

        disposables.add(loadPatientUsecase.streamCamera()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(Response.loading()))
                .subscribe(
                        greeting -> response.setValue(Response.success(greeting)),
                        throwable -> response.setValue(Response.error(throwable))
                )
        );
    }

    private void getCameraStream(LoadPatientUseCase loadPatientUsecase) {

        Log.d("tag", "stream patients cam");

        disposables.add(loadPatientUsecase.streamCamera()
                .subscribeOn(schedulersFacade.io())
                .observeOn(schedulersFacade.ui())
                .doOnSubscribe(__ -> response.setValue(Response.loading()))
                .subscribe(
                        greeting -> response.setValue(Response.success(greeting)),
                        throwable -> response.setValue(Response.error(throwable))
                )
        );
    }
}
