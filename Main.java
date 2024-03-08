package com.java.parkingLot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Member> memList = new ArrayList<Member>(); // 회원 데이터베이스
		ArrayList<Car> parkingLot = new ArrayList<Car>(); // 현재 주차된 차량 데이터베이스
		ArrayList<Car> outCars = new ArrayList<Car>(); // 출차된 차량 데이터베이스(통계용)

		boolean run1 = true; // 전체 메뉴 토글
		boolean run1_1 = true; // 1-1 메뉴 토글 - 입출차 관리
		boolean run1_2 = true; // 1-2 메뉴 토글 - 회원 관리
		boolean run1_2_2 = true; // 1-2-2 메뉴 토글 - 회원 관리 - 회원 정보 조회
		boolean run1_2_3 = true; // 1-2-3 메뉴 토글 - 회원 관리 - 회원 정보 수정 및 삭제
		boolean run1_3 = true; // 1-3 메뉴 토글 - 주차장 관리
		boolean run1_3_2 = true; // 1-3-2 메뉴 토글 - 주차장 관리 - 차량 통계
		boolean run1_3_3 = true; // 1-3-3 메뉴 토글 - 주차장 관리 - 주차장 설정
		boolean run1_3_3_2 = true; // 1-3-3-2 메뉴 토글 - 주차장 관리 - 주차장 설정 - 주차장 요금 설정

		int selectMenu1; // 전체 메뉴 선택지
		int selectMenu1_1; // 1-1 메뉴 선택지 - 입출차 관리
		int selectMenu1_2; // 1-2 메뉴 선택지 - 회원 관리
		int selectMenu1_2_2; // 1-2-2 메뉴 선택지 - 회원 관리 - 회원 정보 조회
		int selectMenu1_2_3; // 1-2-3 메뉴 선택지 - 회원 관리 - 회원 정보 수정 및 삭제
		int selectMenu1_3; // 1-3 메뉴 선택지 - 주차장 관리
		int selectMenu1_3_2; // 1-3-2 메뉴 선택지 - 주차장 관리 - 차량 통계
		int selectMenu1_3_3; // 1-3-3 메뉴 선택지 - 주차장 관리 - 주차장 설정
		int selectMenu1_3_3_2; // 1-3-3-2 메뉴 선택지 - 주차장 관리 - 주차장 설정 - 주차장 요금 설정

		while (run1) {
			Utils.showTitle("전체 메뉴");
			List<String> menu1 = Arrays.asList(
			"입출차 관리",
			"회원 관리",
			"주차장 관리",
			"종료"
			);
			selectMenu1 = Utils.showMenu(menu1, sc);
			switch (selectMenu1) {
			case 1:
				run1_1 = true;
				while (run1_1) {
				Utils.showTitle("입출차 관리");
				List<String> menu1_1 = Arrays.asList(
					"차량 입차",
					"출차 정산",
					"입차 차량 조회",
					"이전 메뉴"
				);
				selectMenu1_1 = Utils.showMenu(menu1_1, sc);
				switch (selectMenu1_1) {
					case 1:
					Utils.showTitle("차량 입차");
					if (Car.getCurrentSpace() > 0) {
						Car.carIn(parkingLot, sc);
					} else {
						Utils.showError(1);
					}
					Utils.showParkingLotInfo1();
					break;
					case 2:
					Utils.showTitle("출차 정산");
					if (Car.getCurrentCars() > 0) {
						Car.carOut(parkingLot, outCars, memList, sc);
					} else {
						Utils.showError(2);
					}
					Utils.showParkingLotInfo1();
					break;
					case 3:
					Utils.showTitle("입차 차량 조회");
					if (Car.getCurrentCars() > 0) {
						Car car = Car.getCar(parkingLot, sc);
						if (car != null) {
						Car.showCar(car);
						} else {
						Utils.showError(3);
						}
					} else {
						Utils.showError(2);
					}
					break;
					case 4:
					Utils.backMenu();
					run1_1 = false;
					break;
				}
				}
				break;
			case 2:
				run1_2 = true;
				while (run1_2) {
				Utils.showTitle("회원 관리");
				List<String> menu1_2 = Arrays.asList(
					"회원 등록",
					"회원 정보 조회",
					"회원 정보 수정 및 삭제",
					"이전 메뉴"
				);
				selectMenu1_2 = Utils.showMenu(menu1_2, sc);
				switch (selectMenu1_2) {
					case 1:
					Utils.showTitle("회원 등록");
					Member.regitMember(memList, sc);
					break;
					case 2:
					run1_2_2 = true;
					while (run1_2_2) {
						Utils.showTitle("회원 정보 조회");
						if (memList.size() > 0) {
						List<String> menu1_2_2 = Arrays.asList(
							"전체 조회",
							"특정 조회"
						);
						selectMenu1_2_2 = Utils.showMenu(menu1_2_2, sc);
						switch (selectMenu1_2_2) {
							case 1:
							Utils.showTitle("전체 조회");
							// Member.showAllMemSortedByID(memList);
							Member.showAllMem(memList);
							break;
							case 2:
							Utils.showTitle("특정 조회");
							Member mem = Member.getMem(memList, sc);
							if (mem != null) {
								Member.showMem(mem);
							} else {
								Utils.showError(3);
							}
							break;
						}
						break;
						} else {
						Utils.showError(4);
						run1_2_2 = false;
						}
					}
					break;
					case 3:
					run1_2_3 = true;
					while (run1_2_3) {
						Utils.showTitle("회원 정보 수정 및 삭제");
						if (memList.size() > 0) {
						List<String> menu1_2_3 = Arrays.asList(
							"회원 정보 수정",
							"회원 정보 삭제"
						);
						selectMenu1_2_3 = Utils.showMenu(menu1_2_3, sc);
						switch (selectMenu1_2_3) {
							case 1:
							Utils.showTitle("회원 정보 수정");
							Member.editMember(memList, sc);
							break;
							case 2:
							Utils.showTitle("회원 정보 삭제");
							Member.delMember(memList, sc);
							break;
						}
						break;
						} else {
						Utils.showError(4);
						run1_2_3 = false;
						}
					}
					break;
					case 4:
					Utils.backMenu();
					run1_2 = false;
					break;
				}
				}
				break;
			case 3:
				run1_3 = true;
				while (run1_3) {
				Utils.showTitle("주차장 관리");
				List<String> menu1_3 = Arrays.asList(
					"현재 주차장 현황",
					"차량 통계",
					"주차장 설정",
					"이전 메뉴"
				);
				selectMenu1_3 = Utils.showMenu(menu1_3, sc);
				switch (selectMenu1_3) {
					case 1:
					Utils.showTitle("현재 주차장 현황");
					if (Car.getCurrentCars() > 0) {
						Car.showAllCar(parkingLot);
						Utils.showParkingLotInfo1();
						Utils.showParkingLotInfo2();
					} else {
						Utils.showError(2);
					}
					break;
					case 2:
					run1_3_2 = true;
					while (run1_3_2) {
						Utils.showTitle("차량 통계");
						if (Car.getCurrentCars() > 0 || Car.getOutCars() > 0) {
						List<String> menu1_3_2 = Arrays.asList(
							"입출차 내역 조회",
							"결제 내역 조회"
						);
						selectMenu1_3_2 = Utils.showMenu(menu1_3_2, sc);
						switch (selectMenu1_3_2) {
							case 1:
							Utils.showTitle("입출차 내역 조회");
							Utils.showStatistics1(parkingLot, outCars);
							break;
							case 2:
							Utils.showTitle("결제 내역 조회");
							if (Car.getOutCars() > 0) {
								Utils.showStatistics2(outCars);
							} else {
								Utils.showError(3);
							}
							break;
						}
						} else {
						Utils.showError(3);
						run1_3_2 = false;
						}
					}
					break;
					case 3:
					run1_3_3 = true;
					while (run1_3_3) {
						Utils.showTitle("주차장 설정");
						List<String> menu1_3_3 = Arrays.asList(
						"주차장 크기 설정",
						"주차장 요금 설정"
						);
						selectMenu1_3_3 = Utils.showMenu(menu1_3_3, sc);
						switch (selectMenu1_3_3) {
						case 1:
							Utils.showTitle("주차장 크기 설정");
							System.out.printf(
							"현재 주차장 크기 : %7s대\n",
							Car.getTotalSpace()
							);
							System.out.print("주차장 크기 ");
							Car.setTotalSpace(Utils.getInt(sc));
							Car.setCurrentSpace();
							break;
						case 2:
							run1_3_3_2 = true;
							while (run1_3_3_2) {
							Utils.showTitle("주차장 요금 설정");
							List<String> menu1_3_3_2 = Arrays.asList(
								"기본 요금 설정",
								"10분당 요금 설정",
								"할인율 설정"
							);
							selectMenu1_3_3_2 = Utils.showMenu(menu1_3_3_2, sc);
							switch (selectMenu1_3_3_2) {
								case 1:
								Utils.showTitle("기본 요금 설정");
								System.out.printf(
									"현재 기본 요금 : %7d원\n",
									Car.getBasicCharge()
								);
								System.out.print("기본 요금 ");
								Car.setBasicCharge(Utils.getInt(sc));
								break;
								case 2:
								Utils.showTitle("10분당 요금 설정");
								System.out.printf(
									"현재 10분당 요금 : %7d원\n",
									Car.getPayTen()
								);
								System.out.print("10분당 요금 ");
								Car.setPayTen(Utils.getInt(sc));
								break;
								case 3:
								Utils.showTitle(
									"할인율 설정(0.3 입력 -> 30% 할인)"
								);
								System.out.printf(
									"현재 경차 할인율 : %7.1f | 현재 회원 할인율 : %7.1f\n",
									Car.getLightDiscount(),
									Car.getMemberDiscount()
								);
								System.out.print("경차 할인율 ");
								Car.setLightDiscount(
									parkingLot,
									Utils.getDouble(sc)
								);
								System.out.print("회원 할인율 ");
								Car.setMemberDiscount(Utils.getDouble(sc));
								break;
							}
							break;
							}
							break;
						}
						break;
					}
					break;
					case 4:
					Utils.backMenu();
					run1_3 = false;
					break;
				}
				}
				break;
			case 4:
				Utils.showTitle("시스템 종료");
				run1 = false;
				break;
			}
		}
	}
}
