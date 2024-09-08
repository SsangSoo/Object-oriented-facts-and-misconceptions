package book.objectorientedfactsandmisconceptions.spring.coffeeshop.member.controller;

import book.objectorientedfactsandmisconceptions.spring.coffeeshop.member.controller.dto.MemberPostDto;
import book.objectorientedfactsandmisconceptions.spring.coffeeshop.member.controller.dto.MemberResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@Validated @RequestBody MemberPostDto memberPost) {
        return null;
    }

}
