package com.java.parkingLot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    Scanner sc = new Scanner(System.in);

    public static int showMenu(List<String> menu, Scanner sc) {
    	while(true) {
    		try {
		        int i = 1;
		        for(String me : menu) {
		            System.out.printf("%d. %-15s", i, me);
		            i++;
		        }
		        System.out.print("\n> ");
		        int selectNum = sc.nextInt();
		        sc.nextLine();
		        if (selectNum > menu.size() || selectNum < 1) {
                    throw new IllegalArgumentException("범위 내 숫자 값을 입력해 주세요.");
                }		        
		        return selectNum;		        
	        }
    	    catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
    	    }
    		catch(Exception e) {
    			System.out.println("잘못된 입력입니다.");
    			sc.nextLine();
    		}
    	}
    }
    
    public static void backMenu() {
    	System.out.printf("%25s[%s]\n", "","이전 메뉴로 돌아갑니다.");
    }
    
    public static void showTitle(String title) {
    	System.out.printf("\n%30s[%s]\n", "", title);
    }
    
    public static String getIsPaid(boolean isPaid) {
    	String getIsPaid;
    	if(isPaid) {
    		getIsPaid = "정산 완료";
    	} else {
    		getIsPaid = "출차 전";
    	}
    	return getIsPaid;
    }
    
    public static String getTimeFormatter(LocalDateTime iTime) {
    	LocalDateTime time = iTime;
    	String formatedNow;
    	if(iTime != null) {
    		formatedNow = time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")); // 포맷팅
    	} else {
    		formatedNow = "출차 전"; // null 이면 출차 전
    	}
		return formatedNow; // 반환
    }
    
    public static int getInt(Scanner sc) {
    	int get;
    	while(true) {
    		try {
    			System.out.print("변경 사항 입력 > ");
    			get = sc.nextInt();
    			return get;
    		}
    		catch(Exception e) {
    			System.out.println("잘못된 입력입니다.");
    			sc.nextLine();
    		}
    	}
    }
    
    public static double getDouble(Scanner sc) {
    	double get;
    	while(true) {
    		try {
    			System.out.print("변경 사항 입력 > ");
    			get = sc.nextDouble();
    			return get;
    		}
    		catch(Exception e) {
    			System.out.println("잘못된 입력입니다.");
    			sc.nextLine();
    		}
    	}
    }
    
    public static String getString(Scanner sc) {
    	String get;
    	while(true) {
    		try {
    			System.out.print("변경 사항 입력 > ");
    			get = sc.nextLine();
    			return get;
    		}
    		catch(Exception e) {
    			System.out.println("잘못된 입력입니다.");
    		}
    	}
    }
    
    // 현재 입차 현황 출력
    public static void showParkingLotInfo1() {
    	System.out.printf("현재 차량 : %7d대 | 남은 자리   : %7d대\n", Car.getCurrentCars(), Car.getCurrentSpace());
    }
    
    // 현재 주차장 설정 출력
    public static void showParkingLotInfo2() {
    	System.out.printf("기본 요금 : %7d원 | 10분당 요금 : %7d원\n", Car.getBasicCharge(), Car.getPayTen());
    }
    
    // 현재 출차 현황 출력
    public static void showParkingLotInfo3() {
    	System.out.printf("출차 차량 : %7d대\n", Car.getOutCars(), Car.getCurrentCars()+Car.getOutCars());
    }
    
    // 현재 입출차 현황 출력
    public static void showParkingLotInfo4() {
    	System.out.printf("총 입출차 차량 : %7d대\n", Car.getCurrentCars()+Car.getOutCars());
    }
    
    // 현재 출차 금액 통계 출력
    public static void showParkingLotInfo5() {
    	System.out.printf("총 결제 금액 : %7d원\n", Car.getOutCarsTotalPay());
    }
    
    
    // 입출차 통계
    public static void showStatistics1(ArrayList<Car> list1, ArrayList<Car> list2) {
		Utils.showTitle("현재 입차 차량");
		Car.showAllCar(list1);
		Utils.showTitle("출차 차량");
		Car.showAllCar(list2);
		Utils.showParkingLotInfo1();
		Utils.showParkingLotInfo3();
    }
    
    // 결제 요금 통계
    public static void showStatistics2(ArrayList<Car> list1) {
  	  Utils.showTitle("출차 차량");
  	  Car.showAllCar2(list1);
  	  Utils.showParkingLotInfo3();
  	  Utils.showParkingLotInfo5();
    }
    
    public static void showError(int num) {
    	switch(num) {
	    	case 1 :
	    		System.out.println("현재 주차장이 가득 찼습니다.");
	    		break;
	    	case 2 :
	    		System.out.println("현재 주차된 차량이 없습니다.");
	    		break;
	    	case 3 :
	    		System.out.println("해당 정보를 찾을 수 없습니다.");
	    		break;
	    	case 4 :
	    		System.out.println("현재 등록된 회원이 없습니다.");
	    		break;
	    	case 5 :
	    		System.out.println("정산이 완료되지 않았습니다.");
	    		break;
    	}
    }
    
//    public static void showTimeFormatter(LocalDateTime iTime) {
//		LocalDateTime time = iTime;    
//		String formatedNow = time.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")); // 포맷팅    
//		System.out.println(formatedNow); // 출력
//    }
}
