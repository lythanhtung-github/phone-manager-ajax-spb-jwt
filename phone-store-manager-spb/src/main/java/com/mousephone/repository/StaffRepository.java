package com.mousephone.repository;

import com.mousephone.model.Staff;
import com.mousephone.model.dto.staff.StaffDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query("SELECT NEW com.mousephone.model.dto.staff.StaffDTO (" +
                "s.id, " +
                "s.fullName, " +
                "s.phone, " +
                "s.locationRegion, " +
                "s.user, " +
                "s.avatar" +
            ") " +
            "FROM Staff AS s " +
            "WHERE s.deleted = false "
    )
    List<StaffDTO> getAllStaffDTO();
    @Query("SELECT NEW com.mousephone.model.dto.staff.StaffDTO (" +
                "s.id, " +
                "s.fullName, " +
                "s.phone, " +
                "s.locationRegion, " +
                "s.user, " +
                "s.avatar" +
            ") " +
            "FROM Staff AS s " +
            "WHERE s.deleted = false " +
            "AND s.id <> :staffId " +
            "AND s.user.role.code <> 'CUSTOMER'"
    )
    List<StaffDTO> getAllStaffDTOWhereIdNot(@Param("staffId") Long staffId);

    @Query("SELECT NEW com.mousephone.model.dto.staff.StaffDTO (" +
                "s.id, " +
                "s.fullName, " +
                "s.phone, " +
                "s.locationRegion, " +
                "s.user, " +
                "s.avatar" +
            ") " +
            "FROM Staff AS s " +
            "WHERE s.deleted = false " +
            "AND s.user.username = :email"
    )
    Optional<StaffDTO> getByEmailDTO(@Param("email") String email);

    @Modifying
    @Query("UPDATE Staff AS s SET s.deleted = true WHERE s.id = :staffId")
    void softDelete(@Param("staffId") long staffId);

    Boolean existsByPhoneAndIdNot(String phone, Long id);

    Optional<Staff> findByPhone(String phone);

    List<Staff> findAllByIdNot(long id);
}
