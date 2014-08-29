package com.torusworks.logexpector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jjuch on 8/18/14.
 */

public class EventType {

    String eventId;

    String regEx;

    @XmlElement(name = "Fields")
    List<Field> fields = new LinkedList<Field>();

    Pattern regExPattern;

    @XmlAttribute(name="id")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @XmlElement(name="RegEx")
    public String getRegEx() {
        return regEx;
    }

    public void setRegEx(String regEx) {
        this.regEx = regEx;
    }

    public List<Field> match(String data) {
        List<Field> ret = new LinkedList<Field>();

        Matcher match = regExPattern.matcher(data);
        if (match.matches()) {
            if (fields.size() != match.groupCount() - 1 ) {
                throw new RegExMatchException(this.regEx, data);
            }
            Iterator<Field> itr = fields.iterator();
            for (int i=1; i < match.groupCount(); i++) {
                Field v = itr.next().newCopy();
                v.setValue(match.group(i));
                ret.add(v);
            }
        }
        return ret;
    }

    public static class Field {
        String variableId;
        String value;
        boolean isKey = false;

        public Field(){

        }

        public Field(String variableId, boolean isKey) {
            this.variableId = variableId;
            this.isKey = isKey;
        }

        public Field newCopy() {
            Field ret = new Field(this.getVariableId(), this.isKey());
            return ret;
        }

        public String getVariableId() {
            return variableId;
        }

        public void setVariableId(String variableId) {
            this.variableId = variableId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isKey() {
            return isKey;
        }

        public void setKey(boolean isKey) {
            this.isKey = isKey;
        }
    }

    public class RegExMatchException extends RuntimeException {
        String message;

        public RegExMatchException (String regEx, String data) {
            this.message = "RegEx = " + regEx + " Data = " + data;
        }

        public String getMessage() {
            return message;
        }

        public String toString() {
            return message;
        }
    }
}
