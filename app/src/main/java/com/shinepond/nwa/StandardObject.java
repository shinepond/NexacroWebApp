package com.shinepond.nwa;

import com.nexacro.NexacroActivity;
import com.nexacro.plugin.NexacroPlugin;
import com.nexacro.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Name  : StandardObject
 * Description : NexacroPlugin 상속받아 앱과 nexacro 화면의 데이터를 주고 받기위해 재정의한 공통 Class
 */
public class StandardObject extends NexacroPlugin {
    private final String LOG_TAG = this.getClass().getSimpleName();

    /* nexacro 로 반환될 이벤트 속성명*/
    private static final String SERVICE_ID    = "serviceId";
    private static final String REASON   = "reason";
    private static final String RETURN_VALUE   = "returnValue";

    /* 처리 결과 코드 */
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_ERROR  = -1;
    public static final int CODE_PERMISSION_ERROR  = -9;

    /* 처리후 nexacro 에서 호출될 이벤트명 (고정) */
    private static final String ON_CALLBACK = "_oncallback";
    private static final String ON_RESUME = "_onresume";
    private static final String ON_PERMISSION_RESULT = "_onpermissionresult";

    /* nexacro 에서 Android 호출한 메소드명 */
    private static final String METHOD_CALLMETHOD = "callMethod";

    /* nexacro 에서 callMethod 한 정보 */
    private String mServiceId = "";
    private JSONObject mParamData = null;

    public StandardObject(String objectId) {
        super(objectId);

        //현재 실행된 nexacroActivity 의 Instance
        MainActivity nexacroObj = (MainActivity) NexacroActivity.getInstance();

        //현재 Class 를 MainActivity 에서 참조할 수 있도록 셋팅
        nexacroObj.setPlugin(this);
    }

    /**
     * Nexacro 화면으로 데이터 반환을 위한 메소드
     * @param reason 화면으로 넘길 반환코드 (CODE_SUCCES, CODE_ERROR, CODE_PERMISSION_ERROR)
     * @param retVal 화면으로 넘길 데이터
     * @param action 화면에서 호출될 이벤트 (ON_CALLBACK, ON_RESUME, ON_PERMISSION_RESULT)
     */
    public void send(int reason, Object retVal, String action)
    {
        JSONObject obj = new JSONObject();

        try {
            obj.put(SERVICE_ID, mServiceId);
            obj.put(REASON, reason);
            obj.put(RETURN_VALUE, retVal);

            callback(action, obj);

        } catch(Exception e) {
            e.printStackTrace();

        } finally {
            mServiceId = null;
            mParamData = null;
        }

    }

    /* 예약된 이벤트명을 반환 */
    public String getActionString (String action)
    {
        switch (action) {
            case ON_CALLBACK:
                return ON_CALLBACK;
            case ON_RESUME:
                return ON_RESUME;
            case ON_PERMISSION_RESULT:
                return ON_PERMISSION_RESULT;
            case METHOD_CALLMETHOD:
                return METHOD_CALLMETHOD;
            default:
                return "";
        }
    }

    @Override
    public void init(JSONObject paramObject) {
        Log.d(LOG_TAG,"init");
    }

    @Override
    public void release(JSONObject paramObject) {
        Log.d(LOG_TAG,"release");
    }

    @Override
    public void execute(String method, JSONObject paramObject) {
        Log.d(LOG_TAG,"execute");

        String serviceId = "";

        try {
            serviceId = paramObject.getJSONObject("params").getString(SERVICE_ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mServiceId = serviceId;

        switch (serviceId) {
            case "serviceId":
                /* TODO 해당 service id 로 진입 시 동작 */
                break;
        }
    }
}
