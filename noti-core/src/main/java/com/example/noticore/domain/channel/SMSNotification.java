package com.example.noticore.domain.channel;

import com.example.noticore.domain.system_profile.SMSSystemProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class SMSNotification extends NotificationChannel {

    @NotEmpty
    @Length(min = 11, max = 11)
    private String senderPhoneNumber;
    @NotEmpty
    private String receiverPhoneNumber;

    private SMSSystemProfile targetSMSSystem;

    public SMSNotification(String type, String senderPhoneNumber, String receiverPhoneNumber, SMSSystemProfile targetSMSSystem) {
        super(type);
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.targetSMSSystem = targetSMSSystem;
    }

}
