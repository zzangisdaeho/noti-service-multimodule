package com.example.notiservice.domain.channel;

import com.example.notiservice.domain.system_profile.EmailSystemProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class EmailNotification extends NotificationChannel{

    @Email
    @NotEmpty
    private String senderEmailAddress;
    @Email
    @NotEmpty
    private String receiverEmailAddress;
//    @Valid
    private EmailSystemProfile targetEmailSystem;

    public EmailNotification(String transactionId, String type, Status status, String senderEmailAddress, String receiverEmailAddress, EmailSystemProfile targetEmailSystem) {
        super(transactionId, type, status);
        this.senderEmailAddress = senderEmailAddress;
        this.receiverEmailAddress = receiverEmailAddress;
        this.targetEmailSystem = targetEmailSystem;
    }

//        @Override
//    protected String getType() {
//        return "email";
//    }
}
