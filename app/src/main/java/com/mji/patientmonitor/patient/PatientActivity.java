package com.mji.patientmonitor.patient;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.mji.patientmonitor.R;
import com.mji.patientmonitor.common.viewmodel.Response;

import org.webrtc.EglBase;
import org.webrtc.MediaStream;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoTrack;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import timber.log.Timber;

public class PatientActivity extends AppCompatActivity {

    @Inject
    PatientViewModelFactory viewModelFactory;

    @BindView(R.id.patient_feedback_textview)
    TextView jokeTextView;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    @BindView(R.id.remote_gl_surface_view)
    SurfaceViewRenderer remoteVideoView;

    private PatientViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("tag", "on create!!!!!");

        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_activity);

        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PatientViewModel.class);
        viewModel.response().observe(this, response -> processResponse(response));

        initViews();
    }

    @OnClick(R.id.cam_button)
    void onCamButtonPressed() {

        viewModel.loadPatientFeedbackText();

        viewModel.setCamera();

        //viewModel.getCameraFeed();

        Log.d("tag", "cam button pressed");

    }

    public void initViews() {
        remoteVideoView.setMirror(false);
        EglBase rootEglBase = EglBase.create();
        remoteVideoView.init(rootEglBase.getEglBaseContext(), null);
        remoteVideoView.setZOrderMediaOverlay(true);
    }

    private void gotRemoteStream(MediaStream stream) {
        final VideoTrack videoTrack = stream.videoTracks.getFirst();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    ////remoteRenderer = new VideoRenderer(remoteVideoView);
                    remoteVideoView.setVisibility(View.VISIBLE);
                    ////videoTrack.addRenderer(remoteRenderer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void processResponse(Response response) {
        switch (response.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState(response.data);
                break;

            case ERROR:
                renderErrorState(response.error);
                break;
        }
    }

    private void renderLoadingState() {
        jokeTextView.setVisibility(View.GONE);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    private void renderDataState(String greeting) {
        loadingIndicator.setVisibility(View.GONE);
        jokeTextView.setVisibility(View.VISIBLE);
        jokeTextView.setText(greeting);
    }

    private void renderErrorState(Throwable throwable) {
        Timber.e(throwable);
        loadingIndicator.setVisibility(View.GONE);
        jokeTextView.setVisibility(View.GONE);
        Toast.makeText(this, R.string.joke_error, Toast.LENGTH_SHORT).show();
    }
}
