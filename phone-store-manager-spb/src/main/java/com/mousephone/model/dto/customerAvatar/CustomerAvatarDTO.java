package com.mousephone.model.dto.customerAvatar;

import com.mousephone.model.CustomerAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerAvatarDTO {
    private String id;
    private String fileName;
    private String fileFolder;
    @NotEmpty(message = "Vui lòng chọn hình ảnh.")
    private String fileUrl;
    private String fileType;
    private String cloudId;
    private Long ts;

    public CustomerAvatar toCustomerAvatar() {
        return new CustomerAvatar()
                .setId(id)
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setFileType(fileType)
                .setCloudId(cloudId)
                .setTs(ts);
    }
}
