package de.fhws.fiw.pvs.assignment_5.zikzak.models;

import com.owlike.genson.annotation.JsonConverter;
import com.owlike.genson.annotation.JsonIgnore;
import de.fhws.fiw.pvs.assignment_5.sutton.api.converter.LinkConverter;
import de.fhws.fiw.pvs.assignment_5.sutton.models.AbstractModel;
import org.glassfish.jersey.linking.InjectLink;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Link;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class MessageModel extends AbstractModel {

    @InjectLink( style = InjectLink.Style.ABSOLUTE, value = "messages/${instance.id}", type = "application/json", rel = "self" )
    protected Link selfUri;	// self identifier

    @NotNull
    protected long userId;

    protected String messageText;

    protected Map<Long, Boolean> voting;

    protected Timestamp timeOfCreation;

    public MessageModel() {
        timeOfCreation = new Timestamp(System.currentTimeMillis());
        voting = new HashMap<>();
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void vote(long userId, boolean vote) {
        voting.put(userId, vote);
    }

    public void upvote(long userId) {
        vote(userId, true);
    }

    public void downvote(long userId) {
        vote(userId, false);
    }

    public Map<Long, Boolean> getVoting() {
        return voting;
    }

    public void setVoting(Map<Long, Boolean> voting) {
        this.voting = voting;
    }

    public Timestamp getTimeOfCreation() {
        return timeOfCreation;
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
