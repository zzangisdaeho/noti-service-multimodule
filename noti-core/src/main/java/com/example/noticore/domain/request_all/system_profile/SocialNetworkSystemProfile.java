package com.example.noticore.domain.request_all.system_profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialNetworkSystemProfile extends SystemProfile {

    public SocialNetworkSystemProfile(String credential) {
        super(credential);
    }
}
