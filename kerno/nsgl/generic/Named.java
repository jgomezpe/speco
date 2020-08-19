package nsgl.generic;

public class Named implements Identifiable{
    protected String id;
    
    public Named( String id ) { this.id = id; }
 
    @Override
    public String id() { return id; }

    @Override
    public void id(String id) { this.id = id; }

}
