package com.sparta.outsourcing.service.menu;

import com.sparta.outsourcing.dto.menu.CreateMenuResponseDto;
import com.sparta.outsourcing.dto.menu.ModifyMenuResponseDto;
import com.sparta.outsourcing.dto.menu.ResponseDto;
import com.sparta.outsourcing.entity.Menu;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.repository.StoreRepository;
import com.sparta.outsourcing.repository.menu.MenuRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
 * Service 계층 , 비즈니스 로직 처리
 */
@Service

/*
 * 생성자 의존성 주입
 */
@RequiredArgsConstructor
public class MenuService {

  /*
   * 메뉴 관련 데이터 액세스를 처리하는 레포지토리 객체
   */
  private final MenuRepository menuRepository;
  private final StoreRepository storeRepository;

  /*
   * 메뉴를 생성하고 저장한 후, 저장된 메뉴 정보를 반환하기 위한 메서드
   */
  public CreateMenuResponseDto createMenu(Long storeId, String name, Integer price) {

    Store store = storeRepository.findByIdOrElseThrow(storeId);
    Menu savedMenu = menuRepository.save(new Menu(name, price, store));
    return new CreateMenuResponseDto(savedMenu.getId(), storeId, savedMenu.getName(), savedMenu.getPrice(), savedMenu.getCreatedAt());
  }

  /**
   * 메뉴 제목 수정
   * 해당 메뉴가 존재하지 않으면 예외가 발생
   *
   * @param storeId 가게의 고유 식별자
   * @param id      수정할 메뉴의 고유 식별자
   * @param name    새로운 메뉴 이름
   * @return 수정된 메뉴 정보를 담은 DTO
   */
  @Transactional
  public ModifyMenuResponseDto updateMenu(Long storeId, Long id, String name) {
    Menu findMenu = menuRepository.findByIdOrElseThrow(id);

    // 메뉴가 해당 가게에 속하는지 검증
    isMenuInStore(storeId, findMenu.getStore().getId());
    findMenu.updateMenu(name);
    return new ModifyMenuResponseDto(findMenu.getId(), storeId, findMenu.getName(), findMenu.getPrice(), findMenu.getCreatedAt());
  }

  /**
   * 메뉴를 삭제
   * 해당 메뉴가 존재하지 않으면 예외가 발생
   * @param storeId 가게의 고유 식별자
   * @param id      삭제할 메뉴의 고유 식별자
   */
  public void deleteMenu(Long storeId, Long id) {

    Menu findMenu = menuRepository.findByIdOrElseThrow(id);
    isMenuInStore(storeId, findMenu.getStore().getId());
    menuRepository.delete(findMenu);
  }
  /**
   * 메뉴가 지정된 가게에 속하는지 확인하는 유효성 검사 메서드.
   * - `storeId`와 `menuStoreId`를 비교하여, 메뉴가 가게에 속하지 않으면 예외를 던집니다.
   * @param storeId     가게의 고유 식별자
   * @param menuStoreId 메뉴가 속한 가게의 고유 식별자
   * @throws IllegalArgumentException 메뉴가 지정된 가게에 속하지 않을 경우 발생
   */
  private void isMenuInStore(Long storeId, Long menuStoreId) {
    if (!storeId.equals(menuStoreId)) {
      throw new IllegalArgumentException("해당 메뉴는 지정된 가게에 속하지 않습니다. ");
    }
  }

  public ResponseDto getMenu(Long storeId, Long id) {

    Menu findMenu = menuRepository.findByIdOrElseThrow(id);
    isMenuInStore(storeId, findMenu.getStore().getId());
    return new ResponseDto(findMenu.getId(), storeId, findMenu.getName(), findMenu.getPrice(), findMenu.getCreatedAt());
  }
}