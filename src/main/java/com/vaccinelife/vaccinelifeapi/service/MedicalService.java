package com.vaccinelife.vaccinelifeapi.service;

import javax.transaction.Transactional;

import com.vaccinelife.vaccinelifeapi.dto.MedicalRequestDto;
import com.vaccinelife.vaccinelifeapi.dto.MedicalResponseDto;
import com.vaccinelife.vaccinelifeapi.dto.MedicalTop3RequestDto;
import com.vaccinelife.vaccinelifeapi.model.Medical;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.repository.MedicalRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service

public class MedicalService {

    private final MedicalRepository medicalRepository;
    private final UserRepository userRepository;

    //    의료진 한마디 조회 기능
    @Transactional
    public List<MedicalResponseDto> getMedicalRequestDto() {
        LocalDateTime month = LocalDateTime.of(LocalDate.now().minusDays(30), LocalTime.of(0,0,0));
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        List<Medical> medicals = medicalRepository.findAllByCreatedAtBetweenOrderByCreatedAtDesc(month, now);
        return MedicalResponseDto.list(medicals);
    }


    //    의료진 한마디 작성 기능
    @Transactional
    public void createMedical(MedicalRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        Medical medical = new Medical(requestDto);
        medical.setUser(user);
        medicalRepository.save(medical);
    }

    //    의료진 한마디 삭제 기능
    @Transactional
    public void deleteMedical(Long medicalId) {
        Medical medical = medicalRepository.findById(medicalId).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디값을 찾을 수 없습니다.")
        );
        medicalRepository.delete(medical);
    }

    //    탑 3
    @Transactional
    public List<MedicalTop3RequestDto> getTopList() {
        LocalDateTime week = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0,0,0));
        LocalDateTime now = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        List<Medical> medicals = medicalRepository.findTop3ByCreatedAtBetweenOrderByLikeCountDescCreatedAtDesc(week, now);
        return MedicalTop3RequestDto.list(medicals);
    }


}
