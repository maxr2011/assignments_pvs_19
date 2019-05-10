package de.fhws.fiw.pvs.assignment_5.zikzak.models;

import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.annotation.JsonIgnore;
import de.fhws.fiw.pvs.assignment_5.sutton.api.converter.LinkConverter;
import de.fhws.fiw.pvs.assignment_5.sutton.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;

public class MessageModel extends AbstractModel {

    @InjectLink( style = InjectLink.Style.ABSOLUTE, value = "messages/${instance.id}", type = "application/json", rel = "self" )
    protected Link selfUri;	// self identifier

    @NotNull
    protected Link userUri; // user Uri

    protected String messageText;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Link getUserUri() {
        return userUri;
    }

    public void setUserUri(Link userUri) {
        this.userUri = userUri;
    }

    @JsonConverter( LinkConverter.class )
    public Link getSelfUri() {
        return selfUri;
    }

    @JsonIgnore
    public void setSelfUri(Link selfUri) {
        this.selfUri = selfUri;
    }

}
