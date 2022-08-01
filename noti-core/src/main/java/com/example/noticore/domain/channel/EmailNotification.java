package com.example.noticore.domain.channel;

import com.example.noticore.domain.system_profile.EmailSystemProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class EmailNotification extends NotificationChannel {

    @Email
    @NotEmpty
    private String senderEmailAddress;
    @Email
    @NotEmpty
    private String receiverEmailAddress;

    private EmailSystemProfile targetEmailSystem;

    public EmailNotification(String type, String senderEmailAddress, String receiverEmailAddress, EmailSystemProfile targetEmailSystem) {
        super(type);
        this.senderEmailAddress = senderEmailAddress;
        this.receiverEmailAddress = receiverEmailAddress;
        this.targetEmailSystem = targetEmailSystem;
    }

}
