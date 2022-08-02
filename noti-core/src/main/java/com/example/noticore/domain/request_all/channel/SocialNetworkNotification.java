package com.example.noticore.domain.request_all.channel;

import com.example.noticore.domain.request_all.system_profile.SocialNetworkSystemProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class SocialNetworkNotification extends NotificationChannel {

    @NotEmpty
    private String senderSocialNetworkId;
    @NotEmpty
    private String receiverSocialNetworkId;

    private SocialNetworkSystemProfile targetSocialNetworkSystem;

    public SocialNetworkNotification(String type, String senderSocialNetworkId, String receiverSocialNetworkId, SocialNetworkSystemProfile targetSocialNetworkSystem) {
        super(type);
        this.senderSocialNetworkId = senderSocialNetworkId;
        this.receiverSocialNetworkId = receiverSocialNetworkId;
        this.targetSocialNetworkSystem = targetSocialNetworkSystem;
    }

}
