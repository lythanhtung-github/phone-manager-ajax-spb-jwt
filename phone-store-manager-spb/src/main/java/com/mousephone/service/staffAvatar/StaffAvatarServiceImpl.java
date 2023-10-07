package com.mousephone.service.staffAvatar;

import com.mousephone.model.StaffAvatar;
import com.mousephone.repository.StaffAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StaffAvatarServiceImpl implements IStaffAvatarService{
    @Autowired
    private StaffAvatarRepository staffAvatarRepository;

    @Override
    public List<StaffAvatar> findAll() {
        return null;
    }

    @Override
    public StaffAvatar getById(Long id) {
        return null;
    }

    @Override
    public Optional<StaffAvatar> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public StaffAvatar save(StaffAvatar staffAvatar) {
        return staffAvatarRepository.save(staffAvatar);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void delete(String id){
        staffAvatarRepository.deleteById(id);
    }
}
