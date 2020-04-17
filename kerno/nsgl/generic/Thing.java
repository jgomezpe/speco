package nsgl.generic;

public class Thing implements Named{
	protected String id;
	public Thing( String id ){ this.id = id; }
	@Override
	public void setId(String id) { this.id = id; }
	@Override
	public String id() { return id; }
}