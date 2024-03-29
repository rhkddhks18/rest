package com.example.rest.member.controller;

import com.example.rest.Rq;
import com.example.rest.member.dto.MemberDto;
import com.example.rest.member.service.MemberService;
import com.example.rest.rsData.RsData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final Rq rq;

    @AllArgsConstructor
    @Getter
    public static class LoginResponseBody {
        private MemberDto item;
    }

    @Getter
    @Setter
    public static class LoginRequestBody {
        @NotBlank
        private String username;
        @NotBlank
        private String password;
    }

    @PostMapping("/login")
    public RsData<LoginResponseBody> login(
            @Valid @RequestBody LoginRequestBody body
    ) {
        RsData<MemberService.AuthAndMakeTokensResponseBody> authAndMakeTokensRs = memberService.authAndMakeTokens(body.getUsername(), body.getPassword());

        rq.setCrossDomainCookie("refreshToken", authAndMakeTokensRs.getData().getRefreshToken());
        rq.setCrossDomainCookie("accessToken", authAndMakeTokensRs.getData().getAccessToken());

        return RsData.of(
                authAndMakeTokensRs.getResultCode(),
                authAndMakeTokensRs.getMsg(),
                new LoginResponseBody(
                        new MemberDto(
                                authAndMakeTokensRs.getData().getMember()
                        )
                )
        );
    }
}
