package me.guillem.labhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver ConnectivityReciver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityReciver = new ConnectivityReciver();
        broadcastIntent();

        Button showtoast = findViewById(R.id.btn_show_toast);
        showtoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });
    }

    private void showToast() {
        View toastView = getLayoutInflater().inflate(R.layout.layout_toast,
                findViewById(R.id.container_layout));
        Toast toast=new Toast(this);
        toast.setView(toastView);
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.show();
    }

    public void broadcastIntent() {
        registerReceiver(ConnectivityReciver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(ConnectivityReciver);
    }

}