package charlie.feng.demo.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public class HttpSenderNTLM2 {

  public static void main(String[] args) throws ClientProtocolException, IOException{
    DefaultHttpClient httpclient = new DefaultHttpClient(); 
    NTCredentials creds = new NTCredentials("administrator","ca$hc0w","Hanyu-Main3","VMWAREM"); 
    httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY, creds); 
    HttpGet httpget = new HttpGet("https://appd-sh-008.eng.vmware.com/repository/data/ManagementmodelEntities.svc/"); 
    HttpResponse response1 = httpclient.execute( httpget); 
    HttpEntity entity1 = response1.getEntity(); 
    if (entity1 != null) { 
    entity1.consumeContent(); 
    } 

  }
}
