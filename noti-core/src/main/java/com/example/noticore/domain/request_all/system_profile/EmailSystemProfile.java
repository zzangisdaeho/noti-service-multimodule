package com.example.noticore.domain.request_all.system_profile;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailSystemProfile extends SystemProfile {

    public EmailSystemProfile(String credential) {
        super(credential);
    }
}
