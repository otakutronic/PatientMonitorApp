package com.mji.patientmonitor.patient;

import io.reactivex.Single;


/**
 * repositories expose their data as streams using RxJava 2 API.
 * While overkill for a simple synchronous code like this, a typical
 * Model is much more involved and utilizes asynchronous calls.
 * RxJava is useful for reactive programming
 */
class PatientRepository {

    //private Webservice webservice;

    Single<String> getPatientMessage() {

        return Single.just("i am a patient");
    }

    Single<String> getPatientText() {
        return Single.just("getting patient text");
    }

    Single<String> setCamera() {

        return Single.just("setting patient cam");

    }

    Single<String> getCamFeed() {
        return Single.just("getting patient cam");
    }
}
