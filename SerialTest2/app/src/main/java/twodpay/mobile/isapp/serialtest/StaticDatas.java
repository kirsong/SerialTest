package twodpay.mobile.isapp.serialtest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android_serialport_api.SerialPort;

/**
 * Created by Developer on 2017-10-18.
 */

public class StaticDatas {
    private static StaticDatas instance;

    public static int BAUD_RATE = 38400;
    public static String COM1 = "/dev/ttyS0";
    public static String COM2 = "/dev/ttyS1";

    public static SerialPort mSerialPort;
    public static OutputStream mOutputStream;
    public static InputStream mInputStream;

    public SerialPort SerialPortUtil() {
        if (mSerialPort == null) {
            String path = COM1;
            int baudrate = BAUD_RATE;
            try {
                mSerialPort = new SerialPort(new File(path), baudrate, 0);
                mOutputStream = mSerialPort.getOutputStream();
                mInputStream = mSerialPort.getInputStream();
            } catch (IOException e) {

            }
        }
        return  mSerialPort;
    }

    public void sendDate(byte[] writeBytes)
    {
        try {
            if (mOutputStream != null) {
                mOutputStream.write(writeBytes);
            }
        } catch (IOException e) {

        }
    }

    public String readData() {
        String readDatas = null;
        for (int i = 0; i < 10; i++)
        {
            try {
                if (mInputStream != null) {
                    byte[] buffer = new byte[7];
                    int size = mInputStream.read(buffer);
                    if (size > 0)
                    {
                        //읽어드릴 응답이 있음
                        //아래에서 데이터 처리
                        readDatas = String.format("%02x", buffer);
                        break;
                    }else {
                        Thread.sleep(2000); //2초후 다시 버퍼를 검사한다.
                    }
                }
                if(i==9){
                    break;
                }
            } catch (IOException e) {

            } catch (InterruptedException e) {

            }
        }
        return readDatas;
    }

    public  void closeSerialPort() {
        if (mSerialPort != null)
        {
            mSerialPort.close();
            mSerialPort = null;
        }
    }



    public String getTime() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);
        return getTime;
    }


    public static int displayWidth = 0;
    public static int displayHeight = 0;

    public static byte[] DAOU_PAY_BYTEARRAY;

    public static String KICC_PAY_STRING;
    public static byte[] NICE_PAY_BYTEARRAY;

    public static int table_amt = 8;

    public static StaticDatas getInstance() {

        if (instance == null) {
            instance = new StaticDatas();
            return instance;
        } else {
            return instance;
        }
    }



}
