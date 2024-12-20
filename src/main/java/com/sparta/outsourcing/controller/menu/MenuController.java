package com.sparta.outsourcing.controller.menu;

import com.sparta.outsourcing.dto.menu.*;
import com.sparta.outsourcing.service.menu.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * @Controller + @ResponseBody , 응답할 Data가 있는 경우
 */
@RestController
/*
 * 생성자 의존성 주입을 자동으로 처리
 */
@RequiredArgsConstructor
/*
 * prefix로 선언할 URL을 class 레벨로 적용
 */
@RequestMapping("/stores/{storeId}/menus")
public class MenuController {

  /**
   * 메뉴 관련 비즈니스 로직을 처리하는 서비스 객체
   * 생성자를 통해 의존성을 주입
   */
  private final MenuService menuService;

  /**
   * 메뉴 생성 Controller
   * 메뉴 생성 요청을 처리합니다.
   *
   * @param storeId    가게의 고유 식별자 (PathVariable로 전달)
   * @param requestDto 메뉴 생성 요청 데이터를 담은 DTO
   * @return 생성된 메뉴 데이터와 HTTP 상태코드 201(CREATED)
   */
  @PostMapping
  public ResponseEntity<CreateMenuResponseDto> createMenu(
      @PathVariable Long storeId,
      @Valid @RequestBody CreateMenuRequestDto requestDto
  ) {
    CreateMenuResponseDto responseDto = menuService.createMenu(storeId, requestDto.getName(), requestDto.getPrice());
    return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
  }

  /**
   * 메뉴 제목 수정 Controller
   * 메뉴 제목 수정 요청을 처리합니다.
   *
   * @param storeId    가게의 고유 식별자 (PathVariable로 전달)
   * @param id         메뉴의 고유 식별자 (PathVariable로 전달)
   * @param requestDto 메뉴 제목 수정 요청 데이터를 담은 DTO
   * @return 수정된 메뉴 데이터와 HTTP 상태코드 200(OK)
   */
  @PatchMapping("/{id}")
  public ResponseEntity<ModifyMenuResponseDto> updateMenu(
      @PathVariable Long storeId,
      @PathVariable Long id,
      @Valid @RequestBody ModifyMenuRequestDto requestDto
  ) {
    ModifyMenuResponseDto responseDto = menuService.updateMenu(storeId, id, requestDto.getName());
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }

  /**
   * 메뉴 삭제 Controller
   * 메뉴 삭제 요청을 처리합니다.
   *
   * @param storeId 가게의 고유 식별자 (PathVariable로 전달)
   * @param id      메뉴의 고유 식별자 (PathVariable로 전달)
   * @return HTTP 상태코드 204(NO_CONTENT).
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMenu(
      @PathVariable Long storeId,
      @PathVariable Long id) {
    menuService.deleteMenu(storeId, id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ResponseDto> getMenu(@PathVariable Long storeId, @PathVariable Long id) {
    ResponseDto findMenu = menuService.getMenu(storeId, id);
    return new ResponseEntity<>(findMenu, HttpStatus.OK);
  }
}
