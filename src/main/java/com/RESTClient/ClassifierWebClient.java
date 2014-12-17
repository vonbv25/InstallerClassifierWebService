package com.RESTClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Created by von on 12/9/14.
 */
public class ClassifierWebClient {


    /**
     * Get the resposnse from Web Service
     * @param dataPath path of data to be classified. must be in .csv format
     * @return 1 if the method proceeds without error otherwise -1
     */
    public static int getResponse(String dataPath) {

        String uri = "";

        String[] token = dataPath.split("/");

        for (String str: token) {
            uri += str+ "%2f";
        }

        try{

            Client client = Client.create();
            WebResource webResource = client.
                    resource("http://localhost:9998/ClassifierWebService/" +
                            uri);
            ClientResponse response = webResource.accept("application/json").
                    get(ClientResponse.class);

            if (response.getStatus()!=200) {
                throw  new RuntimeException("Fialed: HTTP error code + " +
                        response.getStatus());
            }

            String output = response.getEntity(String.class);
            System.out.println("Output from Server .... \n");
            System.out.println(output);

            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

  public static void main(String[] argv) {

    // Please, do not remove this line from file template, here invocation of web service will be inserted  
  }
}
