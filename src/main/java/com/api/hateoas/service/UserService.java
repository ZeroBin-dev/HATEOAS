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
   * 1. 아이디 중복 체크
   */
  public DupRes dupCheck(final DupReq dupReq) {
    boolean isDup = userRepository.findById(dupReq.getId()).isPresent();
    return new DupRes(isDup);
  }

  /**
   * 2. 회원가입
   */
  public JoinRes join(final JoinReq req) {

  }


  /**
   * 로그인
   */
  public LoginRes login(final LoginReq req) {
    Optional<User> user = userRepository.findById(req.getId());
    return user
      .filter(u -> u.getPassword().equals(req.getPassword()))
      .map(u -> new LoginRes(u.getName()))
      .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다."));
  }

  /**
   * 회원탈퇴
   */
  public QuitRes quit(final QuitReq req) {

  }


}
