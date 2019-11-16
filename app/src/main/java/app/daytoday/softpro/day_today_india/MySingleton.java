package app.daytoday.softpro.day_today_india;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    public static MySingleton mInstance;
    private static Context mContext;
    public RequestQueue requestQueue;

    public MySingleton(Context context) {
        mContext = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        return requestQueue;
    }

    public void addToRequestQueue(Request request) {
        requestQueue.add(request);
    }
}
