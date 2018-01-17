package appraamlabs.service.dtos;

import java.io.Serializable;

public class MineDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String mine;
    private String mineid;
	public String getMine() {
		return mine;
	}
	public void setMine(String mine) {
		this.mine = mine;
	}
	public String getMineid() {
		return mineid;
	}
	public void setMineid(String mineid) {
		this.mineid = mineid;
	}
	@Override
	public String toString() {
		return "MineDTO [mine=" + mine + ", mineid=" + mineid + "]";
	}
    
    
}
