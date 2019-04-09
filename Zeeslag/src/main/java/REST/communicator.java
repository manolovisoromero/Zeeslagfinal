package REST;

import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public  class communicator {
    private final String url = "http://localhost:8090/login/";
    private final Gson gson = new Gson();


    public Combi postRegister(String name, String pass){
        Combi combi = new Combi(name,pass);

        final String query = url + "register";

        HttpPost httPostQuery = new HttpPost(query);
        httPostQuery.addHeader("content-type","application/json");

        StringEntity params;
        System.out.println("postregister");

        try {
            params = new StringEntity(gson.toJson(combi));
            httPostQuery.setEntity(params);
        } catch(Exception e){
            System.out.println("fout");

        }
        return executeQuery(httPostQuery);
    }



    public Combi getInfo(String key) {
        final String query = url + key;

        HttpGet httpGetQuery = new HttpGet(query);

        return executeQuery(httpGetQuery);
    }



    private Combi executeQuery(HttpRequestBase requestBaseQuery){
        //Greeting greeting = null;
        Combi combi = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(requestBaseQuery)) {

            System.out.println("status: "+ response.getStatusLine());
            HttpEntity entity = response.getEntity();
            final String entityString = EntityUtils.toString(entity);

            System.out.println("json "+ entityString);

            //greeting = gson.fromJson(entityString, Greeting.class);
            combi = gson.fromJson(entityString, Combi.class);

        } catch (Exception e) {
            System.out.println(e.toString());

        }

        return combi;
    }
}
