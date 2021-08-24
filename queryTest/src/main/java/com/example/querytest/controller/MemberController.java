package com.example.querytest.controller;

import com.example.querytest.domain.MemberSearchCondition;
import com.example.querytest.dto.MemberTeamDto;
import com.example.querytest.repository.MemberJpaRepository;
import com.example.querytest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;

    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCondition condition) {
        return memberJpaRepository.searchByWhere(condition);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCondition condition, Pageable pageable){
        return memberRepository.searchPageSimple(condition, pageable);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCondition condition, Pageable pageable){
        return memberRepository.searchPageComplex(condition, pageable);
    }

    // # Querydsl Web 지원
    // # @QuerydslPredicate
    // @QuerydslPredicate 을 사용하면 컨트롤러의 파라미터를 이용하여 간단하게 Querydsl 조건문을 생성할 수 있다.
    // 기본적으로 파라미터 값이 하나일경우 eq, 여러개일경우 in 조건문이 생성된다.
    // 이렇게 요청을 하면
    //      ?firstname=Dave&lastname=Matthews
    // 이렇게 Predicate 로 변환해준다.
    //      QUser.user.firstname.eq("Dave").and(QUser.user.lastname.eq("Matthews"))
    // 이 조건은 근데 eq 이나 contains 등과 같은 조건만 사용이 가능하다.
    // 그리고 Repository 에서 binding 조건을 넣어줘야 하는데 이도 조금 복잡하다.
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    String index(Model model, @QuerydslPredicate(root = User.class) Predicate predicate,
//                 Pageable pageable, @RequestParam MultiValueMap<String, String> parameters) {
//
//        model.addAttribute("users", repository.findAll(predicate, pageable));
//
//        return "index";
//    }
}
