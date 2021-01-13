package backend.reponses;

public class ConnectionResponse {
	
	private boolean success;
	public ConnectionResponse(boolean success) {
		this.success = success;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	} 

}
