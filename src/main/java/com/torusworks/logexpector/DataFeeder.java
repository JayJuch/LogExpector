package com.torusworks.logexpector;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by jjuch on 8/15/14.
 */
public class DataFeeder {

    StateMachine sm;
    BufferedReader in;

    public DataFeeder(StateMachine sm, BufferedReader in) {
        this.sm = sm;
    }

    public boolean feedLine() throws IOException{

        String line = null;
        if ((line = in.readLine()) != null) {

        }

        return true;
    }

}
