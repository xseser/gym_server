package com.backend.gym.controller.proxy.registration;

import com.backend.gym.controller.proxy.BaseIntegrationTest;
import com.backend.gym.controller.proxy.DataProvider;
import com.gym.user.registration.controller.request.base.UserRegistrationDto;
import com.gym.user.registration.controller.response.UserRegistrationResponseDto;
import com.gym.user.registration.model.Gender;
import com.gym.user.registration.model.Role;
import com.gym.user.registration.model.User;
import com.gym.user.registration.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class TestUserRegistrationDataProvider extends BaseIntegrationTest implements DataProvider {

    @Autowired
    UserRepository userRepository;

    protected UserRegistrationDto provideValidUserRegistrationDto() {
        String password = getPassword();
        return UserRegistrationDto.builder()
                .mail(getMail())
                .gender(getGender())
                .nickName(getNickName())
                .password(password)
                .passwordMatcher(password)
                .build();
    }

    private UserTestObject mapUserRegistrationDtoToTestObject(UserRegistrationDto userRegistrationDto) {
        return UserTestObject.builder()
                .nickname(userRegistrationDto.getNickName())
                .mail(userRegistrationDto.getMail())
                .gender(userRegistrationDto.getGender() == null ? String.valueOf(Gender.UNKNOWN) : userRegistrationDto.getGender())
                .role(String.valueOf(Role.MEMBER))
                .build();
    }

    private UserTestObject mapUserRegistrationResponseDtoToTestObject(UserRegistrationResponseDto userRegistrationResponseDto) {
        return UserTestObject.builder()
                .mail(userRegistrationResponseDto.getMail())
                .gender(String.valueOf(userRegistrationResponseDto.getGender()))
                .nickname(userRegistrationResponseDto.getNickname())
                .role(String.valueOf(userRegistrationResponseDto.getRole()))
                .build();
    }

    private UserTestObject mapUserEntityToTestObject(User user) {
        return UserTestObject.builder()
                .mail(user.getMail())
                .gender(String.valueOf(user.getGender()))
                .nickname(user.getNickname())
                .role(String.valueOf(user.getRole()))
                .build();
    }

    protected void assertValidRegistrationResponse(ResponseEntity responseEntity, UserRegistrationDto registrationDto) {
        //given
        UserRegistrationResponseDto response = ((UserRegistrationResponseDto) responseEntity.getBody());

        //then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isNotNull();

        //given
        UserTestObject testObjectFromRegistration = mapUserRegistrationDtoToTestObject(registrationDto);
        UserTestObject testObjectFromResponse = mapUserRegistrationResponseDtoToTestObject(response);

        //then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(testObjectFromRegistration).isEqualTo(testObjectFromResponse);
        checkIfGivenUserWasProvisioned(registrationDto);
    }

    private void checkIfGivenUserWasProvisioned(UserRegistrationDto registrationDto) {
        //given
        User user = userRepository.findByMail(registrationDto.getMail())
                .orElseThrow();
        UserTestObject testObjectFromRegistration = mapUserRegistrationDtoToTestObject(registrationDto);
        UserTestObject testObjectFromDb = mapUserEntityToTestObject(user);

        //then
        Assertions.assertThat(user.getId()).isNotNull();
        Assertions.assertThat(testObjectFromRegistration).isEqualTo(testObjectFromDb);
    }

    protected void assertInvalidRegistrationResponse(ResponseEntity responseEntity, HttpStatus status, int errorCode) {
        Integer response = ((Integer) responseEntity.getBody());

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(status);
        Assertions.assertThat(response).isEqualTo(errorCode);
    }

    protected void checkIfGivenUserWasNotProvisioned(UserRegistrationDto registrationDto) {
        Assertions
                .assertThat(userRepository
                        .findByMail(registrationDto.getMail())
                        .isPresent())
                .isFalse();
    }
}
