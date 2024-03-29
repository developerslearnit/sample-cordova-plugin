package com.sample.cordova.mobile.sdk;

import android.content.Context;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class samplecordovapreference extends CordovaPlugin {

    SharedPreferences pref;
    Editor editor;

    // Context
    Context _context;
    protected CallbackContext context;

    int INIT_PREF = 0;

    public samplecordovapreference(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences("InitPref", INIT_PREF);
        editor = pref.edit();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        try {
            if (action.equals("set")) {
                String prefKey = args.getString(0);
                String prefVal = args.getString(1);
                this.set(prefKey, prefVal, callbackContext);
                return true;
            } else if (action.equals("get")) {
                String prefKey = args.getString(0);
                this.get(prefKey, callbackContext);
                return true;
            }
            return false;
        } catch (Exception e) {

            handleError(e.getMessage());
            return false;
        }
    }

    private void set(String _key, String _val, CallbackContext callbackContext) {
        if (_key != null && _val != null) {
            editor.putString(_key, _val);
            editor.commit();
            // JSONObject successObj = new JSONObject();
            // successObj.put(_key, _val);
            callbackContext.success(_val);
        } else {
            callbackContext.error("Expected two integer arguments.");
        }

    }

    private void get(String _key, CallbackContext callbackContext) {

        if (_key != null) {

            // JSONObject successObj = new JSONObject();
            // successObj.put(_key, pref.getString(_key, null));
            callbackContext.success(pref.getString(_key, null));
        } else {
            callbackContext.error("Expected two integer arguments.");
        }

    }

    protected void handleError(String errorMsg) {
        try {
            JSONObject error = new JSONObject();
            error.put("error", errorMsg);
            context.error(error);

        } catch (JSONException e) {

        }
    }
}
