package com.mousephone.service.staff;

import com.mousephone.exception.DataInputException;
import com.mousephone.model.LocationRegion;
import com.mousephone.model.Staff;
import com.mousephone.model.StaffAvatar;
import com.mousephone.model.User;
import com.mousephone.model.dto.staff.StaffCreateDTO;
import com.mousephone.model.dto.staff.StaffDTO;
import com.mousephone.model.enums.FileType;
import com.mousephone.repository.StaffRepository;
import com.mousephone.service.locationRegion.ILocationRegionService;
import com.mousephone.service.staffAvatar.IStaffAvatarService;
import com.mousephone.service.user.IUserService;
import com.mousephone.upload.IUploadService;
import com.mousephone.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ILocationRegionService locationRegionService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStaffAvatarService staffAvatarService;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private UploadUtil uploadUtil;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Staff getById(Long id) {
        return staffRepository.getById(id);
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Boolean existsByPhoneAndIdNot(String phone, Long id) {
        return staffRepository.existsByPhoneAndIdNot(phone, id);
    }

    @Override
    public Staff save(Staff staff) {
        userService.save(staff.getUser());
        locationRegionService.save(staff.getLocationRegion());
        return staffRepository.save(staff);
    }

    @Override
    public Staff saveWithUserRoleAndLocationRegion(Staff staff){
        userService.saveNoPassWord(staff.getUser());
        locationRegionService.save(staff.getLocationRegion());
        return staffRepository.save(staff);
    }

    @Override
    public Staff saveWithLocationRegion(Staff staff) {
        locationRegionService.save(staff.getLocationRegion());
        return staffRepository.save(staff);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<StaffDTO> getAllStaffDTO() {
        return staffRepository.getAllStaffDTO();
    }

    @Override
    public List<StaffDTO> getAllStaffDTOWhereIdNot(Long staffId){
        return staffRepository.getAllStaffDTOWhereIdNot(staffId);
    }

    @Override
    public void softDelete(Long staffId) {
        staffRepository.softDelete(staffId);
    }

    @Override
    public Optional<StaffDTO> getByEmailDTO(String email) {
        return staffRepository.getByEmailDTO(email);
    }

    @Override
    public List<Staff> findAllByIdNot(long id) {
        return staffRepository.findAllByIdNot(id);
    }

    @Override
    public Optional<Staff> findByPhone(String phone) {
        return staffRepository.findByPhone(phone);
    }

    @Override
    public Staff createStaffWithAvatar(StaffCreateDTO staffCreateDTO,
                                       LocationRegion locationRegion,
                                       User user) {
        Staff staff = new Staff();
        String fullName = staffCreateDTO.getFullName();
        String phone = staffCreateDTO.getPhone();

        locationRegion = locationRegionService.save(locationRegion);
        user = userService.save(user);

        MultipartFile file = staffCreateDTO.getFile();
        String fileType = file.getContentType();
        assert fileType != null;
        fileType = fileType.substring(0, 5);

        StaffAvatar staffAvatar = new StaffAvatar();
        staffAvatar.setFileType(fileType);
        staffAvatar = staffAvatarService.save(staffAvatar);

        if (fileType.equals(FileType.IMAGE.getValue())) {
            staffAvatar = uploadAndSaveStaffAvatar(file, staffAvatar);
        }
        staff.setId(null)
                .setFullName(fullName)
                .setPhone(phone)
                .setLocationRegion(locationRegion)
                .setUser(user)
                .setAvatar(staffAvatar);
        staff = staffRepository.save(staff);
        return staff;
    }

    private StaffAvatar uploadAndSaveStaffAvatar(MultipartFile file, StaffAvatar staffAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtil.buildImageUploadParams(staffAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            staffAvatar.setFileName(staffAvatar.getId() + "." + fileFormat);
            staffAvatar.setFileUrl(fileUrl);
            staffAvatar.setFileFolder(UploadUtil.STAFF_IMAGE_UPLOAD_FOLDER);
            staffAvatar.setCloudId(staffAvatar.getFileFolder() + "/" + staffAvatar.getId());
            return staffAvatarService.save(staffAvatar);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại.");
        }
    }

    @Override
    public Staff saveWithAvatar(Staff staff, MultipartFile file) {

        StaffAvatar oldStaffAvatar = staff.getAvatar();
        try {
            uploadService.destroyImage(oldStaffAvatar.getCloudId(), uploadUtil.buildImageDestroyParams(staff, oldStaffAvatar.getCloudId()));
            staffAvatarService.delete(oldStaffAvatar.getId());
            String fileType = file.getContentType();
            assert fileType != null;
            fileType = fileType.substring(0, 5);
            StaffAvatar staffAvatar = new StaffAvatar();
            staffAvatar.setFileType(fileType);
            staffAvatar = staffAvatarService.save(staffAvatar);

            if (fileType.equals(FileType.IMAGE.getValue())) {
                staffAvatar = uploadAndSaveStaffAvatar(file, staffAvatar);
            }
            staff.setAvatar(staffAvatar);
            staff = staffRepository.save(staff);
            return staff;
        } catch (IOException e) {
            throw new DataInputException("Xóa hình ảnh thất bại.");
        }
    }
}
