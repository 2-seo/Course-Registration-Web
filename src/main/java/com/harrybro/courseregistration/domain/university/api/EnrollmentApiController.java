package com.harrybro.courseregistration.domain.university.api;

import com.harrybro.courseregistration.domain.university.dto.EnrollmentSaveResponse;
import com.harrybro.courseregistration.domain.university.service.EnrollmentService;
import com.harrybro.courseregistration.global.config.auth.PrincipalDetail;
import com.harrybro.courseregistration.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class EnrollmentApiController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/enrollment/{id}")
    public ResponseDto<EnrollmentSaveResponse> save(@PathVariable("id") Long lectureID, @AuthenticationPrincipal PrincipalDetail principal) {
        return enrollmentService.save(lectureID, principal);
    }

    @DeleteMapping("/enrollment/{id}")
    public ResponseDto delete(@PathVariable("id") Long lectureID, @AuthenticationPrincipal PrincipalDetail principal) {
        return enrollmentService.delete(lectureID, principal);
    }

}
