package be.lsinf1225.minipoll.Classes;

import android.app.Application;

import static java.security.AccessController.getContext;


public class MiniPollApp extends Application
{

    private static MiniPollApp context;


    public static MiniPollApp  getContext()
    {
        return context;
    }

    public void onCreate() {
        super.onCreate();
        context = (MiniPollApp) getApplicationContext();
    }
}
