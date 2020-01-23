package nsgl.json;

import nsgl.exception.ProcessException;

public class ProcessExceptionWithJSON extends ProcessException {
	protected JSON manager;
	
	public ProcessExceptionWithJSON( JSON manager ) { this.manager = manager; }
	
	public ProcessExceptionWithJSON(){ this( new JSON() ); }
	
	@Override
	protected String apply(Object[] msg) {
		StringBuilder sb = new StringBuilder();
		String format = manager.getString((String)msg[0]);
		Object[] e_args = new Object[msg.length-1];
		System.arraycopy(msg, 1, e_args, 0, e_args.length);
		sb.append(String.format(format, e_args));
		return sb.toString();
	}
}