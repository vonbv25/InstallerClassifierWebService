package com.RESTservice;
import com.Classifier.Installer;
import com.Classifier.InstallerClassifier;
import com.Classifier.InstallerSerializer;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by von on 12/9/14.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/ClassifierWebService")
public class ClassifierWebService {
    // The Java method will process HTTP GET requests
    @Path("{dataPath}")
    @GET
    @Produces("application/json")
    public String classify(@PathParam("dataPath") String c) {

        InstallerClassifier classifier = new InstallerClassifier();

        InstallerSerializer serializer = new InstallerSerializer();

        ArrayList<Installer> installerArrayList = classifier.classify(c);
        String json = "";
        for (Installer installer:installerArrayList) {
            json+= serializer.serialize(installer) + "\n";
        }

        return json;

    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");
        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/ClassifierWebService");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
