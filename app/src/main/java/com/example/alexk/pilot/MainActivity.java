package com.example.alexk.pilot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {

    int pitch = 1500;
    int roll = 1500;
    int yaw = 1500;
    int throttle = 100;
    int aux1 = 1000;
    int aux2 = 1000;
    int aux3 = 1000;
    int aux4 = 1000;
    int[] dataArray = {roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4};
    Socket s;
    final static String TAG = "Pilot";
    Thread netThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final JoystickView lj = findViewById(R.id.LeftJoystick);
        final JoystickView rj = findViewById(R.id.RightJoistick);
        SeekBar saux1 = findViewById(R.id.aux1);
        SeekBar saux2 = findViewById(R.id.aux2);
        SeekBar saux3 = findViewById(R.id.aux3);
        SeekBar saux4 = findViewById(R.id.aux4);
        final TextView info = findViewById(R.id.info);
        Button cbtn = findViewById(R.id.conBnt);
        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                netThread.start();
            }
        });
        lj.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                int x = (lj.getNormalizedX() + 1) * 10 + 1000;
                int y = -(lj.getNormalizedY() + 1) * 10 + 2000;
                if (x > 2000) x = 2000;
                if (y > 2000) y = 2000;
                x -= 10;
                y += 10;
                throttle = y;
                yaw = x;
                dataArray = new int[]{roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4};
                info.setText("Roll: " + dataArray[0] + " Pitch: " + dataArray[1] + " Yaw: " + dataArray[2] + " Throttle " + dataArray[3] + " AUX1: " + dataArray[4] + " AUX2: " + dataArray[5] + " AUX3: " + dataArray[6] + " AUX4: " + dataArray[7]);
            }
        });
        rj.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                int x = (rj.getNormalizedX() + 1) * 10 + 1000;
                int y = -(rj.getNormalizedY() + 1) * 10 + 2000;
                if (x > 2000) x = 2000;
                if (y > 2000) y = 2000;
                x -= 10;
                y += 10;
                pitch = y;
                roll = x;
                dataArray = new int[]{roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4};
                info.setText("Roll: " + dataArray[0] + " Pitch: " + dataArray[1] + " Yaw: " + dataArray[2] + " Throttle " + dataArray[3] + " AUX1: " + dataArray[4] + " AUX2: " + dataArray[5] + " AUX3: " + dataArray[6] + " AUX4: " + dataArray[7]);
            }
        });
        saux1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aux1 = progress + 1000;
                dataArray = new int[]{roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4};
                info.setText("Roll: " + dataArray[0] + " Pitch: " + dataArray[1] + " Yaw: " + dataArray[2] + " Throttle " + dataArray[3] + " AUX1: " + dataArray[4] + " AUX2: " + dataArray[5] + " AUX3: " + dataArray[6] + " AUX4: " + dataArray[7]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        saux2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aux2 = progress + 1000;
                dataArray = new int[]{roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4};
                info.setText("Roll: " + dataArray[0] + " Pitch: " + dataArray[1] + " Yaw: " + dataArray[2] + " Throttle " + dataArray[3] + " AUX1: " + dataArray[4] + " AUX2: " + dataArray[5] + " AUX3: " + dataArray[6] + " AUX4: " + dataArray[7]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        saux3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aux3 = progress + 1000;
                dataArray = new int[]{roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4};
                info.setText("Roll: " + dataArray[0] + " Pitch: " + dataArray[1] + " Yaw: " + dataArray[2] + " Throttle " + dataArray[3] + " AUX1: " + dataArray[4] + " AUX2: " + dataArray[5] + " AUX3: " + dataArray[6] + " AUX4: " + dataArray[7]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        saux4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                aux4 = progress + 1000;
                dataArray = new int[]{roll, pitch, yaw, throttle, aux1, aux2, aux3, aux4};
                info.setText("Roll: " + dataArray[0] + " Pitch: " + dataArray[1] + " Yaw: " + dataArray[2] + " Throttle " + dataArray[3] + " AUX1: " + dataArray[4] + " AUX2: " + dataArray[5] + " AUX3: " + dataArray[6] + " AUX4: " + dataArray[7]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        netThread = new Thread(new Runnable() {
            @Override
            public void run() {
                PrintWriter out = null;
                try {
                    s = new Socket(InetAddress.getByName("192.168.31.175"), 2366);
                    s.setKeepAlive(true);
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
                } catch (SocketException e) {
                    Log.d(TAG, e.toString());
                } catch (UnknownHostException e) {
                    Log.d(TAG, e.toString());
                } catch (IOException e) {
                    Log.d(TAG, e.toString());
                }
                while (s.isConnected()) {
                    out.println("Roll: " + dataArray[0] + " Pitch: " + dataArray[1] + " Yaw: " + dataArray[2] + " Throttle " + dataArray[3] + " AUX1: " + dataArray[4] + " AUX2: " + dataArray[5] + " AUX3: " + dataArray[6] + " AUX4: " + dataArray[7]);
                }
            }
        });
    }
}
