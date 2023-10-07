package com.mousephone.uploader;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.mousephone.model.Customer;
import com.mousephone.model.CustomerAvatar;
import com.mousephone.model.LocationRegion;
import com.mousephone.model.User;

@ConfigurationProperties(prefix = "application.uploader")
@Getter
@Setter
@Data
public class CloudinaryConfig {
    @Value("${application.uploader.cloud-name}")
    private String cloudName;
    @Value("${application.uploader.api-key}")
    private String apiKey;
    @Value("${application.uploader.api-secret}")
    private String apiSecret;
}
