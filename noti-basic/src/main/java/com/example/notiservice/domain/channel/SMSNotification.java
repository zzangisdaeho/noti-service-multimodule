package com.example.notiservice.domain.channel;

import com.example.notiservice.domain.system_profile.SMSSystemProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class SMSNotification extends NotificationChannel{

    @NotEmpty
    @Length(min = 11, max = 11)
    private String senderPhoneNumber;
    @NotEmpty
    private String receiverPhoneNumber;
//    @Valid
    private SMSSystemProfile targetSMSSystem;

    public SMSNotification(String transactionId, String type, Status status, String senderPhoneNumber, String receiverPhoneNumber, SMSSystemProfile targetSMSSystem) {
        super(transactionId, type, status);
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.targetSMSSystem = targetSMSSystem;
    }

//        @Override
//    protected String getType() {
//        return "sms";
//    }
}
