package com.hawk.plugin.rollbar;

import android.provider.Settings;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.rollbar.android.Rollbar;


@CapacitorPlugin(name = "CapacitorRollbar")
public class CapacitorRollbarPlugin extends Plugin {

    private Rollbar rollbar;

    @Override
    public void load() {
        super.load();

        // Retrieve the access token from capacitor.config.json
        String accessToken = getConfig().getString("accessToken");
        String environment = getConfig().getString("environment");
        Boolean includeLogCat = getConfig().getBoolean("includeLogcat", false);
        Rollbar.init(getContext(), accessToken, environment, true, includeLogCat);
        String uuid = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        rollbar = Rollbar.instance();
        rollbar.setPersonData(uuid, "", "");
    }


    @PluginMethod
    public void setPersonData(PluginCall call) {
        JSObject personData = call.getObject("person");
        if (personData != null) {
            String userId = personData.getString("id");
            String username = personData.getString("username");
            String email = personData.getString("email");
            rollbar.setPersonData(userId, email, username);
            call.resolve();
        }else{
            call.reject("Invalid person data provided.");
        }
    }

}
