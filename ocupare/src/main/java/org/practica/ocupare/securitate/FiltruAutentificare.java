package org.practica.ocupare.securitate;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

/**
 * 
 * @author mircea
 * Voi folosi metoda basic auth pentru că e cea mai simplă de implementat.
 * Ideea ar fi să trimit tot traficul prin https pentru a nu trimite chiar în clar.
 * O soluție mai serioasă ar folosi OAuth sau asymmetric crypto.
 */
@Provider
public class FiltruAutentificare implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		
		if (!method.isAnnotationPresent(PermitAll.class)) {
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();
			final List<String> authHeader = headers.get(SecurityUtil.AUTHORIZATION_PROPERTY);
			
			/* Nu este autentificat */
			if (authHeader == null || authHeader.isEmpty()) {
				requestContext.abortWith(SecurityUtil.UNAUTHENTICATED_RESPONSE.build());
				return;
			}
			
			
			final String header = authHeader.get(0);
			final String[] userPassPair = SecurityUtil.getUserPassPair(header);
			
			final String user = userPassPair[0];
			final String pass = userPassPair[1];
			
			/* Verifică accesul la resursă */
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotations = method.getAnnotation(RolesAllowed.class);
				Set<String> allowedRolesSet = new HashSet<>(Arrays.asList(rolesAnnotations.value()));
				
				if (!SecurityUtil.isUserAllowedOnMethod(user, pass, allowedRolesSet)) {
					requestContext.abortWith(SecurityUtil.UNAUTHORIZED_RESPONSE.build());
					return;
				}
			}
		}
		
	}
	
	
}
