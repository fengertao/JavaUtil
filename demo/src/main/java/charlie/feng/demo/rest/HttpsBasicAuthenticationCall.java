package charlie.feng.demo.rest;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.ssl.Base64;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 * 
 */
public class HttpsBasicAuthenticationCall {

	public static void main(String[] args) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		X509TrustManager tm = new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { tm }, new SecureRandom());
			SSLSocketFactory ssf = new SSLSocketFactory(ctx);

			ClientConnectionManager ccm = httpClient.getConnectionManager();
			SchemeRegistry sr = ccm.getSchemeRegistry();
			// even if we will connect to port 8281, here only register 443 is okey.
			sr.register(new Scheme("https", ssf, 443));

			httpClient = new DefaultHttpClient(ccm, httpClient.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("administrator@vsphere.local",
				"vmware");
		// ToDo:change AuthScope.ANY to a suitable value
		credsProvider.setCredentials(AuthScope.ANY, credentials);
		httpClient.setCredentialsProvider(credsProvider);

		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);

		// this part seems useless, although some website said it work
		//        ContextAwareHttpComponentsClientHttpRequestFactory customFactory = new ContextAwareHttpComponentsClientHttpRequestFactory(
		//                httpClient);
		//        HttpHost targetHost = new HttpHost("vcac152-013-161.eng.vmware.com", 8281, "http");
		//        // Create AuthCache instance
		//        AuthCache authCache = new BasicAuthCache();
		//        // Generate BASIC scheme object and add it to the local auth cache
		//        BasicScheme basicAuth = new BasicScheme();
		//        authCache.put(targetHost, basicAuth);
		//
		//        // Add AuthCache to the execution context
		//        BasicHttpContext localContext = new BasicHttpContext();
		//        localContext.setAttribute(ClientContext.AUTH_CACHE, authCache);
		//
		//        customFactory.setHttpContext(localContext);
		//        RestTemplate restTemplate = new RestTemplate(customFactory);

		// Optional?
		// List<HttpMessageConverter<?>> hmc = new ArrayList<HttpMessageConverter<?>>();
		// hmc.add(new
		// org.springframework.http.converter.json.MappingJacksonHttpMessageConverter());
		// hmc.add(new org.springframework.http.converter.FormHttpMessageConverter());
		// hmc.add(new org.springframework.http.converter.StringHttpMessageConverter());
		// restTemplate.setMessageConverters(hmc);

		String username = "administrator@vsphere.local";
		String password = "vmware";
		String plainCreds = username + ":" + password;
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		ClientHttpRequestInterceptor interceptor = new HeaderHttpRequestInterceptor("Authorization", "Basic "
				+ base64Creds);
		restTemplate.setInterceptors(Collections.singletonList(interceptor));

		String result = restTemplate.getForObject("https://vcac152-013-161.eng.vmware.com:8281/vco/api/catalog", String.class);
		System.out.print(result);
	}
}

class HeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {
	String key;
	String value;

	public HeaderHttpRequestInterceptor(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {

		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		requestWrapper.getHeaders().set(key, value);

		return execution.execute(requestWrapper, body);
	}
}
