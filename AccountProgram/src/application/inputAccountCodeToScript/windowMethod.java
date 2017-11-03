package application.inputAccountCodeToScript;

import java.time.LocalDateTime;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HWND;

public class windowMethod {
	
	final User32 libUser32 = User32.INSTANCE;
	
	public windowMethod(){
		
	}
	
	public String getClassname(HWND hwnd) {
		char nameA[] = new char[50];
		String temp = "";
		libUser32.GetClassName(hwnd, nameA, 50);
		for (int i =0; i < 50; i++) {
			temp += Character.toString(nameA[i]);
		}
		return temp.trim();
	}
	
	public String getWindowname(HWND hwnd) {
		char nameA[] = new char[50];
		String temp = "";
		libUser32.GetWindowText(hwnd, nameA, 50);

		temp = new String(nameA, 0, nameA.length);
		return temp.trim();
	}
	
	public HWND getHWND(HWND parentHwnd, HWND mainHwnd) {		
		HWND hwnd;
		DWORD gwCHILD = new DWORD(User32.GW_CHILD);
		DWORD gwNEXT = new DWORD(User32.GW_HWNDNEXT);		
		
		if (libUser32.GetWindow(parentHwnd, gwCHILD) != null) {
			hwnd = libUser32.GetWindow(parentHwnd, gwCHILD);
			
		}
		else if (libUser32.GetWindow(parentHwnd, gwNEXT) != null) {
			hwnd = libUser32.GetWindow(parentHwnd, gwNEXT);
			
		}
		else {
			hwnd = getParentNEXT(parentHwnd, mainHwnd);
					
		}

		return hwnd;
		
	}
	
	private HWND getParentNEXT(HWND parentHwnd, HWND mainHwnd) {
		HWND hwnd = null;
		
		DWORD gwNEXT = new DWORD(User32.GW_HWNDNEXT);	
		hwnd = parentHwnd;
		do {									
			if (libUser32.GetWindow(hwnd, gwNEXT) != null) {
				hwnd = libUser32.GetWindow(hwnd, gwNEXT);
				break;
			}
			else if (libUser32.GetWindow(hwnd, gwNEXT) == null) {
				hwnd = libUser32.GetAncestor(hwnd, 1);
				if (hwnd == null) {
					hwnd = mainHwnd;
					break;
				}
			}							
			
		} while (!mainHwnd.equals(hwnd));
		return hwnd;
		
	}
}
