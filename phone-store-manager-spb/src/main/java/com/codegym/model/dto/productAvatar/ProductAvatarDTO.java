package com.codegym.model.dto.productAvatar;

import com.codegym.model.ProductAvatar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductAvatarDTO {
    private String id;
    private String fileName;
    private String fileFolder;
    @NotEmpty(message = "Vui lòng chọn hình ảnh")
    private String fileUrl;
    private String fileType;
    private String cloudId;
    private Long ts;

    public ProductAvatar toProductAvatarDTO(){
        return new ProductAvatar()
                .setId(id)
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setFileType(fileType)
                .setCloudId(cloudId)
                .setTs(ts);
    }
}
