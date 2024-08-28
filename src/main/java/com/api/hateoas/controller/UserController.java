package com.api.hateoas.controller;

import com.api.hateoas.dto.*;
import com.api.hateoas.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * 1. 아이디 중복체크
   */
  @GetMapping("/check/{id}")
  public ResponseEntity<EntityModel<DupRes>> dupCheck(@PathVariable String id) {
    DupReq req = new DupReq(id);
    DupRes res = userService.dupCheck(req);
    EntityModel<DupRes> model = EntityModel.of(res);

    // self 링크
    model.add(linkTo(methodOn(UserController.class).dupCheck(id)).withSelfRel());

    return ResponseEntity.ok(model);
  }

  /**
   * 2. 회원가입
   */
  @PostMapping("/join")
  public ResponseEntity<EntityModel<JoinRes>> join(@RequestBody JoinReq joinReq, HttpServletRequest request) {
    JoinRes response = userService.join(joinReq);
    EntityModel<JoinRes> model = EntityModel.of(response);

    // self 링크
    model.add(linkTo(methodOn(UserController.class).join(joinReq, request)).withSelfRel());

    // 로그인 링크
    model.add(linkTo(methodOn(UserController.class).login(new LoginReq(), request)).withRel("login"));

    return ResponseEntity.ok(model);
  }

  /**
   * 3. 로그인
   */
  @PostMapping("/login")
  public ResponseEntity<EntityModel<LoginRes>> login(@RequestBody LoginReq loginReq, HttpServletRequest request) {
    LoginRes response = userService.login(loginReq);
    EntityModel<LoginRes> model = EntityModel.of(response);
    if (response.isLogin()) {
      request.getSession().setAttribute("id", response.getId());
    }

    // self 링크
    model.add(linkTo(methodOn(UserController.class).login(loginReq, request)).withSelfRel());

    // 회원수정 링크
    model.add(linkTo(methodOn(UserController.class).update(null, null)).withRel("update"));

    // 회원탈퇴 링크
    model.add(linkTo(methodOn(UserController.class).quit(null, null)).withRel("quit"));

    return ResponseEntity.ok(model);
  }

  /**
   * 4. 회원수정
   */
  @PutMapping("/mod/{id}")
  public ResponseEntity<EntityModel<UpdateRes>> update(@PathVariable String id, @RequestBody UpdateReq updateReq) {
    updateReq.setId(id);
    UpdateRes response = userService.update(updateReq);
    EntityModel<UpdateRes> model = EntityModel.of(response);

    // self 링크
    model.add(linkTo(methodOn(UserController.class).update(id, updateReq)).withSelfRel());

    // 회원가입 링크
    model.add(linkTo(methodOn(UserController.class).join(null, null)).withRel("join"));

    return ResponseEntity.ok(model);
  }

  /**
   * 5. 회원탈퇴
   */
  @DeleteMapping("/mod/{id}")
  public ResponseEntity<EntityModel<UpdateRes>> quit(@PathVariable String id, @RequestBody UpdateReq quitReq) {
    quitReq.setId(id);
    UpdateRes response = userService.quit(quitReq);
    EntityModel<UpdateRes> model = EntityModel.of(response);

    // self 링크
    model.add(linkTo(methodOn(UserController.class).quit(id, quitReq)).withSelfRel());

    // 회원가입 링크
    model.add(linkTo(methodOn(UserController.class).join(null, null)).withRel("join"));

    return ResponseEntity.ok(model);
  }
}
