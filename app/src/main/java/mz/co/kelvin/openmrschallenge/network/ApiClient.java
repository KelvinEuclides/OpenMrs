package mz.co.kelvin.openmrschallenge.network;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import mz.co.kelvin.openmrschallenge.util.BasicAuthInterceptor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = getUnsafeOkHttpClient(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://mail.ccsaude.org.mz:5458/junho/ws/rest/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    //metodo responsavel para fazer o bypass de certificados ssl e autenticar a api
    public static OkHttpClient getUnsafeOkHttpClient(Interceptor interceptor) {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Instalamos o certificado ssl
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Criamos uma fábrica de soket ssl com o nosso gerente de confiança
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            //autenticamos a api
            builder.addInterceptor(new BasicAuthInterceptor("teste", "Teste@345"));
            builder.hostnameVerifier((hostname, session) -> true);
            builder.addInterceptor(interceptor);
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}