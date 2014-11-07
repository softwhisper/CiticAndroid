package curso.and21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalWordService extends Service {

	private final IBinder mBinder = new MyBinder();
	private ArrayList<String> list = new ArrayList<String>();
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("SERVICE", "onStartCommand");
		
		Random rnd = new Random();
		if (rnd.nextBoolean()) {
			list.add("Softwhisper");
		}
		if (rnd.nextBoolean()) {
			list.add("Citic");
		}		
		if (rnd.nextBoolean()) {
			list.add("Android");
		}		
		
		return Service.START_NOT_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.d("SERVICE", "BINDER");
		return mBinder;
	}

	public class MyBinder extends Binder {
		LocalWordService getService() {
			Log.d("SERVICE", "returnService()");
			return LocalWordService.this;		
		}
	}

	public List<String> getWordList() {
		Log.d("SERVICE", "getWordList()");
		return list;
	}

}
