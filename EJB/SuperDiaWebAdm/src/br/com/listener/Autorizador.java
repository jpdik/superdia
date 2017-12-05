package br.com.listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.mb.LoginMB;

public class Autorizador implements PhaseListener {

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		if ("/login.xhtml".equals(context.getViewRoot().getViewId()))
			return;
		
		// Obtendo LoginBean da sessão
		LoginMB loginMB = context.getApplication().evaluateExpressionGet(context,
		"#{loginMB}", LoginMB.class);
		if (loginMB.getUsuario().getUsuario() == null) {
			NavigationHandler handler = context.getApplication().getNavigationHandler();
			handler.handleNavigation(context, null, "login?facesredirect=true");
			context.renderResponse();
		}

	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
