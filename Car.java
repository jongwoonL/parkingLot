package com.java.parkingLot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Car {
	Scanner sc = new Scanner(System.in);
	
	private String carNum = null; // 차량 번호
	private String carType = null; // 차량 종류
	private int paidAmount = 0; // 지불한 금액
	private int totalPay = 0; // 전체 지불할 요금
	private boolean isPaid = false; // 정산 여부
	private LocalDateTime timeIn = LocalDateTime.now(); // 입차 시간
	private LocalDateTime timeOut = null; // 출차 시간
	private double discount = 0; // 차량별 적용 할인
	
	private static int totalSpace = 30; // 주차장 크기
	private static int currentCars = 0; // 현재 주차 차량
	private static int currentSpace = totalSpace - currentCars; // 남은 자리
	private static int basicCharge = 3000; // 기본 요금
	private static int payTen = 1000; // 10분당 요금
	private static double lightDiscount = 0.5; // 경차 할인 50%
	private static double memberDiscount = 0.3; // 회원 할인 30%
	
	private static int outCars = 0; // 출차 차량
	private static int outCarsTotalPay = 0; // 출차 차량 요금 통계
	
	// 생성자
	public Car(String num, String type) {
		this.carNum = num;
		this.carType = type;				
	}
	
	// getter
	public String getCarNum() {
		return carNum;
	}	
	public String getCarType() {
		return carType;
	}	
	public int getPaidAmount() {
		return paidAmount;
	}	
	public int getTotalPay() {
		return totalPay;
	}	
	public boolean getIsPaid() {
		return isPaid;
	}	
	public LocalDateTime getTimeIn() {
		return timeIn;
	}	
	public LocalDateTime getTimeOut() {
		return timeOut;
	}
	public double getDiscount() {
		return discount;
	}
	
	// static getter
	public static int getTotalSpace() {
		return totalSpace;
	}
	public static int getCurrentCars() {
		return currentCars;
	}
	public static int getCurrentSpace() {
		return currentSpace;
	}	
	public static int getBasicCharge() {
		return basicCharge;
	}	
	public static int getPayTen() {
		return payTen;
	}
	public static double getLightDiscount() {
		return lightDiscount;
	}
	public static double getMemberDiscount() {
		return memberDiscount;
	}
 	public static int getOutCars() {
		return outCars;
	}
 	public static int getOutCarsTotalPay() {
 		return outCarsTotalPay;
 	}
	
	// setter
	public void setCarNum(String num) {
		carNum = num;
	}
	public void setCarType(String type) {
		carType = type;
	}	
	public void setPaidAmount(int pa) {
		paidAmount = pa;
	}	
	public void setTotalPay(int tp) {
		totalPay = tp;
	}	
	public void setIsPaid(boolean ip) {
		isPaid = ip;
	}	
	public void setTimeIn(LocalDateTime ti) {
		timeIn = ti;
	}	
	public void setTimeOut(LocalDateTime to) {
		timeOut = to;
	}
	public void setDiscount(double dc) {
		discount = dc;
	}
	
	// static setter
	public static void setTotalSpace(int ts) {
		totalSpace = ts;
		System.out.println("주차장 크기 변경 성공");
	}
	public static void setCurrentCars(int cc) {
		currentCars = cc;
	}
	public static void setCurrentSpace() { // 현재 주차장 크기 갱신용
		currentSpace = totalSpace - currentCars;
	}
	public static void setBasicCharge(int bc) {
		basicCharge = bc;
		System.out.println("기본 요금 변경 성공");
	}	
	public static void setPayTen(int pt) {
		payTen = pt;
		System.out.println("10분당 요금 변경 성공");
	}
	
	// 차량 입차 시 적용되므로 변경시 기존 차량 변경 코드 필요
	public static void setLightDiscount(ArrayList<Car> list, double dc) {
		lightDiscount = dc;
		for(Car car : list) {
			if(car.carType.equals("경차")) {
			car.discount = lightDiscount;
			}
		}
		System.out.println("경차 할인율 변경 성공");
	}
	
	// 차량 출차 시 적용되므로 기존 차량 변경 필요 없음
	public static void setMemberDiscount(double dc) {
		memberDiscount = dc;
		System.out.println("회원 할인율 변경 성공");
	}
	public static void setOutCars(int oc) {
		outCars = oc;
	}
	
	// 차량 입차 + 중복 검사
	public static void carIn(ArrayList<Car> list, Scanner sc) {
		boolean run = true;
		boolean duplication = false;
		String num = null;
		
		while(run) {
			System.out.print("입차 차량 번호 > ");
	        num = sc.nextLine();
			
			for(Car car : list) {
				if(num.equals(car.carNum)) {	
					duplication = true;
				}
			}			
			if(duplication == true) {
				System.out.println("이미 입차된 차량 입니다. 다시 입력하세요.");
				duplication = false;
			} else {
				run = false;
			}
		}
		
        System.out.print("차량 종류(1. 일반, 2. 경차) > ");
        int select = sc.nextInt();
        sc.nextLine();
        String type = null;
        
        Car car = new Car(num, type);
        
        switch(select) {
        case 1 : 
        	car.setCarType("일반");
        	car.setDiscount(0);
        	break;
        
        case 2 : 
        	car.setCarType("경차");
        	car.setDiscount(lightDiscount);
        	break;
        }
        
        list.add(car);
        currentCars += 1;
        currentSpace = totalSpace - currentCars; 
        System.out.println("차량 입차 성공");      
	}
	
	// 차량 출차
	public static void carOut(ArrayList<Car> list1, ArrayList<Car> list2, ArrayList<Member> list3, Scanner sc) {
		System.out.print("출차 차량 번호 > ");
        String num = sc.nextLine();
        
        // 차량 호출
        Car car = getCar(list1, num);
        
        // 멤버 호출
        Member mem = Member.getMem(list3, num);
        
        // 차량 유무 검증
        if(car != null) {
        	// 출차 시간 등록
        	car.timeOut = LocalDateTime.now();
        	
        	// 입출차 시간 추출
        	LocalTime in = car.timeIn.toLocalTime();
        	LocalTime out = car.timeOut.toLocalTime();
        	
        	// 시간 차 계산 후 분단위 전환
        	Duration diff = Duration.between(in, out);
        	int spendTime = (int)diff.toMinutes() + 66;
        	
        	// 회원 할인 적용(입차 후 회원 가입해도 적용됨)
        	if(mem != null) {
        		if(car.getDiscount() != 0) { // 경차 차량 && 회원 할인(0.5 * (1.0 + 0.3)) = 0.65(65% 할인);
        			car.setDiscount(car.getDiscount() * (1.0 + Car.getMemberDiscount()));
        		} else { // 일반 차량 && 회원 할인(0.3) = 0.3(30% 할인);
        			car.setDiscount(Car.getMemberDiscount());
        		}
        	}
        	
        	// 전체 요금 계산 = 기본 요금 + (i)((i(분단위절삭) -> d)분단위 절삭 단위 시간 * (d)10분당 요금 * (d)(1.0 - 할인))
        	car.totalPay = Car.basicCharge + (int)(((double)(spendTime / 10)) * (double)Car.payTen * (1.0 - car.discount));
        	
        	System.out.printf("지불해야할 금액 : %7d원\n", car.totalPay);
        	
        	// paidAmount가 totalPay에 도달할 때 까지 지불 요청
        	while(!car.isPaid) {
	        	System.out.print("지불할 금액 > ");
	        	int payment = sc.nextInt();
	        	
	        	car.paidAmount += payment;
	        	if(car.paidAmount < car.totalPay) {
	        		int left = car.totalPay - car.paidAmount;
	        		System.out.printf("남은 금액 %d원 입니다.\n", left);
	        		System.out.println("다시 정산해주세요.\n");
	        	}
	        	if(car.paidAmount == car.totalPay) {
	        		car.isPaid = true;
	        		System.out.println("정산 완료되었습니다. 감사합니다.\n");
	        	}
	        	if(car.paidAmount > car.totalPay) {
	        		int change = car.paidAmount - car.totalPay;
	        		car.isPaid = true;
	        		System.out.printf("거스름돈 %d원 입니다.\n", change);
	        		System.out.println("정산 완료되었습니다. 감사합니다.\n");
	        	}
        	}
        	
        	// 지불 완료 시 출차 및 주차장 자리 연산
	        list1.remove(car);
	        currentCars -= 1;
	        currentSpace = totalSpace - currentCars;
	        
	        // 출차 후 출차 목록에 저장
	        list2.add(car);
	        outCars += 1;
	        outCarsTotalPay += car.totalPay;
	        System.out.println("차량 출차 성공");
	    
	    // 차량 없을 시 오류코드
        } else {
        	Utils.showError(3);
        }	
	}
	
	// 차량 찾기
	public static Car getCar(ArrayList<Car> list, String info) {
	    Car wantCar = null;    
		for (Car car : list) {
			if (car.carNum.equals(info)) {
				wantCar = car;
			}
	    }
		return wantCar;
	}
	
	// 차량 찾기(사용자 입력)
	public static Car getCar(ArrayList<Car> list, Scanner sc) {
	    System.out.print("차량 번호 입력 > ");
	    String info = sc.nextLine();
		Car wantCar = null;
		for (Car car : list) {
			if (car.carNum.equals(info)) {
				wantCar = car;
			} 
	    }
		return wantCar;
	}
	
	// 차량 정보 출력(특정)
	public static void showCar(Car car) {
		System.out.printf("차량 번호 : %s | 차량 종류 : %s | 정산 여부 : %s | 입차 시간 : %s | 출차 시간 : %s\n",
		car.carNum, car.carType, Utils.getIsPaid(car.isPaid), Utils.getTimeFormatter(car.timeIn), Utils.getTimeFormatter(car.timeOut));
	}
	
	// 차량 정보 출력(전체)
	public static void showAllCar(ArrayList<Car> list) {
		for(Car car : list) {
			System.out.printf("차량 번호 : %s | 차량 종류 : %s | 정산 여부 : %s | 입차 시간 : %s | 출차 시간 : %s\n",
			car.carNum, car.carType, Utils.getIsPaid(car.isPaid), Utils.getTimeFormatter(car.timeIn), Utils.getTimeFormatter(car.timeOut));
		}
	}
	
	// 차량 정보 출력(금액 포함 전체)
	public static void showAllCar2(ArrayList<Car> list) {
		for(Car car : list) {
			System.out.printf("차량 번호 : %s | 차량 종류 : %s | 정산 금액 : %6d원 | 입차 시간 : %s | 출차 시간 : %s\n",
			car.carNum, car.carType, car.totalPay, Utils.getTimeFormatter(car.timeIn), Utils.getTimeFormatter(car.timeOut));
		}
	}
}
