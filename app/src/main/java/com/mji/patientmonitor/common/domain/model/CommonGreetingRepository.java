package com.mji.patientmonitor.common.domain.model;

import io.reactivex.Single;

public class CommonGreetingRepository {
    public Single<String> getGreeting() {
        return Single.just("Hello from CommonGreetingRepository");
    }
}
