package uib.teamdank.common.util;

public enum CType {
	// These constants are used to set category
	// and mask for collision filters and
	// are to the power of two, 2^x where x = 0 -> 15
	CATEGORY_PLAYER ((short) 1), 
	CATEGORY_WORLD ((short) 2),
	CATEGORY_PROJECTILE ((short) 4);
	
	private short value;    
	
	private CType(short value) {
		this.value = value;
	}
	
	public short getValue() {
		return value;
	}
}
