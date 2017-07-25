package com.example.user.liangzi;

import com.google.gson.Gson;

import org.apache.http.entity.StringEntity;

import java.util.Map;
import java.util.Set;

/**
 * 统一的http提交工具
 * Created by User on 2015/5/26.
 */
public class HttpUtils {

    private static final String CONTENTTYPE = "application/x-www-form-urlencoded";
    private static final String JSON_TYPE = "application/json;charset=UTF-8";

    /*
                  统一的http提交工具
     */
//    public static void UniteHttp(String url, Map<String, String> conMap, final HttpUtilsCallBack httpUtilsCallBack) {
//
//        try {
//            com.lidroid.xutils.HttpUtils http = new com.lidroid.xutils.HttpUtils(1000 * 60);
//            RequestParams params = new RequestParams();
//
//            Gson gson = new Gson();
//            params.setBodyEntity(new StringEntity(gson.toJson(conMap), "UTF-8"));
//            params.setContentType(CONTENTTYPE);
//            Set<String> set = conMap.keySet();
//            String _url = "";
//            for (String _key : set) {
//                if (!StringUtils.isNullOrEmpty(conMap.get(_key))) {
//                    _url += "&" + _key + "=" + conMap.get(_key);
//                }
//            }
//            if (_url.startsWith("&")) {
//                url += _url.replaceFirst("&", "?");
//            }
//            http.configResponseTextCharset("GBK");
//            http.send(HttpRequest.HttpMethod.POST, url,
//                    params, new RequestCallBack<String>() {
//                        @Override
//                        public void onStart() {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.startCallBack();
//                        }
//
//                        public void onLoading(long total, long current, boolean isUploading) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.loadingCallBack(total, current, isUploading);
//                        }
//
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.successCallBack(responseInfo);
//                        }
//
//                        @Override
//                        public void onFailure(HttpException error, String msg) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.failCallBack(error, msg);
//                        }
//                    });
//        } catch (Exception e) {
////            CustomerLogUtils.e(e.getMessage());
//            e.printStackTrace();
//        }
//    }


    /**
     * 请求des3加密传字符串
     *
     * @param url
     * @param httpUtilsCallBack
     */
//    public static void HttpUtilHeader(RequestParams params,String url, final HttpUtilsCallBack httpUtilsCallBack) {
//
//        try {
//            com.lidroid.xutils.HttpUtils http = new com.lidroid.xutils.HttpUtils(1000 * 60);
//            http.configResponseTextCharset("UTF-8");
//            http.send(HttpRequest.HttpMethod.POST, url,
//                    params, new RequestCallBack<String>() {
//                        @Override
//                        public void onStart() {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.startCallBack();
//                        }
//
//                        public void onLoading(long total, long current, boolean isUploading) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.loadingCallBack(total, current, isUploading);
//                        }
//
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.successCallBack(responseInfo);
//
//                        }
//
//                        @Override
//                        public void onFailure(HttpException error, String msg) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.failCallBack(error, msg);
//                        }
//                    });
//        } catch (Exception e) {
////            CustomerLogUtils.e(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    /**
     * 请求des3加密传字符串
     *
     * @param url
     * @param des3
     * @param httpUtilsCallBack
     */
//    public static void HttpUtil(String url, String des3, final HttpUtilsCallBack httpUtilsCallBack) {
//
//        try {
//            com.lidroid.xutils.HttpUtils http = new com.lidroid.xutils.HttpUtils(1000 * 60);
//            RequestParams params = new RequestParams();
//            params.addBodyParameter("record", des3);
//            params.setContentType(CONTENTTYPE);
//            http.configResponseTextCharset("UTF-8");
//            http.send(HttpRequest.HttpMethod.POST, url,
//                    params, new RequestCallBack<String>() {
//                        @Override
//                        public void onStart() {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.startCallBack();
//                        }
//
//                        public void onLoading(long total, long current, boolean isUploading) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.loadingCallBack(total, current, isUploading);
//                        }
//
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.successCallBack(responseInfo);
//
//                        }
//
//                        @Override
//                        public void onFailure(HttpException error, String msg) {
//                            if (httpUtilsCallBack != null)
//                                httpUtilsCallBack.failCallBack(error, msg);
//                        }
//                    });
//        } catch (Exception e) {
////            CustomerLogUtils.e(e.getMessage());
//            e.printStackTrace();
//        }
//    }


    /*
 统一的http提交工具
  */
//    public static void UploadUniteHttp(String url, RequestParams params, final HttpUtilsCallBack httpUtilsCallBack) {
//
//        try {
//            com.lidroid.xutils.HttpUtils http = new com.lidroid.xutils.HttpUtils(1000 * 60);
//
//            http.send(HttpRequest.HttpMethod.POST, url,
//                    params, new RequestCallBack<String>() {
//                        @Override
//                        public void onStart() {
//                            httpUtilsCallBack.startCallBack();
//                        }
//
//                        public void onLoading(long total, long current, boolean isUploading) {
//                            httpUtilsCallBack.loadingCallBack(total, current, isUploading);
//                        }
//
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            httpUtilsCallBack.successCallBack(responseInfo);
//                        }
//
//                        @Override
//                        public void onFailure(HttpException error, String msg) {
//                            httpUtilsCallBack.failCallBack(error, msg);
//                        }
//                    });
//        } catch (Exception e) {
////            CustomerLogUtils.e(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    /**
     * 统一的http提交工具
     */
//    public static void UploadUniteHttp(String url, String juid, String tokenKey, String tokenValue, MultipartEntity entity, final HttpUtilsCallBack httpUtilsCallBack) {
//        RequestParams params = new RequestParams();
//        try {
//            com.lidroid.xutils.HttpUtils http = new com.lidroid.xutils.HttpUtils(1000 * 60);
//            params.setHeader("juid",juid);
//            params.setHeader(tokenKey,tokenValue);
//            params.setBodyEntity(entity);
//            http.configResponseTextCharset("UTF-8");
//            http.send(HttpRequest.HttpMethod.POST, url,
//                    params, new RequestCallBack<String>() {
//                        @Override
//                        public void onStart() {
//                            httpUtilsCallBack.startCallBack();
//                        }
//
//                        public void onLoading(long total, long current, boolean isUploading) {
//                            httpUtilsCallBack.loadingCallBack(total, current, isUploading);
//                        }
//
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            httpUtilsCallBack.successCallBack(responseInfo);
//                        }
//
//                        @Override
//                        public void onFailure(HttpException error, String msg) {
//                            httpUtilsCallBack.failCallBack(error, msg);
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
