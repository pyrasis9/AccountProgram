package application;

public enum enumSub1Tab1 {
	
	Item0("외상대 입력"), Item1("예수금 입력"), Item2("환경설정");
	
    private String itemName;
    private Object[] obj;
    
    enumSub1Tab1(String itemName){
        //System.out.println("Call Constructor "+this);
        this.itemName = itemName;        
    }
    
    public String getItemName(){
        return this.itemName;
    }

    int getTabIndex(enumSub1Tab1 enumTab1sub1Item) {
    	enumSub1Tab1 i = enumTab1sub1Item;
    	switch (i) {
    	case Item0:
    		return 0;
    	case Item1:
    		return 1;
    	case Item2:
    		return 2;	
    	
    	default : 
    		return 0;	
    	}
    }
    
    public Object[] getItemArrya() {
    	obj = new Object[getCountItem()];
    	for (int i =0; i < obj.length; i++) {
    		
    	}
    	obj[0] = Item0.getItemName();
		return obj;
    }
    
    public int getCountItem() {
		return 3;
    }
    
}