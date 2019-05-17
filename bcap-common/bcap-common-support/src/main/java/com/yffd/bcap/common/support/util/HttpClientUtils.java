package com.yffd.bcap.common.support.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {
    private static Logger LOG = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * POST 请求
     * @param reqUrl
     * @param params    请求参数，例如：name1=value1&name2=value2
     * @return
     */
    public static String sendHttpPost(String reqUrl, String params) {
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(reqUrl);
            connection = (HttpURLConnection) url.openConnection();
            // 创建连接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();//连接

            // 发送POST请求（参数）
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();

            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines = null;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != reader) reader.close();
                if (null != connection) connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String sendHttpPost(String reqUrl, Map<String, String> params) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(reqUrl);
        post.setHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        List<NameValuePair> paramsList = new ArrayList<>();
        for (String key : params.keySet()) {
            paramsList.add(new BasicNameValuePair(key, params.get(key)));
        }
        try {
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(paramsList, "UTF-8");
            post.setEntity(formEntity);
            // 发送请求
            HttpResponse response = client.execute(post);
            // 获取内容
            return extractContent(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendHttpPostWithJson(String reqUrl, String jsonParams) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(reqUrl);
        try {
            StringEntity se = new StringEntity(jsonParams);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");
            post.setEntity(se);
            // 发送请求
            HttpResponse response = client.execute(post);
            // 获取内容
            return extractContent(response);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String sendHttpGet(String reqUrl) {
        // 使用安全包进行检查是否安全
        /*SSRFChecker ssrfChecker = SSRFChecker.instance;
        if (!ssrfChecker.checkUrlWithoutConnection(reqUrl)) {
            LOG.error("HttpClientUtils SSRFCheck Errors ", reqUrl);
            throw new RuntimeException("SSRFChecker fail, url=[" + reqUrl + "]");
         }*/
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(reqUrl);
        httpGet.setHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        try {
            HttpResponse response = client.execute(httpGet);
            return extractContent(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String extractContent(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            InputStream contentInputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(contentInputStream, "UTF-8"));
            StringBuffer sb = new StringBuffer("");
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
        return "";
    }


}
