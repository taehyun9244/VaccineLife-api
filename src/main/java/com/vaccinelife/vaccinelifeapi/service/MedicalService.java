package com.vaccinelife.vaccinelifeapi.service;

import javax.transaction.Transactional;

import com.vaccinelife.vaccinelifeapi.dto.MedicalRequestDto;
import com.vaccinelife.vaccinelifeapi.model.Medical;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.repository.MedicalRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MedicalService {

    private final MedicalRepository medicalRepository;
    private final UserRepository userRepository;

//    의료진 한마디 조회 기능
@Transactional
    public List<Medical> getMedicalRequestDto(){
    List<Medical> medicals = medicalRepository.findAllByOrderByModifiedAtDesc();
    return medicals;
}


//    의료진 한마디 작성 기능
@Transactional
public void createMedical(MedicalRequestDto requestDto){
    User user = userRepository.findById(requestDto.getUserId()).orElseThrow(
            () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
    );
    Medical medical = new Medical(requestDto);
    medical.setUser(user);
    medicalRepository.save(medical);
}

//    의료진 한마디 삭제 기능
    @Transactional
    public void deleteMedical(Long medicalId){
        Medical medical = medicalRepository.findById(medicalId).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디값을 찾을 수 없습니다.")
        );
        medicalRepository.delete(medical);
    }
}
