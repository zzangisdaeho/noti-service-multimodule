package com.example.notiservice.domain.channel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = EmailNotification.class, name = "email"),
                @JsonSubTypes.Type(value = SMSNotification.class, name = "sms"),
                @JsonSubTypes.Type(value = SocialNetworkNotification.class, name = "sns"),
        }
)
//@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class NotificationChannel {

    @NotEmpty
    private String transactionId;

    private String type;

//    @JsonIgnore
    private Status isSuccess;

//    protected abstract String getType();

    public boolean isSuccess(){
        return this.isSuccess == Status.SUCCESS;
    }
}
