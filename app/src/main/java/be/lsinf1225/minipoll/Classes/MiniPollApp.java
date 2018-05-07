package be.lsinf1225.minipoll.Classes;

import android.app.Application;

public class MiniPollApp {

    public class MiniPollApp extends Application
    {

        private static MiniPollApp context;


        public static  getContext()
        {
            return context;
        }

    }
}
