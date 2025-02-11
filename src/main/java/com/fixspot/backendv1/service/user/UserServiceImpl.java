package com.fixspot.backendv1.service.user;

import com.fixspot.backendv1.dto.common.RegisterUserRequest;
import com.fixspot.backendv1.enums.UserRoles;
import com.fixspot.backendv1.dto.responseDtos.ReporterDetailsResponse;
import com.fixspot.backendv1.dto.responseDtos.ReporterIssueResponse;
import com.fixspot.backendv1.entities.UserEntity;
import com.fixspot.backendv1.exception.exceptions.UserExistsException;
import com.fixspot.backendv1.generalUtil.Pair;
import com.fixspot.backendv1.generalUtil.ResultWrapper;
import com.fixspot.backendv1.repositories.IssueRepository;
import com.fixspot.backendv1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ResultWrapper<ReporterDetailsResponse>> getUser(String username) {
        UserEntity user = userRepository.findByUsername(username).get();

        List<ReporterIssueResponse> issues = new ArrayList<>();
        issueRepository.findByIssuer(user.getId()).forEach(i -> {
            issues.add(ReporterIssueResponse.builder()
                    .issuer(user.getId())
                    .address(i.getAddress())
                    .issueDescription(i.getIssueDescription())
                    .status(i.getStatus())
                    .latitude(i.getLatitude())
                    .longitude(i.getLongitude())
                    .id(i.getId())
                    .build());
        });

        ReporterDetailsResponse response = ReporterDetailsResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .area(user.getArea())
                .city(user.getCity())
                .houseNo(user.getHouseNo())
                .landmark(user.getLandmark())
                .latitude(user.getLatitude())
                .longitude(user.getLongitude())
                .pinCode(user.getPinCode())
                .mobileNo(user.getMobileNo())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
//                .reportedIssues(issues) todo extra weight on api response
                .build();

        return ResponseEntity.ok(ResultWrapper.success("success", response));
    }

    @Override
    public ResponseEntity<ResultWrapper<UserEntity>> registerUser(RegisterUserRequest request) throws Exception {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UserExistsException();
        }

        Pair<Boolean, String> registerUserRequestChecker = check(request);
        if (!registerUserRequestChecker.getFirst()) {
            throw new Exception(registerUserRequestChecker.getSecond());
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .area(request.getArea())
                .city(request.getCity())
                .houseNo(request.getHouseNo())
                .landmark(request.getLandmark())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .pinCode(request.getPinCode())
                .mobileNo(request.getMobileNo())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(request.getRole())
                .build();
        userRepository.save(user);
        return ResponseEntity.ok(ResultWrapper.success("success", userRepository.findByUsername(user.getUsername()).get()));
    }


    /*
     * Utility function to validate RegisterUserRequest.
     */
    private Pair<Boolean, String> check(RegisterUserRequest userDto) {
        // Check if role exists
        String role = userDto.getRole();
        if (role == null || role.isEmpty()) {
            return new Pair<>(false, "Role cannot be null or empty.");
        }

        boolean roleExists = Arrays.stream(UserRoles.values())
                .anyMatch(enumValue -> enumValue.name().equalsIgnoreCase(role));

        if (!roleExists) {
            return new Pair<>(false, "Role does not exist in UserRoles enum.");
        }

        // Validate username (should be a valid email)
        String username = userDto.getUsername();
        if (username == null || username.isEmpty()) {
            return new Pair<>(false, "Username (email) cannot be null or empty.");
        }

        if (!isValidEmail(username)) {
            return new Pair<>(false, "Username must be a valid email address.");
        }

        // Validate password (should meet criteria)
        String password = userDto.getPassword();
        if (password == null || password.isEmpty()) {
            return new Pair<>(false, "Password cannot be null or empty.");
        }

        if (!isValidPassword(password)) {
            return new Pair<>(false, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }

        return new Pair<>(true, "Validation successful.");
    }

    /*
     * Utility method to validate email using regex.
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
        return Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE).matcher(email).matches();
    }

    /*
     * Utility method to validate password.
     * Password must be at least 8 characters long and contain:
     * - At least one uppercase letter
     * - At least one lowercase letter
     * - At least one digit
     * - At least one special character
     */
    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }
}


