package pyramid.infra.dispatcher;

import pyramid.infra.appserver.provision.ProvisionMode;

public class Invocable {
	
	private Class<?> invocable ;
	
	private ProvisionMode mode = null;
	
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Invocable))
			return false ;
		
		Invocable inv = (Invocable) obj ;
		
		if (inv.getInvocable().equals(this.getInvocable()) && inv.getServiceName().equals(this.getServiceName())) {
			return true ;
		}
		
		return false ;
		
	}
	
	private String serviceName ;

	public Invocable(String service, Class<?> inter) {
		invocable = inter ;
		serviceName  = service ;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Class<?> getInvocable() {
		return invocable;
	}

	public void setInvocable(Class<?> invocable) {
		this.invocable = invocable;
	}

	/**
	 * If mode is null the invocation will follow global mode set in the store
	 * If invocable doesn't support this mode we will use default binding i.e. no mode supported (error should be logged)
	 * @return
	 */
	public ProvisionMode getProvisionMode() {
		return mode;
	}

	/**
	 * Set provision mode for this invocable
	 * @param mode
	 */
	public void setMode(ProvisionMode mode) {
		this.mode = mode;
	}

}
