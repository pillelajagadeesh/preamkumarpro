package appraamlabs.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mine")
public class MineModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String mine;
    private String mineid;
    
    
	public MineModel(String mine, String mineid) {
		super();
		this.mine = mine;
		this.mineid = mineid;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


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
		return "MineModel [id=" + id + ", mine=" + mine + ", mineid=" + mineid + "]";
	}
    
    

}
