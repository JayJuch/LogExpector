package com.torusworks.logexpector;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jjuch on 8/18/14.
 */
@XmlRootElement(name = "EventTypes")
public class EventTypes {
    @XmlElement(name = "EventType")
    List<EventType> eventTypes = new LinkedList<EventType>();


    public static EventTypes loadFromFileStream(InputStream stream) {
        EventTypes eventTypes = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EventTypes.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            eventTypes = (EventTypes) jaxbUnmarshaller.unmarshal(stream);
            System.out.println(eventTypes);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return eventTypes;

    }

    public static EventTypes loadFromFile(String filename) throws Exception{
        FileInputStream fis = new FileInputStream(filename);
        return loadFromFileStream(fis);
    }

}
