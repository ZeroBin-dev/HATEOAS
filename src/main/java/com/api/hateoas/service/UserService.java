package com.api.hateoas.service;

import com.api.hateoas.dto.*;
import com.api.hateoas.entity.User;
import com.api.hateoas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  /**
   * 1. 아이디 중복체크
   */
  public DupRes dupCheck(final DupReq dupReq) {
    boolean isDup = userRepository.findById(dupReq.getId()).isPresent();
    return DupRes.builder()
      .isDup(isDup)
      .msg(isDup ? "이미 아이디가 존재합니다." : "사용가능한 아이디 입니다.")
      .build();
  }

  /**
   * 2. 회원가입
   */
  public JoinRes join(final JoinReq req) {
    User saveUser = userRepository.save(new User(req.getId(), req.getName(), req.getPassword()));
    boolean isJoin = saveUser != null && !saveUser.getId().isEmpty();
    return JoinRes.builder()
      .isJoin(isJoin)
      .id(isJoin ? saveUser.getId() : "")
      .msg(isJoin ? "가입완료" : "가입실패")
      .build();
  }

  /**
   * 3. 로그인
   */
  public LoginRes login(final LoginReq req) {
    Optional<User> user = userRepository.findById(req.getId());
    long loginCnt = user.filter(u -> u.getPassword().equals(req.getPassword())).stream().count();

    if (loginCnt == 0) {
      return LoginRes.builder()
        .isLogin(false)
        .id(null)
        .msg("아이디 또는 비밀번호가 일치하지 않습니다.")
        .build();
    } else {
      return LoginRes.builder()
        .isLogin(true)
        .id(req.getId())
        .msg("로그인에 성공하였습니다.")
        .build();
    }

  }

  /**
   * 4. 회원수정
   */
  public UpdateRes update(final UpdateReq req) {
    Optional<User> updateUser = userRepository.findById(req.getId()).map(user -> {
      user.setName(req.getName());
      user.setPassword(req.getPassword());
      return userRepository.save(user);
    });

    if (updateUser.isPresent()) {
      return UpdateRes.builder()
        .isUpdate(true)
        .msg("수정이 완료되었습니다.")
        .build();
    } else {
      return UpdateRes.builder()
        .isUpdate(false)
        .msg("수정에 실패하였습니다.")
        .build();
    }
  }


  /**
   * 5. 회원탈퇴
   */
  public UpdateRes quit(final UpdateReq req) {
    // 아이디와 패스워드가 맞아야 회원탈퇴가능
    Optional<User> user = userRepository.findById(req.getId());
    if (user.isPresent()) {

      long loginCnt = user.filter(u -> u.getPassword().equals(req.getPassword())).stream().count();
      if (loginCnt == 0) {
        return UpdateRes.builder()
          .isUpdate(false)
          .msg("비밀번호가 일치하지 않습니다.")
          .build();
      } else {
        userRepository.deleteById(req.getId());
        return UpdateRes.builder()
          .isUpdate(true)
          .msg("회원 탈퇴가 완료되었습니다.")
          .build();
      }
    } else {
      return UpdateRes.builder()
        .isUpdate(false)
        .msg("사용자 정보가 존재하지 않습니다.")
        .build();
    }
  }

}
