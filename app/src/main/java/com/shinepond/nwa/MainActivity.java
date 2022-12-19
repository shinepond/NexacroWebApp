package com.shinepond.nwa;

import android.annotation.SuppressLint;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nexacro.NexacroActivity;


public class MainActivity extends NexacroActivity {
    private final String LOG_TAG = this.getClass().getSimpleName();
    StandardObject standardObj = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Log.i(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
    }

    public void setPlugin(StandardObject obj) {
        standardObj = obj;
    }

    @Override
    @SuppressLint("NewApi")
    protected void onResume()
    {
        super.onResume();
        if(standardObj != null){
            //화면의 on_resume 이벤트 함수호출
            standardObj.send(
                    StandardObject.CODE_SUCCESS,
                    "화면 재활성화",
                    standardObj.getActionString("_onresume")
            );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /* TODO 권한 허용 시 */
            } else {
                /* 권한 거부 */
                Toast.makeText(this, "권한요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
