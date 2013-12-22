package fr.notfound;

import static java.lang.String.format;
import static java.net.HttpURLConnection.HTTP_OK;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestClient {
    
    public String get(final String url) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            return client.execute(request, new ResponseHandler<String>() {
                @Override public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    if (response.getStatusLine().getStatusCode() != HTTP_OK) 
                        throw new ClientProtocolException(format("%s responded %s", url, response.getStatusLine().getStatusCode()));
                    return EntityUtils.toString(response.getEntity());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
