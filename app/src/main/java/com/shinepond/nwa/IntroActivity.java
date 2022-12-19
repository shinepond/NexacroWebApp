package com.shinepond.nwa;

import com.nexacro.NexacroUpdatorActivity;

import android.content.Intent;
import android.os.Bundle;

/**
 * Class Name  : IntroActivity
 * Description : 넥사크로 화면소스 패킹파일의 경로를 설정하여 다운로드 및 버전 업데이트 하기 위한 Class
 */
public class IntroActivity extends NexacroUpdatorActivity {

    public IntroActivity() {
        super();
        /* Deployed File Path */
        setBootstrapURL( BuildConfig.HOST + "/_android_/start_android.json");
        setProjectURL( BuildConfig.HOST + "/_android_/");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if(intent != null) {
            String bootstrapURL = intent.getStringExtra("bootstrapURL");
            String projectUrl = intent.getStringExtra("projectUrl");
            if(bootstrapURL != null) {
                setBootstrapURL(bootstrapURL);
                setProjectURL(projectUrl);
            }
        }

        super.onCreate(savedInstanceState);

        if (!isTaskRoot()) {
            intent = getIntent();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(intent.getAction())) {
                startRuntime();
                finish();
            }
        }
    }

}