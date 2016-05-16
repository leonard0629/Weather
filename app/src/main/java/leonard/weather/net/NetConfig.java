package leonard.weather.net;

/**
 * 网络请求配置工具类
 * Created by leonard on 2016/04/03.
 */
public class NetConfig {
    public static final long TIMEOUT = 20000;          //请求超时时间

    /**
     * 网络状态
     */
    public static final int NET_WIFI = 1;              //wifi
    public static final int NET_MOBILE = 2;            //移动数据
    public static final int NET_UNKNOWN = 3;           //未知
    public static final int NET_NONE = 0;              //无网络

    public static final String HEADER_TOKEN = "token";              //请求头中的token
    public static final String HEADER_TOKEN_PROP_UID = "uid";       //请求头中的token的用户id
    public static final String HEADER_TOKEN_PROP_CODE = "code";     //请求头中的token的用户验证码

    public static final String REQUEST_PARAM = "jsonStr";           //请求参数
}
