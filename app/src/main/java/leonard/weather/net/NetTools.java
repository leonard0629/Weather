package leonard.weather.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import leonard.weather.BuildConfig;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class NetTools {

    private static final String TAG = NetTools.class.getSimpleName();

    private static NetTools mInstance;
    private static Retrofit mRetrofit;
    private int netState;

    private static ApiService apiService;

    public static NetTools getInstance() {
        if (mInstance == null) {
            synchronized (NetTools.class) {
                mInstance = new NetTools();
            }
        }

        return mInstance;
    }

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = NetTools.getInstance().getRetrofit().create(ApiService.class);
        }
        return apiService;
    }

    private NetTools() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(new ApiLoggingInterceptor().setLevel(ApiLoggingInterceptor.Level.BODY))
                .connectTimeout(NetConfig.TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(NetConfig.TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
        ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mRetrofit = new Retrofit.Builder().addConverterFactory(new JsonAndXmlConverters.QualifiedTypeConverterFactory(
                        JacksonConverterFactory.create(mapper), SimpleXmlConverterFactory.create()))
                .baseUrl(BuildConfig.API_HOST).client(client).build();
        netState = NetConfig.NET_UNKNOWN;
    }

    private Retrofit getRetrofit() {
        return mRetrofit;
    }

    /**
     * 网络是否连接
     * @param context 会话上下文
     * @return
     */
    public boolean isNetworkEnabled(Context context) {
        return getNetworkState(context) != NetConfig.NET_NONE;
    }

    /**
     * 获取网络状态
     * @param context 会话上下文
     * @return 获取网络状态
     */
    private int getNetworkState(Context context) {
        if (netState == NetConfig.NET_UNKNOWN) {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo == null) {
                netState = NetConfig.NET_NONE;
                return netState;
            }
            int type = networkInfo.getType();
            if (type == ConnectivityManager.TYPE_MOBILE) {
                if (networkInfo.isAvailable()) {
                    netState = NetConfig.NET_MOBILE;
                } else {
                    netState = NetConfig.NET_NONE;
                }

            } else if (type == ConnectivityManager.TYPE_WIFI) {
                if (networkInfo.isAvailable()) {
                    netState = NetConfig.NET_WIFI;
                } else {
                    netState = NetConfig.NET_NONE;
                }
            }
        }
        return netState;
    }

    /**
     * 重置网络状态
     */
    public void resetNetState() {
        netState = NetConfig.NET_UNKNOWN;
    }


    /**
     * 获取ip地址
     * @return ip地址
     */
    public static String getIpAddress() {
        String ip = "";
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            Log.e(TAG, "get local ip address error: " + e.getMessage());
        }

        return ip;
    }
}
