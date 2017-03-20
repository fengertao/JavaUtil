package charlie.feng.demo.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpSender {

    public final static void main(String[] args) throws Exception {
        URI uri = new URI("https://appd-sh-008.eng.vmware.com/repository/data/ManagementmodelEntities.svc/");
        HttpHost host = URIUtils.extractHost(uri);
        String username = "administrator";
        String password = "ca$hc0w";
        String workstation = "Hanyu-Main3";
        String domain = "VMWAREM";
        Credentials credentials = new NTCredentials(username, password, workstation, domain);
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        AuthScope scope = new AuthScope(host.getHostName(), host.getPort());
        credsProvider.setCredentials(scope, credentials);

        List<String> targetPreferredAuthSchemes = new ArrayList<String>();
        targetPreferredAuthSchemes.add("NTLM");

        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setAuthenticationEnabled(true)
                .setTargetPreferredAuthSchemes(targetPreferredAuthSchemes)
                .build();

        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .setDefaultRequestConfig(defaultRequestConfig)
                .build();
        List<String> authPrefs = new ArrayList<String>(3);
        authPrefs.add(AuthPolicy.NTLM);

        try {
//      HttpGet httpget = new HttpGet("http://www.baidu.com/");
            HttpGet httpget = new HttpGet(uri);

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                public String handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: "
                                + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
    }

    public static void customize(AbstractHttpClient client, URI requestURI) {
        String username = "administrator";
        String password = "ca$hc0w";
        String workstation = "Hanyu-Main3";
        String domain = "VMWAREM";
        HttpHost host = URIUtils.extractHost(requestURI);
        Credentials credentials = new NTCredentials(username, password,
                workstation, domain);
        AuthScope scope = new AuthScope(host.getHostName(), host.getPort());
        client.getCredentialsProvider().setCredentials(scope, credentials);
        List<String> authPrefs = new ArrayList<String>(3);
        authPrefs.add(AuthPolicy.NTLM);
        client.getParams().setParameter(AuthPNames.TARGET_AUTH_PREF, authPrefs);

        HttpConnectionParams.setConnectionTimeout(client.getParams(), 60 * 1000);
        HttpConnectionParams.setSoTimeout(client.getParams(), 60 * 1000);
    }

    private class NTLMAuthenticationBehavior {
        private final String username;
        private final String password;
        private final String workstation;
        private final String domain;

        public NTLMAuthenticationBehavior(String username, String password,
                                          String workstation, String domain) {
            this.username = username;
            this.password = password;
            this.workstation = workstation;
            this.domain = domain;
        }

    }
}
