package com.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by rajasupadhye on 2/23/17.
 */
public class Errors {
    private ObjectMapper mapper = null;
    ObjectNode errorMessage = null;

    public Errors(){
        if(mapper == null){
            mapper = new ObjectMapper();
        }
        errorMessage = mapper.createObjectNode();
    }

    public Errors(String s){
        if(mapper == null){
            mapper = new ObjectMapper();
        }
        errorMessage = mapper.createObjectNode();
        setErrorMessage(s);
    }

    public void setErrorMessage(String s){
        errorMessage.put("error",s);
    }

    public String getErrorMessage(){
        return errorMessage.toString();
    }
}
