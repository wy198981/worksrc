package com.example.administrator.myparkingos.httpUtil;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.logging.Level;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017-04-17.
 */
public enum OkGoUtil
{
    INSTATNCE;

    private int TIME_SENCONDS = 3; // 3秒
    private static final String TAG = "OkGoUtil";

    private OkGoUtil()
    {

    }

    /**
     * 全局参数的设置
     *
     * @param context
     */
    public void init(Application context)
    {
        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");

        OkGo.init(context);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try
        {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()

                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.INFO, true)
                    //如果使用默认的 60秒,以下三行也不需要传
//                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
//                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
//                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    .setConnectTimeout(TIME_SENCONDS)  //全局的连接超时时间
                    .setReadTimeOut(TIME_SENCONDS)     //全局的读取超时时间
                    .setWriteTimeOut(TIME_SENCONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)

                    //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates()                                  //方法一：信任所有证书,不安全有风险
//              .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
//              .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
//              //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//               .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//

                    //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//               .setHostnameVerifier(new SafeHostnameVerifier())

                    //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                    //这两行同上，不需要就不要加入
                    .addCommonHeaders(headers)  //设置全局公共头
                    .addCommonParams(params);   //设置全局公共参数

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 请求String的字符串
     *
     * @param url
     * @param callBack
     */
    public void requsetStringGET(Context context, String url, final HttpCallBackForString callBack)
    {
        OkGo.get(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback()
                {
                    @Override
                    public void onSuccess(String s, Call call, Response response)
                    {
                        if (callBack != null)
                        {
                            callBack.onSuccess(s, call, response);
                        }
                    }
                });

    }

    /**
     * 请求Bitmap的图像
     *
     * @param url
     * @param callBack
     */
    public void requestBitmapGet(Context context, String url, final HttpCallBackForBitmap callBack)
    {
        OkGo.get(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onSuccess(Bitmap bitmap, Call call, Response response)
                    {
                        if (callBack != null)
                        {
                            callBack.onSuccess(bitmap, call, response);
                        }
                    }
                });
    }

    /**
     * 下载文件
     *
     * @param url
     * @param callBack
     */
    public void requestFileGet(Context context, String url, final HttpCallBackForFile callBack)
    {
        OkGo.get(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new FileCallback()
                {
                    @Override
                    public void onSuccess(File file, Call call, Response response)
                    {
                        if (callBack != null)
                        {
                            callBack.onSuccess(file, call, response);
                        }
                    }
                });
    }

    public void cancelAllTask()
    {
        OkGo.getInstance().cancelAll();
    }

    // 将Bitmap转换成File，那么上传文件
    public void uploadFilePost(Context context, String url, String fileName, final HttpCallBackForFile callBack)
    {
        OkGo.post(url)//
                .tag(this)//
                .isMultipart(true)       // 强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .params("file1", new File(fileName))   // 可以添加文件上传
                .execute(new StringCallback()
                {
                    @Override
                    public void onSuccess(String s, Call call, Response response)
                    {
                        //上传成功
                        Log.i(TAG, "上传的返回" + s);
                    }


                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed)
                    {
                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
                        Log.i(TAG, "currentSize:" + currentSize + ",totalSize:" + totalSize + ",progress:" + progress);
                    }
                });
    }
}
