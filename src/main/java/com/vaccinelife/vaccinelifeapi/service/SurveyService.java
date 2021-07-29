package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.SurveyRequestDto;
import com.vaccinelife.vaccinelifeapi.model.Survey;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.repository.SurveyRepository;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createSurvey(SurveyRequestDto requestDto){
        User user = userRepository.findByUserId(requestDto.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 안습니다.")
        );

        Survey survey = Survey.builder()
                .user(user)
                .type(requestDto.getType())
                .gender(requestDto.getGender())
                .age(requestDto.getAge())
                .disease(requestDto.getDisease())
                .degree(requestDto.getDegree())
                .aftereffect(requestDto.getAftereffect())
                .build();
        surveyRepository.save(survey);
    }


}
