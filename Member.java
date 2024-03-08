package com.java.parkingLot;

import java.util.ArrayList;
// import java.util.Collections;
import java.util.Scanner;

public class Member { // implements Comparable<Member> {
    Scanner sc = new Scanner(System.in);

    private String memID = null;
    private String memName = null;
    private String memCarNum = null;

    // 생성자
    public Member(String id, String name, String carNum) {
        this.memID = id;
        this.memName = name;
        this.memCarNum = carNum;
    }
    
    // getter
    public String getId() {
        return memID;
    }
    public String getName() {
        return memName;
    }
    public String getPass() {
        return memCarNum;
    }

    // setter
    public void setID(String id) {
        memID = id;
    }
    public void setName(String name) {
        memName = name;
    }
    public void setCarNum(String carNum) {
        memCarNum = carNum;
    }
    
    // 회원 등록 + 중복 검사
	public static void regitMember(ArrayList<Member> list, Scanner sc) {
		boolean run = true;
		boolean duplication = false;
		String id = null;
		
		while(run) {
			System.out.print("회원 ID > ");
			id = sc.nextLine();
			
			for(Member mem : list) {
				if(id.equals(mem.memID)) {	
					duplication = true;
				}
			}			
			if(duplication == true) {
				System.out.println("중복된 ID 입니다. 다시 입력하세요.");
				duplication = false;
			} else {
				run = false;
			}
		}
			
        System.out.print("회원 이름 > ");
        String name = sc.nextLine();

        System.out.print("회원 차량 번호 > ");
        String carNum = sc.nextLine();

        Member mem = new Member(id, name, carNum);
        list.add(mem);
        System.out.println("회원 등록 성공");
	}
	
	// 회원 수정
	public static void editMember(ArrayList<Member> list, Scanner sc) {        
        System.out.print("수정할 회원 ID 입력 > ");
        String wantMem = sc.nextLine();        
		Member mem = getMem(list, wantMem);
        if(mem != null) {
        	System.out.print("회원 이름 ");
	        mem.setName(Utils.getString(sc));
	        
	        System.out.print("차량 번호 ");
	        mem.setCarNum(Utils.getString(sc));
	        System.out.println("회원 수정 성공");
        } else {
        	Utils.showError(3);
        }	
	}
		
	// 회원 삭제
	public static void delMember(ArrayList<Member> list, Scanner sc) {        
        System.out.print("삭제할 회원 ID 입력 > ");
		Member mem = getMem(list, Utils.getString(sc));
        if(mem != null) {
	        list.remove(mem);
	        System.out.println("회원 삭제 성공");
        } else {
        	Utils.showError(3);
        }	
	}
	
	// 회원 찾기
	public static Member getMem(ArrayList<Member> list, String info) {
	    Member wantMem = null;    
		for (Member mem : list) {
			if (mem.memID.equals(info)) {
	                wantMem = mem;
			}
			if (mem.memName.equals(info)) {
                wantMem = mem;
			}
			if (mem.memCarNum.equals(info)) {
                wantMem = mem;
			}
	    }
		return wantMem;
	}
	
	// 회원 찾기(사용자 입력)
	public static Member getMem(ArrayList<Member> list, Scanner sc) {
	    System.out.print("회원 정보 입력(ID, 이름, 차량 번호) > ");
	    String info = sc.nextLine();
	    Member wantMem = null;    
		for (Member mem : list) {
			if (mem.memID.equals(info)) {
	                wantMem = mem;
			}
			if (mem.memName.equals(info)) {
                wantMem = mem;
			}
			if (mem.memCarNum.equals(info)) {
                wantMem = mem;
			}
	    }
		return wantMem;
	}
	
	// 회원 정보 출력(특정)
	public static void showMem(Member mem) {
		System.out.printf("회원 ID : %s | 회원 이름 : %s | 차량 번호 : %s\n",
				mem.memID, mem.memName, mem.memCarNum);
	}
	
	// 회원 정보 출력(전체)
	public static void showAllMem(ArrayList<Member> list) {
		for(Member mem : list) {
			System.out.printf("회원 ID : %s | 회원 이름 : %s | 차량 번호 : %s\n",
					mem.memID, mem.memName, mem.memCarNum);
		}
	}

//	// 회원 정보 출력(ID 정렬)
//	public static void showAllMemSortedByID(ArrayList<Member> list) {
//		ArrayList<Member> sortingList = new ArrayList<Member>();
//		sortingList.addAll(list);
//		Collections.sort(sortingList);
//		for(Member mem : sortingList) {
//			System.out.printf("회원 ID : %s | 회원 이름 : %s | 차량 번호 : %s\n",
//					mem.memID, mem.memName, mem.memCarNum);
//		}
//		sortingList.removeAll(sortingList);
//	}
//
//	@Override // 정렬
//	public int compareTo(Member o) {
//		Member m = o;		
//		return Integer.parseInt(this.memID) - Integer.parseInt(m.memID); // 나이가 같으면 0을 반환 (오름차순 정렬)
//	}
}
