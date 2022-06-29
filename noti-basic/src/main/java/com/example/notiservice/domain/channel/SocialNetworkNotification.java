package com.example.notiservice.domain.channel;

import com.example.notiservice.domain.system_profile.SocialNetworkSystemProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class SocialNetworkNotification extends NotificationChannel{

    @NotEmpty
    private String senderSocialNetworkId;
    @NotEmpty
    private String receiverSocialNetworkId;
//    @Valid
    private SocialNetworkSystemProfile targetSocialNetworkSystem;

    public SocialNetworkNotification(String transactionId, String type, Status status, String senderSocialNetworkId, String receiverSocialNetworkId, SocialNetworkSystemProfile targetSocialNetworkSystem) {
        super(transactionId, type, status);
        this.senderSocialNetworkId = senderSocialNetworkId;
        this.receiverSocialNetworkId = receiverSocialNetworkId;
        this.targetSocialNetworkSystem = targetSocialNetworkSystem;
    }

//        @Override
//    protected String getType() {
//        return "sns";
//    }
}
