package com.toruswork.test.logexpector;

import com.torusworks.logexpector.EventTypes;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by jjuch on 8/29/14.
 */
public class EventTypesTest {

    @Test
    public void loadTestTestEventDefinition() {

        InputStream is = getClass().getResourceAsStream("/TestEventDefinition.xml");

        EventTypes et = EventTypes.loadFromFileStream(is);
    }
}
