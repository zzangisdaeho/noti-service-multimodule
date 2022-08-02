package com.example.noticore.domain.request_all.channel;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public abstract class NotificationChannel {

    private String type;
}
