package com.Classifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by von on 12/17/14.
 */
public class InstallerSerializer {



    public String serialize(List <Installer> installers) {
        ObjectMapper mapper = new ObjectMapper();

        try {

            return  mapper.writeValueAsString(installers);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "Cannot Serialize";

    }

    public String serialize (Installer installer) {
        ObjectMapper mapper = new ObjectMapper();

        try {

            return  mapper.writeValueAsString(installer);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "Cannot Serialize";

    }


    public List deserializeList(String content) {
        ObjectMapper mapper = new ObjectMapper();
        List <Installer> installers;
        try {
            installers = mapper.readValue(content, List.class);
            return installers;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Installer deserialize(String content) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content,Installer.class);

        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
