package br.com.splessons;

import org.springframework.util.StringUtils;

public class TenantContext {
	
	private static final ThreadLocal<String> TENANT_CONFIG = new ThreadLocal<String>();

	public static final String get(String defaultTenant) {
		String tenantCode = TENANT_CONFIG.get();
		if(StringUtils.isEmpty(tenantCode)) {
			synchronized (TENANT_CONFIG) {
				tenantCode = TENANT_CONFIG.get();
				if(StringUtils.isEmpty(tenantCode)) {
					tenantCode = defaultTenant;
					set(tenantCode);
				}
			}
		}
		return tenantCode;
	}
	
	public static final void set(String tenantCode) {
		TENANT_CONFIG.set(tenantCode);
	}

}